/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftEndPortalFrame
extends CraftBlockData
implements EndPortalFrame {
    private static final BooleanProperty EYE = CraftEndPortalFrame.getBoolean("eye");

    @Override
    public boolean hasEye() {
        return (Boolean)this.get(EYE);
    }

    @Override
    public void setEye(boolean eye) {
        this.set(EYE, eye);
    }
}

