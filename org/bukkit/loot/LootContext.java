/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.loot;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LootContext {
    public static final int DEFAULT_LOOT_MODIFIER = -1;
    private final Location location;
    private final float luck;
    private final int lootingModifier;
    private final Entity lootedEntity;
    private final HumanEntity killer;

    private LootContext(@NotNull Location location, float luck, int lootingModifier, @Nullable Entity lootedEntity, @Nullable HumanEntity killer) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"LootContext location cannot be null");
        Preconditions.checkArgument((location.getWorld() != null ? 1 : 0) != 0, (Object)"LootContext World cannot be null");
        this.location = location;
        this.luck = luck;
        this.lootingModifier = lootingModifier;
        this.lootedEntity = lootedEntity;
        this.killer = killer;
    }

    @NotNull
    public Location getLocation() {
        return this.location;
    }

    public float getLuck() {
        return this.luck;
    }

    public int getLootingModifier() {
        return this.lootingModifier;
    }

    @Nullable
    public Entity getLootedEntity() {
        return this.lootedEntity;
    }

    @Nullable
    public HumanEntity getKiller() {
        return this.killer;
    }

    /* synthetic */ LootContext(Location location, float f, int n, Entity entity, HumanEntity humanEntity, LootContext lootContext) {
        this(location, f, n, entity, humanEntity);
    }

    public static class Builder {
        private final Location location;
        private float luck;
        private int lootingModifier = -1;
        private Entity lootedEntity;
        private HumanEntity killer;

        public Builder(@NotNull Location location) {
            this.location = location;
        }

        @NotNull
        public Builder luck(float luck) {
            this.luck = luck;
            return this;
        }

        @NotNull
        public Builder lootingModifier(int modifier) {
            this.lootingModifier = modifier;
            return this;
        }

        @NotNull
        public Builder lootedEntity(@Nullable Entity lootedEntity) {
            this.lootedEntity = lootedEntity;
            return this;
        }

        @NotNull
        public Builder killer(@Nullable HumanEntity killer) {
            this.killer = killer;
            return this;
        }

        @NotNull
        public LootContext build() {
            return new LootContext(this.location, this.luck, this.lootingModifier, this.lootedEntity, this.killer, null);
        }
    }
}

