/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RegionAccessor {
    @NotNull
    public Biome getBiome(@NotNull Location var1);

    @NotNull
    public Biome getBiome(int var1, int var2, int var3);

    public void setBiome(@NotNull Location var1, @NotNull Biome var2);

    public void setBiome(int var1, int var2, int var3, @NotNull Biome var4);

    @NotNull
    public BlockState getBlockState(@NotNull Location var1);

    @NotNull
    public BlockState getBlockState(int var1, int var2, int var3);

    @NotNull
    public BlockData getBlockData(@NotNull Location var1);

    @NotNull
    public BlockData getBlockData(int var1, int var2, int var3);

    @NotNull
    public Material getType(@NotNull Location var1);

    @NotNull
    public Material getType(int var1, int var2, int var3);

    public void setBlockData(@NotNull Location var1, @NotNull BlockData var2);

    public void setBlockData(int var1, int var2, int var3, @NotNull BlockData var4);

    public void setType(@NotNull Location var1, @NotNull Material var2);

    public void setType(int var1, int var2, int var3, @NotNull Material var4);

    public boolean generateTree(@NotNull Location var1, @NotNull Random var2, @NotNull TreeType var3);

    public boolean generateTree(@NotNull Location var1, @NotNull Random var2, @NotNull TreeType var3, @Nullable Consumer<BlockState> var4);

    public boolean generateTree(@NotNull Location var1, @NotNull Random var2, @NotNull TreeType var3, @Nullable Predicate<BlockState> var4);

    @NotNull
    public Entity spawnEntity(@NotNull Location var1, @NotNull EntityType var2);

    @NotNull
    public Entity spawnEntity(@NotNull Location var1, @NotNull EntityType var2, boolean var3);

    @NotNull
    public List<Entity> getEntities();

    @NotNull
    public List<LivingEntity> getLivingEntities();

    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> var1);

    @NotNull
    public Collection<Entity> getEntitiesByClasses(Class<?> ... var1);

    @NotNull
    public <T extends Entity> T spawn(@NotNull Location var1, @NotNull Class<T> var2) throws IllegalArgumentException;

    @NotNull
    public <T extends Entity> T spawn(@NotNull Location var1, @NotNull Class<T> var2, @Nullable Consumer<T> var3) throws IllegalArgumentException;

    @NotNull
    public <T extends Entity> T spawn(@NotNull Location var1, @NotNull Class<T> var2, boolean var3, @Nullable Consumer<T> var4) throws IllegalArgumentException;

    public int getHighestBlockYAt(int var1, int var2);

    public int getHighestBlockYAt(@NotNull Location var1);

    public int getHighestBlockYAt(int var1, int var2, @NotNull HeightMap var3);

    public int getHighestBlockYAt(@NotNull Location var1, @NotNull HeightMap var2);
}

