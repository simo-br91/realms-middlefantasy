package com.simo.realmsofmiddlefantasy.entity.custom;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Ent Shepherd - Ancient tree guardian of Fangorn Forest.
 * Massive, slow-moving protector of the woodland realms.
 * Extremely powerful with high health and devastating attacks.
 */
public class EntShepherdEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public EntShepherdEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 25; // Boss-tier XP reward
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)      // Extremely tanky
                .add(Attributes.MOVEMENT_SPEED, 0.18D)   // Very slow but unstoppable
                .add(Attributes.ATTACK_DAMAGE, 15.0D)    // Devastating attacks
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ARMOR, 15.0D)            // Massive armor
                .add(Attributes.ARMOR_TOUGHNESS, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D); // Immune to knockback
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.8D, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        // Ents are peaceful unless provoked, but will attack orcs and enemies of nature
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false,
            entity -> entity instanceof OrcEntity || entity instanceof OrcScoutEntity || 
                      entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                      entity instanceof UrukHaiEntity || entity instanceof GoblinEntity));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // Ents are vulnerable to fire
        if (source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE) || 
            source.is(net.minecraft.world.damagesource.DamageTypes.ON_FIRE)) {
            amount *= 2.0F; // Double damage from fire
        }
        
        // Resistant to physical damage
        if (!source.is(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR) && 
            !source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE)) {
            amount *= 0.6F;
        }
        
        return super.hurt(source, amount);
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.WARDEN_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOOD_BREAK;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;
    }

   @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.WARDEN_STEP, 0.25F, 1.0F);
    }

    @Override
    public float getScale() {
        return 2.5F; // Ents are massive
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<EntShepherdEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.ent_shepherd.idle", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.ent_shepherd.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.ent_shepherd.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.ent_shepherd.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}