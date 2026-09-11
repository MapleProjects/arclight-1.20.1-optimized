/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.CommandBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.CommandBlockEntity;
import org.bukkit.World;
import org.bukkit.block.CommandBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;

public class CraftCommandBlock
extends CraftBlockEntityState<CommandBlockEntity>
implements CommandBlock {
    public CraftCommandBlock(World world, CommandBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public String getCommand() {
        return ((CommandBlockEntity)this.getSnapshot()).m_59141_().m_45438_();
    }

    @Override
    public void setCommand(String command) {
        ((CommandBlockEntity)this.getSnapshot()).m_59141_().m_6590_(command != null ? command : "");
    }

    @Override
    public String getName() {
        return CraftChatMessage.fromComponent(((CommandBlockEntity)this.getSnapshot()).m_59141_().m_45439_());
    }

    @Override
    public void setName(String name) {
        ((CommandBlockEntity)this.getSnapshot()).m_59141_().m_45423_(CraftChatMessage.fromStringOrNull(name != null ? name : "@"));
    }
}

