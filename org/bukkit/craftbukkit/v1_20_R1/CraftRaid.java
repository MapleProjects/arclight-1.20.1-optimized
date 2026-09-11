/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.raid.Raid
 *  net.minecraft.world.entity.raid.Raider
 *  net.minecraft.world.level.Level
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.Raider;

public final class CraftRaid
implements Raid {
    private final net.minecraft.world.entity.raid.Raid handle;

    public CraftRaid(net.minecraft.world.entity.raid.Raid handle) {
        this.handle = handle;
    }

    @Override
    public boolean isStarted() {
        return this.handle.m_37770_();
    }

    @Override
    public long getActiveTicks() {
        return this.handle.f_37673_;
    }

    @Override
    public int getBadOmenLevel() {
        return this.handle.f_37679_;
    }

    @Override
    public void setBadOmenLevel(int badOmenLevel) {
        int max = this.handle.m_37772_();
        Preconditions.checkArgument((badOmenLevel >= 0 && badOmenLevel <= max ? 1 : 0) != 0, (String)"Bad Omen level must be between 0 and %s", (int)max);
        this.handle.f_37679_ = badOmenLevel;
    }

    @Override
    public Location getLocation() {
        BlockPos pos = this.handle.m_37780_();
        Level world = this.handle.m_37769_();
        return CraftLocation.toBukkit(pos, (World)world.getWorld());
    }

    @Override
    public Raid.RaidStatus getStatus() {
        if (this.handle.m_37762_()) {
            return Raid.RaidStatus.STOPPED;
        }
        if (this.handle.m_37767_()) {
            return Raid.RaidStatus.VICTORY;
        }
        if (this.handle.m_37768_()) {
            return Raid.RaidStatus.LOSS;
        }
        return Raid.RaidStatus.ONGOING;
    }

    @Override
    public int getSpawnedGroups() {
        return this.handle.m_37771_();
    }

    @Override
    public int getTotalGroups() {
        return this.handle.f_37686_ + (this.handle.f_37679_ > 1 ? 1 : 0);
    }

    @Override
    public int getTotalWaves() {
        return this.handle.f_37686_;
    }

    @Override
    public float getTotalHealth() {
        return this.handle.m_37777_();
    }

    @Override
    public Set<UUID> getHeroes() {
        return Collections.unmodifiableSet(this.handle.f_37672_);
    }

    @Override
    public List<Raider> getRaiders() {
        return (List)this.handle.getRaiders().stream().map(new Function<net.minecraft.world.entity.raid.Raider, Raider>(){

            @Override
            public Raider apply(net.minecraft.world.entity.raid.Raider entityRaider) {
                return (Raider)((Object)entityRaider.getBukkitEntity());
            }
        }).collect(ImmutableList.toImmutableList());
    }

    public net.minecraft.world.entity.raid.Raid getHandle() {
        return this.handle;
    }
}

