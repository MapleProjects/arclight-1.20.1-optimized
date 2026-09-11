/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.help;

import java.util.Collection;
import java.util.List;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HelpMap {
    @Nullable
    public HelpTopic getHelpTopic(@NotNull String var1);

    @NotNull
    public Collection<HelpTopic> getHelpTopics();

    public void addTopic(@NotNull HelpTopic var1);

    public void clear();

    public void registerHelpTopicFactory(@NotNull Class<?> var1, @NotNull HelpTopicFactory<?> var2);

    @NotNull
    public List<String> getIgnoredPlugins();
}

