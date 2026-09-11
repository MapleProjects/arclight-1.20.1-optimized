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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.FormattedCommandAlias;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.command.defaults.HelpCommand;
import org.bukkit.command.defaults.PluginsCommand;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.command.defaults.TimingsCommand;
import org.bukkit.command.defaults.VersionCommand;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleCommandMap
implements CommandMap {
    protected final Map<String, Command> knownCommands = new HashMap<String, Command>();
    private final Server server;

    public SimpleCommandMap(@NotNull Server server) {
        this.server = server;
        this.setDefaultCommands();
    }

    private void setDefaultCommands() {
        this.register("bukkit", new VersionCommand("version"));
        this.register("bukkit", new ReloadCommand("reload"));
        this.register("bukkit", new PluginsCommand("plugins"));
        this.register("bukkit", new TimingsCommand("timings"));
    }

    public void setFallbackCommands() {
        this.register("bukkit", new HelpCommand());
    }

    @Override
    public void registerAll(@NotNull String fallbackPrefix, @NotNull List<Command> commands) {
        if (commands != null) {
            for (Command c : commands) {
                this.register(fallbackPrefix, c);
            }
        }
    }

    @Override
    public boolean register(@NotNull String fallbackPrefix, @NotNull Command command) {
        return this.register(command.getName(), fallbackPrefix, command);
    }

    @Override
    public boolean register(@NotNull String label, @NotNull String fallbackPrefix, @NotNull Command command) {
        label = label.toLowerCase(Locale.ENGLISH).trim();
        fallbackPrefix = fallbackPrefix.toLowerCase(Locale.ENGLISH).trim();
        boolean registered = this.register(label, command, false, fallbackPrefix);
        Iterator<String> iterator = command.getAliases().iterator();
        while (iterator.hasNext()) {
            if (this.register(iterator.next(), command, true, fallbackPrefix)) continue;
            iterator.remove();
        }
        if (!registered) {
            command.setLabel(String.valueOf(fallbackPrefix) + ":" + label);
        }
        command.register(this);
        return registered;
    }

    private synchronized boolean register(@NotNull String label, @NotNull Command command, boolean isAlias, @NotNull String fallbackPrefix) {
        this.knownCommands.put(String.valueOf(fallbackPrefix) + ":" + label, command);
        if ((command instanceof BukkitCommand || isAlias) && this.knownCommands.containsKey(label)) {
            return false;
        }
        boolean registered = true;
        Command conflict = this.knownCommands.get(label);
        if (conflict != null && conflict.getLabel().equals(label)) {
            return false;
        }
        if (!isAlias) {
            command.setLabel(label);
        }
        this.knownCommands.put(label, command);
        return registered;
    }

    @Override
    public boolean dispatch(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
        String[] args = commandLine.split(" ");
        if (args.length == 0) {
            return false;
        }
        String sentCommandLabel = args[0].toLowerCase(Locale.ENGLISH);
        Command target = this.getCommand(sentCommandLabel);
        if (target == null) {
            return false;
        }
        try {
            target.timings.startTiming();
            target.execute(sender, sentCommandLabel, Arrays.copyOfRange(args, 1, args.length));
            target.timings.stopTiming();
        }
        catch (CommandException ex) {
            target.timings.stopTiming();
            throw ex;
        }
        catch (Throwable ex) {
            target.timings.stopTiming();
            throw new CommandException("Unhandled exception executing '" + commandLine + "' in " + target, ex);
        }
        return true;
    }

    @Override
    public synchronized void clearCommands() {
        for (Map.Entry<String, Command> entry : this.knownCommands.entrySet()) {
            entry.getValue().unregister(this);
        }
        this.knownCommands.clear();
        this.setDefaultCommands();
    }

    @Override
    @Nullable
    public Command getCommand(@NotNull String name) {
        Command target = this.knownCommands.get(name.toLowerCase(Locale.ENGLISH));
        return target;
    }

    @Override
    @Nullable
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine) {
        return this.tabComplete(sender, cmdLine, null);
    }

    @Override
    @Nullable
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine, @Nullable Location location) {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"Sender cannot be null");
        Preconditions.checkArgument((cmdLine != null ? 1 : 0) != 0, (Object)"Command line cannot null");
        int spaceIndex = cmdLine.indexOf(32);
        if (spaceIndex == -1) {
            ArrayList<String> completions = new ArrayList<String>();
            Map<String, Command> knownCommands = this.knownCommands;
            String prefix = sender instanceof Player ? "/" : "";
            for (Map.Entry<String, Command> commandEntry : knownCommands.entrySet()) {
                String name;
                Command command = commandEntry.getValue();
                if (!command.testPermissionSilent(sender) || !StringUtil.startsWithIgnoreCase(name = commandEntry.getKey(), cmdLine)) continue;
                completions.add(String.valueOf(prefix) + name);
            }
            Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
            return completions;
        }
        String commandName = cmdLine.substring(0, spaceIndex);
        Command target = this.getCommand(commandName);
        if (target == null) {
            return null;
        }
        if (!target.testPermissionSilent(sender)) {
            return null;
        }
        String[] args = cmdLine.substring(spaceIndex + 1, cmdLine.length()).split(" ", -1);
        try {
            return target.tabComplete(sender, commandName, args, location);
        }
        catch (CommandException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing tab-completer for '" + cmdLine + "' in " + target, ex);
        }
    }

    @NotNull
    public Collection<Command> getCommands() {
        return Collections.unmodifiableCollection(this.knownCommands.values());
    }

    public void registerServerAliases() {
        Map<String, String[]> values = this.server.getCommandAliases();
        for (Map.Entry<String, String[]> entry : values.entrySet()) {
            String alias = entry.getKey();
            if (alias.contains(" ")) {
                this.server.getLogger().warning("Could not register alias " + alias + " because it contains illegal characters");
                continue;
            }
            String[] commandStrings = entry.getValue();
            ArrayList<String> targets = new ArrayList<String>();
            StringBuilder bad = new StringBuilder();
            String[] stringArray = commandStrings;
            int n = commandStrings.length;
            int n2 = 0;
            while (n2 < n) {
                String commandString = stringArray[n2];
                String[] commandArgs = commandString.split(" ");
                Command command = this.getCommand(commandArgs[0]);
                if (command == null) {
                    if (bad.length() > 0) {
                        bad.append(", ");
                    }
                    bad.append(commandString);
                } else {
                    targets.add(commandString);
                }
                ++n2;
            }
            if (bad.length() > 0) {
                this.server.getLogger().warning("Could not register alias " + alias + " because it contains commands that do not exist: " + bad);
                continue;
            }
            if (targets.size() > 0) {
                this.knownCommands.put(alias.toLowerCase(Locale.ENGLISH), new FormattedCommandAlias(alias.toLowerCase(Locale.ENGLISH), targets.toArray(new String[targets.size()])));
                continue;
            }
            this.knownCommands.remove(alias.toLowerCase(Locale.ENGLISH));
        }
    }
}

