/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.world.phys.Vec3;
import org.bukkit.util.Vector;

public final class CraftVector {
    private CraftVector() {
    }

    public static Vector toBukkit(Vec3 nms) {
        return new Vector(nms.f_82479_, nms.f_82480_, nms.f_82481_);
    }

    public static Vec3 toNMS(Vector bukkit) {
        return new Vec3(bukkit.getX(), bukkit.getY(), bukkit.getZ());
    }
}

