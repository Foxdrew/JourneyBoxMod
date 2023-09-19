package com.foxdrew.journeyboxmod;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractExtendedContainerMenu extends AbstractContainerMenu {
    private static final int INVENTORY_HEIGHT = 3;
    private static final int INVENTORY_WIDTH = 9;
    private static final int SLOT_SIZE_PX = 18;

    protected AbstractExtendedContainerMenu(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    protected void drawDefaultPlayerInventory(Container container) {
        drawDefaultPlayerInventory(container, 0, 0);
    }

    protected void drawDefaultPlayerInventory(Container pContainer, int pX, int pY) {


        for(int y = 0; y < INVENTORY_HEIGHT; ++y) {
            for(int x = 0; x < INVENTORY_WIDTH; ++x) {
                addSlot(new Slot(pContainer, x + y * 9 + 9, getCellCordPosition(pX, x), getCellCordPosition(pY, y)));
            }
        }

        for(int x = 0; x < INVENTORY_WIDTH; ++x) {
            addSlot(new Slot(pContainer, x, getCellCordPosition(pX, x), pY + 4 + 3 * 18));
        }
    }

    private int getCellCordPosition(int pStartPosition, int pCurrentPosition) {
        return pStartPosition + pCurrentPosition * SLOT_SIZE_PX;
    }
}
