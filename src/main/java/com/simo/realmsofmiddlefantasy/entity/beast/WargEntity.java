package com.simo.realmsofmiddlefantasy.entity.beast;

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
 * Warg - Massive wolf-like beast.
 * Hunts in packs, aggressive, and fast.
 * Can call for reinforcements and coordinate attacks with pack members.
 */
public class WargEntity extends Monster implements GeoEntity {

    // Constant to control the scale of the Warg entity
    private static final float SCALE = 1.2F;  // Increase size to reflect its wolf-like stature

    // Animation cache for GeckoLib
    private final AnimatableInstanceCache animationCache =
            GeckoLibUtil.createInstanceCache(this);

    public WargEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    // Defining the attributes for the Warg entity, including health, speed, and damage
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 28.0D)  // Moderate health for a predatory beast
                .add(Attributes.MOVEMENT_SPEED, 0.38D)  // Agile, fast movement for hunting
                .add(Attributes.ATTACK_DAMAGE, 5.0D)  // Moderate attack damage for a large wolf
                .add(Attributes.FOLLOW_RANGE, 32.0D);  // Sizable follow range for tracking prey
    }

    // AI goals: Define behavior like attacking, leaping, and tracking players
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));  // Allows the Warg to float
        goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.5F));  // Leap at target for a pounce attack
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3D, false));  // Melee attack for savage bites
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));  // Avoid water while moving
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at players in range
        goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random look-around behavior

        targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Targets those who hurt it

        // Attack players and enemies of nature (such as Orcs, Goblins, etc.)
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    // Animations: Define different states (idle, attacking, running, etc.)
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private PlayState animationPredicate(AnimationState<WargEntity> state) {
        if (isDeadOrDying()) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (swinging) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("bite_savage", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (state.isMoving()) {
            state.getController().setAnimation(
                    RawAnimation.begin().then(
                            isSprinting() ? "sprint_pack" : "run_hunt",  // Sprinting in a pack or hunting mode
                            Animation.LoopType.LOOP
                    )
            );
        } else {
            state.getController().setAnimation(
                    RawAnimation.begin().then("idle_prowl", Animation.LoopType.LOOP)  // Prowl animation when idle
            );
        }

        return PlayState.CONTINUE;
    }

    // Return the animation cache instance for GeckoLib
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }

    // Optional: You could add logic for the Warg to call for reinforcements when nearby Wargs are present.
    // This would trigger a group attack animation for multiple Wargs surrounding their target.

    // Optional: Add howl or pack-bonding behavior, where the Warg howls to summon other Wargs.
}
