/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.axolotl.Axolotl
 *  net.minecraft.world.entity.animal.axolotl.Axolotl$Variant
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Axolotl;

public class CraftAxolotl
extends CraftAnimals
implements Axolotl {
    public CraftAxolotl(CraftServer server, net.minecraft.world.entity.animal.axolotl.Axolotl entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.axolotl.Axolotl getHandle() {
        return (net.minecraft.world.entity.animal.axolotl.Axolotl)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftAxolotl";
    }

    @Override
    public boolean isPlayingDead() {
        return this.getHandle().m_149175_();
    }

    @Override
    public void setPlayingDead(boolean playingDead) {
        this.getHandle().m_149198_(playingDead);
    }

    @Override
    public Axolotl.Variant getVariant() {
        return Axolotl.Variant.values()[this.getHandle().m_28554_().ordinal()];
    }

    @Override
    public void setVariant(Axolotl.Variant variant) {
        Preconditions.checkArgument((variant != null ? 1 : 0) != 0, (Object)"variant");
        this.getHandle().m_28464_(Axolotl.Variant.m_262843_((int)variant.ordinal()));
    }
}

