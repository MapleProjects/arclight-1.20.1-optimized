/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ArmorStand
extends LivingEntity {
    @Deprecated
    @NotNull
    public ItemStack getItemInHand();

    @Deprecated
    public void setItemInHand(@Nullable ItemStack var1);

    @Deprecated
    @NotNull
    public ItemStack getBoots();

    @Deprecated
    public void setBoots(@Nullable ItemStack var1);

    @Deprecated
    @NotNull
    public ItemStack getLeggings();

    @Deprecated
    public void setLeggings(@Nullable ItemStack var1);

    @Deprecated
    @NotNull
    public ItemStack getChestplate();

    @Deprecated
    public void setChestplate(@Nullable ItemStack var1);

    @Deprecated
    @NotNull
    public ItemStack getHelmet();

    @Deprecated
    public void setHelmet(@Nullable ItemStack var1);

    @NotNull
    public EulerAngle getBodyPose();

    public void setBodyPose(@NotNull EulerAngle var1);

    @NotNull
    public EulerAngle getLeftArmPose();

    public void setLeftArmPose(@NotNull EulerAngle var1);

    @NotNull
    public EulerAngle getRightArmPose();

    public void setRightArmPose(@NotNull EulerAngle var1);

    @NotNull
    public EulerAngle getLeftLegPose();

    public void setLeftLegPose(@NotNull EulerAngle var1);

    @NotNull
    public EulerAngle getRightLegPose();

    public void setRightLegPose(@NotNull EulerAngle var1);

    @NotNull
    public EulerAngle getHeadPose();

    public void setHeadPose(@NotNull EulerAngle var1);

    public boolean hasBasePlate();

    public void setBasePlate(boolean var1);

    public boolean isVisible();

    public void setVisible(boolean var1);

    public boolean hasArms();

    public void setArms(boolean var1);

    public boolean isSmall();

    public void setSmall(boolean var1);

    public boolean isMarker();

    public void setMarker(boolean var1);

    public void addEquipmentLock(@NotNull EquipmentSlot var1, @NotNull LockType var2);

    public void removeEquipmentLock(@NotNull EquipmentSlot var1, @NotNull LockType var2);

    public boolean hasEquipmentLock(@NotNull EquipmentSlot var1, @NotNull LockType var2);

    public static enum LockType {
        ADDING_OR_CHANGING,
        REMOVING_OR_CHANGING,
        ADDING;

    }
}

