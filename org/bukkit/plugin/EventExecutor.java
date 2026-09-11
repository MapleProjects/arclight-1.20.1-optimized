/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public interface EventExecutor {
    public void execute(@NotNull Listener var1, @NotNull Event var2) throws EventException;
}

