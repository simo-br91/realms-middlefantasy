package com.simo.realmsofmiddlefantasy.client;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.client.render.OrcRenderer;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Enregistrement des renderers et layer definitions pour les entités du mod.
 */
@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {

    // Déclare la LayerDefinition pour ORC_LAYER
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OrcRenderer.ORC_LAYER, OrcRenderer::createBodyLayer);
    }

    // Déclare le renderer de l'Orc
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ORC.get(), OrcRenderer::new);
    }
}
