package com.simo.realmsofmiddlefantasy.entity.orc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Uruk-hai - Elite version of Orcs, powerful and aggressive.
 * A terror on the battlefield, with superior combat skills.
 */
public class UrukHaiEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public UrukHaiEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 35.0D)  // Elite health
                .add(Attributes.MOVEMENT_SPEED, 0.28D)  // Slightly slower than standard orcs, but resilient
                .add(Attributes.ATTACK_DAMAGE, 7.0D)  // High attack damage
                .add(Attributes.FOLLOW_RANGE, 26.0D)  // Standard follow range
                .add(Attributes.ARMOR, 8.0D)  // Elite armor
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);  // Higher resistance to knockback
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));  // Allow to float in water
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));  // Standard melee attack
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.9D));  // Random movement
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random look around

        // Aggressive targeting
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Aggro logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));  // Attack players
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<UrukHaiEntity> event) {
        // Death animation
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                    RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        // Brutal strike animation during attack
        if (this.swinging) {
            event.getController().setAnimation(
                    RawAnimation.begin().then("brutal_strike", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        // Walking animation with weapon ready
        if (event.isMoving()) {
            event.getController().setAnimation(
                    RawAnimation.begin().then("march_uruk", Animation.LoopType.LOOP)
            );
        } else {
            // Idle animation with a more aggressive posture for elite units
            event.getController().setAnimation(
                    RawAnimation.begin().then("idle_elite", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
