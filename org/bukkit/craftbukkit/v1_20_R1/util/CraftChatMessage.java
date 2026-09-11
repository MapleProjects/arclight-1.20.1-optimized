/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.gson.JsonParseException
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.ClickEvent
 *  net.minecraft.network.chat.ClickEvent$Action
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Component$Serializer
 *  net.minecraft.network.chat.ComponentContents
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.chat.TextColor
 *  net.minecraft.network.chat.contents.LiteralContents
 *  net.minecraft.network.chat.contents.TranslatableContents
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.bukkit.ChatColor;

public final class CraftChatMessage {
    private static final Pattern LINK_PATTERN = Pattern.compile("((?:(?:https?):\\/\\/)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[" + String.valueOf('\u00a7') + " \\n]|$))))");
    private static final Map<Character, ChatFormatting> formatMap;

    static {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        ChatFormatting[] chatFormattingArray = ChatFormatting.values();
        int n = chatFormattingArray.length;
        int n2 = 0;
        while (n2 < n) {
            ChatFormatting format = chatFormattingArray[n2];
            builder.put((Object)Character.valueOf(Character.toLowerCase(format.toString().charAt(1))), (Object)format);
            ++n2;
        }
        formatMap = builder.build();
    }

    public static ChatFormatting getColor(ChatColor color) {
        return formatMap.get(Character.valueOf(color.getChar()));
    }

    public static ChatColor getColor(ChatFormatting format) {
        return ChatColor.getByChar(format.f_126622_);
    }

    public static Component fromStringOrNull(String message) {
        return CraftChatMessage.fromStringOrNull(message, false);
    }

    public static Component fromStringOrNull(String message, boolean keepNewlines) {
        return message == null || message.isEmpty() ? null : CraftChatMessage.fromString(message, keepNewlines)[0];
    }

    public static Component[] fromString(String message) {
        return CraftChatMessage.fromString(message, false);
    }

    public static Component[] fromString(String message, boolean keepNewlines) {
        return CraftChatMessage.fromString(message, keepNewlines, false);
    }

    public static Component[] fromString(String message, boolean keepNewlines, boolean plain) {
        return new StringMessage(message, keepNewlines, plain).getOutput();
    }

    public static String toJSON(Component component) {
        return Component.Serializer.m_130703_((Component)component);
    }

    public static String toJSONOrNull(Component component) {
        if (component == null) {
            return null;
        }
        return CraftChatMessage.toJSON(component);
    }

    public static Component fromJSON(String jsonMessage) throws JsonParseException {
        return Component.Serializer.m_130701_((String)jsonMessage);
    }

    public static Component fromJSONOrNull(String jsonMessage) {
        if (jsonMessage == null) {
            return null;
        }
        try {
            return CraftChatMessage.fromJSON(jsonMessage);
        }
        catch (JsonParseException ex) {
            return null;
        }
    }

    public static Component fromJSONOrString(String message) {
        return CraftChatMessage.fromJSONOrString(message, false);
    }

    public static Component fromJSONOrString(String message, boolean keepNewlines) {
        return CraftChatMessage.fromJSONOrString(message, false, keepNewlines);
    }

    private static Component fromJSONOrString(String message, boolean nullable, boolean keepNewlines) {
        if (message == null) {
            message = "";
        }
        if (nullable && message.isEmpty()) {
            return null;
        }
        Component component = CraftChatMessage.fromJSONOrNull(message);
        if (component != null) {
            return component;
        }
        return CraftChatMessage.fromString(message, keepNewlines)[0];
    }

    public static String fromJSONOrStringToJSON(String message) {
        return CraftChatMessage.fromJSONOrStringToJSON(message, false);
    }

    public static String fromJSONOrStringToJSON(String message, boolean keepNewlines) {
        return CraftChatMessage.fromJSONOrStringToJSON(message, false, keepNewlines, Integer.MAX_VALUE, false);
    }

    public static String fromJSONOrStringOrNullToJSON(String message) {
        return CraftChatMessage.fromJSONOrStringOrNullToJSON(message, false);
    }

    public static String fromJSONOrStringOrNullToJSON(String message, boolean keepNewlines) {
        return CraftChatMessage.fromJSONOrStringToJSON(message, true, keepNewlines, Integer.MAX_VALUE, false);
    }

