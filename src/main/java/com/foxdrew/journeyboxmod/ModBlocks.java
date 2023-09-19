package com.foxdrew.journeyboxmod;

import com.foxdrew.journeyboxmod.block.custom.journeybox.JourneyBoxBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, JourneyBlockMod.ID);

    public static final RegistryObject<Block> DILDO_BLOCK = registerBlock("dildo_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(10f)));
    public static final RegistryObject<Block> JOURNEY_BOX = registerBlock("journey_box_block", () -> new JourneyBoxBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f)));

    private static RegistryObject<Block> registerBlock(String pName, Supplier<Block> pBlockSupplier) {
        return BLOCKS.register(pName, pBlockSupplier);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
