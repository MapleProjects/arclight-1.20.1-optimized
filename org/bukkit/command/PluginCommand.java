/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.command;

import com.google.common.base.Preconditions;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PluginCommand
extends Command
implements PluginIdentifiableCommand {
    private final Plugin owningPlugin;
    private CommandExecutor executor;
    private TabCompleter completer;

    protected PluginCommand(@NotNull String name, @NotNull Plugin owner) {
        super(name);
        this.executor = owner;
        this.owningPlugin = owner;
        this.usageMessage = "";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        boolean success = false;
        if (!this.owningPlugin.isEnabled()) {
            throw new CommandException("Cannot execute command '" + commandLabel + "' in plugin " + this.owningPlugin.getDescription().getFullName() + " - plugin is disabled.");
        }
        if (!this.testPermission(sender)) {
            return true;
        }
        try {
            success = this.executor.onCommand(sender, this, commandLabel, args);
        }
        catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + this.owningPlugin.getDescription().getFullName(), ex);
        }
        if (!success && this.usageMessage.length() > 0) {
            String[] stringArray = this.usageMessage.replace("<command>", commandLabel).split("\n");
            int n = stringArray.length;
            int n2 = 0;
            while (n2 < n) {
                String line = stringArray[n2];
                sender.sendMessage(line);
                ++n2;
            }
        }
        return success;
    }

    public void setExecutor(@Nullable CommandExecutor executor) {
        this.executor = executor == null ? this.owningPlugin : executor;
    }

    @NotNull
    public CommandExecutor getExecutor() {
        return this.executor;
    }

    public void setTabCompleter(@Nullable TabCompleter completer) {
        this.completer = completer;
    }

    @Nullable
    public TabCompleter getTabCompleter() {
        return this.completer;
    }

    @Override
    @NotNull
    public Plugin getPlugin() {
        return this.owningPlugin;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws CommandException, IllegalArgumentException {
        block6: {
            Preconditions.checkArgument((boolean)(sender != null), (Object)"Sender cannot be null");
            Preconditions.checkArgument((boolean)(args != null), (Object)"Arguments cannot be null");
            Preconditions.checkArgument((boolean)(alias != null), (Object)"Alias cannot be null");
            completions = null;
            try {
                if (this.completer != null) {
                    completions = this.completer.onTabComplete(sender, this, alias, args);
                }
                if (completions == null && this.executor instanceof TabCompleter) {
                    completions = ((TabCompleter)this.executor).onTabComplete(sender, this, alias, args);
                }
                break block6;
            }
            catch (Throwable ex) {
                message = new StringBuilder();
                message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
                var10_7 = args;
                var9_8 = args.length;
                var8_9 = 0;
                ** while (var8_9 < var9_8)
            }
lbl-1000:
            // 1 sources

            {
                arg = var10_7[var8_9];
                message.append(arg).append(' ');
                ++var8_9;
                continue;
            }
lbl24:
            // 1 sources

            message.deleteCharAt(message.length() - 1).append("' in plugin ").append(this.owningPlugin.getDescription().getFullName());
            throw new CommandException(message.toString(), ex);
        }
        if (completions == null) {
            return super.tabComplete(sender, alias, args);
        }
        return completions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(", ").append(this.owningPlugin.getDescription().getFullName()).append(')');
        return stringBuilder.toString();
    }
}

