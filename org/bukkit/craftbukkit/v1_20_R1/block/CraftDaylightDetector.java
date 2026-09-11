/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity;
import org.bukkit.World;
import org.bukkit.block.DaylightDetector;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftDaylightDetector
extends CraftBlockEntityState<DaylightDetectorBlockEntity>
implements DaylightDetector {
    public CraftDaylightDetector(World world, DaylightDetectorBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

