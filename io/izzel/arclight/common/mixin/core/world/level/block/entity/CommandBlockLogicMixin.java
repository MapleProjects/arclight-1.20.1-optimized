/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.BaseCommandBlock
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import com.google.common.base.Joiner;
import io.izzel.arclight.common.bridge.core.command.CommandSourceBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BaseCommandBlock;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.event.server.ServerCommandEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BaseCommandBlock.class})
public class CommandBlockLogicMixin {
    @Shadow
    private Component f_45405_;

    @Redirect(method={"performCommand"}, at=@At(value="INVOKE", target="Lnet/minecraft/commands/Commands;performPrefixedCommand(Lnet/minecraft/commands/CommandSourceStack;Ljava/lang/String;)I"))
    private int arclight$serverCommand(Commands commands, CommandSourceStack sender, String command) {
        Joiner joiner = Joiner.on((String)" ");
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        ServerCommandEvent event = new ServerCommandEvent(((CommandSourceBridge)sender).bridge$getBukkitSender(), command);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return 0;
        }
        command = event.getCommand();
        Object[] args = command.split(" ");
        String cmd = args[0];
        if (cmd.startsWith("minecraft:")) {
            cmd = cmd.substring("minecraft:".length());
        }
        if (cmd.startsWith("bukkit:")) {
            cmd = cmd.substring("bukkit:".length());
        }
        if (cmd.equalsIgnoreCase("stop") || cmd.equalsIgnoreCase("kick") || cmd.equalsIgnoreCase("op") || cmd.equalsIgnoreCase("deop") || cmd.equalsIgnoreCase("ban") || cmd.equalsIgnoreCase("ban-ip") || cmd.equalsIgnoreCase("pardon") || cmd.equalsIgnoreCase("pardon-ip") || cmd.equalsIgnoreCase("reload")) {
            return 0;
        }
        if (((CraftServer)Bukkit.getServer()).getCommandBlockOverride(args[0])) {
            args[0] = "minecraft:" + args[0];
        }
        return commands.m_230957_(sender, joiner.join(args));
    }

    @Inject(method={"setName"}, at={@At(value="RETURN")})
    public void arclight$setName(Component nameIn, CallbackInfo ci) {
        if (this.f_45405_ == null) {
            this.f_45405_ = Component.m_237113_((String)"@");
        }
    }
}

