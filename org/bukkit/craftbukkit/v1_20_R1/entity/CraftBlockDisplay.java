/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Display
 *  net.minecraft.world.entity.Display$BlockDisplay
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Display;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDisplay;
import org.bukkit.entity.BlockDisplay;

public class CraftBlockDisplay
extends CraftDisplay
implements BlockDisplay {
    public CraftBlockDisplay(CraftServer server, Display.BlockDisplay entity) {
        super(server, (Display)entity);
    }

    public Display.BlockDisplay getHandle() {
        return (Display.BlockDisplay)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftBlockDisplay";
    }

    @Override
    public BlockData getBlock() {
        return CraftBlockData.fromData(this.getHandle().m_269134_());
    }

    @Override
    public void setBlock(BlockData block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"Block cannot be null");
        this.getHandle().m_269329_(((CraftBlockData)block).getState());
    }
}

