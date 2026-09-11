/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.Collection;
import java.util.Set;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HumanEntity
extends LivingEntity,
AnimalTamer,
InventoryHolder {
    @Override
    @NotNull
    public String getName();

    @Override
    @NotNull
    public PlayerInventory getInventory();

    @NotNull
    public Inventory getEnderChest();

    @NotNull
    public MainHand getMainHand();

    public boolean setWindowProperty(@NotNull InventoryView.Property var1, int var2);

    public int getEnchantmentSeed();

    public void setEnchantmentSeed(int var1);

    @NotNull
    public InventoryView getOpenInventory();

    @Nullable
    public InventoryView openInventory(@NotNull Inventory var1);

    @Nullable
    public InventoryView openWorkbench(@Nullable Location var1, boolean var2);

    @Nullable
    public InventoryView openEnchanting(@Nullable Location var1, boolean var2);

    public void openInventory(@NotNull InventoryView var1);

    @Nullable
    public InventoryView openMerchant(@NotNull Villager var1, boolean var2);

    @Nullable
    public InventoryView openMerchant(@NotNull Merchant var1, boolean var2);

    public void closeInventory();

    @Deprecated
    @NotNull
    public ItemStack getItemInHand();

    @Deprecated
    public void setItemInHand(@Nullable ItemStack var1);

    @NotNull
    public ItemStack getItemOnCursor();

    public void setItemOnCursor(@Nullable ItemStack var1);

    public boolean hasCooldown(@NotNull Material var1);

    public int getCooldown(@NotNull Material var1);

    public void setCooldown(@NotNull Material var1, int var2);

    public int getSleepTicks();

    public boolean sleep(@NotNull Location var1, boolean var2);

    public void wakeup(boolean var1);

    @NotNull
    public Location getBedLocation();

    @NotNull
    public GameMode getGameMode();

    public void setGameMode(@NotNull GameMode var1);

    public boolean isBlocking();

    public boolean isHandRaised();

    @Nullable
    public ItemStack getItemInUse();

    public int getExpToLevel();

    public float getAttackCooldown();

    public boolean discoverRecipe(@NotNull NamespacedKey var1);

    public int discoverRecipes(@NotNull Collection<NamespacedKey> var1);

    public boolean undiscoverRecipe(@NotNull NamespacedKey var1);

    public int undiscoverRecipes(@NotNull Collection<NamespacedKey> var1);

    public boolean hasDiscoveredRecipe(@NotNull NamespacedKey var1);

    @NotNull
    public Set<NamespacedKey> getDiscoveredRecipes();

    @Deprecated
    @Nullable
    public Entity getShoulderEntityLeft();

    @Deprecated
    public void setShoulderEntityLeft(@Nullable Entity var1);

    @Deprecated
    @Nullable
    public Entity getShoulderEntityRight();

    @Deprecated
    public void setShoulderEntityRight(@Nullable Entity var1);

    public boolean dropItem(boolean var1);

    public float getExhaustion();

    public void setExhaustion(float var1);

    public float getSaturation();

    public void setSaturation(float var1);

    public int getFoodLevel();

    public void setFoodLevel(int var1);

    public int getSaturatedRegenRate();

    public void setSaturatedRegenRate(int var1);

    public int getUnsaturatedRegenRate();

    public void setUnsaturatedRegenRate(int var1);

    public int getStarvationRate();

    public void setStarvationRate(int var1);

    @Nullable
    public Location getLastDeathLocation();

    public void setLastDeathLocation(@Nullable Location var1);

    @Nullable
    public Firework fireworkBoost(@NotNull ItemStack var1);
}

