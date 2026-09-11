/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Evoker
 *  net.minecraft.world.entity.monster.SpellcasterIllager
 *  net.minecraft.world.entity.monster.SpellcasterIllager$IllagerSpell
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpellcaster;
import org.bukkit.entity.Evoker;

public class CraftEvoker
extends CraftSpellcaster
implements Evoker {
    public CraftEvoker(CraftServer server, net.minecraft.world.entity.monster.Evoker entity) {
        super(server, (SpellcasterIllager)entity);
    }

    public net.minecraft.world.entity.monster.Evoker getHandle() {
        return (net.minecraft.world.entity.monster.Evoker)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftEvoker";
    }

    @Override
    public Evoker.Spell getCurrentSpell() {
        return Evoker.Spell.values()[this.getHandle().m_33737_().ordinal()];
    }

    @Override
    public void setCurrentSpell(Evoker.Spell spell) {
        this.getHandle().m_33727_(spell == null ? SpellcasterIllager.IllagerSpell.NONE : SpellcasterIllager.IllagerSpell.m_33758_((int)spell.ordinal()));
    }
}

