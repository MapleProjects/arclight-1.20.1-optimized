/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Redstone;

@Deprecated
public class Command
extends MaterialData
implements Redstone {
    public Command() {
        super(Material.LEGACY_COMMAND);
    }

    public Command(Material type) {
        super(type);
    }

    @Deprecated
    public Command(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPowered() {
        return (this.getData() & 1) != 0;
    }

    public void setPowered(boolean bool) {
        this.setData((byte)(bool ? this.getData() | 1 : this.getData() & 0xFFFFFFFE));
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " " + (this.isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public Command clone() {
        return (Command)super.clone();
    }
}

