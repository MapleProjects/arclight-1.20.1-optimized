/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.net.InetAddress;
import java.util.UUID;
import org.bukkit.Warning;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Warning(reason="This event causes a login thread to synchronize with the main thread")
public class PlayerPreLoginEvent
extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Result result = Result.ALLOWED;
    private String message = "";
    private final String name;
    private final InetAddress ipAddress;
    private final UUID uniqueId;

    @Deprecated
    public PlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress) {
        this(name, ipAddress, null);
    }

    public PlayerPreLoginEvent(@NotNull String name, @NotNull InetAddress ipAddress, @NotNull UUID uniqueId) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.uniqueId = uniqueId;
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

    public void allow() {
        this.result = Result.ALLOWED;
        this.message = "";
    }

    public void disallow(@NotNull Result result, @NotNull String message) {
        this.result = result;
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

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public UUID getUniqueId() {
        return this.uniqueId;
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

