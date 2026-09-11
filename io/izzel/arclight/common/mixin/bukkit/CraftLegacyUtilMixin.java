/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.bukkit;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLegacy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CraftLegacy.class}, remap=false)
public class CraftLegacyUtilMixin {
    private static Material[] moddedMaterials;
    private static int offset;

    @Overwrite
    public static Material[] modern_values() {
        if (moddedMaterials == null) {
            int origin = Material.values().length;
            moddedMaterials = (Material[])Arrays.stream(Material.values()).filter(it -> !it.isLegacy()).toArray(Material[]::new);
            offset = origin - moddedMaterials.length;
        }
        return Arrays.copyOf(moddedMaterials, moddedMaterials.length);
    }

    @Overwrite
    public static int modern_ordinal(Material material) {
        if (moddedMaterials == null) {
            CraftLegacyUtilMixin.modern_values();
        }
        if (material.isLegacy()) {
            throw new NoSuchFieldError("Legacy field ordinal: " + String.valueOf(material));
        }
        int ordinal = material.ordinal();
        return ordinal < Material.LEGACY_AIR.ordinal() ? ordinal : ordinal - offset;
    }
}

