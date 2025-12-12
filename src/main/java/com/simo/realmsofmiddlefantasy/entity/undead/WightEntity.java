package com.simo.realmsofmiddlefantasy.entity.undead;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
 * Wight des Galgals - Spectral entity from the tombs.
 * Slows down players, drains health, and causes vision to distort.
 */
public class WightEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public WightEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 15; // A boss-like entity but less than trolls
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)  // Slow but menacing
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.7D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        
        // Spectral effect: Soul particles around the Wight
        if (this.level().isClientSide && this.getRandom().nextFloat() < 0.3F) {
            this.level().addParticle(
                ParticleTypes.SOUL,
                this.getRandomX(0.5D),
                this.getRandomY(),
                this.getRandomZ(0.5D),
                0.0D, 0.05D, 0.0D
            );
        }
        
        // Drain aura: applies to nearby players
        if (!this.level().isClientSide && this.isAlive() && this.tickCount % 40 == 0) {
            applyDrainAura();
        }
    }

    private void applyDrainAura() {
        double radius = 6.0D;
        for (Player player : this.level().getEntitiesOfClass(
            Player.class,
            this.getBoundingBox().inflate(radius)
        )) {
            // Drain effect on players
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
            
            // Nausea effect (shaking vision)
            if (this.getRandom().nextFloat() < 0.3F) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
            }
        }
    }

    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        boolean flag = super.doHurtTarget(target);
        
        if (flag && target instanceof LivingEntity living) {
            // Health drain: Wight heals by attacking
            this.heal(2.0F);
            
            // Apply spectral effects on the target
            living.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 1));
            
            // Soul drain particles around the target
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                    ParticleTypes.SOUL,
                    living.getX(), living.getY() + 1.0D, living.getZ(),
                    10, 0.3D, 0.5D, 0.3D, 0.05D
                );
            }
        }
        
        return flag;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // Resistance to physical damage, weakness to magic-based damage
        if (source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE) || 
            source.is(net.minecraft.world.damagesource.DamageTypes.ON_FIRE) ||
            source.is(net.minecraft.tags.DamageTypeTags.IS_EXPLOSION)) {
            amount *= 1.5F; // Vulnerable to fire and explosions
        } else if (!source.is(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR)) {
            amount *= 0.7F; // Resistant to physical damage
        }
        
        return super.hurt(source, amount);
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<WightEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death_dissipate", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("attack_drain", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("float_glide", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_hover", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
