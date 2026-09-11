/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.decoration.HangingEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.HangingEntity;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Hanging;

public class CraftHanging
extends CraftEntity
implements Hanging {
    public CraftHanging(CraftServer server, HangingEntity entity) {
        super(server, (Entity)entity);
    }

    @Override
    public BlockFace getAttachedFace() {
        return this.getFacing().getOppositeFace();
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        this.setFacingDirection(face, false);
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        HangingEntity hanging = this.getHandle();
        Direction dir = hanging.m_6350_();
        switch (face) {
            case SOUTH: {
                this.getHandle().m_6022_(Direction.SOUTH);
                break;
            }
            case WEST: {
                this.getHandle().m_6022_(Direction.WEST);
                break;
            }
            case NORTH: {
                this.getHandle().m_6022_(Direction.NORTH);
                break;
            }
            case EAST: {
                this.getHandle().m_6022_(Direction.EAST);
                break;
            }
            default: {
                throw new IllegalArgumentException(String.format("%s is not a valid facing direction", new Object[]{face}));
            }
        }
        if (!(force || this.getHandle().generation || hanging.m_7088_())) {
            hanging.m_6022_(dir);
            return false;
        }
        return true;
    }

    @Override
    public BlockFace getFacing() {
        Direction direction = this.getHandle().m_6350_();
        if (direction == null) {
            return BlockFace.SELF;
        }
        return CraftBlock.notchToBlockFace(direction);
    }

    public HangingEntity getHandle() {
        return (HangingEntity)this.entity;
    }

    @Override
    public String toString() {
        return "CraftHanging";
    }
}

