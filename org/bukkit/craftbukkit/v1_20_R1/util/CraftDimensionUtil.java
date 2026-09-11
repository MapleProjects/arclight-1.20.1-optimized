/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.dimension.LevelStem
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;

public class CraftDimensionUtil {
    private CraftDimensionUtil() {
    }

    public static ResourceKey<Level> getMainDimensionKey(Level world) {
        ResourceKey typeKey = world.getTypeKey();
        if (typeKey == LevelStem.f_63971_) {
            return Level.f_46428_;
        }
        if (typeKey == LevelStem.f_63972_) {
            return Level.f_46429_;
        }
        if (typeKey == LevelStem.f_63973_) {
            return Level.f_46430_;
        }
        return world.m_46472_();
    }
}

