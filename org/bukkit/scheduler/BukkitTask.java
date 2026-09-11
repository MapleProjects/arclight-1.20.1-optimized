/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface BukkitTask {
    public int getTaskId();

    @NotNull
    public Plugin getOwner();

    public boolean isSync();

    public boolean isCancelled();

    public void cancel();
}

