/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Panda
 *  net.minecraft.world.entity.animal.Panda$Gene
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Panda;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Panda;

public class CraftPanda
extends CraftAnimals
implements Panda {
    public CraftPanda(CraftServer server, net.minecraft.world.entity.animal.Panda entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.Panda getHandle() {
        return (net.minecraft.world.entity.animal.Panda)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftPanda";
    }

    @Override
    public Panda.Gene getMainGene() {
        return CraftPanda.fromNms(this.getHandle().m_29154_());
    }

    @Override
    public void setMainGene(Panda.Gene gene) {
        this.getHandle().m_29099_(CraftPanda.toNms(gene));
    }

    @Override
    public Panda.Gene getHiddenGene() {
        return CraftPanda.fromNms(this.getHandle().m_29155_());
    }

    @Override
    public void setHiddenGene(Panda.Gene gene) {
        this.getHandle().m_29116_(CraftPanda.toNms(gene));
    }

    @Override
    public boolean isRolling() {
        return this.getHandle().m_29156_();
    }

    @Override
    public void setRolling(boolean flag) {
        this.getHandle().m_29222_(flag);
    }

    @Override
    public boolean isSneezing() {
        return this.getHandle().m_29149_();
    }

    @Override
    public void setSneezing(boolean flag) {
        this.getHandle().m_29220_(flag);
    }

    @Override
    public boolean isSitting() {
        return this.getHandle().m_29150_();
    }

    @Override
    public void setSitting(boolean flag) {
        this.getHandle().m_29208_(flag);
    }

    @Override
    public boolean isOnBack() {
        return this.getHandle().m_29151_();
    }

    @Override
    public void setOnBack(boolean flag) {
        this.getHandle().m_29212_(flag);
    }

    @Override
    public boolean isEating() {
        return this.getHandle().m_29152_();
    }

    @Override
    public void setEating(boolean flag) {
        this.getHandle().m_29216_(flag);
    }

    @Override
    public boolean isScared() {
        return this.getHandle().m_29165_();
    }

    @Override
    public int getUnhappyTicks() {
        return this.getHandle().m_29148_();
    }

    public static Panda.Gene fromNms(Panda.Gene gene) {
        Preconditions.checkArgument((gene != null ? 1 : 0) != 0, (Object)"Gene may not be null");
        return Panda.Gene.values()[gene.ordinal()];
    }

    public static Panda.Gene toNms(Panda.Gene gene) {
        Preconditions.checkArgument((gene != null ? 1 : 0) != 0, (Object)"Gene may not be null");
        return Panda.Gene.values()[gene.ordinal()];
    }
}

