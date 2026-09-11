/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;

public abstract class Waitable<T>
implements Runnable {
    Throwable t = null;
    T value = null;
    Status status = Status.WAITING;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void run() {
        block17: {
            Waitable waitable = this;
            synchronized (waitable) {
                Preconditions.checkState((this.status == Status.WAITING ? 1 : 0) != 0, (String)"Invalid state %s", (Object)((Object)this.status));
                this.status = Status.RUNNING;
            }
            try {
                try {
                    this.value = this.evaluate();
                }
                catch (Throwable t) {
                    this.t = t;
                    Waitable waitable2 = this;
                    synchronized (waitable2) {
                        this.status = Status.FINISHED;
                        this.notifyAll();
                        break block17;
                    }
                }
            }
            catch (Throwable throwable) {
                Waitable waitable3 = this;
                synchronized (waitable3) {
                    this.status = Status.FINISHED;
                    this.notifyAll();
                }
                throw throwable;
            }
            Waitable waitable4 = this;
            synchronized (waitable4) {
                this.status = Status.FINISHED;
                this.notifyAll();
            }
        }
    }

    protected abstract T evaluate();

    public synchronized T get() throws InterruptedException, ExecutionException {
        while (this.status != Status.FINISHED) {
            this.wait();
        }
        if (this.t != null) {
            throw new ExecutionException(this.t);
        }
        return this.value;
    }

    private static enum Status {
        WAITING,
        RUNNING,
        FINISHED;

    }
}

