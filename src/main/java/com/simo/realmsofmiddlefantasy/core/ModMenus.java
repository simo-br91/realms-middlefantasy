package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registers custom menus for the mod.
 * Here we register the custom DwarvenForge menu.
 */
public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RealmsOfMiddleFantasy.MODID);

    /**
     * Menu for Dwarven Forge
     */
    public static final RegistryObject<MenuType<DwarvenForgeMenu>> DWARVEN_FORGE =
            MENUS.register("dwarven_forge",
                    () -> IForgeMenuType.create(DwarvenForgeMenu::new)
            );
}
