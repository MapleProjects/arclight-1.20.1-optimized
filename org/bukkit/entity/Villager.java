/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.Locale;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.ZombieVillager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Villager
extends AbstractVillager {
    @NotNull
    public Profession getProfession();

    public void setProfession(@NotNull Profession var1);

    @NotNull
    public Type getVillagerType();

    public void setVillagerType(@NotNull Type var1);

    public int getVillagerLevel();

    public void setVillagerLevel(int var1);

    public int getVillagerExperience();

    public void setVillagerExperience(int var1);

    public boolean sleep(@NotNull Location var1);

    public void wakeup();

    public void shakeHead();

    @Nullable
    public ZombieVillager zombify();

    public static enum Profession implements Keyed
    {
        NONE,
        ARMORER,
        BUTCHER,
        CARTOGRAPHER,
        CLERIC,
        FARMER,
        FISHERMAN,
        FLETCHER,
        LEATHERWORKER,
        LIBRARIAN,
        MASON,
        NITWIT,
        SHEPHERD,
        TOOLSMITH,
        WEAPONSMITH;

        private final NamespacedKey key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));

        @Override
        @NotNull
        public NamespacedKey getKey() {
            return this.key;
        }
    }

    public static enum Type implements Keyed
    {
        DESERT,
        JUNGLE,
        PLAINS,
        SAVANNA,
        SNOW,
        SWAMP,
        TAIGA;

        private final NamespacedKey key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));

        @Override
        @NotNull
        public NamespacedKey getKey() {
            return this.key;
        }
    }
}

