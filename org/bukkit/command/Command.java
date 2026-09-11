/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.command;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.permissions.Permissible;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.CustomTimingsHandler;

public abstract class Command {
    private String name;
    private String nextLabel;
    private String label;
    private List<String> aliases;
    private List<String> activeAliases;
    private CommandMap commandMap;
    protected String description;
    protected String usageMessage;
    private String permission;
    private String permissionMessage;
    public CustomTimingsHandler timings;

    protected Command(@NotNull String name) {
        this(name, "", "/" + name, new ArrayList<String>());
    }

    protected Command(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        this.name = name;
        this.nextLabel = name;
        this.label = name;
        this.description = description == null ? "" : description;
        this.usageMessage = usageMessage == null ? "/" + name : usageMessage;
        this.aliases = aliases;
        this.activeAliases = new ArrayList<String>(aliases);
        this.timings = new CustomTimingsHandler("** Command: " + name);
    }

    public abstract boolean execute(@NotNull CommandSender var1, @NotNull String var2, @NotNull String[] var3);

    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return this.tabComplete0(sender, alias, args, null);
    }

    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        return this.tabComplete(sender, alias, args);
    }

    @NotNull
    private List<String> tabComplete0(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"Sender cannot be null");
        Preconditions.checkArgument((args != null ? 1 : 0) != 0, (Object)"Arguments cannot be null");
        Preconditions.checkArgument((alias != null ? 1 : 0) != 0, (Object)"Alias cannot be null");
        if (args.length == 0) {
            return ImmutableList.of();
        }
        String lastWord = args[args.length - 1];
        Player senderPlayer = sender instanceof Player ? (Player)sender : null;
        ArrayList<String> matchedPlayers = new ArrayList<String>();
        for (Player player : sender.getServer().getOnlinePlayers()) {
            String name = player.getName();
            if (senderPlayer != null && !senderPlayer.canSee(player) || !StringUtil.startsWithIgnoreCase(name, lastWord)) continue;
            matchedPlayers.add(name);
        }
        Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
        return matchedPlayers;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public boolean setName(@NotNull String name) {
        if (!this.isRegistered()) {
            this.name = name == null ? "" : name;
            return true;
        }
        return false;
    }

    @Nullable
    public String getPermission() {
        return this.permission;
    }

    public void setPermission(@Nullable String permission) {
        this.permission = permission;
    }

    public boolean testPermission(@NotNull CommandSender target) {
        if (this.testPermissionSilent(target)) {
            return true;
        }
        if (this.permissionMessage == null) {
            target.sendMessage((Object)((Object)ChatColor.RED) + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is a mistake.");
        } else if (this.permissionMessage.length() != 0) {
            String[] stringArray = this.permissionMessage.replace("<permission>", this.permission).split("\n");
            int n = stringArray.length;
            int n2 = 0;
            while (n2 < n) {
                String line = stringArray[n2];
                target.sendMessage(line);
                ++n2;
            }
        }
        return false;
    }

    public boolean testPermissionSilent(@NotNull CommandSender target) {
        if (this.permission == null || this.permission.length() == 0) {
            return true;
        }
        String[] stringArray = this.permission.split(";");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String p = stringArray[n2];
            if (target.hasPermission(p)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @NotNull
    public String getLabel() {
        return this.label;
    }

    public boolean setLabel(@NotNull String name) {
        if (name == null) {
            name = "";
        }
        this.nextLabel = name;
        if (!this.isRegistered()) {
            this.timings = new CustomTimingsHandler("** Command: " + name);
            this.label = name;
            return true;
        }
        return false;
    }

    public boolean register(@NotNull CommandMap commandMap) {
        if (this.allowChangesFrom(commandMap)) {
            this.commandMap = commandMap;
            return true;
        }
        return false;
    }

    public boolean unregister(@NotNull CommandMap commandMap) {
        if (this.allowChangesFrom(commandMap)) {
            this.commandMap = null;
            this.activeAliases = new ArrayList<String>(this.aliases);
            this.label = this.nextLabel;
            return true;
        }
        return false;
    }

    private boolean allowChangesFrom(@NotNull CommandMap commandMap) {
        return this.commandMap == null || this.commandMap == commandMap;
    }

    public boolean isRegistered() {
        return this.commandMap != null;
    }

    @NotNull
    public List<String> getAliases() {
        return this.activeAliases;
    }

    @Nullable
    public String getPermissionMessage() {
        return this.permissionMessage;
    }

    @NotNull
    public String getDescription() {
        return this.description;
    }

    @NotNull
    public String getUsage() {
        return this.usageMessage;
    }

    @NotNull
    public Command setAliases(@NotNull List<String> aliases) {
        this.aliases = aliases;
        if (!this.isRegistered()) {
            this.activeAliases = new ArrayList<String>(aliases);
        }
        return this;
    }

    @NotNull
    public Command setDescription(@NotNull String description) {
        this.description = description == null ? "" : description;
        return this;
    }

    @NotNull
    public Command setPermissionMessage(@Nullable String permissionMessage) {
        this.permissionMessage = permissionMessage;
        return this;
    }

    @NotNull
    public Command setUsage(@NotNull String usage) {
        this.usageMessage = usage == null ? "" : usage;
        return this;
    }

    public static void broadcastCommandMessage(@NotNull CommandSender source, @NotNull String message) {
        Command.broadcastCommandMessage(source, message, true);
    }

    public static void broadcastCommandMessage(@NotNull CommandSender source, @NotNull String message, boolean sendToSource) {
        CommandMinecart commandMinecart;
        String result = String.valueOf(source.getName()) + ": " + message;
        if (source instanceof BlockCommandSender) {
            BlockCommandSender blockCommandSender = (BlockCommandSender)source;
            if (!blockCommandSender.getBlock().getWorld().getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT).booleanValue()) {
                Bukkit.getConsoleSender().sendMessage(result);
                return;
            }
        } else if (source instanceof CommandMinecart && !(commandMinecart = (CommandMinecart)source).getWorld().getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT).booleanValue()) {
            Bukkit.getConsoleSender().sendMessage(result);
            return;
        }
        Set<Permissible> users = Bukkit.getPluginManager().getPermissionSubscriptions("bukkit.broadcast.admin");
        String colored = (Object)((Object)ChatColor.GRAY) + (Object)((Object)ChatColor.ITALIC) + "[" + result + (Object)((Object)ChatColor.GRAY) + (Object)((Object)ChatColor.ITALIC) + "]";
        if (sendToSource && !(source instanceof ConsoleCommandSender)) {
            source.sendMessage(message);
        }
        for (Permissible user : users) {
            if (!(user instanceof CommandSender) || !user.hasPermission("bukkit.broadcast.admin")) continue;
            CommandSender target = (CommandSender)user;
            if (target instanceof ConsoleCommandSender) {
                target.sendMessage(result);
                continue;
            }
            if (target == source) continue;
            target.sendMessage(colored);
        }
    }

    public String toString() {
        return String.valueOf(this.getClass().getName()) + '(' + this.name + ')';
    }
}

