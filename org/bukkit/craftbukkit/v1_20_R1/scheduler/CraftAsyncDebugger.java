/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.scheduler;

import org.bukkit.plugin.Plugin;

class CraftAsyncDebugger {
    private CraftAsyncDebugger next = null;
    private final int expiry;
    private final Plugin plugin;
    private final Class<?> clazz;

    CraftAsyncDebugger(int expiry, Plugin plugin, Class<?> clazz) {
        this.expiry = expiry;
        this.plugin = plugin;
        this.clazz = clazz;
    }

    final CraftAsyncDebugger getNextHead(int time) {
        CraftAsyncDebugger next;
        CraftAsyncDebugger current = this;
        while (time > current.expiry && (next = current.next) != null) {
            current = next;
        }
        return current;
    }

    final CraftAsyncDebugger setNext(CraftAsyncDebugger next) {
        this.next = next;
        return this.next;
    }

    StringBuilder debugTo(StringBuilder string) {
        CraftAsyncDebugger next = this;
        while (next != null) {
            string.append(next.plugin.getDescription().getName()).append(':').append(next.clazz.getName()).append('@').append(next.expiry).append(',');
            next = next.next;
        }
        return string;
    }
}

