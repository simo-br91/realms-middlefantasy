package com.simo.realmsofmiddlefantasy.entity.custom;

import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeMod;


public class DwarfEntity extends AbstractVillager {

    public DwarfEntity(EntityType<? extends DwarfEntity> type, Level level) {
        super(type, level);
    }

    // Attributs de base du nain
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)        // plus tanky qu'un villageois
                .add(Attributes.MOVEMENT_SPEED, 0.32D)    // un peu lent
                .add(Attributes.ARMOR, 2.0D)              // léger bonus
                .add(Attributes.FOLLOW_RANGE, 16.0D)      // distance de "perception"
                .add(ForgeMod.ENTITY_GRAVITY.get(), 0.08D); // gravité par défaut, évite le crash
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        // Interaction de trade
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(2, new LookAtTradingPlayerGoal(this));
        // Comportement social
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        // Empêcher le comportement bizarre si on le clique avec un œuf de spawn
        if (itemstack.is(Items.VILLAGER_SPAWN_EGG)) {
            return super.mobInteract(player, hand);
        }

        // Conditions “normales” de trade : vivant, adulte, pas déjà en train de trader
        if (!this.isBaby() && this.isAlive() && !this.isTrading()) {
            if (this.level().isClientSide) {
                // Côté client : on consomme l’action, le serveur ouvrira le GUI
                return InteractionResult.CONSUME;
            } else {
                // Côté serveur : on met à jour les offres et on ouvre l’écran de trade
                this.updateTrades(); // construit/rafraîchit les MerchantOffers
                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1);
                return InteractionResult.CONSUME;
            }
        }

    // Sinon : comportement par défaut (hit, etc.)
    return super.mobInteract(player, hand);
}


    /**
     * Définition des trades du nain.
     */
    @Override
    protected void updateTrades() {
        // Initialise les offres si besoin
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }
        this.offers.clear();

        MerchantOffers offers = this.offers;

        // Trade 1 : 12 fer + 4 charbon -> 1 fer noir brut
        offers.add(new MerchantOffer(
                new ItemStack(Items.IRON_INGOT, 12),
                new ItemStack(Items.COAL, 4),
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.RAW_BLACK_IRON.get(), 1),
                16,    // max uses
                4,     // xp donné au nain
                0.05F  // ajustement de prix (faible)
        ));

        // Trade 2 : 18 fer + 6 charbon -> 1 lingot de fer noir
        offers.add(new MerchantOffer(
                new ItemStack(Items.IRON_INGOT, 18),
                new ItemStack(Items.COAL, 6),
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_INGOT.get(), 1),
                12,
                6,
                0.08F
        ));

        // Trade 3 : 6 lingots de fer noir -> 1 épée en fer noir
        offers.add(new MerchantOffer(
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_INGOT.get(), 6),
                ItemStack.EMPTY,
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_SWORD.get(), 1),
                4,
                10,
                0.12F
        ));

        // Trade 4 : 8 lingots de fer noir -> 1 plastron en fer noir
        offers.add(new MerchantOffer(
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_INGOT.get(), 8),
                ItemStack.EMPTY,
                new ItemStack(com.simo.realmsofmiddlefantasy.core.ModItems.BLACK_IRON_CHESTPLATE.get(), 1),
                4,
                12,
                0.15F
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
        // XP pour le nain (tu peux garder simple pour l’instant)
        this.level().broadcastEntityEvent(this, (byte) 14); // petites particules “trade réussi”
    }


    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob mate) {
        // Pas de reproduction de nains pour l’instant
        return null;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        super.notifyTrade(offer);
        // Tu peux ajouter du son spécial ici plus tard si tu veux
    }
}
