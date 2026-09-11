/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

@Deprecated
public abstract class SimpleAttachableMaterialData
extends MaterialData
implements Attachable {
    public SimpleAttachableMaterialData(Material type, BlockFace direction) {
        this(type);
        this.setFacingDirection(direction);
    }

    public SimpleAttachableMaterialData(Material type) {
        super(type);
    }

    @Deprecated
    public SimpleAttachableMaterialData(Material type, byte data) {
        super(type, data);
    }

    @Override
    public BlockFace getFacing() {
        BlockFace attachedFace = this.getAttachedFace();
        return attachedFace == null ? null : attachedFace.getOppositeFace();
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " facing " + (Object)((Object)this.getFacing());
    }

    @Override
    public SimpleAttachableMaterialData clone() {
        return (SimpleAttachableMaterialData)super.clone();
    }
}

