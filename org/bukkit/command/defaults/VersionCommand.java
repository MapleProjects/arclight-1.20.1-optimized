/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.io.Resources
 *  com.google.gson.Gson
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command.defaults;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

public class VersionCommand
extends BukkitCommand {
    private final ReentrantLock versionLock = new ReentrantLock();
    private boolean hasVersion = false;
    private String versionMessage = null;
    private final Set<CommandSender> versionWaiters = new HashSet<CommandSender>();
    private boolean versionTaskStarted = false;
    private long lastCheck = 0L;

    public VersionCommand(@NotNull String name) {
        super(name);
        this.description = "Gets the version of this server including any plugins in use";
        this.usageMessage = "/version [plugin name]";
        this.setPermission("bukkit.command.version");
        this.setAliases(Arrays.asList("ver", "about"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("This server is running " + Bukkit.getName() + " version " + Bukkit.getVersion() + " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
            this.sendVersion(sender);
        } else {
            StringBuilder name = new StringBuilder();
            String[] stringArray = args;
            int n = args.length;
            int n2 = 0;
            while (n2 < n) {
                String arg = stringArray[n2];
                if (name.length() > 0) {
                    name.append(' ');
                }
                name.append(arg);
                ++n2;
            }
            String pluginName = name.toString();
            Plugin exactPlugin = Bukkit.getPluginManager().getPlugin(pluginName);
            if (exactPlugin != null) {
                this.describeToSender(exactPlugin, sender);
                return true;
            }
            boolean found = false;
            pluginName = pluginName.toLowerCase(Locale.ENGLISH);
            Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
            int n3 = pluginArray.length;
            int n4 = 0;
            while (n4 < n3) {
                Plugin plugin = pluginArray[n4];
                if (plugin.getName().toLowerCase(Locale.ENGLISH).contains(pluginName)) {
                    this.describeToSender(plugin, sender);
                    found = true;
                }
                ++n4;
            }
            if (!found) {
                sender.sendMessage("This server is not running any plugin by that name.");
                sender.sendMessage("Use /plugins to get a list of plugins.");
            }
        }
        return true;
    }

    private void describeToSender(@NotNull Plugin plugin, @NotNull CommandSender sender) {
        PluginDescriptionFile desc = plugin.getDescription();
        sender.sendMessage((Object)((Object)ChatColor.GREEN) + desc.getName() + (Object)((Object)ChatColor.WHITE) + " version " + (Object)((Object)ChatColor.GREEN) + desc.getVersion());
        if (desc.getDescription() != null) {
            sender.sendMessage(desc.getDescription());
        }
        if (desc.getWebsite() != null) {
            sender.sendMessage("Website: " + (Object)((Object)ChatColor.GREEN) + desc.getWebsite());
        }
        if (!desc.getAuthors().isEmpty()) {
            if (desc.getAuthors().size() == 1) {
                sender.sendMessage("Author: " + this.getNameList(desc.getAuthors()));
            } else {
                sender.sendMessage("Authors: " + this.getNameList(desc.getAuthors()));
            }
        }
        if (!desc.getContributors().isEmpty()) {
            sender.sendMessage("Contributors: " + this.getNameList(desc.getContributors()));
        }
    }

    @NotNull
    private String getNameList(@NotNull List<String> names) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < names.size()) {
            if (result.length() > 0) {
                result.append((Object)ChatColor.WHITE);
                if (i < names.size() - 1) {
                    result.append(", ");
                } else {
                    result.append(" and ");
                }
            }
            result.append((Object)ChatColor.GREEN);
            result.append(names.get(i));
            ++i;
        }
        return result.toString();
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"Sender cannot be null");
        Preconditions.checkArgument((args != null ? 1 : 0) != 0, (Object)"Arguments cannot be null");
        Preconditions.checkArgument((alias != null ? 1 : 0) != 0, (Object)"Alias cannot be null");
        if (args.length == 1) {
            ArrayList<String> completions = new ArrayList<String>();
            String toComplete = args[0].toLowerCase(Locale.ENGLISH);
            Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
            int n = pluginArray.length;
            int n2 = 0;
            while (n2 < n) {
                Plugin plugin = pluginArray[n2];
                if (StringUtil.startsWithIgnoreCase(plugin.getName(), toComplete)) {
                    completions.add(plugin.getName());
                }
                ++n2;
            }
            return completions;
        }
        return ImmutableList.of();
    }

    private void sendVersion(@NotNull CommandSender sender) {
        if (this.hasVersion) {
            if (System.currentTimeMillis() - this.lastCheck > 21600000L) {
                this.lastCheck = System.currentTimeMillis();
                this.hasVersion = false;
            } else {
                sender.sendMessage(this.versionMessage);
                return;
            }
        }
        this.versionLock.lock();
        try {
            if (this.hasVersion) {
                sender.sendMessage(this.versionMessage);
                return;
            }
            this.versionWaiters.add(sender);
            sender.sendMessage("Checking version, please wait...");
            if (!this.versionTaskStarted) {
                this.versionTaskStarted = true;
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        VersionCommand.this.obtainVersion();
                    }
                }).start();
            }
        }
        finally {
            this.versionLock.unlock();
        }
    }

    private void obtainVersion() {
        String[] parts;
        String version = Bukkit.getVersion();
        if (version == null) {
            version = "Custom";
        }
        if ((parts = version.substring(0, version.indexOf(32)).split("-")).length == 4) {
            int cbVersions = VersionCommand.getDistance("craftbukkit", parts[3]);
            int spigotVersions = VersionCommand.getDistance("spigot", parts[2]);
            if (cbVersions == -1 || spigotVersions == -1) {
                this.setVersionMessage("Error obtaining version information");
            } else if (cbVersions == 0 && spigotVersions == 0) {
                this.setVersionMessage("You are running the latest version");
            } else {
                this.setVersionMessage("You are " + (cbVersions + spigotVersions) + " version(s) behind");
            }
        } else if (parts.length == 3) {
            int cbVersions = VersionCommand.getDistance("craftbukkit", parts[2]);
            if (cbVersions == -1) {
                this.setVersionMessage("Error obtaining version information");
            } else if (cbVersions == 0) {
                this.setVersionMessage("You are running the latest version");
            } else {
                this.setVersionMessage("You are " + cbVersions + " version(s) behind");
            }
        } else {
            this.setVersionMessage("Unknown version, custom build?");
        }
    }

    private void setVersionMessage(@NotNull String msg) {
        this.lastCheck = System.currentTimeMillis();
        this.versionMessage = msg;
        this.versionLock.lock();
        try {
            this.hasVersion = true;
            this.versionTaskStarted = false;
            for (CommandSender sender : this.versionWaiters) {
                sender.sendMessage(this.versionMessage);
            }
            this.versionWaiters.clear();
        }
        finally {
            this.versionLock.unlock();
        }
    }

    private static int getDistance(@NotNull String repo, @NotNull String hash) {
        int n;
        BufferedReader reader = Resources.asCharSource((URL)new URL("https://hub.spigotmc.org/stash/rest/api/1.0/projects/SPIGOT/repos/" + repo + "/commits?since=" + URLEncoder.encode(hash, "UTF-8") + "&withCounts=true"), (Charset)Charsets.UTF_8).openBufferedStream();
        try {
            JsonObject obj = (JsonObject)new Gson().fromJson((Reader)reader, JsonObject.class);
            n = obj.get("totalCount").getAsInt();
        }
        catch (JsonSyntaxException ex) {
            try {
                ex.printStackTrace();
            }
            catch (Throwable throwable) {
                try {
                    reader.close();
                    throw throwable;
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            reader.close();
            return -1;
        }
        reader.close();
        return n;
    }
}

