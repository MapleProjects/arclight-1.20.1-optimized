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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.FireworkMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaFirework
extends CraftMetaItem
implements FireworkMeta {
    static final CraftMetaItem.ItemMetaKey FIREWORKS = new CraftMetaItem.ItemMetaKey("Fireworks");
    static final CraftMetaItem.ItemMetaKey FLIGHT = new CraftMetaItem.ItemMetaKey("Flight", "power");
    static final CraftMetaItem.ItemMetaKey EXPLOSIONS = new CraftMetaItem.ItemMetaKey("Explosions", "firework-effects");
    static final CraftMetaItem.ItemMetaKey EXPLOSION_COLORS = new CraftMetaItem.ItemMetaKey("Colors");
    static final CraftMetaItem.ItemMetaKey EXPLOSION_TYPE = new CraftMetaItem.ItemMetaKey("Type");
    static final CraftMetaItem.ItemMetaKey EXPLOSION_TRAIL = new CraftMetaItem.ItemMetaKey("Trail");
    static final CraftMetaItem.ItemMetaKey EXPLOSION_FLICKER = new CraftMetaItem.ItemMetaKey("Flicker");
    static final CraftMetaItem.ItemMetaKey EXPLOSION_FADE = new CraftMetaItem.ItemMetaKey("FadeColors");
    private List<FireworkEffect> effects;
    private Integer power;

    public CraftMetaFirework(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaFirework var2_3)) {
            return;
        }
        this.power = that.power;
        if (that.hasEffects()) {
            this.effects = new ArrayList<FireworkEffect>(that.effects);
        }
    }

    CraftMetaFirework(CompoundTag tag) {
        super(tag);
        if (!tag.m_128441_(CraftMetaFirework.FIREWORKS.NBT)) {
            return;
        }
        CompoundTag fireworks = tag.m_128469_(CraftMetaFirework.FIREWORKS.NBT);
        this.power = fireworks.m_128445_(CraftMetaFirework.FLIGHT.NBT);
        if (!fireworks.m_128441_(CraftMetaFirework.EXPLOSIONS.NBT)) {
            return;
        }
        ListTag fireworkEffects = fireworks.m_128437_(CraftMetaFirework.EXPLOSIONS.NBT, 10);
        this.effects = new ArrayList<FireworkEffect>(fireworkEffects.size());
        ArrayList<FireworkEffect> effects = this.effects;
        int i = 0;
        while (i < fireworkEffects.size()) {
            try {
                effects.add(CraftMetaFirework.getEffect((CompoundTag)fireworkEffects.get(i)));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            ++i;
        }
    }

    static FireworkEffect getEffect(CompoundTag explosion) {
        int color;
        FireworkEffect.Builder effect = FireworkEffect.builder().flicker(explosion.m_128471_(CraftMetaFirework.EXPLOSION_FLICKER.NBT)).trail(explosion.m_128471_(CraftMetaFirework.EXPLOSION_TRAIL.NBT)).with(CraftMetaFirework.getEffectType(0xFF & explosion.m_128445_(CraftMetaFirework.EXPLOSION_TYPE.NBT)));
        int[] colors = explosion.m_128465_(CraftMetaFirework.EXPLOSION_COLORS.NBT);
        if (colors.length == 0) {
            effect.withColor(Color.WHITE);
        }
        int[] nArray = colors;
        int n = colors.length;
        int n2 = 0;
        while (n2 < n) {
            color = nArray[n2];
            effect.withColor(Color.fromRGB(color));
            ++n2;
        }
        nArray = explosion.m_128465_(CraftMetaFirework.EXPLOSION_FADE.NBT);
        n = nArray.length;
        n2 = 0;
        while (n2 < n) {
            color = nArray[n2];
            effect.withFade(Color.fromRGB(color));
            ++n2;
        }
        return effect.build();
    }

    static CompoundTag getExplosion(FireworkEffect effect) {
        CompoundTag explosion = new CompoundTag();
        if (effect.hasFlicker()) {
            explosion.m_128379_(CraftMetaFirework.EXPLOSION_FLICKER.NBT, true);
        }
        if (effect.hasTrail()) {
            explosion.m_128379_(CraftMetaFirework.EXPLOSION_TRAIL.NBT, true);
        }
        CraftMetaFirework.addColors(explosion, EXPLOSION_COLORS, effect.getColors());
        CraftMetaFirework.addColors(explosion, EXPLOSION_FADE, effect.getFadeColors());
        explosion.m_128344_(CraftMetaFirework.EXPLOSION_TYPE.NBT, (byte)CraftMetaFirework.getNBT(effect.getType()));
        return explosion;
    }

    static int getNBT(FireworkEffect.Type type) {
        switch (type) {
            case BALL: {
                return 0;
            }
            case BALL_LARGE: {
                return 1;
            }
            case STAR: {
                return 2;
            }
            case CREEPER: {
                return 3;
            }
            case BURST: {
                return 4;
            }
        }
        throw new IllegalArgumentException("Unknown effect type " + (Object)((Object)type));
    }

    static FireworkEffect.Type getEffectType(int nbt) {
        switch (nbt) {
            case 0: {
                return FireworkEffect.Type.BALL;
            }
            case 1: {
                return FireworkEffect.Type.BALL_LARGE;
            }
            case 2: {
                return FireworkEffect.Type.STAR;
            }
            case 3: {
                return FireworkEffect.Type.CREEPER;
            }
            case 4: {
                return FireworkEffect.Type.BURST;
            }
        }
        throw new IllegalArgumentException("Unknown effect type " + nbt);
    }

    CraftMetaFirework(Map<String, Object> map) {
        super(map);
        Integer power = CraftMetaItem.SerializableMeta.getObject(Integer.class, map, CraftMetaFirework.FLIGHT.BUKKIT, true);
        if (power != null) {
            this.power = power;
        }
        Iterable effects = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaFirework.EXPLOSIONS.BUKKIT, true);
        this.safelyAddEffects(effects);
    }

    @Override
    public boolean hasEffects() {
        return this.effects != null && !this.effects.isEmpty();
    }

    void safelyAddEffects(Iterable<?> collection) {
        if (collection == null || collection instanceof Collection && ((Collection)collection).isEmpty()) {
            return;
        }
        List<FireworkEffect> effects = this.effects;
        if (effects == null) {
            effects = this.effects = new ArrayList<FireworkEffect>();
        }
        for (Object obj : collection) {
            Preconditions.checkArgument((boolean)(obj instanceof FireworkEffect), (String)"%s in %s is not a FireworkEffect", obj, collection);
            effects.add((FireworkEffect)obj);
        }
    }

    @Override
    void applyToItem(CompoundTag itemTag) {
        super.applyToItem(itemTag);
        if (this.isFireworkEmpty()) {
            return;
        }
        CompoundTag fireworks = itemTag.m_128469_(CraftMetaFirework.FIREWORKS.NBT);
        itemTag.m_128365_(CraftMetaFirework.FIREWORKS.NBT, (Tag)fireworks);
        if (this.hasEffects()) {
            ListTag effects = new ListTag();
            for (FireworkEffect effect : this.effects) {
                effects.add((Object)CraftMetaFirework.getExplosion(effect));
            }
            if (effects.size() > 0) {
                fireworks.m_128365_(CraftMetaFirework.EXPLOSIONS.NBT, (Tag)effects);
            }
        }
        if (this.hasPower()) {
            fireworks.m_128344_(CraftMetaFirework.FLIGHT.NBT, this.power.byteValue());
        }
    }

    static void addColors(CompoundTag compound, CraftMetaItem.ItemMetaKey key, List<Color> colors) {
        if (colors.isEmpty()) {
            return;
        }
        int[] colorArray = new int[colors.size()];
        int i = 0;
        for (Color color : colors) {
            colorArray[i++] = color.asRGB();
        }
        compound.m_128385_(key.NBT, colorArray);
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.FIREWORK_ROCKET;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isFireworkEmpty();
    }

    boolean isFireworkEmpty() {
        return !this.hasEffects() && !this.hasPower();
    }

    boolean hasPower() {
        return this.power != null && this.power != 0;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaFirework var2_3) {
            return (this.hasPower() ? that.hasPower() && this.power == that.power : !that.hasPower()) && (this.hasEffects() ? that.hasEffects() && this.effects.equals(that.effects) : !that.hasEffects());
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaFirework || this.isFireworkEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasPower()) {
            hash = 61 * hash + this.power;
        }
        if (this.hasEffects()) {
            hash = 61 * hash + 13 * this.effects.hashCode();
        }
        return hash != original ? CraftMetaFirework.class.hashCode() ^ hash : hash;
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasEffects()) {
            builder.put((Object)CraftMetaFirework.EXPLOSIONS.BUKKIT, (Object)ImmutableList.copyOf(this.effects));
        }
        if (this.hasPower()) {
            builder.put((Object)CraftMetaFirework.FLIGHT.BUKKIT, (Object)this.power);
        }
        return builder;
    }

    @Override
    public CraftMetaFirework clone() {
        CraftMetaFirework meta = (CraftMetaFirework)super.clone();
        if (this.effects != null) {
            meta.effects = new ArrayList<FireworkEffect>(this.effects);
        }
        return meta;
    }

    @Override
    public void addEffect(FireworkEffect effect) {
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (Object)"FireworkEffect cannot be null");
        if (this.effects == null) {
            this.effects = new ArrayList<FireworkEffect>();
        }
        this.effects.add(effect);
    }

    @Override
    public void addEffects(FireworkEffect ... effects) {
        Preconditions.checkArgument((effects != null ? 1 : 0) != 0, (Object)"effects cannot be null");
        if (effects.length == 0) {
            return;
        }
        List<FireworkEffect> list = this.effects;
        if (list == null) {
            list = this.effects = new ArrayList<FireworkEffect>();
        }
        FireworkEffect[] fireworkEffectArray = effects;
        int n = effects.length;
        int n2 = 0;
        while (n2 < n) {
            FireworkEffect effect = fireworkEffectArray[n2];
            Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (Object)"effects cannot contain null FireworkEffect");
            list.add(effect);
            ++n2;
        }
    }

    @Override
    public void addEffects(Iterable<FireworkEffect> effects) {
        Preconditions.checkArgument((effects != null ? 1 : 0) != 0, (Object)"effects cannot be null");
        this.safelyAddEffects(effects);
    }

    @Override
    public List<FireworkEffect> getEffects() {
        return this.effects == null ? ImmutableList.of() : ImmutableList.copyOf(this.effects);
    }

    @Override
    public int getEffectsSize() {
        return this.effects == null ? 0 : this.effects.size();
    }

    @Override
    public void removeEffect(int index) {
        if (this.effects == null) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: 0");
        }
        this.effects.remove(index);
    }

    @Override
    public void clearEffects() {
        this.effects = null;
    }

    @Override
    public int getPower() {
        return this.hasPower() ? this.power : 0;
    }

    @Override
    public void setPower(int power) {
        Preconditions.checkArgument((power >= 0 ? 1 : 0) != 0, (String)"power cannot be less than zero: %s", (int)power);
        Preconditions.checkArgument((power < 128 ? 1 : 0) != 0, (String)"power cannot be more than 127: %s", (int)power);
        this.power = power;
    }
}

