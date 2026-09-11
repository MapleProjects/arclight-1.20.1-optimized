/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Bee
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.Bee;

public class CraftBee
extends CraftAnimals
implements Bee {
    public CraftBee(CraftServer server, net.minecraft.world.entity.animal.Bee entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.Bee getHandle() {
        return (net.minecraft.world.entity.animal.Bee)this.entity;
    }

    @Override
    public String toString() {
        return "CraftBee";
    }

    @Override
    public Location getHive() {
        BlockPos hive = this.getHandle().m_27855_();
        return hive == null ? null : CraftLocation.toBukkit(hive, this.getWorld());
    }

    @Override
    public void setHive(Location location) {
        Preconditions.checkArgument((location == null || this.getWorld().equals(location.getWorld()) ? 1 : 0) != 0, (Object)"Hive must be in same world");
        this.getHandle().f_27698_ = location == null ? null : CraftLocation.toBlockPosition(location);
    }

    @Override
    public Location getFlower() {
        BlockPos flower = this.getHandle().m_27851_();
        return flower == null ? null : CraftLocation.toBukkit(flower, this.getWorld());
    }

    @Override
    public void setFlower(Location location) {
        Preconditions.checkArgument((location == null || this.getWorld().equals(location.getWorld()) ? 1 : 0) != 0, (Object)"Flower must be in same world");
        this.getHandle().m_27876_(location == null ? null : CraftLocation.toBlockPosition(location));
    }

    @Override
    public boolean hasNectar() {
        return this.getHandle().m_27856_();
    }

    @Override
    public void setHasNectar(boolean nectar) {
        this.getHandle().m_27919_(nectar);
    }

    @Override
    public boolean hasStung() {
        return this.getHandle().m_27857_();
    }

    @Override
    public void setHasStung(boolean stung) {
        this.getHandle().m_27925_(stung);
    }

    @Override
    public int getAnger() {
        return this.getHandle().m_6784_();
    }

    @Override
    public void setAnger(int anger) {
        this.getHandle().m_7870_(anger);
    }

    @Override
    public int getCannotEnterHiveTicks() {
        return this.getHandle().f_27711_;
    }

    @Override
    public void setCannotEnterHiveTicks(int ticks) {
        this.getHandle().m_27915_(ticks);
    }
}

