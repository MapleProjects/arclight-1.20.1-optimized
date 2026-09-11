/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.packs;

import java.util.Set;
import org.bukkit.FeatureFlag;
import org.bukkit.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Experimental
public interface DataPack
extends Keyed {
    @NotNull
    public String getTitle();

    @NotNull
    public String getDescription();

    public int getPackFormat();

    public boolean isEnabled();

    public boolean isRequired();

    @NotNull
    public Compatibility getCompatibility();

    @NotNull
    public Set<FeatureFlag> getRequestedFeatures();

    @NotNull
    public Source getSource();

    public static enum Compatibility {
        NEW,
        OLD,
        COMPATIBLE;

    }

    public static enum Source {
        DEFAULT,
        BUILT_IN,
        FEATURE,
        WORLD,
        SERVER;

    }
}

