/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.scheduler;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;

public interface BukkitScheduler {
    public int scheduleSyncDelayedTask(@NotNull Plugin var1, @NotNull Runnable var2, long var3);

    @Deprecated
    public int scheduleSyncDelayedTask(@NotNull Plugin var1, @NotNull BukkitRunnable var2, long var3);

    public int scheduleSyncDelayedTask(@NotNull Plugin var1, @NotNull Runnable var2);

    @Deprecated
    public int scheduleSyncDelayedTask(@NotNull Plugin var1, @NotNull BukkitRunnable var2);

    public int scheduleSyncRepeatingTask(@NotNull Plugin var1, @NotNull Runnable var2, long var3, long var5);

    @Deprecated
    public int scheduleSyncRepeatingTask(@NotNull Plugin var1, @NotNull BukkitRunnable var2, long var3, long var5);

    @Deprecated
    public int scheduleAsyncDelayedTask(@NotNull Plugin var1, @NotNull Runnable var2, long var3);

    @Deprecated
    public int scheduleAsyncDelayedTask(@NotNull Plugin var1, @NotNull Runnable var2);

    @Deprecated
    public int scheduleAsyncRepeatingTask(@NotNull Plugin var1, @NotNull Runnable var2, long var3, long var5);

    @NotNull
    public <T> Future<T> callSyncMethod(@NotNull Plugin var1, @NotNull Callable<T> var2);

    public void cancelTask(int var1);

    public void cancelTasks(@NotNull Plugin var1);

    public boolean isCurrentlyRunning(int var1);

    public boolean isQueued(int var1);

    @NotNull
    public List<BukkitWorker> getActiveWorkers();

    @NotNull
    public List<BukkitTask> getPendingTasks();

    @NotNull
    public BukkitTask runTask(@NotNull Plugin var1, @NotNull Runnable var2) throws IllegalArgumentException;

    public void runTask(@NotNull Plugin var1, @NotNull Consumer<BukkitTask> var2) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public BukkitTask runTask(@NotNull Plugin var1, @NotNull BukkitRunnable var2) throws IllegalArgumentException;

    @NotNull
    public BukkitTask runTaskAsynchronously(@NotNull Plugin var1, @NotNull Runnable var2) throws IllegalArgumentException;

    public void runTaskAsynchronously(@NotNull Plugin var1, @NotNull Consumer<BukkitTask> var2) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public BukkitTask runTaskAsynchronously(@NotNull Plugin var1, @NotNull BukkitRunnable var2) throws IllegalArgumentException;

    @NotNull
    public BukkitTask runTaskLater(@NotNull Plugin var1, @NotNull Runnable var2, long var3) throws IllegalArgumentException;

    public void runTaskLater(@NotNull Plugin var1, @NotNull Consumer<BukkitTask> var2, long var3) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public BukkitTask runTaskLater(@NotNull Plugin var1, @NotNull BukkitRunnable var2, long var3) throws IllegalArgumentException;

    @NotNull
    public BukkitTask runTaskLaterAsynchronously(@NotNull Plugin var1, @NotNull Runnable var2, long var3) throws IllegalArgumentException;

    public void runTaskLaterAsynchronously(@NotNull Plugin var1, @NotNull Consumer<BukkitTask> var2, long var3) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public BukkitTask runTaskLaterAsynchronously(@NotNull Plugin var1, @NotNull BukkitRunnable var2, long var3) throws IllegalArgumentException;

    @NotNull
    public BukkitTask runTaskTimer(@NotNull Plugin var1, @NotNull Runnable var2, long var3, long var5) throws IllegalArgumentException;

    public void runTaskTimer(@NotNull Plugin var1, @NotNull Consumer<BukkitTask> var2, long var3, long var5) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public BukkitTask runTaskTimer(@NotNull Plugin var1, @NotNull BukkitRunnable var2, long var3, long var5) throws IllegalArgumentException;

    @NotNull
    public BukkitTask runTaskTimerAsynchronously(@NotNull Plugin var1, @NotNull Runnable var2, long var3, long var5) throws IllegalArgumentException;

    public void runTaskTimerAsynchronously(@NotNull Plugin var1, @NotNull Consumer<BukkitTask> var2, long var3, long var5) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public BukkitTask runTaskTimerAsynchronously(@NotNull Plugin var1, @NotNull BukkitRunnable var2, long var3, long var5) throws IllegalArgumentException;
}

