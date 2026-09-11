/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.animal.Parrot
 *  net.minecraft.world.entity.animal.Parrot$Variant
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Parrot;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTameableAnimal;
import org.bukkit.entity.Parrot;

public class CraftParrot
extends CraftTameableAnimal
implements Parrot {
    public CraftParrot(CraftServer server, net.minecraft.world.entity.animal.Parrot parrot) {
        super(server, (TamableAnimal)parrot);
    }

    public net.minecraft.world.entity.animal.Parrot getHandle() {
        return (net.minecraft.world.entity.animal.Parrot)this.entity;
    }

    @Override
    public Parrot.Variant getVariant() {
        return Parrot.Variant.values()[this.getHandle().m_28554_().ordinal()];
    }

    @Override
    public void setVariant(Parrot.Variant variant) {
        Preconditions.checkArgument((variant != null ? 1 : 0) != 0, (Object)"variant");
        this.getHandle().m_28464_(Parrot.Variant.m_262398_((int)variant.ordinal()));
    }

    @Override
    public String toString() {
        return "CraftParrot";
    }

    @Override
    public boolean isDancing() {
        return this.getHandle().m_29439_();
    }
}

