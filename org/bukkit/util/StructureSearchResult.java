/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util;

import org.bukkit.Location;
import org.bukkit.generator.structure.Structure;
import org.jetbrains.annotations.NotNull;

public interface StructureSearchResult {
    @NotNull
    public Structure getStructure();

    @NotNull
    public Location getLocation();
}

