/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.animal.allay.Allay
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.allay.Allay;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.inventory.Inventory;

public class CraftAllay
extends CraftCreature
implements org.bukkit.entity.Allay {
    public CraftAllay(CraftServer server, Allay entity) {
        super(server, (PathfinderMob)entity);
    }

    public Allay getHandle() {
        return (Allay)this.entity;
    }

    @Override
    public String toString() {
        return "CraftAllay";
    }

    @Override
    public Inventory getInventory() {
        return new CraftInventory((Container)this.getHandle().m_35311_());
    }

    @Override
    public boolean canDuplicate() {
        return this.getHandle().m_218324_();
    }

    @Override
    public void setCanDuplicate(boolean canDuplicate) {
        this.getHandle().setCanDuplicate(canDuplicate);
    }

    @Override
    public long getDuplicationCooldown() {
        return this.getHandle().f_238791_;
    }

    @Override
    public void setDuplicationCooldown(long l) {
        this.getHandle().f_238791_ = l;
    }

    @Override
    public void resetDuplicationCooldown() {
        this.getHandle().m_239811_();
    }

    @Override
    public boolean isDancing() {
        return this.getHandle().m_239559_();
    }

    @Override
    public void startDancing(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((boolean)location.getBlock().getType().equals(Material.JUKEBOX), (Object)"The Block in the Location need to be a JukeBox");
        this.getHandle().m_240101_(CraftLocation.toBlockPosition(location), true);
    }

    @Override
    public void startDancing() {
        this.getHandle().forceDancing = true;
        this.getHandle().m_240177_(true);
    }

    @Override
    public void stopDancing() {
        this.getHandle().forceDancing = false;
        this.getHandle().f_238682_ = null;
        this.getHandle().m_240101_(null, false);
    }

    @Override
    public org.bukkit.entity.Allay duplicateAllay() {
        Allay nmsAllay = this.getHandle().duplicateAllay();
        return nmsAllay != null ? (org.bukkit.entity.Allay)((Object)nmsAllay.getBukkitEntity()) : null;
    }

    @Override
    public Location getJukebox() {
        BlockPos nmsJukeboxPos = this.getHandle().f_238682_;
        return nmsJukeboxPos != null ? CraftLocation.toBukkit(nmsJukeboxPos, this.getWorld()) : null;
    }
}

