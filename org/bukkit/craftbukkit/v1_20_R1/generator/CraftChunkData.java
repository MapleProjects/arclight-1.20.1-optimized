/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.EntityBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkAccess
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import com.google.common.base.Preconditions;
import java.lang.ref.WeakReference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

public final class CraftChunkData
implements ChunkGenerator.ChunkData {
    private final int maxHeight;
    private final int minHeight;
    private final WeakReference<ChunkAccess> weakChunk;

    public CraftChunkData(World world, ChunkAccess chunkAccess) {
        this(world.getMaxHeight(), world.getMinHeight(), chunkAccess);
    }

    CraftChunkData(int maxHeight, int minHeight, ChunkAccess chunkAccess) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.weakChunk = new WeakReference<ChunkAccess>(chunkAccess);
    }

    public ChunkAccess getHandle() {
        ChunkAccess access = (ChunkAccess)this.weakChunk.get();
        Preconditions.checkState((access != null ? 1 : 0) != 0, (Object)"IChunkAccess no longer present, are you using it in a different tick?");
        return access;
    }

    public void breakLink() {
        this.weakChunk.clear();
    }

    @Override
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override
    public int getMinHeight() {
        return this.minHeight;
    }

    @Override
    public Biome getBiome(int x, int y, int z) {
        return CraftBlock.biomeBaseToBiome((Registry<net.minecraft.world.level.biome.Biome>)this.getHandle().biomeRegistry, (Holder<net.minecraft.world.level.biome.Biome>)this.getHandle().m_203495_(x >> 2, y >> 2, z >> 2));
    }

    @Override
    public void setBlock(int x, int y, int z, Material material) {
        this.setBlock(x, y, z, material.createBlockData());
    }

    @Override
    public void setBlock(int x, int y, int z, MaterialData material) {
        this.setBlock(x, y, z, CraftMagicNumbers.getBlock(material));
    }

    @Override
    public void setBlock(int x, int y, int z, BlockData blockData) {
        this.setBlock(x, y, z, ((CraftBlockData)blockData).getState());
    }

    @Override
    public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, Material material) {
        this.setRegion(xMin, yMin, zMin, xMax, yMax, zMax, material.createBlockData());
    }

    @Override
    public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, MaterialData material) {
        this.setRegion(xMin, yMin, zMin, xMax, yMax, zMax, CraftMagicNumbers.getBlock(material));
    }

    @Override
    public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockData blockData) {
        this.setRegion(xMin, yMin, zMin, xMax, yMax, zMax, ((CraftBlockData)blockData).getState());
    }

    @Override
    public Material getType(int x, int y, int z) {
        return CraftMagicNumbers.getMaterial(this.getTypeId(x, y, z).m_60734_());
    }

    @Override
    public MaterialData getTypeAndData(int x, int y, int z) {
        return CraftMagicNumbers.getMaterial(this.getTypeId(x, y, z));
    }

    @Override
    public BlockData getBlockData(int x, int y, int z) {
        return CraftBlockData.fromData(this.getTypeId(x, y, z));
    }

    public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockState type) {
        if (xMin > 15 || yMin >= this.maxHeight || zMin > 15) {
            return;
        }
        if (xMin < 0) {
            xMin = 0;
        }
        if (yMin < this.minHeight) {
            yMin = this.minHeight;
        }
        if (zMin < 0) {
            zMin = 0;
        }
        if (xMax > 16) {
            xMax = 16;
        }
        if (yMax > this.maxHeight) {
            yMax = this.maxHeight;
        }
        if (zMax > 16) {
            zMax = 16;
        }
        if (xMin >= xMax || yMin >= yMax || zMin >= zMax) {
            return;
        }
        int y = yMin;
        while (y < yMax) {
            int x = xMin;
            while (x < xMax) {
                int z = zMin;
                while (z < zMax) {
                    this.setBlock(x, y, z, type);
                    ++z;
                }
                ++x;
            }
            ++y;
        }
    }

    public BlockState getTypeId(int x, int y, int z) {
        if (x != (x & 0xF) || y < this.minHeight || y >= this.maxHeight || z != (z & 0xF)) {
            return Blocks.f_50016_.m_49966_();
        }
        ChunkAccess access = this.getHandle();
        return access.m_8055_(new BlockPos(access.m_7697_().m_45604_() + x, y, access.m_7697_().m_45605_() + z));
    }

    @Override
    public byte getData(int x, int y, int z) {
        return CraftMagicNumbers.toLegacyData(this.getTypeId(x, y, z));
    }

    private void setBlock(int x, int y, int z, BlockState type) {
        if (x != (x & 0xF) || y < this.minHeight || y >= this.maxHeight || z != (z & 0xF)) {
            return;
        }
        ChunkAccess access = this.getHandle();
        BlockPos blockPosition = new BlockPos(access.m_7697_().m_45604_() + x, y, access.m_7697_().m_45605_() + z);
        BlockState oldBlockData = access.m_6978_(blockPosition, type, false);
        if (type.m_155947_()) {
            BlockEntity tileEntity = ((EntityBlock)type.m_60734_()).m_142194_(blockPosition, type);
            if (tileEntity == null) {
                access.m_8114_(blockPosition);
            } else {
                access.m_142169_(tileEntity);
            }
        } else if (oldBlockData != null && oldBlockData.m_155947_()) {
            access.m_8114_(blockPosition);
        }
    }
}

