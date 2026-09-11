/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package net.md_5.bungee.api.chat.hover.content;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class TextSerializer
implements JsonSerializer<Text>,
JsonDeserializer<Text> {
    public Text deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (element.isJsonArray()) {
            return new Text((BaseComponent[])context.deserialize(element, BaseComponent[].class));
        }
        if (element.isJsonPrimitive()) {
            return new Text(element.getAsJsonPrimitive().getAsString());
        }
        return new Text(new BaseComponent[]{(BaseComponent)context.deserialize(element, BaseComponent.class)});
    }

    public JsonElement serialize(Text content, Type type, JsonSerializationContext context) {
        return context.serialize(content.getValue());
    }
}

