/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.TileState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CraftBlockEntityState<T extends BlockEntity>
extends CraftBlockState
implements TileState {
    private final T tileEntity;
    private final T snapshot;

    public CraftBlockEntityState(World world, T tileEntity) {
        super(world, tileEntity.m_58899_(), tileEntity.m_58900_());
        this.tileEntity = tileEntity;
        this.snapshot = this.createSnapshot(tileEntity);
        this.load(this.snapshot);
    }

    public void refreshSnapshot() {
        this.load(this.tileEntity);
    }

    private T createSnapshot(T tileEntity) {
        if (tileEntity == null) {
            return null;
        }
        CompoundTag nbtTagCompound = tileEntity.m_187480_();
        BlockEntity snapshot = BlockEntity.m_155241_((BlockPos)this.getPosition(), (BlockState)this.getHandle(), (CompoundTag)nbtTagCompound);
        return (T)snapshot;
    }

    private void copyData(T from, T to) {
        CompoundTag nbtTagCompound = from.m_187480_();
        to.m_142466_(nbtTagCompound);
    }

    protected T getTileEntity() {
        return this.tileEntity;
    }

    protected T getSnapshot() {
        return this.snapshot;
    }

    protected BlockEntity getTileEntityFromWorld() {
        this.requirePlaced();
        return this.getWorldHandle().m_7702_(this.getPosition());
    }

    public CompoundTag getSnapshotNBT() {
        this.applyTo(this.snapshot);
        return this.snapshot.m_187480_();
    }

    protected void load(T tileEntity) {
        if (tileEntity != null && tileEntity != this.snapshot) {
            this.copyData(tileEntity, this.snapshot);
        }
    }

    protected void applyTo(T tileEntity) {
        if (tileEntity != null && tileEntity != this.snapshot) {
            this.copyData(this.snapshot, tileEntity);
        }
    }

    protected boolean isApplicable(BlockEntity tileEntity) {
        return tileEntity != null && this.tileEntity.getClass() == tileEntity.getClass();
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        BlockEntity tile;
        boolean result = super.update(force, applyPhysics);
        if (result && this.isPlaced() && this.isApplicable(tile = this.getTileEntityFromWorld())) {
            this.applyTo(tile);
            tile.m_6596_();
        }
        return result;
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return ((BlockEntity)this.getSnapshot()).persistentDataContainer;
    }

    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket(@NotNull Location location) {
        BlockEntity vanillaTileEntitiy = BlockEntity.m_155241_((BlockPos)CraftLocation.toBlockPosition(location), (BlockState)this.getHandle(), (CompoundTag)this.getSnapshotNBT());
        return ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)vanillaTileEntitiy);
    }
}

