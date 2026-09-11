/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.SpawnData
 *  net.minecraft.world.level.block.entity.SpawnerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.bukkit.World;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftCreatureSpawner
extends CraftBlockEntityState<SpawnerBlockEntity>
implements CreatureSpawner {
    public CraftCreatureSpawner(World world, SpawnerBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public org.bukkit.entity.EntityType getSpawnedType() {
        SpawnData spawnData = ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45444_;
        if (spawnData == null) {
            return null;
        }
        Optional type = EntityType.m_20637_((CompoundTag)spawnData.m_186567_());
        return type.map(entityTypes -> org.bukkit.entity.EntityType.fromName(EntityType.m_20613_((EntityType)entityTypes).m_135815_())).orElse(null);
    }

    @Override
    public void setSpawnedType(org.bukkit.entity.EntityType entityType) {
        if (entityType == null) {
            ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45443_ = SimpleWeightedRandomList.m_185864_();
            ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45444_ = new SpawnData();
            return;
        }
        Preconditions.checkArgument((entityType != org.bukkit.entity.EntityType.UNKNOWN ? 1 : 0) != 0, (String)"Can't spawn EntityType %s from mob spawners!", (Object)entityType);
        RandomSource rand = this.isPlaced() ? this.getWorldHandle().m_213780_() : RandomSource.m_216327_();
        ((SpawnerBlockEntity)this.getSnapshot()).m_252803_((EntityType)EntityType.m_20632_((String)entityType.getName()).get(), rand);
    }

    @Override
    public String getCreatureTypeName() {
        SpawnData spawnData = ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45444_;
        if (spawnData == null) {
            return null;
        }
        Optional type = EntityType.m_20637_((CompoundTag)spawnData.m_186567_());
        return type.map(entityTypes -> EntityType.m_20613_((EntityType)entityTypes).m_135815_()).orElse(null);
    }

    @Override
    public void setCreatureTypeByName(String creatureType) {
        org.bukkit.entity.EntityType type = org.bukkit.entity.EntityType.fromName(creatureType);
        if (type == null) {
            this.setSpawnedType(null);
            return;
        }
        this.setSpawnedType(type);
    }

    @Override
    public int getDelay() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45442_;
    }

    @Override
    public void setDelay(int delay) {
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45442_ = delay;
    }

    @Override
    public int getMinSpawnDelay() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45447_;
    }

    @Override
    public void setMinSpawnDelay(int spawnDelay) {
        Preconditions.checkArgument((spawnDelay <= this.getMaxSpawnDelay() ? 1 : 0) != 0, (Object)"Minimum Spawn Delay must be less than or equal to Maximum Spawn Delay");
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45447_ = spawnDelay;
    }

    @Override
    public int getMaxSpawnDelay() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45448_;
    }

    @Override
    public void setMaxSpawnDelay(int spawnDelay) {
        Preconditions.checkArgument((spawnDelay > 0 ? 1 : 0) != 0, (Object)"Maximum Spawn Delay must be greater than 0.");
        Preconditions.checkArgument((spawnDelay >= this.getMinSpawnDelay() ? 1 : 0) != 0, (Object)"Maximum Spawn Delay must be greater than or equal to Minimum Spawn Delay");
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45448_ = spawnDelay;
    }

    @Override
    public int getMaxNearbyEntities() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45451_;
    }

    @Override
    public void setMaxNearbyEntities(int maxNearbyEntities) {
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45451_ = maxNearbyEntities;
    }

    @Override
    public int getSpawnCount() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45449_;
    }

    @Override
    public void setSpawnCount(int count) {
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45449_ = count;
    }

    @Override
    public int getRequiredPlayerRange() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45452_;
    }

    @Override
    public void setRequiredPlayerRange(int requiredPlayerRange) {
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45452_ = requiredPlayerRange;
    }

    @Override
    public int getSpawnRange() {
        return ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45453_;
    }

    @Override
    public void setSpawnRange(int spawnRange) {
        ((SpawnerBlockEntity)this.getSnapshot()).m_59801_().f_45453_ = spawnRange;
    }
}

