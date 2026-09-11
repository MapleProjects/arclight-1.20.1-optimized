/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command;

import java.net.SocketAddress;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface RemoteConsoleCommandSender
extends CommandSender {
    @NotNull
    @ApiStatus.Experimental
    public SocketAddress getAddress();
}

