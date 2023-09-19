package com.example.examplemod;

import com.example.examplemod.block.custom.journeybox.JourneyBoxBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, JourneyBlockMod.ID);

    public static final RegistryObject<BlockEntityType<JourneyBoxBlockEntity>> JOURNEY_BOX = registerBlockEntity("journey_box_block_entity", () -> BlockEntityType.Builder.of(JourneyBoxBlockEntity::new, ModBlocks.JOURNEY_BOX.get()).build(null));

    private static <T extends BlockEntityType<? extends BlockEntity>> RegistryObject<T> registerBlockEntity (String pName, Supplier<T> pBlockEntitySupplier) {
        return BLOCK_ENTITIES.register(pName, pBlockEntitySupplier);
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
