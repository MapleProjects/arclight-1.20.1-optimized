/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.collect.BiMap;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={CraftMagicNumbers.class}, remap=false)
public class CraftMagicNumbersMixin {
    @Redirect(method={"<clinit>"}, at=@At(value="INVOKE", target="Lcom/google/common/collect/BiMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static <K, V> V arclight$skip(BiMap<K, V> instance, K k, V v) {
        if (v == null) {
            return null;
        }
        return (V)instance.put(k, v);
    }
}

