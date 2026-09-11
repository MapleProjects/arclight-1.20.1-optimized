/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class MaterialData
implements Cloneable {
    private final Material type;
    private byte data = 0;

    public MaterialData(Material type) {
        this(type, 0);
    }

    @Deprecated
    public MaterialData(Material type, byte data) {
        this.type = type;
        this.data = data;
    }

    @Deprecated
    public byte getData() {
        return this.data;
    }

    @Deprecated
    public void setData(byte data) {
        this.data = data;
    }

    public Material getItemType() {
        return this.type;
    }

    @Deprecated
    public ItemStack toItemStack() {
        return new ItemStack(this.type, 0, this.data);
    }

    public ItemStack toItemStack(int amount) {
        return new ItemStack(this.type, amount, this.data);
    }

    public String toString() {
        return this.getItemType() + "(" + this.getData() + ")";
    }

    public int hashCode() {
        return this.getItemType().hashCode() << 8 ^ this.getData();
    }

    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MaterialData) {
            MaterialData md = (MaterialData)obj;
            return md.getItemType() == this.getItemType() && md.getData() == this.getData();
        }
        return false;
    }

    public MaterialData clone() {
        try {
            return (MaterialData)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
}

