/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.inventory.MerchantContainer
 *  net.minecraft.world.item.trading.Merchant
 *  net.minecraft.world.item.trading.MerchantOffer
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

public class CraftInventoryMerchant
extends CraftInventory
implements MerchantInventory {
    private final Merchant merchant;

    public CraftInventoryMerchant(Merchant merchant, MerchantContainer inventory) {
        super((Container)inventory);
        this.merchant = merchant;
    }

    @Override
    public int getSelectedRecipeIndex() {
        return this.getInventory().f_40000_;
    }

    @Override
    public MerchantRecipe getSelectedRecipe() {
        MerchantOffer nmsRecipe = this.getInventory().m_40025_();
        return nmsRecipe == null ? null : nmsRecipe.asBukkit();
    }

    public MerchantContainer getInventory() {
        return (MerchantContainer)this.inventory;
    }

    @Override
    public org.bukkit.inventory.Merchant getMerchant() {
        return this.merchant.getCraftMerchant();
    }
}

