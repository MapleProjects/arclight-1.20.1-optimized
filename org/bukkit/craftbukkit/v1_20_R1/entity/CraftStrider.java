/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Strider
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Strider;

public class CraftStrider
extends CraftAnimals
implements Strider {
    public CraftStrider(CraftServer server, net.minecraft.world.entity.monster.Strider entity) {
        super(server, (Animal)entity);
    }

    @Override
    public boolean isShivering() {
        return this.getHandle().m_33935_();
    }

    @Override
    public void setShivering(boolean shivering) {
        this.getHandle().m_33951_(shivering);
    }

    @Override
    public boolean hasSaddle() {
        return this.getHandle().m_6254_();
    }

    @Override
    public void setSaddle(boolean saddled) {
        this.getHandle().f_33857_.m_20849_(saddled);
    }

    @Override
    public int getBoostTicks() {
        return this.getHandle().f_33857_.f_20834_ ? this.getHandle().f_33857_.m_274397_() : 0;
    }

    @Override
    public void setBoostTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks must be >= 0");
        this.getHandle().f_33857_.setBoostTicks(ticks);
    }

    @Override
    public int getCurrentBoostTicks() {
        return this.getHandle().f_33857_.f_20834_ ? this.getHandle().f_33857_.f_20835_ : 0;
    }

    @Override
    public void setCurrentBoostTicks(int ticks) {
        if (!this.getHandle().f_33857_.f_20834_) {
            return;
        }
        int max = this.getHandle().f_33857_.m_274397_();
        Preconditions.checkArgument((ticks >= 0 && ticks <= max ? 1 : 0) != 0, (String)"boost ticks must not exceed 0 or %d (inclusive)", (int)max);
        this.getHandle().f_33857_.f_20835_ = ticks;
    }

    @Override
    public Material getSteerMaterial() {
        return Material.WARPED_FUNGUS_ON_A_STICK;
    }

    public net.minecraft.world.entity.monster.Strider getHandle() {
        return (net.minecraft.world.entity.monster.Strider)this.entity;
    }

    @Override
    public String toString() {
        return "CraftStrider";
    }
}

