/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.AbstractIllager
 *  net.minecraft.world.entity.monster.Vindicator
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.AbstractIllager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftIllager;
import org.bukkit.entity.Vindicator;

public class CraftVindicator
extends CraftIllager
implements Vindicator {
    public CraftVindicator(CraftServer server, net.minecraft.world.entity.monster.Vindicator entity) {
        super(server, (AbstractIllager)entity);
    }

    public net.minecraft.world.entity.monster.Vindicator getHandle() {
        return (net.minecraft.world.entity.monster.Vindicator)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftVindicator";
    }

    @Override
    public boolean isJohnny() {
        return this.getHandle().f_34071_;
    }

    @Override
    public void setJohnny(boolean johnny) {
        this.getHandle().f_34071_ = johnny;
    }
}

