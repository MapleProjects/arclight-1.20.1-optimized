/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.AbstractIllager
 *  net.minecraft.world.entity.monster.SpellcasterIllager
 *  net.minecraft.world.entity.monster.SpellcasterIllager$IllagerSpell
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftIllager;
import org.bukkit.entity.Spellcaster;

public class CraftSpellcaster
extends CraftIllager
implements Spellcaster {
    public CraftSpellcaster(CraftServer server, SpellcasterIllager entity) {
        super(server, (AbstractIllager)entity);
    }

    public SpellcasterIllager getHandle() {
        return (SpellcasterIllager)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftSpellcaster";
    }

    @Override
    public Spellcaster.Spell getSpell() {
        return CraftSpellcaster.toBukkitSpell(this.getHandle().m_33737_());
    }

    @Override
    public void setSpell(Spellcaster.Spell spell) {
        Preconditions.checkArgument((spell != null ? 1 : 0) != 0, (Object)"Use Spell.NONE");
        this.getHandle().m_33727_(CraftSpellcaster.toNMSSpell(spell));
    }

    public static Spellcaster.Spell toBukkitSpell(SpellcasterIllager.IllagerSpell spell) {
        return Spellcaster.Spell.valueOf(spell.name());
    }

    public static SpellcasterIllager.IllagerSpell toNMSSpell(Spellcaster.Spell spell) {
        return SpellcasterIllager.IllagerSpell.m_33758_((int)spell.ordinal());
    }
}

