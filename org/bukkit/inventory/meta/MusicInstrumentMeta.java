/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.MusicInstrument;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MusicInstrumentMeta
extends ItemMeta {
    public void setInstrument(@Nullable MusicInstrument var1);

    @Nullable
    public MusicInstrument getInstrument();

    @Override
    @NotNull
    public MusicInstrumentMeta clone();
}

