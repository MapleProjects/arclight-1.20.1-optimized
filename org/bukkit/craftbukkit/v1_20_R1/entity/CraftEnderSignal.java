/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.EyeOfEnder
 *  net.minecraft.world.item.Items
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.Items;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.EnderSignal;
import org.bukkit.inventory.ItemStack;

public class CraftEnderSignal
extends CraftEntity
implements EnderSignal {
    public CraftEnderSignal(CraftServer server, EyeOfEnder entity) {
        super(server, (Entity)entity);
    }

    public EyeOfEnder getHandle() {
        return (EyeOfEnder)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderSignal";
    }

    @Override
    public Location getTargetLocation() {
        return new Location(this.getWorld(), this.getHandle().f_36950_, this.getHandle().f_36951_, this.getHandle().f_36952_, this.getHandle().m_146908_(), this.getHandle().m_146909_());
    }

    @Override
    public void setTargetLocation(Location location) {
        Preconditions.checkArgument((boolean)this.getWorld().equals(location.getWorld()), (Object)"Cannot target EnderSignal across worlds");
        this.getHandle().m_36967_(CraftLocation.toBlockPosition(location));
    }

    @Override
    public boolean getDropItem() {
        return this.getHandle().f_36954_;
    }

    @Override
    public void setDropItem(boolean shouldDropItem) {
        this.getHandle().f_36954_ = shouldDropItem;
    }

    @Override
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(this.getHandle().m_7846_());
    }

    @Override
    public void setItem(ItemStack item) {
        this.getHandle().m_36972_(item != null ? CraftItemStack.asNMSCopy(item) : Items.f_42545_.m_7968_());
    }

    @Override
    public int getDespawnTimer() {
        return this.getHandle().f_36953_;
    }

    @Override
    public void setDespawnTimer(int time) {
        this.getHandle().f_36953_ = time;
    }
}

