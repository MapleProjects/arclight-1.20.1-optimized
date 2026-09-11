/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.level.Level
 */
package io.izzel.arclight.common.bridge.core.util;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;

public interface IWorldPosCallableBridge {
    default public Level bridge$getWorld() {
        return ((ContainerLevelAccess)this).m_6721_((a, b) -> a).orElse(null);
    }

    default public BlockPos bridge$getPosition() {
        return ((ContainerLevelAccess)this).m_6721_((a, b) -> b).orElse(null);
    }

    default public Location bridge$getLocation() {
        BlockPos blockPos = this.bridge$getPosition();
        if (blockPos == null) {
            return null;
        }
        Level level = this.bridge$getWorld();
        CraftWorld world = level == null ? null : ((WorldBridge)level).bridge$getWorld();
        return new Location(world, blockPos.m_123341_(), blockPos.m_123342_(), blockPos.m_123343_());
    }
}

