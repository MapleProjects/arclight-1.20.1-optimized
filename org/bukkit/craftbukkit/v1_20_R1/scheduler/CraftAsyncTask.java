/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.scheduler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitWorker;

class CraftAsyncTask
extends CraftTask {
    private final LinkedList<BukkitWorker> workers = new LinkedList();
    private final Map<Integer, CraftTask> runners;

    CraftAsyncTask(Map<Integer, CraftTask> runners, Plugin plugin, Object task, int id, long delay) {
        super(plugin, task, id, delay);
        this.runners = runners;
    }

    @Override
    public boolean isSync() {
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        block37: {
            Throwable thrown;
            Thread thread;
            block35: {
                thread = Thread.currentThread();
                LinkedList<BukkitWorker> linkedList = this.workers;
                synchronized (linkedList) {
                    if (this.getPeriod() == -2L) {
                        return;
                    }
                    this.workers.add(new BukkitWorker(){

                        @Override
                        public Thread getThread() {
                            return thread;
                        }

                        @Override
                        public int getTaskId() {
                            return CraftAsyncTask.this.getTaskId();
                        }

                        @Override
                        public Plugin getOwner() {
                            return CraftAsyncTask.this.getOwner();
                        }
                    });
                }
                thrown = null;
                try {
                    try {
                        super.run();
                        break block35;
                    }
                    catch (Throwable t) {
                        thrown = t;
                        this.getOwner().getLogger().log(Level.WARNING, String.format("Plugin %s generated an exception while executing task %s", this.getOwner().getDescription().getFullName(), this.getTaskId()), thrown);
                        LinkedList<BukkitWorker> linkedList2 = this.workers;
                        synchronized (linkedList2) {
                            try {
                                Iterator workers = this.workers.iterator();
                                boolean removed = false;
                                while (workers.hasNext()) {
                                    if (((BukkitWorker)workers.next()).getThread() != thread) continue;
                                    workers.remove();
                                    removed = true;
                                    break;
                                }
                                if (!removed) {
                                    throw new IllegalStateException(String.format("Unable to remove worker %s on task %s for %s", thread.getName(), this.getTaskId(), this.getOwner().getDescription().getFullName()), thrown);
                                }
                            }
                            finally {
                                if (this.getPeriod() < 0L && this.workers.isEmpty()) {
                                    this.runners.remove(this.getTaskId());
                                }
                            }
                            break block37;
                        }
                    }
                }
                catch (Throwable throwable) {
                    LinkedList<BukkitWorker> linkedList3 = this.workers;
                    synchronized (linkedList3) {
                        try {
                            Iterator workers = this.workers.iterator();
                            boolean removed = false;
                            while (workers.hasNext()) {
                                if (((BukkitWorker)workers.next()).getThread() != thread) continue;
                                workers.remove();
                                removed = true;
                                break;
                            }
                            if (!removed) {
                                throw new IllegalStateException(String.format("Unable to remove worker %s on task %s for %s", thread.getName(), this.getTaskId(), this.getOwner().getDescription().getFullName()), thrown);
                            }
                        }
                        finally {
                            if (this.getPeriod() < 0L && this.workers.isEmpty()) {
                                this.runners.remove(this.getTaskId());
                            }
                        }
                    }
                }
                throw throwable;
            }
            LinkedList<BukkitWorker> linkedList = this.workers;
            synchronized (linkedList) {
                try {
                    Iterator workers = this.workers.iterator();
                    boolean removed = false;
                    while (workers.hasNext()) {
                        if (((BukkitWorker)workers.next()).getThread() != thread) continue;
                        workers.remove();
                        removed = true;
                        break;
                    }
                    if (!removed) {
                        throw new IllegalStateException(String.format("Unable to remove worker %s on task %s for %s", thread.getName(), this.getTaskId(), this.getOwner().getDescription().getFullName()), thrown);
                    }
                }
                finally {
                    if (this.getPeriod() < 0L && this.workers.isEmpty()) {
                        this.runners.remove(this.getTaskId());
                    }
                }
            }
        }
    }

    LinkedList<BukkitWorker> getWorkers() {
        return this.workers;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    boolean cancel0() {
        LinkedList<BukkitWorker> linkedList = this.workers;
        synchronized (linkedList) {
            this.setPeriod(-2L);
            if (this.workers.isEmpty()) {
                this.runners.remove(this.getTaskId());
            }
        }
        return true;
    }
}

