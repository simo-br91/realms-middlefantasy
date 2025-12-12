package com.simo.realmsofmiddlefantasy.event;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
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

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * This class handles the registration of entity attributes for all modded entities in the game.
 * It listens to the EntityAttributeCreationEvent to add specific attributes for different entities.
 */
@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    /**
     * Registers the attributes for all entities in the mod.
     * This event is triggered when the game initializes entity attributes.
     */
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        
        // ─────────────────────────────────────────────────────────
        // Orcs: Base Orc + Variants
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.ORC.get(), OrcEntity.createAttributes().build());
        event.put(ModEntities.ORC_SCOUT.get(), OrcScoutEntity.createAttributes().build());
        event.put(ModEntities.ORC_WARRIOR.get(), OrcWarriorEntity.createAttributes().build());
        event.put(ModEntities.ORC_CAPTAIN.get(), OrcCaptainEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Uruk-Hai - Elite orc warriors
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.URUK_HAI.get(), UrukHaiEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Goblins - Small, nimble enemies
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.GOBLIN.get(), GoblinEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Wargs - Wolf-like beasts used by Orcs
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.WARG.get(), WargEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Trolls - Two distinct variants: Hill Troll and Cave Troll
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.HILL_TROLL.get(), HillTrollEntity.createAttributes().build());
        event.put(ModEntities.CAVE_TROLL.get(), CaveTrollEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Shadow Spiders - Creeping predators of the dark
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.SHADOW_SPIDER.get(), ShadowSpiderEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Wights - Spectral creatures that drain life and confuse enemies
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.WIGHT.get(), WightEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Free Peoples of Middle-earth: Dwarves, Elves, Men
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.DWARF.get(), DwarfEntity.createAttributes().build());
        
        // Elves
        event.put(ModEntities.HIGH_ELF_WARRIOR.get(), HighElfWarriorEntity.createAttributes().build());
        event.put(ModEntities.SILVAN_ELF_SCOUT.get(), SilvanElfScoutEntity.createAttributes().build());
        event.put(ModEntities.GALADHRIM_ARCHER.get(), GaladhrimArcherEntity.createAttributes().build());

        // Men of the West
        event.put(ModEntities.DUNEDAIN_RANGER.get(), DunedainRangerEntity.createAttributes().build());
        event.put(ModEntities.MAN_OF_NORTH.get(), ManOfNorthEntity.createAttributes().build());

        // Gondor Forces
        event.put(ModEntities.CITADEL_GUARD.get(), CitadelGuardEntity.createAttributes().build());
        event.put(ModEntities.SOLDIER_OF_GONDOR.get(), SoldierOfGondorEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Hobbit - Small, peaceful creatures from the Shire
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.HOBBIT.get(), HobbitEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Iron Hills Warrior - Elite dwarven warriors
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.IRON_HILLS_WARRIOR.get(), IronHillsWarriorEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Ancient Guardians - Protectors of nature
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.ENT_SHEPHERD.get(), EntShepherdEntity.createAttributes().build());
        event.put(ModEntities.HUORN.get(), HuornEntity.createAttributes().build());

        // ─────────────────────────────────────────────────────────
        // Great Eagle - Majestic flying creature of Middle Earth
        // ─────────────────────────────────────────────────────────
        event.put(ModEntities.GREAT_EAGLE.get(), GreatEagleEntity.createAttributes().build());
    }
}
