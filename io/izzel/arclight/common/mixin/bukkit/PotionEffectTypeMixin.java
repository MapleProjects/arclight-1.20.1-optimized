/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.bukkit;

import java.util.Arrays;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={PotionEffectType.class}, remap=false)
public class PotionEffectTypeMixin {
    @Shadow
    @Final
    private static PotionEffectType[] byId;

    @Overwrite
    @NotNull
    public static PotionEffectType[] values() {
        int from = byId[0] == null ? 1 : 0;
        int to = byId[byId.length - 1] == null ? byId.length - 1 : byId.length;
        return Arrays.copyOfRange(byId, from, to);
    }
}

