package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registers all the block entities for the mod.
 */
public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RealmsOfMiddleFantasy.MODID);

    // Dwarven Forge Block Entity
    public static final RegistryObject<BlockEntityType<DwarvenForgeBlockEntity>> DWARVEN_FORGE =
            BLOCK_ENTITIES.register("dwarven_forge", 
                    () -> BlockEntityType.Builder.of(DwarvenForgeBlockEntity::new, ModBlocks.DWARVEN_FORGE.get()).build(null));
}
