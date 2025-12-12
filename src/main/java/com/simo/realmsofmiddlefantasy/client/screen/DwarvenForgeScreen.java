package com.simo.realmsofmiddlefantasy.client.screen;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.core.DwarvenForgeMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DwarvenForgeScreen extends AbstractContainerScreen<DwarvenForgeMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RealmsOfMiddleFantasy.MODID,
                    "textures/gui/dwarven_forge.png");

    public DwarvenForgeScreen(DwarvenForgeMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(net.minecraft.client.gui.GuiGraphics gui,
                            float partialTicks, int mouseX, int mouseY) {
        gui.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
