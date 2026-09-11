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
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.BannerMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaBanner
extends CraftMetaItem
implements BannerMeta {
    private static final Set<Material> BANNER_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.BLACK_BANNER, Material.BLACK_WALL_BANNER, Material.BLUE_BANNER, Material.BLUE_WALL_BANNER, Material.BROWN_BANNER, Material.BROWN_WALL_BANNER, Material.CYAN_BANNER, Material.CYAN_WALL_BANNER, Material.GRAY_BANNER, Material.GRAY_WALL_BANNER, Material.GREEN_BANNER, Material.GREEN_WALL_BANNER, Material.LIGHT_BLUE_BANNER, Material.LIGHT_BLUE_WALL_BANNER, Material.LIGHT_GRAY_BANNER, Material.LIGHT_GRAY_WALL_BANNER, Material.LIME_BANNER, Material.LIME_WALL_BANNER, Material.MAGENTA_BANNER, Material.MAGENTA_WALL_BANNER, Material.ORANGE_BANNER, Material.ORANGE_WALL_BANNER, Material.PINK_BANNER, Material.PINK_WALL_BANNER, Material.PURPLE_BANNER, Material.PURPLE_WALL_BANNER, Material.RED_BANNER, Material.RED_WALL_BANNER, Material.WHITE_BANNER, Material.WHITE_WALL_BANNER, Material.YELLOW_BANNER, Material.YELLOW_WALL_BANNER});
    static final CraftMetaItem.ItemMetaKey BASE = new CraftMetaItem.ItemMetaKey("Base", "base-color");
    static final CraftMetaItem.ItemMetaKey PATTERNS = new CraftMetaItem.ItemMetaKey("Patterns", "patterns");
    static final CraftMetaItem.ItemMetaKey COLOR = new CraftMetaItem.ItemMetaKey("Color", "color");
    static final CraftMetaItem.ItemMetaKey PATTERN = new CraftMetaItem.ItemMetaKey("Pattern", "pattern");
    private DyeColor base;
    private List<Pattern> patterns = new ArrayList<Pattern>();

    public CraftMetaBanner(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaBanner)) {
            return;
        }
        CraftMetaBanner banner = (CraftMetaBanner)meta;
        this.base = banner.base;
        this.patterns = new ArrayList<Pattern>(banner.patterns);
    }

    CraftMetaBanner(CompoundTag tag) {
        super(tag);
        if (!tag.m_128441_("BlockEntityTag")) {
            return;
        }
        CompoundTag entityTag = tag.m_128469_("BlockEntityTag");
        DyeColor dyeColor = this.base = entityTag.m_128441_(CraftMetaBanner.BASE.NBT) ? DyeColor.getByWoolData((byte)entityTag.m_128451_(CraftMetaBanner.BASE.NBT)) : null;
        if (entityTag.m_128441_(CraftMetaBanner.PATTERNS.NBT)) {
            ListTag patterns = entityTag.m_128437_(CraftMetaBanner.PATTERNS.NBT, 10);
            int i = 0;
            while (i < Math.min(patterns.size(), 20)) {
                CompoundTag p = patterns.m_128728_(i);
                DyeColor color = DyeColor.getByWoolData((byte)p.m_128451_(CraftMetaBanner.COLOR.NBT));
                PatternType pattern = PatternType.getByIdentifier(p.m_128461_(CraftMetaBanner.PATTERN.NBT));
                if (color != null && pattern != null) {
                    this.patterns.add(new Pattern(color, pattern));
                }
                ++i;
            }
        }
    }

    CraftMetaBanner(Map<String, Object> map) {
        super(map);
        Iterable rawPatternList;
        String baseStr = CraftMetaItem.SerializableMeta.getString(map, CraftMetaBanner.BASE.BUKKIT, true);
        if (baseStr != null) {
            this.base = DyeColor.legacyValueOf(baseStr);
        }
        if ((rawPatternList = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaBanner.PATTERNS.BUKKIT, true)) == null) {
            return;
        }
        for (Object obj : rawPatternList) {
            Preconditions.checkArgument((boolean)(obj instanceof Pattern), (String)"Object (%s) in pattern list is not valid", obj.getClass());
            this.addPattern((Pattern)obj);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        CompoundTag entityTag = new CompoundTag();
        if (this.base != null) {
            entityTag.m_128405_(CraftMetaBanner.BASE.NBT, (int)this.base.getWoolData());
        }
        ListTag newPatterns = new ListTag();
        for (Pattern p : this.patterns) {
            CompoundTag compound = new CompoundTag();
            compound.m_128405_(CraftMetaBanner.COLOR.NBT, (int)p.getColor().getWoolData());
            compound.m_128359_(CraftMetaBanner.PATTERN.NBT, p.getPattern().getIdentifier());
            newPatterns.add((Object)compound);
        }
        entityTag.m_128365_(CraftMetaBanner.PATTERNS.NBT, (Tag)newPatterns);
        tag.m_128365_("BlockEntityTag", (Tag)entityTag);
    }

    @Override
    public DyeColor getBaseColor() {
        return this.base;
    }

    @Override
    public void setBaseColor(DyeColor color) {
        this.base = color;
    }

    @Override
    public List<Pattern> getPatterns() {
        return new ArrayList<Pattern>(this.patterns);
    }

    @Override
    public void setPatterns(List<Pattern> patterns) {
        this.patterns = new ArrayList<Pattern>(patterns);
    }

    @Override
    public void addPattern(Pattern pattern) {
        this.patterns.add(pattern);
    }

    @Override
    public Pattern getPattern(int i) {
        return this.patterns.get(i);
    }

    @Override
    public Pattern removePattern(int i) {
        return this.patterns.remove(i);
    }

    @Override
    public void setPattern(int i, Pattern pattern) {
        this.patterns.set(i, pattern);
    }

    @Override
    public int numberOfPatterns() {
        return this.patterns.size();
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.base != null) {
            builder.put((Object)CraftMetaBanner.BASE.BUKKIT, (Object)this.base.toString());
        }
        if (!this.patterns.isEmpty()) {
            builder.put((Object)CraftMetaBanner.PATTERNS.BUKKIT, (Object)ImmutableList.copyOf(this.patterns));
        }
        return builder;
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.base != null) {
            hash = 31 * hash + this.base.hashCode();
        }
        if (!this.patterns.isEmpty()) {
            hash = 31 * hash + this.patterns.hashCode();
        }
        return original != hash ? CraftMetaBanner.class.hashCode() ^ hash : hash;
    }

    @Override
    public boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaBanner) {
            CraftMetaBanner that = (CraftMetaBanner)meta;
            return this.base == that.base && this.patterns.equals(that.patterns);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaBanner || this.patterns.isEmpty() && this.base == null);
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.patterns.isEmpty() && this.base == null;
    }

    @Override
    boolean applicableTo(Material type) {
        return BANNER_MATERIALS.contains(type);
    }

    @Override
    public CraftMetaBanner clone() {
        CraftMetaBanner meta = (CraftMetaBanner)super.clone();
        meta.patterns = new ArrayList<Pattern>(this.patterns);
        return meta;
    }
}

