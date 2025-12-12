package com.simo.realmsofmiddlefantasy.entity;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ancient.EntShepherdEntity;
import com.simo.realmsofmiddlefantasy.entity.ancient.HuornEntity;
import com.simo.realmsofmiddlefantasy.entity.beast.GreatEagleEntity;
import com.simo.realmsofmiddlefantasy.entity.beast.ShadowSpiderEntity;
import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;
import com.simo.realmsofmiddlefantasy.entity.dwarf.DwarfEntity;
import com.simo.realmsofmiddlefantasy.entity.dwarf.IronHillsWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.elf.GaladhrimArcherEntity;
import com.simo.realmsofmiddlefantasy.entity.elf.HighElfWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.elf.SilvanElfScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.giant.CaveTrollEntity;
import com.simo.realmsofmiddlefantasy.entity.giant.HillTrollEntity;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.hobbit.HobbitEntity;
import com.simo.realmsofmiddlefantasy.entity.men.CitadelGuardEntity;
import com.simo.realmsofmiddlefantasy.entity.men.DunedainRangerEntity;
import com.simo.realmsofmiddlefantasy.entity.men.ManOfNorthEntity;
import com.simo.realmsofmiddlefantasy.entity.men.SoldierOfGondorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcCaptainEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;
import com.simo.realmsofmiddlefantasy.entity.undead.WightEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    // Deferred Register for all entities in the mod
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RealmsOfMiddleFantasy.MODID);

    // ─────────────────────────────────────────────────────────
    // Orcs: Base Orc + Variants
    // ─────────────────────────────────────────────────────────
    
    // Basic Orc
    public static final RegistryObject<EntityType<OrcEntity>> ORC =
            ENTITY_TYPES.register("orc",
                    () -> EntityType.Builder.<OrcEntity>of(OrcEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc"));

    // Orc Scout - Agile ranged variant
    public static final RegistryObject<EntityType<OrcScoutEntity>> ORC_SCOUT =
            ENTITY_TYPES.register("orc_scout",
                    () -> EntityType.Builder.<OrcScoutEntity>of(OrcScoutEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc_scout"));

    // Orc Warrior - Standard melee orc
    public static final RegistryObject<EntityType<OrcWarriorEntity>> ORC_WARRIOR =
            ENTITY_TYPES.register("orc_warrior",
                    () -> EntityType.Builder.<OrcWarriorEntity>of(OrcWarriorEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc_warrior"));

    // Orc Captain - Leadership unit that boosts nearby orcs
    public static final RegistryObject<EntityType<OrcCaptainEntity>> ORC_CAPTAIN =
            ENTITY_TYPES.register("orc_captain",
                    () -> EntityType.Builder.<OrcCaptainEntity>of(OrcCaptainEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":orc_captain"));

    // ─────────────────────────────────────────────────────────
    // Uruk-Hai - Elite orc warriors
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<UrukHaiEntity>> URUK_HAI =
            ENTITY_TYPES.register("uruk_hai",
                    () -> EntityType.Builder.<UrukHaiEntity>of(UrukHaiEntity::new, MobCategory.MONSTER)
                            .sized(0.7f, 2.1f)
                            .build(RealmsOfMiddleFantasy.MODID + ":uruk_hai"));

    // ─────────────────────────────────────────────────────────
    // Goblins - Small, nimble enemies
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<GoblinEntity>> GOBLIN =
            ENTITY_TYPES.register("goblin",
                    () -> EntityType.Builder.<GoblinEntity>of(GoblinEntity::new, MobCategory.MONSTER)
                            .sized(0.5f, 1.4f)
                            .build(RealmsOfMiddleFantasy.MODID + ":goblin"));

    // ─────────────────────────────────────────────────────────
    // Wargs - Giant wolf-like beasts used by Orcs
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<WargEntity>> WARG =
            ENTITY_TYPES.register("warg",
                    () -> EntityType.Builder.<WargEntity>of(WargEntity::new, MobCategory.MONSTER)
                            .sized(1.2f, 1.4f)
                            .build(RealmsOfMiddleFantasy.MODID + ":warg"));

    // ─────────────────────────────────────────────────────────
    // Trolls - Two distinct variants: Hill Troll and Cave Troll
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
    // Shadow Spiders - Lurking predators of the dark
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<ShadowSpiderEntity>> SHADOW_SPIDER =
            ENTITY_TYPES.register("shadow_spider",
                    () -> EntityType.Builder.<ShadowSpiderEntity>of(ShadowSpiderEntity::new, MobCategory.MONSTER)
                            .sized(1.4f, 0.9f)
                            .build(RealmsOfMiddleFantasy.MODID + ":shadow_spider"));

    // ─────────────────────────────────────────────────────────
    // Wights - Spectral creatures that drain life and cause confusion
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<WightEntity>> WIGHT =
            ENTITY_TYPES.register("wight",
                    () -> EntityType.Builder.<WightEntity>of(WightEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":wight"));

    // ─────────────────────────────────────────────────────────
    // Free Peoples of Middle-earth
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<DwarfEntity>> DWARF =
            ENTITY_TYPES.register("dwarf", () ->
                    EntityType.Builder.<DwarfEntity>of(DwarfEntity::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.4F)
                            .build(new ResourceLocation(RealmsOfMiddleFantasy.MODID, "dwarf").toString())
            );

    // Elves - High, Silvan, and Galadhrim variants
    public static final RegistryObject<EntityType<HighElfWarriorEntity>> HIGH_ELF_WARRIOR =
            ENTITY_TYPES.register("high_elf_warrior",
                    () -> EntityType.Builder.<HighElfWarriorEntity>of(HighElfWarriorEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":high_elf_warrior"));

    public static final RegistryObject<EntityType<SilvanElfScoutEntity>> SILVAN_ELF_SCOUT =
            ENTITY_TYPES.register("silvan_elf_scout",
                    () -> EntityType.Builder.<SilvanElfScoutEntity>of(SilvanElfScoutEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":silvan_elf_scout"));

    public static final RegistryObject<EntityType<GaladhrimArcherEntity>> GALADHRIM_ARCHER =
            ENTITY_TYPES.register("galadhrim_archer",
                    () -> EntityType.Builder.<GaladhrimArcherEntity>of(GaladhrimArcherEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":galadhrim_archer"));

    // ─────────────────────────────────────────────────────────
    // Men of the West
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<DunedainRangerEntity>> DUNEDAIN_RANGER =
            ENTITY_TYPES.register("dunedain_ranger",
                    () -> EntityType.Builder.<DunedainRangerEntity>of(DunedainRangerEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":dunedain_ranger"));

    public static final RegistryObject<EntityType<ManOfNorthEntity>> MAN_OF_NORTH =
            ENTITY_TYPES.register("man_of_north",
                    () -> EntityType.Builder.<ManOfNorthEntity>of(ManOfNorthEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":man_of_north"));

    // ─────────────────────────────────────────────────────────
    // Gondor Forces
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<CitadelGuardEntity>> CITADEL_GUARD =
            ENTITY_TYPES.register("citadel_guard",
                    () -> EntityType.Builder.<CitadelGuardEntity>of(CitadelGuardEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":citadel_guard"));

    public static final RegistryObject<EntityType<SoldierOfGondorEntity>> SOLDIER_OF_GONDOR =
            ENTITY_TYPES.register("soldier_of_gondor",
                    () -> EntityType.Builder.<SoldierOfGondorEntity>of(SoldierOfGondorEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.95f)
                            .build(RealmsOfMiddleFantasy.MODID + ":soldier_of_gondor"));

    // ─────────────────────────────────────────────────────────
    // Hobbit - Small, peaceful creatures from the Shire
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<HobbitEntity>> HOBBIT =
            ENTITY_TYPES.register("hobbit",
                    () -> EntityType.Builder.<HobbitEntity>of(HobbitEntity::new, MobCategory.CREATURE)
                            .sized(0.5f, 1.2f)  // Hobbits are small
                            .build(RealmsOfMiddleFantasy.MODID + ":hobbit"));

    // ─────────────────────────────────────────────────────────
    // Iron Hills Warrior - Dwarven elite warriors
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<IronHillsWarriorEntity>> IRON_HILLS_WARRIOR =
            ENTITY_TYPES.register("iron_hills_warrior",
                    () -> EntityType.Builder.<IronHillsWarriorEntity>of(IronHillsWarriorEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.5f)
                            .build(RealmsOfMiddleFantasy.MODID + ":iron_hills_warrior"));

    // ─────────────────────────────────────────────────────────
    // Ancient Guardians - Powerful protectors of nature
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<EntShepherdEntity>> ENT_SHEPHERD =
            ENTITY_TYPES.register("ent_shepherd",
                    () -> EntityType.Builder.<EntShepherdEntity>of(EntShepherdEntity::new, MobCategory.CREATURE)
                            .sized(1.8f, 4.5f)  // Massive size for Ents
                            .build(RealmsOfMiddleFantasy.MODID + ":ent_shepherd"));

    public static final RegistryObject<EntityType<HuornEntity>> HUORN =
            ENTITY_TYPES.register("huorn",
                    () -> EntityType.Builder.<HuornEntity>of(HuornEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 3.5f)  // Large tree-like creature
                            .build(RealmsOfMiddleFantasy.MODID + ":huorn"));

    // ─────────────────────────────────────────────────────────
    // Great Eagle - Majestic flying beast of Middle Earth
    // ─────────────────────────────────────────────────────────
    public static final RegistryObject<EntityType<GreatEagleEntity>> GREAT_EAGLE =
            ENTITY_TYPES.register("great_eagle",
                    () -> EntityType.Builder.<GreatEagleEntity>of(GreatEagleEntity::new, MobCategory.CREATURE)
                            .sized(1.8f, 2.0f)  // Large flying creature
                            .build(RealmsOfMiddleFantasy.MODID + ":great_eagle"));

}
