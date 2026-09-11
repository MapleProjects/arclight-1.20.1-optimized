/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.material.DirectionalContainer;

@Deprecated
public class FurnaceAndDispenser
extends DirectionalContainer {
    public FurnaceAndDispenser(Material type) {
        super(type);
    }

    @Deprecated
    public FurnaceAndDispenser(Material type, byte data) {
        super(type, data);
    }

    @Override
    public FurnaceAndDispenser clone() {
        return (FurnaceAndDispenser)super.clone();
    }
}

