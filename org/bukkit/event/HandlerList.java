/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;

public class HandlerList {
    private volatile RegisteredListener[] handlers = null;
    private final EnumMap<EventPriority, ArrayList<RegisteredListener>> handlerslots = new EnumMap(EventPriority.class);
    private static ArrayList<HandlerList> allLists = new ArrayList();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void bakeAll() {
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            for (HandlerList h : allLists) {
                h.bake();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void unregisterAll() {
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            Iterator<HandlerList> iterator = allLists.iterator();
            while (iterator.hasNext()) {
                HandlerList h;
                HandlerList handlerList = h = iterator.next();
                synchronized (handlerList) {
                    for (List list : h.handlerslots.values()) {
                        list.clear();
                    }
                    h.handlers = null;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void unregisterAll(@NotNull Plugin plugin) {
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            for (HandlerList h : allLists) {
                h.unregister(plugin);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void unregisterAll(@NotNull Listener listener) {
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            for (HandlerList h : allLists) {
                h.unregister(listener);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public HandlerList() {
        EventPriority[] eventPriorityArray = EventPriority.values();
        int n = eventPriorityArray.length;
        int n2 = 0;
        while (n2 < n) {
            EventPriority o = eventPriorityArray[n2];
            this.handlerslots.put(o, new ArrayList());
            ++n2;
        }
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            allLists.add(this);
        }
    }

    public synchronized void register(@NotNull RegisteredListener listener) {
        if (this.handlerslots.get((Object)listener.getPriority()).contains(listener)) {
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString());
        }
        this.handlers = null;
        this.handlerslots.get((Object)listener.getPriority()).add(listener);
    }

    public void registerAll(@NotNull Collection<RegisteredListener> listeners) {
        for (RegisteredListener listener : listeners) {
            this.register(listener);
        }
    }

    public synchronized void unregister(@NotNull RegisteredListener listener) {
        if (this.handlerslots.get((Object)listener.getPriority()).remove(listener)) {
            this.handlers = null;
        }
    }

    public synchronized void unregister(@NotNull Plugin plugin) {
        boolean changed = false;
        for (List list : this.handlerslots.values()) {
            ListIterator i = list.listIterator();
            while (i.hasNext()) {
                if (!((RegisteredListener)i.next()).getPlugin().equals(plugin)) continue;
                i.remove();
                changed = true;
            }
        }
        if (changed) {
            this.handlers = null;
        }
    }

    public synchronized void unregister(@NotNull Listener listener) {
        boolean changed = false;
        for (List list : this.handlerslots.values()) {
            ListIterator i = list.listIterator();
            while (i.hasNext()) {
                if (!((RegisteredListener)i.next()).getListener().equals(listener)) continue;
                i.remove();
                changed = true;
            }
        }
        if (changed) {
            this.handlers = null;
        }
    }

    public synchronized void bake() {
        if (this.handlers != null) {
            return;
        }
        ArrayList entries = new ArrayList();
        for (Map.Entry<EventPriority, ArrayList<RegisteredListener>> entry : this.handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        this.handlers = entries.toArray(new RegisteredListener[entries.size()]);
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    @NotNull
    public RegisteredListener[] getRegisteredListeners() {
        RegisteredListener[] handlers;
        block3: {
            block2: {
                if (!true) break block2;
                handlers = this.handlers;
                if (this.handlers != null) break block3;
            }
            do {
                this.bake();
                handlers = this.handlers;
            } while (this.handlers == null);
        }
        return handlers;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static ArrayList<RegisteredListener> getRegisteredListeners(@NotNull Plugin plugin) {
        ArrayList<RegisteredListener> listeners = new ArrayList<RegisteredListener>();
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            Iterator<HandlerList> iterator = allLists.iterator();
            while (iterator.hasNext()) {
                HandlerList h;
                HandlerList handlerList = h = iterator.next();
                synchronized (handlerList) {
                    for (List list : h.handlerslots.values()) {
                        for (RegisteredListener listener : list) {
                            if (!listener.getPlugin().equals(plugin)) continue;
                            listeners.add(listener);
                        }
                    }
                }
            }
        }
        return listeners;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static ArrayList<HandlerList> getHandlerLists() {
        ArrayList<HandlerList> arrayList = allLists;
        synchronized (arrayList) {
            return (ArrayList)allLists.clone();
        }
    }
}

