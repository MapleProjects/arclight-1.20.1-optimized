/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.level.Level
 */
package org.spigotmc;

import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.spigotmc.RestartCommand;

public class WatchdogThread
extends Thread {
    private static WatchdogThread instance;
    private long timeoutTime;
    private boolean restart;
    private volatile long lastTick;
    private volatile boolean stopping;

    private WatchdogThread(long timeoutTime, boolean restart) {
        super("Spigot Watchdog Thread");
        this.timeoutTime = timeoutTime;
        this.restart = restart;
    }

    private static long monotonicMillis() {
        return System.nanoTime() / 1000000L;
    }

    public static void doStart(int timeoutTime, boolean restart) {
        if (instance == null) {
            instance = new WatchdogThread((long)timeoutTime * 1000L, restart);
            instance.start();
        } else {
            WatchdogThread.instance.timeoutTime = (long)timeoutTime * 1000L;
            WatchdogThread.instance.restart = restart;
        }
    }

    public static void tick() {
        WatchdogThread.instance.lastTick = WatchdogThread.monotonicMillis();
    }

    public static void doStop() {
        if (instance != null) {
            WatchdogThread.instance.stopping = true;
        }
    }

    @Override
    public void run() {
        while (!this.stopping) {
            if (this.lastTick != 0L && this.timeoutTime > 0L && WatchdogThread.monotonicMillis() > this.lastTick + this.timeoutTime) {
                ThreadInfo[] threads;
                Logger log = Bukkit.getServer().getLogger();
                log.log(Level.SEVERE, "------------------------------");
                log.log(Level.SEVERE, "The server has stopped responding! This is (probably) not a Spigot bug.");
                log.log(Level.SEVERE, "If you see a plugin in the Server thread dump below, then please report it to that author");
                log.log(Level.SEVERE, "\t *Especially* if it looks like HTTP or MySQL operations are occurring");
                log.log(Level.SEVERE, "If you see a world save or edit, then it means you did far more than your server can handle at once");
                log.log(Level.SEVERE, "\t If this is the case, consider increasing timeout-time in spigot.yml but note that this will replace the crash with LARGE lag spikes");
                log.log(Level.SEVERE, "If you are unsure or still think this is a Spigot bug, please report to https://www.spigotmc.org/");
                log.log(Level.SEVERE, "Be sure to include ALL relevant console errors and Minecraft crash reports");
                log.log(Level.SEVERE, "Spigot version: " + Bukkit.getServer().getVersion());
                if (net.minecraft.world.level.Level.lastPhysicsProblem != null) {
                    log.log(Level.SEVERE, "------------------------------");
                    log.log(Level.SEVERE, "During the run of the server, a physics stackoverflow was supressed");
                    log.log(Level.SEVERE, "near " + net.minecraft.world.level.Level.lastPhysicsProblem);
                }
                log.log(Level.SEVERE, "------------------------------");
                log.log(Level.SEVERE, "Server thread dump (Look for plugins here before reporting to Spigot!):");
                WatchdogThread.dumpThread(ManagementFactory.getThreadMXBean().getThreadInfo(MinecraftServer.getServer().f_129725_.getId(), Integer.MAX_VALUE), log);
                log.log(Level.SEVERE, "------------------------------");
                log.log(Level.SEVERE, "Entire Thread Dump:");
                ThreadInfo[] threadInfoArray = threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
                int n = threads.length;
                int n2 = 0;
                while (n2 < n) {
                    ThreadInfo thread = threadInfoArray[n2];
                    WatchdogThread.dumpThread(thread, log);
                    ++n2;
                }
                log.log(Level.SEVERE, "------------------------------");
                if (!this.restart || MinecraftServer.getServer().hasStopped()) break;
                RestartCommand.restart();
                break;
            }
            try {
                WatchdogThread.sleep(10000L);
            }
            catch (InterruptedException ex) {
                this.interrupt();
            }
        }
    }

    private static void dumpThread(ThreadInfo thread, Logger log) {
        int n;
        int n2;
        Object[] objectArray;
        log.log(Level.SEVERE, "------------------------------");
        log.log(Level.SEVERE, "Current Thread: " + thread.getThreadName());
        log.log(Level.SEVERE, "\tPID: " + thread.getThreadId() + " | Suspended: " + thread.isSuspended() + " | Native: " + thread.isInNative() + " | State: " + (Object)((Object)thread.getThreadState()));
        if (thread.getLockedMonitors().length != 0) {
            log.log(Level.SEVERE, "\tThread is waiting on monitor(s):");
            objectArray = thread.getLockedMonitors();
            n2 = objectArray.length;
            n = 0;
            while (n < n2) {
                Object monitor = objectArray[n];
                log.log(Level.SEVERE, "\t\tLocked on:" + ((MonitorInfo)monitor).getLockedStackFrame());
                ++n;
            }
        }
        log.log(Level.SEVERE, "\tStack:");
        objectArray = thread.getStackTrace();
        n2 = objectArray.length;
        n = 0;
        while (n < n2) {
            Object stack = objectArray[n];
            log.log(Level.SEVERE, "\t\t" + stack);
            ++n;
        }
    }
}

