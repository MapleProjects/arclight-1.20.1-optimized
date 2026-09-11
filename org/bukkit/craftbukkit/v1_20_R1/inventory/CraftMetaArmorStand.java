/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaArmorStand
extends CraftMetaItem {
    static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
    CompoundTag entityTag;

    public CraftMetaArmorStand(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaArmorStand)) {
            return;
        }
        CraftMetaArmorStand armorStand = (CraftMetaArmorStand)meta;
        this.entityTag = armorStand.entityTag;
    }

    CraftMetaArmorStand(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaArmorStand.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaArmorStand.ENTITY_TAG.NBT).m_6426_();
        }
    }

    CraftMetaArmorStand(Map<String, Object> map) {
        super(map);
    }

    @Override
    void deserializeInternal(CompoundTag tag, Object context) {
        super.deserializeInternal(tag, context);
        if (tag.m_128441_(CraftMetaArmorStand.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaArmorStand.ENTITY_TAG.NBT);
        }
    }

    @Override
    void serializeInternal(Map<String, Tag> internalTags) {
        if (this.entityTag != null && !this.entityTag.m_128456_()) {
            internalTags.put(CraftMetaArmorStand.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.entityTag != null) {
            tag.m_128365_(CraftMetaArmorStand.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.ARMOR_STAND;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isArmorStandEmpty();
    }

    boolean isArmorStandEmpty() {
        return this.entityTag == null;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaArmorStand) {
            CraftMetaArmorStand that = (CraftMetaArmorStand)meta;
            return this.entityTag != null ? that.entityTag != null && this.entityTag.equals((Object)that.entityTag) : this.entityTag == null;
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaArmorStand || this.isArmorStandEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.entityTag != null) {
            hash = 73 * hash + this.entityTag.hashCode();
        }
        return original != hash ? CraftMetaArmorStand.class.hashCode() ^ hash : hash;
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        return builder;
    }

    @Override
    public CraftMetaArmorStand clone() {
        CraftMetaArmorStand clone = (CraftMetaArmorStand)super.clone();
        if (this.entityTag != null) {
            clone.entityTag = this.entityTag.m_6426_();
        }
        return clone;
    }
}

