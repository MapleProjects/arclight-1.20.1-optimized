/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.ExtendedRails;
import org.bukkit.material.PressureSensor;

@Deprecated
public class DetectorRail
extends ExtendedRails
implements PressureSensor {
    public DetectorRail() {
        super(Material.LEGACY_DETECTOR_RAIL);
    }

    public DetectorRail(Material type) {
        super(type);
    }

    @Deprecated
    public DetectorRail(Material type, byte data) {
        super(type, data);
    }

    @Override
    public boolean isPressed() {
        return (this.getData() & 8) == 8;
    }

    public void setPressed(boolean isPressed) {
        this.setData((byte)(isPressed ? this.getData() | 8 : this.getData() & 0xFFFFFFF7));
    }

    @Override
    public DetectorRail clone() {
        return (DetectorRail)super.clone();
    }
}

