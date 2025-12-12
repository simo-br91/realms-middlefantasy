package com.simo.realmsofmiddlefantasy.entity.goblin;

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
 * Goblin des montagnes - Petit, rapide, attaque en essaim.
 * Goblins are cowardly and will flee from stronger enemies when not attacking.
 */
public class GoblinEntity extends Monster implements GeoEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GoblinEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)     // Low health for a goblin
                .add(Attributes.MOVEMENT_SPEED, 0.35D)  // High speed for agility
                .add(Attributes.ATTACK_DAMAGE, 2.5D)    // Low damage, but fast
                .add(Attributes.FOLLOW_RANGE, 20.0D);   // Moderate follow range
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));  // Goblins can float in water
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));  // Leap attack
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));  // Fast melee attack
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));  // Random movement
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random look around

        // Flee from stronger enemies (cowardly nature)
        this.goalSelector.addGoal(10, new FleeGoal(this, 15.0F, 1.2D, 2.5D));  // Flee goal

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Aggro logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));  // Attack players
    }

    /**
     * Goblin will flee when facing a stronger target.
     * The Goblin tries to run away if it's not currently attacking.
     */
    public static class FleeGoal extends Goal {
        private final Monster mob;
        private final double fleeDistance;
        private final double fleeSpeed;
        private final double fleeSpeedWhenRunning;

        public FleeGoal(Monster mob, float fleeDistance, double fleeSpeed, double fleeSpeedWhenRunning) {
            this.mob = mob;
            this.fleeDistance = fleeDistance;
            this.fleeSpeed = fleeSpeed;
            this.fleeSpeedWhenRunning = fleeSpeedWhenRunning;
        }

        @Override
        public boolean canUse() {
            return mob.getTarget() != null && mob.distanceTo(mob.getTarget()) < fleeDistance && !mob.isAggressive();
        }

        @Override
        public void start() {
            mob.getNavigation().moveTo(mob.getTarget().getX(), mob.getTarget().getY(), mob.getTarget().getZ(), fleeSpeedWhenRunning);
        }

        @Override
        public void stop() {
            mob.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (mob.getTarget() != null && mob.distanceTo(mob.getTarget()) < fleeDistance) {
                mob.getNavigation().moveTo(mob.getTarget().getX(), mob.getTarget().getY(), mob.getTarget().getZ(), fleeSpeed);
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<GoblinEntity> event) {
        // Death animation
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        // When swinging, the goblin performs a stabbing motion
        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("stab", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        // Movement animation (twitchy and fast)
        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("run_frantic", Animation.LoopType.LOOP)
            );
        } else {
            // Idle behavior with a twitchy animation
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_twitch", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;  // Return the cache for animations
    }
}
