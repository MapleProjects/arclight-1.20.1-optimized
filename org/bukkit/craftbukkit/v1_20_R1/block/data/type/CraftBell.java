/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Bell;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBell
extends CraftBlockData
implements Bell {
    private static final EnumProperty<?> ATTACHMENT = CraftBell.getEnum("attachment");

    @Override
    public Bell.Attachment getAttachment() {
        return this.get(ATTACHMENT, Bell.Attachment.class);
    }

    @Override
    public void setAttachment(Bell.Attachment leaves) {
        this.set(ATTACHMENT, leaves);
    }
}

