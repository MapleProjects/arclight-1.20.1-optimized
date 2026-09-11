/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.block.state.pattern.BlockPattern$BlockPatternMatch
 *  net.minecraft.world.level.dimension.end.DragonRespawnAnimation
 *  net.minecraft.world.level.dimension.end.EndDragonFight
 */
package org.bukkit.craftbukkit.v1_20_R1.boss;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.dimension.end.DragonRespawnAnimation;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftBossBar;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderCrystal;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;

public class CraftDragonBattle
implements DragonBattle {
    private final EndDragonFight handle;

    public CraftDragonBattle(EndDragonFight handle) {
        this.handle = handle;
    }

    @Override
    public EnderDragon getEnderDragon() {
        Entity entity = this.handle.f_64061_.m_8791_(this.handle.f_64070_);
        return entity != null ? (EnderDragon)((Object)entity.getBukkitEntity()) : null;
    }

    @Override
    public BossBar getBossBar() {
        return new CraftBossBar(this.handle.f_64060_);
    }

    @Override
    public Location getEndPortalLocation() {
        if (this.handle.f_64072_ == null) {
            return null;
        }
        return CraftLocation.toBukkit(this.handle.f_64072_, (World)this.handle.f_64061_.getWorld());
    }

    @Override
    public boolean generateEndPortal(boolean withPortals) {
        if (this.handle.f_64072_ != null || this.handle.m_64105_() != null) {
            return false;
        }
        this.handle.m_64093_(withPortals);
        return true;
    }

    @Override
    public boolean hasBeenPreviouslyKilled() {
        return this.handle.m_64099_();
    }

    @Override
    public void initiateRespawn() {
        this.handle.tryRespawn();
    }

    @Override
    public boolean initiateRespawn(Collection<EnderCrystal> list) {
        if (this.hasBeenPreviouslyKilled() && this.getRespawnPhase() == DragonBattle.RespawnPhase.NONE) {
            BlockPattern.BlockPatternMatch shapedetector_shapedetectorcollection;
            if (this.handle.f_64072_ == null && (shapedetector_shapedetectorcollection = this.handle.m_64105_()) == null) {
                this.handle.m_64093_(true);
            }
            list = list != null ? new ArrayList<EnderCrystal>(list) : Collections.emptyList();
            list.removeIf(enderCrystal -> {
                if (enderCrystal == null) {
                    return true;
                }
                World world = enderCrystal.getWorld();
                return !((CraftWorld)world).getHandle().equals(this.handle.f_64061_);
            });
            return this.handle.respawnDragon(list.stream().map(enderCrystal -> ((CraftEnderCrystal)enderCrystal).getHandle()).collect(Collectors.toList()));
        }
        return false;
    }

    @Override
    public DragonBattle.RespawnPhase getRespawnPhase() {
        return this.toBukkitRespawnPhase(this.handle.f_64073_);
    }

    @Override
    public boolean setRespawnPhase(DragonBattle.RespawnPhase phase) {
        Preconditions.checkArgument((phase != null && phase != DragonBattle.RespawnPhase.NONE ? 1 : 0) != 0, (String)"Invalid respawn phase provided: %s", (Object)((Object)phase));
        if (this.handle.f_64073_ == null) {
            return false;
        }
        this.handle.m_64087_(this.toNMSRespawnPhase(phase));
        return true;
    }

    @Override
    public void resetCrystals() {
        this.handle.m_64101_();
    }

    public int hashCode() {
        return this.handle.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof CraftDragonBattle && ((CraftDragonBattle)obj).handle == this.handle;
    }

    private DragonBattle.RespawnPhase toBukkitRespawnPhase(DragonRespawnAnimation phase) {
        return phase != null ? DragonBattle.RespawnPhase.values()[phase.ordinal()] : DragonBattle.RespawnPhase.NONE;
    }

    private DragonRespawnAnimation toNMSRespawnPhase(DragonBattle.RespawnPhase phase) {
        return phase != DragonBattle.RespawnPhase.NONE ? DragonRespawnAnimation.values()[phase.ordinal()] : null;
    }
}

