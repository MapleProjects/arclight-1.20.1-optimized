/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.Ticket
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.world.level.ChunkPos
 */
package io.izzel.arclight.common.bridge.core.world.server;

import net.minecraft.server.level.Ticket;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;

public interface TicketManagerBridge {
    public <T> boolean bridge$addTicketAtLevel(TicketType<T> var1, ChunkPos var2, int var3, T var4);

    public <T> boolean bridge$removeTicketAtLevel(TicketType<T> var1, ChunkPos var2, int var3, T var4);

    public boolean bridge$addTicket(long var1, Ticket<?> var3);

    public boolean bridge$removeTicket(long var1, Ticket<?> var3);

    public void bridge$tick();

    public <T> void bridge$removeAllTicketsFor(TicketType<T> var1, int var2, T var3);
}

