/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  it.unimi.dsi.fastutil.objects.ObjectSet
 *  net.minecraft.core.SectionPos
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.DistanceManager
 *  net.minecraft.server.level.DistanceManager$ChunkTicketTracker
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.Ticket
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.server.level.TickingTracker
 *  net.minecraft.util.SortedArraySet
 *  net.minecraft.world.level.ChunkPos
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Invoker
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.server.level;

import io.izzel.arclight.common.bridge.core.world.server.TicketManagerBridge;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.Ticket;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.level.TickingTracker;
import net.minecraft.util.SortedArraySet;
import net.minecraft.world.level.ChunkPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={DistanceManager.class})
public abstract class DistanceManagerMixin
implements TicketManagerBridge {
    @Shadow
    private long f_140771_;
    @Shadow
    @Final
    private DistanceManager.ChunkTicketTracker f_140762_;
    @Shadow
    @Final
    public Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> f_140761_;
    @Shadow(remap=false)
    @Final
    private Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> forcedTickets;

    @Shadow
    protected abstract SortedArraySet<Ticket<?>> m_140857_(long var1);

    @Shadow
    private static int m_140797_(SortedArraySet<Ticket<?>> p_229844_0_) {
        return 0;
    }

    @Shadow
    abstract TickingTracker m_183915_();

    @Override
    @Invoker(value="purgeStaleTickets")
    public abstract void bridge$tick();

    @Inject(method={"removePlayer"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", remap=false, target="Lit/unimi/dsi/fastutil/objects/ObjectSet;remove(Ljava/lang/Object;)Z")})
    private void arclight$remove(SectionPos p_140829_, ServerPlayer p_140830_, CallbackInfo ci, ChunkPos pos, long l, ObjectSet<?> set) {
        if (set == null) {
            ci.cancel();
        }
    }

    @Redirect(method={"runAllUpdates"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/Set;forEach(Ljava/util/function/Consumer;)V"))
    private void arclight$safeIter(Set<ChunkHolder> instance, Consumer<ChunkHolder> consumer) {
        Iterator<ChunkHolder> iter = instance.iterator();
        int expectedSize = instance.size();
        do {
            ChunkHolder chunkHolder = iter.next();
            iter.remove();
            consumer.accept(chunkHolder);
            if (instance.size() == --expectedSize) continue;
            expectedSize = instance.size();
            iter = instance.iterator();
        } while (iter.hasNext());
    }

    public <T> boolean addRegionTicketAtDistance(TicketType<T> type, ChunkPos pos, int level, T value) {
        Ticket ticket = new Ticket(type, 33 - level, value);
        boolean ret = this.addTicket(pos.m_45588_(), ticket);
        this.m_183915_().m_184151_(pos.m_45588_(), ticket);
        return ret;
    }

    public <T> boolean removeRegionTicketAtDistance(TicketType<T> type, ChunkPos pos, int level, T value) {
        Ticket ticket = new Ticket(type, 33 - level, value);
        boolean ret = this.removeTicket(pos.m_45588_(), ticket);
        this.m_183915_().m_184165_(pos.m_45588_(), ticket);
        return ret;
    }

    public <T> boolean addTicketAtLevel(TicketType<T> type, ChunkPos pos, int level, T value) {
        Ticket ticket = new Ticket(type, level, value);
        return this.addTicket(pos.m_45588_(), ticket);
    }

    public <T> boolean removeTicketAtLevel(TicketType<T> type, ChunkPos pos, int level, T value) {
        Ticket ticket = new Ticket(type, level, value);
        return this.removeTicket(pos.m_45588_(), ticket);
    }

    @Override
    public <T> boolean bridge$addTicketAtLevel(TicketType<T> type, ChunkPos pos, int level, T value) {
        return this.addTicketAtLevel(type, pos, level, value);
    }

    @Override
    public <T> boolean bridge$removeTicketAtLevel(TicketType<T> type, ChunkPos pos, int level, T value) {
        return this.removeTicketAtLevel(type, pos, level, value);
    }

    boolean removeTicket(long chunkPosIn, Ticket<?> ticketIn) {
        SortedArraySet tickets;
        SortedArraySet<Ticket<?>> ticketSet = this.m_140857_(chunkPosIn);
        boolean removed = false;
        if (ticketSet.remove(ticketIn)) {
            removed = true;
        }
        if (ticketSet.isEmpty()) {
            this.f_140761_.remove(chunkPosIn);
        }
        this.f_140762_.m_140715_(chunkPosIn, DistanceManagerMixin.m_140797_(ticketSet), false);
        if (ticketIn.isForceTicks() && (tickets = (SortedArraySet)this.forcedTickets.get(chunkPosIn)) != null) {
            tickets.remove(ticketIn);
        }
        return removed;
    }

    @Override
    public boolean bridge$removeTicket(long chunkPos, Ticket<?> ticket) {
        return this.removeTicket(chunkPos, ticket);
    }

    boolean addTicket(long chunkPosIn, Ticket<?> ticketIn) {
        SortedArraySet<Ticket<?>> ticketSet = this.m_140857_(chunkPosIn);
        int level = DistanceManagerMixin.m_140797_(ticketSet);
        Ticket ticket = (Ticket)ticketSet.m_14253_(ticketIn);
        ticket.m_9429_(this.f_140771_);
        if (ticketIn.m_9433_() < level) {
            this.f_140762_.m_140715_(chunkPosIn, ticketIn.m_9433_(), true);
        }
        if (ticketIn.isForceTicks()) {
            SortedArraySet tickets = (SortedArraySet)this.forcedTickets.computeIfAbsent(chunkPosIn, e -> SortedArraySet.m_14246_((int)4));
            tickets.m_14253_(ticketIn);
        }
        return ticketIn == ticket;
    }

    @Override
    public boolean bridge$addTicket(long chunkPos, Ticket<?> ticket) {
        return this.addTicket(chunkPos, ticket);
    }

    public <T> void removeAllTicketsFor(TicketType<T> ticketType, int ticketLevel, T ticketIdentifier) {
        Ticket target = new Ticket(ticketType, ticketLevel, ticketIdentifier);
        ObjectIterator iterator = this.f_140761_.long2ObjectEntrySet().fastIterator();
        while (iterator.hasNext()) {
            Long2ObjectMap.Entry entry = (Long2ObjectMap.Entry)iterator.next();
            SortedArraySet tickets = (SortedArraySet)entry.getValue();
            if (!tickets.remove((Object)target)) continue;
            this.f_140762_.m_140715_(entry.getLongKey(), DistanceManagerMixin.m_140797_(tickets), false);
            if (!tickets.isEmpty()) continue;
            iterator.remove();
        }
    }

    @Override
    public <T> void bridge$removeAllTicketsFor(TicketType<T> ticketType, int ticketLevel, T ticketIdentifier) {
        this.removeAllTicketsFor(ticketType, ticketLevel, ticketIdentifier);
    }
}

