/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.help;

import org.bukkit.command.Command;
import org.bukkit.help.HelpTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HelpTopicFactory<TCommand extends Command> {
    @Nullable
    public HelpTopic createTopic(@NotNull TCommand var1);
}

