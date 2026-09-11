/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public final class CraftRayTraceResult {
    private CraftRayTraceResult() {
    }

    public static RayTraceResult fromNMS(World world, HitResult nmsHitResult) {
        if (nmsHitResult == null || nmsHitResult.m_6662_() == HitResult.Type.MISS) {
            return null;
        }
        Vec3 nmsHitPos = nmsHitResult.m_82450_();
        Vector hitPosition = new Vector(nmsHitPos.f_82479_, nmsHitPos.f_82480_, nmsHitPos.f_82481_);
        BlockFace hitBlockFace = null;
        if (nmsHitResult.m_6662_() == HitResult.Type.ENTITY) {
            CraftEntity hitEntity = ((EntityHitResult)nmsHitResult).m_82443_().getBukkitEntity();
            return new RayTraceResult(hitPosition, hitEntity, null);
        }
        Block hitBlock = null;
        BlockPos nmsBlockPos = null;
        if (nmsHitResult.m_6662_() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult)nmsHitResult;
            hitBlockFace = CraftBlock.notchToBlockFace(blockHitResult.m_82434_());
            nmsBlockPos = blockHitResult.m_82425_();
        }
        if (nmsBlockPos != null && world != null) {
            hitBlock = world.getBlockAt(nmsBlockPos.m_123341_(), nmsBlockPos.m_123342_(), nmsBlockPos.m_123343_());
        }
        return new RayTraceResult(hitPosition, hitBlock, hitBlockFace);
    }
}

