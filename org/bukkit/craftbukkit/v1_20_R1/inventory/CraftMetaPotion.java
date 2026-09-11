/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaPotion
extends CraftMetaItem
implements PotionMeta {
    private static final Set<Material> POTION_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION, Material.TIPPED_ARROW});
    static final CraftMetaItem.ItemMetaKey AMPLIFIER = new CraftMetaItem.ItemMetaKey("Amplifier", "amplifier");
    static final CraftMetaItem.ItemMetaKey AMBIENT = new CraftMetaItem.ItemMetaKey("Ambient", "ambient");
    static final CraftMetaItem.ItemMetaKey DURATION = new CraftMetaItem.ItemMetaKey("Duration", "duration");
    static final CraftMetaItem.ItemMetaKey SHOW_PARTICLES = new CraftMetaItem.ItemMetaKey("ShowParticles", "has-particles");
    static final CraftMetaItem.ItemMetaKey SHOW_ICON = new CraftMetaItem.ItemMetaKey("ShowIcon", "has-icon");
    static final CraftMetaItem.ItemMetaKey POTION_EFFECTS = new CraftMetaItem.ItemMetaKey("CustomPotionEffects", "custom-effects");
    static final CraftMetaItem.ItemMetaKey POTION_COLOR = new CraftMetaItem.ItemMetaKey("CustomPotionColor", "custom-color");
    static final CraftMetaItem.ItemMetaKey ID = new CraftMetaItem.ItemMetaKey("Id", "potion-id");
    static final CraftMetaItem.ItemMetaKey DEFAULT_POTION = new CraftMetaItem.ItemMetaKey("Potion", "potion-type");
    private PotionData type = new PotionData(PotionType.UNCRAFTABLE, false, false);
    private List<PotionEffect> customEffects;
    private Color color;

    public CraftMetaPotion(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaPotion var2_3)) {
            return;
        }
        this.type = potionMeta.type;
        this.color = potionMeta.color;
        if (potionMeta.hasCustomEffects()) {
            this.customEffects = new ArrayList<PotionEffect>(potionMeta.customEffects);
        }
    }

    CraftMetaPotion(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaPotion.DEFAULT_POTION.NBT)) {
            this.type = CraftPotionUtil.toBukkit(tag.m_128461_(CraftMetaPotion.DEFAULT_POTION.NBT));
        }
        if (tag.m_128441_(CraftMetaPotion.POTION_COLOR.NBT)) {
            try {
                this.color = Color.fromRGB(tag.m_128451_(CraftMetaPotion.POTION_COLOR.NBT));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        if (tag.m_128441_(CraftMetaPotion.POTION_EFFECTS.NBT)) {
            ListTag list = tag.m_128437_(CraftMetaPotion.POTION_EFFECTS.NBT, 10);
            int length = list.size();
            this.customEffects = new ArrayList<PotionEffect>(length);
            int i = 0;
            while (i < length) {
                CompoundTag effect = list.m_128728_(i);
                PotionEffectType type = PotionEffectType.getById(effect.m_128445_(CraftMetaPotion.ID.NBT));
                if (type != null) {
                    byte amp = effect.m_128445_(CraftMetaPotion.AMPLIFIER.NBT);
                    int duration = effect.m_128451_(CraftMetaPotion.DURATION.NBT);
                    boolean ambient = effect.m_128471_(CraftMetaPotion.AMBIENT.NBT);
                    boolean particles = effect.m_128425_(CraftMetaPotion.SHOW_PARTICLES.NBT, 1) ? effect.m_128471_(CraftMetaPotion.SHOW_PARTICLES.NBT) : true;
                    boolean icon = effect.m_128425_(CraftMetaPotion.SHOW_ICON.NBT, 1) ? effect.m_128471_(CraftMetaPotion.SHOW_ICON.NBT) : particles;
                    this.customEffects.add(new PotionEffect(type, duration, amp, ambient, particles, icon));
                }
                ++i;
            }
        }
    }

    CraftMetaPotion(Map<String, Object> map) {
        super(map);
        Iterable rawEffectList;
        this.type = CraftPotionUtil.toBukkit(CraftMetaItem.SerializableMeta.getString(map, CraftMetaPotion.DEFAULT_POTION.BUKKIT, true));
        Color color = CraftMetaItem.SerializableMeta.getObject(Color.class, map, CraftMetaPotion.POTION_COLOR.BUKKIT, true);
        if (color != null) {
            this.setColor(color);
        }
        if ((rawEffectList = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaPotion.POTION_EFFECTS.BUKKIT, true)) == null) {
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
        tag.m_128359_(CraftMetaPotion.DEFAULT_POTION.NBT, CraftPotionUtil.fromBukkit(this.type));
        if (this.hasColor()) {
            tag.m_128405_(CraftMetaPotion.POTION_COLOR.NBT, this.color.asRGB());
        }
        if (this.customEffects != null) {
            ListTag effectList = new ListTag();
            tag.m_128365_(CraftMetaPotion.POTION_EFFECTS.NBT, (Tag)effectList);
            for (PotionEffect effect : this.customEffects) {
                CompoundTag effectData = new CompoundTag();
                effectData.m_128344_(CraftMetaPotion.ID.NBT, (byte)effect.getType().getId());
                effectData.m_128344_(CraftMetaPotion.AMPLIFIER.NBT, (byte)effect.getAmplifier());
                effectData.m_128405_(CraftMetaPotion.DURATION.NBT, effect.getDuration());
                effectData.m_128379_(CraftMetaPotion.AMBIENT.NBT, effect.isAmbient());
                effectData.m_128379_(CraftMetaPotion.SHOW_PARTICLES.NBT, effect.hasParticles());
                effectData.m_128379_(CraftMetaPotion.SHOW_ICON.NBT, effect.hasIcon());
                effectList.add((Object)effectData);
            }
        }
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isPotionEmpty();
    }

    boolean isPotionEmpty() {
        return this.type.getType() == PotionType.UNCRAFTABLE && !this.hasCustomEffects() && !this.hasColor();
    }

    @Override
    boolean applicableTo(Material type) {
        return POTION_MATERIALS.contains(type);
    }

    @Override
    public CraftMetaPotion clone() {
        CraftMetaPotion clone = (CraftMetaPotion)super.clone();
        clone.type = this.type;
        if (this.customEffects != null) {
            clone.customEffects = new ArrayList<PotionEffect>(this.customEffects);
        }
        return clone;
    }

    @Override
    public void setBasePotionData(PotionData data) {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"PotionData cannot be null");
        this.type = data;
    }

    @Override
    public PotionData getBasePotionData() {
        return this.type;
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
                if (old.getAmplifier() == effect.getAmplifier() && old.getDuration() == effect.getDuration() && old.isAmbient() == effect.isAmbient()) {
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

    @Override
    public boolean setMainEffect(PotionEffectType type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Potion effect type cannot be null");
        int index = this.indexOfEffect(type);
        if (index == -1 || index == 0) {
            return false;
        }
        PotionEffect old = this.customEffects.get(0);
        this.customEffects.set(0, this.customEffects.get(index));
        this.customEffects.set(index, old);
        return true;
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
    public boolean hasColor() {
        return this.color != null;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.type.getType() != PotionType.UNCRAFTABLE) {
            hash = 73 * hash + this.type.hashCode();
        }
        if (this.hasColor()) {
            hash = 73 * hash + this.color.hashCode();
        }
        if (this.hasCustomEffects()) {
            hash = 73 * hash + this.customEffects.hashCode();
        }
        return original != hash ? CraftMetaPotion.class.hashCode() ^ hash : hash;
    }

    @Override
    public boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaPotion) {
            CraftMetaPotion that = (CraftMetaPotion)meta;
            return this.type.equals(that.type) && (this.hasCustomEffects() ? that.hasCustomEffects() && this.customEffects.equals(that.customEffects) : !that.hasCustomEffects()) && (this.hasColor() ? that.hasColor() && this.color.equals(that.color) : !that.hasColor());
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaPotion || this.isPotionEmpty());
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.type.getType() != PotionType.UNCRAFTABLE) {
            builder.put((Object)CraftMetaPotion.DEFAULT_POTION.BUKKIT, (Object)CraftPotionUtil.fromBukkit(this.type));
        }
        if (this.hasColor()) {
            builder.put((Object)CraftMetaPotion.POTION_COLOR.BUKKIT, (Object)this.getColor());
        }
        if (this.hasCustomEffects()) {
            builder.put((Object)CraftMetaPotion.POTION_EFFECTS.BUKKIT, (Object)ImmutableList.copyOf(this.customEffects));
        }
        return builder;
    }
}

