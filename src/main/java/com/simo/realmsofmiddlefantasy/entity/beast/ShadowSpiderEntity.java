package com.simo.realmsofmiddlefantasy.entity.beast;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
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

import com.simo.realmsofmiddlefantasy.entity.orc.OrcCaptainEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;

/**
 * Shadow Spider - Deadlier variant of the vanilla spider.
 * Longer poison, ambush behavior, and slowing bites.
 */
public class ShadowSpiderEntity extends Spider implements GeoEntity {

    // Constants for ambush, drop, and slow chances
    private static final float AMBUSH_DROP_CHANCE = 0.02F;  // Chance for ambush drop from above
    private static final float DROP_FORCE = -0.3F;  // Drop force for ambush
    private static final float SLOW_CHANCE = 0.4F;  // Chance to slow target

    // Animation cache for GeckoLib
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public ShadowSpiderEntity(EntityType<? extends Spider> type, Level level) {
        super(type, level);
    }

    // Defining the attributes for the Shadow Spider entity, such as health, speed, and attack damage
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 22.0D)  // Moderate health to make it a dangerous but not invincible foe
                .add(Attributes.MOVEMENT_SPEED, 0.32D)  // Agile, fast-moving spider
                .add(Attributes.ATTACK_DAMAGE, 3.5D);  // Moderate attack damage, with added poison effects
    }

    // AI goals: Define behavior like attacking, ambushing, and looking at players
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new FloatGoal(this));  // Allows spider to float (no swimming)
        goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.5F));  // Jump towards the target
        goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));  // Basic melee attack
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));  // Avoid water, remain stealthy
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at the player within a short range
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));  // Randomly looks around for more prey

        targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Targets those who hurt it

        // Attack players (intruders) or enemies of nature
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

        // Attack enemies of nature (orcs, goblins, etc.)
        targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false, ShadowSpiderEntity::isEnemyOfNature)
        );
    }

    // Helper method to determine if the entity is an enemy of nature
    private static boolean isEnemyOfNature(LivingEntity entity) {
        return entity instanceof OrcEntity || entity instanceof OrcScoutEntity ||
                entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                entity instanceof UrukHaiEntity || entity instanceof GoblinEntity;
    }

    // Combat Effects: Apply poison and slow effects on target after bite
    @Override
    public boolean doHurtTarget(Entity target) {
        if (!super.doHurtTarget(target)) {
            return false;
        }

        if (target instanceof LivingEntity living) {
            // Poison effect with different duration based on difficulty
            int poisonSeconds = switch (level().getDifficulty()) {
                case EASY -> 7;
                case NORMAL -> 15;
                case HARD -> 20;
                default -> 0;
            };

            if (poisonSeconds > 0) {
                living.addEffect(new MobEffectInstance(MobEffects.POISON, poisonSeconds * 20, 1), this);
            }

            // Chance to slow target with movement slowdown
            if (random.nextFloat() < SLOW_CHANCE) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0), this);
            }
        }

        return true;
    }

    // Ambush Logic: Spider drops from the ceiling for an ambush
    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide || getTarget() == null) {
            return;
        }

        BlockPos ceiling = blockPosition().above(3);

        if (level().getBlockState(ceiling).isSolidRender(level(), ceiling)) {
            if (random.nextFloat() < AMBUSH_DROP_CHANCE) {
                setDeltaMovement(getDeltaMovement().add(0, DROP_FORCE, 0));  // Drop from the ceiling
            }
        }
    }

    // Animations: Define idle, attack, and movement animations for the spider
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private PlayState animationPredicate(AnimationState<ShadowSpiderEntity> state) {
        if (isDeadOrDying()) {
            state.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (swinging) {
            state.getController().setAnimation(RawAnimation.begin().then("attack_bite", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().then(onClimbable() ? "climb_wall" : "walk_crawl", Animation.LoopType.LOOP));
        } else {
            state.getController().setAnimation(RawAnimation.begin().then("idle_lurk", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    // Return the animation cache instance
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }
}
