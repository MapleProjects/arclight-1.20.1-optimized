/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  org.jetbrains.annotations.Nullable
 */
package io.izzel.arclight.common.bridge.core.block;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftPortalEvent;
import org.jetbrains.annotations.Nullable;

public interface PortalInfoBridge {
    public void bridge$setPortalEventInfo(CraftPortalEvent var1);

    public CraftPortalEvent bridge$getPortalEventInfo();

    public void bridge$setWorld(ServerLevel var1);

    @Nullable
    public ServerLevel bridge$getWorld();
}

