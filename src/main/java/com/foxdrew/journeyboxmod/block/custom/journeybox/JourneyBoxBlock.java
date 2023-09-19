package com.foxdrew.journeyboxmod.block.custom.journeybox;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class JourneyBoxBlock extends BaseEntityBlock {
    public JourneyBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any());
    }

    @Override
    public void destroy(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        super.destroy(pLevel, pPos, pState);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new JourneyBoxBlockEntity(pPos, pState);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel,
            @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand,
            @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            pPlayer.sendMessage(new TranslatableComponent("journey.box.block.open"), pPlayer.getUUID());
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof JourneyBoxBlockEntity) {
                pPlayer.openMenu((JourneyBoxBlockEntity)blockEntity);
            }
            return InteractionResult.CONSUME;
        }
    }
}
