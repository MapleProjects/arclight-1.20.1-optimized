/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.ban;

import java.util.Date;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProfileBanList
extends BanList<PlayerProfile> {
    @Override
    @Nullable
    public BanEntry<PlayerProfile> addBan(@NotNull PlayerProfile var1, @Nullable String var2, @Nullable Date var3, @Nullable String var4);
}

