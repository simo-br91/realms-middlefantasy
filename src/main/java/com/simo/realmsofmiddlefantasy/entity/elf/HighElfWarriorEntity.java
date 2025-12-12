package com.simo.realmsofmiddlefantasy.entity.elf;

import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;

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

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * High Elf Warrior - Elite elven sentinel with heavy armor.
 * Strong in melee combat, defends elven ruins and sanctuaries.
 */
public class HighElfWarriorEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public HighElfWarriorEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)      // Health for a durable fighter
                .add(Attributes.MOVEMENT_SPEED, 0.28D)   // Slightly slower movement due to heavy armor
                .add(Attributes.ATTACK_DAMAGE, 6.0D)     // Strong melee attacks
                .add(Attributes.FOLLOW_RANGE, 24.0D)     // Moderate follow range
                .add(Attributes.ARMOR, 8.0D)             // Heavy armor to reflect their defensive prowess
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D);  // Resistant to knockback, as befits an elite warrior
    }

    @Override
    protected void registerGoals() {
        // Goals for movement and combat
        this.goalSelector.addGoal(0, new FloatGoal(this));  // Can float in water
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));  // Melee attack goal
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 0.6D));  // Random wandering
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random looking behavior

        // Targeting goals - targeting orcs and other evil entities
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Aggro logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false, 
            entity -> entity instanceof OrcEntity || entity instanceof OrcScoutEntity || 
                      entity instanceof OrcWarriorEntity || entity instanceof UrukHaiEntity));
    }

    // Sound events: Adding specific sounds for combat and death
    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;  // Elven ambient sound for atmosphere
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.VILLAGER_HURT;  // Generic hurt sound
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;  // Generic death sound, but could be customized
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    // Define different animations based on the entity's state (idle, walk, attack)
    private PlayState predicate(AnimationState<HighElfWarriorEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.high_elf_warrior.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.high_elf_warrior.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.high_elf_warrior.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    // Return the animation cache for GeckoLib
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * Shield Block Mechanism (optional)
     * Adds a blocking ability for the warrior to shield against incoming attacks.
     * Would trigger a "block" animation and reduce incoming damage.
     */
    public void shieldBlock() {
        // Implement logic for blocking incoming damage with shield
    }
}
