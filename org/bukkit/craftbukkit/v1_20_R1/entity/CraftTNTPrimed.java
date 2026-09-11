/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

public class CraftTNTPrimed
extends CraftEntity
implements TNTPrimed {
    public CraftTNTPrimed(CraftServer server, PrimedTnt entity) {
        super(server, (net.minecraft.world.entity.Entity)entity);
    }

    @Override
    public float getYield() {
        return this.getHandle().yield;
    }

    @Override
    public boolean isIncendiary() {
        return this.getHandle().isIncendiary;
    }

    @Override
    public void setIsIncendiary(boolean isIncendiary) {
        this.getHandle().isIncendiary = isIncendiary;
    }

    @Override
    public void setYield(float yield) {
        this.getHandle().yield = yield;
    }

    @Override
    public int getFuseTicks() {
        return this.getHandle().m_32100_();
    }

    @Override
    public void setFuseTicks(int fuseTicks) {
        this.getHandle().m_32085_(fuseTicks);
    }

    public PrimedTnt getHandle() {
        return (PrimedTnt)this.entity;
    }

    @Override
    public String toString() {
        return "CraftTNTPrimed";
    }

    @Override
    public Entity getSource() {
        LivingEntity source = this.getHandle().m_19749_();
        return source != null ? source.getBukkitEntity() : null;
    }

    @Override
    public void setSource(Entity source) {
        this.getHandle().f_32072_ = source instanceof org.bukkit.entity.LivingEntity ? ((CraftLivingEntity)source).getHandle() : null;
    }
}

