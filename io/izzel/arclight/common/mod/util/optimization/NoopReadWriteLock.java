/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.util.optimization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import org.jetbrains.annotations.NotNull;

public class NoopReadWriteLock
implements ReadWriteLock {
    private static final NoopReadWriteLock INSTANCE = new NoopReadWriteLock();
    private static final NoopLock LOCK = new NoopLock();

    @Override
    @NotNull
    public Lock readLock() {
        return LOCK;
    }

    @Override
    @NotNull
    public Lock writeLock() {
        return LOCK;
    }

    public static ReadWriteLock instance() {
        return INSTANCE;
    }

    private static class NoopLock
    implements Lock {
        private NoopLock() {
        }

        @Override
        public void lock() {
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
        }

        @Override
        public boolean tryLock() {
            return true;
        }

        @Override
        public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
            return true;
        }

        @Override
        public void unlock() {
        }

        @Override
        @NotNull
        public Condition newCondition() {
            throw new AssertionError();
        }
    }
}

