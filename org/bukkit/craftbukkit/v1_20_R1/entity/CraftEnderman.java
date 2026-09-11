/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.EnderMan
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.material.MaterialData;

public class CraftEnderman
extends CraftMonster
implements Enderman {
    public CraftEnderman(CraftServer server, EnderMan entity) {
        super(server, (Monster)entity);
    }

    @Override
    public MaterialData getCarriedMaterial() {
        BlockState blockData = this.getHandle().m_32530_();
        return blockData == null ? Material.AIR.getNewData((byte)0) : CraftMagicNumbers.getMaterial(blockData);
    }

    @Override
    public BlockData getCarriedBlock() {
        BlockState blockData = this.getHandle().m_32530_();
        return blockData == null ? null : CraftBlockData.fromData(blockData);
    }

    @Override
    public void setCarriedMaterial(MaterialData data) {
        this.getHandle().m_32521_(CraftMagicNumbers.getBlock(data));
    }

    @Override
    public void setCarriedBlock(BlockData blockData) {
        this.getHandle().m_32521_(blockData == null ? null : ((CraftBlockData)blockData).getState());
    }

    public EnderMan getHandle() {
        return (EnderMan)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderman";
    }

    @Override
    public boolean teleport() {
        return this.getHandle().m_32529_();
    }

    @Override
    public boolean teleportTowards(Entity entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"entity cannot be null");
        return this.getHandle().m_32500_(((CraftEntity)entity).getHandle());
    }
}

