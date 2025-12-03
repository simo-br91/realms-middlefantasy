package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre centralisé pour tous les blocs du mod.
 * Palier 0 : Registre vide, prêt à accueillir les blocs du Palier 1.
 */
public class ModBlocks {
    
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, RealmsOfMiddleFantasy.MODID);
    
    // Les blocs seront ajoutés à partir du Palier 1
    // Exemple futur :
    // public static final RegistryObject<Block> DWARVEN_STONE = BLOCKS.register("dwarven_stone",
    //     () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 6.0F)));
}
