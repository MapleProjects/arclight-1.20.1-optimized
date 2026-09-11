/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.advancement;

import java.util.Collection;
import org.bukkit.Keyed;
import org.bukkit.advancement.AdvancementDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Advancement
extends Keyed {
    @NotNull
    public Collection<String> getCriteria();

    @Nullable
    public AdvancementDisplay getDisplay();
}

