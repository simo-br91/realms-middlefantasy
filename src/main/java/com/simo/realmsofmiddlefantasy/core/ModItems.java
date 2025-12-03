package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre centralisé pour tous les items du mod.
 * Palier 0 : Registre vide, prêt à accueillir les items du Palier 1.
 */
public class ModItems {
    
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, RealmsOfMiddleFantasy.MODID);
    
    // Les items seront ajoutés à partir du Palier 1
    // Exemple futur :
    // public static final RegistryObject<Item> BLACK_IRON_INGOT = ITEMS.register("black_iron_ingot", 
    //     () -> new Item(new Item.Properties()));
}
