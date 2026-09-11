/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.CompoundContainer
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.ChestBlock;
import org.bukkit.Location;
import org.bukkit.block.DoubleChest;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryDoubleChest
extends CraftInventory
implements DoubleChestInventory {
    public MenuProvider tile;
    private final CraftInventory left;
    private final CraftInventory right;

    public CraftInventoryDoubleChest(ChestBlock.2.1 block) {
        super((Container)block.inventorylargechest);
        this.tile = block;
        this.left = new CraftInventory(block.inventorylargechest.f_18910_);
        this.right = new CraftInventory(block.inventorylargechest.f_18911_);
    }

    public CraftInventoryDoubleChest(CompoundContainer largeChest) {
        super((Container)largeChest);
        this.left = largeChest.f_18910_ instanceof CompoundContainer ? new CraftInventoryDoubleChest((CompoundContainer)largeChest.f_18910_) : new CraftInventory(largeChest.f_18910_);
        this.right = largeChest.f_18911_ instanceof CompoundContainer ? new CraftInventoryDoubleChest((CompoundContainer)largeChest.f_18911_) : new CraftInventory(largeChest.f_18911_);
    }

    @Override
    public Inventory getLeftSide() {
        return this.left;
    }

    @Override
    public Inventory getRightSide() {
        return this.right;
    }

    @Override
    public void setContents(ItemStack[] items) {
        Preconditions.checkArgument((items.length <= this.getInventory().m_6643_() ? 1 : 0) != 0, (String)"Invalid inventory size (%s); expected %s or less", (int)items.length, (int)this.getInventory().m_6643_());
        ItemStack[] leftItems = new ItemStack[this.left.getSize()];
        ItemStack[] rightItems = new ItemStack[this.right.getSize()];
        System.arraycopy(items, 0, leftItems, 0, Math.min(this.left.getSize(), items.length));
        this.left.setContents(leftItems);
        if (items.length >= this.left.getSize()) {
            System.arraycopy(items, this.left.getSize(), rightItems, 0, Math.min(this.right.getSize(), items.length - this.left.getSize()));
            this.right.setContents(rightItems);
        }
    }

    @Override
    public DoubleChest getHolder() {
        return new DoubleChest(this);
    }

    @Override
    public Location getLocation() {
        return this.getLeftSide().getLocation().add(this.getRightSide().getLocation()).multiply(0.5);
    }
}

