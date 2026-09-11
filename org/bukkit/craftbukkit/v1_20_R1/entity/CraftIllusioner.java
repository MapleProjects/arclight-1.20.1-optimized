/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Illusioner
 *  net.minecraft.world.entity.monster.SpellcasterIllager
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpellcaster;
import org.bukkit.entity.Illusioner;

public class CraftIllusioner
extends CraftSpellcaster
implements Illusioner {
    public CraftIllusioner(CraftServer server, net.minecraft.world.entity.monster.Illusioner entity) {
        super(server, (SpellcasterIllager)entity);
    }

    public net.minecraft.world.entity.monster.Illusioner getHandle() {
        return (net.minecraft.world.entity.monster.Illusioner)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftIllusioner";
    }
}

