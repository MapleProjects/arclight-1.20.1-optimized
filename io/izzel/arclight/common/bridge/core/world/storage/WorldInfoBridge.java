/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelSettings
 */
package io.izzel.arclight.common.bridge.core.world.storage;

import com.mojang.serialization.Lifecycle;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelSettings;

public interface WorldInfoBridge {
    public void bridge$setWorld(ServerLevel var1);

    public ServerLevel bridge$getWorld();

    public LevelSettings bridge$getWorldSettings();

    public Lifecycle bridge$getLifecycle();
}