    public static String fromJSONOrStringToJSON(String message, boolean nullable, boolean keepNewlines, int maxLength, boolean checkJsonContentLength) {
        if (message == null) {
            message = "";
        }
        if (nullable && message.isEmpty()) {
            return null;
        }
        Component component = CraftChatMessage.fromJSONOrNull(message);
        if (component != null) {
            String trimmedContent;
            String content;
            if (checkJsonContentLength && (content = CraftChatMessage.fromComponent(component)) != (trimmedContent = CraftChatMessage.trimMessage(content, maxLength))) {
                return CraftChatMessage.fromStringToJSON(trimmedContent, keepNewlines);
            }
            return message;
        }
        message = CraftChatMessage.trimMessage(message, maxLength);
        return CraftChatMessage.fromStringToJSON(message, keepNewlines);
    }

    public static String trimMessage(String message, int maxLength) {
        if (message != null && message.length() > maxLength) {
            return message.substring(0, maxLength);
        }
        return message;
    }

    public static String fromStringToJSON(String message) {
        return CraftChatMessage.fromStringToJSON(message, false);
    }

    public static String fromStringToJSON(String message, boolean keepNewlines) {
        Component component = CraftChatMessage.fromString(message, keepNewlines)[0];
        return CraftChatMessage.toJSON(component);
    }

    public static String fromStringOrNullToJSON(String message) {
        Component component = CraftChatMessage.fromStringOrNull(message);
        return CraftChatMessage.toJSONOrNull(component);
    }

    public static String fromJSONComponent(String jsonMessage) {
        Component component = CraftChatMessage.fromJSONOrNull(jsonMessage);
        return CraftChatMessage.fromComponent(component);
    }

    public static String fromComponent(Component component) {
        if (component == null) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        boolean hadFormat = false;
        for (Component c : component) {
            Style modi = c.m_7383_();
            TextColor color = modi.m_131135_();
            if (c.m_214077_() != ComponentContents.f_237124_ || color != null) {
                if (color != null) {
                    if (color.format != null) {
                        out.append(color.format);
                    } else {
                        out.append('\u00a7').append("x");
                        char[] cArray = color.m_131274_().substring(1).toCharArray();
                        int n = cArray.length;
                        int n2 = 0;
                        while (n2 < n) {
                            char magic = cArray[n2];
                            out.append('\u00a7').append(magic);
                            ++n2;
                        }
                    }
                    hadFormat = true;
                } else if (hadFormat) {
                    out.append((Object)ChatColor.RESET);
                    hadFormat = false;
                }
            }
            if (modi.m_131154_()) {
                out.append(ChatFormatting.BOLD);
                hadFormat = true;
            }
            if (modi.m_131161_()) {
                out.append(ChatFormatting.ITALIC);
                hadFormat = true;
            }
            if (modi.m_131171_()) {
                out.append(ChatFormatting.UNDERLINE);
                hadFormat = true;
            }
            if (modi.m_131168_()) {
                out.append(ChatFormatting.STRIKETHROUGH);
                hadFormat = true;
            }
            if (modi.m_131176_()) {
                out.append(ChatFormatting.OBFUSCATED);
                hadFormat = true;
            }
            c.m_214077_().m_213874_(x -> {
                out.append(x);
                return Optional.empty();
            });
        }
        return out.toString();
    }

    public static Component fixComponent(MutableComponent component) {
        Matcher matcher = LINK_PATTERN.matcher("");
        return CraftChatMessage.fixComponent(component, matcher);
    }

