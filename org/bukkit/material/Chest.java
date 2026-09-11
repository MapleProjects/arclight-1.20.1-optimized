/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.DirectionalContainer;

@Deprecated
public class Chest
extends DirectionalContainer {
    public Chest() {
        super(Material.LEGACY_CHEST);
    }

    public Chest(BlockFace direction) {
        this();
        this.setFacingDirection(direction);
    }

    public Chest(Material type) {
        super(type);
    }

    @Deprecated
    public Chest(Material type, byte data) {
        super(type, data);
    }

    @Override
    public Chest clone() {
        return (Chest)super.clone();
    }
}

