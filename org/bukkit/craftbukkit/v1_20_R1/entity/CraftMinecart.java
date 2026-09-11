/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVehicle;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

public abstract class CraftMinecart
extends CraftVehicle
implements Minecart {
    public CraftMinecart(CraftServer server, AbstractMinecart entity) {
        super(server, (Entity)entity);
    }

    @Override
    public void setDamage(double damage) {
        this.getHandle().m_38109_((float)damage);
    }

    @Override
    public double getDamage() {
        return this.getHandle().m_38169_();
    }

    @Override
    public double getMaxSpeed() {
        return this.getHandle().maxSpeed;
    }

    @Override
    public void setMaxSpeed(double speed) {
        if (speed >= 0.0) {
            this.getHandle().maxSpeed = speed;
        }
    }

    @Override
    public boolean isSlowWhenEmpty() {
        return this.getHandle().slowWhenEmpty;
    }

    @Override
    public void setSlowWhenEmpty(boolean slow) {
        this.getHandle().slowWhenEmpty = slow;
    }

    @Override
    public Vector getFlyingVelocityMod() {
        return this.getHandle().getFlyingVelocityMod();
    }

    @Override
    public void setFlyingVelocityMod(Vector flying) {
        this.getHandle().setFlyingVelocityMod(flying);
    }

    @Override
    public Vector getDerailedVelocityMod() {
        return this.getHandle().getDerailedVelocityMod();
    }

    @Override
    public void setDerailedVelocityMod(Vector derailed) {
        this.getHandle().setDerailedVelocityMod(derailed);
    }

    public AbstractMinecart getHandle() {
        return (AbstractMinecart)this.entity;
    }

    @Override
    public void setDisplayBlock(MaterialData material) {
        if (material != null) {
            BlockState block = CraftMagicNumbers.getBlock(material);
            this.getHandle().m_38146_(block);
        } else {
            this.getHandle().m_38146_(Blocks.f_50016_.m_49966_());
            this.getHandle().m_38138_(false);
        }
    }

    @Override
    public void setDisplayBlockData(BlockData blockData) {
        if (blockData != null) {
            BlockState block = ((CraftBlockData)blockData).getState();
            this.getHandle().m_38146_(block);
        } else {
            this.getHandle().m_38146_(Blocks.f_50016_.m_49966_());
            this.getHandle().m_38138_(false);
        }
    }

    @Override
    public MaterialData getDisplayBlock() {
        BlockState blockData = this.getHandle().m_38178_();
        return CraftMagicNumbers.getMaterial(blockData);
    }

    @Override
    public BlockData getDisplayBlockData() {
        BlockState blockData = this.getHandle().m_38178_();
        return CraftBlockData.fromData(blockData);
    }

    @Override
    public void setDisplayBlockOffset(int offset) {
        this.getHandle().m_38174_(offset);
    }

    @Override
    public int getDisplayBlockOffset() {
        return this.getHandle().m_38183_();
    }
}

