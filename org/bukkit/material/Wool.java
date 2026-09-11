/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.material.Colorable;
import org.bukkit.material.MaterialData;

@Deprecated
public class Wool
extends MaterialData
implements Colorable {
    public Wool() {
        super(Material.LEGACY_WOOL);
    }

    public Wool(DyeColor color) {
        this();
        this.setColor(color);
    }

    public Wool(Material type) {
        super(type);
    }

    @Deprecated
    public Wool(Material type, byte data) {
        super(type, data);
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.getByWoolData(this.getData());
    }

    @Override
    public void setColor(DyeColor color) {
        this.setData(color.getWoolData());
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getColor()) + " " + super.toString();
    }

    @Override
    public Wool clone() {
        return (Wool)super.clone();
    }
}

