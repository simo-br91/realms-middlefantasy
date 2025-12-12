package com.simo.realmsofmiddlefantasy;

import com.mojang.logging.LogUtils;
import com.simo.realmsofmiddlefantasy.core.ModBlockEntities;
import com.simo.realmsofmiddlefantasy.core.ModBlocks;
import com.simo.realmsofmiddlefantasy.core.ModCreativeTabs;
import com.simo.realmsofmiddlefantasy.core.ModItems;
import com.simo.realmsofmiddlefantasy.core.ModMenus;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import com.simo.realmsofmiddlefantasy.world.ModFeatures;
import com.simo.realmsofmiddlefantasy.world.ModStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

/**
 * Main Class for "Realms of Middle-Fantasy: Chronicles of Arda".
 */
@Mod(RealmsOfMiddleFantasy.MODID)
public class RealmsOfMiddleFantasy {
    
    public static final String MODID = "realms_middlefantasy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RealmsOfMiddleFantasy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Initialize GeckoLib (Required for animations)
        GeckoLib.initialize();
        
        // ─────────────────────────────────────────────────────────
        // REGISTER DEFERRED REGISTERS
        // ─────────────────────────────────────────────────────────
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        
        // World Gen & Structures
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        // ─────────────────────────────────────────────────────────
        // REGISTER LIFECYCLE LISTENERS
        // ─────────────────────────────────────────────────────────
        // This is crucial for future networking or config setup
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("Realms of Middle-Fantasy: Core Initialized.");
    }

    // Common setup method: Used for packet registration, config loading, etc.
    private void commonSetup(final FMLCommonSetupEvent event) {
        // event.enqueueWork(() -> {
        //     PacketHandler.register();
        // });
    }
}