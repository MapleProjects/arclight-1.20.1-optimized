/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.tree.CommandNode
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.players.PlayerList
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.tree.CommandNode;
import io.izzel.arclight.common.bridge.core.command.CommandSourceBridge;
import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.command.ArclightDummyCommandSender;
import io.izzel.arclight.common.mod.compat.CommandNodeHooks;
import java.util.Objects;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.command.VanillaCommandWrapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CommandSourceStack.class})
public abstract class CommandSourceStackMixin
implements CommandSourceBridge {
    @Shadow
    @Final
    @Mutable
    public CommandSource f_81288_;
    @Shadow
    @Final
    private int f_81291_;
    public CommandNode currentCommand;

    @Shadow
    public abstract ServerLevel m_81372_();

    @Override
    public void bridge$setSource(CommandSource source) {
        this.f_81288_ = source;
    }

    @Inject(method={"hasPermission"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$checkPermission(int level, CallbackInfoReturnable<Boolean> cir) {
        CommandNode<?> currentCommand = this.bridge$getCurrentCommand();
        if (currentCommand != null) {
            cir.setReturnValue((Object)this.hasPermission(level, VanillaCommandWrapper.getPermission(currentCommand)));
        }
    }

    @Redirect(method={"broadcastToAdmins"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;isOp(Lcom/mojang/authlib/GameProfile;)Z"))
    private boolean arclight$feedbackPermission(PlayerList instance, GameProfile profile) {
        return ((ServerPlayerEntityBridge)instance.m_11259_(profile.getId())).bridge$getBukkitEntity().hasPermission("minecraft.admin.command_feedback");
    }

    public boolean hasPermission(int i, String bukkitPermission) {
        return (this.m_81372_() == null || !((CraftServer)Bukkit.getServer()).ignoreVanillaPermissions) && this.f_81291_ >= i || this.getBukkitSender().hasPermission(bukkitPermission);
    }

    @Override
    public boolean bridge$hasPermission(int i, String bukkitPermission) {
        return this.hasPermission(i, bukkitPermission);
    }

    @Override
    public CommandNode<?> bridge$getCurrentCommand() {
        if (this.currentCommand == null) {
            return CommandNodeHooks.getCurrent();
        }
        return this.currentCommand;
    }

    @Override
    public void bridge$setCurrentCommand(CommandNode<?> node) {
        this.currentCommand = node;
    }

    public CommandSender getBukkitSender() {
        CommandSourceStack thus = (CommandSourceStack)this;
        CommandSender sender = ((ICommandSourceBridge)this.f_81288_).bridge$getBukkitSender(thus);
        return Objects.requireNonNullElseGet(sender, () -> new ArclightDummyCommandSender(thus));
    }

    @Override
    public CommandSender bridge$getBukkitSender() {
        return this.getBukkitSender();
    }
}

