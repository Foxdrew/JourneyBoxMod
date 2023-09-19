package com.example.examplemod;

import com.example.examplemod.block.custom.journeybox.JourneyBoxMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModMenus {
    private static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, JourneyBlockMod.ID);

    public static final RegistryObject<MenuType<JourneyBoxMenu>> JOURNEY_BOX = registerMenuType("journey_box_menu", () -> new MenuType<>(JourneyBoxMenu::new));

    private static <T extends MenuType<? extends AbstractContainerMenu>> RegistryObject<T> registerMenuType(
            String pName, Supplier<T> pMenuTypeSupplier) {
        return MENU_TYPES.register(pName, pMenuTypeSupplier);
    }

    public static void register(IEventBus modEventBus) {
        MENU_TYPES.register(modEventBus);
    }
}
