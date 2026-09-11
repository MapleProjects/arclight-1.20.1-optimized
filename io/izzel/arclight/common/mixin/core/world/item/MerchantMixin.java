/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.trading.Merchant
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.merchant.IMerchantBridge;
import net.minecraft.world.item.trading.Merchant;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchant;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={Merchant.class})
public interface MerchantMixin
extends IMerchantBridge {
    default public CraftMerchant getCraftMerchant() {
        return this.bridge$getCraftMerchant();
    }
}

