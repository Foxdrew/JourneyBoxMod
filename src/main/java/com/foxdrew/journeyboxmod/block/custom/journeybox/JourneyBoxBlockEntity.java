package com.foxdrew.journeyboxmod.block.custom.journeybox;

import com.foxdrew.journeyboxmod.JourneyBlockMod;
import com.foxdrew.journeyboxmod.ModEntities;

import com.foxdrew.journeyboxmod.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

public class JourneyBoxBlockEntity extends BaseContainerBlockEntity {
    private ItemStack containedItemStack = ItemStack.EMPTY;
    private int itemCount = 0;
    private static final int SIZE = 1;
    private final NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    public JourneyBoxBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModEntities.JOURNEY_BOX.get(), pPos, pBlockState);
    }

    public ItemStack getContainedItemStack() {
        return containedItemStack;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return new TranslatableComponent("journey.box.block.entity");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory) {
        return new JourneyBoxMenu(pContainerId, pPlayerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        return items.get(pSlot);
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        ItemStack itemStack = ContainerHelper.removeItem(items, pSlot, pAmount);
        if (!itemStack.isEmpty()) {
            setChanged();
        }
        return itemStack;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(items, pSlot);
    }

    @Override
    public void setItem(int pSlot, @NotNull ItemStack pStack) {
        if (pSlot == 0) {
            if (isItemStackEmpty()) {
                containedItemStack = pStack.copy();
                containedItemStack.setCount(1);
                itemCount += pStack.getCount();
                pStack.setCount(0);
            } else {
                if (containedItemStack.sameItem(pStack)) {
                    itemCount += pStack.getCount();
                    pStack.setCount(0);
                }
            }
        }
        JourneyBlockMod.LOGGER.info("Block contain: " + containedItemStack.getItem() + " qty: " + itemCount);
        setChanged();
        /*items.set(pSlot, pStack);
        if (pStack.getCount() > getMaxStackSize()) {
            pStack.setCount(getMaxStackSize());
        }*/
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        if (level.getBlockEntity(worldPosition) != this) {
            return false;
        } else {
            return !(Utils.getDistanceBetweenPlayerAndBlockPosition(pPlayer, worldPosition) > 64.0D);
        }
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        ContainerHelper.loadAllItems(pTag, this.items);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag, this.items);
    }

    public boolean isItemStackEmpty() {
        return containedItemStack.isEmpty();
    }
}
