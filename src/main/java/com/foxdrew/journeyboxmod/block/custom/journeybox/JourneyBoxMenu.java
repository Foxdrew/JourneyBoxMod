package com.foxdrew.journeyboxmod.block.custom.journeybox;

import com.foxdrew.journeyboxmod.AbstractExtendedContainerMenu;
import com.foxdrew.journeyboxmod.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class JourneyBoxMenu extends AbstractExtendedContainerMenu {
    private final Container container;
    private JourneyBoxBlockEntity pBlockEntity;

    public JourneyBoxMenu(int pContainerId, Container pPlayerContainer) {
        this(pContainerId, pPlayerContainer, new SimpleContainer(1));
    }

    public JourneyBoxMenu(int pContainerId, Container pPlayerContainer, Container pEntityContainer) {
        super(ModMenus.JOURNEY_BOX.get(), pContainerId);
        checkContainerSize(pEntityContainer, 1);
        this.pBlockEntity = null;
        container = pEntityContainer;
        Slot slot = new FilterSlot(pEntityContainer, 0, 80, 20);
        addSlot(slot);
        drawDefaultPlayerInventory(pPlayerContainer, 8, 51);
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return container.stillValid(pPlayer);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        // todo: Shift+LMB action
        return super.quickMoveStack(pPlayer, pIndex);
    }

    private class FilterSlot extends Slot {
        public FilterSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return pStack.sameItem(pBlockEntity.getContainedItemStack());
        }
    }
}
