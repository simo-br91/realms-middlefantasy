package com.simo.realmsofmiddlefantasy.entity.orc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Orc Scout - Light and fast skirmisher with a bow.
 * Harasses targets at range, avoids direct fights, and retreats when threatened.
 * Often used to spot prey and soften enemies before the main force arrives.
 */
public class OrcScoutEntity extends Monster implements GeoEntity, RangedAttackMob {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int fleeCooldown = 0;

    public OrcScoutEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 18.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ARMOR, 2.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));  // Orc Scout can float
        this.goalSelector.addGoal(1, new RangedBowAttackGoal<>(this, 1.0D, 20, 25.0F));  // Ranged attack
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));  // Restrict sun during day
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.2D));  // Flee from sunlight
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.2D, 1.4D));  // Avoid players
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));  // Random movement
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random look around

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Hurt by target logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));  // Attack players
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        ItemStack bow = new ItemStack(Items.BOW);
        AbstractArrow arrow = ProjectileUtil.getMobArrow(this, bow, velocity);
        
        double d0 = target.getX() - this.getX();
        double d1 = target.getY(0.3333333333333333D) - arrow.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        
        arrow.shoot(d0, d1 + d3 * 0.2D, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        
        this.playSound(net.minecraft.sounds.SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(arrow);
    }

    // ===== GECKOLIB ANIMATIONS =====
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<OrcScoutEntity> event) {
        // Death animation
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        // Attack animation
        if (this.isAggressive()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("bow_draw", Animation.LoopType.LOOP)
            );
        } else if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("run_fast", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_alert", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void tick() {
        super.tick();

        // Fleeing behavior - when health is low, try to run away
        if (this.getHealth() < this.getMaxHealth() * 0.3 && fleeCooldown == 0) {
            flee();
            fleeCooldown = 100;  // Cooldown to prevent constant fleeing
        }

        if (fleeCooldown > 0) fleeCooldown--;
    }

    private void flee() {
        // Implement fleeing logic by moving in the opposite direction of the target
        if (this.getTarget() != null) {
            double dx = this.getX() - this.getTarget().getX();
            double dz = this.getZ() - this.getTarget().getZ();
            this.setDeltaMovement(dx * 0.5D, 0, dz * 0.5D);  // Run away in the opposite direction
            this.playSound(net.minecraft.sounds.SoundEvents.GENERIC_SWIM, 1.0F, 1.0F);
        }
    }
}
