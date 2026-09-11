/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.TheEndPortalBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftEndPortal
extends CraftBlockEntityState<TheEndPortalBlockEntity> {
    public CraftEndPortal(World world, TheEndPortalBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

