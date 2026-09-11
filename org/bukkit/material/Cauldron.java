/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

@Deprecated
public class Cauldron
extends MaterialData {
    private static final int CAULDRON_FULL = 3;
    private static final int CAULDRON_EMPTY = 0;

    public Cauldron() {
        super(Material.LEGACY_CAULDRON);
    }

    @Deprecated
    public Cauldron(Material type, byte data) {
        super(type, data);
    }

    @Deprecated
    public Cauldron(byte data) {
        super(Material.LEGACY_CAULDRON, data);
    }

    public boolean isFull() {
        return this.getData() >= 3;
    }

    public boolean isEmpty() {
        return this.getData() <= 0;
    }

    @Override
    public String toString() {
        return String.valueOf(this.isEmpty() ? "EMPTY" : (this.isFull() ? "FULL" : String.valueOf(this.getData()) + "/3 FULL")) + " CAULDRON";
    }

    @Override
    public Cauldron clone() {
        return (Cauldron)super.clone();
    }
}

