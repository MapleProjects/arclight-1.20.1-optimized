/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import java.util.Map;
import org.bukkit.conversations.Conversable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConversationContext {
    private final Conversable forWhom;
    private final Map<Object, Object> sessionData;
    private final Plugin plugin;

    public ConversationContext(@Nullable Plugin plugin, @NotNull Conversable forWhom, @NotNull Map<Object, Object> initialSessionData) {
        this.plugin = plugin;
        this.forWhom = forWhom;
        this.sessionData = initialSessionData;
    }

    @Nullable
    public Plugin getPlugin() {
        return this.plugin;
    }

    @NotNull
    public Conversable getForWhom() {
        return this.forWhom;
    }

    @NotNull
    public Map<Object, Object> getAllSessionData() {
        return this.sessionData;
    }

    @Nullable
    public Object getSessionData(@NotNull Object key) {
        return this.sessionData.get(key);
    }

    public void setSessionData(@NotNull Object key, @Nullable Object value) {
        this.sessionData.put(key, value);
    }
}

