/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftNoteBlock
extends CraftBlockData
implements NoteBlock {
    private static final EnumProperty<?> INSTRUMENT = CraftNoteBlock.getEnum("instrument");
    private static final IntegerProperty NOTE = CraftNoteBlock.getInteger("note");

    @Override
    public Instrument getInstrument() {
        return this.get(INSTRUMENT, Instrument.class);
    }

    @Override
    public void setInstrument(Instrument instrument) {
        this.set(INSTRUMENT, instrument);
    }

    @Override
    public Note getNote() {
        return new Note((Integer)this.get(NOTE));
    }

    @Override
    public void setNote(Note note) {
        this.set(NOTE, Integer.valueOf(note.getId()));
    }
}

