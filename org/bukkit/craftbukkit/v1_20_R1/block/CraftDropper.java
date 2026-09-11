/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.DropperBlock
 *  net.minecraft.world.level.block.entity.DropperBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.Container;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropperBlock;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dropper;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftLootable;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftDropper
extends CraftLootable<DropperBlockEntity>
implements Dropper {
    public CraftDropper(World world, DropperBlockEntity tileEntity) {
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
    public void drop() {
        this.ensureNoWorldGeneration();
        Block block = this.getBlock();
        if (block.getType() == Material.DROPPER) {
            CraftWorld world = (CraftWorld)this.getWorld();
            DropperBlock drop = (DropperBlock)Blocks.f_50286_;
            drop.m_5824_(world.getHandle(), this.getPosition());
        }
    }
}

