/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.ComparatorBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.ComparatorBlockEntity;
import org.bukkit.World;
import org.bukkit.block.Comparator;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftComparator
extends CraftBlockEntityState<ComparatorBlockEntity>
implements Comparator {
    public CraftComparator(World world, ComparatorBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

