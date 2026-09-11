/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.resources.ResourceLocation;
import org.bukkit.NamespacedKey;

public final class CraftNamespacedKey {
    public static NamespacedKey fromStringOrNull(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        ResourceLocation minecraft = ResourceLocation.m_135820_((String)string);
        return minecraft == null ? null : CraftNamespacedKey.fromMinecraft(minecraft);
    }

    public static NamespacedKey fromString(String string) {
        return CraftNamespacedKey.fromMinecraft(new ResourceLocation(string));
    }

    public static NamespacedKey fromMinecraft(ResourceLocation minecraft) {
        return new NamespacedKey(minecraft.m_135827_(), minecraft.m_135815_());
    }

    public static ResourceLocation toMinecraft(NamespacedKey key) {
        return new ResourceLocation(key.getNamespace(), key.getKey());
    }
}

