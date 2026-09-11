/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec$MaterialType
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.MaterialBridge;
import io.izzel.arclight.i18n.conf.MaterialPropertySpec;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLegacy;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftItemFactory.class}, remap=false)
public class CraftItemFactoryMixin {
    @Inject(method={"getItemMeta*"}, require=0, expect=0, cancellable=true, at={@At(value="HEAD")})
    private void arclight$getItemMeta(Material material, CraftMetaItem meta, CallbackInfoReturnable<ItemMeta> cir) {
        MaterialBridge bridge = (MaterialBridge)((Object)CraftLegacy.fromLegacy(material));
        if (bridge.bridge$getType() != MaterialPropertySpec.MaterialType.VANILLA) {
            cir.setReturnValue((Object)bridge.bridge$itemMetaFactory().apply(meta));
        }
    }
}

