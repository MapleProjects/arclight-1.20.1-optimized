/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 */
package net.md_5.bungee.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.lang.reflect.Type;
import java.util.Set;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.KeybindComponent;
import net.md_5.bungee.api.chat.ScoreComponent;
import net.md_5.bungee.api.chat.SelectorComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.EntitySerializer;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.ItemSerializer;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.chat.hover.content.TextSerializer;
import net.md_5.bungee.chat.KeybindComponentSerializer;
import net.md_5.bungee.chat.ScoreComponentSerializer;
import net.md_5.bungee.chat.SelectorComponentSerializer;
import net.md_5.bungee.chat.TextComponentSerializer;
import net.md_5.bungee.chat.TranslatableComponentSerializer;

public class ComponentSerializer
implements JsonDeserializer<BaseComponent> {
    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(BaseComponent.class, (Object)new ComponentSerializer()).registerTypeAdapter(TextComponent.class, (Object)new TextComponentSerializer()).registerTypeAdapter(TranslatableComponent.class, (Object)new TranslatableComponentSerializer()).registerTypeAdapter(KeybindComponent.class, (Object)new KeybindComponentSerializer()).registerTypeAdapter(ScoreComponent.class, (Object)new ScoreComponentSerializer()).registerTypeAdapter(SelectorComponent.class, (Object)new SelectorComponentSerializer()).registerTypeAdapter(Entity.class, (Object)new EntitySerializer()).registerTypeAdapter(Text.class, (Object)new TextSerializer()).registerTypeAdapter(Item.class, (Object)new ItemSerializer()).registerTypeAdapter(ItemTag.class, (Object)new ItemTag.Serializer()).create();
    public static final ThreadLocal<Set<BaseComponent>> serializedComponents = new ThreadLocal();

    public static BaseComponent[] parse(String json) {
        JsonElement jsonElement = JSON_PARSER.parse(json);
        if (jsonElement.isJsonArray()) {
            return (BaseComponent[])gson.fromJson(jsonElement, BaseComponent[].class);
        }
        return new BaseComponent[]{(BaseComponent)gson.fromJson(jsonElement, BaseComponent.class)};
    }

    public static String toString(Object object) {
        return gson.toJson(object);
    }

    public static String toString(BaseComponent component) {
        return gson.toJson((Object)component);
    }

    public static String toString(BaseComponent ... components) {
        if (components.length == 1) {
            return gson.toJson((Object)components[0]);
        }
        return gson.toJson((Object)new TextComponent(components));
    }

    public BaseComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return new TextComponent(json.getAsString());
        }
        JsonObject object = json.getAsJsonObject();
        if (object.has("translate")) {
            return (BaseComponent)context.deserialize(json, TranslatableComponent.class);
        }
        if (object.has("keybind")) {
            return (BaseComponent)context.deserialize(json, KeybindComponent.class);
        }
        if (object.has("score")) {
            return (BaseComponent)context.deserialize(json, ScoreComponent.class);
        }
        if (object.has("selector")) {
            return (BaseComponent)context.deserialize(json, SelectorComponent.class);
        }
        return (BaseComponent)context.deserialize(json, TextComponent.class);
    }
}

