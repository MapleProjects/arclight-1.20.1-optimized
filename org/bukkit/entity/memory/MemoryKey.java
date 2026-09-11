/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity.memory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MemoryKey<T>
implements Keyed {
    private final NamespacedKey namespacedKey;
    private final Class<T> tClass;
    private static final Map<NamespacedKey, MemoryKey> MEMORY_KEYS = new HashMap<NamespacedKey, MemoryKey>();
    public static final MemoryKey<Location> HOME = new MemoryKey<Location>(NamespacedKey.minecraft("home"), Location.class);
    public static final MemoryKey<Location> POTENTIAL_JOB_SITE = new MemoryKey<Location>(NamespacedKey.minecraft("potential_job_site"), Location.class);
    public static final MemoryKey<Location> JOB_SITE = new MemoryKey<Location>(NamespacedKey.minecraft("job_site"), Location.class);
    public static final MemoryKey<Location> MEETING_POINT = new MemoryKey<Location>(NamespacedKey.minecraft("meeting_point"), Location.class);
    public static final MemoryKey<Boolean> GOLEM_DETECTED_RECENTLY = new MemoryKey<Boolean>(NamespacedKey.minecraft("golem_detected_recently"), Boolean.class);
    public static final MemoryKey<Long> LAST_SLEPT = new MemoryKey<Long>(NamespacedKey.minecraft("last_slept"), Long.class);
    public static final MemoryKey<Long> LAST_WOKEN = new MemoryKey<Long>(NamespacedKey.minecraft("last_woken"), Long.class);
    public static final MemoryKey<Long> LAST_WORKED_AT_POI = new MemoryKey<Long>(NamespacedKey.minecraft("last_worked_at_poi"), Long.class);
    public static final MemoryKey<Boolean> UNIVERSAL_ANGER = new MemoryKey<Boolean>(NamespacedKey.minecraft("universal_anger"), Boolean.class);
    public static final MemoryKey<UUID> ANGRY_AT = new MemoryKey<UUID>(NamespacedKey.minecraft("angry_at"), UUID.class);
    public static final MemoryKey<Boolean> ADMIRING_ITEM = new MemoryKey<Boolean>(NamespacedKey.minecraft("admiring_item"), Boolean.class);
    public static final MemoryKey<Boolean> ADMIRING_DISABLED = new MemoryKey<Boolean>(NamespacedKey.minecraft("admiring_disabled"), Boolean.class);
    public static final MemoryKey<Boolean> HUNTED_RECENTLY = new MemoryKey<Boolean>(NamespacedKey.minecraft("hunted_recently"), Boolean.class);
    public static final MemoryKey<Integer> PLAY_DEAD_TICKS = new MemoryKey<Integer>(NamespacedKey.minecraft("play_dead_ticks"), Integer.class);
    public static final MemoryKey<Integer> TEMPTATION_COOLDOWN_TICKS = new MemoryKey<Integer>(NamespacedKey.minecraft("temptation_cooldown_ticks"), Integer.class);
    public static final MemoryKey<Boolean> IS_TEMPTED = new MemoryKey<Boolean>(NamespacedKey.minecraft("is_tempted"), Boolean.class);
    public static final MemoryKey<Integer> LONG_JUMP_COOLING_DOWN = new MemoryKey<Integer>(NamespacedKey.minecraft("long_jump_cooling_down"), Integer.class);
    public static final MemoryKey<Boolean> HAS_HUNTING_COOLDOWN = new MemoryKey<Boolean>(NamespacedKey.minecraft("has_hunting_cooldown"), Boolean.class);
    public static final MemoryKey<Integer> RAM_COOLDOWN_TICKS = new MemoryKey<Integer>(NamespacedKey.minecraft("ram_cooldown_ticks"), Integer.class);
    public static final MemoryKey<UUID> LIKED_PLAYER = new MemoryKey<UUID>(NamespacedKey.minecraft("liked_player"), UUID.class);
    public static final MemoryKey<Location> LIKED_NOTEBLOCK_POSITION = new MemoryKey<Location>(NamespacedKey.minecraft("liked_noteblock"), Location.class);
    public static final MemoryKey<Integer> LIKED_NOTEBLOCK_COOLDOWN_TICKS = new MemoryKey<Integer>(NamespacedKey.minecraft("liked_noteblock_cooldown_ticks"), Integer.class);
    public static final MemoryKey<Integer> ITEM_PICKUP_COOLDOWN_TICKS = new MemoryKey<Integer>(NamespacedKey.minecraft("item_pickup_cooldown_ticks"), Integer.class);
    public static final MemoryKey<Location> SNIFFER_EXPLORED_POSITIONS = new MemoryKey<Location>(NamespacedKey.minecraft("sniffer_explored_positions"), Location.class);

    private MemoryKey(NamespacedKey namespacedKey, Class<T> tClass) {
        this.namespacedKey = namespacedKey;
        this.tClass = tClass;
        MEMORY_KEYS.put(namespacedKey, this);
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.namespacedKey;
    }

    @NotNull
    public Class<T> getMemoryClass() {
        return this.tClass;
    }

    @Nullable
    public static MemoryKey getByKey(@NotNull NamespacedKey namespacedKey) {
        return MEMORY_KEYS.get(namespacedKey);
    }

    @NotNull
    public static Set<MemoryKey> values() {
        return new HashSet<MemoryKey>(MEMORY_KEYS.values());
    }
}

