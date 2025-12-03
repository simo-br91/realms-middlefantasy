package com.simo.realmsofmiddlefantasy.event;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Classe pour les events Forge côté MOD (enregistrement des attributs, etc.).
 */
@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ORC.get(), OrcEntity.createAttributes().build());
    }
}