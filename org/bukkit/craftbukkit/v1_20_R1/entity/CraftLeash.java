/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.entity.decoration.LeashFenceKnotEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHanging;
import org.bukkit.entity.LeashHitch;

public class CraftLeash
extends CraftHanging
implements LeashHitch {
    public CraftLeash(CraftServer server, LeashFenceKnotEntity entity) {
        super(server, (HangingEntity)entity);
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        Preconditions.checkArgument((face == BlockFace.SELF ? 1 : 0) != 0, (String)"%s is not a valid facing direction", (Object)((Object)face));
        return force || this.getHandle().generation || this.getHandle().m_7088_();
    }

    @Override
    public BlockFace getFacing() {
        return BlockFace.SELF;
    }

    public LeashFenceKnotEntity getHandle() {
        return (LeashFenceKnotEntity)this.entity;
    }

    @Override
    public String toString() {
        return "CraftLeash";
    }
}

