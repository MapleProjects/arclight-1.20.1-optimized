/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.dimension.LevelStem
 */
package io.izzel.arclight.common.bridge.core.world;

import io.izzel.arclight.common.bridge.core.world.IWorldBridge;
import io.izzel.arclight.common.bridge.core.world.IWorldWriterBridge;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.dimension.LevelStem;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.generator.ChunkGenerator;
import org.spigotmc.SpigotWorldConfig;

public interface WorldBridge
extends IWorldWriterBridge,
IWorldBridge {
    public CraftServer bridge$getServer();

    public CraftWorld bridge$getWorld();

    public boolean bridge$isPvpMode();

    public boolean bridge$isKeepSpawnInMemory();

    public boolean bridge$isPopulating();

    public void bridge$setPopulating(boolean var1);

    public ChunkGenerator bridge$getGenerator();

    public BlockEntity bridge$getTileEntity(BlockPos var1, boolean var2);

    public SpigotWorldConfig bridge$spigotConfig();

    public Object2LongOpenHashMap<SpawnCategory> bridge$ticksPerSpawnCategory();

    public ResourceKey<LevelStem> bridge$getTypeKey();

    public void bridge$setLastPhysicsProblem(BlockPos var1);
}

