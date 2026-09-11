/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 *  net.minecraft.nbt.CompoundTag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaArmor;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaLeatherArmor;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaColorableArmor
extends CraftMetaArmor
implements ColorableArmorMeta {
    private static final Set<Material> LEATHER_ARMOR_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS});
    private Color color = CraftItemFactory.DEFAULT_LEATHER_COLOR;

    CraftMetaColorableArmor(CraftMetaItem meta) {
        super(meta);
        CraftMetaLeatherArmor.readColor((LeatherArmorMeta)this, meta);
    }

    CraftMetaColorableArmor(CompoundTag tag) {
        super(tag);
        CraftMetaLeatherArmor.readColor((LeatherArmorMeta)this, tag);
    }

    CraftMetaColorableArmor(Map<String, Object> map) {
        super(map);
        CraftMetaLeatherArmor.readColor((LeatherArmorMeta)this, map);
    }

    @Override
    void applyToItem(CompoundTag itemTag) {
        super.applyToItem(itemTag);
        CraftMetaLeatherArmor.applyColor(this, itemTag);
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isLeatherArmorEmpty();
    }

    boolean isLeatherArmorEmpty() {
        return !this.hasColor();
    }

    @Override
    boolean applicableTo(Material type) {
        return LEATHER_ARMOR_MATERIALS.contains(type);
    }

    @Override
    public CraftMetaColorableArmor clone() {
        CraftMetaColorableArmor clone = (CraftMetaColorableArmor)super.clone();
        clone.color = this.color;
        return clone;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color == null ? CraftItemFactory.DEFAULT_LEATHER_COLOR : color;
    }

    boolean hasColor() {
        return CraftMetaLeatherArmor.hasColor(this);
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        CraftMetaLeatherArmor.serialize(this, builder);
        return builder;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaColorableArmor) {
            CraftMetaColorableArmor that = (CraftMetaColorableArmor)meta;
            return this.color.equals(that.color);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaColorableArmor || this.isLeatherArmorEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasColor()) {
            hash ^= this.color.hashCode();
        }
        return original != hash ? CraftMetaColorableArmor.class.hashCode() ^ hash : hash;
    }
}

