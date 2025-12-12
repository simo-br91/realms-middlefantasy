package com.simo.realmsofmiddlefantasy.core;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Dwarven Forge Block Entity: Handles the storage and crafting logic for the Dwarven Forge.
 */
public class DwarvenForgeBlockEntity extends BlockEntity implements MenuProvider {

    public DwarvenForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DWARVEN_FORGE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.realms_middlefantasy.dwarven_forge");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, net.minecraft.world.entity.player.Inventory inv, net.minecraft.world.entity.player.Player player) {
        return new DwarvenForgeMenu(id, inv, this);
    }
}
