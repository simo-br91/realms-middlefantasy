package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.OrcWarriorModel;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcWarriorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrcWarriorRenderer extends GeoEntityRenderer<OrcWarriorEntity> {
    
    public OrcWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcWarriorModel());
        this.shadowRadius = 0.5f;
    }
}