package com.simo.realmsofmiddlefantasy.entity;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre centralisé pour toutes les entités custom (mobs, PNJ).
 * Palier 0 : Registre vide, les entités seront ajoutées à partir du Palier 2.
 */
public class ModEntities {
    
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RealmsOfMiddleFantasy.MODID);
    
    // Les entités seront ajoutées à partir du Palier 2
    // Exemple futur :
    // public static final RegistryObject<EntityType<OrcEntity>> ORC = ENTITY_TYPES.register("orc",
    //     () -> EntityType.Builder.of(OrcEntity::new, MobCategory.MONSTER)
    //         .sized(0.6F, 1.95F)
    //         .build("orc"));
}
