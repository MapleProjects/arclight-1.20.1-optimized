/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.CommandBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftCommandBlock
extends CraftBlockData
implements CommandBlock {
    private static final BooleanProperty CONDITIONAL = CraftCommandBlock.getBoolean("conditional");

    @Override
    public boolean isConditional() {
        return (Boolean)this.get(CONDITIONAL);
    }

    @Override
    public void setConditional(boolean conditional) {
        this.set(CONDITIONAL, conditional);
    }
}

