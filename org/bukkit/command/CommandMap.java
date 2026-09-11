/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.command;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandMap {
    public void registerAll(@NotNull String var1, @NotNull List<Command> var2);

    public boolean register(@NotNull String var1, @NotNull String var2, @NotNull Command var3);

    public boolean register(@NotNull String var1, @NotNull Command var2);

    public boolean dispatch(@NotNull CommandSender var1, @NotNull String var2) throws CommandException;

    public void clearCommands();

    @Nullable
    public Command getCommand(@NotNull String var1);

    @Nullable
    public List<String> tabComplete(@NotNull CommandSender var1, @NotNull String var2) throws IllegalArgumentException;

    @Nullable
    public List<String> tabComplete(@NotNull CommandSender var1, @NotNull String var2, @Nullable Location var3) throws IllegalArgumentException;
}

