/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.storage.ServerLevelData
 */
package io.izzel.arclight.common.bridge.core.world.storage;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.ServerLevelData;

public interface DerivedWorldInfoBridge {
    public ServerLevelData bridge$getDelegate();

    public void bridge$setDimType(ResourceKey<LevelStem> var1);
}

