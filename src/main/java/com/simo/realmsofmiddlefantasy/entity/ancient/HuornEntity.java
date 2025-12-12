package com.simo.realmsofmiddlefantasy.entity.ancient;

import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.*;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.block.state.BlockState;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Huorn - Savage animated tree creature.
 * More aggressive and less intelligent than Ents.
 * Defends the forest and attacks intruders.
 */
public class HuornEntity extends Monster implements GeoEntity {

    // Constants for fire damage and physical resistance multipliers
    private static final float FIRE_DAMAGE_MULTIPLIER = 2.0F;
    private static final float PHYSICAL_RESISTANCE_MULTIPLIER = 0.7F;
    private static final float STEP_SOUND_VOLUME = 0.15F;

    // Animation cache for GeoLib
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public HuornEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 15; // Reward XP when the Huorn is defeated
    }

    // Defining the attributes for the Huorn entity, such as health, speed, armor
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)  // Moderate health for a savage guardian
                .add(Attributes.MOVEMENT_SPEED, 0.15D)  // Slow-moving, fitting for a tree-like creature
                .add(Attributes.ATTACK_DAMAGE, 10.0D)  // Moderate attack damage, enough to deter intruders
                .add(Attributes.FOLLOW_RANGE, 24.0D)  // Shorter follow range compared to larger guardians
                .add(Attributes.ARMOR, 8.0D)  // Moderate armor to reflect its wooden exterior
                .add(Attributes.ARMOR_TOUGHNESS, 4.0D)  // Toughness due to its strong bark
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);  // Some knockback resistance for a sturdy creature
    }

    // AI goals: Define behavior like attacking, wandering, and looking at players
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));  // Allows the Huorn to float
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.7D, true));  // Melee attack goal for close combat
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.4D));  // Avoids water, as it thrives in forests
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));  // Looks at players within a short range
        goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random looking behavior

        // Aggressive forest guardian, targets those who harm nature
        targetSelector.addGoal(1, new HurtByTargetGoal(this));

        // Attacks players (intruders) who get too close
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

        // Also attacks enemies of nature (orcs, goblins, etc.)
        targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false, HuornEntity::isEnemyOfNature)
        );
    }

    // Helper method to determine if the entity is an enemy of nature
    private static boolean isEnemyOfNature(LivingEntity entity) {
        return entity instanceof OrcEntity || entity instanceof OrcScoutEntity ||
                entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                entity instanceof UrukHaiEntity || entity instanceof GoblinEntity;
    }

    // Damage handling: Increased damage from fire and resistance to physical damage
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE)
                || source.is(net.minecraft.world.damagesource.DamageTypes.ON_FIRE)) {
            amount *= FIRE_DAMAGE_MULTIPLIER;  // Huorns are vulnerable to fire, like trees
        }

        if (!source.is(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR)
                && !source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE)) {
            amount *= PHYSICAL_RESISTANCE_MULTIPLIER;  // Huorns are resistant to physical attacks due to their bark
        }

        return super.hurt(source, amount);
    }

    // Custom sounds for the Huorn
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WARDEN_AMBIENT;  // Deep, ominous forest sounds
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOOD_BREAK;  // Sound of cracking wood when hurt
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;  // A crashing tree sound when the Huorn dies
    }

    // Custom step sound, like heavy wood creaking when the Huorn walks
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.WOOD_STEP, STEP_SOUND_VOLUME, 1.0F);
    }

    // Custom scaling for the Huorn to reflect its towering size
    @Override
    public float getScale() {
        return 1.8F;  // Slightly larger than average mobs, but still not as huge as Ents
    }

    // Animation control for different states (idle, walking, attacking)
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    // Animation predicates based on the entity's state (moving, attacking, etc.)
    private PlayState animationPredicate(AnimationState<HuornEntity> state) {
        if (isDeadOrDying()) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("animation.huorn.idle", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        if (swinging) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("animation.huorn.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        state.getController().setAnimation(
                RawAnimation.begin().then(
                        state.isMoving()
                                ? "animation.huorn.walk"
                                : "animation.huorn.idle",
                        Animation.LoopType.LOOP
                )
        );

        return PlayState.CONTINUE;
    }

    // Returns the instance cache for animations
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }
}
