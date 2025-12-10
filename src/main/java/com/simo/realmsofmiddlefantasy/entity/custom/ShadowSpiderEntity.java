package com.simo.realmsofmiddlefantasy.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Araignée d'Ombre - Variante améliorée de l'araignée vanilla.
 * Poison plus long, comportement d'embuscade.
 */
public class ShadowSpiderEntity extends Spider implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ShadowSpiderEntity(EntityType<? extends Spider> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 22.0D) // Plus tanky que spider vanilla
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE, 3.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.5F));
        this.goalSelector.addGoal(4, new Spider.SpiderAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new Spider.SpiderTargetGoal<>(this, Player.class));
    }

    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                int poisonDuration = 0;
                
                // Poison plus long selon la difficulté
                switch (this.level().getDifficulty()) {
                    case EASY -> poisonDuration = 7;
                    case NORMAL -> poisonDuration = 15;
                    case HARD -> poisonDuration = 20;
                }
                
                if (poisonDuration > 0) {
                    living.addEffect(new MobEffectInstance(
                        MobEffects.POISON, 
                        poisonDuration * 20, 
                        1 // Poison II
                    ), this);
                }
                
                // Chance de ralentir (effet toile)
                if (this.getRandom().nextFloat() < 0.4F) {
                    living.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, 
                        60, 
                        0
                    ), this);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        
        // Descente depuis le plafond (embuscade)
        if (!this.level().isClientSide && this.getTarget() != null) {
            BlockPos above = this.blockPosition().above(3);
            if (this.level().getBlockState(above).isSolidRender(this.level(), above)) {
                // L'araignée est sous un plafond solide
                if (this.getRandom().nextFloat() < 0.02F) { // 2% de chance par tick
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.3D, 0));
                }
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<ShadowSpiderEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("attack_bite", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            if (this.onClimbable()) {
                event.getController().setAnimation(
                    RawAnimation.begin().then("climb_wall", Animation.LoopType.LOOP)
                );
            } else {
                event.getController().setAnimation(
                    RawAnimation.begin().then("walk_crawl", Animation.LoopType.LOOP)
                );
            }
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_lurk", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}