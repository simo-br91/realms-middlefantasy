package com.simo.realmsofmiddlefantasy.entity.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Orc de base : premier mob hostile du mod.
 *
 * Version simple :
 * - mêlée,
 * - cible les joueurs,
 * - un peu plus costaud qu'un zombie.
 */
public class OrcEntity extends Monster {

    public OrcEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    // ─────────────────────────────────────────────────────────
    // Attributes de base
    // ─────────────────────────────────────────────────────────

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)      // Zombie = 20. Orc = un peu plus tanky.
                .add(Attributes.MOVEMENT_SPEED, 0.26D)  // Légèrement plus rapide qu'un zombie (~0.23)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)    // Un peu plus dangereux que zombie (~3)
                .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    // ─────────────────────────────────────────────────────────
    // Goals / IA
    // ─────────────────────────────────────────────────────────

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // Attaque en mêlée
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));

        // Déplacement
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        // Regard / ambiance
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        // Cibles
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    // ─────────────────────────────────────────────────────────
    // Spawn & sons
    // ─────────────────────────────────────────────────────────

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData,
                                        @Nullable net.minecraft.nbt.CompoundTag dataTag) {
        // Ici on pourrait adapter l'équipement selon la difficulté, etc.
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData, dataTag);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    this.level().playSound(null, pos, SoundEvents.ZOMBIE_STEP, SoundSource.HOSTILE, 0.15F, 1.0F);
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }
}
