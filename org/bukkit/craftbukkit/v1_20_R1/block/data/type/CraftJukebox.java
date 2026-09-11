/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftJukebox
extends CraftBlockData
implements Jukebox {
    private static final BooleanProperty HAS_RECORD = CraftJukebox.getBoolean("has_record");

    @Override
    public boolean hasRecord() {
        return (Boolean)this.get(HAS_RECORD);
    }
}

