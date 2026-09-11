/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Command
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  com.mojang.brigadier.tree.LiteralCommandNode
 *  net.minecraft.commands.CommandSourceStack
 */
package org.bukkit.craftbukkit.v1_20_R1.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.logging.Level;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;

public class BukkitCommandWrapper
implements com.mojang.brigadier.Command<CommandSourceStack>,
Predicate<CommandSourceStack>,
SuggestionProvider<CommandSourceStack> {
    private final CraftServer server;
    private final Command command;

    public BukkitCommandWrapper(CraftServer server, Command command) {
        this.server = server;
        this.command = command;
    }

    public LiteralCommandNode<CommandSourceStack> register(CommandDispatcher<CommandSourceStack> dispatcher, String label) {
        return dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)LiteralArgumentBuilder.literal((String)label).requires((Predicate)this)).executes((com.mojang.brigadier.Command)this)).then(RequiredArgumentBuilder.argument((String)"args", (ArgumentType)StringArgumentType.greedyString()).suggests((SuggestionProvider)this).executes((com.mojang.brigadier.Command)this)));
    }

    @Override
    public boolean test(CommandSourceStack wrapper) {
        return this.command.testPermissionSilent(wrapper.getBukkitSender());
    }

    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSender sender = ((CommandSourceStack)context.getSource()).getBukkitSender();
        try {
            return this.server.dispatchCommand(sender, context.getInput()) ? 1 : 0;
        }
        catch (CommandException ex) {
            sender.sendMessage((Object)((Object)ChatColor.RED) + "An internal error occurred while attempting to perform this command");
            this.server.getLogger().log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        List<String> results = this.server.tabComplete(((CommandSourceStack)context.getSource()).getBukkitSender(), builder.getInput(), ((CommandSourceStack)context.getSource()).m_81372_(), ((CommandSourceStack)context.getSource()).m_81371_(), true);
        builder = builder.createOffset(builder.getInput().lastIndexOf(32) + 1);
        for (String s : results) {
            builder.suggest(s);
        }
        return builder.buildFuture();
    }
}

