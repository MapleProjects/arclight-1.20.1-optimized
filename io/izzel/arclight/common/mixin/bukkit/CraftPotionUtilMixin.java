/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={CraftPotionUtil.class}, remap=false)
public class CraftPotionUtilMixin {
    @Shadow
    @Final
    private static BiMap<PotionType, String> upgradeable;
    @Shadow
    @Final
    private static BiMap<PotionType, String> extendable;
    @Shadow
    @Final
    @Mutable
    private static BiMap<PotionType, String> regular;

    @Overwrite
    public static String fromBukkit(PotionData data) {
        String type = data.isUpgraded() ? (String)upgradeable.get((Object)data.getType()) : (data.isExtended() ? (String)extendable.get((Object)data.getType()) : (String)regular.get((Object)data.getType()));
        Preconditions.checkNotNull((Object)type, (Object)("Unknown potion type from data " + String.valueOf(data)));
        if (type.indexOf(58) != -1) {
            return type;
        }
        return "minecraft:" + type;
    }
}

