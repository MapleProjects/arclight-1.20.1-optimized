/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.Warning;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Warning(value=false)
public class PlayerBucketFishEvent
extends PlayerBucketEntityEvent {
    public PlayerBucketFishEvent(@NotNull Player player, @NotNull Fish fish, @NotNull ItemStack waterBucket, @NotNull ItemStack fishBucket, @NotNull EquipmentSlot hand) {
        super(player, fish, waterBucket, fishBucket, hand);
    }

    @Override
    @NotNull
    public Fish getEntity() {
        return (Fish)super.getEntity();
    }

    @Deprecated
    @NotNull
    public ItemStack getWaterBucket() {
        return this.getOriginalBucket();
    }

    @Deprecated
    @NotNull
    public ItemStack getFishBucket() {
        return this.getEntityBucket();
    }
}

