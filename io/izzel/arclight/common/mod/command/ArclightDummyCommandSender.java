/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Component$Serializer
 *  net.minecraft.network.chat.MutableComponent
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package io.izzel.arclight.common.mod.command;

import io.izzel.arclight.common.mod.permission.ArclightDummyPermissible;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArclightDummyCommandSender
extends ArclightDummyPermissible
implements CommandSender {
    public CommandSourceStack stack;
    public Spigot spigot;

    public ArclightDummyCommandSender(CommandSourceStack stack) {
        this.stack = stack;
    }

    @Override
    public void sendMessage(@NotNull String s) {
        for (Component msg : CraftChatMessage.fromString(s)) {
            this.stack.m_243053_(msg);
        }
    }

    @Override
    public void sendMessage(String ... strings) {
        for (String raw : strings) {
            this.sendMessage(raw);
        }
    }

    @Override
    public void sendMessage(@Nullable UUID uuid, @NotNull String s) {
        this.sendMessage(s);
    }

    @Override
    public void sendMessage(@Nullable UUID uuid, String ... strings) {
        this.sendMessage(strings);
    }

    @Override
    @NotNull
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    @NotNull
    public String getName() {
        return this.stack.m_81368_();
    }

    @Override
    @NotNull
    public Spigot spigot() {
        if (this.spigot != null) {
            return this.spigot;
        }
        this.spigot = new Spigot();
        return this.spigot;
    }

    public class Spigot
    extends CommandSender.Spigot {
        @Override
        public void sendMessage(BaseComponent ... components) {
            for (BaseComponent raw : components) {
                this.sendMessage(raw);
            }
        }

        @Override
        public void sendMessage(@Nullable UUID sender, @NotNull BaseComponent component) {
            this.sendMessage(component);
        }

        @Override
        public void sendMessage(@Nullable UUID sender, BaseComponent ... components) {
            this.sendMessage(components);
        }

        @Override
        public void sendMessage(@NotNull BaseComponent component) {
            String json = ComponentSerializer.toString(component);
            MutableComponent result = Component.Serializer.m_130701_((String)json);
            if (result != null) {
                ArclightDummyCommandSender.this.stack.m_243053_((Component)result);
            }
        }
    }
}

