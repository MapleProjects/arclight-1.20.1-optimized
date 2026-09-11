/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_20_R1.util.LazyHashSet;
import org.bukkit.entity.Player;

public class LazyPlayerSet
extends LazyHashSet<Player> {
    private final MinecraftServer server;

    public LazyPlayerSet(MinecraftServer server) {
        this.server = server;
    }

    @Override
    HashSet<Player> makeReference() {
        Preconditions.checkState((this.reference == null ? 1 : 0) != 0, (Object)"Reference already created!");
        List players = this.server.m_6846_().f_11196_;
        HashSet<Player> reference = new HashSet<Player>(players.size());
        for (ServerPlayer player : players) {
            reference.add(player.getBukkitEntity());
        }
        return reference;
    }
}

