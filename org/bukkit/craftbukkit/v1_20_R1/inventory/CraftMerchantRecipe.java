/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.trading.MerchantOffer
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class CraftMerchantRecipe
extends MerchantRecipe {
    private final MerchantOffer handle;

    public CraftMerchantRecipe(MerchantOffer merchantRecipe) {
        super(CraftItemStack.asBukkitCopy(merchantRecipe.f_45312_), 0);
        this.handle = merchantRecipe;
        this.addIngredient(CraftItemStack.asBukkitCopy(merchantRecipe.f_45310_));
        this.addIngredient(CraftItemStack.asBukkitCopy(merchantRecipe.f_45311_));
    }

    @Deprecated
    public CraftMerchantRecipe(org.bukkit.inventory.ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier) {
        this(result, uses, maxUses, experienceReward, experience, priceMultiplier, 0, 0);
    }

    public CraftMerchantRecipe(org.bukkit.inventory.ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier, int demand, int specialPrice) {
        super(result, uses, maxUses, experienceReward, experience, priceMultiplier, demand, specialPrice);
        this.handle = new MerchantOffer(ItemStack.f_41583_, ItemStack.f_41583_, CraftItemStack.asNMSCopy(result), uses, maxUses, experience, priceMultiplier, demand, this);
        this.setSpecialPrice(specialPrice);
        this.setExperienceReward(experienceReward);
    }

    @Override
    public int getSpecialPrice() {
        return this.handle.m_45377_();
    }

    @Override
    public void setSpecialPrice(int specialPrice) {
        this.handle.f_45316_ = specialPrice;
    }

    @Override
    public int getDemand() {
        return this.handle.f_45317_;
    }

    @Override
    public void setDemand(int demand) {
        this.handle.f_45317_ = demand;
    }

    @Override
    public int getUses() {
        return this.handle.f_45313_;
    }

    @Override
    public void setUses(int uses) {
        this.handle.f_45313_ = uses;
    }

    @Override
    public int getMaxUses() {
        return this.handle.f_45314_;
    }

    @Override
    public void setMaxUses(int maxUses) {
        this.handle.f_45314_ = maxUses;
    }

    @Override
    public boolean hasExperienceReward() {
        return this.handle.f_45315_;
    }

    @Override
    public void setExperienceReward(boolean flag) {
        this.handle.f_45315_ = flag;
    }

    @Override
    public int getVillagerExperience() {
        return this.handle.f_45319_;
    }

    @Override
    public void setVillagerExperience(int villagerExperience) {
        this.handle.f_45319_ = villagerExperience;
    }

    @Override
    public float getPriceMultiplier() {
        return this.handle.f_45318_;
    }

    @Override
    public void setPriceMultiplier(float priceMultiplier) {
        this.handle.f_45318_ = priceMultiplier;
    }

    public MerchantOffer toMinecraft() {
        List<org.bukkit.inventory.ItemStack> ingredients = this.getIngredients();
        Preconditions.checkState((!ingredients.isEmpty() ? 1 : 0) != 0, (Object)"No offered ingredients");
        this.handle.f_45310_ = CraftItemStack.asNMSCopy(ingredients.get(0));
        if (ingredients.size() > 1) {
            this.handle.f_45311_ = CraftItemStack.asNMSCopy(ingredients.get(1));
        }
        return this.handle;
    }

    public static CraftMerchantRecipe fromBukkit(MerchantRecipe recipe) {
        if (recipe instanceof CraftMerchantRecipe) {
            return (CraftMerchantRecipe)recipe;
        }
        CraftMerchantRecipe craft = new CraftMerchantRecipe(recipe.getResult(), recipe.getUses(), recipe.getMaxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier(), recipe.getDemand(), recipe.getSpecialPrice());
        craft.setIngredients(recipe.getIngredients());
        return craft;
    }
}

