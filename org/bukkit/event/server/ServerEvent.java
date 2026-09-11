/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.event.server;

import org.bukkit.event.Event;

public abstract class ServerEvent
extends Event {
    public ServerEvent() {
    }

    public ServerEvent(boolean isAsync) {
        super(isAsync);
    }
}

