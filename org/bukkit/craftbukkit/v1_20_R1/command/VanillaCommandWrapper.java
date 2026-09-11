/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Preconditions
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.tree.CommandNode
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 */
package org.bukkit.craftbukkit.v1_20_R1.command;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.tree.CommandNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.ProxiedCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftBlockCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftRemoteConsoleCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.ProxiedNativeCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.CommandMinecart;

public final class VanillaCommandWrapper
extends BukkitCommand {
    private final Commands dispatcher;
    public final CommandNode<CommandSourceStack> vanillaCommand;

    public VanillaCommandWrapper(Commands dispatcher, CommandNode<CommandSourceStack> vanillaCommand) {
        super(vanillaCommand.getName(), "A Mojang provided command.", vanillaCommand.getUsageText(), Collections.EMPTY_LIST);
        this.dispatcher = dispatcher;
        this.vanillaCommand = vanillaCommand;
        this.setPermission(VanillaCommandWrapper.getPermission(vanillaCommand));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        CommandSourceStack icommandlistener = VanillaCommandWrapper.getListener(sender);
        this.dispatcher.performPrefixedCommand(icommandlistener, this.toDispatcher(args, this.getName()), this.toDispatcher(args, commandLabel));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"Sender cannot be null");
        Preconditions.checkArgument((args != null ? 1 : 0) != 0, (Object)"Arguments cannot be null");
        Preconditions.checkArgument((alias != null ? 1 : 0) != 0, (Object)"Alias cannot be null");
        CommandSourceStack icommandlistener = VanillaCommandWrapper.getListener(sender);
        ParseResults parsed = this.dispatcher.m_82094_().parse(this.toDispatcher(args, this.getName()), (Object)icommandlistener);
        ArrayList<String> results = new ArrayList<String>();
        this.dispatcher.m_82094_().getCompletionSuggestions(parsed).thenAccept(suggestions -> suggestions.getList().forEach(s -> {
            boolean bl = results.add(s.getText());
        }));
        return results;
    }

    public static CommandSourceStack getListener(CommandSender sender) {
        if (sender instanceof Entity) {
            if (sender instanceof CommandMinecart) {
                return ((CraftMinecartCommand)sender).getHandle().m_38534_().m_6712_();
            }
            return ((CraftEntity)sender).getHandle().m_20203_();
        }
        if (sender instanceof BlockCommandSender) {
            return ((CraftBlockCommandSender)sender).getWrapper();
        }
        if (sender instanceof RemoteConsoleCommandSender) {
            return ((CraftRemoteConsoleCommandSender)sender).getListener().m_11514_();
        }
        if (sender instanceof ConsoleCommandSender) {
            return ((CraftServer)sender.getServer()).getServer().m_129893_();
        }
        if (sender instanceof ProxiedCommandSender) {
            return ((ProxiedNativeCommandSender)sender).getHandle();
        }
        throw new IllegalArgumentException("Cannot make " + sender + " a vanilla command listener");
    }

    public static String getPermission(CommandNode<CommandSourceStack> vanillaCommand) {
        return "minecraft.command." + (vanillaCommand.getRedirect() == null ? vanillaCommand.getName() : vanillaCommand.getRedirect().getName());
    }

    private String toDispatcher(String[] args, String name) {
        return String.valueOf(name) + (args.length > 0 ? " " + Joiner.on((char)' ').join((Object[])args) : "");
    }
}

