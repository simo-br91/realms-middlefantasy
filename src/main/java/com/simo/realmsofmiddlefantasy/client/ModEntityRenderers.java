package com.simo.realmsofmiddlefantasy.client;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;

import com.simo.realmsofmiddlefantasy.client.renderer.ancient.EntShepherdRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.ancient.HuornRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.beast.GreatEagleRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.beast.ShadowSpiderRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.beast.WargRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.dwarf.DwarfRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.dwarf.IronHillsWarriorRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.elf.GaladhrimArcherRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.elf.HighElfWarriorRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.elf.SilvanElfScoutRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.giant.CaveTrollRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.giant.HillTrollRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.goblin.GoblinRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.hobbit.HobbitRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.men.CitadelGuardRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.men.DunedainRangerRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.men.ManOfNorthRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.men.SoldierOfGondorRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.orc.OrcCaptainRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.orc.OrcRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.orc.OrcScoutRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.orc.OrcWarriorRenderer;
import com.simo.realmsofmiddlefantasy.client.renderer.orc.UrukHaiRenderer;

import com.simo.realmsofmiddlefantasy.client.renderer.undead.WightRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Client-only registration of entity renderers.
 */
@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ModEntityRenderers {

    private ModEntityRenderers() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        // ───── Orcs / Goblins / Uruk-hai ─────
        event.registerEntityRenderer(ModEntities.ORC.get(), OrcRenderer::new);
        event.registerEntityRenderer(ModEntities.ORC_SCOUT.get(), OrcScoutRenderer::new);
        event.registerEntityRenderer(ModEntities.ORC_WARRIOR.get(), OrcWarriorRenderer::new);
        event.registerEntityRenderer(ModEntities.ORC_CAPTAIN.get(), OrcCaptainRenderer::new);
        event.registerEntityRenderer(ModEntities.URUK_HAI.get(), UrukHaiRenderer::new);
        event.registerEntityRenderer(ModEntities.GOBLIN.get(), GoblinRenderer::new);

        // ───── Beasts / Monsters ─────
        event.registerEntityRenderer(ModEntities.WARG.get(), WargRenderer::new);
        event.registerEntityRenderer(ModEntities.SHADOW_SPIDER.get(), ShadowSpiderRenderer::new);
        event.registerEntityRenderer(ModEntities.GREAT_EAGLE.get(), GreatEagleRenderer::new);

        // ───── Trolls ─────
        event.registerEntityRenderer(ModEntities.HILL_TROLL.get(), HillTrollRenderer::new);
        event.registerEntityRenderer(ModEntities.CAVE_TROLL.get(), CaveTrollRenderer::new);

        // ───── Undead ─────
        event.registerEntityRenderer(ModEntities.WIGHT.get(), WightRenderer::new);

        // ───── Free Peoples ─────
        event.registerEntityRenderer(ModEntities.DWARF.get(), DwarfRenderer::new);
        event.registerEntityRenderer(ModEntities.IRON_HILLS_WARRIOR.get(), IronHillsWarriorRenderer::new);

        event.registerEntityRenderer(ModEntities.HIGH_ELF_WARRIOR.get(), HighElfWarriorRenderer::new);
        event.registerEntityRenderer(ModEntities.SILVAN_ELF_SCOUT.get(), SilvanElfScoutRenderer::new);
        event.registerEntityRenderer(ModEntities.GALADHRIM_ARCHER.get(), GaladhrimArcherRenderer::new);

        event.registerEntityRenderer(ModEntities.DUNEDAIN_RANGER.get(), DunedainRangerRenderer::new);
        event.registerEntityRenderer(ModEntities.MAN_OF_NORTH.get(), ManOfNorthRenderer::new);
        event.registerEntityRenderer(ModEntities.CITADEL_GUARD.get(), CitadelGuardRenderer::new);
        event.registerEntityRenderer(ModEntities.SOLDIER_OF_GONDOR.get(), SoldierOfGondorRenderer::new);

        event.registerEntityRenderer(ModEntities.HOBBIT.get(), HobbitRenderer::new);

        // ───── Ancient ─────
        event.registerEntityRenderer(ModEntities.ENT_SHEPHERD.get(), EntShepherdRenderer::new);
        event.registerEntityRenderer(ModEntities.HUORN.get(), HuornRenderer::new);
    }
}
