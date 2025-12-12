package com.simo.realmsofmiddlefantasy.entity.men;

import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcCaptainEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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
 * Soldier of Gondor - Standard infantry of Minas Tirith.
 * Well-trained warrior with sword and shield.
 * Forms the backbone of Gondor's military forces.
 */
public class SoldierOfGondorEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SoldierOfGondorEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)      // Elite guards, very tanky
                .add(Attributes.MOVEMENT_SPEED, 0.28D)  // Slower due to heavy armor
                .add(Attributes.ATTACK_DAMAGE, 6.0D)    // Strong attacks
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ARMOR, 7.0D)           // Very high armor
                .add(Attributes.ARMOR_TOUGHNESS, 3.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // Can float in water
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false)); // Melee attack
        this.goalSelector.addGoal(2, new ShieldDefenseGoal(this)); // New shield defense goal
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 0.65D)); // Random walking
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F)); // Look at player
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this)); // Random looking around

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // Aggro logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false,
            entity -> entity instanceof OrcEntity || entity instanceof OrcScoutEntity || 
                      entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                      entity instanceof UrukHaiEntity || entity instanceof GoblinEntity || 
                      entity instanceof WargEntity));
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<SoldierOfGondorEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.gondor_soldier.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.gondor_soldier.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.gondor_soldier.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}

// New goal class for shield defense
class ShieldDefenseGoal extends Goal {
    private final SoldierOfGondorEntity soldier;
    
    public ShieldDefenseGoal(SoldierOfGondorEntity soldier) {
        this.soldier = soldier;
    }

    @Override
    public boolean canUse() {
        return this.soldier.getHealth() < this.soldier.getMaxHealth() * 0.5; // Trigger defense when health is low
    }

    @Override
    public void tick() {
        // In defense mode, reduce incoming damage
        // Logic for shield defense animation can be added here
    }
}
