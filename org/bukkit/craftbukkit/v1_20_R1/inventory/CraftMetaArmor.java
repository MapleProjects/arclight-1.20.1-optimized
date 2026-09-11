/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaArmor
extends CraftMetaItem
implements ArmorMeta {
    private static final Set<Material> ARMOR_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS, Material.TURTLE_HELMET});
    static final CraftMetaItem.ItemMetaKey TRIM = new CraftMetaItem.ItemMetaKey("Trim", "trim");
    static final CraftMetaItem.ItemMetaKey TRIM_MATERIAL = new CraftMetaItem.ItemMetaKey("material");
    static final CraftMetaItem.ItemMetaKey TRIM_PATTERN = new CraftMetaItem.ItemMetaKey("pattern");
    private ArmorTrim trim;

    CraftMetaArmor(CraftMetaItem meta) {
        super(meta);
        if (meta instanceof CraftMetaArmor var2_3) {
            this.trim = armorMeta.trim;
        }
    }

    CraftMetaArmor(CompoundTag tag) {
        super(tag);
        CompoundTag trimCompound;
        if (tag.m_128441_(CraftMetaArmor.TRIM.NBT) && (trimCompound = tag.m_128469_(CraftMetaArmor.TRIM.NBT)).m_128441_(CraftMetaArmor.TRIM_MATERIAL.NBT) && trimCompound.m_128441_(CraftMetaArmor.TRIM_PATTERN.NBT)) {
            TrimMaterial trimMaterial = Registry.TRIM_MATERIAL.get(NamespacedKey.fromString(trimCompound.m_128461_(CraftMetaArmor.TRIM_MATERIAL.NBT)));
            TrimPattern trimPattern = Registry.TRIM_PATTERN.get(NamespacedKey.fromString(trimCompound.m_128461_(CraftMetaArmor.TRIM_PATTERN.NBT)));
            this.trim = new ArmorTrim(trimMaterial, trimPattern);
        }
    }

    CraftMetaArmor(Map<String, Object> map) {
        super(map);
        Map trimData = CraftMetaItem.SerializableMeta.getObject(Map.class, map, CraftMetaArmor.TRIM.BUKKIT, true);
        if (trimData != null) {
            String materialKeyString = CraftMetaItem.SerializableMeta.getString(trimData, CraftMetaArmor.TRIM_MATERIAL.BUKKIT, true);
            String patternKeyString = CraftMetaItem.SerializableMeta.getString(trimData, CraftMetaArmor.TRIM_PATTERN.BUKKIT, true);
            if (materialKeyString != null && patternKeyString != null) {
                NamespacedKey materialKey = NamespacedKey.fromString(materialKeyString);
                NamespacedKey patternKey = NamespacedKey.fromString(patternKeyString);
                if (materialKey != null && patternKey != null) {
                    TrimMaterial trimMaterial = Registry.TRIM_MATERIAL.get(materialKey);
                    TrimPattern trimPattern = Registry.TRIM_PATTERN.get(patternKey);
                    if (trimMaterial != null && trimPattern != null) {
                        this.trim = new ArmorTrim(trimMaterial, trimPattern);
                    }
                }
            }
        }
    }

    @Override
    void applyToItem(CompoundTag itemTag) {
        super.applyToItem(itemTag);
        if (this.hasTrim()) {
            CompoundTag trimCompound = new CompoundTag();
            trimCompound.m_128359_(CraftMetaArmor.TRIM_MATERIAL.NBT, this.trim.getMaterial().getKey().toString());
            trimCompound.m_128359_(CraftMetaArmor.TRIM_PATTERN.NBT, this.trim.getPattern().getKey().toString());
            itemTag.m_128365_(CraftMetaArmor.TRIM.NBT, (Tag)trimCompound);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return ARMOR_MATERIALS.contains(type);
    }

    @Override
    boolean equalsCommon(CraftMetaItem that) {
        if (!super.equalsCommon(that)) {
            return false;
        }
        if (that instanceof CraftMetaArmor var2_3) {
            return Objects.equals(this.trim, armorMeta.trim);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaArmor || this.isArmorEmpty());
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isArmorEmpty();
    }

    private boolean isArmorEmpty() {
        return !this.hasTrim();
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasTrim()) {
            hash = 61 * hash + this.trim.hashCode();
        }
        return original != hash ? CraftMetaArmor.class.hashCode() ^ hash : hash;
    }

    @Override
    public CraftMetaArmor clone() {
        CraftMetaArmor meta = (CraftMetaArmor)super.clone();
        meta.trim = this.trim;
        return meta;
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasTrim()) {
            HashMap<String, String> trimData = new HashMap<String, String>();
            trimData.put(CraftMetaArmor.TRIM_MATERIAL.BUKKIT, this.trim.getMaterial().getKey().toString());
            trimData.put(CraftMetaArmor.TRIM_PATTERN.BUKKIT, this.trim.getPattern().getKey().toString());
            builder.put((Object)CraftMetaArmor.TRIM.BUKKIT, trimData);
        }
        return builder;
    }

    @Override
    public boolean hasTrim() {
        return this.trim != null;
    }

    @Override
    public void setTrim(ArmorTrim trim) {
        this.trim = trim;
    }

    @Override
    public ArmorTrim getTrim() {
        return this.trim;
    }
}

