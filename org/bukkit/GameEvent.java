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

public abstract class GameEvent
implements Keyed {
    public static final GameEvent BLOCK_ACTIVATE = GameEvent.getEvent("block_activate");
    public static final GameEvent BLOCK_ATTACH = GameEvent.getEvent("block_attach");
    public static final GameEvent BLOCK_CHANGE = GameEvent.getEvent("block_change");
    public static final GameEvent BLOCK_CLOSE = GameEvent.getEvent("block_close");
    public static final GameEvent BLOCK_DEACTIVATE = GameEvent.getEvent("block_deactivate");
    public static final GameEvent BLOCK_DESTROY = GameEvent.getEvent("block_destroy");
    public static final GameEvent BLOCK_DETACH = GameEvent.getEvent("block_detach");
    public static final GameEvent BLOCK_OPEN = GameEvent.getEvent("block_open");
    public static final GameEvent BLOCK_PLACE = GameEvent.getEvent("block_place");
    @Deprecated
    public static final GameEvent BLOCK_PRESS = GameEvent.getEvent("block_activate");
    @Deprecated
    public static final GameEvent BLOCK_SWITCH = GameEvent.getEvent("block_activate");
    @Deprecated
    public static final GameEvent BLOCK_UNPRESS = GameEvent.getEvent("block_deactivate");
    @Deprecated
    public static final GameEvent BLOCK_UNSWITCH = GameEvent.getEvent("block_deactivate");
    public static final GameEvent CONTAINER_CLOSE = GameEvent.getEvent("container_close");
    public static final GameEvent CONTAINER_OPEN = GameEvent.getEvent("container_open");
    @Deprecated
    public static final GameEvent DISPENSE_FAIL = GameEvent.getEvent("block_activate");
    public static final GameEvent DRINK = GameEvent.getEvent("drink");
    @Deprecated
    public static final GameEvent DRINKING_FINISH = GameEvent.getEvent("drink");
    public static final GameEvent EAT = GameEvent.getEvent("eat");
    @Deprecated
    public static final GameEvent ELYTRA_FREE_FALL = GameEvent.getEvent("elytra_glide");
    public static final GameEvent ELYTRA_GLIDE = GameEvent.getEvent("elytra_glide");
    public static final GameEvent ENTITY_DAMAGE = GameEvent.getEvent("entity_damage");
    @Deprecated
    public static final GameEvent ENTITY_DAMAGED = GameEvent.getEvent("entity_damage");
    public static final GameEvent ENTITY_DIE = GameEvent.getEvent("entity_die");
    public static final GameEvent ENTITY_DISMOUNT = GameEvent.getEvent("entity_dismount");
    @Deprecated
    public static final GameEvent ENTITY_DYING = GameEvent.getEvent("entity_die");
    public static final GameEvent ENTITY_INTERACT = GameEvent.getEvent("entity_interact");
    public static final GameEvent ENTITY_MOUNT = GameEvent.getEvent("entity_mount");
    @Deprecated
    public static final GameEvent ENTITY_KILLED = GameEvent.getEvent("entity_die");
    public static final GameEvent ENTITY_PLACE = GameEvent.getEvent("entity_place");
    public static final GameEvent ENTITY_ROAR = GameEvent.getEvent("entity_roar");
    public static final GameEvent ENTITY_SHAKE = GameEvent.getEvent("entity_shake");
    public static final GameEvent EQUIP = GameEvent.getEvent("equip");
    public static final GameEvent EXPLODE = GameEvent.getEvent("explode");
    public static final GameEvent FLAP = GameEvent.getEvent("flap");
    public static final GameEvent FLUID_PICKUP = GameEvent.getEvent("fluid_pickup");
    public static final GameEvent FLUID_PLACE = GameEvent.getEvent("fluid_place");
    public static final GameEvent HIT_GROUND = GameEvent.getEvent("hit_ground");
    public static final GameEvent INSTRUMENT_PLAY = GameEvent.getEvent("instrument_play");
    public static final GameEvent ITEM_INTERACT_FINISH = GameEvent.getEvent("item_interact_finish");
    public static final GameEvent ITEM_INTERACT_START = GameEvent.getEvent("item_interact_start");
    public static final GameEvent JUKEBOX_PLAY = GameEvent.getEvent("jukebox_play");
    public static final GameEvent JUKEBOX_STOP_PLAY = GameEvent.getEvent("jukebox_stop_play");
    public static final GameEvent LIGHTNING_STRIKE = GameEvent.getEvent("lightning_strike");
    @Deprecated
    public static final GameEvent MOB_INTERACT = GameEvent.getEvent("entity_interact");
    public static final GameEvent NOTE_BLOCK_PLAY = GameEvent.getEvent("note_block_play");
    @Deprecated
    public static final GameEvent PISTON_CONTRACT = GameEvent.getEvent("block_deactivate");
    @Deprecated
    public static final GameEvent PISTON_EXTEND = GameEvent.getEvent("block_activate");
    public static final GameEvent PRIME_FUSE = GameEvent.getEvent("prime_fuse");
    public static final GameEvent PROJECTILE_LAND = GameEvent.getEvent("projectile_land");
    public static final GameEvent PROJECTILE_SHOOT = GameEvent.getEvent("projectile_shoot");
    @Deprecated
    public static final GameEvent RAVAGER_ROAR = GameEvent.getEvent("entity_roar");
    @Deprecated
    public static final GameEvent RING_BELL = GameEvent.getEvent("block_change");
    public static final GameEvent SCULK_SENSOR_TENDRILS_CLICKING = GameEvent.getEvent("sculk_sensor_tendrils_clicking");
    public static final GameEvent SHEAR = GameEvent.getEvent("shear");
    public static final GameEvent SHRIEK = GameEvent.getEvent("shriek");
    @Deprecated
    public static final GameEvent SHULKER_CLOSE = GameEvent.getEvent("container_close");
    @Deprecated
    public static final GameEvent SHULKER_OPEN = GameEvent.getEvent("container_open");
    public static final GameEvent SPLASH = GameEvent.getEvent("splash");
    public static final GameEvent STEP = GameEvent.getEvent("step");
    public static final GameEvent SWIM = GameEvent.getEvent("swim");
    public static final GameEvent TELEPORT = GameEvent.getEvent("teleport");
    @Deprecated
    public static final GameEvent WOLF_SHAKING = GameEvent.getEvent("entity_shake");
    public static final GameEvent RESONATE_1 = GameEvent.getEvent("resonate_1");
    public static final GameEvent RESONATE_2 = GameEvent.getEvent("resonate_2");
    public static final GameEvent RESONATE_3 = GameEvent.getEvent("resonate_3");
    public static final GameEvent RESONATE_4 = GameEvent.getEvent("resonate_4");
    public static final GameEvent RESONATE_5 = GameEvent.getEvent("resonate_5");
    public static final GameEvent RESONATE_6 = GameEvent.getEvent("resonate_6");
    public static final GameEvent RESONATE_7 = GameEvent.getEvent("resonate_7");
    public static final GameEvent RESONATE_8 = GameEvent.getEvent("resonate_8");
    public static final GameEvent RESONATE_9 = GameEvent.getEvent("resonate_9");
    public static final GameEvent RESONATE_10 = GameEvent.getEvent("resonate_10");
    public static final GameEvent RESONATE_11 = GameEvent.getEvent("resonate_11");
    public static final GameEvent RESONATE_12 = GameEvent.getEvent("resonate_12");
    public static final GameEvent RESONATE_13 = GameEvent.getEvent("resonate_13");
    public static final GameEvent RESONATE_14 = GameEvent.getEvent("resonate_14");
    public static final GameEvent RESONATE_15 = GameEvent.getEvent("resonate_15");

    @Deprecated
    @Nullable
    public static GameEvent getByKey(@NotNull NamespacedKey namespacedKey) {
        return Registry.GAME_EVENT.get(namespacedKey);
    }

    @Deprecated
    @NotNull
    public static Collection<GameEvent> values() {
        return Collections.unmodifiableCollection(Lists.newArrayList(Registry.GAME_EVENT));
    }

    @NotNull
    private static GameEvent getEvent(@NotNull String key) {
        NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
        GameEvent gameEvent = Registry.GAME_EVENT.get(namespacedKey);
        Preconditions.checkNotNull((Object)gameEvent, (String)"No GameEvent found for %s. This is a bug.", (Object)namespacedKey);
        return gameEvent;
    }
}

