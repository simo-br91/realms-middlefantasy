package com.simo.realmsofmiddlefantasy.core;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

/**
 * Ithildin Plate Block: Changes state depending on day/night cycle (lit/unlit).
 */
public class IthildinPlateBlock extends Block {

    private final boolean lit;
    private final RegistryObject<Block> litBlock;
    private final RegistryObject<Block> unlitBlock;

    public IthildinPlateBlock(Properties props, boolean lit, RegistryObject<Block> litBlock, RegistryObject<Block> unlitBlock) {
        super(props);
        this.lit = lit;
        this.litBlock = litBlock;
        this.unlitBlock = unlitBlock;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) level.scheduleTick(pos, this, 20);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean night = level.isNight();

        // Switch block state based on day or night
        if (night && !this.lit) {
            level.setBlock(pos, litBlock.get().defaultBlockState(), 3);
        } else if (!night && this.lit) {
            level.setBlock(pos, unlitBlock.get().defaultBlockState(), 3);
        }

        // Schedule the next tick
        level.scheduleTick(pos, this, 40);
    }
}
