/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.level.block.entity.SculkShriekerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import org.bukkit.World;
import org.bukkit.block.SculkShrieker;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CraftSculkShrieker
extends CraftBlockEntityState<SculkShriekerBlockEntity>
implements SculkShrieker {
    public CraftSculkShrieker(World world, SculkShriekerBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public int getWarningLevel() {
        return ((SculkShriekerBlockEntity)this.getSnapshot()).f_222831_;
    }

    @Override
    public void setWarningLevel(int level) {
        ((SculkShriekerBlockEntity)this.getSnapshot()).f_222831_ = level;
    }

    @Override
    public void tryShriek(Player player) {
        this.requirePlaced();
        ServerPlayer entityPlayer = player == null ? null : ((CraftPlayer)player).getHandle();
        ((SculkShriekerBlockEntity)this.getTileEntity()).m_222841_(this.world.getHandle(), entityPlayer);
    }
}

