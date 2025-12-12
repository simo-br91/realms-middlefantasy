package com.simo.realmsofmiddlefantasy.entity.giant;

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
 * Cave Troll - Variante des cavernes, très tanky et puissant.
 * Se trouve principalement dans les grottes et cavernes profondes.
 * Plus lent mais beaucoup plus résistant que le Hill Troll.
 */
public class CaveTrollEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int slamCooldown = 0;
    private int rockThrowCooldown = 0;

    public CaveTrollEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 22; // Plus d'XP que Hill Troll
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 70.0D)    // Très tanky
                .add(Attributes.MOVEMENT_SPEED, 0.20D) // Plus lent
                .add(Attributes.ATTACK_DAMAGE, 12.0D)  // Beaucoup de dégâts
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.ARMOR, 10.0D)          // Beaucoup d'armure
                .add(Attributes.ARMOR_TOUGHNESS, 5.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.9D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.85D, true));  // Melee combat behavior
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.55D));  // Random movement
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));  // Target players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random look around

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Aggro logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));  // Target players
    }

    @Override
    public void tick() {
        super.tick();
        
        if (slamCooldown > 0) slamCooldown--;
        if (rockThrowCooldown > 0) rockThrowCooldown--;
        
        // Lancer de rocher (moins fréquent)
        if (!this.level().isClientSide && this.getTarget() != null && rockThrowCooldown == 0) {
            double distance = this.distanceToSqr(this.getTarget());
            if (distance > 64.0D && distance < 400.0D) {
                throwRock();
                rockThrowCooldown = 160;  // Plus long cooldown
            }
        }
    }

    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        boolean flag = super.doHurtTarget(target);
        
        if (flag && target instanceof LivingEntity living) {
            if (slamCooldown == 0 && this.getRandom().nextFloat() < 0.35F) {
                performSlam(living);  // Perform slam attack
                slamCooldown = 120;  // Cooldown for slam
            }
        }
        
        return flag;
    }

    private void performSlam(LivingEntity target) {
        double knockbackStrength = 3.0D;  // Stronger knockback
        double xRatio = target.getX() - this.getX();
        double zRatio = target.getZ() - this.getZ();
        target.knockback(knockbackStrength, xRatio, zRatio);
        
        this.playSound(SoundEvents.GENERIC_EXPLODE, 2.0F, 0.7F);
        
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                this.getX(), this.getY(), this.getZ(),
                15, 1.2D, 0.6D, 1.2D, 0.0D);
            
            AABB area = this.getBoundingBox().inflate(5.0D);  // Larger area for slam effect
            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(
                LivingEntity.class, area,
                entity -> entity != this && entity instanceof Player
            );
            
            for (LivingEntity entity : nearbyEntities) {
                entity.hurt(this.damageSources().mobAttack(this), 6.0F);  // Higher damage
                double dx = entity.getX() - this.getX();
                double dz = entity.getZ() - this.getZ();
                entity.knockback(2.0D, dx, dz);  // Knockback effect
            }
        }
    }

    private void throwRock() {
        if (this.getTarget() == null) return;
        
        Vec3 targetPos = this.getTarget().position();
        Vec3 throwPos = this.position().add(0, 2.0D, 0);
        
        net.minecraft.world.entity.projectile.Snowball rock = 
            new net.minecraft.world.entity.projectile.Snowball(this.level(), this);
        
        double dx = targetPos.x - throwPos.x;
        double dy = targetPos.y + this.getTarget().getEyeHeight() - throwPos.y;
        double dz = targetPos.z - throwPos.z;
        double distance = Math.sqrt(dx * dx + dz * dz);
        
        rock.shoot(dx, dy + distance * 0.12D, dz, 1.4F, 6.0F);  // Adjusted rock trajectory and speed
        rock.setPos(throwPos);
        
        this.level().addFreshEntity(rock);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 2.0F, 0.4F);
    }

    @Override
    public float getScale() {
        return 1.45F;  // Larger than Hill Troll
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.RAVAGER_AMBIENT;  // Deep, menacing sound
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.RAVAGER_HURT;  // Aggressive hurt sound
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;  // Death sound with a deep roar
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<CaveTrollEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.cave_troll.death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.cave_troll.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.cave_troll.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.cave_troll.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
