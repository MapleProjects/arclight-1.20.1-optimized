/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.raid.Raider
 *  net.minecraft.world.level.LevelAccessor
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelAccessor;
import org.bukkit.Raid;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftRaid;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Raider;

public abstract class CraftRaider
extends CraftMonster
implements Raider {
    public CraftRaider(CraftServer server, net.minecraft.world.entity.raid.Raider entity) {
        super(server, (Monster)entity);
    }

    public net.minecraft.world.entity.raid.Raider getHandle() {
        return (net.minecraft.world.entity.raid.Raider)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftRaider";
    }

    @Override
    public void setRaid(Raid raid) {
        this.getHandle().m_37851_(raid != null ? ((CraftRaid)raid).getHandle() : null);
    }

    @Override
    public Raid getRaid() {
        return this.getHandle().m_37885_() == null ? null : new CraftRaid(this.getHandle().m_37885_());
    }

    @Override
    public void setWave(int wave) {
        Preconditions.checkArgument((wave >= 0 ? 1 : 0) != 0, (Object)"wave must be >= 0");
        this.getHandle().m_37842_(wave);
    }

    @Override
    public int getWave() {
        return this.getHandle().m_37887_();
    }

    @Override
    public Block getPatrolTarget() {
        return this.getHandle().m_33065_() == null ? null : CraftBlock.at((LevelAccessor)this.getHandle().m_9236_(), this.getHandle().m_33065_());
    }

    @Override
    public void setPatrolTarget(Block block) {
        if (block == null) {
            this.getHandle().m_33070_(null);
        } else {
            Preconditions.checkArgument((boolean)block.getWorld().equals(this.getWorld()), (Object)"Block must be in same world");
            this.getHandle().m_33070_(((CraftBlock)block).getPosition());
        }
    }

    @Override
    public boolean isPatrolLeader() {
        return this.getHandle().m_33067_();
    }

    @Override
    public void setPatrolLeader(boolean leader) {
        this.getHandle().m_33075_(leader);
    }

    @Override
    public boolean isCanJoinRaid() {
        return this.getHandle().m_37882_();
    }

    @Override
    public void setCanJoinRaid(boolean join) {
        this.getHandle().m_37897_(join);
    }

    @Override
    public boolean isCelebrating() {
        return this.getHandle().m_37888_();
    }

    @Override
    public void setCelebrating(boolean celebrating) {
        this.getHandle().m_37899_(celebrating);
    }

    @Override
    public int getTicksOutsideRaid() {
        return this.getHandle().m_37889_();
    }

    @Override
    public void setTicksOutsideRaid(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks must be >= 0");
        this.getHandle().m_37863_(ticks);
    }

    @Override
    public Sound getCelebrationSound() {
        return CraftSound.getBukkit(this.getHandle().m_7930_());
    }
}

