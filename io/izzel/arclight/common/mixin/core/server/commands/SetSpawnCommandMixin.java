/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.commands.SetSpawnCommand
 *  net.minecraft.server.level.ServerPlayer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.SetSpawnCommand;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={SetSpawnCommand.class})
public class SetSpawnCommandMixin {
    @Inject(method={"setSpawn"}, at={@At(value="HEAD")})
    private static void arclight$cause(CommandSourceStack p_138650_, Collection<ServerPlayer> players, BlockPos p_138652_, float p_138653_, CallbackInfoReturnable<Integer> cir) {
        for (ServerPlayer player : players) {
            ((ServerPlayerEntityBridge)player).bridge$pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause.COMMAND);
        }
    }
}

