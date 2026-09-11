/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.decoration.GlowItemFrame
 *  net.minecraft.world.entity.decoration.ItemFrame
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.ItemFrame;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftItemFrame;

public class CraftGlowItemFrame
extends CraftItemFrame
implements org.bukkit.entity.GlowItemFrame {
    public CraftGlowItemFrame(CraftServer server, GlowItemFrame entity) {
        super(server, (ItemFrame)entity);
    }

    public GlowItemFrame getHandle() {
        return (GlowItemFrame)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftGlowItemFrame{item=" + this.getItem() + ", rotation=" + (Object)((Object)this.getRotation()) + "}";
    }
}

