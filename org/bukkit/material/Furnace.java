/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.FurnaceAndDispenser;

@Deprecated
public class Furnace
extends FurnaceAndDispenser {
    public Furnace() {
        super(Material.LEGACY_FURNACE);
    }

    public Furnace(BlockFace direction) {
        this();
        this.setFacingDirection(direction);
    }

    public Furnace(Material type) {
        super(type);
    }

    @Deprecated
    public Furnace(Material type, byte data) {
        super(type, data);
    }

    @Override
    public Furnace clone() {
        return (Furnace)super.clone();
    }
}

