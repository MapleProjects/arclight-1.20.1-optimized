/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.network.ServerPlayerConnection
 *  net.minecraft.world.entity.Entity
 */
package io.izzel.arclight.common.bridge.core.world;

import java.util.Set;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;

public interface ServerEntityBridge {
    public void bridge$setTrackedPlayers(Set<ServerPlayerConnection> var1);

    public Entity bridge$getTrackingEntity();

    public boolean bridge$syncPosition();

    public boolean bridge$instantSyncPosition();

    public boolean bridge$instantSyncMotion();
}

