/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MerchantRecipe
implements Recipe {
    private ItemStack result;
    private List<ItemStack> ingredients = new ArrayList<ItemStack>();
    private int uses;
    private int maxUses;
    private boolean experienceReward;
    private int specialPrice;
    private int demand;
    private int villagerExperience;
    private float priceMultiplier;

    public MerchantRecipe(@NotNull ItemStack result, int maxUses) {
        this(result, 0, maxUses, false);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward) {
        this(result, uses, maxUses, experienceReward, 0, 0.0f, 0, 0);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier) {
        this(result, uses, maxUses, experienceReward, villagerExperience, priceMultiplier, 0, 0);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier, int demand, int specialPrice) {
        this.result = result;
        this.uses = uses;
        this.maxUses = maxUses;
        this.experienceReward = experienceReward;
        this.villagerExperience = villagerExperience;
        this.priceMultiplier = priceMultiplier;
        this.demand = demand;
        this.specialPrice = specialPrice;
    }

    @Override
    @NotNull
    public ItemStack getResult() {
        return this.result;
    }

    public void addIngredient(@NotNull ItemStack item) {
        Preconditions.checkState((this.ingredients.size() < 2 ? 1 : 0) != 0, (Object)"MerchantRecipe can only have maximum 2 ingredients");
        this.ingredients.add(item.clone());
    }

    public void removeIngredient(int index) {
        this.ingredients.remove(index);
    }

    public void setIngredients(@NotNull List<ItemStack> ingredients) {
        Preconditions.checkState((ingredients.size() <= 2 ? 1 : 0) != 0, (Object)"MerchantRecipe can only have maximum 2 ingredients");
        this.ingredients = new ArrayList<ItemStack>();
        for (ItemStack item : ingredients) {
            this.ingredients.add(item.clone());
        }
    }

    @NotNull
    public List<ItemStack> getIngredients() {
        ArrayList<ItemStack> copy = new ArrayList<ItemStack>();
        for (ItemStack item : this.ingredients) {
            copy.add(item.clone());
        }
        return copy;
    }

    @Nullable
    public ItemStack getAdjustedIngredient1() {
        if (this.ingredients.isEmpty()) {
            return null;
        }
        ItemStack firstIngredient = this.ingredients.get(0).clone();
        this.adjust(firstIngredient);
        return firstIngredient;
    }

    public void adjust(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR || itemStack.getAmount() <= 0) {
            return;
        }
        int amount = itemStack.getAmount();
        int demandAdjustment = Math.max(0, NumberConversions.floor((float)(amount * this.getDemand()) * this.getPriceMultiplier()));
        itemStack.setAmount(Math.max(1, Math.min(itemStack.getMaxStackSize(), amount + demandAdjustment + this.getSpecialPrice())));
    }

    public int getDemand() {
        return this.demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public int getSpecialPrice() {
        return this.specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getUses() {
        return this.uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public boolean hasExperienceReward() {
        return this.experienceReward;
    }

    public void setExperienceReward(boolean flag) {
        this.experienceReward = flag;
    }

    public int getVillagerExperience() {
        return this.villagerExperience;
    }

    public void setVillagerExperience(int villagerExperience) {
        this.villagerExperience = villagerExperience;
    }

    public float getPriceMultiplier() {
        return this.priceMultiplier;
    }

    public void setPriceMultiplier(float priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
}

