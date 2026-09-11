/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.MobCategory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import net.minecraft.world.entity.MobCategory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftSpawnCategory;
import org.bukkit.entity.SpawnCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftSpawnCategory.class}, remap=false)
public class CraftSpawnCategoryMixin {
    @Overwrite
    public static boolean isValidForLimits(SpawnCategory spawnCategory) {
        return spawnCategory != null && spawnCategory.ordinal() < SpawnCategory.MISC.ordinal();
    }

    @Inject(method={"toBukkit"}, cancellable=true, at={@At(value="NEW", target="java/lang/UnsupportedOperationException")})
    private static void arclight$modToBukkit(MobCategory mobCategory, CallbackInfoReturnable<SpawnCategory> cir) {
        cir.setReturnValue((Object)SpawnCategory.valueOf(mobCategory.name()));
    }

    @Inject(method={"toNMS"}, cancellable=true, at={@At(value="NEW", target="java/lang/UnsupportedOperationException")})
    private static void arclight$bukkitToMod(SpawnCategory spawnCategory, CallbackInfoReturnable<MobCategory> cir) {
        cir.setReturnValue((Object)MobCategory.valueOf((String)spawnCategory.name()));
    }
}

