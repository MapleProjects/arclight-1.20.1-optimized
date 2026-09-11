/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.server.level.ServerPlayer
 */
package io.izzel.arclight.common.bridge.core.network.datasync;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerPlayer;

public interface SynchedEntityDataBridge {
    public <T> void bridge$markDirty(EntityDataAccessor<T> var1);

    public void bridge$refresh(ServerPlayer var1);
}

