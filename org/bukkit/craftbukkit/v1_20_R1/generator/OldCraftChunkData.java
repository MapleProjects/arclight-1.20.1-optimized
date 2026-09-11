/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Registry
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.LevelChunkSection
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

@Deprecated
public final class OldCraftChunkData
implements ChunkGenerator.ChunkData {
    private final int minHeight;
    private final int maxHeight;
    private final LevelChunkSection[] sections;
    private final Registry<net.minecraft.world.level.biome.Biome> biomes;
    private Set<BlockPos> tiles;
    private final Set<BlockPos> lights = new HashSet<BlockPos>();

    public OldCraftChunkData(int minHeight, int maxHeight, Registry<net.minecraft.world.level.biome.Biome> biomes) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.biomes = biomes;
        this.sections = new LevelChunkSection[(maxHeight - 1 >> 4) + 1 - (minHeight >> 4)];
    }

    @Override
    public int getMinHeight() {
        return this.minHeight;
    }

    @Override
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override
    public Biome getBiome(int x, int y, int z) {
        throw new UnsupportedOperationException("Unsupported, in older chunk generator api");
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
            LevelChunkSection section = this.getChunkSection(y, true);
            int offsetBase = y & 0xF;
            int x = xMin;
            while (x < xMax) {
                int z = zMin;
                while (z < zMax) {
                    section.m_62986_(x, offsetBase, z, type);
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
        LevelChunkSection section = this.getChunkSection(y, false);
        if (section == null) {
            return Blocks.f_50016_.m_49966_();
        }
        return section.m_62982_(x, y & 0xF, z);
    }

    @Override
    public byte getData(int x, int y, int z) {
        return CraftMagicNumbers.toLegacyData(this.getTypeId(x, y, z));
    }

    private void setBlock(int x, int y, int z, BlockState type) {
        if (x != (x & 0xF) || y < this.minHeight || y >= this.maxHeight || z != (z & 0xF)) {
            return;
        }
        LevelChunkSection section = this.getChunkSection(y, true);
        section.m_62986_(x, y & 0xF, z, type);
        if (type.m_60791_() > 0) {
            this.lights.add(new BlockPos(x, y, z));
        } else {
            this.lights.remove(new BlockPos(x, y, z));
        }
        if (type.m_155947_()) {
            if (this.tiles == null) {
                this.tiles = new HashSet<BlockPos>();
            }
            this.tiles.add(new BlockPos(x, y, z));
        }
    }

    private LevelChunkSection getChunkSection(int y, boolean create) {
        int offset = y - this.minHeight >> 4;
        LevelChunkSection section = this.sections[offset];
        if (create && section == null) {
            this.sections[offset] = section = new LevelChunkSection(this.biomes);
        }
        return section;
    }

    LevelChunkSection[] getRawChunkData() {
        return this.sections;
    }

    Set<BlockPos> getTiles() {
        return this.tiles;
    }

    Set<BlockPos> getLights() {
        return this.lights;
    }
}

