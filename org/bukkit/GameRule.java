/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GameRule<T> {
    private static Map<String, GameRule<?>> gameRules = new HashMap();
    public static final GameRule<Boolean> ANNOUNCE_ADVANCEMENTS = new GameRule<Boolean>("announceAdvancements", Boolean.class);
    public static final GameRule<Boolean> COMMAND_BLOCK_OUTPUT = new GameRule<Boolean>("commandBlockOutput", Boolean.class);
    public static final GameRule<Boolean> DISABLE_ELYTRA_MOVEMENT_CHECK = new GameRule<Boolean>("disableElytraMovementCheck", Boolean.class);
    public static final GameRule<Boolean> DO_DAYLIGHT_CYCLE = new GameRule<Boolean>("doDaylightCycle", Boolean.class);
    public static final GameRule<Boolean> DO_ENTITY_DROPS = new GameRule<Boolean>("doEntityDrops", Boolean.class);
    public static final GameRule<Boolean> DO_FIRE_TICK = new GameRule<Boolean>("doFireTick", Boolean.class);
    public static final GameRule<Boolean> DO_LIMITED_CRAFTING = new GameRule<Boolean>("doLimitedCrafting", Boolean.class);
    public static final GameRule<Boolean> DO_MOB_LOOT = new GameRule<Boolean>("doMobLoot", Boolean.class);
    public static final GameRule<Boolean> DO_MOB_SPAWNING = new GameRule<Boolean>("doMobSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_TILE_DROPS = new GameRule<Boolean>("doTileDrops", Boolean.class);
    public static final GameRule<Boolean> DO_WEATHER_CYCLE = new GameRule<Boolean>("doWeatherCycle", Boolean.class);
    public static final GameRule<Boolean> KEEP_INVENTORY = new GameRule<Boolean>("keepInventory", Boolean.class);
    public static final GameRule<Boolean> LOG_ADMIN_COMMANDS = new GameRule<Boolean>("logAdminCommands", Boolean.class);
    public static final GameRule<Boolean> MOB_GRIEFING = new GameRule<Boolean>("mobGriefing", Boolean.class);
    public static final GameRule<Boolean> NATURAL_REGENERATION = new GameRule<Boolean>("naturalRegeneration", Boolean.class);
    public static final GameRule<Boolean> REDUCED_DEBUG_INFO = new GameRule<Boolean>("reducedDebugInfo", Boolean.class);
    public static final GameRule<Boolean> SEND_COMMAND_FEEDBACK = new GameRule<Boolean>("sendCommandFeedback", Boolean.class);
    public static final GameRule<Boolean> SHOW_DEATH_MESSAGES = new GameRule<Boolean>("showDeathMessages", Boolean.class);
    public static final GameRule<Boolean> SPECTATORS_GENERATE_CHUNKS = new GameRule<Boolean>("spectatorsGenerateChunks", Boolean.class);
    public static final GameRule<Boolean> DISABLE_RAIDS = new GameRule<Boolean>("disableRaids", Boolean.class);
    public static final GameRule<Boolean> DO_INSOMNIA = new GameRule<Boolean>("doInsomnia", Boolean.class);
    public static final GameRule<Boolean> DO_IMMEDIATE_RESPAWN = new GameRule<Boolean>("doImmediateRespawn", Boolean.class);
    public static final GameRule<Boolean> DROWNING_DAMAGE = new GameRule<Boolean>("drowningDamage", Boolean.class);
    public static final GameRule<Boolean> FALL_DAMAGE = new GameRule<Boolean>("fallDamage", Boolean.class);
    public static final GameRule<Boolean> FIRE_DAMAGE = new GameRule<Boolean>("fireDamage", Boolean.class);
    public static final GameRule<Boolean> FREEZE_DAMAGE = new GameRule<Boolean>("freezeDamage", Boolean.class);
    public static final GameRule<Boolean> DO_PATROL_SPAWNING = new GameRule<Boolean>("doPatrolSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_TRADER_SPAWNING = new GameRule<Boolean>("doTraderSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_WARDEN_SPAWNING = new GameRule<Boolean>("doWardenSpawning", Boolean.class);
    public static final GameRule<Boolean> FORGIVE_DEAD_PLAYERS = new GameRule<Boolean>("forgiveDeadPlayers", Boolean.class);
    public static final GameRule<Boolean> UNIVERSAL_ANGER = new GameRule<Boolean>("universalAnger", Boolean.class);
    public static final GameRule<Boolean> BLOCK_EXPLOSION_DROP_DECAY = new GameRule<Boolean>("blockExplosionDropDecay", Boolean.class);
    public static final GameRule<Boolean> MOB_EXPLOSION_DROP_DECAY = new GameRule<Boolean>("mobExplosionDropDecay", Boolean.class);
    public static final GameRule<Boolean> TNT_EXPLOSION_DROP_DECAY = new GameRule<Boolean>("tntExplosionDropDecay", Boolean.class);
    public static final GameRule<Boolean> WATER_SOURCE_CONVERSION = new GameRule<Boolean>("waterSourceConversion", Boolean.class);
    public static final GameRule<Boolean> LAVA_SOURCE_CONVERSION = new GameRule<Boolean>("lavaSourceConversion", Boolean.class);
    public static final GameRule<Boolean> GLOBAL_SOUND_EVENTS = new GameRule<Boolean>("globalSoundEvents", Boolean.class);
    public static final GameRule<Boolean> DO_VINES_SPREAD = new GameRule<Boolean>("doVinesSpread", Boolean.class);
    public static final GameRule<Integer> RANDOM_TICK_SPEED = new GameRule<Integer>("randomTickSpeed", Integer.class);
    public static final GameRule<Integer> SPAWN_RADIUS = new GameRule<Integer>("spawnRadius", Integer.class);
    public static final GameRule<Integer> MAX_ENTITY_CRAMMING = new GameRule<Integer>("maxEntityCramming", Integer.class);
    public static final GameRule<Integer> MAX_COMMAND_CHAIN_LENGTH = new GameRule<Integer>("maxCommandChainLength", Integer.class);
    public static final GameRule<Integer> COMMAND_MODIFICATION_BLOCK_LIMIT = new GameRule<Integer>("commandModificationBlockLimit", Integer.class);
    public static final GameRule<Integer> PLAYERS_SLEEPING_PERCENTAGE = new GameRule<Integer>("playersSleepingPercentage", Integer.class);
    public static final GameRule<Integer> SNOW_ACCUMULATION_HEIGHT = new GameRule<Integer>("snowAccumulationHeight", Integer.class);
    private final String name;
    private final Class<T> type;

    private GameRule(@NotNull String name, @NotNull Class<T> clazz) {
        Preconditions.checkNotNull((Object)name, (Object)"GameRule name cannot be null");
        Preconditions.checkNotNull(clazz, (Object)"GameRule type cannot be null");
        Preconditions.checkArgument((clazz == Boolean.class || clazz == Integer.class ? 1 : 0) != 0, (String)"Must be of type Boolean or Integer. Found %s ", (Object)clazz.getName());
        this.name = name;
        this.type = clazz;
        gameRules.put(name, this);
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public Class<T> getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GameRule)) {
            return false;
        }
        GameRule other = (GameRule)obj;
        return this.getName().equals(other.getName()) && this.getType() == other.getType();
    }

    public String toString() {
        return "GameRule{key=" + this.name + ", type=" + this.type + '}';
    }

    @Nullable
    public static GameRule<?> getByName(@NotNull String rule) {
        Preconditions.checkNotNull((Object)rule, (Object)"Rule cannot be null");
        return gameRules.get(rule);
    }

    @NotNull
    public static GameRule<?>[] values() {
        return gameRules.values().toArray(new GameRule[gameRules.size()]);
    }
}

