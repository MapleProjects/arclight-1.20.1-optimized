/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.jetbrains.annotations.NotNull;

public class CreatureSpawnEvent
extends EntitySpawnEvent {
    private final SpawnReason spawnReason;

    public CreatureSpawnEvent(@NotNull LivingEntity spawnee, @NotNull SpawnReason spawnReason) {
        super(spawnee);
        this.spawnReason = spawnReason;
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity)this.entity;
    }

    @NotNull
    public SpawnReason getSpawnReason() {
        return this.spawnReason;
    }

    public static enum SpawnReason {
        NATURAL,
        JOCKEY,
        CHUNK_GEN,
        SPAWNER,
        EGG,
        SPAWNER_EGG,
        LIGHTNING,
        BUILD_SNOWMAN,
        BUILD_IRONGOLEM,
        BUILD_WITHER,
        VILLAGE_DEFENSE,
        VILLAGE_INVASION,
        BREEDING,
        SLIME_SPLIT,
        REINFORCEMENTS,
        NETHER_PORTAL,
        DISPENSE_EGG,
        INFECTION,
        CURED,
        OCELOT_BABY,
        SILVERFISH_BLOCK,
        MOUNT,
        TRAP,
        ENDER_PEARL,
        SHOULDER_ENTITY,
        DROWNED,
        SHEARED,
        EXPLOSION,
        RAID,
        PATROL,
        BEEHIVE,
        PIGLIN_ZOMBIFIED,
        SPELL,
        FROZEN,
        METAMORPHOSIS,
        DUPLICATION,
        COMMAND,
        CUSTOM,
        DEFAULT;

    }
}

