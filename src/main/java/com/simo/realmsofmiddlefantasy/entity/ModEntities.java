package com.simo.realmsofmiddlefantasy.entity;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RealmsOfMiddleFantasy.MODID);

    // ─────────────────────────────────────────────────────────
    // Orc de base (premier mob hostile)
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<OrcEntity>> ORC =
            ENTITY_TYPES.register("orc",
                    () -> EntityType.Builder.<OrcEntity>of(OrcEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f) // dimensions proches d'un humain
                            .build(RealmsOfMiddleFantasy.MODID + ":orc"));
}
