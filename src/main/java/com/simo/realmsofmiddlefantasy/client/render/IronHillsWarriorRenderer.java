package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.IronHillsWarriorModel;
import com.simo.realmsofmiddlefantasy.entity.custom.IronHillsWarriorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class IronHillsWarriorRenderer extends GeoEntityRenderer<IronHillsWarriorEntity> {
    
    public IronHillsWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new IronHillsWarriorModel());
        this.shadowRadius = 0.5f;
    }
}