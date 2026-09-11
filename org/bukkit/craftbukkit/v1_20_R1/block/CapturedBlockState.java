/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;

public final class CapturedBlockState
extends CraftBlockState {
    private final boolean treeBlock;

    public CapturedBlockState(Block block, int flag, boolean treeBlock) {
        super(block, flag);
        this.treeBlock = treeBlock;
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);
        if (this.treeBlock && this.getType() == Material.BEE_NEST) {
            ServerLevel generatoraccessseed = this.world.getHandle();
            BlockPos blockposition1 = this.getPosition();
            RandomSource random = generatoraccessseed.m_213780_();
            BlockEntity tileentity = generatoraccessseed.m_7702_(blockposition1);
            if (tileentity instanceof BeehiveBlockEntity) {
                BeehiveBlockEntity tileentitybeehive = (BeehiveBlockEntity)tileentity;
                int j = 2 + random.m_188503_(2);
                int k = 0;
                while (k < j) {
                    Bee entitybee = new Bee(EntityType.f_20550_, (Level)generatoraccessseed.getMinecraftWorld());
                    tileentitybeehive.m_58744_((Entity)entitybee, false, random.m_188503_(599));
                    ++k;
                }
            }
        }
        return result;
    }

    public static CapturedBlockState getBlockState(Level world, BlockPos pos, int flag) {
        return new CapturedBlockState(world.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()), flag, false);
    }

    public static CapturedBlockState getTreeBlockState(Level world, BlockPos pos, int flag) {
        return new CapturedBlockState(world.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()), flag, true);
    }
}

