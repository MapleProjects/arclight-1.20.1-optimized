/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Mob;
import org.bukkit.loot.LootTable;

public abstract class CraftMob
extends CraftLivingEntity
implements Mob {
    public CraftMob(CraftServer server, net.minecraft.world.entity.Mob entity) {
        super(server, (LivingEntity)entity);
    }

    @Override
    public void setTarget(org.bukkit.entity.LivingEntity target) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot set target during world generation");
        net.minecraft.world.entity.Mob entity = this.getHandle();
        if (target == null) {
            entity.setTarget(null, null, false);
        } else if (target instanceof CraftLivingEntity) {
            entity.setTarget(((CraftLivingEntity)target).getHandle(), null, false);
        }
    }

    @Override
    public CraftLivingEntity getTarget() {
        if (this.getHandle().m_5448_() == null) {
            return null;
        }
        return (CraftLivingEntity)this.getHandle().m_5448_().getBukkitEntity();
    }

    @Override
    public void setAware(boolean aware) {
        this.getHandle().aware = aware;
    }

    @Override
    public boolean isAware() {
        return this.getHandle().aware;
    }

    @Override
    public Sound getAmbientSound() {
        SoundEvent sound = this.getHandle().getAmbientSound0();
        return sound != null ? CraftSound.getBukkit(sound) : null;
    }

    public net.minecraft.world.entity.Mob getHandle() {
        return (net.minecraft.world.entity.Mob)this.entity;
    }

    @Override
    public String toString() {
        return "CraftMob";
    }

    @Override
    public void setLootTable(LootTable table) {
        this.getHandle().f_21355_ = table == null ? null : CraftNamespacedKey.toMinecraft(table.getKey());
    }

    @Override
    public LootTable getLootTable() {
        NamespacedKey key = CraftNamespacedKey.fromMinecraft(this.getHandle().m_5743_());
        return Bukkit.getLootTable(key);
    }

    @Override
    public void setSeed(long seed) {
        this.getHandle().f_21356_ = seed;
    }

    @Override
    public long getSeed() {
        return this.getHandle().f_21356_;
    }
}

