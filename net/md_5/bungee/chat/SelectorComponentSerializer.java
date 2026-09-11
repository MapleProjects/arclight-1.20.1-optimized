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
import net.md_5.bungee.api.chat.SelectorComponent;
import net.md_5.bungee.chat.BaseComponentSerializer;

public class SelectorComponentSerializer
extends BaseComponentSerializer
implements JsonSerializer<SelectorComponent>,
JsonDeserializer<SelectorComponent> {
    public SelectorComponent deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        if (!object.has("selector")) {
            throw new JsonParseException("Could not parse JSON: missing 'selector' property");
        }
        SelectorComponent component = new SelectorComponent(object.get("selector").getAsString());
        this.deserialize(object, component, context);
        return component;
    }

    public JsonElement serialize(SelectorComponent component, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        this.serialize(object, component, context);
        object.addProperty("selector", component.getSelector());
        return object;
    }
}

