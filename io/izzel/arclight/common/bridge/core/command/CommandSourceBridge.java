/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.tree.CommandNode
 *  net.minecraft.commands.CommandSource
 */
package io.izzel.arclight.common.bridge.core.command;

import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSource;
import org.bukkit.command.CommandSender;

public interface CommandSourceBridge {
    public void bridge$setSource(CommandSource var1);

    public CommandNode<?> bridge$getCurrentCommand();

    public void bridge$setCurrentCommand(CommandNode<?> var1);

    public boolean bridge$hasPermission(int var1, String var2);

    public CommandSender bridge$getBukkitSender();
}

