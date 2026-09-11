/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.net.InetAddresses
 *  net.minecraft.server.players.IpBanList
 *  net.minecraft.server.players.IpBanListEntry
 *  net.minecraft.server.players.StoredUserEntry
 */
package org.bukkit.craftbukkit.v1_20_R1.ban;

import com.google.common.net.InetAddresses;
import java.net.InetAddress;
import java.time.Instant;
import java.util.Date;
import net.minecraft.server.players.IpBanList;
import net.minecraft.server.players.IpBanListEntry;
import net.minecraft.server.players.StoredUserEntry;
import org.bukkit.BanEntry;

public final class CraftIpBanEntry
implements BanEntry<InetAddress> {
    private static final Date minorDate = Date.from(Instant.parse("1899-12-31T04:00:00Z"));
    private final IpBanList list;
    private final String target;
    private Date created;
    private String source;
    private Date expiration;
    private String reason;

    public CraftIpBanEntry(String target, IpBanListEntry entry, IpBanList list) {
        this.list = list;
        this.target = target;
        this.created = entry.m_143954_() != null ? new Date(entry.m_143954_().getTime()) : null;
        this.source = entry.m_10960_();
        this.expiration = entry.m_10961_() != null ? new Date(entry.m_10961_().getTime()) : null;
        this.reason = entry.m_10962_();
    }

    @Override
    public String getTarget() {
        return this.target;
    }

    @Override
    public InetAddress getBanTarget() {
        return InetAddresses.forString((String)this.target);
    }

    @Override
    public Date getCreated() {
        return this.created == null ? null : (Date)this.created.clone();
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Date getExpiration() {
        return this.expiration == null ? null : (Date)this.expiration.clone();
    }

    @Override
    public void setExpiration(Date expiration) {
        if (expiration != null && expiration.getTime() == minorDate.getTime()) {
            expiration = null;
        }
        this.expiration = expiration;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public void save() {
        IpBanListEntry entry = new IpBanListEntry(this.target, this.created, this.source, this.expiration, this.reason);
        this.list.m_11381_((StoredUserEntry)entry);
    }

    @Override
    public void remove() {
        this.list.m_11393_((Object)this.target);
    }
}

