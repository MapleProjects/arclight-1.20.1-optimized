/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.rcon.RconConsoleSource
 */
package org.bukkit.craftbukkit.v1_20_R1.command;

import java.net.SocketAddress;
import net.minecraft.network.chat.Component;
import net.minecraft.server.rcon.RconConsoleSource;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.ServerCommandSender;

public class CraftRemoteConsoleCommandSender
extends ServerCommandSender
implements RemoteConsoleCommandSender {
    private final RconConsoleSource listener;

    public CraftRemoteConsoleCommandSender(RconConsoleSource listener) {
        this.listener = listener;
    }

    public RconConsoleSource getListener() {
        return this.listener;
    }

    @Override
    public SocketAddress getAddress() {
        return this.listener.socketAddress;
    }

    @Override
    public void sendMessage(String message) {
        this.listener.m_213846_((Component)Component.m_237113_((String)(String.valueOf(message) + "\n")));
    }

    @Override
    public void sendMessage(String ... messages) {
        String[] stringArray = messages;
        int n = messages.length;
        int n2 = 0;
        while (n2 < n) {
            String message = stringArray[n2];
            this.sendMessage(message);
            ++n2;
        }
    }

    @Override
    public String getName() {
        return "Rcon";
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot change operator status of remote controller.");
    }
}

