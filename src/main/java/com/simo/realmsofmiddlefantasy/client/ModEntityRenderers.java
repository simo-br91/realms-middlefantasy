package com.simo.realmsofmiddlefantasy.client;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.client.render.*;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Orcs (Base + Variantes)
        event.registerEntityRenderer(ModEntities.ORC.get(), OrcRenderer::new);
        event.registerEntityRenderer(ModEntities.ORC_SCOUT.get(), OrcScoutRenderer::new);
        event.registerEntityRenderer(ModEntities.ORC_WARRIOR.get(), OrcWarriorRenderer::new);
        event.registerEntityRenderer(ModEntities.ORC_CAPTAIN.get(), OrcCaptainRenderer::new);
        
        // Uruk-hai
        event.registerEntityRenderer(ModEntities.URUK_HAI.get(), UrukHaiRenderer::new);
        
        // Goblins
        event.registerEntityRenderer(ModEntities.GOBLIN.get(), GoblinRenderer::new);
        
        // Wargs
        event.registerEntityRenderer(ModEntities.WARG.get(), WargRenderer::new);
        
        // Trolls
        event.registerEntityRenderer(ModEntities.TROLL.get(), TrollRenderer::new);
        
        // Araignées d'ombre
        event.registerEntityRenderer(ModEntities.SHADOW_SPIDER.get(), ShadowSpiderRenderer::new);
        
        // Wights
        event.registerEntityRenderer(ModEntities.WIGHT.get(), WightRenderer::new);
        
        // Peuples Libres
        event.registerEntityRenderer(ModEntities.DWARF.get(), DwarfRenderer::new);
    }
}