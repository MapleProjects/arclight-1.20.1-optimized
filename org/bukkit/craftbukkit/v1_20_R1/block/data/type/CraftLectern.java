/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Lectern;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftLectern
extends CraftBlockData
implements Lectern {
    private static final BooleanProperty HAS_BOOK = CraftLectern.getBoolean("has_book");

    @Override
    public boolean hasBook() {
        return (Boolean)this.get(HAS_BOOK);
    }
}

