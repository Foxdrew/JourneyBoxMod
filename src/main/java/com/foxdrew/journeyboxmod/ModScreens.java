package com.foxdrew.journeyboxmod;

import com.foxdrew.journeyboxmod.block.custom.journeybox.JourneyBoxScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreens {
    public static void register() {
        MenuScreens.register(ModMenus.JOURNEY_BOX.get(), JourneyBoxScreen::new);
    }
}
