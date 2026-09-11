/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.net.InetAddress;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLoginEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final InetAddress address;
    private final String hostname;
    private Result result = Result.ALLOWED;
    private String message = "";
    private final InetAddress realAddress;

    public PlayerLoginEvent(@NotNull Player player, @NotNull String hostname, @NotNull InetAddress address, @NotNull InetAddress realAddress) {
        super(player);
        this.hostname = hostname;
        this.address = address;
        this.realAddress = realAddress;
    }

    public PlayerLoginEvent(@NotNull Player player, @NotNull String hostname, @NotNull InetAddress address) {
        this(player, hostname, address, address);
    }

    public PlayerLoginEvent(@NotNull Player player, @NotNull String hostname, @NotNull InetAddress address, @NotNull Result result, @NotNull String message, @NotNull InetAddress realAddress) {
        this(player, hostname, address, realAddress);
        this.result = result;
        this.message = message;
    }

    @NotNull
    public InetAddress getRealAddress() {
        return this.realAddress;
    }

    @NotNull
    public Result getResult() {
        return this.result;
    }

    public void setResult(@NotNull Result result) {
        this.result = result;
    }

    @NotNull
    public String getKickMessage() {
        return this.message;
    }

    public void setKickMessage(@NotNull String message) {
        this.message = message;
    }

    @NotNull
    public String getHostname() {
        return this.hostname;
    }

    public void allow() {
        this.result = Result.ALLOWED;
        this.message = "";
    }

    public void disallow(@NotNull Result result, @NotNull String message) {
        this.result = result;
        this.message = message;
    }

    @NotNull
    public InetAddress getAddress() {
        return this.address;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static enum Result {
        ALLOWED,
        KICK_FULL,
        KICK_BANNED,
        KICK_WHITELIST,
        KICK_OTHER;

    }
}

