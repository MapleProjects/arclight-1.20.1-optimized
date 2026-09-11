/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.Commands
 */
package io.izzel.arclight.common.bridge.core.server;

import net.minecraft.commands.Commands;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;

public interface MinecraftServerBridge {
    public void bridge$setAutosavePeriod(int var1);

    public void bridge$setConsole(ConsoleCommandSender var1);

    public void bridge$setServer(CraftServer var1);

    public RemoteConsoleCommandSender bridge$getRemoteConsole();

    public void bridge$setRemoteConsole(RemoteConsoleCommandSender var1);

    public void bridge$queuedProcess(Runnable var1);

    public void bridge$drainQueuedTasks();

    public boolean bridge$hasStopped();

    public Commands bridge$getVanillaCommands();
}

