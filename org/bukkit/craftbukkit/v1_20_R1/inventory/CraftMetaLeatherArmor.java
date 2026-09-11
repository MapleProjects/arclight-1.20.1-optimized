/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.IntTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaLeatherArmor
extends CraftMetaItem
implements LeatherArmorMeta {
    private static final Set<Material> LEATHER_ARMOR_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.LEATHER_HELMET, Material.LEATHER_HORSE_ARMOR, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS});
    static final CraftMetaItem.ItemMetaKey COLOR = new CraftMetaItem.ItemMetaKey("color");
    private Color color = CraftItemFactory.DEFAULT_LEATHER_COLOR;

    public CraftMetaLeatherArmor(CraftMetaItem meta) {
        super(meta);
        CraftMetaLeatherArmor.readColor((LeatherArmorMeta)this, meta);
    }

    CraftMetaLeatherArmor(CompoundTag tag) {
        super(tag);
        CraftMetaLeatherArmor.readColor((LeatherArmorMeta)this, tag);
    }

    CraftMetaLeatherArmor(Map<String, Object> map) {
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
    public CraftMetaLeatherArmor clone() {
        return (CraftMetaLeatherArmor)super.clone();
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
        if (meta instanceof CraftMetaLeatherArmor) {
            CraftMetaLeatherArmor that = (CraftMetaLeatherArmor)meta;
            return this.color.equals(that.color);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaLeatherArmor || this.isLeatherArmorEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasColor()) {
            hash ^= this.color.hashCode();
        }
        return original != hash ? CraftMetaLeatherArmor.class.hashCode() ^ hash : hash;
    }

    static void readColor(LeatherArmorMeta meta, CraftMetaItem other) {
        if (!(other instanceof CraftMetaLeatherArmor var2_3)) {
            return;
        }
        meta.setColor(armorMeta.color);
    }

    static void readColor(LeatherArmorMeta meta, CompoundTag tag) {
        CompoundTag display;
        if (tag.m_128441_(CraftMetaLeatherArmor.DISPLAY.NBT) && (display = tag.m_128469_(CraftMetaLeatherArmor.DISPLAY.NBT)).m_128441_(CraftMetaLeatherArmor.COLOR.NBT)) {
            try {
                meta.setColor(Color.fromRGB(display.m_128451_(CraftMetaLeatherArmor.COLOR.NBT)));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
    }

    static void readColor(LeatherArmorMeta meta, Map<String, Object> map) {
        meta.setColor(CraftMetaItem.SerializableMeta.getObject(Color.class, map, CraftMetaLeatherArmor.COLOR.BUKKIT, true));
    }

    static boolean hasColor(LeatherArmorMeta meta) {
        return !CraftItemFactory.DEFAULT_LEATHER_COLOR.equals(meta.getColor());
    }

    static void applyColor(LeatherArmorMeta meta, CompoundTag tag) {
        if (CraftMetaLeatherArmor.hasColor(meta)) {
            ((CraftMetaItem)((Object)meta)).setDisplayTag(tag, CraftMetaLeatherArmor.COLOR.NBT, (Tag)IntTag.m_128679_((int)meta.getColor().asRGB()));
        }
    }

    static void serialize(LeatherArmorMeta meta, ImmutableMap.Builder<String, Object> builder) {
        if (CraftMetaLeatherArmor.hasColor(meta)) {
            builder.put((Object)CraftMetaLeatherArmor.COLOR.BUKKIT, (Object)meta.getColor());
        }
    }
}

