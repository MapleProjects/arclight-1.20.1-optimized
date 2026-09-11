/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.item.trading.Merchant
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.npc;

import io.izzel.arclight.common.bridge.core.entity.merchant.IMerchantBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.item.MerchantOfferBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchant;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchantRecipe;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractVillager.class})
public abstract class AbstractVillagerMixin
extends PathfinderMobMixin
implements IMerchantBridge {
    @Shadow
    @Final
    private SimpleContainer f_35264_;
    private CraftMerchant craftMerchant;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends AbstractVillager> type, Level worldIn, CallbackInfo ci) {
        ((IInventoryBridge)this.f_35264_).setOwner((InventoryHolder)((Object)this.getBukkitEntity()));
    }

    @Override
    public CraftMerchant bridge$getCraftMerchant() {
        return this.craftMerchant == null ? (this.craftMerchant = new CraftMerchant((Merchant)((AbstractVillager)this))) : this.craftMerchant;
    }

    @Redirect(method={"addOffersFromItemListings"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/trading/MerchantOffers;add(Ljava/lang/Object;)Z"))
    private boolean arclight$gainOffer(MerchantOffers merchantOffers, Object e) {
        MerchantOffer offer = (MerchantOffer)e;
        VillagerAcquireTradeEvent event = new VillagerAcquireTradeEvent((org.bukkit.entity.AbstractVillager)((Object)this.getBukkitEntity()), ((MerchantOfferBridge)offer).bridge$asBukkit());
        if (this.valid) {
            Bukkit.getPluginManager().callEvent(event);
        }
        if (!event.isCancelled()) {
            return merchantOffers.add((Object)CraftMerchantRecipe.fromBukkit(event.getRecipe()).toMinecraft());
        }
        return false;
    }
}

