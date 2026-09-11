/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.GlowSquid
 *  net.minecraft.world.entity.animal.Squid
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.Squid;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSquid;
import org.bukkit.entity.GlowSquid;

public class CraftGlowSquid
extends CraftSquid
implements GlowSquid {
    public CraftGlowSquid(CraftServer server, net.minecraft.world.entity.GlowSquid entity) {
        super(server, (Squid)entity);
    }

    public net.minecraft.world.entity.GlowSquid getHandle() {
        return (net.minecraft.world.entity.GlowSquid)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftGlowSquid";
    }

    @Override
    public int getDarkTicksRemaining() {
        return this.getHandle().m_147128_();
    }

    @Override
    public void setDarkTicksRemaining(int darkTicksRemaining) {
        Preconditions.checkArgument((darkTicksRemaining >= 0 ? 1 : 0) != 0, (Object)"darkTicksRemaining must be >= 0");
        this.getHandle().m_147119_(darkTicksRemaining);
    }
}

