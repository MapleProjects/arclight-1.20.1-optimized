/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Redstone;

@Deprecated
public class RedstoneWire
extends MaterialData
implements Redstone {
    public RedstoneWire() {
        super(Material.LEGACY_REDSTONE_WIRE);
    }

    public RedstoneWire(Material type) {
        super(type);
    }

    @Deprecated
    public RedstoneWire(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPowered() {
        return this.getData() > 0;
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " " + (this.isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public RedstoneWire clone() {
        return (RedstoneWire)super.clone();
    }
}

