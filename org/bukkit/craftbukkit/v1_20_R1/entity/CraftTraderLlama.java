/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.Llama
 *  net.minecraft.world.entity.animal.horse.TraderLlama
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.horse.Llama;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLlama;
import org.bukkit.entity.TraderLlama;

public class CraftTraderLlama
extends CraftLlama
implements TraderLlama {
    public CraftTraderLlama(CraftServer server, net.minecraft.world.entity.animal.horse.TraderLlama entity) {
        super(server, (Llama)entity);
    }

    public net.minecraft.world.entity.animal.horse.TraderLlama getHandle() {
        return (net.minecraft.world.entity.animal.horse.TraderLlama)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftTraderLlama";
    }
}

