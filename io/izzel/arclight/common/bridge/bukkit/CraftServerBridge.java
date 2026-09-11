/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.players.PlayerList
 */
package io.izzel.arclight.common.bridge.bukkit;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;

public interface CraftServerBridge {
    public void bridge$setPlayerList(PlayerList var1);

    public void bridge$removeWorld(ServerLevel var1);
}

