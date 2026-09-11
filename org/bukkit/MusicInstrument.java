/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Lists
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MusicInstrument
implements Keyed {
    public static final MusicInstrument PONDER = MusicInstrument.getInstrument("ponder_goat_horn");
    public static final MusicInstrument SING = MusicInstrument.getInstrument("sing_goat_horn");
    public static final MusicInstrument SEEK = MusicInstrument.getInstrument("seek_goat_horn");
    public static final MusicInstrument FEEL = MusicInstrument.getInstrument("feel_goat_horn");
    public static final MusicInstrument ADMIRE = MusicInstrument.getInstrument("admire_goat_horn");
    public static final MusicInstrument CALL = MusicInstrument.getInstrument("call_goat_horn");
    public static final MusicInstrument YEARN = MusicInstrument.getInstrument("yearn_goat_horn");
    public static final MusicInstrument DREAM = MusicInstrument.getInstrument("dream_goat_horn");

    @Deprecated
    @Nullable
    public static MusicInstrument getByKey(@NotNull NamespacedKey namespacedKey) {
        return Registry.INSTRUMENT.get(namespacedKey);
    }

    @Deprecated
    @NotNull
    public static Collection<MusicInstrument> values() {
        return Collections.unmodifiableCollection(Lists.newArrayList(Registry.INSTRUMENT));
    }

    @NotNull
    private static MusicInstrument getInstrument(@NotNull String key) {
        NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
        MusicInstrument instrument = Registry.INSTRUMENT.get(namespacedKey);
        Preconditions.checkNotNull((Object)instrument, (String)"No MusicInstrument found for %s. This is a bug.", (Object)namespacedKey);
        return instrument;
    }
}

