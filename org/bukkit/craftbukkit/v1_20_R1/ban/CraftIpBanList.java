/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.google.common.net.InetAddresses
 *  net.minecraft.server.players.IpBanList
 *  net.minecraft.server.players.IpBanListEntry
 *  net.minecraft.server.players.StoredUserEntry
 */
package org.bukkit.craftbukkit.v1_20_R1.ban;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.net.InetAddresses;
import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import net.minecraft.server.players.IpBanList;
import net.minecraft.server.players.IpBanListEntry;
import net.minecraft.server.players.StoredUserEntry;
import org.bukkit.BanEntry;
import org.bukkit.craftbukkit.v1_20_R1.ban.CraftIpBanEntry;

public class CraftIpBanList
implements org.bukkit.ban.IpBanList {
    private final IpBanList list;

    public CraftIpBanList(IpBanList list) {
        this.list = list;
    }

    @Override
    public BanEntry<InetAddress> getBanEntry(String target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        IpBanListEntry entry = (IpBanListEntry)this.list.m_11388_((Object)target);
        if (entry == null) {
            return null;
        }
        return new CraftIpBanEntry(target, entry, this.list);
    }

    @Override
    public BanEntry<InetAddress> getBanEntry(InetAddress target) {
        return this.getBanEntry(this.getIpFromAddress(target));
    }

    @Override
    public BanEntry<InetAddress> addBan(String target, String reason, Date expires, String source) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Ban target cannot be null");
        IpBanListEntry entry = new IpBanListEntry(target, new Date(), source == null || source.isBlank() ? null : source, expires, reason == null || reason.isBlank() ? null : reason);
        this.list.m_11381_((StoredUserEntry)entry);
        return new CraftIpBanEntry(target, entry, this.list);
    }

    @Override
    public BanEntry<InetAddress> addBan(InetAddress target, String reason, Date expires, String source) {
        return this.addBan(this.getIpFromAddress(target), reason, expires, source);
    }

    @Override
    public BanEntry<InetAddress> addBan(InetAddress target, String reason, Instant expires, String source) {
        Date date = expires != null ? Date.from(expires) : null;
        return this.addBan(target, reason, date, source);
    }

    @Override
    public BanEntry<InetAddress> addBan(InetAddress target, String reason, Duration duration, String source) {
        Instant instant = duration != null ? Instant.now().plus(duration) : null;
        return this.addBan(target, reason, instant, source);
    }

    @Override
    public Set<BanEntry> getBanEntries() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        String[] stringArray = this.list.m_5875_();
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String target = stringArray[n2];
            IpBanListEntry ipBanEntry = (IpBanListEntry)this.list.m_11388_((Object)target);
            if (ipBanEntry != null) {
                builder.add((Object)new CraftIpBanEntry(target, ipBanEntry, this.list));
            }
            ++n2;
        }
        return builder.build();
    }

    @Override
    public Set<BanEntry<InetAddress>> getEntries() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        String[] stringArray = this.list.m_5875_();
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String target = stringArray[n2];
            IpBanListEntry ipBanEntry = (IpBanListEntry)this.list.m_11388_((Object)target);
            if (ipBanEntry != null) {
                builder.add((Object)new CraftIpBanEntry(target, ipBanEntry, this.list));
            }
            ++n2;
        }
        return builder.build();
    }

    @Override
    public boolean isBanned(String target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        return this.list.m_11039_(target);
    }

    @Override
    public boolean isBanned(InetAddress target) {
        return this.isBanned(this.getIpFromAddress(target));
    }

    @Override
    public void pardon(String target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"Target cannot be null");
        this.list.m_11393_((Object)target);
    }

    @Override
    public void pardon(InetAddress target) {
        this.pardon(this.getIpFromAddress(target));
    }

    private String getIpFromAddress(InetAddress address) {
        if (address == null) {
            return null;
        }
        return InetAddresses.toAddrString((InetAddress)address);
    }
}

