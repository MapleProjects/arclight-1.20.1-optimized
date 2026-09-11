/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.configuration.serialization;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface ConfigurationSerializable {
    @NotNull
    public Map<String, Object> serialize();
}

