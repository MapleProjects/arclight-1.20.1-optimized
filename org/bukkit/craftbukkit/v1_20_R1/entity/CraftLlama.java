/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.animal.horse.AbstractChestedHorse
 *  net.minecraft.world.entity.animal.horse.Llama
 *  net.minecraft.world.entity.animal.horse.Llama$Variant
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftChestedHorse;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryLlama;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.LlamaInventory;

public class CraftLlama
extends CraftChestedHorse
implements org.bukkit.entity.Llama {
    public CraftLlama(CraftServer server, Llama entity) {
        super(server, (AbstractChestedHorse)entity);
    }

    public Llama getHandle() {
        return (Llama)super.getHandle();
    }

    @Override
    public Llama.Color getColor() {
        return Llama.Color.values()[this.getHandle().m_28554_().ordinal()];
    }

    @Override
    public void setColor(Llama.Color color) {
        Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"color");
        this.getHandle().m_28464_(Llama.Variant.m_262458_((int)color.ordinal()));
    }

    @Override
    public LlamaInventory getInventory() {
        return new CraftInventoryLlama((Container)this.getHandle().f_30520_);
    }

    @Override
    public int getStrength() {
        return this.getHandle().m_30823_();
    }

    @Override
    public void setStrength(int strength) {
        Preconditions.checkArgument((1 <= strength && strength <= 5 ? 1 : 0) != 0, (Object)"strength must be [1,5]");
        if (strength == this.getStrength()) {
            return;
        }
        this.getHandle().setStrengthPublic(strength);
        this.getHandle().m_30625_();
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.LLAMA;
    }

    @Override
    public String toString() {
        return "CraftLlama";
    }
}

