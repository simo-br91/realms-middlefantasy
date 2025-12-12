package com.simo.realmsofmiddlefantasy.event;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import com.simo.realmsofmiddlefantasy.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Classe pour les events Forge côté MOD (enregistrement des attributs, etc.).
 */
@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        // Orcs
        event.put(ModEntities.ORC.get(), OrcEntity.createAttributes().build());
        event.put(ModEntities.ORC_SCOUT.get(), OrcScoutEntity.createAttributes().build());
        event.put(ModEntities.ORC_WARRIOR.get(), OrcWarriorEntity.createAttributes().build());
        event.put(ModEntities.ORC_CAPTAIN.get(), OrcCaptainEntity.createAttributes().build());
        
        // Uruk-hai
        event.put(ModEntities.URUK_HAI.get(), UrukHaiEntity.createAttributes().build());
        
        // Goblins
        event.put(ModEntities.GOBLIN.get(), GoblinEntity.createAttributes().build());
        
        // Wargs
        event.put(ModEntities.WARG.get(), WargEntity.createAttributes().build());
        
        // Trolls (DEUX VARIANTES)
        event.put(ModEntities.HILL_TROLL.get(), HillTrollEntity.createAttributes().build());
        event.put(ModEntities.CAVE_TROLL.get(), CaveTrollEntity.createAttributes().build());
        
        // Araignées d'ombre
        event.put(ModEntities.SHADOW_SPIDER.get(), ShadowSpiderEntity.createAttributes().build());
        
        // Wights
        event.put(ModEntities.WIGHT.get(), WightEntity.createAttributes().build());
        
        // Peuples Libres
        event.put(ModEntities.DWARF.get(), DwarfEntity.createAttributes().build());

        // Elves
        event.put(ModEntities.HIGH_ELF_WARRIOR.get(), HighElfWarriorEntity.createAttributes().build());
        event.put(ModEntities.SILVAN_ELF_SCOUT.get(), SilvanElfScoutEntity.createAttributes().build());
        event.put(ModEntities.GALADHRIM_ARCHER.get(), GaladhrimArcherEntity.createAttributes().build());
    }
}