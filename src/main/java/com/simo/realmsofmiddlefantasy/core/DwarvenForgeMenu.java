package com.simo.realmsofmiddlefantasy.core;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Dwarven Forge Menu: Manages player inventory and interactions with the Dwarven Forge block entity.
 */
public class DwarvenForgeMenu extends AbstractContainerMenu {

    private final DwarvenForgeBlockEntity blockEntity;

    // Constructor called client-side from network
    public DwarvenForgeMenu(int id, Inventory playerInv, FriendlyByteBuf buf) {
        this(id, playerInv, getBlockEntity(playerInv, buf));
    }

    // Constructor called server-side from block entity
    public DwarvenForgeMenu(int id, Inventory playerInv, DwarvenForgeBlockEntity blockEntity) {
        super(ModMenus.DWARVEN_FORGE.get(), id);
        this.blockEntity = blockEntity;

        // Player inventory slots
        for (int row = 0; row < 3; ++row)
            for (int col = 0; col < 9; ++col)
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));

        // Hotbar slots
        for (int col = 0; col < 9; ++col)
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
    }

    private static DwarvenForgeBlockEntity getBlockEntity(Inventory playerInv, FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        Level level = playerInv.player.level();
        if (level.getBlockEntity(pos) instanceof DwarvenForgeBlockEntity be) return be;
        throw new IllegalStateException("DwarvenForge block entity not found at " + pos);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
