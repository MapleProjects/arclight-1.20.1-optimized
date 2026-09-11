/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.TileState;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Skull
extends TileState {
    public boolean hasOwner();

    @Deprecated
    @Nullable
    public String getOwner();

    @Deprecated
    @Contract(value="null -> false")
    public boolean setOwner(@Nullable String var1);

    @Nullable
    public OfflinePlayer getOwningPlayer();

    public void setOwningPlayer(@NotNull OfflinePlayer var1);

    @Nullable
    public PlayerProfile getOwnerProfile();

    public void setOwnerProfile(@Nullable PlayerProfile var1);

    @Nullable
    public NamespacedKey getNoteBlockSound();

    public void setNoteBlockSound(@Nullable NamespacedKey var1);

    @Deprecated
    @NotNull
    public BlockFace getRotation();

    @Deprecated
    public void setRotation(@NotNull BlockFace var1);

    @Deprecated
    @NotNull
    public SkullType getSkullType();

    @Deprecated
    @Contract(value="_ -> fail")
    public void setSkullType(SkullType var1);
}

