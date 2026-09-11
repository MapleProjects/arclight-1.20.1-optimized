/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.commands;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={CommandSource.class})
public interface CommandSourceMixin
extends ICommandSourceBridge {
    default public CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return this.bridge$getBukkitSender(wrapper);
    }
}

