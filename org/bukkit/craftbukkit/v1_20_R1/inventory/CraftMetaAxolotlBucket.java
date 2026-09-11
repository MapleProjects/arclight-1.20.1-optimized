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
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.meta.AxolotlBucketMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaAxolotlBucket
extends CraftMetaItem
implements AxolotlBucketMeta {
    static final CraftMetaItem.ItemMetaKey VARIANT = new CraftMetaItem.ItemMetaKey("Variant", "axolotl-variant");
    static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
    private Integer variant;
    private CompoundTag entityTag;

    CraftMetaAxolotlBucket(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaAxolotlBucket)) {
            return;
        }
        CraftMetaAxolotlBucket bucket = (CraftMetaAxolotlBucket)meta;
        this.variant = bucket.variant;
        this.entityTag = bucket.entityTag;
    }

    CraftMetaAxolotlBucket(CompoundTag tag) {
        super(tag);
        if (tag.m_128425_(CraftMetaAxolotlBucket.VARIANT.NBT, 3)) {
            this.variant = tag.m_128451_(CraftMetaAxolotlBucket.VARIANT.NBT);
        }
        if (tag.m_128441_(CraftMetaAxolotlBucket.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaAxolotlBucket.ENTITY_TAG.NBT).m_6426_();
        }
    }

    CraftMetaAxolotlBucket(Map<String, Object> map) {
        super(map);
        Integer variant = CraftMetaItem.SerializableMeta.getObject(Integer.class, map, CraftMetaAxolotlBucket.VARIANT.BUKKIT, true);
        if (variant != null) {
            this.variant = variant;
        }
    }

    @Override
    void deserializeInternal(CompoundTag tag, Object context) {
        super.deserializeInternal(tag, context);
        if (tag.m_128441_(CraftMetaAxolotlBucket.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaAxolotlBucket.ENTITY_TAG.NBT);
        }
    }

    @Override
    void serializeInternal(Map<String, Tag> internalTags) {
        if (this.entityTag != null && !this.entityTag.m_128456_()) {
            internalTags.put(CraftMetaAxolotlBucket.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.hasVariant()) {
            tag.m_128405_(CraftMetaAxolotlBucket.VARIANT.NBT, this.variant.intValue());
        }
        if (this.entityTag != null) {
            tag.m_128365_(CraftMetaAxolotlBucket.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.AXOLOTL_BUCKET;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isBucketEmpty();
    }

    boolean isBucketEmpty() {
        return !this.hasVariant() && this.entityTag == null;
    }

    @Override
    public Axolotl.Variant getVariant() {
        return Axolotl.Variant.values()[this.variant];
    }

    @Override
    public void setVariant(Axolotl.Variant variant) {
        if (variant == null) {
            variant = Axolotl.Variant.LUCY;
        }
        this.variant = variant.ordinal();
    }

    @Override
    public boolean hasVariant() {
        return this.variant != null;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaAxolotlBucket) {
            CraftMetaAxolotlBucket that = (CraftMetaAxolotlBucket)meta;
            return (this.hasVariant() ? that.hasVariant() && this.variant.equals(that.variant) : !that.hasVariant()) && (this.entityTag != null ? that.entityTag != null && this.entityTag.equals((Object)that.entityTag) : that.entityTag == null);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaAxolotlBucket || this.isBucketEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasVariant()) {
            hash = 61 * hash + this.variant;
        }
        if (this.entityTag != null) {
            hash = 61 * hash + this.entityTag.hashCode();
        }
        return original != hash ? CraftMetaAxolotlBucket.class.hashCode() ^ hash : hash;
    }

    @Override
    public CraftMetaAxolotlBucket clone() {
        CraftMetaAxolotlBucket clone = (CraftMetaAxolotlBucket)super.clone();
        if (this.entityTag != null) {
            clone.entityTag = this.entityTag.m_6426_();
        }
        return clone;
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasVariant()) {
            builder.put((Object)CraftMetaAxolotlBucket.VARIANT.BUKKIT, (Object)this.variant);
        }
        return builder;
    }
}

