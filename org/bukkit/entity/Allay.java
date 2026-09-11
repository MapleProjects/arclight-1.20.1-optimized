/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Allay
extends Creature,
InventoryHolder {
    public boolean canDuplicate();

    public void setCanDuplicate(boolean var1);

    public long getDuplicationCooldown();

    public void setDuplicationCooldown(long var1);

    public void resetDuplicationCooldown();

    public boolean isDancing();

    public void startDancing(@NotNull Location var1);

    public void startDancing();

    public void stopDancing();

    @Nullable
    public Allay duplicateAllay();

    @Nullable
    public Location getJukebox();
}

