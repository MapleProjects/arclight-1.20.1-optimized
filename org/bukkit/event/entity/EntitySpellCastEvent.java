/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Spellcaster;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntitySpellCastEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final Spellcaster.Spell spell;

    public EntitySpellCastEvent(@NotNull Spellcaster what, @NotNull Spellcaster.Spell spell) {
        super(what);
        this.spell = spell;
    }

    @Override
    @NotNull
    public Spellcaster getEntity() {
        return (Spellcaster)this.entity;
    }

    @NotNull
    public Spellcaster.Spell getSpell() {
        return this.spell;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

