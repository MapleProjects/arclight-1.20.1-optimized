/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.ChiseledBookShelfBlock
 *  net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity
 *  net.minecraft.world.phys.Vec2
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.phys.Vec2;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.data.Directional;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryChiseledBookshelf;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.bukkit.util.Vector;

public class CraftChiseledBookshelf
extends CraftBlockEntityState<ChiseledBookShelfBlockEntity>
implements ChiseledBookshelf {
    public CraftChiseledBookshelf(World world, ChiseledBookShelfBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public int getLastInteractedSlot() {
        return ((ChiseledBookShelfBlockEntity)this.getSnapshot()).m_262444_();
    }

    @Override
    public void setLastInteractedSlot(int lastInteractedSlot) {
        ((ChiseledBookShelfBlockEntity)this.getSnapshot()).f_262317_ = lastInteractedSlot;
    }

    @Override
    public ChiseledBookshelfInventory getSnapshotInventory() {
        return new CraftInventoryChiseledBookshelf((ChiseledBookShelfBlockEntity)this.getSnapshot());
    }

    @Override
    public ChiseledBookshelfInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventoryChiseledBookshelf((ChiseledBookShelfBlockEntity)this.getTileEntity());
    }

    @Override
    public int getSlot(Vector clickVector) {
        Vec2 faceVector;
        BlockFace facing = ((Directional)this.getBlockData()).getFacing();
        switch (facing) {
            case NORTH: {
                faceVector = new Vec2((float)(1.0 - clickVector.getX()), (float)clickVector.getY());
                break;
            }
            case SOUTH: {
                faceVector = new Vec2((float)clickVector.getX(), (float)clickVector.getY());
                break;
            }
            case WEST: {
                faceVector = new Vec2((float)clickVector.getZ(), (float)clickVector.getY());
                break;
            }
            case EAST: {
                faceVector = new Vec2((float)(1.0 - clickVector.getZ()), (float)clickVector.getY());
                break;
            }
            default: {
                return -1;
            }
        }
        return ChiseledBookShelfBlock.m_261279_((Vec2)faceVector);
    }
}

