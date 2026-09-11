/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.PositionImpl
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.PositionImpl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;
import org.bukkit.World;

public final class CraftLocation {
    private CraftLocation() {
    }

    public static Location toBukkit(Vec3 vec3D) {
        return CraftLocation.toBukkit(vec3D, null);
    }

    public static Location toBukkit(Vec3 vec3D, World world) {
        return CraftLocation.toBukkit(vec3D, world, 0.0f, 0.0f);
    }

    public static Location toBukkit(Vec3 vec3D, World world, float yaw, float pitch) {
        return new Location(world, vec3D.m_7096_(), vec3D.m_7098_(), vec3D.m_7094_(), yaw, pitch);
    }

    public static Location toBukkit(BlockPos blockPosition) {
        return CraftLocation.toBukkit(blockPosition, null);
    }

    public static Location toBukkit(BlockPos blockPosition, Level world) {
        return CraftLocation.toBukkit(blockPosition, (World)world.getWorld(), 0.0f, 0.0f);
    }

    public static Location toBukkit(BlockPos blockPosition, World world) {
        return CraftLocation.toBukkit(blockPosition, world, 0.0f, 0.0f);
    }

    public static Location toBukkit(BlockPos blockPosition, World world, float yaw, float pitch) {
        return new Location(world, blockPosition.m_123341_(), blockPosition.m_123342_(), blockPosition.m_123343_(), yaw, pitch);
    }

    public static Location toBukkit(PositionImpl position) {
        return CraftLocation.toBukkit(position, null, 0.0f, 0.0f);
    }

    public static Location toBukkit(PositionImpl position, World world) {
        return CraftLocation.toBukkit(position, world, 0.0f, 0.0f);
    }

    public static Location toBukkit(PositionImpl position, World world, float yaw, float pitch) {
        return new Location(world, position.m_7096_(), position.m_7098_(), position.m_7094_(), yaw, pitch);
    }

    public static BlockPos toBlockPosition(Location location) {
        return new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static PositionImpl toPosition(Location location) {
        return new PositionImpl(location.getX(), location.getY(), location.getZ());
    }

    public static Vec3 toVec3D(Location location) {
        return new Vec3(location.getX(), location.getY(), location.getZ());
    }
}

