/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.EnderChestBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.World;
import org.bukkit.block.EnderChest;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftEnderChest
extends CraftBlockEntityState<EnderChestBlockEntity>
implements EnderChest {
    public CraftEnderChest(World world, EnderChestBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void open() {
        this.requirePlaced();
        if (!((EnderChestBlockEntity)this.getTileEntity()).f_155511_.opened && this.getWorldHandle() instanceof Level) {
            BlockState block = ((EnderChestBlockEntity)this.getTileEntity()).m_58900_();
            int openCount = ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.m_155450_();
            ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.onAPIOpen((Level)this.getWorldHandle(), this.getPosition(), block);
            ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.openerAPICountChanged((Level)this.getWorldHandle(), this.getPosition(), block, openCount, openCount + 1);
        }
        ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.opened = true;
    }

    @Override
    public void close() {
        this.requirePlaced();
        if (((EnderChestBlockEntity)this.getTileEntity()).f_155511_.opened && this.getWorldHandle() instanceof Level) {
            BlockState block = ((EnderChestBlockEntity)this.getTileEntity()).m_58900_();
            int openCount = ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.m_155450_();
            ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.onAPIClose((Level)this.getWorldHandle(), this.getPosition(), block);
            ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.openerAPICountChanged((Level)this.getWorldHandle(), this.getPosition(), block, openCount, 0);
        }
        ((EnderChestBlockEntity)this.getTileEntity()).f_155511_.opened = false;
    }
}

