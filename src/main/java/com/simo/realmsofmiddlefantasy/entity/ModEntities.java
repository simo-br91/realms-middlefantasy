package com.simo.realmsofmiddlefantasy.entity;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RealmsOfMiddleFantasy.MODID);

    // ─────────────────────────────────────────────────────────
    // Orc de base + variantes
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<OrcEntity>> ORC =
            ENTITY_TYPES.register("orc",
                    () -> EntityType.Builder.<OrcEntity>of(OrcEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc"));

    public static final RegistryObject<EntityType<OrcScoutEntity>> ORC_SCOUT =
            ENTITY_TYPES.register("orc_scout",
                    () -> EntityType.Builder.<OrcScoutEntity>of(OrcScoutEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc_scout"));

    public static final RegistryObject<EntityType<OrcWarriorEntity>> ORC_WARRIOR =
            ENTITY_TYPES.register("orc_warrior",
                    () -> EntityType.Builder.<OrcWarriorEntity>of(OrcWarriorEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc_warrior"));

    public static final RegistryObject<EntityType<OrcCaptainEntity>> ORC_CAPTAIN =
            ENTITY_TYPES.register("orc_captain",
                    () -> EntityType.Builder.<OrcCaptainEntity>of(OrcCaptainEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc_captain"));

    // ─────────────────────────────────────────────────────────
    // Uruk-hai
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<UrukHaiEntity>> URUK_HAI =
            ENTITY_TYPES.register("uruk_hai",
                    () -> EntityType.Builder.<UrukHaiEntity>of(UrukHaiEntity::new, MobCategory.MONSTER)
                            .sized(0.7f, 2.1f)
                            .build(RealmsOfMiddleFantasy.MODID + ":uruk_hai"));

    // ─────────────────────────────────────────────────────────
    // Goblins
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<GoblinEntity>> GOBLIN =
            ENTITY_TYPES.register("goblin",
                    () -> EntityType.Builder.<GoblinEntity>of(GoblinEntity::new, MobCategory.MONSTER)
                            .sized(0.5f, 1.4f)
                            .build(RealmsOfMiddleFantasy.MODID + ":goblin"));

    // ─────────────────────────────────────────────────────────
    // Wargs
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<WargEntity>> WARG =
            ENTITY_TYPES.register("warg",
                    () -> EntityType.Builder.<WargEntity>of(WargEntity::new, MobCategory.MONSTER)
                            .sized(1.2f, 1.4f)
                            .build(RealmsOfMiddleFantasy.MODID + ":warg"));

    // ─────────────────────────────────────────────────────────
    // Trolls (DEUX VARIANTES DISTINCTES)
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<HillTrollEntity>> HILL_TROLL =
            ENTITY_TYPES.register("hill_troll",
                    () -> EntityType.Builder.<HillTrollEntity>of(HillTrollEntity::new, MobCategory.MONSTER)
                            .sized(1.3f, 3.0f)
                            .build(RealmsOfMiddleFantasy.MODID + ":hill_troll"));

    public static final RegistryObject<EntityType<CaveTrollEntity>> CAVE_TROLL =
            ENTITY_TYPES.register("cave_troll",
                    () -> EntityType.Builder.<CaveTrollEntity>of(CaveTrollEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 3.4f)
                            .build(RealmsOfMiddleFantasy.MODID + ":cave_troll"));

    // ─────────────────────────────────────────────────────────
    // Araignées d'ombre
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<ShadowSpiderEntity>> SHADOW_SPIDER =
            ENTITY_TYPES.register("shadow_spider",
                    () -> EntityType.Builder.<ShadowSpiderEntity>of(ShadowSpiderEntity::new, MobCategory.MONSTER)
                            .sized(1.4f, 0.9f)
                            .build(RealmsOfMiddleFantasy.MODID + ":shadow_spider"));

    // ─────────────────────────────────────────────────────────
    // Wights
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<WightEntity>> WIGHT =
            ENTITY_TYPES.register("wight",
                    () -> EntityType.Builder.<WightEntity>of(WightEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":wight"));

    // ─────────────────────────────────────────────────────────
    // Peuples Libres
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<EntityType<DwarfEntity>> DWARF =
            ENTITY_TYPES.register("dwarf", () ->
                    EntityType.Builder.<DwarfEntity>of(DwarfEntity::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.4F)
                            .build(new ResourceLocation(RealmsOfMiddleFantasy.MODID, "dwarf").toString())
            );
}