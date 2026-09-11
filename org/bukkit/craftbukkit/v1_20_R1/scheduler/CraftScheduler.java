/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 */
package org.bukkit.craftbukkit.v1_20_R1.scheduler;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
import java.util.logging.Level;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftAsyncDebugger;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftAsyncTask;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftFuture;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftTask;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

public class CraftScheduler
implements BukkitScheduler {
    private static final int START_ID = 1;
    private static final IntUnaryOperator INCREMENT_IDS = previous -> {
        if (previous == Integer.MAX_VALUE) {
            return 1;
        }
        return previous + 1;
    };
    private final AtomicInteger ids = new AtomicInteger(1);
    private volatile CraftTask head = new CraftTask();
    private final AtomicReference<CraftTask> tail = new AtomicReference<CraftTask>(this.head);
    private final PriorityQueue<CraftTask> pending = new PriorityQueue<CraftTask>(10, new Comparator<CraftTask>(){

        @Override
        public int compare(CraftTask o1, CraftTask o2) {
            int value = Long.compare(o1.getNextRun(), o2.getNextRun());
            return value != 0 ? value : Long.compare(o1.getCreatedAt(), o2.getCreatedAt());
        }
    });
    private final List<CraftTask> temp = new ArrayList<CraftTask>();
    private final ConcurrentHashMap<Integer, CraftTask> runners = new ConcurrentHashMap();
    private volatile CraftTask currentTask = null;
    private volatile int currentTick = -1;
    private final Executor executor = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("Craft Scheduler Thread - %d").build());
    private CraftAsyncDebugger debugHead;
    private CraftAsyncDebugger debugTail = this.debugHead = new CraftAsyncDebugger(-1, null, null){

        @Override
        StringBuilder debugTo(StringBuilder string) {
            return string;
        }
    };
    private static final int RECENT_TICKS = 30;

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task) {
        return this.scheduleSyncDelayedTask(plugin, task, 0L);
    }

    @Override
    public BukkitTask runTask(Plugin plugin, Runnable runnable) {
        return this.runTaskLater(plugin, runnable, 0L);
    }

    @Override
    public void runTask(Plugin plugin, Consumer<BukkitTask> task) throws IllegalArgumentException {
        this.runTaskLater(plugin, task, 0L);
    }

    @Override
    @Deprecated
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task) {
        return this.scheduleAsyncDelayedTask(plugin, task, 0L);
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable runnable) {
        return this.runTaskLaterAsynchronously(plugin, runnable, 0L);
    }

    @Override
    public void runTaskAsynchronously(Plugin plugin, Consumer<BukkitTask> task) throws IllegalArgumentException {
        this.runTaskLaterAsynchronously(plugin, task, 0L);
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay) {
        return this.scheduleSyncRepeatingTask(plugin, task, delay, -1L);
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, Runnable runnable, long delay) {
        return this.runTaskTimer(plugin, runnable, delay, -1L);
    }

    @Override
    public void runTaskLater(Plugin plugin, Consumer<BukkitTask> task, long delay) throws IllegalArgumentException {
        this.runTaskTimer(plugin, task, delay, -1L);
    }

    @Override
    @Deprecated
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay) {
        return this.scheduleAsyncRepeatingTask(plugin, task, delay, -1L);
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable runnable, long delay) {
        return this.runTaskTimerAsynchronously(plugin, runnable, delay, -1L);
    }

    @Override
    public void runTaskLaterAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay) throws IllegalArgumentException {
        this.runTaskTimerAsynchronously(plugin, task, delay, -1L);
    }

    @Override
    public void runTaskTimerAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        this.runTaskTimerAsynchronously(plugin, (Object)task, delay, -1L);
    }

    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, Runnable runnable, long delay, long period) {
        return this.runTaskTimer(plugin, runnable, delay, period).getTaskId();
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, Runnable runnable, long delay, long period) {
        return this.runTaskTimer(plugin, (Object)runnable, delay, period);
    }

    @Override
    public void runTaskTimer(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        this.runTaskTimer(plugin, (Object)task, delay, period);
    }

    public BukkitTask runTaskTimer(Plugin plugin, Object runnable, long delay, long period) {
        CraftScheduler.validate(plugin, runnable);
        if (delay < 0L) {
            delay = 0L;
        }
        if (period == 0L) {
            period = 1L;
        } else if (period < -1L) {
            period = -1L;
        }
        return this.handle(new CraftTask(plugin, runnable, this.nextId(), period), delay);
    }

    @Override
    @Deprecated
    public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable runnable, long delay, long period) {
        return this.runTaskTimerAsynchronously(plugin, runnable, delay, period).getTaskId();
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable runnable, long delay, long period) {
        return this.runTaskTimerAsynchronously(plugin, (Object)runnable, delay, period);
    }

    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Object runnable, long delay, long period) {
        CraftScheduler.validate(plugin, runnable);
        if (delay < 0L) {
            delay = 0L;
        }
        if (period == 0L) {
            period = 1L;
        } else if (period < -1L) {
            period = -1L;
        }
        return this.handle(new CraftAsyncTask(this.runners, plugin, runnable, this.nextId(), period), delay);
    }

    @Override
    public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task) {
        CraftScheduler.validate(plugin, task);
        CraftFuture<T> future = new CraftFuture<T>(task, plugin, this.nextId());
        this.handle(future, 0L);
        return future;
    }

    @Override
    public void cancelTask(final int taskId) {
        if (taskId <= 0) {
            return;
        }
        CraftTask task = this.runners.get(taskId);
        if (task != null) {
            task.cancel0();
        }
        task = new CraftTask(new Runnable(){

            @Override
            public void run() {
                if (!this.check(CraftScheduler.this.temp)) {
                    this.check(CraftScheduler.this.pending);
                }
            }

            private boolean check(Iterable<CraftTask> collection) {
                Iterator<CraftTask> tasks = collection.iterator();
                while (tasks.hasNext()) {
                    CraftTask task = tasks.next();
                    if (task.getTaskId() != taskId) continue;
                    task.cancel0();
                    tasks.remove();
                    if (task.isSync()) {
                        CraftScheduler.this.runners.remove(taskId);
                    }
                    return true;
                }
                return false;
            }
        });
        this.handle(task, 0L);
        CraftTask taskPending = this.head.getNext();
        while (taskPending != null) {
            if (taskPending == task) {
                return;
            }
            if (taskPending.getTaskId() == taskId) {
                taskPending.cancel0();
            }
            taskPending = taskPending.getNext();
        }
    }

    @Override
    public void cancelTasks(final Plugin plugin) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Cannot cancel tasks of null plugin");
        CraftTask task = new CraftTask(new Runnable(){

            @Override
            public void run() {
                this.check(CraftScheduler.this.pending);
                this.check(CraftScheduler.this.temp);
            }

            void check(Iterable<CraftTask> collection) {
                Iterator<CraftTask> tasks = collection.iterator();
                while (tasks.hasNext()) {
                    CraftTask task = tasks.next();
                    if (!task.getOwner().equals(plugin)) continue;
                    task.cancel0();
                    tasks.remove();
                    if (!task.isSync()) continue;
                    CraftScheduler.this.runners.remove(task.getTaskId());
                }
            }
        });
        this.handle(task, 0L);
        CraftTask taskPending = this.head.getNext();
        while (taskPending != null) {
            if (taskPending == task) break;
            if (taskPending.getTaskId() != -1 && taskPending.getOwner().equals(plugin)) {
                taskPending.cancel0();
            }
            taskPending = taskPending.getNext();
        }
        for (CraftTask runner : this.runners.values()) {
            if (!runner.getOwner().equals(plugin)) continue;
            runner.cancel0();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isCurrentlyRunning(int taskId) {
        CraftTask task = this.runners.get(taskId);
        if (task == null) {
            return false;
        }
        if (task.isSync()) {
            return task == this.currentTask;
        }
        CraftAsyncTask asyncTask = (CraftAsyncTask)task;
        LinkedList<BukkitWorker> linkedList = asyncTask.getWorkers();
        synchronized (linkedList) {
            return !asyncTask.getWorkers().isEmpty();
        }
    }

    @Override
    public boolean isQueued(int taskId) {
        if (taskId <= 0) {
            return false;
        }
        CraftTask task = this.head.getNext();
        while (task != null) {
            if (task.getTaskId() == taskId) {
                return task.getPeriod() >= -1L;
            }
            task = task.getNext();
        }
        task = this.runners.get(taskId);
        return task != null && task.getPeriod() >= -1L;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BukkitWorker> getActiveWorkers() {
        ArrayList<BukkitWorker> workers = new ArrayList<BukkitWorker>();
        for (CraftTask taskObj : this.runners.values()) {
            if (taskObj.isSync()) continue;
            CraftAsyncTask task = (CraftAsyncTask)taskObj;
            LinkedList<BukkitWorker> linkedList = task.getWorkers();
            synchronized (linkedList) {
                workers.addAll(task.getWorkers());
            }
        }
        return workers;
    }

    @Override
    public List<BukkitTask> getPendingTasks() {
        ArrayList<CraftTask> truePending = new ArrayList<CraftTask>();
        CraftTask task = this.head.getNext();
        while (task != null) {
            if (task.getTaskId() != -1) {
                truePending.add(task);
            }
            task = task.getNext();
        }
        ArrayList<BukkitTask> pending = new ArrayList<BukkitTask>();
        for (CraftTask task2 : this.runners.values()) {
            if (task2.getPeriod() < -1L) continue;
            pending.add(task2);
        }
        for (CraftTask task2 : truePending) {
            if (task2.getPeriod() < -1L || pending.contains(task2)) continue;
            pending.add(task2);
        }
        return pending;
    }

    public void mainThreadHeartbeat(int currentTick) {
        this.currentTick = currentTick;
        List<CraftTask> temp = this.temp;
        this.parsePending();
        while (this.isReady(currentTick)) {
            CraftTask task = (CraftTask)this.pending.remove();
            if (task.getPeriod() < -1L) {
                if (task.isSync()) {
                    this.runners.remove(task.getTaskId(), task);
                }
                this.parsePending();
                continue;
            }
            if (task.isSync()) {
                block11: {
                    this.currentTask = task;
                    try {
                        try {
                            task.timings.startTiming();
                            task.run();
                            task.timings.stopTiming();
                        }
                        catch (Throwable throwable) {
                            task.getOwner().getLogger().log(Level.WARNING, String.format("Task #%s for %s generated an exception", task.getTaskId(), task.getOwner().getDescription().getFullName()), throwable);
                            this.currentTask = null;
                            break block11;
                        }
                    }
                    catch (Throwable throwable) {
                        this.currentTask = null;
                        throw throwable;
                    }
                    this.currentTask = null;
                }
                this.parsePending();
            } else {
                this.debugTail = this.debugTail.setNext(new CraftAsyncDebugger(currentTick + RECENT_TICKS, task.getOwner(), task.getTaskClass()));
                this.executor.execute(task);
            }
            long period = task.getPeriod();
            if (period > 0L) {
                task.setNextRun((long)currentTick + period);
                temp.add(task);
                continue;
            }
            if (!task.isSync()) continue;
            this.runners.remove(task.getTaskId());
        }
        this.pending.addAll(temp);
        temp.clear();
        this.debugHead = this.debugHead.getNextHead(currentTick);
    }

    private void addTask(CraftTask task) {
        AtomicReference<CraftTask> tail = this.tail;
        CraftTask tailTask = tail.get();
        while (!tail.compareAndSet(tailTask, task)) {
            tailTask = tail.get();
        }
        tailTask.setNext(task);
    }

    private CraftTask handle(CraftTask task, long delay) {
        task.setNextRun((long)this.currentTick + delay);
        this.addTask(task);
        return task;
    }

    private static void validate(Plugin plugin, Object task) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        Preconditions.checkArgument((task instanceof Runnable || task instanceof Consumer || task instanceof Callable ? 1 : 0) != 0, (Object)"Task must be Runnable, Consumer, or Callable");
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
        }
    }

    private int nextId() {
        int id;
        Preconditions.checkArgument((this.runners.size() < Integer.MAX_VALUE ? 1 : 0) != 0, (String)"There are already %s tasks scheduled! Cannot schedule more", (int)Integer.MAX_VALUE);
        while (this.runners.containsKey(id = this.ids.updateAndGet(INCREMENT_IDS))) {
        }
        return id;
    }

    private void parsePending() {
        CraftTask head = this.head;
        CraftTask task = head.getNext();
        CraftTask lastTask = head;
        while (task != null) {
            if (task.getTaskId() == -1) {
                task.run();
            } else if (task.getPeriod() >= -1L) {
                this.pending.add(task);
                this.runners.put(task.getTaskId(), task);
            }
            lastTask = task;
            task = lastTask.getNext();
        }
        task = head;
        while (task != lastTask) {
            head = task.getNext();
            task.setNext(null);
            task = head;
        }
        this.head = lastTask;
    }

    private boolean isReady(int currentTick) {
        return !this.pending.isEmpty() && this.pending.peek().getNextRun() <= (long)currentTick;
    }

    public String toString() {
        int debugTick = this.currentTick;
        StringBuilder string = new StringBuilder("Recent tasks from ").append(debugTick - RECENT_TICKS).append('-').append(debugTick).append('{');
        this.debugHead.debugTo(string);
        return string.append('}').toString();
    }

    @Override
    @Deprecated
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay) {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLater(Plugin, long)");
    }

    @Override
    @Deprecated
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task) {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTask(Plugin)");
    }

    @Override
    @Deprecated
    public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period) {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimer(Plugin, long, long)");
    }

    @Override
    @Deprecated
    public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTask(Plugin)");
    }

    @Override
    @Deprecated
    public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskAsynchronously(Plugin)");
    }

    @Override
    @Deprecated
    public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLater(Plugin, long)");
    }

    @Override
    @Deprecated
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLaterAsynchronously(Plugin, long)");
    }

    @Override
    @Deprecated
    public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimer(Plugin, long, long)");
    }

    @Override
    @Deprecated
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimerAsynchronously(Plugin, long, long)");
    }
}

