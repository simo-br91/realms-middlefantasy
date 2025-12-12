package com.simo.realmsofmiddlefantasy.core;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class IthildinPlateBlock extends Block {

    // Define a boolean property for the state
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public IthildinPlateBlock(Properties props) {
        super(props);
        // Set default state to unlit
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Determine state immediately upon placement based on time
        return this.defaultBlockState().setValue(LIT, context.getLevel().isNight());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 100); // Check every 5 seconds (100 ticks)
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean isNight = level.isNight();
        boolean isLit = state.getValue(LIT);

        // Only change blockstate if the condition doesn't match the current state
        if (isNight != isLit) {
            level.setBlock(pos, state.setValue(LIT, isNight), 3);
        }

        level.scheduleTick(pos, this, 100);
    }
}