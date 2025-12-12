package com.simo.realmsofmiddlefantasy.entity.hobbit;

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
import net.minecraft.world.entity.monster.Monster; // Add this import

/**
 * Hobbit - Peaceful trading entity from the Shire.
 * Trades food items and simple goods.
 */
public class HobbitEntity extends AbstractVillager implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public HobbitEntity(EntityType<? extends HobbitEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.ARMOR, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(ForgeMod.ENTITY_GRAVITY.get(), 0.08D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));  // Trade goal
        this.goalSelector.addGoal(2, new LookAtTradingPlayerGoal(this)); // Look at the player while trading
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.5D)); // Random walking behavior
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F)); // Look at players
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this)); // Random look around

        // Flee when attacked (cowardly nature)
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, Monster.class, 12.0F, 1.5D, 2.0D)); // Avoid hostile mobs
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
                this.openTradingScreen(player, this.getDisplayName(), 1);  // Open trading interface
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

        // Hobbit bread trade
        offers.add(new MerchantOffer(
                new ItemStack(Items.WHEAT, 3),
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.HOBBIT_BREAD.get(), 2),
                12, 3, 0.05F
        ));

        // Basic food trades
        offers.add(new MerchantOffer(
                new ItemStack(Items.EMERALD, 1),
                ItemStack.EMPTY,
                new ItemStack(Items.COOKED_BEEF, 4),
                16, 2, 0.05F
        ));

        offers.add(new MerchantOffer(
                new ItemStack(Items.EMERALD, 1),
                ItemStack.EMPTY,
                new ItemStack(Items.APPLE, 6),
                16, 2, 0.05F
        ));

        // Seeds and farming supplies
        offers.add(new MerchantOffer(
                new ItemStack(Items.EMERALD, 1),
                ItemStack.EMPTY,
                new ItemStack(Items.WHEAT_SEEDS, 12),
                16, 1, 0.05F
        ));
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
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

    @Override
    public float getScale() {
        return 0.85F;  // Hobbits are smaller than most creatures
    }

    // ===== GECKOLIB ANIMATIONS =====
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<HobbitEntity> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.hobbit.idle", Animation.LoopType.LOOP)
            );
            return PlayState.CONTINUE;
        }

        if (this.swinging) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.hobbit.attack", Animation.LoopType.PLAY_ONCE)
            );
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.hobbit.walk", Animation.LoopType.LOOP)
            );
        } else {
            event.getController().setAnimation(
                RawAnimation.begin().then("animation.hobbit.idle", Animation.LoopType.LOOP)
            );
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
