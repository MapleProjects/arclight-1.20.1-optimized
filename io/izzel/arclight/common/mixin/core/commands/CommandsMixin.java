/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.RootCommandNode
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.SharedSuggestionProvider
 *  net.minecraft.commands.synchronization.SuggestionProviders
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundCommandsPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.server.command.CommandHelper
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.commands;

import com.google.common.collect.Maps;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import io.izzel.arclight.common.mod.compat.CommandNodeHooks;
import io.izzel.arclight.common.mod.util.BukkitDispatcher;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundCommandsPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.command.CommandHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Commands.class})
public abstract class CommandsMixin {
    @Mutable
    @Shadow
    @Final
    private CommandDispatcher<CommandSourceStack> f_82090_;

    @Shadow
    public abstract int m_242674_(ParseResults<CommandSourceStack> var1, String var2);

    @Shadow
    public abstract int m_230957_(CommandSourceStack var1, String var2);

    @Shadow
    protected abstract void m_82112_(CommandNode<CommandSourceStack> var1, CommandNode<SharedSuggestionProvider> var2, CommandSourceStack var3, Map<CommandNode<CommandSourceStack>, CommandNode<SharedSuggestionProvider>> var4);

    public void arclight$constructor() {
        this.f_82090_ = new BukkitDispatcher((Commands)this);
        this.f_82090_.setConsumer((context, b, i) -> ((CommandSourceStack)context.getSource()).m_81342_(context, b, i));
    }

    public int performPrefixedCommand(CommandSourceStack commandSourceStack, String s, String label) {
        return this.m_230957_(commandSourceStack, s);
    }

    public int performCommand(ParseResults<CommandSourceStack> parseResults, String s, String label) {
        return this.m_242674_(parseResults, s);
    }

    @Overwrite
    public void m_82095_(ServerPlayer player) {
        if (SpigotConfig.tabComplete < 0) {
            return;
        }
        IdentityHashMap map = Maps.newIdentityHashMap();
        RootCommandNode vanillaRoot = new RootCommandNode();
        Commands vanillaCommands = ((MinecraftServerBridge)player.f_8924_).bridge$getVanillaCommands();
        map.put(vanillaCommands.m_82094_().getRoot(), vanillaRoot);
        CommandHelper.mergeCommandNode((CommandNode)vanillaCommands.m_82094_().getRoot(), (CommandNode)vanillaRoot, (Map)map, (Object)player.m_20203_(), ctx -> 0, suggest -> SuggestionProviders.m_121664_((SuggestionProvider)suggest));
        RootCommandNode node = new RootCommandNode();
        map.put(this.f_82090_.getRoot(), node);
        CommandHelper.mergeCommandNode((CommandNode)this.f_82090_.getRoot(), (CommandNode)node, (Map)map, (Object)player.m_20203_(), ctx -> 0, suggest -> SuggestionProviders.m_121664_((SuggestionProvider)suggest));
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        for (CommandNode child : node.getChildren()) {
            set.add(child.getName());
        }
        PlayerCommandSendEvent event = new PlayerCommandSendEvent((Player)((ServerPlayerEntityBridge)player).bridge$getBukkitEntity(), new LinkedHashSet<String>(set));
        Bukkit.getPluginManager().callEvent(event);
        for (String s : set) {
            if (event.getCommands().contains(s)) continue;
            CommandNodeHooks.removeCommand(node, s);
        }
        player.f_8906_.m_9829_((Packet)new ClientboundCommandsPacket(node));
    }

    @Redirect(method={"fillUsableCommands"}, at=@At(value="INVOKE", remap=false, target="Lcom/mojang/brigadier/tree/CommandNode;canUse(Ljava/lang/Object;)Z"))
    private <S> boolean arclight$canUse(CommandNode<S> commandNode, S source) {
        return CommandNodeHooks.canUse(commandNode, source);
    }
}

