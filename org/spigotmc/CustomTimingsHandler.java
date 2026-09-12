/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.spigotmc;

import java.io.PrintStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.defaults.TimingsCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomTimingsHandler {
    public static volatile boolean timingsEnabled = false;
    private static Queue<CustomTimingsHandler> HANDLERS = new ConcurrentLinkedQueue<CustomTimingsHandler>();
    private final String name;
    private final CustomTimingsHandler parent;
    private long count = 0L;
    private long start = 0L;
    private long timingDepth = 0L;
    private long totalTime = 0L;
    private long curTickTotal = 0L;
    private long violations = 0L;

    public CustomTimingsHandler(@NotNull String string) {
        this(string, null);
    }

    public CustomTimingsHandler(@NotNull String string, @Nullable CustomTimingsHandler customTimingsHandler) {
        this.name = string;
        this.parent = customTimingsHandler;
        HANDLERS.add(this);
    }

    public static void printTimings(@NotNull PrintStream printStream) {
        printStream.println("Minecraft");
        for (CustomTimingsHandler customTimingsHandler : HANDLERS) {
            long l = customTimingsHandler.totalTime;
            long l2 = customTimingsHandler.count;
            if (l2 == 0L) continue;
            long l3 = l / l2;
            printStream.println("    " + customTimingsHandler.name + " Time: " + l + " Count: " + l2 + " Avg: " + l3 + " Violations: " + customTimingsHandler.violations);
        }
        printStream.println("# Version " + Bukkit.getVersion());
        int n = 0;
        int n2 = 0;
        for (World world : Bukkit.getWorlds()) {
            n += world.getEntities().size();
            n2 += world.getLivingEntities().size();
        }
        printStream.println("# Entities " + n);
        printStream.println("# LivingEntities " + n2);
    }

    public static void reload() {
        if (timingsEnabled) {
            for (CustomTimingsHandler customTimingsHandler : HANDLERS) {
                customTimingsHandler.reset();
            }
        }
        TimingsCommand.timingStart = System.nanoTime();
    }

    public static void tick() {
        if (timingsEnabled) {
            for (CustomTimingsHandler customTimingsHandler : HANDLERS) {
                if (customTimingsHandler.curTickTotal > 50000000L) {
                    customTimingsHandler.violations = (long)((double)customTimingsHandler.violations + Math.ceil(customTimingsHandler.curTickTotal / 50000000L));
                }
                customTimingsHandler.curTickTotal = 0L;
                customTimingsHandler.timingDepth = 0L;
            }
        }
    }

    public void startTiming() {
        if (timingsEnabled && ++this.timingDepth == 1L) {
            this.start = System.nanoTime();
            if (this.parent != null && ++this.parent.timingDepth == 1L) {
                this.parent.start = this.start;
            }
        }
    }

    public void stopTiming() {
        if (timingsEnabled) {
            if (--this.timingDepth != 0L || this.start == 0L) {
                return;
            }
            long l = System.nanoTime() - this.start;
            this.totalTime += l;
            this.curTickTotal += l;
            ++this.count;
            this.start = 0L;
            if (this.parent != null) {
                this.parent.stopTiming();
            }
        }
    }

    public void reset() {
        this.count = 0L;
        this.violations = 0L;
        this.curTickTotal = 0L;
        this.totalTime = 0L;
        this.start = 0L;
        this.timingDepth = 0L;
    }
}

