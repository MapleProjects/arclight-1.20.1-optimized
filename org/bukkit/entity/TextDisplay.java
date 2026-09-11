/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Color;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TextDisplay
extends Display {
    @Nullable
    public String getText();

    public void setText(@Nullable String var1);

    public int getLineWidth();

    public void setLineWidth(int var1);

    @Deprecated
    @Nullable
    public Color getBackgroundColor();

    @Deprecated
    public void setBackgroundColor(@Nullable Color var1);

    public byte getTextOpacity();

    public void setTextOpacity(byte var1);

    public boolean isShadowed();

    public void setShadowed(boolean var1);

    public boolean isSeeThrough();

    public void setSeeThrough(boolean var1);

    public boolean isDefaultBackground();

    public void setDefaultBackground(boolean var1);

    @NotNull
    public TextAlignment getAlignment();

    public void setAlignment(@NotNull TextAlignment var1);

    public static enum TextAlignment {
        CENTER,
        LEFT,
        RIGHT;

    }
}

