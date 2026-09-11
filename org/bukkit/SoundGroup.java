/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public interface SoundGroup {
    public float getVolume();

    public float getPitch();

    @NotNull
    public Sound getBreakSound();

    @NotNull
    public Sound getStepSound();

    @NotNull
    public Sound getPlaceSound();

    @NotNull
    public Sound getHitSound();

    @NotNull
    public Sound getFallSound();
}

