/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

@Deprecated
public class Cake
extends MaterialData {
    public Cake() {
        super(Material.LEGACY_CAKE_BLOCK);
    }

    public Cake(Material type) {
        super(type);
    }

    @Deprecated
    public Cake(Material type, byte data) {
        super(type, data);
    }

    public int getSlicesEaten() {
        return this.getData();
    }

    public int getSlicesRemaining() {
        return 6 - this.getData();
    }

    public void setSlicesEaten(int n) {
        if (n < 6) {
            this.setData((byte)n);
        }
    }

    public void setSlicesRemaining(int n) {
        if (n > 6) {
            n = 6;
        }
        this.setData((byte)(6 - n));
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " " + this.getSlicesEaten() + "/" + this.getSlicesRemaining() + " slices eaten/remaining";
    }

    @Override
    public Cake clone() {
        return (Cake)super.clone();
    }
}

