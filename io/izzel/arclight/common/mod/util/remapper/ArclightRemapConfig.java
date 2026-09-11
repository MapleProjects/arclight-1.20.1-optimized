/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.common.mod.util.remapper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public record ArclightRemapConfig(boolean remap) {
    public static final ArclightRemapConfig PLUGIN = new ArclightRemapConfig(true);

    public ArclightRemapConfig copy() {
        return new ArclightRemapConfig(this.remap);
    }

    public int write(DataOutput output) throws IOException {
        output.writeBoolean(this.remap);
        return 1;
    }

    public static ArclightRemapConfig read(DataInput input) throws IOException {
        return new ArclightRemapConfig(input.readBoolean());
    }
}

