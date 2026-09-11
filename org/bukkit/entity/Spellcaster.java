/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Illager;
import org.jetbrains.annotations.NotNull;

public interface Spellcaster
extends Illager {
    @NotNull
    public Spell getSpell();

    public void setSpell(@NotNull Spell var1);

    public static enum Spell {
        NONE,
        SUMMON_VEX,
        FANGS,
        WOLOLO,
        DISAPPEAR,
        BLINDNESS;

    }
}

