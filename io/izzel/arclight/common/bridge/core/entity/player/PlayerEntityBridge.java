/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.Unit
 *  net.minecraft.world.entity.player.Player$BedSleepingProblem
 */
package io.izzel.arclight.common.bridge.core.entity.player;

import com.mojang.datafixers.util.Either;
import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.event.entity.EntityExhaustionEvent;

public interface PlayerEntityBridge
extends LivingEntityBridge {
    public boolean bridge$isFauxSleeping();

    @Override
    public CraftHumanEntity bridge$getBukkitEntity();

    public Either<Player.BedSleepingProblem, Unit> bridge$trySleep(BlockPos var1, boolean var2);

    public void bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason var1);
}

