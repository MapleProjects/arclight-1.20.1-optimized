/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Player;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LivingEntity
extends Attributable,
Damageable,
ProjectileSource {
    public double getEyeHeight();

    public double getEyeHeight(boolean var1);

    @NotNull
    public Location getEyeLocation();

    @NotNull
    public List<Block> getLineOfSight(@Nullable Set<Material> var1, int var2);

    @NotNull
    public Block getTargetBlock(@Nullable Set<Material> var1, int var2);

    @NotNull
    public List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> var1, int var2);

    @Nullable
    public Block getTargetBlockExact(int var1);

    @Nullable
    public Block getTargetBlockExact(int var1, @NotNull FluidCollisionMode var2);

    @Nullable
    public RayTraceResult rayTraceBlocks(double var1);

    @Nullable
    public RayTraceResult rayTraceBlocks(double var1, @NotNull FluidCollisionMode var3);

    public int getRemainingAir();

    public void setRemainingAir(int var1);

    public int getMaximumAir();

    public void setMaximumAir(int var1);

    public int getArrowCooldown();

    public void setArrowCooldown(int var1);

    public int getArrowsInBody();

    public void setArrowsInBody(int var1);

    public int getMaximumNoDamageTicks();

    public void setMaximumNoDamageTicks(int var1);

    public double getLastDamage();

    public void setLastDamage(double var1);

    public int getNoDamageTicks();

    public void setNoDamageTicks(int var1);

    public int getNoActionTicks();

    public void setNoActionTicks(int var1);

    @Nullable
    public Player getKiller();

    public boolean addPotionEffect(@NotNull PotionEffect var1);

    @Deprecated
    public boolean addPotionEffect(@NotNull PotionEffect var1, boolean var2);

    public boolean addPotionEffects(@NotNull Collection<PotionEffect> var1);

    public boolean hasPotionEffect(@NotNull PotionEffectType var1);

    @Nullable
    public PotionEffect getPotionEffect(@NotNull PotionEffectType var1);

    public void removePotionEffect(@NotNull PotionEffectType var1);

    @NotNull
    public Collection<PotionEffect> getActivePotionEffects();

    public boolean hasLineOfSight(@NotNull Entity var1);

    public boolean getRemoveWhenFarAway();

    public void setRemoveWhenFarAway(boolean var1);

    @Nullable
    public EntityEquipment getEquipment();

    public void setCanPickupItems(boolean var1);

    public boolean getCanPickupItems();

    public boolean isLeashed();

    @NotNull
    public Entity getLeashHolder() throws IllegalStateException;

    public boolean setLeashHolder(@Nullable Entity var1);

    public boolean isGliding();

    public void setGliding(boolean var1);

    public boolean isSwimming();

    public void setSwimming(boolean var1);

    public boolean isRiptiding();

    public boolean isSleeping();

    public boolean isClimbing();

    public void setAI(boolean var1);

    public boolean hasAI();

    public void attack(@NotNull Entity var1);

    public void swingMainHand();

    public void swingOffHand();

    public void playHurtAnimation(float var1);

    public void setCollidable(boolean var1);

    public boolean isCollidable();

    @NotNull
    public Set<UUID> getCollidableExemptions();

    @Nullable
    public <T> T getMemory(@NotNull MemoryKey<T> var1);

    public <T> void setMemory(@NotNull MemoryKey<T> var1, @Nullable T var2);

    @Nullable
    public Sound getHurtSound();

    @Nullable
    public Sound getDeathSound();

    @NotNull
    public Sound getFallDamageSound(int var1);

    @NotNull
    public Sound getFallDamageSoundSmall();

    @NotNull
    public Sound getFallDamageSoundBig();

    @NotNull
    public Sound getDrinkingSound(@NotNull ItemStack var1);

    @NotNull
    public Sound getEatingSound(@NotNull ItemStack var1);

    public boolean canBreatheUnderwater();

    @NotNull
    public EntityCategory getCategory();

    public void setInvisible(boolean var1);

    public boolean isInvisible();
}

