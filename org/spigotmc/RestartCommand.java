/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 */
package org.spigotmc;

import java.io.File;
import java.util.Locale;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.spigotmc.AsyncCatcher;
import org.spigotmc.SpigotConfig;
import org.spigotmc.WatchdogThread;

public class RestartCommand
extends Command {
    public RestartCommand(String name) {
        super(name);
        this.description = "Restarts the server";
        this.usageMessage = "/restart";
        this.setPermission("bukkit.command.restart");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (this.testPermission(sender)) {
            MinecraftServer.getServer().processQueue.add(new Runnable(){

                @Override
                public void run() {
                    RestartCommand.restart();
                }
            });
        }
        return true;
    }

    public static void restart() {
        RestartCommand.restart(SpigotConfig.restartScript);
    }

    private static void restart(final String restartScript) {
        AsyncCatcher.enabled = false;
        try {
            String[] split = restartScript.split(" ");
            if (split.length > 0 && new File(split[0]).isFile()) {
                System.out.println("Attempting to restart with " + restartScript);
                WatchdogThread.doStop();
                for (ServerPlayer p : MinecraftServer.getServer().m_6846_().f_11196_) {
                    p.f_8906_.disconnect(SpigotConfig.restartMessage);
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException p) {
                    // empty catch block
                }
                MinecraftServer.getServer().m_129919_().m_9718_();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException p) {
                    // empty catch block
                }
                try {
                    MinecraftServer.getServer().close();
                }
                catch (Throwable p) {
                    // empty catch block
                }
                Thread shutdownHook = new Thread(){

                    @Override
                    public void run() {
                        try {
                            String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
                            if (os.contains("win")) {
                                Runtime.getRuntime().exec("cmd /c start " + restartScript);
                            } else {
                                Runtime.getRuntime().exec("sh " + restartScript);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                shutdownHook.setDaemon(true);
                Runtime.getRuntime().addShutdownHook(shutdownHook);
            } else {
                System.out.println("Startup script '" + SpigotConfig.restartScript + "' does not exist! Stopping server.");
                try {
                    MinecraftServer.getServer().close();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            System.exit(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

