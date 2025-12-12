package com.simo.realmsofmiddlefantasy.entity.dwarf;

import com.simo.realmsofmiddlefantasy.entity.beast.ShadowSpiderEntity;
import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;
import com.simo.realmsofmiddlefantasy.entity.giant.CaveTrollEntity;
import com.simo.realmsofmiddlefantasy.entity.giant.HillTrollEntity;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcCaptainEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;
import com.simo.realmsofmiddlefantasy.entity.undead.WightEntity;

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

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Iron Hills Warrior - Elite dwarven warrior from the Iron Hills.
 * Heavily armored, excellent melee combatant with superior defense.
 */
public class IronHillsWarriorEntity extends Monster implements GeoEntity {
    
    // Animation cache for GeoLib
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public IronHillsWarriorEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    /**
     * Registers the attributes (health, movement speed, armor, etc.) for the Iron Hills Warrior.
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)      // Increased health for durability
                .add(Attributes.MOVEMENT_SPEED, 0.23D)   // Slow but steady movement
                .add(Attributes.ATTACK_DAMAGE, 8.0D)     // Strong melee attacks
                .add(Attributes.FOLLOW_RANGE, 20.0D)     // Moderate follow range
                .add(Attributes.ARMOR, 12.0D)            // Excellent armor for tanking
                .add(Attributes.ARMOR_TOUGHNESS, 4.0D)   // Tough armor to withstand damage
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.7D);  // Highly resistant to knockback
    }

    /**
     * Registers the goals for the Iron Hills Warrior.
     * Goals include movement, attack, and target selection.
     */
    @Override
    protected void registerGoals() {
        // Basic goals
        this.goalSelector.addGoal(0, new FloatGoal(this)); // Can float in water
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false)); // Melee attack goal
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 0.6D)); // Random wandering behavior
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 10.0F)); // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this)); // Random look-around behavior

        // Targeting goals - attacking enemies
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // Hurt by target (aggro logic)
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false,
            entity -> entity instanceof OrcEntity || entity instanceof OrcScoutEntity || 
                      entity instanceof OrcWarriorEntity || entity instanceof OrcCaptainEntity ||
                      entity instanceof UrukHaiEntity || entity instanceof GoblinEntity || 
                      entity instanceof WargEntity || entity instanceof HillTrollEntity ||
                      entity instanceof CaveTrollEntity || entity instanceof ShadowSpiderEntity ||
                      entity instanceof WightEntity));
    }

    /**
     * Sounds for the Iron Hills Warrior (ambient, hurt, and death).
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT; // Villager ambient sound for flavor
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT; // Iron Golem hurt sound for heavy armor
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH; // Iron Golem death sound for heavy death
    }

    /**
     * Scales down the entity's size.
     * Dwarves are smaller and stocky.
     */
    @Override
    public float getScale() {
        return 1.0F; // Adjusted to give the warrior a more imposing stature
    }

    /**
     * Registers animation controllers to handle different animation states (idle, walking, attacking).
     */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    /**
     * Animation logic to control the behavior of the entity based on state (idle, attack, walk).
     */
    private PlayState predicate(AnimationState<IronHillsWarriorEntity> event) {
        // Check if the entity is dead or dying
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.iron_hills_warrior.idle", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        // Check if the entity is swinging (attacking)
        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.iron_hills_warrior.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        // Check movement and apply animation
        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.iron_hills_warrior.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.iron_hills_warrior.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    /**
     * Returns the animation cache for GeckoLib.
     */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache; // Return the animation cache for the entity
    }
}
