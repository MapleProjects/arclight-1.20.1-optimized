/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  com.mojang.authlib.properties.Property
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package org.bukkit.craftbukkit.v1_20_R1.profile;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.properties.Property;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerProfile;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftProfileProperty;
import org.bukkit.craftbukkit.v1_20_R1.util.JsonHelper;
import org.bukkit.profile.PlayerTextures;

final class CraftPlayerTextures
implements PlayerTextures {
    static final String PROPERTY_NAME = "textures";
    private static final String MINECRAFT_HOST = "textures.minecraft.net";
    private static final String MINECRAFT_PATH = "/texture/";
    private final CraftPlayerProfile profile;
    private boolean loaded = false;
    private JsonObject data;
    private long timestamp;
    private URL skin;
    private PlayerTextures.SkinModel skinModel = PlayerTextures.SkinModel.CLASSIC;
    private URL cape;
    private boolean dirty = false;

    private static void validateTextureUrl(@Nullable URL url) {
        if (url == null) {
            return;
        }
        Preconditions.checkArgument((boolean)url.getHost().equals(MINECRAFT_HOST), (String)"Expected host '%s' but got '%s'", (Object)MINECRAFT_HOST, (Object)url.getHost());
        Preconditions.checkArgument((boolean)url.getPath().startsWith(MINECRAFT_PATH), (String)"Expected path starting with '%s' but got '%s", (Object)MINECRAFT_PATH, (Object)url.getPath());
    }

    @Nullable
    private static URL parseUrl(@Nullable String urlString) {
        if (urlString == null) {
            return null;
        }
        try {
            return new URL(urlString);
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    @Nullable
    private static PlayerTextures.SkinModel parseSkinModel(@Nullable String skinModelName) {
        if (skinModelName == null) {
            return null;
        }
        try {
            return PlayerTextures.SkinModel.valueOf(skinModelName.toUpperCase(Locale.ROOT));
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    CraftPlayerTextures(@Nonnull CraftPlayerProfile profile) {
        this.profile = profile;
    }

    void copyFrom(@Nonnull PlayerTextures other) {
        if (other == this) {
            return;
        }
        Preconditions.checkArgument((boolean)(other instanceof CraftPlayerTextures), (String)"Expecting CraftPlayerTextures, got %s", (Object)other.getClass().getName());
        CraftPlayerTextures otherTextures = (CraftPlayerTextures)other;
        this.clear();
        Property texturesProperty = otherTextures.getProperty();
        this.profile.setProperty(PROPERTY_NAME, texturesProperty);
        if (!(texturesProperty == null || Objects.equals(this.profile.getUniqueId(), otherTextures.profile.getUniqueId()) && Objects.equals(this.profile.getName(), otherTextures.profile.getName()))) {
            this.ensureLoaded();
            this.markDirty();
            this.rebuildPropertyIfDirty();
        }
    }

    private void ensureLoaded() {
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        Property property = this.getProperty();
        if (property == null) {
            return;
        }
        this.data = CraftProfileProperty.decodePropertyValue(property.getValue());
        if (this.data != null) {
            JsonObject texturesMap = JsonHelper.getObjectOrNull(this.data, PROPERTY_NAME);
            this.loadSkin(texturesMap);
            this.loadCape(texturesMap);
            this.loadTimestamp();
        }
    }

    private void loadSkin(@Nullable JsonObject texturesMap) {
        if (texturesMap == null) {
            return;
        }
        JsonObject texture = JsonHelper.getObjectOrNull(texturesMap, MinecraftProfileTexture.Type.SKIN.name());
        if (texture == null) {
            return;
        }
        String skinUrlString = JsonHelper.getStringOrNull(texture, "url");
        this.skin = CraftPlayerTextures.parseUrl(skinUrlString);
        this.skinModel = CraftPlayerTextures.loadSkinModel(texture);
        if (this.skinModel == null && this.skin != null) {
            this.skinModel = PlayerTextures.SkinModel.CLASSIC;
        }
    }

    @Nullable
    private static PlayerTextures.SkinModel loadSkinModel(@Nullable JsonObject texture) {
        if (texture == null) {
            return null;
        }
        JsonObject metadata = JsonHelper.getObjectOrNull(texture, "metadata");
        if (metadata == null) {
            return null;
        }
        String skinModelName = JsonHelper.getStringOrNull(metadata, "model");
        return CraftPlayerTextures.parseSkinModel(skinModelName);
    }

    private void loadCape(@Nullable JsonObject texturesMap) {
        if (texturesMap == null) {
            return;
        }
        JsonObject texture = JsonHelper.getObjectOrNull(texturesMap, MinecraftProfileTexture.Type.CAPE.name());
        if (texture == null) {
            return;
        }
        String skinUrlString = JsonHelper.getStringOrNull(texture, "url");
        this.cape = CraftPlayerTextures.parseUrl(skinUrlString);
    }

    private void loadTimestamp() {
        if (this.data == null) {
            return;
        }
        JsonPrimitive timestamp = JsonHelper.getPrimitiveOrNull(this.data, "timestamp");
        if (timestamp == null) {
            return;
        }
        try {
            this.timestamp = timestamp.getAsLong();
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
    }

    private void markDirty() {
        this.dirty = true;
        this.data = null;
        this.timestamp = 0L;
    }

    @Override
    public boolean isEmpty() {
        this.ensureLoaded();
        return this.skin == null && this.cape == null;
    }

    @Override
    public void clear() {
        this.profile.removeProperty(PROPERTY_NAME);
        this.loaded = false;
        this.data = null;
        this.timestamp = 0L;
        this.skin = null;
        this.skinModel = PlayerTextures.SkinModel.CLASSIC;
        this.cape = null;
        this.dirty = false;
    }

    @Override
    public URL getSkin() {
        this.ensureLoaded();
        return this.skin;
    }

    @Override
    public void setSkin(URL skinUrl) {
        this.setSkin(skinUrl, PlayerTextures.SkinModel.CLASSIC);
    }

    @Override
    public void setSkin(URL skinUrl, PlayerTextures.SkinModel skinModel) {
        CraftPlayerTextures.validateTextureUrl(skinUrl);
        if (skinModel == null) {
            skinModel = PlayerTextures.SkinModel.CLASSIC;
        }
        if (Objects.equals(this.getSkin(), skinUrl) && Objects.equals((Object)this.getSkinModel(), (Object)skinModel)) {
            return;
        }
        this.skin = skinUrl;
        this.skinModel = skinUrl != null ? skinModel : PlayerTextures.SkinModel.CLASSIC;
        this.markDirty();
    }

    @Override
    public PlayerTextures.SkinModel getSkinModel() {
        this.ensureLoaded();
        return this.skinModel;
    }

    @Override
    public URL getCape() {
        this.ensureLoaded();
        return this.cape;
    }

    @Override
    public void setCape(URL capeUrl) {
        CraftPlayerTextures.validateTextureUrl(capeUrl);
        if (Objects.equals(this.getCape(), capeUrl)) {
            return;
        }
        this.cape = capeUrl;
        this.markDirty();
    }

    @Override
    public long getTimestamp() {
        this.ensureLoaded();
        return this.timestamp;
    }

    @Override
    public boolean isSigned() {
        if (this.dirty) {
            return false;
        }
        Property property = this.getProperty();
        return property != null && CraftProfileProperty.hasValidSignature(property);
    }

    @Nullable
    Property getProperty() {
        this.rebuildPropertyIfDirty();
        return this.profile.getProperty(PROPERTY_NAME);
    }

    void rebuildPropertyIfDirty() {
        JsonObject skinTexture;
        JsonObject texturesMap;
        if (!this.dirty) {
            return;
        }
        this.dirty = false;
        if (this.isEmpty()) {
            this.profile.removeProperty(PROPERTY_NAME);
            return;
        }
        JsonObject propertyData = new JsonObject();
        if (this.skin != null) {
            texturesMap = JsonHelper.getOrCreateObject(propertyData, PROPERTY_NAME);
            skinTexture = JsonHelper.getOrCreateObject(texturesMap, MinecraftProfileTexture.Type.SKIN.name());
            skinTexture.addProperty("url", this.skin.toExternalForm());
            if (this.skinModel != PlayerTextures.SkinModel.CLASSIC) {
                JsonObject metadata = JsonHelper.getOrCreateObject(skinTexture, "metadata");
                metadata.addProperty("model", this.skinModel.name().toLowerCase(Locale.ROOT));
            }
        }
        if (this.cape != null) {
            texturesMap = JsonHelper.getOrCreateObject(propertyData, PROPERTY_NAME);
            skinTexture = JsonHelper.getOrCreateObject(texturesMap, MinecraftProfileTexture.Type.CAPE.name());
            skinTexture.addProperty("url", this.cape.toExternalForm());
        }
        this.data = propertyData;
        String encodedTexturesData = CraftProfileProperty.encodePropertyValue(propertyData, CraftProfileProperty.JsonFormatter.COMPACT);
        Property property = new Property(PROPERTY_NAME, encodedTexturesData);
        this.profile.setProperty(PROPERTY_NAME, property);
    }

    private JsonObject getData() {
        this.ensureLoaded();
        this.rebuildPropertyIfDirty();
        return this.data;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CraftPlayerTextures [data=");
        builder.append(this.getData());
        builder.append("]");
        return builder.toString();
    }

    public int hashCode() {
        Property property = this.getProperty();
        return property == null ? 0 : CraftProfileProperty.hashCode(property);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CraftPlayerTextures)) {
            return false;
        }
        CraftPlayerTextures other = (CraftPlayerTextures)obj;
        Property property = this.getProperty();
        Property otherProperty = other.getProperty();
        return CraftProfileProperty.equals(property, otherProperty);
    }
}

