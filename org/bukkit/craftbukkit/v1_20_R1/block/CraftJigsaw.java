/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.JigsawBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import org.bukkit.World;
import org.bukkit.block.Jigsaw;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftJigsaw
extends CraftBlockEntityState<JigsawBlockEntity>
implements Jigsaw {
    public CraftJigsaw(World world, JigsawBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}

