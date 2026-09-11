/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Multimap
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  com.mojang.authlib.properties.PropertyMap
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.Util
 *  net.minecraft.server.dedicated.DedicatedServer
 *  org.apache.commons.lang.StringUtils
 */
package org.bukkit.craftbukkit.v1_20_R1.profile;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.server.dedicated.DedicatedServer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.configuration.ConfigSerializationUtil;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerTextures;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftProfileProperty;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

@SerializableAs(value="PlayerProfile")
public final class CraftPlayerProfile
implements PlayerProfile {
    private final UUID uniqueId;
    private final String name;
    private final PropertyMap properties = new PropertyMap();
    private final CraftPlayerTextures textures = new CraftPlayerTextures(this);

    @Nonnull
    public static GameProfile validateSkullProfile(@Nonnull GameProfile gameProfile) {
        boolean isValidSkullProfile = gameProfile.getName() != null || gameProfile.getProperties().containsKey((Object)"textures");
        Preconditions.checkArgument((boolean)isValidSkullProfile, (Object)"The skull profile is missing a name or textures!");
        return gameProfile;
    }

    @Nullable
    public static Property getProperty(@Nonnull GameProfile profile, String propertyName) {
        return (Property)Iterables.getFirst((Iterable)profile.getProperties().get((Object)propertyName), null);
    }

    public CraftPlayerProfile(UUID uniqueId, String name) {
        Preconditions.checkArgument((uniqueId != null || !StringUtils.isBlank((String)name) ? 1 : 0) != 0, (Object)"uniqueId is null or name is blank");
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public CraftPlayerProfile(@Nonnull GameProfile gameProfile) {
        this(gameProfile.getId(), gameProfile.getName());
        this.properties.putAll((Multimap)gameProfile.getProperties());
    }

    private CraftPlayerProfile(@Nonnull CraftPlayerProfile other) {
        this(other.uniqueId, other.name);
        this.properties.putAll((Multimap)other.properties);
        this.textures.copyFrom(other.textures);
    }

    @Override
    public UUID getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Nullable
    Property getProperty(String propertyName) {
        return (Property)Iterables.getFirst((Iterable)this.properties.get((Object)propertyName), null);
    }

    void setProperty(String propertyName, @Nullable Property property) {
        this.removeProperty(propertyName);
        if (property != null) {
            this.properties.put((Object)property.getName(), (Object)property);
        }
    }

    void removeProperty(String propertyName) {
        this.properties.removeAll((Object)propertyName);
    }

    void rebuildDirtyProperties() {
        this.textures.rebuildPropertyIfDirty();
    }

    @Override
    public CraftPlayerTextures getTextures() {
        return this.textures;
    }

    @Override
    public void setTextures(@Nullable PlayerTextures textures) {
        if (textures == null) {
            this.textures.clear();
        } else {
            this.textures.copyFrom(textures);
        }
    }

    @Override
    public boolean isComplete() {
        return this.uniqueId != null && this.name != null && !this.textures.isEmpty();
    }

    @Override
    public CompletableFuture<PlayerProfile> update() {
        return CompletableFuture.supplyAsync(this::getUpdatedProfile, Util.m_183991_());
    }

    private CraftPlayerProfile getUpdatedProfile() {
        GameProfile newProfile;
        DedicatedServer server = ((CraftServer)Bukkit.getServer()).getServer();
        GameProfile profile = this.buildGameProfile();
        if (profile.getId() == null) {
            profile = server.m_129927_().m_10996_(profile.getName()).orElse(profile);
        }
        if (profile.getId() != null && (newProfile = server.m_129925_().fillProfileProperties(profile, true)) != null) {
            profile = newProfile;
        }
        return new CraftPlayerProfile(profile);
    }

    @Nonnull
    public GameProfile buildGameProfile() {
        this.rebuildDirtyProperties();
        GameProfile profile = new GameProfile(this.uniqueId, this.name);
        profile.getProperties().putAll((Multimap)this.properties);
        return profile;
    }

    public String toString() {
        this.rebuildDirtyProperties();
        StringBuilder builder = new StringBuilder();
        builder.append("CraftPlayerProfile [uniqueId=");
        builder.append(this.uniqueId);
        builder.append(", name=");
        builder.append(this.name);
        builder.append(", properties=");
        builder.append(CraftPlayerProfile.toString(this.properties));
        builder.append("]");
        return builder.toString();
    }

    private static String toString(@Nonnull PropertyMap propertyMap) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        propertyMap.asMap().forEach((propertyName, properties) -> {
            builder.append((String)propertyName);
            builder.append("=");
            builder.append(properties.stream().map(CraftProfileProperty::toString).collect(Collectors.joining(",", "[", "]")));
        });
        builder.append("}");
        return builder.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CraftPlayerProfile)) {
            return false;
        }
        CraftPlayerProfile other = (CraftPlayerProfile)obj;
        if (!Objects.equals(this.uniqueId, other.uniqueId)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        this.rebuildDirtyProperties();
        other.rebuildDirtyProperties();
        return CraftPlayerProfile.equals(this.properties, other.properties);
    }

    private static boolean equals(@Nonnull PropertyMap propertyMap, @Nonnull PropertyMap other) {
        if (propertyMap.size() != other.size()) {
            return false;
        }
        Iterator iterator1 = propertyMap.values().iterator();
        Iterator iterator2 = other.values().iterator();
        while (iterator1.hasNext()) {
            Property property2;
            if (!iterator2.hasNext()) {
                return false;
            }
            Property property1 = (Property)iterator1.next();
            if (CraftProfileProperty.equals(property1, property2 = (Property)iterator2.next())) continue;
            return false;
        }
        return !iterator2.hasNext();
    }

    public int hashCode() {
        this.rebuildDirtyProperties();
        int result = 1;
        result = 31 * result + Objects.hashCode(this.uniqueId);
        result = 31 * result + Objects.hashCode(this.name);
        result = 31 * result + CraftPlayerProfile.hashCode(this.properties);
        return result;
    }

    private static int hashCode(PropertyMap propertyMap) {
        int result = 1;
        for (Property property : propertyMap.values()) {
            result = 31 * result + CraftProfileProperty.hashCode(property);
        }
        return result;
    }

    @Override
    public CraftPlayerProfile clone() {
        return new CraftPlayerProfile(this);
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        if (this.uniqueId != null) {
            map.put("uniqueId", this.uniqueId.toString());
        }
        if (this.name != null) {
            map.put("name", this.name);
        }
        this.rebuildDirtyProperties();
        if (!this.properties.isEmpty()) {
            ArrayList propertiesData = new ArrayList();
            this.properties.forEach((propertyName, property) -> propertiesData.add(CraftProfileProperty.serialize(property)));
            map.put("properties", propertiesData);
        }
        return map;
    }

    public static CraftPlayerProfile deserialize(Map<String, Object> map) {
        UUID uniqueId = ConfigSerializationUtil.getUuid(map, "uniqueId", true);
        String name = ConfigSerializationUtil.getString(map, "name", true);
        CraftPlayerProfile profile = new CraftPlayerProfile(uniqueId, name);
        if (map.containsKey("properties")) {
            for (Object propertyData : (List)map.get("properties")) {
                Preconditions.checkArgument((boolean)(propertyData instanceof Map), (String)"Propertu data (%s) is not a valid Map", propertyData);
                Property property = CraftProfileProperty.deserialize((Map)propertyData);
                profile.properties.put((Object)property.getName(), (Object)property);
            }
        }
        return profile;
    }
}

