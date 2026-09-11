/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  jline.console.ConsoleReader
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.dedicated.DedicatedPlayerList
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.players.PlayerList
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.CommandEvent
 *  net.minecraftforge.eventbus.api.Event
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.collect.Lists;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import io.izzel.arclight.common.bridge.bukkit.CraftServerBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.dedicated.DedicatedPlayerList;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.Event;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftBlockCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftCommandMap;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.help.SimpleHelpMap;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftScheduler;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.scheduler.BukkitWorker;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftServer.class}, remap=false)
public abstract class CraftServerMixin
implements CraftServerBridge {
    @Shadow
    @Final
    private CraftCommandMap commandMap;
    @Shadow
    @Final
    private SimplePluginManager pluginManager;
    @Shadow
    @Final
    private SimpleHelpMap helpMap;
    @Shadow
    @Final
    protected DedicatedServer console;
    @Shadow
    @Final
    @Mutable
    private String serverName;
    @Shadow
    @Final
    @Mutable
    protected DedicatedPlayerList playerList;
    @Shadow
    @Final
    @Mutable
    private List<CraftPlayer> playerView;
    @Shadow
    @Final
    private Map<String, World> worlds;
    @Shadow
    public int reloadCount;
    @Shadow
    private YamlConfiguration configuration;
    @Shadow
    private YamlConfiguration commandsConfiguration;
    @Shadow
    @Final
    private Logger logger;
    @Shadow
    private boolean overrideAllCommandBlockCommands;
    @Shadow
    public boolean ignoreVanillaPermissions;
    @Shadow
    @Final
    private String serverVersion;

    @Shadow
    protected abstract void enablePlugin(Plugin var1);

    @Shadow
    protected abstract void loadCustomPermissions();

    @Shadow
    protected abstract File getConfigFile();

    @Shadow
    protected abstract File getCommandsConfigFile();

    @Shadow
    public abstract void reloadData();

    @Shadow
    public abstract CraftScheduler getScheduler();

    @Shadow
    public abstract Logger getLogger();

    @Shadow
    public abstract void loadPlugins();

    @Shadow
    public abstract void enablePlugins(PluginLoadOrder var1);

    @Shadow
    public abstract PluginManager getPluginManager();

    @Accessor(value="logger")
    @Mutable
    public abstract void setLogger(Logger var1);

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void arclight$setBrand(DedicatedServer console, PlayerList playerList, CallbackInfo ci) {
        this.serverName = "Arclight";
    }

    @Overwrite(remap=false)
    public String getName() {
        return "Arclight";
    }

    @Override
    public void bridge$setPlayerList(PlayerList playerList) {
        this.playerList = (DedicatedPlayerList)playerList;
        this.playerView = Collections.unmodifiableList(Lists.transform((List)playerList.f_11196_, player -> ((ServerPlayerEntityBridge)player).bridge$getBukkitEntity()));
    }

    @Overwrite(remap=false)
    public ConsoleReader getReader() {
        return null;
    }

    @ModifyVariable(method={"dispatchCommand"}, remap=false, index=2, at=@At(value="INVOKE", shift=At.Shift.AFTER, target="Lorg/spigotmc/AsyncCatcher;catchOp(Ljava/lang/String;)V"))
    private String arclight$forgeCommandEvent(String commandLine, CommandSender sender) {
        ParseResults parse;
        CommandEvent event;
        CommandSourceStack commandSource;
        if (sender instanceof CraftEntity) {
            commandSource = ((CraftEntity)sender).getHandle().m_20203_();
        } else if (sender == Bukkit.getConsoleSender()) {
            commandSource = ArclightServer.getMinecraftServer().m_129893_();
        } else if (sender instanceof CraftBlockCommandSender) {
            commandSource = ((CraftBlockCommandSender)sender).getWrapper();
        } else {
            return commandLine;
        }
        StringReader stringreader = new StringReader("/" + commandLine);
        if (stringreader.canRead() && stringreader.peek() == '/') {
            stringreader.skip();
        }
        if (MinecraftForge.EVENT_BUS.post((Event)(event = new CommandEvent(parse = ArclightServer.getMinecraftServer().m_129892_().m_82094_().parse(stringreader, (Object)commandSource))))) {
            return null;
        }
        if (event.getException() != null) {
            return null;
        }
        String s = event.getParseResults().getReader().getString();
        return s.startsWith("/") ? s.substring(1) : s;
    }

    @Inject(method={"dispatchCommand"}, remap=false, cancellable=true, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lorg/spigotmc/AsyncCatcher;catchOp(Ljava/lang/String;)V")})
    private void arclight$returnIfFail(CommandSender sender, String commandLine, CallbackInfoReturnable<Boolean> cir) {
        if (commandLine == null) {
            cir.setReturnValue((Object)false);
        }
    }

    @Override
    public void bridge$removeWorld(ServerLevel world) {
        if (world == null) {
            return;
        }
        this.worlds.remove(((WorldBridge)world).bridge$getWorld().getName().toLowerCase(Locale.ROOT));
    }

    @Overwrite(remap=false)
    public void reload() {
        ++this.reloadCount;
        this.configuration = YamlConfiguration.loadConfiguration(this.getConfigFile());
        this.commandsConfiguration = YamlConfiguration.loadConfiguration(this.getCommandsConfigFile());
        try {
            this.playerList.m_11299_().m_11399_();
        }
        catch (IOException var12) {
            this.logger.log(Level.WARNING, "Failed to load banned-ips.json, " + var12.getMessage());
        }
        try {
            this.playerList.m_11295_().m_11399_();
        }
        catch (IOException var11) {
            this.logger.log(Level.WARNING, "Failed to load banned-players.json, " + var11.getMessage());
        }
        this.pluginManager.clearPlugins();
        this.commandMap.clearCommands();
        this.reloadData();
        SpigotConfig.registerCommands();
        this.overrideAllCommandBlockCommands = this.commandsConfiguration.getStringList("command-block-overrides").contains("*");
        this.ignoreVanillaPermissions = this.commandsConfiguration.getBoolean("ignore-vanilla-permissions");
        for (int pollCount = 0; pollCount < 50 && this.getScheduler().getActiveWorkers().size() > 0; ++pollCount) {
            try {
                Thread.sleep(50L);
                continue;
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
        List<BukkitWorker> overdueWorkers = this.getScheduler().getActiveWorkers();
        for (BukkitWorker worker : overdueWorkers) {
            Plugin plugin = worker.getOwner();
            this.getLogger().log(Level.SEVERE, String.format("Nag author(s): '%s' of '%s' about the following: %s", plugin.getDescription().getAuthors(), plugin.getDescription().getFullName(), "This plugin is not properly shutting down its async tasks when it is being reloaded.  This may cause conflicts with the newly loaded version of the plugin"));
        }
        this.loadPlugins();
        this.enablePlugins(PluginLoadOrder.STARTUP);
        this.enablePlugins(PluginLoadOrder.POSTWORLD);
        this.getPluginManager().callEvent(new ServerLoadEvent(ServerLoadEvent.LoadType.RELOAD));
    }
}

