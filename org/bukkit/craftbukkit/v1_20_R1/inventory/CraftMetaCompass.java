/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.Level
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import java.util.Map;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.CompassMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaCompass
extends CraftMetaItem
implements CompassMeta {
    static final CraftMetaItem.ItemMetaKey LODESTONE_DIMENSION = new CraftMetaItem.ItemMetaKey("LodestoneDimension");
    static final CraftMetaItem.ItemMetaKey LODESTONE_POS = new CraftMetaItem.ItemMetaKey("LodestonePos", "lodestone");
    static final CraftMetaItem.ItemMetaKey LODESTONE_POS_WORLD = new CraftMetaItem.ItemMetaKey("LodestonePosWorld");
    static final CraftMetaItem.ItemMetaKey LODESTONE_POS_X = new CraftMetaItem.ItemMetaKey("LodestonePosX");
    static final CraftMetaItem.ItemMetaKey LODESTONE_POS_Y = new CraftMetaItem.ItemMetaKey("LodestonePosY");
    static final CraftMetaItem.ItemMetaKey LODESTONE_POS_Z = new CraftMetaItem.ItemMetaKey("LodestonePosZ");
    static final CraftMetaItem.ItemMetaKey LODESTONE_TRACKED = new CraftMetaItem.ItemMetaKey("LodestoneTracked");
    private StringTag lodestoneWorld;
    private int lodestoneX;
    private int lodestoneY;
    private int lodestoneZ;
    private Boolean tracked;

    CraftMetaCompass(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaCompass)) {
            return;
        }
        CraftMetaCompass compassMeta = (CraftMetaCompass)meta;
        this.lodestoneWorld = compassMeta.lodestoneWorld;
        this.lodestoneX = compassMeta.lodestoneX;
        this.lodestoneY = compassMeta.lodestoneY;
        this.lodestoneZ = compassMeta.lodestoneZ;
        this.tracked = compassMeta.tracked;
    }

    CraftMetaCompass(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaCompass.LODESTONE_DIMENSION.NBT) && tag.m_128441_(CraftMetaCompass.LODESTONE_POS.NBT)) {
            this.lodestoneWorld = (StringTag)tag.m_128423_(CraftMetaCompass.LODESTONE_DIMENSION.NBT);
            CompoundTag pos = tag.m_128469_(CraftMetaCompass.LODESTONE_POS.NBT);
            this.lodestoneX = pos.m_128451_("X");
            this.lodestoneY = pos.m_128451_("Y");
            this.lodestoneZ = pos.m_128451_("Z");
        }
        if (tag.m_128441_(CraftMetaCompass.LODESTONE_TRACKED.NBT)) {
            this.tracked = tag.m_128471_(CraftMetaCompass.LODESTONE_TRACKED.NBT);
        }
    }

    CraftMetaCompass(Map<String, Object> map) {
        super(map);
        String lodestoneWorldString = CraftMetaItem.SerializableMeta.getString(map, CraftMetaCompass.LODESTONE_POS_WORLD.BUKKIT, true);
        if (lodestoneWorldString != null) {
            this.lodestoneWorld = StringTag.m_129297_((String)lodestoneWorldString);
            this.lodestoneX = (Integer)map.get(CraftMetaCompass.LODESTONE_POS_X.BUKKIT);
            this.lodestoneY = (Integer)map.get(CraftMetaCompass.LODESTONE_POS_Y.BUKKIT);
            this.lodestoneZ = (Integer)map.get(CraftMetaCompass.LODESTONE_POS_Z.BUKKIT);
        } else {
            Location lodestone = CraftMetaItem.SerializableMeta.getObject(Location.class, map, CraftMetaCompass.LODESTONE_POS.BUKKIT, true);
            if (lodestone != null && lodestone.getWorld() != null) {
                this.setLodestone(lodestone);
            }
        }
        this.tracked = CraftMetaItem.SerializableMeta.getBoolean(map, CraftMetaCompass.LODESTONE_TRACKED.BUKKIT);
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.lodestoneWorld != null) {
            CompoundTag pos = new CompoundTag();
            pos.m_128405_("X", this.lodestoneX);
            pos.m_128405_("Y", this.lodestoneY);
            pos.m_128405_("Z", this.lodestoneZ);
            tag.m_128365_(CraftMetaCompass.LODESTONE_POS.NBT, (Tag)pos);
            tag.m_128365_(CraftMetaCompass.LODESTONE_DIMENSION.NBT, (Tag)this.lodestoneWorld);
        }
        if (this.tracked != null) {
            tag.m_128379_(CraftMetaCompass.LODESTONE_TRACKED.NBT, this.tracked.booleanValue());
        }
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isCompassEmpty();
    }

    boolean isCompassEmpty() {
        return !this.hasLodestone() && !this.hasLodestoneTracked();
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.COMPASS;
    }

    @Override
    public CraftMetaCompass clone() {
        CraftMetaCompass clone = (CraftMetaCompass)super.clone();
        return clone;
    }

    @Override
    public boolean hasLodestone() {
        return this.lodestoneWorld != null;
    }

    @Override
    public Location getLodestone() {
        if (this.lodestoneWorld == null) {
            return null;
        }
        Optional key = Level.f_46427_.parse((DynamicOps)NbtOps.f_128958_, (Object)this.lodestoneWorld).result();
        ServerLevel worldServer = key.isPresent() ? MinecraftServer.getServer().m_129880_((ResourceKey)key.get()) : null;
        CraftWorld world = worldServer != null ? worldServer.getWorld() : null;
        return new Location(world, this.lodestoneX, this.lodestoneY, this.lodestoneZ);
    }

    @Override
    public void setLodestone(Location lodestone) {
        Preconditions.checkArgument((lodestone == null || lodestone.getWorld() != null ? 1 : 0) != 0, (Object)"world is null");
        if (lodestone == null) {
            this.lodestoneWorld = null;
        } else {
            ResourceKey key = ((CraftWorld)lodestone.getWorld()).getHandle().m_46472_();
            DataResult dataresult = Level.f_46427_.encodeStart((DynamicOps)NbtOps.f_128958_, (Object)key);
            this.lodestoneWorld = (StringTag)dataresult.get().orThrow();
            this.lodestoneX = lodestone.getBlockX();
            this.lodestoneY = lodestone.getBlockY();
            this.lodestoneZ = lodestone.getBlockZ();
        }
    }

    boolean hasLodestoneTracked() {
        return this.tracked != null;
    }

    @Override
    public boolean isLodestoneTracked() {
        return this.hasLodestoneTracked() && this.tracked != false;
    }

    @Override
    public void setLodestoneTracked(boolean tracked) {
        this.tracked = tracked;
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasLodestone()) {
            hash = 73 * hash + this.lodestoneWorld.hashCode();
            hash = 73 * hash + this.lodestoneX;
            hash = 73 * hash + this.lodestoneY;
            hash = 73 * hash + this.lodestoneZ;
        }
        if (this.hasLodestoneTracked()) {
            hash = 73 * hash + (this.isLodestoneTracked() ? 1231 : 1237);
        }
        return original != hash ? CraftMetaCompass.class.hashCode() ^ hash : hash;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaCompass) {
            CraftMetaCompass that = (CraftMetaCompass)meta;
            return (this.hasLodestone() ? that.hasLodestone() && this.lodestoneWorld.m_7916_().equals(that.lodestoneWorld.m_7916_()) && this.lodestoneX == that.lodestoneX && this.lodestoneY == that.lodestoneY && this.lodestoneZ == that.lodestoneZ : !that.hasLodestone()) && this.tracked == that.tracked;
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaCompass || this.isCompassEmpty());
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasLodestone()) {
            builder.put((Object)CraftMetaCompass.LODESTONE_POS_WORLD.BUKKIT, (Object)this.lodestoneWorld.m_7916_());
            builder.put((Object)CraftMetaCompass.LODESTONE_POS_X.BUKKIT, (Object)this.lodestoneX);
            builder.put((Object)CraftMetaCompass.LODESTONE_POS_Y.BUKKIT, (Object)this.lodestoneY);
            builder.put((Object)CraftMetaCompass.LODESTONE_POS_Z.BUKKIT, (Object)this.lodestoneZ);
        }
        if (this.hasLodestoneTracked()) {
            builder.put((Object)CraftMetaCompass.LODESTONE_TRACKED.BUKKIT, (Object)this.tracked);
        }
        return builder;
    }
}

