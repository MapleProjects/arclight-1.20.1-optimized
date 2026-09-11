/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.boss.EnderDragonPart
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftDragonBattle;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMob;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.EnderDragon;

public class CraftEnderDragon
extends CraftMob
implements org.bukkit.entity.EnderDragon,
CraftEnemy {
    public CraftEnderDragon(CraftServer server, EnderDragon entity) {
        super(server, (Mob)entity);
    }

    @Override
    public Set<ComplexEntityPart> getParts() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        EnderDragonPart[] enderDragonPartArray = this.getHandle().f_31089_;
        int n = this.getHandle().f_31089_.length;
        int n2 = 0;
        while (n2 < n) {
            EnderDragonPart part = enderDragonPartArray[n2];
            builder.add((Object)((ComplexEntityPart)((Object)part.getBukkitEntity())));
            ++n2;
        }
        return builder.build();
    }

    public EnderDragon getHandle() {
        return (EnderDragon)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderDragon";
    }

    @Override
    public EnderDragon.Phase getPhase() {
        return EnderDragon.Phase.values()[(Integer)this.getHandle().m_20088_().m_135370_(EnderDragon.f_31067_)];
    }

    @Override
    public void setPhase(EnderDragon.Phase phase) {
        this.getHandle().m_31157_().m_31416_(CraftEnderDragon.getMinecraftPhase(phase));
    }

    public static EnderDragon.Phase getBukkitPhase(EnderDragonPhase phase) {
        return EnderDragon.Phase.values()[phase.m_31405_()];
    }

    public static EnderDragonPhase getMinecraftPhase(EnderDragon.Phase phase) {
        return EnderDragonPhase.m_31398_((int)phase.ordinal());
    }

    @Override
    public BossBar getBossBar() {
        DragonBattle battle = this.getDragonBattle();
        return battle != null ? battle.getBossBar() : null;
    }

    @Override
    public DragonBattle getDragonBattle() {
        return this.getHandle().m_31158_() != null ? new CraftDragonBattle(this.getHandle().m_31158_()) : null;
    }

    @Override
    public int getDeathAnimationTicks() {
        return this.getHandle().f_31084_;
    }
}

