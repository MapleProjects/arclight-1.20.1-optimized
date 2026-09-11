/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.block.TileState;
import org.jetbrains.annotations.Nullable;

public interface EndGateway
extends TileState {
    @Nullable
    public Location getExitLocation();

    public void setExitLocation(@Nullable Location var1);

    public boolean isExactTeleport();

    public void setExactTeleport(boolean var1);

    public long getAge();

    public void setAge(long var1);
}

