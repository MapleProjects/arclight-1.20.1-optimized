/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.server;

import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.util.Iterator;
import org.bukkit.UndefinedNullability;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;

public class ServerListPingEvent
extends ServerEvent
implements Iterable<Player> {
    private static final int MAGIC_PLAYER_COUNT = Integer.MIN_VALUE;
    private static final HandlerList handlers = new HandlerList();
    private final String hostname;
    private final InetAddress address;
    private String motd;
    private final int numPlayers;
    private int maxPlayers;

    public ServerListPingEvent(@NotNull String hostname, @NotNull InetAddress address, @NotNull String motd, int numPlayers, int maxPlayers) {
        super(true);
        Preconditions.checkArgument((numPlayers >= 0 ? 1 : 0) != 0, (String)"Cannot have negative number of players online", (int)numPlayers);
        this.hostname = hostname;
        this.address = address;
        this.motd = motd;
        this.numPlayers = numPlayers;
        this.maxPlayers = maxPlayers;
    }

    protected ServerListPingEvent(@NotNull String hostname, @NotNull InetAddress address, @NotNull String motd, int maxPlayers) {
        super(true);
        this.numPlayers = Integer.MIN_VALUE;
        this.hostname = hostname;
        this.address = address;
        this.motd = motd;
        this.maxPlayers = maxPlayers;
    }

    @NotNull
    public String getHostname() {
        return this.hostname;
    }

    @NotNull
    public InetAddress getAddress() {
        return this.address;
    }

    @NotNull
    public String getMotd() {
        return this.motd;
    }

    public void setMotd(@NotNull String motd) {
        this.motd = motd;
    }

    public int getNumPlayers() {
        int numPlayers = this.numPlayers;
        if (numPlayers == Integer.MIN_VALUE) {
            numPlayers = 0;
            for (Player player : this) {
                ++numPlayers;
            }
        }
        return numPlayers;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    @Deprecated
    public boolean shouldSendChatPreviews() {
        return false;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setServerIcon(@UndefinedNullability(value="implementation dependent") @UndefinedNullability(value="implementation dependent") CachedServerIcon icon) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
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

    @Override
    @NotNull
    public Iterator<Player> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

