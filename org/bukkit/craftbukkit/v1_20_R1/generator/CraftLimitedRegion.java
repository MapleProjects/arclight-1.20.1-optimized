/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.ProtoChunk
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import com.google.common.base.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.ProtoChunk;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegionAccessor;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;

public class CraftLimitedRegion
extends CraftRegionAccessor
implements LimitedRegion {
    private final WeakReference<WorldGenLevel> weakAccess;
    private final int centerChunkX;
    private final int centerChunkZ;
    private final int buffer = 16;
    private final BoundingBox region;
    boolean entitiesLoaded = false;
    private final List<net.minecraft.world.entity.Entity> entities = new ArrayList<net.minecraft.world.entity.Entity>();
    private final List<net.minecraft.world.entity.Entity> outsideEntities = new ArrayList<net.minecraft.world.entity.Entity>();

    public CraftLimitedRegion(WorldGenLevel access, ChunkPos center) {
        this.weakAccess = new WeakReference<WorldGenLevel>(access);
        this.centerChunkX = center.f_45578_;
        this.centerChunkZ = center.f_45579_;
        CraftWorld world = access.getMinecraftWorld().getWorld();
        int xCenter = this.centerChunkX << 4;
        int zCenter = this.centerChunkZ << 4;
        int xMin = xCenter - this.getBuffer();
        int zMin = zCenter - this.getBuffer();
        int xMax = xCenter + this.getBuffer() + 16;
        int zMax = zCenter + this.getBuffer() + 16;
        this.region = new BoundingBox(xMin, world.getMinHeight(), zMin, xMax, world.getMaxHeight(), zMax);
    }

    @Override
    public WorldGenLevel getHandle() {
        WorldGenLevel handle = (WorldGenLevel)this.weakAccess.get();
        Preconditions.checkState((handle != null ? 1 : 0) != 0, (Object)"GeneratorAccessSeed no longer present, are you using it in a different tick?");
        return handle;
    }

    public void loadEntities() {
        if (this.entitiesLoaded) {
            return;
        }
        WorldGenLevel access = this.getHandle();
        int x = -1;
        while (x <= 1) {
            int z = -1;
            while (z <= 1) {
                ProtoChunk chunk = (ProtoChunk)access.m_6325_(this.centerChunkX + x, this.centerChunkZ + z);
                for (CompoundTag compound : chunk.m_63293_()) {
                    EntityType.m_20645_((CompoundTag)compound, (Level)access.getMinecraftWorld(), entity -> {
                        if (this.region.contains(entity.m_20185_(), entity.m_20186_(), entity.m_20189_())) {
                            entity.generation = true;
                            this.entities.add((net.minecraft.world.entity.Entity)entity);
                        } else {
                            this.outsideEntities.add((net.minecraft.world.entity.Entity)entity);
                        }
                        return entity;
                    });
                }
                ++z;
            }
            ++x;
        }
        this.entitiesLoaded = true;
    }

    public void saveEntities() {
        WorldGenLevel access = this.getHandle();
        if (this.entitiesLoaded) {
            int x = -1;
            while (x <= 1) {
                int z = -1;
                while (z <= 1) {
                    ProtoChunk chunk = (ProtoChunk)access.m_6325_(this.centerChunkX + x, this.centerChunkZ + z);
                    chunk.m_63293_().clear();
                    ++z;
                }
                ++x;
            }
        }
        for (net.minecraft.world.entity.Entity entity : this.entities) {
            if (!entity.m_6084_()) continue;
            Preconditions.checkState((boolean)this.region.contains(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), (String)"Entity %s is not in the region", (Object)entity);
            access.m_7967_(entity);
        }
        for (net.minecraft.world.entity.Entity entity : this.outsideEntities) {
            access.m_7967_(entity);
        }
    }

    public void breakLink() {
        this.weakAccess.clear();
    }

    @Override
    public int getBuffer() {
        return 16;
    }

    @Override
    public boolean isInRegion(Location location) {
        return this.region.contains(location.getX(), location.getY(), location.getZ());
    }

    @Override
    public boolean isInRegion(int x, int y, int z) {
        return this.region.contains(x, y, z);
    }

    @Override
    public List<BlockState> getTileEntities() {
        ArrayList<BlockState> blockStates = new ArrayList<BlockState>();
        int x = -1;
        while (x <= 1) {
            int z = -1;
            while (z <= 1) {
                ProtoChunk chunk = (ProtoChunk)this.getHandle().m_6325_(this.centerChunkX + x, this.centerChunkZ + z);
                for (BlockPos position : chunk.m_5928_()) {
                    blockStates.add(this.getBlockState(position.m_123341_(), position.m_123342_(), position.m_123343_()));
                }
                ++z;
            }
            ++x;
        }
        return blockStates;
    }

    @Override
    public Biome getBiome(int x, int y, int z) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, y, z), (String)"Coordinates %s, %s, %s are not in the region", (Object)x, (Object)y, (Object)z);
        return super.getBiome(x, y, z);
    }

    @Override
    public void setBiome(int x, int y, int z, Holder<net.minecraft.world.level.biome.Biome> biomeBase) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, y, z), (String)"Coordinates %s, %s, %s are not in the region", (Object)x, (Object)y, (Object)z);
        ChunkAccess chunk = this.getHandle().m_46819_(x >> 4, z >> 4, ChunkStatus.f_62314_);
        chunk.setBiome(x >> 2, y >> 2, z >> 2, biomeBase);
    }

    @Override
    public BlockState getBlockState(int x, int y, int z) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, y, z), (String)"Coordinates %s, %s, %s are not in the region", (Object)x, (Object)y, (Object)z);
        return super.getBlockState(x, y, z);
    }

    @Override
    public BlockData getBlockData(int x, int y, int z) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, y, z), (String)"Coordinates %s, %s, %s are not in the region", (Object)x, (Object)y, (Object)z);
        return super.getBlockData(x, y, z);
    }

    @Override
    public Material getType(int x, int y, int z) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, y, z), (String)"Coordinates %s, %s, %s are not in the region", (Object)x, (Object)y, (Object)z);
        return super.getType(x, y, z);
    }

    @Override
    public void setBlockData(int x, int y, int z, BlockData blockData) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, y, z), (String)"Coordinates %s, %s, %s are not in the region", (Object)x, (Object)y, (Object)z);
        super.setBlockData(x, y, z, blockData);
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, this.region.getCenter().getBlockY(), z), (String)"Coordinates %s, %s are not in the region", (int)x, (int)z);
        return super.getHighestBlockYAt(x, z);
    }

    @Override
    public int getHighestBlockYAt(Location location) {
        Preconditions.checkArgument((boolean)this.isInRegion(location), (String)"Coordinates %s, %s, %s are not in the region", (Object)location.getBlockX(), (Object)location.getBlockY(), (Object)location.getBlockZ());
        return super.getHighestBlockYAt(location);
    }

    @Override
    public int getHighestBlockYAt(int x, int z, HeightMap heightMap) {
        Preconditions.checkArgument((boolean)this.isInRegion(x, this.region.getCenter().getBlockY(), z), (String)"Coordinates %s, %s are not in the region", (int)x, (int)z);
        return super.getHighestBlockYAt(x, z, heightMap);
    }

    @Override
    public int getHighestBlockYAt(Location location, HeightMap heightMap) {
        Preconditions.checkArgument((boolean)this.isInRegion(location), (String)"Coordinates %s, %s, %s are not in the region", (Object)location.getBlockX(), (Object)location.getBlockY(), (Object)location.getBlockZ());
        return super.getHighestBlockYAt(location, heightMap);
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType) {
        Preconditions.checkArgument((boolean)this.isInRegion(location), (String)"Coordinates %s, %s, %s are not in the region", (Object)location.getBlockX(), (Object)location.getBlockY(), (Object)location.getBlockZ());
        return super.generateTree(location, random, treeType);
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType, Consumer<BlockState> consumer) {
        Preconditions.checkArgument((boolean)this.isInRegion(location), (String)"Coordinates %s, %s, %s are not in the region", (Object)location.getBlockX(), (Object)location.getBlockY(), (Object)location.getBlockZ());
        return super.generateTree(location, random, treeType, consumer);
    }

    public Collection<net.minecraft.world.entity.Entity> getNMSEntities() {
        this.loadEntities();
        return new ArrayList<net.minecraft.world.entity.Entity>(this.entities);
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function, CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
        Preconditions.checkArgument((boolean)this.isInRegion(location), (String)"Coordinates %s, %s, %s are not in the region", (Object)location.getBlockX(), (Object)location.getBlockY(), (Object)location.getBlockZ());
        return super.spawn(location, clazz, function, reason);
    }

    @Override
    public void addEntityToWorld(net.minecraft.world.entity.Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        this.entities.add(entity);
    }
}

