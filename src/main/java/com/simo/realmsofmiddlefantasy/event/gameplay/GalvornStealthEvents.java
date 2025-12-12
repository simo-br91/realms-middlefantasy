package com.simo.realmsofmiddlefantasy.event.gameplay;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.core.ModItems;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.ai.attributes.Attributes;

@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID)
public class GalvornStealthEvents {

    private static int countGalvornPieces(Player player) {
        int count = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.is(ModItems.GALVORN_HELMET.get()) ||
                stack.is(ModItems.GALVORN_CHESTPLATE.get()) ||
                stack.is(ModItems.GALVORN_LEGGINGS.get()) ||
                stack.is(ModItems.GALVORN_BOOTS.get())) {
                count++;
            }
        }
        return count;
    }

    private static double stealthMultiplier(int pieces) {
        // 0 pieces -> no effect
        // 1 piece -> 0.85x follow range
        // 2 pieces -> 0.70x
        // 3 pieces -> 0.60x
        // 4 pieces -> 0.50x (strong stealth)
        return switch (pieces) {
            case 1 -> 0.85;
            case 2 -> 0.70;
            case 3 -> 0.60;
            case 4 -> 0.50;
            default -> 1.00;
        };
    }

    @SubscribeEvent
    public static void onChangeTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof Monster monster)) return;

        LivingEntity newTarget = event.getNewTarget();
        if (!(newTarget instanceof Player player)) return;

        int pieces = countGalvornPieces(player);
        if (pieces <= 0) return;

        double baseRange = monster.getAttributeValue(Attributes.FOLLOW_RANGE);
        double allowed = baseRange * stealthMultiplier(pieces);

        double dist = monster.distanceTo(player);
        if (dist > allowed) {
            event.setNewTarget(null); // cancel target acquisition
        }
    }

    // Extra safety: if a mob is already targeting you, drop target when too far.
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Monster monster)) return;

        LivingEntity target = monster.getTarget();
        if (!(target instanceof Player player)) return;

        int pieces = countGalvornPieces(player);
        if (pieces <= 0) return;

        double baseRange = monster.getAttributeValue(Attributes.FOLLOW_RANGE);
        double allowed = baseRange * stealthMultiplier(pieces);

        if (monster.distanceTo(player) > allowed) {
            monster.setTarget(null);
        }
    }
}
