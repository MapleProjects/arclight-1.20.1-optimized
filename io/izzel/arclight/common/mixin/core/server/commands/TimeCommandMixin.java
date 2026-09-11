/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.commands.TimeCommand
 *  net.minecraft.server.level.ServerLevel
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import java.util.List;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.TimeCommand;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.Bukkit;
import org.bukkit.event.world.TimeSkipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={TimeCommand.class})
public class TimeCommandMixin {
    @Redirect(method={"setTime"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getAllLevels()Ljava/lang/Iterable;"))
    private static Iterable<ServerLevel> arclight$useSourceLevel1(MinecraftServer server, CommandSourceStack source) {
        return List.of(source.m_81372_());
    }

    @Redirect(method={"addTime"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getAllLevels()Ljava/lang/Iterable;"))
    private static Iterable<ServerLevel> arclight$useSourceLevel2(MinecraftServer server, CommandSourceStack source) {
        return List.of(source.m_81372_());
    }

    @Redirect(method={"addTime"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"))
    private static void arclight$addTimeEvent(ServerLevel serverWorld, long time) {
        TimeSkipEvent event = new TimeSkipEvent(((ServerWorldBridge)serverWorld).bridge$getWorld(), TimeSkipEvent.SkipReason.COMMAND, time - serverWorld.m_46468_());
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            serverWorld.m_8615_(serverWorld.m_46468_() + event.getSkipAmount());
        }
    }

    @Redirect(method={"setTime"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"))
    private static void arclight$setTimeEvent(ServerLevel serverWorld, long time) {
        TimeSkipEvent event = new TimeSkipEvent(((ServerWorldBridge)serverWorld).bridge$getWorld(), TimeSkipEvent.SkipReason.COMMAND, time - serverWorld.m_46468_());
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            serverWorld.m_8615_(serverWorld.m_46468_() + event.getSkipAmount());
        }
    }
}

