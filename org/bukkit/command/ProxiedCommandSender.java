/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface ProxiedCommandSender
extends CommandSender {
    @NotNull
    public CommandSender getCaller();

    @NotNull
    public CommandSender getCallee();
}

