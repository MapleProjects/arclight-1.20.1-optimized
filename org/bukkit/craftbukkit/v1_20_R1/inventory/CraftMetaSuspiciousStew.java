/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaSuspiciousStew
extends CraftMetaItem
implements SuspiciousStewMeta {
    static final CraftMetaItem.ItemMetaKey DURATION = new CraftMetaItem.ItemMetaKey("EffectDuration", "duration");
    static final CraftMetaItem.ItemMetaKey EFFECTS = new CraftMetaItem.ItemMetaKey("Effects", "effects");
    static final CraftMetaItem.ItemMetaKey ID = new CraftMetaItem.ItemMetaKey("EffectId", "id");
    private List<PotionEffect> customEffects;

    public CraftMetaSuspiciousStew(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaSuspiciousStew var2_3)) {
            return;
        }
        if (stewMeta.hasCustomEffects()) {
            this.customEffects = new ArrayList<PotionEffect>(stewMeta.customEffects);
        }
    }

    CraftMetaSuspiciousStew(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaSuspiciousStew.EFFECTS.NBT)) {
            ListTag list = tag.m_128437_(CraftMetaSuspiciousStew.EFFECTS.NBT, 10);
            int length = list.size();
            this.customEffects = new ArrayList<PotionEffect>(length);
            int i = 0;
            while (i < length) {
                CompoundTag effect = list.m_128728_(i);
                PotionEffectType type = PotionEffectType.getById(effect.m_128445_(CraftMetaSuspiciousStew.ID.NBT));
                if (type != null) {
                    int duration = effect.m_128451_(CraftMetaSuspiciousStew.DURATION.NBT);
                    this.customEffects.add(new PotionEffect(type, duration, 0));
                }
                ++i;
            }
        }
    }

    CraftMetaSuspiciousStew(Map<String, Object> map) {
        super(map);
        Iterable rawEffectList = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaSuspiciousStew.EFFECTS.BUKKIT, true);
        if (rawEffectList == null) {
            return;
        }
        for (Object obj : rawEffectList) {
            Preconditions.checkArgument((boolean)(obj instanceof PotionEffect), (String)"Object (%s) in effect list is not valid", obj.getClass());
            this.addCustomEffect((PotionEffect)obj, true);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.customEffects != null) {
            ListTag effectList = new ListTag();
            tag.m_128365_(CraftMetaSuspiciousStew.EFFECTS.NBT, (Tag)effectList);
            for (PotionEffect effect : this.customEffects) {
                CompoundTag effectData = new CompoundTag();
                effectData.m_128344_(CraftMetaSuspiciousStew.ID.NBT, (byte)effect.getType().getId());
                effectData.m_128405_(CraftMetaSuspiciousStew.DURATION.NBT, effect.getDuration());
                effectList.add((Object)effectData);
            }
        }
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isStewEmpty();
    }

    boolean isStewEmpty() {
        return !this.hasCustomEffects();
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.SUSPICIOUS_STEW;
    }

    @Override
    public CraftMetaSuspiciousStew clone() {
        CraftMetaSuspiciousStew clone = (CraftMetaSuspiciousStew)super.clone();
        if (this.customEffects != null) {
            clone.customEffects = new ArrayList<PotionEffect>(this.customEffects);
        }
        return clone;
    }

    @Override
    public boolean hasCustomEffects() {
        return this.customEffects != null;
    }

    @Override
    public List<PotionEffect> getCustomEffects() {
        if (this.hasCustomEffects()) {
            return ImmutableList.copyOf(this.customEffects);
        }
        return ImmutableList.of();
    }

    @Override
    public boolean addCustomEffect(PotionEffect effect, boolean overwrite) {
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (Object)"Potion effect cannot be null");
        int index = this.indexOfEffect(effect.getType());
        if (index != -1) {
            if (overwrite) {
                PotionEffect old = this.customEffects.get(index);
                if (old.getDuration() == effect.getDuration()) {
                    return false;
                }
                this.customEffects.set(index, effect);
                return true;
            }
            return false;
        }
        if (this.customEffects == null) {
            this.customEffects = new ArrayList<PotionEffect>();
        }
        this.customEffects.add(effect);
        return true;
    }

    @Override
    public boolean removeCustomEffect(PotionEffectType type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Potion effect type cannot be null");
        if (!this.hasCustomEffects()) {
            return false;
        }
        boolean changed = false;
        Iterator<PotionEffect> iterator = this.customEffects.iterator();
        while (iterator.hasNext()) {
            PotionEffect effect = iterator.next();
            if (!type.equals(effect.getType())) continue;
            iterator.remove();
            changed = true;
        }
        if (this.customEffects.isEmpty()) {
            this.customEffects = null;
        }
        return changed;
    }

    @Override
    public boolean hasCustomEffect(PotionEffectType type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Potion effect type cannot be null");
        return this.indexOfEffect(type) != -1;
    }

    private int indexOfEffect(PotionEffectType type) {
        if (!this.hasCustomEffects()) {
            return -1;
        }
        int i = 0;
        while (i < this.customEffects.size()) {
            if (this.customEffects.get(i).getType().equals(type)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public boolean clearCustomEffects() {
        boolean changed = this.hasCustomEffects();
        this.customEffects = null;
        return changed;
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasCustomEffects()) {
            hash = 73 * hash + this.customEffects.hashCode();
        }
        return original != hash ? CraftMetaSuspiciousStew.class.hashCode() ^ hash : hash;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaSuspiciousStew var2_3) {
            return this.hasCustomEffects() ? that.hasCustomEffects() && this.customEffects.equals(that.customEffects) : !that.hasCustomEffects();
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaSuspiciousStew || this.isStewEmpty());
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasCustomEffects()) {
            builder.put((Object)CraftMetaSuspiciousStew.EFFECTS.BUKKIT, (Object)ImmutableList.copyOf(this.customEffects));
        }
        return builder;
    }
}

