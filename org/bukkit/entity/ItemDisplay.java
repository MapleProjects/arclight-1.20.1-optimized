/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Display;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemDisplay
extends Display {
    @Nullable
    public ItemStack getItemStack();

    public void setItemStack(@Nullable ItemStack var1);

    @NotNull
    public ItemDisplayTransform getItemDisplayTransform();

    public void setItemDisplayTransform(@NotNull ItemDisplayTransform var1);

    public static enum ItemDisplayTransform {
        NONE,
        THIRDPERSON_LEFTHAND,
        THIRDPERSON_RIGHTHAND,
        FIRSTPERSON_LEFTHAND,
        FIRSTPERSON_RIGHTHAND,
        HEAD,
        GUI,
        GROUND,
        FIXED;

    }
}

