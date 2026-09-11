/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec$MaterialType
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.MaterialBridge;
import io.izzel.arclight.i18n.conf.MaterialPropertySpec;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.legacy.CraftLegacy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CraftLegacy.class}, remap=false)
public class CraftLegacyLegacyMixin {
    @Overwrite
    public static Material valueOf(String name) {
        if (name.startsWith("LEGACY_")) {
            return Material.valueOf(name);
        }
        try {
            Material material = Material.valueOf(name);
            if (material != null && ((MaterialBridge)((Object)material)).bridge$getType() == MaterialPropertySpec.MaterialType.FORGE) {
                return material;
            }
            return Material.valueOf("LEGACY_" + name);
        }
        catch (IllegalArgumentException e) {
            return Material.valueOf("LEGACY_" + name);
        }
    }

    @Overwrite
    public static Material getMaterial(String name) {
        if (name.startsWith("LEGACY_")) {
            return Material.getMaterial(name);
        }
        try {
            Material material = Material.getMaterial(name);
            if (material != null && ((MaterialBridge)((Object)material)).bridge$getType() == MaterialPropertySpec.MaterialType.FORGE) {
                return material;
            }
            return Material.getMaterial("LEGACY_" + name);
        }
        catch (IllegalArgumentException e) {
            return Material.getMaterial("LEGACY_" + name);
        }
    }

    @Overwrite
    public static Material matchMaterial(String name) {
        if (name.startsWith("LEGACY_")) {
            return Material.matchMaterial(name);
        }
        try {
            Material material = Material.matchMaterial(name);
            if (((MaterialBridge)((Object)material)).bridge$getType() == MaterialPropertySpec.MaterialType.FORGE) {
                return material;
            }
            return Material.matchMaterial("LEGACY_" + name);
        }
        catch (IllegalArgumentException e) {
            return Material.matchMaterial("LEGACY_" + name);
        }
    }

    @Overwrite
    public static String name(Material material) {
        if (((MaterialBridge)((Object)material)).bridge$getType() == MaterialPropertySpec.MaterialType.FORGE) {
            return material.name();
        }
        return material.name().replaceAll("^LEGACY_", "");
    }
}

