/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.advancement;

import org.bukkit.advancement.AdvancementDisplayType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface AdvancementDisplay {
    @NotNull
    public String getTitle();

    @NotNull
    public String getDescription();

    @NotNull
    public ItemStack getIcon();

    public boolean shouldShowToast();

    public boolean shouldAnnounceChat();

    public boolean isHidden();

    public float getX();

    public float getY();

    @NotNull
    public AdvancementDisplayType getType();
}

