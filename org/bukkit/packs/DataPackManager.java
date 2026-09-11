/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.packs;

import java.util.Collection;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.packs.DataPack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Experimental
public interface DataPackManager {
    @NotNull
    public Collection<DataPack> getDataPacks();

    @Nullable
    public DataPack getDataPack(@NotNull NamespacedKey var1);

    @NotNull
    public Collection<DataPack> getEnabledDataPacks(@NotNull World var1);

    @NotNull
    public Collection<DataPack> getDisabledDataPacks(@NotNull World var1);

    public boolean isEnabledByFeature(@NotNull Material var1, @NotNull World var2);

    public boolean isEnabledByFeature(@NotNull EntityType var1, @NotNull World var2);
}

