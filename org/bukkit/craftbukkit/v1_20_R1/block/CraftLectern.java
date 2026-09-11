/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Lectern;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryLectern;
import org.bukkit.inventory.Inventory;

public class CraftLectern
extends CraftBlockEntityState<LecternBlockEntity>
implements Lectern {
    public CraftLectern(World world, LecternBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public int getPage() {
        return ((LecternBlockEntity)this.getSnapshot()).m_59568_();
    }

    @Override
    public void setPage(int page) {
        ((LecternBlockEntity)this.getSnapshot()).m_59532_(page);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventoryLectern(((LecternBlockEntity)this.getSnapshot()).f_59525_);
    }

    @Override
    public Inventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventoryLectern(((LecternBlockEntity)this.getTileEntity()).f_59525_);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);
        if (result && this.getType() == Material.LECTERN && this.getWorldHandle() instanceof Level) {
            LecternBlock.m_54488_((Level)this.world.getHandle(), (BlockPos)this.getPosition(), (BlockState)this.getHandle());
        }
        return result;
    }
}

