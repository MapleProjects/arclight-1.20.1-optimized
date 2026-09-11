/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  joptsimple.OptionParser
 */
package io.izzel.arclight.common.mod.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import joptsimple.OptionParser;

public class BukkitOptionParser
extends OptionParser {
    public BukkitOptionParser() {
        this.acceptsAll(Arrays.asList("c", "config"), "Properties file to use").withRequiredArg().ofType(File.class).defaultsTo((Object)new File("server.properties"), (Object[])new File[0]).describedAs("Properties file");
        this.acceptsAll(Arrays.asList("P", "plugins"), "Plugin directory to use").withRequiredArg().ofType(File.class).defaultsTo((Object)new File("plugins"), (Object[])new File[0]).describedAs("Plugin directory");
        this.acceptsAll(Arrays.asList("h", "host", "server-ip"), "Host to listen on").withRequiredArg().ofType(String.class).describedAs("Hostname or IP");
        this.acceptsAll(Arrays.asList("W", "world-dir", "universe", "world-container"), "World container").withRequiredArg().ofType(File.class).describedAs("Directory containing worlds");
        this.acceptsAll(Arrays.asList("w", "world", "level-name"), "World name").withRequiredArg().ofType(String.class).describedAs("World name");
        this.acceptsAll(Arrays.asList("p", "port", "server-port"), "Port to listen on").withRequiredArg().ofType(Integer.class).describedAs("Port");
        this.acceptsAll(Arrays.asList("o", "online-mode"), "Whether to use online authentication").withRequiredArg().ofType(Boolean.class).describedAs("Authentication");
        this.acceptsAll(Arrays.asList("s", "size", "max-players"), "Maximum amount of players").withRequiredArg().ofType(Integer.class).describedAs("Server size");
        this.acceptsAll(Arrays.asList("d", "date-format"), "Format of the date to display in the console (for log entries)").withRequiredArg().ofType(SimpleDateFormat.class).describedAs("Log date format");
        this.acceptsAll(Arrays.asList("log-pattern"), "Specfies the log filename pattern").withRequiredArg().ofType(String.class).defaultsTo((Object)"server.log", (Object[])new String[0]).describedAs("Log filename");
        this.acceptsAll(Arrays.asList("log-limit"), "Limits the maximum size of the log file (0 = unlimited)").withRequiredArg().ofType(Integer.class).defaultsTo((Object)0, (Object[])new Integer[0]).describedAs("Max log size");
        this.acceptsAll(Arrays.asList("log-count"), "Specified how many log files to cycle through").withRequiredArg().ofType(Integer.class).defaultsTo((Object)1, (Object[])new Integer[0]).describedAs("Log count");
        this.acceptsAll(Arrays.asList("log-append"), "Whether to append to the log file").withRequiredArg().ofType(Boolean.class).defaultsTo((Object)true, (Object[])new Boolean[0]).describedAs("Log append");
        this.acceptsAll(Arrays.asList("log-strip-color"), "Strips color codes from log file");
        this.acceptsAll(Arrays.asList("b", "bukkit-settings"), "File for bukkit settings").withRequiredArg().ofType(File.class).defaultsTo((Object)new File("bukkit.yml"), (Object[])new File[0]).describedAs("Yml file");
        this.acceptsAll(Arrays.asList("C", "commands-settings"), "File for command settings").withRequiredArg().ofType(File.class).defaultsTo((Object)new File("commands.yml"), (Object[])new File[0]).describedAs("Yml file");
        this.acceptsAll(Arrays.asList("forceUpgrade"), "Whether to force a world upgrade");
        this.acceptsAll(Arrays.asList("eraseCache"), "Whether to force cache erase during world upgrade");
        this.acceptsAll(Arrays.asList("nojline"), "Disables jline and emulates the vanilla console");
        this.acceptsAll(Arrays.asList("noconsole"), "Disables the console");
        this.acceptsAll(Arrays.asList("demo"), "Demo mode");
        this.acceptsAll(Arrays.asList("S", "spigot-settings"), "File for spigot settings").withRequiredArg().ofType(File.class).defaultsTo((Object)new File("spigot.yml"), (Object[])new File[0]).describedAs("Yml file");
        this.allowsUnrecognizedOptions();
    }
}

