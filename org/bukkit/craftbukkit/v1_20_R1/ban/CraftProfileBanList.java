/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.players.StoredUserEntry
 *  net.minecraft.server.players.UserBanList
 *  net.minecraft.server.players.UserBanListEntry
 */
package org.bukkit.craftbukkit.v1_20_R1.ban;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.GameProfile;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.StoredUserEntry;
import net.minecraft.server.players.UserBanList;
import net.minecraft.server.players.UserBanListEntry;
import org.bukkit.BanEntry;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.craftbukkit.v1_20_R1.ban.CraftProfileBanEntry;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerProfile;
import org.bukkit.profile.PlayerProfile;

public class CraftProfileBanList
implements ProfileBanList {
    private final UserBanList list;

    public CraftProfileBanList(UserBanList list) {
        this.list = list;
    }

    @Override
    public BanEntry<PlayerProfile> getBanEntry(String target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        return this.getBanEntry(CraftProfileBanList.getProfile(target));
    }

    @Override
    public BanEntry<PlayerProfile> getBanEntry(PlayerProfile target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        return this.getBanEntry(((CraftPlayerProfile)target).buildGameProfile());
    }

    @Override
    public BanEntry<PlayerProfile> addBan(String target, String reason, Date expires, String source) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Ban target cannot be null");
        return this.addBan(CraftProfileBanList.getProfileByName(target), reason, expires, source);
    }

    @Override
    public BanEntry<PlayerProfile> addBan(PlayerProfile target, String reason, Date expires, String source) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"PlayerProfile cannot be null");
        Preconditions.checkArgument((target.getUniqueId() != null ? 1 : 0) != 0, (Object)"The PlayerProfile UUID cannot be null");
        return this.addBan(((CraftPlayerProfile)target).buildGameProfile(), reason, expires, source);
    }

    @Override
    public BanEntry<PlayerProfile> addBan(PlayerProfile target, String reason, Instant expires, String source) {
        Date date = expires != null ? Date.from(expires) : null;
        return this.addBan(target, reason, date, source);
    }

    @Override
    public BanEntry<PlayerProfile> addBan(PlayerProfile target, String reason, Duration duration, String source) {
        Instant instant = duration != null ? Instant.now().plus(duration) : null;
        return this.addBan(target, reason, instant, source);
    }

    @Override
    public Set<BanEntry> getBanEntries() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (UserBanListEntry entry : this.list.m_11395_()) {
            GameProfile profile = (GameProfile)entry.m_11373_();
            builder.add((Object)new CraftProfileBanEntry(profile, entry, this.list));
        }
        return builder.build();
    }

    @Override
    public Set<BanEntry<PlayerProfile>> getEntries() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (UserBanListEntry entry : this.list.m_11395_()) {
            GameProfile profile = (GameProfile)entry.m_11373_();
            builder.add((Object)new CraftProfileBanEntry(profile, entry, this.list));
        }
        return builder.build();
    }

    @Override
    public boolean isBanned(PlayerProfile target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        return this.isBanned(((CraftPlayerProfile)target).buildGameProfile());
    }

    @Override
    public boolean isBanned(String target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        return this.isBanned(CraftProfileBanList.getProfile(target));
    }

    @Override
    public void pardon(PlayerProfile target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        this.pardon(((CraftPlayerProfile)target).buildGameProfile());
    }

    @Override
    public void pardon(String target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        this.pardon(CraftProfileBanList.getProfile(target));
    }

    @Override
    public BanEntry<PlayerProfile> getBanEntry(GameProfile profile) {
        if (profile == null) {
            return null;
        }
        UserBanListEntry entry = (UserBanListEntry)this.list.m_11388_((Object)profile);
        if (entry == null) {
            return null;
        }
        return new CraftProfileBanEntry(profile, entry, this.list);
    }

    @Override
    public BanEntry<PlayerProfile> addBan(GameProfile profile, String reason, Date expires, String source) {
        if (profile == null) {
            return null;
        }
        UserBanListEntry entry = new UserBanListEntry(profile, new Date(), source == null || source.isBlank() ? null : source, expires, reason == null || reason.isBlank() ? null : reason);
        this.list.m_11381_((StoredUserEntry)entry);
        return new CraftProfileBanEntry(profile, entry, this.list);
    }

    @Override
    private void pardon(GameProfile profile) {
        this.list.m_11393_((Object)profile);
    }

    @Override
    private boolean isBanned(GameProfile profile) {
        return profile != null && this.list.m_11406_(profile);
    }

    static GameProfile getProfile(String target) {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(target);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return uuid != null ? CraftProfileBanList.getProfileByUUID(uuid) : CraftProfileBanList.getProfileByName(target);
    }

    static GameProfile getProfileByUUID(UUID uuid) {
        return MinecraftServer.getServer() != null ? (GameProfile)MinecraftServer.getServer().m_129927_().m_11002_(uuid).orElse(null) : null;
    }

    static GameProfile getProfileByName(String name) {
        return MinecraftServer.getServer() != null ? (GameProfile)MinecraftServer.getServer().m_129927_().m_10996_(name).orElse(null) : null;
    }
}

