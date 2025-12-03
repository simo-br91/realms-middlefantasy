package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RealmsOfMiddleFantasy.MODID);

    // ─────────────────────────────────────────────────────────
    // Dwarven core blocks
    // ─────────────────────────────────────────────────────────

    // Bloc de stockage du fer noir (métal dense, résistant)
    public static final RegistryObject<Block> BLACK_IRON_BLOCK = registerBlock("black_iron_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5.0f, 6.0f)          // proche du fer
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    // Pierre naine décorative (mur de salles naines, halls, etc.)
    public static final RegistryObject<Block> DWARVEN_STONE = registerBlock("dwarven_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.5f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // ─────────────────────────────────────────────────────────
    // Helper pour enregistrer bloc + block item
    // ─────────────────────────────────────────────────────────

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        // Enregistre automatiquement le BlockItem correspondant
        ModItems.ITEMS.register(name,
                () -> new net.minecraft.world.item.BlockItem(block.get(), new net.minecraft.world.item.Item.Properties()));
        return block;
    }
}
