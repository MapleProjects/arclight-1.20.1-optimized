/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.Container
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BarrelBlock
 *  net.minecraft.world.level.block.entity.BarrelBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.World;
import org.bukkit.block.Barrel;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftLootable;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftBarrel
extends CraftLootable<BarrelBlockEntity>
implements Barrel {
    public CraftBarrel(World world, BarrelBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory((Container)this.getSnapshot());
    }

    @Override
    public Inventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventory((Container)this.getTileEntity());
    }

    @Override
    public void open() {
        BlockState blockData;
        boolean open;
        this.requirePlaced();
        if (!((BarrelBlockEntity)this.getTileEntity()).f_155050_.opened && !(open = ((Boolean)(blockData = ((BarrelBlockEntity)this.getTileEntity()).m_58900_()).m_61143_((Property)BarrelBlock.f_49043_)).booleanValue())) {
            ((BarrelBlockEntity)this.getTileEntity()).m_58606_(blockData, true);
            if (this.getWorldHandle() instanceof Level) {
                ((BarrelBlockEntity)this.getTileEntity()).m_58600_(blockData, SoundEvents.f_11725_);
            }
        }
        ((BarrelBlockEntity)this.getTileEntity()).f_155050_.opened = true;
    }

    @Override
    public void close() {
        this.requirePlaced();
        if (((BarrelBlockEntity)this.getTileEntity()).f_155050_.opened) {
            BlockState blockData = ((BarrelBlockEntity)this.getTileEntity()).m_58900_();
            ((BarrelBlockEntity)this.getTileEntity()).m_58606_(blockData, false);
            if (this.getWorldHandle() instanceof Level) {
                ((BarrelBlockEntity)this.getTileEntity()).m_58600_(blockData, SoundEvents.f_11724_);
            }
        }
        ((BarrelBlockEntity)this.getTileEntity()).f_155050_.opened = false;
    }
}

