package com.example.examplemod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JourneyBlockMod.ID);

    public static final RegistryObject<Item> JOURNEY_BOX = registerBlock(ModBlocks.JOURNEY_BOX, CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Item> DILDO_BLOCK = registerBlock(ModBlocks.DILDO_BLOCK, CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Item> DILDO = registerItem("dildo", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static <T extends Item> RegistryObject<Item> registerItem(String pName, Supplier<T> pItemSupplier) {
        return ITEMS.register(pName, pItemSupplier);
    }

    public static <T extends Block> RegistryObject<Item> registerBlock(String pName, RegistryObject<T> pRegistryBlock, CreativeModeTab pTab) {
        return ITEMS.register(pName, () -> new BlockItem(pRegistryBlock.get(), new Item.Properties().tab(pTab)));
    }

    public static <T extends Block> RegistryObject<Item> registerBlock(RegistryObject<T> pRegistryBlock, CreativeModeTab pTab) {
        return registerBlock(pRegistryBlock.getId().getPath(), pRegistryBlock, pTab);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
