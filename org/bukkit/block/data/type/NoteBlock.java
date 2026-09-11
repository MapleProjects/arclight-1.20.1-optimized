/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface NoteBlock
extends Powerable {
    @NotNull
    public Instrument getInstrument();

    public void setInstrument(@NotNull Instrument var1);

    @NotNull
    public Note getNote();

    public void setNote(@NotNull Note var1);
}

