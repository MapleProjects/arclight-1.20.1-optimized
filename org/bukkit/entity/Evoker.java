/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Spellcaster;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Evoker
extends Spellcaster {
    @Deprecated
    @NotNull
    public Spell getCurrentSpell();

    @Deprecated
    public void setCurrentSpell(@Nullable Spell var1);

    @Deprecated
    public static enum Spell {
        NONE,
        SUMMON,
        FANGS,
        WOLOLO,
        DISAPPEAR,
        BLINDNESS;

    }
}

