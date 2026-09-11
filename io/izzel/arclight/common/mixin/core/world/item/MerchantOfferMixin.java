/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.trading.MerchantOffer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.item.MerchantOfferBridge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchantRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={MerchantOffer.class})
public class MerchantOfferMixin
implements MerchantOfferBridge {
    @Shadow
    public ItemStack f_45310_;
    @Shadow
    private int f_45317_;
    private CraftMerchantRecipe bukkitHandle;

    public CraftMerchantRecipe asBukkit() {
        return this.bukkitHandle == null ? (this.bukkitHandle = new CraftMerchantRecipe((MerchantOffer)this)) : this.bukkitHandle;
    }

    public void arclight$constructor(ItemStack buyingStackFirstIn, ItemStack buyingStackSecondIn, ItemStack sellingStackIn, int usesIn, int maxUsesIn, int givenEXPIn, float priceMultiplierIn, int demand) {
        throw new RuntimeException();
    }

    public void arclight$constructor(ItemStack buyingStackFirstIn, ItemStack buyingStackSecondIn, ItemStack sellingStackIn, int usesIn, int maxUsesIn, int givenEXPIn, float priceMultiplierIn, int demand, CraftMerchantRecipe bukkit) {
        this.arclight$constructor(buyingStackFirstIn, buyingStackSecondIn, sellingStackIn, usesIn, maxUsesIn, givenEXPIn, priceMultiplierIn, demand);
        this.bukkitHandle = bukkit;
    }

    @Override
    public CraftMerchantRecipe bridge$asBukkit() {
        return this.asBukkit();
    }

    @Inject(method={"getCostA"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$fix(CallbackInfoReturnable<ItemStack> cir) {
        if (this.f_45310_.m_41613_() <= 0) {
            cir.setReturnValue((Object)ItemStack.f_41583_);
        }
    }
}

