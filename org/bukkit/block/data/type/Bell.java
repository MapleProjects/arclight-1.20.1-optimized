/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface Bell
extends Directional,
Powerable {
    @NotNull
    public Attachment getAttachment();

    public void setAttachment(@NotNull Attachment var1);

    public static enum Attachment {
        FLOOR,
        CEILING,
        SINGLE_WALL,
        DOUBLE_WALL;

    }
}

