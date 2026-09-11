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
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTropicalFish;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaTropicalFishBucket
extends CraftMetaItem
implements TropicalFishBucketMeta {
    static final CraftMetaItem.ItemMetaKey VARIANT = new CraftMetaItem.ItemMetaKey("BucketVariantTag", "fish-variant");
    static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
    private Integer variant;
    private CompoundTag entityTag;

    public CraftMetaTropicalFishBucket(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaTropicalFishBucket)) {
            return;
        }
        CraftMetaTropicalFishBucket bucket = (CraftMetaTropicalFishBucket)meta;
        this.variant = bucket.variant;
        this.entityTag = bucket.entityTag;
    }

    CraftMetaTropicalFishBucket(CompoundTag tag) {
        super(tag);
        if (tag.m_128425_(CraftMetaTropicalFishBucket.VARIANT.NBT, 3)) {
            this.variant = tag.m_128451_(CraftMetaTropicalFishBucket.VARIANT.NBT);
        }
        if (tag.m_128441_(CraftMetaTropicalFishBucket.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaTropicalFishBucket.ENTITY_TAG.NBT).m_6426_();
        }
    }

    CraftMetaTropicalFishBucket(Map<String, Object> map) {
        super(map);
        Integer variant = CraftMetaItem.SerializableMeta.getObject(Integer.class, map, CraftMetaTropicalFishBucket.VARIANT.BUKKIT, true);
        if (variant != null) {
            this.variant = variant;
        }
    }

    @Override
    void deserializeInternal(CompoundTag tag, Object context) {
        super.deserializeInternal(tag, context);
        if (tag.m_128441_(CraftMetaTropicalFishBucket.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaTropicalFishBucket.ENTITY_TAG.NBT);
        }
    }

    @Override
    void serializeInternal(Map<String, Tag> internalTags) {
        if (this.entityTag != null && !this.entityTag.m_128456_()) {
            internalTags.put(CraftMetaTropicalFishBucket.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.hasVariant()) {
            tag.m_128405_(CraftMetaTropicalFishBucket.VARIANT.NBT, this.variant.intValue());
        }
        if (this.entityTag != null) {
            tag.m_128365_(CraftMetaTropicalFishBucket.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.TROPICAL_FISH_BUCKET;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isBucketEmpty();
    }

    boolean isBucketEmpty() {
        return !this.hasVariant() && this.entityTag == null;
    }

    @Override
    public DyeColor getPatternColor() {
        return CraftTropicalFish.getPatternColor(this.variant);
    }

    @Override
    public void setPatternColor(DyeColor color) {
        if (this.variant == null) {
            this.variant = 0;
        }
        this.variant = CraftTropicalFish.getData(color, this.getPatternColor(), this.getPattern());
    }

    @Override
    public DyeColor getBodyColor() {
        return CraftTropicalFish.getBodyColor(this.variant);
    }

    @Override
    public void setBodyColor(DyeColor color) {
        if (this.variant == null) {
            this.variant = 0;
        }
        this.variant = CraftTropicalFish.getData(this.getPatternColor(), color, this.getPattern());
    }

    @Override
    public TropicalFish.Pattern getPattern() {
        return CraftTropicalFish.getPattern(this.variant);
    }

    @Override
    public void setPattern(TropicalFish.Pattern pattern) {
        if (this.variant == null) {
            this.variant = 0;
        }
        this.variant = CraftTropicalFish.getData(this.getPatternColor(), this.getBodyColor(), pattern);
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
        if (meta instanceof CraftMetaTropicalFishBucket) {
            CraftMetaTropicalFishBucket that = (CraftMetaTropicalFishBucket)meta;
            return (this.hasVariant() ? that.hasVariant() && this.variant.equals(that.variant) : !that.hasVariant()) && (this.entityTag != null ? that.entityTag != null && this.entityTag.equals((Object)that.entityTag) : that.entityTag == null);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaTropicalFishBucket || this.isBucketEmpty());
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
        return original != hash ? CraftMetaTropicalFishBucket.class.hashCode() ^ hash : hash;
    }

    @Override
    public CraftMetaTropicalFishBucket clone() {
        CraftMetaTropicalFishBucket clone = (CraftMetaTropicalFishBucket)super.clone();
        if (this.entityTag != null) {
            clone.entityTag = this.entityTag.m_6426_();
        }
        return clone;
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasVariant()) {
            builder.put((Object)CraftMetaTropicalFishBucket.VARIANT.BUKKIT, (Object)this.variant);
        }
        return builder;
    }
}

