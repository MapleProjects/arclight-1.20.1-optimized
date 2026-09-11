/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BellBlock
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BellBlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.bukkit.World;
import org.bukkit.block.Bell;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class CraftBell
extends CraftBlockEntityState<BellBlockEntity>
implements Bell {
    public CraftBell(World world, BellBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public boolean ring(Entity entity, BlockFace direction) {
        Preconditions.checkArgument((direction == null || direction.isCartesian() ? 1 : 0) != 0, (String)"direction must be cartesian, given %s", (Object)((Object)direction));
        BlockEntity tileEntity = this.getTileEntityFromWorld();
        if (tileEntity == null) {
            return false;
        }
        net.minecraft.world.entity.Entity nmsEntity = entity != null ? ((CraftEntity)entity).getHandle() : null;
        Direction enumDirection = CraftBlock.blockFaceToNotch(direction);
        return ((BellBlock)Blocks.f_50680_).m_152188_(nmsEntity, (Level)this.world.getHandle(), this.getPosition(), enumDirection);
    }

    @Override
    public boolean ring(Entity entity) {
        return this.ring(entity, null);
    }

    @Override
    public boolean ring(BlockFace direction) {
        return this.ring(null, direction);
    }

    @Override
    public boolean ring() {
        return this.ring(null, null);
    }

    @Override
    public boolean isShaking() {
        return ((BellBlockEntity)this.getSnapshot()).f_58814_;
    }

    @Override
    public int getShakingTicks() {
        return ((BellBlockEntity)this.getSnapshot()).f_58813_;
    }

    @Override
    public boolean isResonating() {
        return ((BellBlockEntity)this.getSnapshot()).f_58818_;
    }

    @Override
    public int getResonatingTicks() {
        return this.isResonating() ? ((BellBlockEntity)this.getSnapshot()).f_58813_ : 0;
    }
}

