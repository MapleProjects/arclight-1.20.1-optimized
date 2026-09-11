/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.ChestBlock
 *  net.minecraft.world.level.block.entity.ChestBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftLootable;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryDoubleChest;
import org.bukkit.inventory.Inventory;

public class CraftChest
extends CraftLootable<ChestBlockEntity>
implements Chest {
    public CraftChest(World world, ChestBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory((Container)this.getSnapshot());
    }

    @Override
    public Inventory getBlockInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventory((Container)this.getTileEntity());
    }

    @Override
    public Inventory getInventory() {
        CraftInventory inventory = (CraftInventory)this.getBlockInventory();
        if (!this.isPlaced() || this.isWorldGeneration()) {
            return inventory;
        }
        CraftWorld world = (CraftWorld)this.getWorld();
        ChestBlock blockChest = (ChestBlock)(this.getType() == Material.CHEST ? Blocks.f_50087_ : Blocks.f_50325_);
        MenuProvider nms = blockChest.getMenuProvider(this.data, (Level)world.getHandle(), this.getPosition(), true);
        if (nms instanceof ChestBlock.2.1) {
            inventory = new CraftInventoryDoubleChest((ChestBlock.2.1)nms);
        }
        return inventory;
    }

    @Override
    public void open() {
        this.requirePlaced();
        if (!((ChestBlockEntity)this.getTileEntity()).f_155324_.opened && this.getWorldHandle() instanceof Level) {
            BlockState block = ((ChestBlockEntity)this.getTileEntity()).m_58900_();
            int openCount = ((ChestBlockEntity)this.getTileEntity()).f_155324_.m_155450_();
            ((ChestBlockEntity)this.getTileEntity()).f_155324_.onAPIOpen((Level)this.getWorldHandle(), this.getPosition(), block);
            ((ChestBlockEntity)this.getTileEntity()).f_155324_.openerAPICountChanged((Level)this.getWorldHandle(), this.getPosition(), block, openCount, openCount + 1);
        }
        ((ChestBlockEntity)this.getTileEntity()).f_155324_.opened = true;
    }

    @Override
    public void close() {
        this.requirePlaced();
        if (((ChestBlockEntity)this.getTileEntity()).f_155324_.opened && this.getWorldHandle() instanceof Level) {
            BlockState block = ((ChestBlockEntity)this.getTileEntity()).m_58900_();
            int openCount = ((ChestBlockEntity)this.getTileEntity()).f_155324_.m_155450_();
            ((ChestBlockEntity)this.getTileEntity()).f_155324_.onAPIClose((Level)this.getWorldHandle(), this.getPosition(), block);
            ((ChestBlockEntity)this.getTileEntity()).f_155324_.openerAPICountChanged((Level)this.getWorldHandle(), this.getPosition(), block, openCount, 0);
        }
        ((ChestBlockEntity)this.getTileEntity()).f_155324_.opened = false;
    }
}

