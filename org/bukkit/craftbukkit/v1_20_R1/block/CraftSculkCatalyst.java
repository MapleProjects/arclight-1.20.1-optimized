/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.block.entity.SculkCatalystBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.SculkCatalyst;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftSculkCatalyst
extends CraftBlockEntityState<SculkCatalystBlockEntity>
implements SculkCatalyst {
    public CraftSculkCatalyst(World world, SculkCatalystBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void bloom(Block block, int charge) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"block cannot be null");
        Preconditions.checkArgument((charge > 0 ? 1 : 0) != 0, (Object)"charge must be positive");
        this.requirePlaced();
        ((SculkCatalystBlockEntity)this.getTileEntity()).m_280052_().m_280309_(this.world.getHandle(), this.getPosition(), this.getHandle(), this.world.getHandle().m_213780_());
        ((SculkCatalystBlockEntity)this.getTileEntity()).m_280052_().m_280490_().m_222266_(new BlockPos(block.getX(), block.getY(), block.getZ()), charge);
    }
}

