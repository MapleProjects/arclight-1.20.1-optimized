/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SkullMeta
extends ItemMeta {
    @Deprecated
    @Nullable
    public String getOwner();

    public boolean hasOwner();

    @Deprecated
    public boolean setOwner(@Nullable String var1);

    @Nullable
    public OfflinePlayer getOwningPlayer();

    public boolean setOwningPlayer(@Nullable OfflinePlayer var1);

    @Nullable
    public PlayerProfile getOwnerProfile();

    public void setOwnerProfile(@Nullable PlayerProfile var1);

    public void setNoteBlockSound(@Nullable NamespacedKey var1);

    @Nullable
    public NamespacedKey getNoteBlockSound();

    @Override
    @NotNull
    public SkullMeta clone();
}

