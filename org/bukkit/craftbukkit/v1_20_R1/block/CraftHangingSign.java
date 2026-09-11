/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.HangingSignBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import org.bukkit.World;
import org.bukkit.block.HangingSign;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSign;

public class CraftHangingSign
extends CraftSign<HangingSignBlockEntity>
implements HangingSign {
    public CraftHangingSign(World world, HangingSignBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

