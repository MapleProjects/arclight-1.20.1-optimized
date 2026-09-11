/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import java.util.Collection;
import org.bukkit.Nameable;
import org.bukkit.block.Lockable;
import org.bukkit.block.TileState;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Beacon
extends TileState,
Lockable,
Nameable {
    @NotNull
    public Collection<LivingEntity> getEntitiesInRange();

    public int getTier();

    @Nullable
    public PotionEffect getPrimaryEffect();

    public void setPrimaryEffect(@Nullable PotionEffectType var1);

    @Nullable
    public PotionEffect getSecondaryEffect();

    public void setSecondaryEffect(@Nullable PotionEffectType var1);
}

