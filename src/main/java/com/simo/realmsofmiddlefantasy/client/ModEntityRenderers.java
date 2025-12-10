package com.simo.realmsofmiddlefantasy.client;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.client.render.DwarfRenderer;
import com.simo.realmsofmiddlefantasy.client.render.OrcRenderer;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ORC.get(), OrcRenderer::new);
        event.registerEntityRenderer(ModEntities.DWARF.get(), DwarfRenderer::new);
    }
}