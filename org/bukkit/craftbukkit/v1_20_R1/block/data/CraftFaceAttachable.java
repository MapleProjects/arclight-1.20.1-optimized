/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftFaceAttachable
extends CraftBlockData
implements FaceAttachable {
    private static final EnumProperty<?> ATTACH_FACE = CraftFaceAttachable.getEnum("face");

    @Override
    public FaceAttachable.AttachedFace getAttachedFace() {
        return this.get(ATTACH_FACE, FaceAttachable.AttachedFace.class);
    }

    @Override
    public void setAttachedFace(FaceAttachable.AttachedFace face) {
        this.set(ATTACH_FACE, face);
    }
}

