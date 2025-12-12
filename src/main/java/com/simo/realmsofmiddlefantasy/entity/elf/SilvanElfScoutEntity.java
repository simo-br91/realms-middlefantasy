package com.simo.realmsofmiddlefantasy.entity.elf;

import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;

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
 * Silvan Elf Scout - Woodland elf ranger with superior tracking abilities.
 * Fast, stealthy, and deadly with a bow.
 */
public class SilvanElfScoutEntity extends Monster implements GeoEntity, RangedAttackMob {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SilvanElfScoutEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)     // Low health for a scout (glass cannon)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)  // Fast movement, reflecting agility
                .add(Attributes.ATTACK_DAMAGE, 4.0D)    // Moderate attack damage, but with high accuracy
                .add(Attributes.FOLLOW_RANGE, 32.0D)    // High follow range for scouting purposes
                .add(Attributes.ARMOR, 4.0D);           // Light armor for agility
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));  // Can float in water
        this.goalSelector.addGoal(1, new RangedBowAttackGoal<>(this, 1.0D, 20, 28.0F));  // Ranged attack goal
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Monster.class, 10.0F, 1.2D, 1.4D));  // Avoidance behavior
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 0.8D));  // Random wandering
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 10.0F));  // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random looking behavior

        // Targeting goals - targets orcs and other enemies of nature
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Hurt by target (aggro logic)
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false, 
            entity -> entity instanceof OrcEntity || entity instanceof OrcScoutEntity || 
                      entity instanceof OrcWarriorEntity || entity instanceof UrukHaiEntity ||
                      entity instanceof GoblinEntity || entity instanceof WargEntity));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        ItemStack bow = new ItemStack(Items.BOW);
        AbstractArrow arrow = ProjectileUtil.getMobArrow(this, bow, velocity);
        
        // Silvan elves have superior accuracy and range
        double d0 = target.getX() - this.getX();
        double d1 = target.getY(0.3333333333333333D) - arrow.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        
        arrow.shoot(d0, d1 + d3 * 0.15D, d2, 1.7F, (float)(12 - this.level().getDifficulty().getId() * 4));
        
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(arrow);
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<SilvanElfScoutEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.silvan_elf_scout.hurt", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.isAggressive()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.silvan_elf_scout.attack", Animation.LoopType.HOLD_ON_LAST_FRAME)
            );
        } else if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.silvan_elf_scout.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.silvan_elf_scout.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
