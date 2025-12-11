package com.simo.realmsofmiddlefantasy.event;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.ShadowSpiderEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.TrollEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.UrukHaiEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.WargEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.WightEntity;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.simo.realmsofmiddlefantasy.entity.custom.DwarfEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.GoblinEntity;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcCaptainEntity;

/**
 * Classe pour les events Forge côté MOD (enregistrement des attributs, etc.).
 */
@Mod.EventBusSubscriber(modid = RealmsOfMiddleFantasy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ORC.get(), OrcEntity.createAttributes().build());
        event.put(ModEntities.ORC_SCOUT.get(), OrcScoutEntity.createAttributes().build());
        event.put(ModEntities.ORC_WARRIOR.get(), OrcWarriorEntity.createAttributes().build());
        event.put(ModEntities.ORC_CAPTAIN.get(), OrcCaptainEntity.createAttributes().build());
        event.put(ModEntities.URUK_HAI.get(), UrukHaiEntity.createAttributes().build());
        event.put(ModEntities.GOBLIN.get(), GoblinEntity.createAttributes().build());
        event.put(ModEntities.WARG.get(), WargEntity.createAttributes().build());
        event.put(ModEntities.TROLL.get(), TrollEntity.createAttributes().build());
        event.put(ModEntities.SHADOW_SPIDER.get(), ShadowSpiderEntity.createAttributes().build());
        event.put(ModEntities.WIGHT.get(), WightEntity.createAttributes().build());
        event.put(ModEntities.DWARF.get(), DwarfEntity.createAttributes().build());
    }

}