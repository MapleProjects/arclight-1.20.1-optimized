/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.gson.Gson
 *  com.google.gson.JsonObject
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command.defaults;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.spigotmc.CustomTimingsHandler;

public class TimingsCommand
extends BukkitCommand {
    private static final List<String> TIMINGS_SUBCOMMANDS = ImmutableList.of((Object)"report", (Object)"reset", (Object)"on", (Object)"off", (Object)"paste");
    public static long timingStart = 0L;

    public TimingsCommand(@NotNull String name) {
        super(name);
        this.description = "Manages Spigot Timings data to see performance of the server.";
        this.usageMessage = "/timings <reset|report|on|off|paste>";
        this.setPermission("bukkit.command.timings");
    }

    public void executeSpigotTimings(@NotNull CommandSender sender, @NotNull String[] args) {
        if ("on".equals(args[0])) {
            ((SimplePluginManager)Bukkit.getPluginManager()).useTimings(true);
            CustomTimingsHandler.reload();
            sender.sendMessage("Enabled Timings & Reset");
            return;
        }
        if ("off".equals(args[0])) {
            ((SimplePluginManager)Bukkit.getPluginManager()).useTimings(false);
            sender.sendMessage("Disabled Timings");
            return;
        }
        if (!Bukkit.getPluginManager().useTimings()) {
            sender.sendMessage("Please enable timings by typing /timings on");
            return;
        }
        boolean paste = "paste".equals(args[0]);
        if ("reset".equals(args[0])) {
            CustomTimingsHandler.reload();
            sender.sendMessage("Timings reset");
        } else if ("merged".equals(args[0]) || "report".equals(args[0]) || paste) {
            long sampleTime = System.nanoTime() - timingStart;
            int index = 0;
            File timingFolder = new File("timings");
            timingFolder.mkdirs();
            File timings = new File(timingFolder, "timings.txt");
            ByteArrayOutputStream bout = paste ? new ByteArrayOutputStream() : null;
            while (timings.exists()) {
                timings = new File(timingFolder, "timings" + ++index + ".txt");
            }
            try (PrintStream fileTimings = null;){
                fileTimings = paste ? new PrintStream(bout) : new PrintStream(timings);
                CustomTimingsHandler.printTimings(fileTimings);
                fileTimings.println("Sample time " + sampleTime + " (" + (double)sampleTime / 1.0E9 + "s)");
                fileTimings.println("<spigotConfig>");
                fileTimings.println(Bukkit.spigot().getConfig().saveToString());
                fileTimings.println("</spigotConfig>");
                if (paste) {
                    new PasteThread(sender, bout).start();
                    return;
                }
                try {
                    sender.sendMessage("Timings written to " + timings.getPath());
                    sender.sendMessage("Paste contents of file into form at http://www.spigotmc.org/go/timings to read results.");
                }
                catch (IOException iOException) {}
            }
        }
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage((Object)((Object)ChatColor.RED) + "Usage: " + this.usageMessage);
            return false;
        }
        this.executeSpigotTimings(sender, args);
        return true;
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"Sender cannot be null");
        Preconditions.checkArgument((args != null ? 1 : 0) != 0, (Object)"Arguments cannot be null");
        Preconditions.checkArgument((alias != null ? 1 : 0) != 0, (Object)"Alias cannot be null");
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], TIMINGS_SUBCOMMANDS, new ArrayList(TIMINGS_SUBCOMMANDS.size()));
        }
        return ImmutableList.of();
    }

    private static class PasteThread
    extends Thread {
        private final CommandSender sender;
        private final ByteArrayOutputStream bout;

        public PasteThread(@NotNull CommandSender sender, @NotNull ByteArrayOutputStream bout) {
            super("Timings paste thread");
            this.sender = sender;
            this.bout = bout;
        }

        @Override
        public synchronized void start() {
            if (this.sender instanceof RemoteConsoleCommandSender) {
                this.run();
            } else {
                super.start();
            }
        }

        @Override
        public void run() {
            try {
                HttpURLConnection con = (HttpURLConnection)new URL("https://timings.spigotmc.org/paste").openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setInstanceFollowRedirects(false);
                OutputStream out = con.getOutputStream();
                out.write(this.bout.toByteArray());
                out.close();
                JsonObject location = (JsonObject)new Gson().fromJson((Reader)new InputStreamReader(con.getInputStream()), JsonObject.class);
                con.getInputStream().close();
                String pasteID = location.get("key").getAsString();
                this.sender.sendMessage((Object)((Object)ChatColor.GREEN) + "Timings results can be viewed at https://www.spigotmc.org/go/timings?url=" + pasteID);
            }
            catch (IOException ex) {
                this.sender.sendMessage((Object)((Object)ChatColor.RED) + "Error pasting timings, check your console for more information");
                Bukkit.getServer().getLogger().log(Level.WARNING, "Could not paste timings", ex);
            }
        }
    }
}

