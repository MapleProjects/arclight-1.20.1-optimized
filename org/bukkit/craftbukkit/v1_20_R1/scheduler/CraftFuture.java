/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftTask;
import org.bukkit.plugin.Plugin;

class CraftFuture<T>
extends CraftTask
implements Future<T> {
    private final Callable<T> callable;
    private T value;
    private Exception exception = null;

    CraftFuture(Callable<T> callable, Plugin plugin, int id) {
        super(plugin, null, id, -1L);
        this.callable = callable;
    }

    @Override
    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        if (this.getPeriod() != -1L) {
            return false;
        }
        this.setPeriod(-2L);
        return true;
    }

    @Override
    public boolean isDone() {
        long period = this.getPeriod();
        return period != -1L && period != -3L;
    }

    @Override
    public T get() throws CancellationException, InterruptedException, ExecutionException {
        try {
            return this.get(0L, TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException e) {
            throw new Error(e);
        }
    }

    @Override
    public synchronized T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long timestamp;
        timeout = unit.toMillis(timeout);
        long period = this.getPeriod();
        long l = timestamp = timeout > 0L ? System.currentTimeMillis() : 0L;
        while (period == -1L || period == -3L) {
            this.wait(timeout);
            period = this.getPeriod();
            if (period != -1L && period != -3L) break;
            if (timeout == 0L || (timeout += timestamp - (timestamp = System.currentTimeMillis())) > 0L) continue;
            throw new TimeoutException();
        }
        if (period == -2L) {
            throw new CancellationException();
        }
        if (period == -4L) {
            if (this.exception == null) {
                return this.value;
            }
            throw new ExecutionException(this.exception);
        }
        throw new IllegalStateException("Expected -1 to -4, got " + period);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        block18: {
            CraftFuture craftFuture = this;
            synchronized (craftFuture) {
                if (this.getPeriod() == -2L) {
                    return;
                }
                this.setPeriod(-3L);
            }
            try {
                try {
                    this.value = this.callable.call();
                }
                catch (Exception e) {
                    this.exception = e;
                    CraftFuture craftFuture2 = this;
                    synchronized (craftFuture2) {
                        this.setPeriod(-4L);
                        this.notifyAll();
                        break block18;
                    }
                }
            }
            catch (Throwable throwable) {
                CraftFuture craftFuture3 = this;
                synchronized (craftFuture3) {
                    this.setPeriod(-4L);
                    this.notifyAll();
                }
                throw throwable;
            }
            CraftFuture craftFuture4 = this;
            synchronized (craftFuture4) {
                this.setPeriod(-4L);
                this.notifyAll();
            }
        }
    }

    @Override
    synchronized boolean cancel0() {
        if (this.getPeriod() != -1L) {
            return false;
        }
        this.setPeriod(-2L);
        this.notifyAll();
        return true;
    }
}

