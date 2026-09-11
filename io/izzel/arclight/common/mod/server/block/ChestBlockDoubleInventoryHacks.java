/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraft.world.CompoundContainer
 */
package io.izzel.arclight.common.mod.server.block;

import io.izzel.arclight.api.Unsafe;
import java.lang.reflect.Field;
import net.minecraft.world.CompoundContainer;

public class ChestBlockDoubleInventoryHacks {
    private static final Class<?> cl;
    private static final long offset;

    public static CompoundContainer get(Object obj) {
        return (CompoundContainer)Unsafe.getObject((Object)obj, (long)offset);
    }

    public static boolean isInstance(Object obj) {
        return cl.isInstance(obj);
    }

    static {
        try {
            cl = Class.forName("net.minecraft.world.level.block.ChestBlock$2$1");
            Field field = cl.getDeclaredField("inventorylargechest");
            offset = Unsafe.objectFieldOffset((Field)field);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

