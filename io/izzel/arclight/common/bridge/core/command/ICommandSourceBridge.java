/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 */
package io.izzel.arclight.common.bridge.core.command;

import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;

public interface ICommandSourceBridge {
    default public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return null;
    }
}

