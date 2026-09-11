/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TimedRegisteredListener
extends RegisteredListener {
    private int count;
    private long totalTime;
    private Class<? extends Event> eventClass;
    private boolean multiple = false;

    public TimedRegisteredListener(@NotNull Listener pluginListener, @NotNull EventExecutor eventExecutor, @NotNull EventPriority eventPriority, @NotNull Plugin registeredPlugin, boolean listenCancelled) {
        super(pluginListener, eventExecutor, eventPriority, registeredPlugin, listenCancelled);
    }

    @Override
    public void callEvent(@NotNull Event event) throws EventException {
        if (event.isAsynchronous()) {
            super.callEvent(event);
            return;
        }
        ++this.count;
        Class<?> newEventClass = event.getClass();
        if (this.eventClass == null) {
            this.eventClass = newEventClass;
        } else if (!this.eventClass.equals(newEventClass)) {
            this.multiple = true;
            this.eventClass = TimedRegisteredListener.getCommonSuperclass(newEventClass, this.eventClass).asSubclass(Event.class);
        }
        long start = System.nanoTime();
        super.callEvent(event);
        this.totalTime += System.nanoTime() - start;
    }

    @NotNull
    private static Class<?> getCommonSuperclass(@NotNull Class<?> class1, @NotNull Class<?> class2) {
        while (!class1.isAssignableFrom(class2)) {
            class1 = class1.getSuperclass();
        }
        return class1;
    }

    public void reset() {
        this.count = 0;
        this.totalTime = 0L;
    }

    public int getCount() {
        return this.count;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    @Nullable
    public Class<? extends Event> getEventClass() {
        return this.eventClass;
    }

    public boolean hasMultiple() {
        return this.multiple;
    }
}

