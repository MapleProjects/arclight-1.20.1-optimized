/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSculkShrieker
extends CraftBlockData
implements SculkShrieker {
    private static final BooleanProperty CAN_SUMMON = CraftSculkShrieker.getBoolean("can_summon");
    private static final BooleanProperty SHRIEKING = CraftSculkShrieker.getBoolean("shrieking");

    @Override
    public boolean isCanSummon() {
        return (Boolean)this.get(CAN_SUMMON);
    }

    @Override
    public void setCanSummon(boolean can_summon) {
        this.set(CAN_SUMMON, can_summon);
    }

    @Override
    public boolean isShrieking() {
        return (Boolean)this.get(SHRIEKING);
    }

    @Override
    public void setShrieking(boolean shrieking) {
        this.set(SHRIEKING, shrieking);
    }
}

