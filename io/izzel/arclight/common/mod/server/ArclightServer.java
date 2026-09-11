/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.graph.Graphs
 *  com.google.common.graph.MutableGraph
 *  com.google.common.util.concurrent.ThreadFactoryBuilder
 *  io.izzel.arclight.api.Arclight
 *  io.izzel.arclight.api.ArclightServer
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.server;

import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.izzel.arclight.api.Arclight;
import io.izzel.arclight.common.bridge.bukkit.CraftServerBridge;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import io.izzel.arclight.common.mixin.bukkit.SimplePluginManagerAccessor;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.BukkitRegistry;
import io.izzel.arclight.common.mod.server.api.DefaultArclightServer;
import java.io.File;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.command.ColouredConsoleSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.spigotmc.SpigotConfig;

public class ArclightServer {
    private static final ExecutorWithThread mainThreadExecutor = new ExecutorWithThread(){

        @Override
        public void execute(@NotNull Runnable command) {
            ArclightServer.executeOnMainThread(command);
        }

        @Override
        public Thread get() {
            return ArclightServer.getMinecraftServer().m_6304_();
        }
    };
    private static final ExecutorService chatExecutor = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Async Chat Thread - #%d").setThreadFactory(ArclightServer.chatFactory()).build());
    private static CraftServer server;

    private static ThreadFactory chatFactory() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return r -> {
            Thread thread = new Thread(group, r);
            thread.setContextClassLoader(classLoader);
            return thread;
        };
    }

    public static Set<String> iterateDepends(PluginDescriptionFile desc) {
        SimplePluginManager manager = (SimplePluginManager)Bukkit.getPluginManager();
        MutableGraph<String> dependencyGraph = ((SimplePluginManagerAccessor)((Object)manager)).arclight$dependencyGraph();
        if (dependencyGraph.nodes().contains(desc.getName())) {
            return Graphs.reachableNodes(dependencyGraph, (Object)desc.getName());
        }
        return Collections.emptySet();
    }

    public static CraftServer createOrLoad(DedicatedServer console, PlayerList playerList) {
        if (server == null) {
            Arclight.setServer((io.izzel.arclight.api.ArclightServer)new DefaultArclightServer());
            try {
                server = new CraftServer(console, playerList);
                ((MinecraftServerBridge)console).bridge$setServer(server);
                ((MinecraftServerBridge)console).bridge$setConsole(ColouredConsoleSender.getInstance());
                Class.forName("org.sqlite.JDBC");
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (Throwable t) {
                throw new RuntimeException("Error initializing Arclight", t);
            }
            try {
                ArclightMod.LOGGER.info("registry.begin");
                BukkitRegistry.registerAll(console);
                SpigotConfig.init(new File("./spigot.yml"));
                SpigotConfig.registerCommands();
            }
            catch (Throwable t) {
                ArclightMod.LOGGER.error("registry.error", t);
                throw t;
            }
        }
        ((CraftServerBridge)((Object)server)).bridge$setPlayerList(playerList);
        return server;
    }

    public static CraftServer get() {
        return Objects.requireNonNull(server);
    }

    public static boolean isPrimaryThread() {
        if (server == null) {
            return Thread.currentThread().equals(ArclightServer.getMinecraftServer().m_6304_());
        }
        return server.isPrimaryThread();
    }

    public static MinecraftServer getMinecraftServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static void executeOnMainThread(Runnable runnable) {
        ((MinecraftServerBridge)ArclightServer.getMinecraftServer()).bridge$queuedProcess(runnable);
        if (LockSupport.getBlocker(ArclightServer.getMinecraftServer().m_6304_()) == "waiting for tasks") {
            LockSupport.unpark(ArclightServer.getMinecraftServer().m_6304_());
        }
    }

    public static Executor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public static ExecutorService getChatExecutor() {
        return chatExecutor;
    }

    public static World.Environment getEnvironment(ResourceKey<LevelStem> key) {
        return (World.Environment)((Object)BukkitRegistry.DIM_MAP.getOrDefault(key, (Object)World.Environment.CUSTOM));
    }

    private static interface ExecutorWithThread
    extends Executor,
    Supplier<Thread> {
    }
}

