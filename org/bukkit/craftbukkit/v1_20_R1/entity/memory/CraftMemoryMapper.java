/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.GlobalPos
 *  net.minecraft.resources.ResourceKey
 */
package org.bukkit.craftbukkit.v1_20_R1.entity.memory;

import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;

public final class CraftMemoryMapper {
    private CraftMemoryMapper() {
    }

    public static Object fromNms(Object object) {
        if (object instanceof GlobalPos) {
            return CraftMemoryMapper.fromNms((GlobalPos)object);
        }
        if (object instanceof Long) {
            return (Long)object;
        }
        if (object instanceof UUID) {
            return (UUID)object;
        }
        if (object instanceof Boolean) {
            return (Boolean)object;
        }
        if (object instanceof Integer) {
            return (Integer)object;
        }
        throw new UnsupportedOperationException("Do not know how to map " + object);
    }

    public static Object toNms(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Location) {
            return CraftMemoryMapper.toNms((Location)object);
        }
        if (object instanceof Long) {
            return (Long)object;
        }
        if (object instanceof UUID) {
            return (UUID)object;
        }
        if (object instanceof Boolean) {
            return (Boolean)object;
        }
        if (object instanceof Integer) {
            return (Integer)object;
        }
        throw new UnsupportedOperationException("Do not know how to map " + object);
    }

    public static Location fromNms(GlobalPos globalPos) {
        return new Location(((CraftServer)Bukkit.getServer()).getServer().m_129880_(globalPos.m_122640_()).getWorld(), globalPos.m_122646_().m_123341_(), globalPos.m_122646_().m_123342_(), globalPos.m_122646_().m_123343_());
    }

    public static GlobalPos toNms(Location location) {
        return GlobalPos.m_122643_((ResourceKey)((CraftWorld)location.getWorld()).getHandle().m_46472_(), (BlockPos)BlockPos.m_274561_((double)location.getX(), (double)location.getY(), (double)location.getZ()));
    }
}