    private static Component fixComponent(MutableComponent component, Matcher matcher) {
        LiteralContents text;
        String msg;
        if (component.m_214077_() instanceof LiteralContents && matcher.reset(msg = (text = (LiteralContents)component.m_214077_()).f_237368_()).find()) {
            matcher.reset();
            Style modifier = component.m_7383_();
            ArrayList<Object> extras = new ArrayList<Object>();
            ArrayList extrasOld = new ArrayList(component.m_7360_());
            component = Component.m_237119_();
            int pos = 0;
            while (matcher.find()) {
                String match = matcher.group();
                if (!match.startsWith("http://") && !match.startsWith("https://")) {
                    match = "http://" + match;
                }
                MutableComponent mutableComponent = Component.m_237113_((String)msg.substring(pos, matcher.start()));
                mutableComponent.m_6270_(modifier);
                extras.add(mutableComponent);
                MutableComponent link = Component.m_237113_((String)matcher.group());
                Style linkModi = modifier.m_131142_(new ClickEvent(ClickEvent.Action.OPEN_URL, match));
                link.m_6270_(linkModi);
                extras.add(link);
                pos = matcher.end();
            }
            MutableComponent prev = Component.m_237113_((String)msg.substring(pos));
            prev.m_6270_(modifier);
            extras.add(prev);
            extras.addAll(extrasOld);
            for (Component component2 : extras) {
                component.m_7220_(component2);
            }
        }
        List extras = component.m_7360_();
        int i = 0;
        while (i < extras.size()) {
            Component comp = (Component)extras.get(i);
            if (comp.m_7383_() != null && comp.m_7383_().m_131182_() == null) {
                extras.set(i, CraftChatMessage.fixComponent(comp.m_6881_(), matcher));
            }
            ++i;
        }
        if (component.m_214077_() instanceof TranslatableContents) {
            Object[] subs = ((TranslatableContents)component.m_214077_()).m_237523_();
            int i2 = 0;
            while (i2 < subs.length) {
                Object comp = subs[i2];
                if (comp instanceof Component) {
                    Component c = (Component)comp;
                    if (c.m_7383_() != null && c.m_7383_().m_131182_() == null) {
                        subs[i2] = CraftChatMessage.fixComponent(c.m_6881_(), matcher);
                    }
                } else if (comp instanceof String && matcher.reset((String)comp).find()) {
                    subs[i2] = CraftChatMessage.fixComponent(Component.m_237113_((String)((String)comp)), matcher);
                }
                ++i2;
            }
        }
        return component;
    }

    private CraftChatMessage() {
    }

    private static final class StringMessage {
        private static final Pattern INCREMENTAL_PATTERN = Pattern.compile("(" + String.valueOf('\u00a7') + "[0-9a-fk-orx])|((?:(?:https?):\\/\\/)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[" + String.valueOf('\u00a7') + " \\n]|$))))|(\\n)", 2);
        private static final Pattern INCREMENTAL_PATTERN_KEEP_NEWLINES = Pattern.compile("(" + String.valueOf('\u00a7') + "[0-9a-fk-orx])|((?:(?:https?):\\/\\/)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[" + String.valueOf('\u00a7') + " ]|$))))", 2);
        private static final Style RESET = Style.f_131099_.m_131136_(Boolean.valueOf(false)).m_131155_(Boolean.valueOf(false)).m_131162_(Boolean.valueOf(false)).m_178522_(Boolean.valueOf(false)).m_178524_(Boolean.valueOf(false));
        private final List<Component> list = new ArrayList<Component>();
        private MutableComponent currentChatComponent = Component.m_237119_();
        private Style modifier = Style.f_131099_;
        private final Component[] output;
        private int currentIndex;
        private StringBuilder hex;
        private final String message;
        private static volatile /* synthetic */ int[] $SWITCH_TABLE$net$minecraft$EnumChatFormat;

