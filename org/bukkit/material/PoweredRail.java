/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.ExtendedRails;
import org.bukkit.material.Redstone;

@Deprecated
public class PoweredRail
extends ExtendedRails
implements Redstone {
    public PoweredRail() {
        super(Material.LEGACY_POWERED_RAIL);
    }

    public PoweredRail(Material type) {
        super(type);
    }

    @Deprecated
    public PoweredRail(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPowered() {
        return (this.getData() & 8) == 8;
    }

    public void setPowered(boolean isPowered) {
        this.setData((byte)(isPowered ? this.getData() | 8 : this.getData() & 0xFFFFFFF7));
    }

    @Override
    public PoweredRail clone() {
        return (PoweredRail)super.clone();
    }
}

