/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.boss.EnderDragonPart
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.boss.EnderDragonPart;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftComplexPart;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;

public class CraftEnderDragonPart
extends CraftComplexPart
implements org.bukkit.entity.EnderDragonPart {
    public CraftEnderDragonPart(CraftServer server, EnderDragonPart entity) {
        super(server, entity);
    }

    @Override
    public EnderDragon getParent() {
        return (EnderDragon)super.getParent();
    }

    @Override
    public EnderDragonPart getHandle() {
        return (EnderDragonPart)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderDragonPart";
    }

    @Override
    public void damage(double amount) {
        this.getParent().damage(amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.getParent().damage(amount, source);
    }

    @Override
    public double getHealth() {
        return this.getParent().getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.getParent().setHealth(health);
    }

    @Override
    public double getAbsorptionAmount() {
        return this.getParent().getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        this.getParent().setAbsorptionAmount(amount);
    }

    @Override
    public double getMaxHealth() {
        return this.getParent().getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        this.getParent().setMaxHealth(health);
    }

    @Override
    public void resetMaxHealth() {
        this.getParent().resetMaxHealth();
    }
}

