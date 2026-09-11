/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import org.bukkit.World;
import org.bukkit.block.BlastFurnace;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftFurnace;

public class CraftBlastFurnace
extends CraftFurnace<BlastFurnaceBlockEntity>
implements BlastFurnace {
    public CraftBlastFurnace(World world, BlastFurnaceBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

