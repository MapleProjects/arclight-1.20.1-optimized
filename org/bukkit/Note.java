/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Note {
    private final byte note;

    public Note(int note) {
        Preconditions.checkArgument((note >= 0 && note <= 24 ? 1 : 0) != 0, (Object)"The note value has to be between 0 and 24.");
        this.note = (byte)note;
    }

    public Note(int octave, @NotNull Tone tone, boolean sharped) {
        if (sharped && !tone.isSharpable()) {
            tone = Tone.values()[tone.ordinal() + 1];
            sharped = false;
        }
        if (octave < 0 || octave > 2 || octave == 2 && (tone != Tone.F || !sharped)) {
            throw new IllegalArgumentException("Tone and octave have to be between F#0 and F#2");
        }
        this.note = (byte)(octave * 12 + tone.getId(sharped));
    }

    @NotNull
    public static Note flat(int octave, @NotNull Tone tone) {
        Preconditions.checkArgument((octave != 2 ? 1 : 0) != 0, (Object)"Octave cannot be 2 for flats");
        tone = tone == Tone.G ? Tone.F : Tone.values()[tone.ordinal() - 1];
        return new Note(octave, tone, tone.isSharpable());
    }

    @NotNull
    public static Note sharp(int octave, @NotNull Tone tone) {
        return new Note(octave, tone, true);
    }

    @NotNull
    public static Note natural(int octave, @NotNull Tone tone) {
        Preconditions.checkArgument((octave != 2 ? 1 : 0) != 0, (Object)"Octave cannot be 2 for naturals");
        return new Note(octave, tone, false);
    }

    @NotNull
    public Note sharped() {
        Preconditions.checkArgument((this.note < 24 ? 1 : 0) != 0, (Object)"This note cannot be sharped because it is the highest known note!");
        return new Note(this.note + 1);
    }

    @NotNull
    public Note flattened() {
        Preconditions.checkArgument((this.note > 0 ? 1 : 0) != 0, (Object)"This note cannot be flattened because it is the lowest known note!");
        return new Note(this.note - 1);
    }

    @Deprecated
    public byte getId() {
        return this.note;
    }

    public int getOctave() {
        return this.note / 12;
    }

    private byte getToneByte() {
        return (byte)(this.note % 12);
    }

    @NotNull
    public Tone getTone() {
        return Tone.getById(this.getToneByte());
    }

    public boolean isSharped() {
        byte note = this.getToneByte();
        return Tone.getById(note).isSharped(note);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.note;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Note other = (Note)obj;
        return this.note == other.note;
    }

    public String toString() {
        return "Note{" + this.getTone().toString() + (this.isSharped() ? "#" : "") + "}";
    }

    public static enum Tone {
        G(1, true),
        A(3, true),
        B(5, false),
        C(6, true),
        D(8, true),
        E(10, false),
        F(11, true);

        private final boolean sharpable;
        private final byte id;
        private static final Map<Byte, Tone> BY_DATA;
        public static final byte TONES_COUNT = 12;

        static {
            BY_DATA = Maps.newHashMap();
            Tone[] toneArray = Tone.values();
            int n = toneArray.length;
            int n2 = 0;
            while (n2 < n) {
                Tone tone = toneArray[n2];
                int id = tone.id % 12;
                BY_DATA.put((byte)id, tone);
                if (tone.isSharpable()) {
                    id = (id + 1) % 12;
                    BY_DATA.put((byte)id, tone);
                }
                ++n2;
            }
        }

        private Tone(int id, boolean sharpable) {
            this.id = (byte)(id % 12);
            this.sharpable = sharpable;
        }

        @Deprecated
        public byte getId() {
            return this.getId(false);
        }

        @Deprecated
        public byte getId(boolean sharped) {
            byte id = (byte)(sharped && this.sharpable ? this.id + 1 : this.id);
            return (byte)(id % 12);
        }

        public boolean isSharpable() {
            return this.sharpable;
        }

        @Deprecated
        public boolean isSharped(byte id) {
            if (id == this.getId(false)) {
                return false;
            }
            if (id == this.getId(true)) {
                return true;
            }
            throw new IllegalArgumentException("The id isn't matching to the tone.");
        }

        @Deprecated
        @Nullable
        public static Tone getById(byte id) {
            return BY_DATA.get(id);
        }
    }
}

