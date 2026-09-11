/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.piston.PistonMovingBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftMovingPiston
extends CraftBlockEntityState<PistonMovingBlockEntity> {
    public CraftMovingPiston(World world, PistonMovingBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

