/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.level.block.entity.BrewingStandBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.bukkit.World;
import org.bukkit.block.BrewingStand;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryBrewer;
import org.bukkit.inventory.BrewerInventory;

public class CraftBrewingStand
extends CraftContainer<BrewingStandBlockEntity>
implements BrewingStand {
    public CraftBrewingStand(World world, BrewingStandBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public BrewerInventory getSnapshotInventory() {
        return new CraftInventoryBrewer((Container)this.getSnapshot());
    }

    @Override
    public BrewerInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventoryBrewer((Container)this.getTileEntity());
    }

    @Override
    public int getBrewingTime() {
        return ((BrewingStandBlockEntity)this.getSnapshot()).f_58976_;
    }

    @Override
    public void setBrewingTime(int brewTime) {
        ((BrewingStandBlockEntity)this.getSnapshot()).f_58976_ = brewTime;
    }

    @Override
    public int getFuelLevel() {
        return ((BrewingStandBlockEntity)this.getSnapshot()).f_58979_;
    }

    @Override
    public void setFuelLevel(int level) {
        ((BrewingStandBlockEntity)this.getSnapshot()).f_58979_ = level;
    }
}

