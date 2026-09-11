/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.hoglin.Hoglin
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;
import org.bukkit.entity.Hoglin;

public class CraftHoglin
extends CraftAnimals
implements Hoglin,
CraftEnemy {
    public CraftHoglin(CraftServer server, net.minecraft.world.entity.monster.hoglin.Hoglin entity) {
        super(server, (Animal)entity);
    }

    @Override
    public boolean isImmuneToZombification() {
        return this.getHandle().m_34557_();
    }

    @Override
    public void setImmuneToZombification(boolean flag) {
        this.getHandle().m_34564_(flag);
    }

    @Override
    public boolean isAbleToBeHunted() {
        return this.getHandle().f_34485_;
    }

    @Override
    public void setIsAbleToBeHunted(boolean flag) {
        this.getHandle().f_34485_ = flag;
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState((boolean)this.isConverting(), (Object)"Entity not converting");
        return this.getHandle().f_34484_;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            this.getHandle().f_34484_ = -1;
            this.getHandle().m_34564_(false);
        } else {
            this.getHandle().f_34484_ = time;
        }
    }

    @Override
    public boolean isConverting() {
        return this.getHandle().m_34554_();
    }

    public net.minecraft.world.entity.monster.hoglin.Hoglin getHandle() {
        return (net.minecraft.world.entity.monster.hoglin.Hoglin)this.entity;
    }

    @Override
    public String toString() {
        return "CraftHoglin";
    }
}

