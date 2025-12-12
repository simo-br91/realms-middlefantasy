package com.simo.realmsofmiddlefantasy.entity.custom;

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
 * Citadel Guard - Elite guard of Minas Tirith's highest tier.
 * Heavily armored defender of the White Tower and royal court.
 * Superior combat skills and exceptional armor.
 */
public class CitadelGuardEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public CitadelGuardEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 38.0D)      // Elite guards, very tanky
                .add(Attributes.MOVEMENT_SPEED, 0.26D)  // Slower due to heavy armor
                .add(Attributes.ATTACK_DAMAGE, 7.0D)    // Strong attacks
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ARMOR, 10.0D)           // Very high armor
                .add(Attributes.ARMOR_TOUGHNESS, 3.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false,
            entity -> entity instanceof OrcEntity || entity instanceof OrcScoutEntity || 
                      entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                      entity instanceof UrukHaiEntity || entity instanceof GoblinEntity || 
                      entity instanceof WargEntity || entity instanceof HillTrollEntity ||
                      entity instanceof CaveTrollEntity || entity instanceof WightEntity ||
                      entity instanceof ShadowSpiderEntity));
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.IRON_GOLEM_STEP;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<CitadelGuardEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.citadel_guard.hurt", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.citadel_guard.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.citadel_guard.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.citadel_guard.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}