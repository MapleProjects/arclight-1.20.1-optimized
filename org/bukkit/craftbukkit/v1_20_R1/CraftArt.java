/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.ImmutableBiMap
 *  com.google.common.collect.ImmutableBiMap$Builder
 *  net.minecraft.core.Holder
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.entity.decoration.PaintingVariant
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.bukkit.Art;

public class CraftArt {
    private static final BiMap<Holder<PaintingVariant>, Art> artwork;

    static {
        ImmutableBiMap.Builder artworkBuilder = ImmutableBiMap.builder();
        for (ResourceKey key : BuiltInRegistries.f_257051_.m_214010_()) {
            artworkBuilder.put((Object)BuiltInRegistries.f_257051_.m_246971_(key), (Object)Art.getByName(key.m_135782_().m_135815_()));
        }
        artwork = artworkBuilder.build();
    }

    public static Art NotchToBukkit(Holder<PaintingVariant> art) {
        Art bukkit = (Art)artwork.get(art);
        Preconditions.checkArgument((bukkit != null ? 1 : 0) != 0);
        return bukkit;
    }

    public static Holder<PaintingVariant> BukkitToNotch(Art art) {
        Holder nms = (Holder)artwork.inverse().get((Object)art);
        Preconditions.checkArgument((nms != null ? 1 : 0) != 0);
        return nms;
    }
}

