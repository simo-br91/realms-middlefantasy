package com.simo.realmsofmiddlefantasy.entity.custom;

import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DwarfEntity extends AbstractVillager implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public DwarfEntity(EntityType<? extends DwarfEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(ForgeMod.ENTITY_GRAVITY.get(), 0.08D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(2, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (itemstack.is(Items.VILLAGER_SPAWN_EGG)) {
            return super.mobInteract(player, hand);
        }

        if (!this.isBaby() && this.isAlive() && !this.isTrading()) {
            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                this.updateTrades();
                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1);
                return InteractionResult.CONSUME;
            }
        }

        return super.mobInteract(player, hand);
    }

    @Override
    protected void updateTrades() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }
        this.offers.clear();

        MerchantOffers offers = this.offers;

        offers.add(new MerchantOffer(
                new ItemStack(Items.IRON_INGOT, 12),
                new ItemStack(Items.COAL, 4),
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.RAW_BLACK_IRON.get(), 1),
                16, 4, 0.05F
        ));

        offers.add(new MerchantOffer(
                new ItemStack(Items.IRON_INGOT, 18),
                new ItemStack(Items.COAL, 6),
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_INGOT.get(), 1),
                12, 6, 0.08F
        ));

        offers.add(new MerchantOffer(
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_INGOT.get(), 6),
                ItemStack.EMPTY,
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_SWORD.get(), 1),
                4, 10, 0.12F
        ));

        offers.add(new MerchantOffer(
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_INGOT.get(), 8),
                ItemStack.EMPTY,
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_CHESTPLATE.get(), 1),
                4, 12, 0.15F
        ));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        this.level().broadcastEntityEvent(this, (byte) 14);
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob mate) {
        return null;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        super.notifyTrade(offer);
    }

    // ===== GECKOLIB ANIMATIONS =====
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<DwarfEntity> event) {
        if (this.isTrading()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_stance", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("march_heavy", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("idle_stance", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}