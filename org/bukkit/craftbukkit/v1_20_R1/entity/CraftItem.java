/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import java.util.UUID;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class CraftItem
extends CraftEntity
implements Item {
    private final ItemEntity item;

    public CraftItem(CraftServer server, Entity entity, ItemEntity item) {
        super(server, entity);
        this.item = item;
    }

    public CraftItem(CraftServer server, ItemEntity entity) {
        this(server, (Entity)entity, entity);
    }

    @Override
    public ItemStack getItemStack() {
        return CraftItemStack.asCraftMirror(this.item.m_32055_());
    }

    @Override
    public void setItemStack(ItemStack stack) {
        this.item.m_32045_(CraftItemStack.asNMSCopy(stack));
    }

    @Override
    public int getPickupDelay() {
        return this.item.f_31986_;
    }

    @Override
    public void setPickupDelay(int delay) {
        this.item.f_31986_ = Math.min(delay, Short.MAX_VALUE);
    }

    @Override
    public void setUnlimitedLifetime(boolean unlimited) {
        this.item.f_31985_ = unlimited ? Short.MIN_VALUE : this.getTicksLived();
    }

    @Override
    public boolean isUnlimitedLifetime() {
        return this.item.f_31985_ == Short.MIN_VALUE;
    }

    @Override
    public void setTicksLived(int value) {
        super.setTicksLived(value);
        if (!this.isUnlimitedLifetime()) {
            this.item.f_31985_ = value;
        }
    }

    @Override
    public void setOwner(UUID uuid) {
        this.item.m_266426_(uuid);
    }

    @Override
    public UUID getOwner() {
        return this.item.f_265881_;
    }

    @Override
    public void setThrower(UUID uuid) {
        this.item.m_32052_(uuid);
    }

    @Override
    public UUID getThrower() {
        return this.item.f_31988_;
    }

    @Override
    public String toString() {
        return "CraftItem";
    }
}

