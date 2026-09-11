/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.commands.GameRuleCommand
 *  net.minecraft.world.level.GameRules
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={GameRuleCommand.class})
public class GameRuleCommandMixin {
    @Redirect(method={"setRule"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getGameRules()Lnet/minecraft/world/level/GameRules;"))
    private static GameRules arclight$perWorldGameRule(MinecraftServer minecraftServer, CommandContext<CommandSourceStack> context) {
        return ((CommandSourceStack)context.getSource()).m_81372_().m_46469_();
    }

    @Redirect(method={"queryRule"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getGameRules()Lnet/minecraft/world/level/GameRules;"))
    private static GameRules arclight$perWorldGameRule2(MinecraftServer minecraftServer, CommandSourceStack source) {
        return source.m_81372_().m_46469_();
    }
}

