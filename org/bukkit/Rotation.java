/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import org.jetbrains.annotations.NotNull;

public enum Rotation {
    NONE,
    CLOCKWISE_45,
    CLOCKWISE,
    CLOCKWISE_135,
    FLIPPED,
    FLIPPED_45,
    COUNTER_CLOCKWISE,
    COUNTER_CLOCKWISE_45;

    private static final Rotation[] rotations;

    static {
        rotations = Rotation.values();
    }

    @NotNull
    public Rotation rotateClockwise() {
        return rotations[this.ordinal() + 1 & 7];
    }

    @NotNull
    public Rotation rotateCounterClockwise() {
        return rotations[this.ordinal() - 1 & 7];
    }
}

