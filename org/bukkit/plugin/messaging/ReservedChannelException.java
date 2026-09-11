/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.plugin.messaging;

public class ReservedChannelException
extends RuntimeException {
    public ReservedChannelException() {
        this("Attempted to register for a reserved channel name.");
    }

    public ReservedChannelException(String name) {
        super("Attempted to register for a reserved channel name ('" + name + "')");
    }
}

