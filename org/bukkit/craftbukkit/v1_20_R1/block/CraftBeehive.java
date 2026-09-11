/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity$BeeReleaseStatus
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Beehive;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBee;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.Bee;

public class CraftBeehive
extends CraftBlockEntityState<BeehiveBlockEntity>
implements Beehive {
    public CraftBeehive(World world, BeehiveBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Location getFlower() {
        BlockPos flower = ((BeehiveBlockEntity)this.getSnapshot()).f_58733_;
        return flower == null ? null : CraftLocation.toBukkit(flower, this.getWorld());
    }

    @Override
    public void setFlower(Location location) {
        Preconditions.checkArgument((location == null || this.getWorld().equals(location.getWorld()) ? 1 : 0) != 0, (Object)"Flower must be in same world");
        ((BeehiveBlockEntity)this.getSnapshot()).f_58733_ = location == null ? null : CraftLocation.toBlockPosition(location);
    }

    @Override
    public boolean isFull() {
        return ((BeehiveBlockEntity)this.getSnapshot()).m_58775_();
    }

    @Override
    public boolean isSedated() {
        return this.isPlaced() && ((BeehiveBlockEntity)this.getTileEntity()).m_58777_();
    }

    @Override
    public int getEntityCount() {
        return ((BeehiveBlockEntity)this.getSnapshot()).m_58776_();
    }

    @Override
    public int getMaxEntities() {
        return ((BeehiveBlockEntity)this.getSnapshot()).maxBees;
    }

    @Override
    public void setMaxEntities(int max) {
        Preconditions.checkArgument((max > 0 ? 1 : 0) != 0, (Object)"Max bees must be more than 0");
        ((BeehiveBlockEntity)this.getSnapshot()).maxBees = max;
    }

    @Override
    public List<Bee> releaseEntities() {
        this.ensureNoWorldGeneration();
        ArrayList<Bee> bees = new ArrayList<Bee>();
        if (this.isPlaced()) {
            BeehiveBlockEntity beehive = (BeehiveBlockEntity)this.getTileEntityFromWorld();
            for (Entity bee : beehive.releaseBees(this.getHandle(), BeehiveBlockEntity.BeeReleaseStatus.BEE_RELEASED, true)) {
                bees.add((Bee)((Object)bee.getBukkitEntity()));
            }
        }
        return bees;
    }

    @Override
    public void addEntity(Bee entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity must not be null");
        ((BeehiveBlockEntity)this.getSnapshot()).m_58741_((Entity)((CraftBee)entity).getHandle(), false);
    }
}

