/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package net.md_5.bungee.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Arrays;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.md_5.bungee.chat.BaseComponentSerializer;

public class TranslatableComponentSerializer
extends BaseComponentSerializer
implements JsonSerializer<TranslatableComponent>,
JsonDeserializer<TranslatableComponent> {
    public TranslatableComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TranslatableComponent component = new TranslatableComponent();
        JsonObject object = json.getAsJsonObject();
        this.deserialize(object, component, context);
        if (!object.has("translate")) {
            throw new JsonParseException("Could not parse JSON: missing 'translate' property");
        }
        component.setTranslate(object.get("translate").getAsString());
        if (object.has("with")) {
            component.setWith(Arrays.asList((Object[])context.deserialize(object.get("with"), BaseComponent[].class)));
        }
        return component;
    }

    public JsonElement serialize(TranslatableComponent src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        this.serialize(object, src, context);
        object.addProperty("translate", src.getTranslate());
        if (src.getWith() != null) {
            object.add("with", context.serialize(src.getWith()));
        }
        return object;
    }
}

