package com.simo.realmsofmiddlefantasy.world;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre centralisé pour les features de worldgen (minerais, arbres spéciaux, etc.).
 * Palier 0 : Registre vide, les features seront ajoutées aux paliers suivants.
 */
public class ModFeatures {
    
    public static final DeferredRegister<Feature<?>> FEATURES = 
        DeferredRegister.create(ForgeRegistries.FEATURES, RealmsOfMiddleFantasy.MODID);
    
    // Les features seront ajoutées selon les besoins (minerais, arbres custom...)
}
