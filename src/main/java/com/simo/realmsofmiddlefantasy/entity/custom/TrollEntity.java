package com.simo.realmsofmiddlefantasy.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

/**
 * Troll - Mini-boss local des cavernes et collines.
 * Attaques : Slam, charge, lancer de rocher.
 */
public class TrollEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int slamCooldown = 0;
    private int rockThrowCooldown = 0;
    private boolean isVariantCave;

    public TrollEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 20;
        this.isVariantCave = level.getRandom().nextBoolean();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.22D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 4.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.9D, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        
        if (slamCooldown > 0) slamCooldown--;
        if (rockThrowCooldown > 0) rockThrowCooldown--;
        
        // Lancer de rocher à distance
        if (!this.level().isClientSide && this.getTarget() != null && rockThrowCooldown == 0) {
            double distance = this.distanceToSqr(this.getTarget());
            if (distance > 64.0D && distance < 400.0D) { // Entre 8 et 20 blocs
                throwRock();
                rockThrowCooldown = 140; // 7 secondes
            }
        }
    }

    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        boolean flag = super.doHurtTarget(target);
        
        if (flag && target instanceof LivingEntity living) {
            if (slamCooldown == 0 && this.getRandom().nextFloat() < 0.3F) {
                performSlam(living);
                slamCooldown = 100;
            }
        }
        
        return flag;
    }

    private void performSlam(LivingEntity target) {
        double knockbackStrength = 2.5D;
        double xRatio = target.getX() - this.getX();
        double zRatio = target.getZ() - this.getZ();
        target.knockback(knockbackStrength, xRatio, zRatio);
        
        this.playSound(SoundEvents.GENERIC_EXPLODE, 1.5F, 0.8F);
        
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                this.getX(), this.getY(), this.getZ(),
                10, 1.0D, 0.5D, 1.0D, 0.0D);
            
            AABB area = this.getBoundingBox().inflate(4.0D);
            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(
                LivingEntity.class, area,
                entity -> entity != this && entity instanceof Player
            );
            
            for (LivingEntity entity : nearbyEntities) {
                entity.hurt(this.damageSources().mobAttack(this), 5.0F);
                double dx = entity.getX() - this.getX();
                double dz = entity.getZ() - this.getZ();
                entity.knockback(1.5D, dx, dz);
            }
        }
    }

    private void throwRock() {
        if (this.getTarget() == null) return;
        
        Vec3 targetPos = this.getTarget().position();
        Vec3 throwPos = this.position().add(0, 1.8D, 0);
        
        // Créer un projectile (on utilise un snowball comme placeholder)
        net.minecraft.world.entity.projectile.Snowball rock = 
            new net.minecraft.world.entity.projectile.Snowball(this.level(), this);
        
        double dx = targetPos.x - throwPos.x;
        double dy = targetPos.y + this.getTarget().getEyeHeight() - throwPos.y;
        double dz = targetPos.z - throwPos.z;
        double distance = Math.sqrt(dx * dx + dz * dz);
        
        rock.shoot(dx, dy + distance * 0.1D, dz, 1.2F, 8.0F);
        rock.setPos(throwPos);
        
        this.level().addFreshEntity(rock);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.5F, 0.5F);
    }

    @Override
    public float getScale() {
        return isVariantCave ? 1.4F : 1.3F; // Cave trolls légèrement plus grands
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.RAVAGER_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<TrollEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death_fall", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("slam_ground", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("walk_heavy", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_breathing", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}