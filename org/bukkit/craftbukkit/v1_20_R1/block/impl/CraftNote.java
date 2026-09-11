/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.NoteBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.data.Powerable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftNote
extends CraftBlockData
implements org.bukkit.block.data.type.NoteBlock,
Powerable {
    private static final EnumProperty<?> INSTRUMENT = CraftNote.getEnum(NoteBlock.class, "instrument");
    private static final IntegerProperty NOTE = CraftNote.getInteger(NoteBlock.class, "note");
    private static final BooleanProperty POWERED = CraftNote.getBoolean(NoteBlock.class, "powered");

    public CraftNote() {
    }

    public CraftNote(BlockState state) {
        super(state);
    }

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

    @Override
    public boolean isPowered() {
        return (Boolean)this.get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        this.set(POWERED, powered);
    }
}

