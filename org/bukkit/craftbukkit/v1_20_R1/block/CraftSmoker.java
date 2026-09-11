/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.SmokerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import org.bukkit.World;
import org.bukkit.block.Smoker;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftFurnace;

public class CraftSmoker
extends CraftFurnace<SmokerBlockEntity>
implements Smoker {
    public CraftSmoker(World world, SmokerBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

