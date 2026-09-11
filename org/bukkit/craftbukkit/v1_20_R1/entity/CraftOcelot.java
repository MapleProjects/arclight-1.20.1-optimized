/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Ocelot
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Ocelot;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Ocelot;

public class CraftOcelot
extends CraftAnimals
implements org.bukkit.entity.Ocelot {
    public CraftOcelot(CraftServer server, Ocelot ocelot) {
        super(server, (Animal)ocelot);
    }

    public Ocelot getHandle() {
        return (Ocelot)this.entity;
    }

    @Override
    public boolean isTrusting() {
        return this.getHandle().m_29038_();
    }

    @Override
    public void setTrusting(boolean trust) {
        this.getHandle().m_29045_(trust);
    }

    @Override
    public Ocelot.Type getCatType() {
        return Ocelot.Type.WILD_OCELOT;
    }

    @Override
    public void setCatType(Ocelot.Type type) {
        throw new UnsupportedOperationException("Cats are now a different entity!");
    }

    @Override
    public String toString() {
        return "CraftOcelot";
    }
}

