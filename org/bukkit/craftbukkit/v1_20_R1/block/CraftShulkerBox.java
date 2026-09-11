/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.ShulkerBoxBlock
 *  net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.ShulkerBox;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftLootable;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.Inventory;

public class CraftShulkerBox
extends CraftLootable<ShulkerBoxBlockEntity>
implements ShulkerBox {
    public CraftShulkerBox(World world, ShulkerBoxBlockEntity tileEntity) {
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
    public DyeColor getColor() {
        net.minecraft.world.item.DyeColor color = ((ShulkerBoxBlock)CraftMagicNumbers.getBlock((Material)this.getType())).f_56185_;
        return color == null ? null : DyeColor.getByWoolData((byte)color.m_41060_());
    }

    @Override
    public void open() {
        this.requirePlaced();
        if (!((ShulkerBoxBlockEntity)this.getTileEntity()).opened && this.getWorldHandle() instanceof Level) {
            Level world = ((ShulkerBoxBlockEntity)this.getTileEntity()).m_58904_();
            world.m_7696_(this.getPosition(), ((ShulkerBoxBlockEntity)this.getTileEntity()).m_58900_().m_60734_(), 1, 1);
            world.m_5594_(null, this.getPosition(), SoundEvents.f_12409_, SoundSource.BLOCKS, 0.5f, world.f_46441_.m_188501_() * 0.1f + 0.9f);
        }
        ((ShulkerBoxBlockEntity)this.getTileEntity()).opened = true;
    }

    @Override
    public void close() {
        this.requirePlaced();
        if (((ShulkerBoxBlockEntity)this.getTileEntity()).opened && this.getWorldHandle() instanceof Level) {
            Level world = ((ShulkerBoxBlockEntity)this.getTileEntity()).m_58904_();
            world.m_7696_(this.getPosition(), ((ShulkerBoxBlockEntity)this.getTileEntity()).m_58900_().m_60734_(), 1, 0);
            world.m_5594_(null, this.getPosition(), SoundEvents.f_12409_, SoundSource.BLOCKS, 0.5f, world.f_46441_.m_188501_() * 0.1f + 0.9f);
        }
        ((ShulkerBoxBlockEntity)this.getTileEntity()).opened = false;
    }
}

