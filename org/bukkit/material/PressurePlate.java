/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.bukkit.material.PressureSensor;

@Deprecated
public class PressurePlate
extends MaterialData
implements PressureSensor {
    public PressurePlate() {
        super(Material.LEGACY_WOOD_PLATE);
    }

    public PressurePlate(Material type) {
        super(type);
    }

    @Deprecated
    public PressurePlate(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPressed() {
        return this.getData() == 1;
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + (this.isPressed() ? " PRESSED" : "");
    }

    @Override
    public PressurePlate clone() {
        return (PressurePlate)super.clone();
    }
}

