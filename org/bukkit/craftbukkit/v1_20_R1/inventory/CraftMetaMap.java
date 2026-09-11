/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.IntTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaMap
extends CraftMetaItem
implements MapMeta {
    static final CraftMetaItem.ItemMetaKey MAP_SCALING = new CraftMetaItem.ItemMetaKey("map_is_scaling", "scaling");
    @Deprecated
    static final CraftMetaItem.ItemMetaKey MAP_LOC_NAME = new CraftMetaItem.ItemMetaKey("LocName", "display-loc-name");
    static final CraftMetaItem.ItemMetaKey MAP_COLOR = new CraftMetaItem.ItemMetaKey("MapColor", "display-map-color");
    static final CraftMetaItem.ItemMetaKey MAP_ID = new CraftMetaItem.ItemMetaKey("map", "map-id");
    static final byte SCALING_EMPTY = 0;
    static final byte SCALING_TRUE = 1;
    static final byte SCALING_FALSE = 2;
    private Integer mapId;
    private byte scaling = 0;
    private Color color;

    public CraftMetaMap(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaMap)) {
            return;
        }
        CraftMetaMap map = (CraftMetaMap)meta;
        this.mapId = map.mapId;
        this.scaling = map.scaling;
        this.color = map.color;
    }

    CraftMetaMap(CompoundTag tag) {
        super(tag);
        CompoundTag display;
        if (tag.m_128425_(CraftMetaMap.MAP_ID.NBT, 99)) {
            this.mapId = tag.m_128451_(CraftMetaMap.MAP_ID.NBT);
        }
        if (tag.m_128441_(CraftMetaMap.MAP_SCALING.NBT)) {
            this.scaling = (byte)(tag.m_128471_(CraftMetaMap.MAP_SCALING.NBT) ? 1 : 2);
        }
        if (tag.m_128441_(CraftMetaMap.DISPLAY.NBT) && (display = tag.m_128469_(CraftMetaMap.DISPLAY.NBT)).m_128441_(CraftMetaMap.MAP_COLOR.NBT)) {
            try {
                this.color = Color.fromRGB(display.m_128451_(CraftMetaMap.MAP_COLOR.NBT));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
    }

    CraftMetaMap(Map<String, Object> map) {
        super(map);
        Color color;
        String locName;
        Boolean scaling;
        Integer id = CraftMetaItem.SerializableMeta.getObject(Integer.class, map, CraftMetaMap.MAP_ID.BUKKIT, true);
        if (id != null) {
            this.setMapId(id);
        }
        if ((scaling = CraftMetaItem.SerializableMeta.getObject(Boolean.class, map, CraftMetaMap.MAP_SCALING.BUKKIT, true)) != null) {
            this.setScaling(scaling);
        }
        if ((locName = CraftMetaItem.SerializableMeta.getString(map, CraftMetaMap.MAP_LOC_NAME.BUKKIT, true)) != null) {
            this.setLocationName(locName);
        }
        if ((color = CraftMetaItem.SerializableMeta.getObject(Color.class, map, CraftMetaMap.MAP_COLOR.BUKKIT, true)) != null) {
            this.setColor(color);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.hasMapId()) {
            tag.m_128405_(CraftMetaMap.MAP_ID.NBT, this.getMapId());
        }
        if (this.hasScaling()) {
            tag.m_128379_(CraftMetaMap.MAP_SCALING.NBT, this.isScaling());
        }
        if (this.hasLocationName()) {
            this.setDisplayTag(tag, CraftMetaMap.MAP_LOC_NAME.NBT, (Tag)StringTag.m_129297_((String)this.getLocationName()));
        }
        if (this.hasColor()) {
            this.setDisplayTag(tag, CraftMetaMap.MAP_COLOR.NBT, (Tag)IntTag.m_128679_((int)this.color.asRGB()));
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.FILLED_MAP;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isMapEmpty();
    }

    boolean isMapEmpty() {
        return !this.hasMapId() && !(this.hasScaling() | this.hasLocationName()) && !this.hasColor();
    }

    @Override
    public boolean hasMapId() {
        return this.mapId != null;
    }

    @Override
    public int getMapId() {
        return this.mapId;
    }

    @Override
    public void setMapId(int id) {
        this.mapId = id;
    }

    @Override
    public boolean hasMapView() {
        return this.mapId != null;
    }

    @Override
    public MapView getMapView() {
        Preconditions.checkState((boolean)this.hasMapView(), (Object)"Item does not have map associated - check hasMapView() first!");
        return Bukkit.getMap(this.mapId);
    }

    @Override
    public void setMapView(MapView map) {
        this.mapId = map != null ? Integer.valueOf(map.getId()) : null;
    }

    boolean hasScaling() {
        return this.scaling != 0;
    }

    @Override
    public boolean isScaling() {
        return this.scaling == 1;
    }

    @Override
    public void setScaling(boolean scaling) {
        this.scaling = (byte)(scaling ? 1 : 2);
    }

    @Override
    public boolean hasLocationName() {
        return this.hasLocalizedName();
    }

    @Override
    public String getLocationName() {
        return this.getLocalizedName();
    }

    @Override
    public void setLocationName(String name) {
        this.setLocalizedName(name);
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
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaMap) {
            CraftMetaMap that = (CraftMetaMap)meta;
            return this.scaling == that.scaling && (this.hasMapId() ? that.hasMapId() && this.mapId.equals(that.mapId) : !that.hasMapId()) && (this.hasColor() ? that.hasColor() && this.color.equals(that.color) : !that.hasColor());
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaMap || this.isMapEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasMapId()) {
            hash = 61 * hash + this.mapId.hashCode();
        }
        if (this.hasScaling()) {
            hash ^= 0x22222222 << (this.isScaling() ? 1 : -1);
        }
        if (this.hasColor()) {
            hash = 61 * hash + this.color.hashCode();
        }
        return original != hash ? CraftMetaMap.class.hashCode() ^ hash : hash;
    }

    @Override
    public CraftMetaMap clone() {
        return (CraftMetaMap)super.clone();
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasMapId()) {
            builder.put((Object)CraftMetaMap.MAP_ID.BUKKIT, (Object)this.getMapId());
        }
        if (this.hasScaling()) {
            builder.put((Object)CraftMetaMap.MAP_SCALING.BUKKIT, (Object)this.isScaling());
        }
        if (this.hasColor()) {
            builder.put((Object)CraftMetaMap.MAP_COLOR.BUKKIT, (Object)this.getColor());
        }
        return builder;
    }
}

