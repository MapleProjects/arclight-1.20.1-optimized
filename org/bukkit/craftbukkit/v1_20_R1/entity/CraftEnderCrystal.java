/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.boss.enderdragon.EndCrystal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.EnderCrystal;

public class CraftEnderCrystal
extends CraftEntity
implements EnderCrystal {
    public CraftEnderCrystal(CraftServer server, EndCrystal entity) {
        super(server, (Entity)entity);
    }

    @Override
    public boolean isShowingBottom() {
        return this.getHandle().m_31065_();
    }

    @Override
    public void setShowingBottom(boolean showing) {
        this.getHandle().m_31056_(showing);
    }

    @Override
    public Location getBeamTarget() {
        BlockPos pos = this.getHandle().m_31064_();
        return pos == null ? null : CraftLocation.toBukkit(pos, this.getWorld());
    }

    @Override
    public void setBeamTarget(Location location) {
        if (location == null) {
            this.getHandle().m_31052_(null);
        } else {
            if (location.getWorld() != this.getWorld()) {
                throw new IllegalArgumentException("Cannot set beam target location to different world");
            }
            this.getHandle().m_31052_(CraftLocation.toBlockPosition(location));
        }
    }

    public EndCrystal getHandle() {
        return (EndCrystal)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderCrystal";
    }
}

