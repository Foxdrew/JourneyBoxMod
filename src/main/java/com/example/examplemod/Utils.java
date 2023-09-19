package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public class Utils {
    public static double getDistanceBetweenPlayerAndBlockPosition(Player pPlayer, BlockPos pBlockPos) {
        return pPlayer.distanceToSqr(pBlockPos.getX() + .5d, pBlockPos.getY() + .5d, pBlockPos.getZ() + .5d);
    }
}
