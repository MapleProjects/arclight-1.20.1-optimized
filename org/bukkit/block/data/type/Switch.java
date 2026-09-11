/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface Switch
extends Directional,
FaceAttachable,
Powerable {
    @Deprecated
    @NotNull
    public Face getFace();

    @Deprecated
    public void setFace(@NotNull Face var1);

    @Deprecated
    public static enum Face {
        FLOOR,
        WALL,
        CEILING;

    }
}

