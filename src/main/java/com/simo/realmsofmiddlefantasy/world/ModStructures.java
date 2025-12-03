package com.simo.realmsofmiddlefantasy.world;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;

/**
 * Registre centralisé pour les types de structures custom.
 * Palier 0 : Registre vide, les structures seront ajoutées à partir du Palier 4.
 */
public class ModStructures {
    
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = 
        DeferredRegister.create(Registries.STRUCTURE_TYPE, RealmsOfMiddleFantasy.MODID);
    
    // Les structures seront ajoutées à partir du Palier 4
    // Exemple futur :
    // public static final RegistryObject<StructureType<OrcCampStructure>> ORC_CAMP = 
    //     STRUCTURE_TYPES.register("orc_camp", () -> ...);
}
