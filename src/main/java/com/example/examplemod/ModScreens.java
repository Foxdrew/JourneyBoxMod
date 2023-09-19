package com.example.examplemod;

import com.example.examplemod.block.custom.journeybox.JourneyBoxScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreens {
    public static void register() {
        MenuScreens.register(ModMenus.JOURNEY_BOX.get(), JourneyBoxScreen::new);
    }
}
