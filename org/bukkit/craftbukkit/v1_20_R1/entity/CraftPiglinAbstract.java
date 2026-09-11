/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.piglin.AbstractPiglin
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.PiglinAbstract;

public class CraftPiglinAbstract
extends CraftMonster
implements PiglinAbstract {
    public CraftPiglinAbstract(CraftServer server, AbstractPiglin entity) {
        super(server, (Monster)entity);
    }

    @Override
    public boolean isImmuneToZombification() {
        return this.getHandle().m_34665_();
    }

    @Override
    public void setImmuneToZombification(boolean flag) {
        this.getHandle().m_34670_(flag);
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState((boolean)this.isConverting(), (Object)"Entity not converting");
        return this.getHandle().f_34649_;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            this.getHandle().f_34649_ = -1;
            this.getHandle().m_34670_(false);
        } else {
            this.getHandle().f_34649_ = time;
        }
    }

    @Override
    public boolean isConverting() {
        return this.getHandle().m_34666_();
    }

    @Override
    public boolean isBaby() {
        return this.getHandle().m_6162_();
    }

    @Override
    public void setBaby(boolean flag) {
        this.getHandle().m_6863_(flag);
    }

    @Override
    public int getAge() {
        return this.getHandle().m_6162_() ? -1 : 0;
    }

    @Override
    public void setAge(int i) {
        this.getHandle().m_6863_(i < 0);
    }

    @Override
    public void setAgeLock(boolean b) {
    }

    @Override
    public boolean getAgeLock() {
        return false;
    }

    @Override
    public void setBaby() {
        this.getHandle().m_6863_(true);
    }

    @Override
    public void setAdult() {
        this.getHandle().m_6863_(false);
    }

    @Override
    public boolean isAdult() {
        return !this.getHandle().m_6162_();
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public void setBreed(boolean b) {
    }

    public AbstractPiglin getHandle() {
        return (AbstractPiglin)super.getHandle();
    }
}

