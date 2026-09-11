/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 */
package io.izzel.arclight.common.mod.server.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.event.block.CauldronLevelChangeEvent;

public class CauldronHooks {
    private static Entity entity;
    private static CauldronLevelChangeEvent.ChangeReason reason;
    private static boolean lastRet;

    public static void setChangeReason(Entity entity, CauldronLevelChangeEvent.ChangeReason reason) {
        CauldronHooks.entity = entity;
        CauldronHooks.reason = reason;
    }

    public static void reset() {
        entity = null;
        reason = CauldronLevelChangeEvent.ChangeReason.UNKNOWN;
        lastRet = true;
    }

    public static Entity getEntity() {
        return entity;
    }

    public static CauldronLevelChangeEvent.ChangeReason getReason() {
        return reason;
    }

    public static boolean getResult() {
        return lastRet;
    }

    public static boolean changeLevel(BlockState old, Level world, BlockPos pos, BlockState state, Entity entity, CauldronLevelChangeEvent.ChangeReason reason) {
        CraftBlockState newState = CraftBlockStates.getBlockState((LevelAccessor)world, pos);
        newState.setData(state);
        CauldronLevelChangeEvent event = new CauldronLevelChangeEvent(CraftBlock.at((LevelAccessor)world, pos), entity == null ? null : ((EntityBridge)entity).bridge$getBukkitEntity(), reason, newState);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            lastRet = false;
            return false;
        }
        newState.update(true);
        lastRet = true;
        return true;
    }

    static {
        reason = CauldronLevelChangeEvent.ChangeReason.UNKNOWN;
        lastRet = true;
    }
}

