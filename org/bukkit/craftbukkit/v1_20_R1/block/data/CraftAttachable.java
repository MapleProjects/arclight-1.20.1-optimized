/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Attachable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftAttachable
extends CraftBlockData
implements Attachable {
    private static final BooleanProperty ATTACHED = CraftAttachable.getBoolean("attached");

    @Override
    public boolean isAttached() {
        return (Boolean)this.get(ATTACHED);
    }

    @Override
    public void setAttached(boolean attached) {
        this.set(ATTACHED, attached);
    }
}

