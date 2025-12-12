package com.simo.realmsofmiddlefantasy.entity.orc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
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
 * Orc Warrior - Standard melee combatant in the orc army.
 * Well-balanced in both offense and defense, with solid armor and powerful melee attacks.
 * A frontline fighter with decent armor, often seen leading smaller squads of orcs.
 */
public class OrcWarriorEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public OrcWarriorEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
    }

    @Override
    public net.minecraft.world.entity.SpawnGroupData finalizeSpawn(net.minecraft.world.level.ServerLevelAccessor level, net.minecraft.world.DifficultyInstance difficulty, net.minecraft.world.entity.MobSpawnType reason, @javax.annotation.Nullable net.minecraft.world.entity.SpawnGroupData spawnData, @javax.annotation.Nullable net.minecraft.nbt.CompoundTag dataTag) {
        SpawnGroupData result = super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
        
        // Logic runs once on spawn
        double difficultyMultiplier = 1 + (difficulty.getDifficulty().getId() * 0.1);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(26.0D * difficultyMultiplier);
        this.setHealth(this.getMaxHealth()); // Ensure they spawn with full health
        
        return result;
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));  // Can float in water
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));  // Melee attack
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.9D));  // Wander around
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));  // Look at players
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));  // Random look around

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));  // Aggro logic
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));  // Attack players
    }

    // ===== GECKOLIB ANIMATIONS =====
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<OrcWarriorEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("sword_slash", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("march_armed", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_ready", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
