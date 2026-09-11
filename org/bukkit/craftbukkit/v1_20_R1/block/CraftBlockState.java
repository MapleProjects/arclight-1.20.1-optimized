/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CraftBlockState
implements BlockState {
    protected final CraftWorld world;
    private final BlockPos position;
    protected net.minecraft.world.level.block.state.BlockState data;
    protected int flag;
    private WeakReference<LevelAccessor> weakWorld;

    protected CraftBlockState(Block block) {
        this(block.getWorld(), ((CraftBlock)block).getPosition(), ((CraftBlock)block).getNMS());
        this.flag = 3;
        this.setWorldHandle(((CraftBlock)block).getHandle());
    }

    protected CraftBlockState(Block block, int flag) {
        this(block);
        this.flag = flag;
    }

    protected CraftBlockState(@Nullable World world, BlockPos blockPosition, net.minecraft.world.level.block.state.BlockState blockData) {
        this.world = (CraftWorld)world;
        this.position = blockPosition;
        this.data = blockData;
    }

    public void setWorldHandle(LevelAccessor generatorAccess) {
        this.weakWorld = generatorAccess instanceof Level ? null : new WeakReference<LevelAccessor>(generatorAccess);
    }

    public LevelAccessor getWorldHandle() {
        if (this.weakWorld == null) {
            return this.isPlaced() ? this.world.getHandle() : null;
        }
        LevelAccessor access = (LevelAccessor)this.weakWorld.get();
        if (access == null) {
            this.weakWorld = null;
            return this.isPlaced() ? this.world.getHandle() : null;
        }
        return access;
    }

    protected final boolean isWorldGeneration() {
        LevelAccessor generatorAccess = this.getWorldHandle();
        return generatorAccess != null && !(generatorAccess instanceof Level);
    }

    protected final void ensureNoWorldGeneration() {
        Preconditions.checkState((!this.isWorldGeneration() ? 1 : 0) != 0, (Object)"This operation is not supported during world generation!");
    }

    @Override
    public World getWorld() {
        this.requirePlaced();
        return this.world;
    }

    @Override
    public int getX() {
        return this.position.m_123341_();
    }

    @Override
    public int getY() {
        return this.position.m_123342_();
    }

    @Override
    public int getZ() {
        return this.position.m_123343_();
    }

    @Override
    public Chunk getChunk() {
        this.requirePlaced();
        return this.world.getChunkAt(this.getX() >> 4, this.getZ() >> 4);
    }

    public void setData(net.minecraft.world.level.block.state.BlockState data) {
        this.data = data;
    }

    public BlockPos getPosition() {
        return this.position;
    }

    public net.minecraft.world.level.block.state.BlockState getHandle() {
        return this.data;
    }

    @Override
    public BlockData getBlockData() {
        return CraftBlockData.fromData(this.data);
    }

    @Override
    public void setBlockData(BlockData data) {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"BlockData cannot be null");
        this.data = ((CraftBlockData)data).getState();
    }

    @Override
    public void setData(MaterialData data) {
        Material mat = CraftMagicNumbers.getMaterial(this.data).getItemType();
        if (mat == null || mat.getData() == null) {
            this.data = CraftMagicNumbers.getBlock(data);
        } else {
            Preconditions.checkArgument((data.getClass() == mat.getData() || data.getClass() == MaterialData.class ? 1 : 0) != 0, (String)"Provided data is not of type %s, found %s", (Object)mat.getData().getName(), (Object)data.getClass().getName());
            this.data = CraftMagicNumbers.getBlock(data);
        }
    }

    @Override
    public MaterialData getData() {
        return CraftMagicNumbers.getMaterial(this.data);
    }

    @Override
    public void setType(Material type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((boolean)type.isBlock(), (Object)"Material must be a block!");
        if (this.getType() != type) {
            this.data = CraftMagicNumbers.getBlock(type).m_49966_();
        }
    }

    @Override
    public Material getType() {
        return CraftMagicNumbers.getMaterial(this.data.m_60734_());
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }

    @Override
    public byte getLightLevel() {
        return this.getBlock().getLightLevel();
    }

    @Override
    public CraftBlock getBlock() {
        this.requirePlaced();
        return CraftBlock.at(this.getWorldHandle(), this.position);
    }

    @Override
    public boolean update() {
        return this.update(false);
    }

    @Override
    public boolean update(boolean force) {
        return this.update(force, true);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        if (!this.isPlaced()) {
            return true;
        }
        LevelAccessor access = this.getWorldHandle();
        CraftBlock block = this.getBlock();
        if (block.getType() != this.getType() && !force) {
            return false;
        }
        net.minecraft.world.level.block.state.BlockState newBlock = this.data;
        block.setTypeAndData(newBlock, applyPhysics);
        if (access instanceof Level) {
            this.world.getHandle().m_7260_(this.position, block.getNMS(), newBlock, 3);
        }
        return true;
    }

    @Override
    public byte getRawData() {
        return CraftMagicNumbers.toLegacyData(this.data);
    }

    @Override
    public Location getLocation() {
        return CraftLocation.toBukkit(this.position, (World)this.world);
    }

    @Override
    public Location getLocation(Location loc) {
        if (loc != null) {
            loc.setWorld(this.world);
            loc.setX(this.getX());
            loc.setY(this.getY());
            loc.setZ(this.getZ());
            loc.setYaw(0.0f);
            loc.setPitch(0.0f);
        }
        return loc;
    }

    @Override
    public void setRawData(byte data) {
        this.data = CraftMagicNumbers.getBlock(this.getType(), data);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CraftBlockState other = (CraftBlockState)obj;
        if (!(this.world == other.world || this.world != null && this.world.equals(other.world))) {
            return false;
        }
        if (!(this.position == other.position || this.position != null && this.position.equals((Object)other.position))) {
            return false;
        }
        return this.data == other.data || this.data != null && this.data.equals(other.data);
    }

    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.world != null ? this.world.hashCode() : 0);
        hash = 73 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 73 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.requirePlaced();
        this.world.getBlockMetadata().setMetadata(this.getBlock(), metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        this.requirePlaced();
        return this.world.getBlockMetadata().getMetadata(this.getBlock(), metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        this.requirePlaced();
        return this.world.getBlockMetadata().hasMetadata(this.getBlock(), metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.requirePlaced();
        this.world.getBlockMetadata().removeMetadata(this.getBlock(), metadataKey, owningPlugin);
    }

    @Override
    public boolean isPlaced() {
        return this.world != null;
    }

    protected void requirePlaced() {
        Preconditions.checkState((boolean)this.isPlaced(), (Object)"The blockState must be placed to call this method");
    }
}

