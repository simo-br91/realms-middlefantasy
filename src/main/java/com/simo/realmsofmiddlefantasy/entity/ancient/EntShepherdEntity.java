package com.simo.realmsofmiddlefantasy.entity.ancient;

import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.*;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
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
 * Ent Shepherd - Guardian of Fangorn.
 * Massive, slow-moving, extremely resilient.
 * Neutral unless provoked or facing enemies of nature (Orcs, Goblins).
 */
public class EntShepherdEntity extends Monster implements GeoEntity {

    // Constants for fire damage and physical resistance multipliers
    private static final float FIRE_DAMAGE_MULTIPLIER = 2.0F;
    private static final float PHYSICAL_RESISTANCE_MULTIPLIER = 0.6F;
    private static final float STEP_SOUND_VOLUME = 0.25F;

    // Animation cache for GeoLib
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public EntShepherdEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 25; // Reward XP when the Ent Shepherd is defeated
    }

    // Defining the attributes for the Ent Shepherd entity, such as health, speed, armor
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)  // High health to reflect resilience
                .add(Attributes.MOVEMENT_SPEED, 0.18D)  // Slow-moving, fitting for Ents
                .add(Attributes.ATTACK_DAMAGE, 15.0D)  // Strong melee damage for a giant tree protector
                .add(Attributes.FOLLOW_RANGE, 32.0D)  // Long follow range due to its guarding nature
                .add(Attributes.ARMOR, 15.0D)  // High armor to reflect the tough bark of the Ents
                .add(Attributes.ARMOR_TOUGHNESS, 8.0D)  // Toughness due to their thick wood
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);  // Unyielding against knockback
    }

    // AI goals: Define behavior like attacking, wandering, and looking at players
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));  // Allows the Ent Shepherd to float
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.8D, true));  // Melee attack goal
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5D));  // Avoids water, fitting for forest dwellers
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 16.0F));  // Looks at players within a range
        goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Looks around randomly

        targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Targets those who hurt it
        targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false, EntShepherdEntity::isEnemyOfNature)
        );
    }

    // Helper method to determine if the entity is an enemy of nature
    private static boolean isEnemyOfNature(net.minecraft.world.entity.LivingEntity entity) {
        return entity instanceof OrcEntity || entity instanceof OrcScoutEntity ||
                entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                entity instanceof UrukHaiEntity || entity instanceof GoblinEntity;
    }

    // Damage handling: Increased damage from fire and resistance to physical damage
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE)
                || source.is(net.minecraft.world.damagesource.DamageTypes.ON_FIRE)) {
            amount *= FIRE_DAMAGE_MULTIPLIER;  // Ents are vulnerable to fire
        }

        if (!source.is(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR)
                && !source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE)) {
            amount *= PHYSICAL_RESISTANCE_MULTIPLIER;  // Ents are resistant to physical damage
        }

        return super.hurt(source, amount);
    }

    // Custom sounds for the Ent Shepherd
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WARDEN_AMBIENT;  // Sound of a deep, ancient presence
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOOD_BREAK;  // Wood cracking when hurt
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;  // Death sound similar to a giant falling tree
    }

    // Custom step sound, matching the heavy footsteps of an Ent
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.WARDEN_STEP, STEP_SOUND_VOLUME, 1.0F);
    }

    // Custom scaling for the Ent Shepherd to reflect its massive size
    @Override
    public float getScale() {
        return 2.5F;  // Adjust the scale to reflect the towering stature of an Ent
    }

    // Animation control for different states (idle, walking, attacking)
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    // Animation predicates based on the entity's state (moving, attacking, etc.)
    private PlayState animationPredicate(AnimationState<EntShepherdEntity> state) {
        if (isDeadOrDying()) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("animation.ent_shepherd.idle", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        if (swinging) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("animation.ent_shepherd.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        state.getController().setAnimation(
                RawAnimation.begin().then(
                        state.isMoving()
                                ? "animation.ent_shepherd.walk"
                                : "animation.ent_shepherd.idle",
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
