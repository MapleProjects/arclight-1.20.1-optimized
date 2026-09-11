/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.Redstone;
import org.bukkit.material.Torch;

@Deprecated
public class RedstoneTorch
extends Torch
implements Redstone {
    public RedstoneTorch() {
        super(Material.LEGACY_REDSTONE_TORCH_ON);
    }

    public RedstoneTorch(Material type) {
        super(type);
    }

    @Deprecated
    public RedstoneTorch(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPowered() {
        return this.getItemType() == Material.LEGACY_REDSTONE_TORCH_ON;
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " " + (this.isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public RedstoneTorch clone() {
        return (RedstoneTorch)super.clone();
    }
}

