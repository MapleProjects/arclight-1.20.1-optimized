/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.common.mod.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ArclightCallbackExecutor
implements Executor,
Runnable {
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<Runnable>();

    @Override
    public void execute(Runnable runnable) {
        this.queue.add(runnable);
    }

    @Override
    public void run() {
        Runnable runnable;
        int n = 512;
        long l = System.nanoTime();
        while (--n >= 0 && (runnable = this.queue.poll()) != null) {
            try {
                runnable.run();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            if (System.nanoTime() - l <= 5000000L) continue;
            break;
        }
    }
}

