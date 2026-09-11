/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.profile;

import java.net.URL;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PlayerTextures {
    public boolean isEmpty();

    public void clear();

    @Nullable
    public URL getSkin();

    public void setSkin(@Nullable URL var1);

    public void setSkin(@Nullable URL var1, @Nullable SkinModel var2);

    @NotNull
    public SkinModel getSkinModel();

    @Nullable
    public URL getCape();

    public void setCape(@Nullable URL var1);

    public long getTimestamp();

    public boolean isSigned();

    public static enum SkinModel {
        CLASSIC,
        SLIM;

    }
}

