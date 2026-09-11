/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.LockCode
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BeaconBlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.LockCode;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.bukkit.World;
import org.bukkit.block.Beacon;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CraftBeacon
extends CraftBlockEntityState<BeaconBlockEntity>
implements Beacon {
    public CraftBeacon(World world, BeaconBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Collection<LivingEntity> getEntitiesInRange() {
        this.ensureNoWorldGeneration();
        BlockEntity tileEntity = this.getTileEntityFromWorld();
        if (tileEntity instanceof BeaconBlockEntity) {
            BeaconBlockEntity beacon = (BeaconBlockEntity)tileEntity;
            List nms = BeaconBlockEntity.getHumansInRange((Level)beacon.m_58904_(), (BlockPos)beacon.m_58899_(), (int)beacon.f_58650_);
            ArrayList<LivingEntity> bukkit = new ArrayList<LivingEntity>(nms.size());
            for (Player human : nms) {
                bukkit.add(human.getBukkitEntity());
            }
            return bukkit;
        }
        return new ArrayList<LivingEntity>();
    }

    @Override
    public int getTier() {
        return ((BeaconBlockEntity)this.getSnapshot()).f_58650_;
    }

    @Override
    public PotionEffect getPrimaryEffect() {
        return ((BeaconBlockEntity)this.getSnapshot()).getPrimaryEffect();
    }

    @Override
    public void setPrimaryEffect(PotionEffectType effect) {
        ((BeaconBlockEntity)this.getSnapshot()).f_58652_ = effect != null ? MobEffect.m_19453_((int)effect.getId()) : null;
    }

    @Override
    public PotionEffect getSecondaryEffect() {
        return ((BeaconBlockEntity)this.getSnapshot()).getSecondaryEffect();
    }

    @Override
    public void setSecondaryEffect(PotionEffectType effect) {
        ((BeaconBlockEntity)this.getSnapshot()).f_58653_ = effect != null ? MobEffect.m_19453_((int)effect.getId()) : null;
    }

    @Override
    public String getCustomName() {
        BeaconBlockEntity beacon = (BeaconBlockEntity)this.getSnapshot();
        return beacon.f_58654_ != null ? CraftChatMessage.fromComponent(beacon.f_58654_) : null;
    }

    @Override
    public void setCustomName(String name) {
        ((BeaconBlockEntity)this.getSnapshot()).m_58681_(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public boolean isLocked() {
        return !((BeaconBlockEntity)this.getSnapshot()).f_58655_.f_19103_.isEmpty();
    }

    @Override
    public String getLock() {
        return ((BeaconBlockEntity)this.getSnapshot()).f_58655_.f_19103_;
    }

    @Override
    public void setLock(String key) {
        ((BeaconBlockEntity)this.getSnapshot()).f_58655_ = key == null ? LockCode.f_19102_ : new LockCode(key);
    }
}

