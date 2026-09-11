/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 */
package io.izzel.arclight.common.bridge.core.world.storage;

import java.io.IOException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelStorageSource;

public interface LevelStorageSourceBridge {
    public LevelStorageSource.LevelStorageAccess bridge$getLevelSave(String var1, ResourceKey<LevelStem> var2) throws IOException;

    public static interface LevelStorageAccessBridge {
        public void bridge$setDimType(ResourceKey<LevelStem> var1);

        public ResourceKey<LevelStem> bridge$getTypeKey();
    }
}

