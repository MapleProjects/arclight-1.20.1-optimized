/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.level.block.entity.SculkSensorBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import org.bukkit.World;
import org.bukkit.block.SculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftSculkSensor<T extends SculkSensorBlockEntity>
extends CraftBlockEntityState<T>
implements SculkSensor {
    public CraftSculkSensor(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public int getLastVibrationFrequency() {
        return ((SculkSensorBlockEntity)this.getSnapshot()).m_155656_();
    }

    @Override
    public void setLastVibrationFrequency(int lastVibrationFrequency) {
        Preconditions.checkArgument((lastVibrationFrequency >= 0 && lastVibrationFrequency <= 15 ? 1 : 0) != 0, (Object)"Vibration frequency must be between 0-15");
        ((SculkSensorBlockEntity)this.getSnapshot()).f_155633_ = lastVibrationFrequency;
    }
}

