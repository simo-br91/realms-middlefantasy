package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * Registers all the blocks for the mod (Palier 1: Black Iron + Mithril).
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RealmsOfMiddleFantasy.MODID);

    // ─────────────────────────────────────────────────────────
    // Dwarven Core Blocks
    // ─────────────────────────────────────────────────────────

    // Black Iron Block - Dense and resistant metal
    public static final RegistryObject<Block> BLACK_IRON_BLOCK = registerBlock("black_iron_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5.0f, 6.0f)          // Similar to iron
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    // Dwarven Stone - Decorative stone for dwarven halls and walls
    public static final RegistryObject<Block> DWARVEN_STONE = registerBlock("dwarven_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.5f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // ─────────────────────────────────────────────────────────
    // Mithril and Ithildin Plates
    // ─────────────────────────────────────────────────────────

    // Mithril Ore - Found in the depths of the mountains
    public static final RegistryObject<Block> MITHRIL_ORE = registerBlock("mithril_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(4.0F, 8.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), UniformInt.of(3, 7)));

    // Mithril Block - A solid block of mithril, much stronger than iron
    public static final RegistryObject<Block> MITHRIL_BLOCK = registerBlock("mithril_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(6.0F, 10.0F) // stronger than iron
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    // Ithildin Plate (Unlit and Lit Versions)
        public static final RegistryObject<Block> ITHILDIN_PLATE = registerBlock("ithildin_plate",
                () -> new IthildinPlateBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.METAL)
                        .strength(3.0F, 6.0F)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.METAL)
                        .lightLevel(state -> state.getValue(IthildinPlateBlock.LIT) ? 12 : 0))); // Dynamic light

    // ─────────────────────────────────────────────────────────
    // Dwarven Forge Block
    // ─────────────────────────────────────────────────────────

    // Dwarven Forge - A place for crafting and smelting blacksmith items
    public static final RegistryObject<Block> DWARVEN_FORGE = registerBlock("dwarven_forge",
            () -> new DwarvenForgeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5.0F, 10.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    // ─────────────────────────────────────────────────────────
    // Helper Methods
    // ─────────────────────────────────────────────────────────

    // Register a block and its associated item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        // Automatically register the corresponding BlockItem
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
