/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 *  com.mojang.authlib.properties.Property
 *  com.mojang.authlib.yggdrasil.ServicesKeySet
 *  com.mojang.authlib.yggdrasil.ServicesKeyType
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package org.bukkit.craftbukkit.v1_20_R1.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.yggdrasil.ServicesKeySet;
import com.mojang.authlib.yggdrasil.ServicesKeyType;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.craftbukkit.v1_20_R1.configuration.ConfigSerializationUtil;

final class CraftProfileProperty {
    private static final ServicesKeySet PUBLIC_KEYS;

    static {
        try {
            PUBLIC_KEYS = new YggdrasilAuthenticationService(Proxy.NO_PROXY).getServicesKeySet();
        }
        catch (Exception e) {
            throw new Error("Could not load yggdrasil_session_pubkey.der! This indicates a bug.");
        }
    }

    public static boolean hasValidSignature(@Nonnull Property property) {
        return property.hasSignature() && PUBLIC_KEYS.keys(ServicesKeyType.PROFILE_PROPERTY).stream().anyMatch(key -> key.validateProperty(property));
    }

    @Nullable
    private static String decodeBase64(@Nonnull String encoded) {
        try {
            return new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Nullable
    public static JsonObject decodePropertyValue(@Nonnull String encodedPropertyValue) {
        JsonElement jsonElement;
        block4: {
            String json = CraftProfileProperty.decodeBase64(encodedPropertyValue);
            if (json == null) {
                return null;
            }
            try {
                jsonElement = JsonParser.parseString((String)json);
                if (jsonElement.isJsonObject()) break block4;
                return null;
            }
            catch (JsonParseException e) {
                return null;
            }
        }
        return jsonElement.getAsJsonObject();
    }

    @Nonnull
    public static String encodePropertyValue(@Nonnull JsonObject propertyValue, @Nonnull JsonFormatter formatter) {
        String json = formatter.format((JsonElement)propertyValue);
        return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
    }

    @Nonnull
    public static String toString(@Nonnull Property property) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("name=");
        builder.append(property.getName());
        builder.append(", value=");
        builder.append(property.getValue());
        builder.append(", signature=");
        builder.append(property.getSignature());
        builder.append("}");
        return builder.toString();
    }

    public static int hashCode(@Nonnull Property property) {
        int result = 1;
        result = 31 * result + Objects.hashCode(property.getName());
        result = 31 * result + Objects.hashCode(property.getValue());
        result = 31 * result + Objects.hashCode(property.getSignature());
        return result;
    }

    public static boolean equals(@Nullable Property property, @Nullable Property other) {
        if (property == null || other == null) {
            return property == other;
        }
        if (!Objects.equals(property.getValue(), other.getValue())) {
            return false;
        }
        if (!Objects.equals(property.getName(), other.getName())) {
            return false;
        }
        return Objects.equals(property.getSignature(), other.getSignature());
    }

    public static Map<String, Object> serialize(@Nonnull Property property) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", property.getName());
        map.put("value", property.getValue());
        if (property.hasSignature()) {
            map.put("signature", property.getSignature());
        }
        return map;
    }

    public static Property deserialize(@Nonnull Map<?, ?> map) {
        String name = ConfigSerializationUtil.getString(map, "name", false);
        String value = ConfigSerializationUtil.getString(map, "value", false);
        String signature = ConfigSerializationUtil.getString(map, "signature", true);
        return new Property(name, value, signature);
    }

    private CraftProfileProperty() {
    }

    public static interface JsonFormatter {
        public static final JsonFormatter COMPACT = new JsonFormatter(){
            private final Gson gson = new GsonBuilder().create();

            @Override
            public String format(JsonElement jsonElement) {
                return this.gson.toJson(jsonElement);
            }
        };

        public String format(JsonElement var1);
    }
}

