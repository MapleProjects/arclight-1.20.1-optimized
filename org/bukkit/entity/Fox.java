/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Sittable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Fox
extends Animals,
Sittable {
    @NotNull
    public Type getFoxType();

    public void setFoxType(@NotNull Type var1);

    public boolean isCrouching();

    public void setCrouching(boolean var1);

    public void setSleeping(boolean var1);

    @Nullable
    public AnimalTamer getFirstTrustedPlayer();

    public void setFirstTrustedPlayer(@Nullable AnimalTamer var1);

    @Nullable
    public AnimalTamer getSecondTrustedPlayer();

    public void setSecondTrustedPlayer(@Nullable AnimalTamer var1);

    public boolean isFaceplanted();

    public static enum Type {
        RED,
        SNOW;

    }
}

