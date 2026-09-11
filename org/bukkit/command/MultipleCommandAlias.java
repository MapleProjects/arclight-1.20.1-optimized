/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MultipleCommandAlias
extends Command {
    private Command[] commands;

    public MultipleCommandAlias(@NotNull String name, @NotNull Command[] commands) {
        super(name);
        this.commands = commands;
    }

    @NotNull
    public Command[] getCommands() {
        return this.commands;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        boolean result = false;
        Command[] commandArray = this.commands;
        int n = this.commands.length;
        int n2 = 0;
        while (n2 < n) {
            Command command = commandArray[n2];
            result |= command.execute(sender, commandLabel, args);
            ++n2;
        }
        return result;
    }
}