        /*
         * Unable to fully structure code
         */
        private StringMessage(String message, boolean keepNewlines, boolean plain) {
            super();
            this.message = message;
            if (message == null) {
                this.output = new Component[]{this.currentChatComponent};
                return;
            }
            this.list.add((Component)this.currentChatComponent);
            matcher = (keepNewlines != false ? StringMessage.INCREMENTAL_PATTERN_KEEP_NEWLINES : StringMessage.INCREMENTAL_PATTERN).matcher(message);
            match = null;
            needsAdd = false;
            while (matcher.find()) {
                groupId = 0;
                while ((match = matcher.group(++groupId)) == null) {
                }
                index = matcher.start(groupId);
                if (index > this.currentIndex) {
                    needsAdd = false;
                    this.appendNewComponent(index);
                }
                switch (groupId) {
                    case 1: {
                        c = match.toLowerCase(Locale.ENGLISH).charAt(1);
                        format = CraftChatMessage.formatMap.get(Character.valueOf(c));
                        if (c != 'x') ** GOTO lbl29
                        this.hex = new StringBuilder("#");
                        ** GOTO lbl56
lbl29:
                        // 1 sources

                        if (this.hex == null) ** GOTO lbl36
                        this.hex.append(c);
                        if (this.hex.length() == 7) {
                            this.modifier = StringMessage.RESET.m_131148_(TextColor.m_131268_((String)this.hex.toString()));
                            this.hex = null;
                        }
                        ** GOTO lbl56
lbl36:
                        // 1 sources

                        if (!format.m_126661_() || format == ChatFormatting.RESET) ** GOTO lbl55
                        switch (StringMessage.$SWITCH_TABLE$net$minecraft$EnumChatFormat()[format.ordinal()]) {
                            case 18: {
                                this.modifier = this.modifier.m_131136_(Boolean.TRUE);
                                ** GOTO lbl56
                            }
                            case 21: {
                                this.modifier = this.modifier.m_131155_(Boolean.TRUE);
                                ** GOTO lbl56
                            }
                            case 19: {
                                this.modifier = this.modifier.m_178522_(Boolean.TRUE);
                                ** GOTO lbl56
                            }
                            case 20: {
                                this.modifier = this.modifier.m_131162_(Boolean.TRUE);
                                ** GOTO lbl56
                            }
                            case 17: {
                                this.modifier = this.modifier.m_178524_(Boolean.TRUE);
                                ** GOTO lbl56
                            }
                            default: {
                                throw new AssertionError((Object)"Unexpected message format");
                            }
                        }
lbl55:
                        // 1 sources

                        this.modifier = StringMessage.RESET.m_131140_(format);
lbl56:
                        // 8 sources

                        needsAdd = true;
                        break;
                    }
                    case 2: {
                        if (plain) {
                            this.appendNewComponent(matcher.end(groupId));
                            break;
                        }
                        if (!match.startsWith("http://") && !match.startsWith("https://")) {
                            match = "http://" + match;
                        }
                        this.modifier = this.modifier.m_131142_(new ClickEvent(ClickEvent.Action.OPEN_URL, match));
                        this.appendNewComponent(matcher.end(groupId));
                        this.modifier = this.modifier.m_131142_(null);
                        break;
                    }
                    case 3: {
                        if (needsAdd) {
                            this.appendNewComponent(index);
                        }
                        this.currentChatComponent = null;
                    }
                }
                this.currentIndex = matcher.end(groupId);
            }
            if (this.currentIndex < message.length() || needsAdd) {
                this.appendNewComponent(message.length());
            }
            this.output = this.list.toArray(new Component[this.list.size()]);
        }

        private void appendNewComponent(int index) {
            MutableComponent addition = Component.m_237113_((String)this.message.substring(this.currentIndex, index)).m_6270_(this.modifier);
            this.currentIndex = index;
            if (this.currentChatComponent == null) {
                this.currentChatComponent = Component.m_237119_();
                this.list.add((Component)this.currentChatComponent);
            }
            this.currentChatComponent.m_7220_((Component)addition);
        }

        private Component[] getOutput() {
            return this.output;
        }

        static /* synthetic */ int[] $SWITCH_TABLE$net$minecraft$EnumChatFormat() {
            if ($SWITCH_TABLE$net$minecraft$EnumChatFormat != null) {
                return $SWITCH_TABLE$net$minecraft$EnumChatFormat;
            }
            int[] nArray = new int[ChatFormatting.values().length];
            try {
                nArray[ChatFormatting.AQUA.ordinal()] = 12;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.BLACK.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.BLUE.ordinal()] = 10;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.BOLD.ordinal()] = 18;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.DARK_AQUA.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.DARK_BLUE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.DARK_GRAY.ordinal()] = 9;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.DARK_GREEN.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.DARK_PURPLE.ordinal()] = 6;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.DARK_RED.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.GOLD.ordinal()] = 7;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.GRAY.ordinal()] = 8;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.GREEN.ordinal()] = 11;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.ITALIC.ordinal()] = 21;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.LIGHT_PURPLE.ordinal()] = 14;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.OBFUSCATED.ordinal()] = 17;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.RED.ordinal()] = 13;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.RESET.ordinal()] = 22;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.STRIKETHROUGH.ordinal()] = 19;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.UNDERLINE.ordinal()] = 20;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.WHITE.ordinal()] = 16;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                nArray[ChatFormatting.YELLOW.ordinal()] = 15;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            $SWITCH_TABLE$net$minecraft$EnumChatFormat = nArray;
            return nArray;
        }
    }
}

