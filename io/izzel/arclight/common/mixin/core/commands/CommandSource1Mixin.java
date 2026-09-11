/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.commands;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.ServerCommandSender;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets={"net/minecraft/commands/CommandSource$1"})
public class CommandSource1Mixin
implements ICommandSourceBridge {
    public CommandSender getBukkitSender(final CommandSourceStack wrapper) {
        return new ServerCommandSender(){
            private final boolean isOp;
            {
                this.isOp = wrapper.m_6761_(wrapper.m_81377_().m_7022_());
            }

            @Override
            public boolean isOp() {
                return this.isOp;
            }

            @Override
            public void setOp(boolean value) {
            }

            @Override
            public void sendMessage(@NotNull String message) {
            }

            @Override
            public void sendMessage(@NotNull String[] messages) {
            }

            @Override
            @NotNull
            public String getName() {
                return "NULL";
            }
        };
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.getBukkitSender(wrapper);
    }
}

