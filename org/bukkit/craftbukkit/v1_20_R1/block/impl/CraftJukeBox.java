/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.JukeboxBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftJukeBox
extends CraftBlockData
implements Jukebox {
    private static final BooleanProperty HAS_RECORD = CraftJukeBox.getBoolean(JukeboxBlock.class, "has_record");

    public CraftJukeBox() {
    }

    public CraftJukeBox(BlockState state) {
        super(state);
    }

    @Override
    public boolean hasRecord() {
        return (Boolean)this.get(HAS_RECORD);
    }
}

