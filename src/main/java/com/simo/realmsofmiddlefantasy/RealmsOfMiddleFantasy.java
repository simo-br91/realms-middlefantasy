package com.simo.realmsofmiddlefantasy;

import com.mojang.logging.LogUtils;
import com.simo.realmsofmiddlefantasy.core.ModBlocks;
import com.simo.realmsofmiddlefantasy.core.ModCreativeTabs;
import com.simo.realmsofmiddlefantasy.core.ModItems;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import com.simo.realmsofmiddlefantasy.world.ModFeatures;
import com.simo.realmsofmiddlefantasy.world.ModStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Classe principale du mod "Realms of Middle-Fantasy: Chronicles of Arda".
 * Ce mod enrichit Minecraft vanilla avec un univers inspiré du Seigneur des Anneaux.
 * 
 * Architecture CORE modulaire :
 * - Module A : Ressources (items, blocs, crafts)
 * - Module B : Mobs & Factions
 * - Module C : Structures & Worldgen
 * - Module D : Lore & Quêtes
 * - Module E : Systèmes avancés (réputation, events)
 */
@Mod(RealmsOfMiddleFantasy.MODID)
public class RealmsOfMiddleFantasy {
    
    public static final String MODID = "realms_middlefantasy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RealmsOfMiddleFantasy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Enregistrement des registres DeferredRegister
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        
        LOGGER.info("Version CORE - Palier 0/2 : Mod initialisé avec Orc de base.");
    }
}
