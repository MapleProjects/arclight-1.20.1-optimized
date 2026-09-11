/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.net.InetAddress;
import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.jetbrains.annotations.NotNull;

public class AsyncPlayerPreLoginEvent
extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Result result = Result.ALLOWED;
    private String message = "";
    private final String name;
    private final InetAddress ipAddress;
    private final UUID uniqueId;

    @Deprecated
    public AsyncPlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress) {
        this(name, ipAddress, null);
    }

    public AsyncPlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress, @NotNull UUID uniqueId) {
        super(true);
        this.name = name;
        this.ipAddress = ipAddress;
        this.uniqueId = uniqueId;
    }

    @NotNull
    public Result getLoginResult() {
        return this.result;
    }

    @Deprecated
    @NotNull
    public PlayerPreLoginEvent.Result getResult() {
        return this.result == null ? null : this.result.old();
    }

    public void setLoginResult(@NotNull Result result) {
        this.result = result;
    }

    @Deprecated
    public void setResult(@NotNull PlayerPreLoginEvent.Result result) {
        this.result = result == null ? null : Result.valueOf(result.name());
    }

    @NotNull
    public String getKickMessage() {
        return this.message;
    }

    public void setKickMessage(@NotNull String message) {
        this.message = message;
    }

    public void allow() {
        this.result = Result.ALLOWED;
        this.message = "";
    }

    public void disallow(@NotNull Result result, @NotNull String message) {
        this.result = result;
        this.message = message;
    }

    @Deprecated
    public void disallow(@NotNull PlayerPreLoginEvent.Result result, @NotNull String message) {
        this.result = result == null ? null : Result.valueOf(result.name());
        this.message = message;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public InetAddress getAddress() {
        return this.ipAddress;
    }

    @NotNull
    public UUID getUniqueId() {
        return this.uniqueId;
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


        @Deprecated
        @NotNull
        private PlayerPreLoginEvent.Result old() {
            return PlayerPreLoginEvent.Result.valueOf(this.name());
        }
    }
}

