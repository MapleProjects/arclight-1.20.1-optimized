/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.collect.Lists
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.trading.Merchant
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchantRecipe;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.MerchantRecipe;

public class CraftMerchant
implements org.bukkit.inventory.Merchant {
    protected final Merchant merchant;

    public CraftMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Merchant getMerchant() {
        return this.merchant;
    }

    @Override
    public List<MerchantRecipe> getRecipes() {
        return Collections.unmodifiableList(Lists.transform((List)this.merchant.m_6616_(), (Function)new Function<MerchantOffer, MerchantRecipe>(){

            public MerchantRecipe apply(MerchantOffer recipe) {
                return recipe.asBukkit();
            }
        }));
    }

    @Override
    public void setRecipes(List<MerchantRecipe> recipes) {
        MerchantOffers recipesList = this.merchant.m_6616_();
        recipesList.clear();
        for (MerchantRecipe recipe : recipes) {
            recipesList.add((Object)CraftMerchantRecipe.fromBukkit(recipe).toMinecraft());
        }
    }

    @Override
    public MerchantRecipe getRecipe(int i) {
        return ((MerchantOffer)this.merchant.m_6616_().get(i)).asBukkit();
    }

    @Override
    public void setRecipe(int i, MerchantRecipe merchantRecipe) {
        this.merchant.m_6616_().set(i, (Object)CraftMerchantRecipe.fromBukkit(merchantRecipe).toMinecraft());
    }

    @Override
    public int getRecipeCount() {
        return this.merchant.m_6616_().size();
    }

    @Override
    public boolean isTrading() {
        return this.getTrader() != null;
    }

    @Override
    public HumanEntity getTrader() {
        Player eh = this.merchant.m_7962_();
        return eh == null ? null : eh.getBukkitEntity();
    }

    public int hashCode() {
        return this.merchant.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof CraftMerchant && ((CraftMerchant)obj).merchant.equals(this.merchant);
    }
}

