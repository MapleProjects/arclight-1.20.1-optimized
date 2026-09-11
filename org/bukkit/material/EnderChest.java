/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.DirectionalContainer;

@Deprecated
public class EnderChest
extends DirectionalContainer {
    public EnderChest() {
        super(Material.LEGACY_ENDER_CHEST);
    }

    public EnderChest(BlockFace direction) {
        this();
        this.setFacingDirection(direction);
    }

    public EnderChest(Material type) {
        super(type);
    }

    @Deprecated
    public EnderChest(Material type, byte data) {
        super(type, data);
    }

    @Override
    public EnderChest clone() {
        return (EnderChest)super.clone();
    }
}

