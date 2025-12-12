package com.simo.realmsofmiddlefantasy.entity.beast;

import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.*;
import com.simo.realmsofmiddlefantasy.entity.undead.WightEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
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
 * Great Eagle - Noble flying creature allied with the Free Peoples.
 * Fast, mobile, attacks enemies of nature and darkness.
 */
public class GreatEagleEntity extends Monster implements GeoEntity {

    // Constants for flying mechanics and scaling
    private static final float AIR_BRAKE_FACTOR_Y = 0.6F;  // Slows falling while airborne
    private static final float SCALE = 2.2F;  // Size of the Great Eagle

    // Animation cache for GeoLib
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public GreatEagleEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);  // Flying control for the eagle
        this.xpReward = 20;
    }

    // Defining attributes such as health, movement, and attack power for the Great Eagle
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)  // Moderate health to reflect an agile predator
                .add(Attributes.MOVEMENT_SPEED, 0.30D)  // Agile movement on the ground
                .add(Attributes.FLYING_SPEED, 0.8D)  // Fast in the air
                .add(Attributes.ATTACK_DAMAGE, 8.0D)  // Moderate damage for a powerful bird of prey
                .add(Attributes.FOLLOW_RANGE, 48.0D)  // Large follow range due to aerial ability
                .add(Attributes.ARMOR, 4.0D)  // Light armor, as eagles are swift and agile, not heavily armored
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D);  // Some resistance to knockback due to its aerial nature
    }

    // Navigation: Custom flying path navigation for the Great Eagle
    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        nav.setCanPassDoors(true);
        return nav;
    }

    // AI goals: Define behavior like attacking, flying, and avoiding water
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));  // Allows the eagle to fly
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));  // Melee attack for close combat
        goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0D));  // Avoids water, stays in the air
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 16.0F));  // Looks at players
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));  // Random look-around behavior

        targetSelector.addGoal(1, new HurtByTargetGoal(this));

        // Attacks evil creatures (orcs, goblins, wargs, spiders, wights...)
        targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false, GreatEagleEntity::isEvilTarget)
        );
    }

    // Helper method to determine if the entity is an enemy of nature
    private static boolean isEvilTarget(LivingEntity entity) {
        return entity instanceof OrcEntity
                || entity instanceof OrcScoutEntity
                || entity instanceof OrcWarriorEntity
                || entity instanceof OrcCaptainEntity
                || entity instanceof UrukHaiEntity
                || entity instanceof GoblinEntity
                || entity instanceof WargEntity
                || entity instanceof ShadowSpiderEntity
                || entity instanceof WightEntity;
    }

    // Prevent fall damage for the eagle (as it flies)
    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;  // No fall damage for the eagle
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        // No-op (no fall damage)
    }

    // Check if the eagle is actually flying
    public boolean isActuallyFlying() {
        return !onGround() && getDeltaMovement().y > -0.05D;
    }

    // Tick method: Reduce falling speed while airborne for a gliding effect
    @Override
    public void tick() {
        super.tick();

        if (!onGround() && getDeltaMovement().y < 0.0D) {
            setDeltaMovement(getDeltaMovement().multiply(1.0D, AIR_BRAKE_FACTOR_Y, 1.0D));
        }
    }

    // Sound events: Custom sounds for the eagle
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;  // A placeholder, should be an eagle screech
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PARROT_HURT;  // Replace with an eagle's pained screech
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PARROT_DEATH;  // Replace with an eagle's death screech or fall sound
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        // Eagles don't make step sounds while flying
    }

    // Set the eagle's scale to reflect its majestic size
    @Override
    public float getScale() {
        return SCALE;  // Eagle size should be larger than regular creatures
    }

    // Register animations: flying, idle, attack, etc.
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    // Animation predicate: Change animations based on the eagle's state (flying, attacking, etc.)
    private PlayState animationPredicate(AnimationState<GreatEagleEntity> state) {

        if (isDeadOrDying()) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("animation.great_eagle.idle_perch", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        if (swinging) {
            state.getController().setAnimation(
                    RawAnimation.begin().then("animation.great_eagle.air_attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        state.getController().setAnimation(
                RawAnimation.begin().then(
                        isActuallyFlying() ? "animation.great_eagle.fly_cycle" : "animation.great_eagle.idle_perch",
                        Animation.LoopType.LOOP
                )
        );

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }
}
