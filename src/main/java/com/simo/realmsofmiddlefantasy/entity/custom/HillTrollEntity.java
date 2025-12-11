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
 * Hill Troll - Variante plus mobile que le Cave Troll.
 * Se trouve dans les collines et plaines montagneuses.
 * Légèrement moins tanky mais plus rapide.
 */
public class HillTrollEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int slamCooldown = 0;
    private int rockThrowCooldown = 0;

    public HillTrollEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 18; // Légèrement moins que Cave Troll
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 55.0D)    // Un peu moins tanky
                .add(Attributes.MOVEMENT_SPEED, 0.25D) // Plus rapide
                .add(Attributes.ATTACK_DAMAGE, 9.0D)   // Légèrement moins de dégâts
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.ARMOR, 6.0D)           // Moins d'armure
                .add(Attributes.ARMOR_TOUGHNESS, 3.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.7D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.7D));
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
            if (distance > 64.0D && distance < 400.0D) {
                throwRock();
                rockThrowCooldown = 120; // Plus court que Cave Troll
            }
        }
    }

    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        boolean flag = super.doHurtTarget(target);
        
        if (flag && target instanceof LivingEntity living) {
            if (slamCooldown == 0 && this.getRandom().nextFloat() < 0.25F) {
                performSlam(living);
                slamCooldown = 80; // Plus court que Cave Troll
            }
        }
        
        return flag;
    }

    private void performSlam(LivingEntity target) {
        double knockbackStrength = 2.0D;
        double xRatio = target.getX() - this.getX();
        double zRatio = target.getZ() - this.getZ();
        target.knockback(knockbackStrength, xRatio, zRatio);
        
        this.playSound(SoundEvents.GENERIC_EXPLODE, 1.3F, 0.9F);
        
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                this.getX(), this.getY(), this.getZ(),
                8, 0.8D, 0.4D, 0.8D, 0.0D);
            
            AABB area = this.getBoundingBox().inflate(3.5D);
            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(
                LivingEntity.class, area,
                entity -> entity != this && entity instanceof Player
            );
            
            for (LivingEntity entity : nearbyEntities) {
                entity.hurt(this.damageSources().mobAttack(this), 4.0F);
                double dx = entity.getX() - this.getX();
                double dz = entity.getZ() - this.getZ();
                entity.knockback(1.2D, dx, dz);
            }
        }
    }

    private void throwRock() {
        if (this.getTarget() == null) return;
        
        Vec3 targetPos = this.getTarget().position();
        Vec3 throwPos = this.position().add(0, 1.8D, 0);
        
        net.minecraft.world.entity.projectile.Snowball rock = 
            new net.minecraft.world.entity.projectile.Snowball(this.level(), this);
        
        double dx = targetPos.x - throwPos.x;
        double dy = targetPos.y + this.getTarget().getEyeHeight() - throwPos.y;
        double dz = targetPos.z - throwPos.z;
        double distance = Math.sqrt(dx * dx + dz * dz);
        
        rock.shoot(dx, dy + distance * 0.1D, dz, 1.3F, 7.0F);
        rock.setPos(throwPos);
        
        this.level().addFreshEntity(rock);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.5F, 0.6F);
    }

    @Override
    public float getScale() {
        return 1.25F; // Légèrement plus petit que Cave Troll
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

    private PlayState predicate(AnimationState<HillTrollEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death_fall", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}