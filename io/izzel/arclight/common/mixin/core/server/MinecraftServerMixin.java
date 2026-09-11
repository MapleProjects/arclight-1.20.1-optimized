/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  io.izzel.arclight.api.ArclightVersion
 *  it.unimi.dsi.fastutil.longs.LongIterator
 *  javax.annotation.Nullable
 *  joptsimple.OptionSet
 *  net.minecraft.CrashReport
 *  net.minecraft.ReportedException
 *  net.minecraft.SystemReport
 *  net.minecraft.Util
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.LayeredRegistryAccess
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess$Frozen
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.protocol.status.ServerStatus
 *  net.minecraft.network.protocol.status.ServerStatus$Favicon
 *  net.minecraft.obfuscate.DontObfuscate
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.MinecraftServer$ReloadableResources
 *  net.minecraft.server.MinecraftServer$TimeProfiler
 *  net.minecraft.server.RegistryLayer
 *  net.minecraft.server.ServerFunctionManager
 *  net.minecraft.server.Services
 *  net.minecraft.server.TickTask
 *  net.minecraft.server.WorldLoader$DataLoadContext
 *  net.minecraft.server.WorldStem
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.server.level.progress.ChunkProgressListener
 *  net.minecraft.server.level.progress.ChunkProgressListenerFactory
 *  net.minecraft.server.packs.repository.PackRepository
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.util.Unit
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraft.util.profiling.jfr.JvmProfiler
 *  net.minecraft.util.thread.ReentrantBlockableEventLoop
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.DataPackConfig
 *  net.minecraft.world.level.ForcedChunksSavedData
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.levelgen.WorldOptions
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  net.minecraft.world.level.storage.ServerLevelData
 *  net.minecraft.world.level.storage.WorldData
 *  net.minecraft.world.scores.Scoreboard
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.world.ForgeChunkManager
 *  net.minecraftforge.event.level.LevelEvent$Load
 *  net.minecraftforge.event.level.LevelEvent$Unload
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.internal.BrandingControl
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.server;

import com.mojang.datafixers.DataFixer;
import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.ArclightConstants;
import io.izzel.arclight.common.mod.server.BukkitRegistry;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.common.mod.util.BukkitOptionParser;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BooleanSupplier;
import javax.annotation.Nullable;
import joptsimple.OptionSet;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.SystemReport;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.obfuscate.DontObfuscate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ServerFunctionManager;
import net.minecraft.server.Services;
import net.minecraft.server.TickTask;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.players.PlayerList;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.util.profiling.jfr.JvmProfiler;
import net.minecraft.util.thread.ReentrantBlockableEventLoop;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.DataPackConfig;
import net.minecraft.world.level.ForcedChunksSavedData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.ForgeChunkManager;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.internal.BrandingControl;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardManager;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.PluginLoadOrder;
import org.slf4j.Logger;
import org.spigotmc.WatchdogThread;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={MinecraftServer.class})
public abstract class MinecraftServerMixin
extends ReentrantBlockableEventLoop<TickTask>
implements MinecraftServerBridge,
ICommandSourceBridge {
    @Shadow
    private int f_129766_;
    @Shadow
    protected long f_129726_;
    @Shadow
    private ServerStatus f_129757_;
    @Shadow
    @Nullable
    private String f_129709_;
    @Shadow
    private volatile boolean f_129764_;
    @Shadow
    private long f_129718_;
    @Shadow
    @Final
    static Logger f_129750_;
    @Shadow
    private boolean f_129728_;
    @Shadow
    private long f_129727_;
    @Shadow
    private volatile boolean f_129717_;
    @Shadow
    private boolean f_129765_;
    @Shadow
    private ProfilerFiller f_129754_;
    @Shadow
    @Final
    public Map<ResourceKey<Level>, ServerLevel> f_129762_;
    @Shadow
    protected WorldData f_129749_;
    @Shadow
    private float f_129737_;
    @Shadow
    @Final
    private PackRepository f_129730_;
    @Shadow
    @Final
    public Executor f_129738_;
    @Shadow
    public MinecraftServer.ReloadableResources f_129740_;
    @Shadow
    @Final
    private ServerFunctionManager f_129734_;
    @Shadow
    @Final
    protected Services f_236721_;
    @Shadow
    @Final
    private StructureTemplateManager f_236720_;
    @Shadow
    private boolean f_177877_;
    @Shadow
    @Nullable
    private MinecraftServer.TimeProfiler f_177876_;
    @Shadow
    @Nullable
    private ServerStatus.Favicon f_271173_;
    public WorldLoader.DataLoadContext worldLoader;
    private boolean forceTicks;
    public CraftServer server;
    public OptionSet options;
    public ConsoleCommandSender console;
    public RemoteConsoleCommandSender remoteConsole;
    public Queue<Runnable> processQueue = new ConcurrentLinkedQueue<Runnable>();
    public int autosavePeriod;
    public Commands vanillaCommandDispatcher;
    private boolean hasStopped = false;
    private final Object stopLock = new Object();
    private static final int TPS = 20;
    private static final int TICK_TIME = 50000000;
    private static final int SAMPLE_INTERVAL = 100;
    private static int currentTick;
    public final double[] recentTps = new double[3];
    @Unique
    private transient ServerLevel arclight$capturedLevel;

    @Shadow(aliases={"m_7038_"})
    protected abstract boolean m_7038_() throws IOException;

    @Shadow
    public abstract void m_5705_(BooleanSupplier var1);

    @Shadow
    protected abstract boolean m_129960_();

    @Shadow
    protected abstract void m_130012_();

    @Shadow
    protected abstract void m_7268_(CrashReport var1);

    @Shadow
    public abstract File m_6237_();

    @Shadow
    public abstract void m_7041_();

    @Shadow
    public abstract void m_6988_();

    @Shadow
    public abstract Commands m_129892_();

    @Shadow
    protected abstract void m_129962_();

    @Shadow
    public abstract ServerLevel m_129783_();

    @Shadow
    protected abstract void m_129847_(WorldData var1);

    @Shadow(remap=false)
    @Deprecated
    public abstract void markWorldsDirty();

    @Shadow
    private static void m_177896_(ServerLevel p_177897_, ServerLevelData p_177898_, boolean p_177899_, boolean p_177900_) {
    }

    @Shadow
    public abstract boolean m_7004_();

    @Shadow
    public abstract boolean m_6998_();

    @Shadow
    protected abstract void m_177945_();

    @Shadow
    protected abstract void m_177946_();

    @Shadow
    public abstract SystemReport m_177935_(SystemReport var1);

    @Shadow
    public abstract boolean m_6982_();

    @Shadow
    public abstract int m_7034_();

    @Shadow
    public abstract RegistryAccess.Frozen m_206579_();

    @Shadow
    private static DataPackConfig m_129817_(PackRepository p_129818_) {
        return null;
    }

    @Shadow
    public abstract PlayerList m_6846_();

    @Shadow
    public abstract boolean m_214005_();

    @Shadow
    private static CrashReport m_206568_(Throwable p_206569_) {
        return null;
    }

    @Shadow
    public abstract LayeredRegistryAccess<RegistryLayer> m_247573_();

    @Shadow
    protected abstract ServerStatus m_271988_();

    @Shadow
    protected abstract Optional<ServerStatus.Favicon> m_272273_();

    public MinecraftServerMixin(String name) {
        super(name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean hasStopped() {
        Object object = this.stopLock;
        synchronized (object) {
            return this.hasStopped;
        }
    }

    @Override
    public boolean bridge$hasStopped() {
        return this.hasStopped();
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void arclight$loadOptions(Thread p_236723_, LevelStorageSource.LevelStorageAccess p_236724_, PackRepository p_236725_, WorldStem worldStem, Proxy p_236727_, DataFixer p_236728_, Services p_236729_, ChunkProgressListenerFactory p_236730_, CallbackInfo ci) {
        String[] arguments = ManagementFactory.getRuntimeMXBean().getInputArguments().toArray(new String[0]);
        BukkitOptionParser parser = new BukkitOptionParser();
        try {
            this.options = parser.parse(arguments);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.vanillaCommandDispatcher = worldStem.f_206893_().m_206888_();
        this.worldLoader = ArclightCaptures.getDataLoadContext();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Overwrite
    protected void m_130011_() {
        try {
            if (!this.m_7038_()) {
                throw new IllegalStateException("Failed to initialize server");
            }
            ServerLifecycleHooks.handleServerStarted((MinecraftServer)((MinecraftServer)this));
            this.f_129726_ = Util.m_137550_();
            this.f_271173_ = this.m_272273_().orElse(null);
            this.f_129757_ = this.m_271988_();
            Arrays.fill(this.recentTps, 20.0);
            long tickSection = Util.m_137550_();
            long tickCount = 1L;
            while (this.f_129764_) {
                long curTime = Util.m_137550_();
                long i = curTime - this.f_129726_;
                if (i > 2000L && this.f_129726_ - this.f_129718_ >= 15000L) {
                    long j = i / 50L;
                    if (this.server.getWarnOnOverload()) {
                        f_129750_.warn("Can't keep up! Is the server overloaded? Running {}ms or {} ticks behind", (Object)i, (Object)j);
                    }
                    this.f_129726_ += j * 50L;
                    this.f_129718_ = this.f_129726_;
                }
                if (tickCount++ % 100L == 0L) {
                    double currentTps = 1000.0 / (double)(curTime - tickSection) * 100.0;
                    this.recentTps[0] = MinecraftServerMixin.calcTps(this.recentTps[0], 0.92, currentTps);
                    this.recentTps[1] = MinecraftServerMixin.calcTps(this.recentTps[1], 0.9835, currentTps);
                    this.recentTps[2] = MinecraftServerMixin.calcTps(this.recentTps[2], 0.9945, currentTps);
                    tickSection = curTime;
                }
                currentTick = (int)(System.currentTimeMillis() / 50L);
                if (this.f_177877_) {
                    this.f_177877_ = false;
                    this.f_177876_ = new MinecraftServer.TimeProfiler(Util.m_137569_(), this.f_129766_);
                }
                this.f_129726_ += 50L;
                this.m_177945_();
                this.f_129754_.m_6180_("tick");
                this.m_5705_(this::m_129960_);
                this.f_129754_.m_6182_("nextTickWait");
                this.f_129728_ = true;
                this.f_129727_ = Math.max(Util.m_137550_() + 50L, this.f_129726_);
                this.m_130012_();
                this.f_129754_.m_7238_();
                this.m_177946_();
                this.f_129717_ = true;
                JvmProfiler.f_185340_.m_183597_(this.f_129737_);
            }
            ServerLifecycleHooks.handleServerStopping((MinecraftServer)((MinecraftServer)this));
            ServerLifecycleHooks.expectServerStopped();
        }
        catch (Throwable throwable1) {
            f_129750_.error("Encountered an unexpected exception", throwable1);
            CrashReport crashreport = MinecraftServerMixin.m_206568_(throwable1);
            this.m_177935_(crashreport.m_178626_());
            File file1 = new File(new File(this.m_6237_(), "crash-reports"), "crash-" + Util.m_241986_() + "-server.txt");
            if (crashreport.m_127512_(file1)) {
                f_129750_.error("This crash report has been saved to: {}", (Object)file1.getAbsolutePath());
            } else {
                f_129750_.error("We were unable to save this crash report to disk.");
            }
            ServerLifecycleHooks.expectServerStopped();
            this.m_7268_(crashreport);
        }
        finally {
            try {
                this.f_129765_ = true;
                this.m_7041_();
            }
            catch (Throwable throwable) {
                f_129750_.error("Exception stopping the server", throwable);
            }
            finally {
                if (this.f_236721_.f_214336_() != null) {
                    this.f_236721_.f_214336_().m_196559_();
                }
                WatchdogThread.doStop();
                ServerLifecycleHooks.handleServerStopped((MinecraftServer)((MinecraftServer)this));
                this.m_6988_();
            }
        }
    }

    private static double calcTps(double avg, double exp, double tps) {
        return avg * exp + tps * (1.0 - exp);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Inject(method={"stopServer"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$setStopped(CallbackInfo ci) {
        Object object = this.stopLock;
        synchronized (object) {
            if (this.hasStopped) {
                ci.cancel();
                return;
            }
            this.hasStopped = true;
        }
    }

    @Inject(method={"stopServer"}, at={@At(value="INVOKE", remap=false, ordinal=0, shift=At.Shift.AFTER, target="Lorg/slf4j/Logger;info(Ljava/lang/String;)V")})
    public void arclight$unloadPlugins(CallbackInfo ci) {
        if (this.server != null) {
            this.server.disablePlugins();
        }
    }

    @Inject(method={"createLevels"}, at={@At(value="RETURN")})
    public void arclight$enablePlugins(ChunkProgressListener p_240787_1_, CallbackInfo ci) {
        BukkitRegistry.unlockRegistries();
        this.server.enablePlugins(PluginLoadOrder.POSTWORLD);
        BukkitRegistry.lockRegistries();
        this.server.getPluginManager().callEvent(new ServerLoadEvent(ServerLoadEvent.LoadType.STARTUP));
    }

    private void executeModerately() {
        this.m_18699_();
        this.bridge$drainQueuedTasks();
        LockSupport.parkNanos("executing tasks", 1000L);
    }

    @Override
    public void bridge$drainQueuedTasks() {
        int n = 64;
        long l = System.nanoTime();
        while (--n >= 0 && !this.processQueue.isEmpty()) {
            Runnable runnable = this.processQueue.poll();
            if (runnable != null) {
                try {
                    runnable.run();
                }
                catch (Throwable throwable) {}
            }
            if (System.nanoTime() - l <= 5000000L) continue;
        }
    }

    @Inject(method={"haveTime"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$forceAheadOfTime(CallbackInfoReturnable<Boolean> cir) {
        if (this.forceTicks) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"createLevels"}, at={@At(value="NEW", ordinal=0, target="net/minecraft/server/level/ServerLevel")})
    private void arclight$registerEnv(ChunkProgressListener p_240787_1_, CallbackInfo ci) {
        BukkitRegistry.registerEnvironments((Registry<LevelStem>)this.m_206579_().m_175515_(Registries.f_256862_));
    }

    @ModifyArg(method={"createLevels"}, index=1, at=@At(value="INVOKE", remap=false, target="Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object arclight$worldInitCapture(Object value) {
        this.arclight$capturedLevel = (ServerLevel)value;
        return value;
    }

    @Inject(method={"createLevels"}, at={@At(value="INVOKE", remap=false, target="Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")})
    private void arclight$worldInit(ChunkProgressListener chunkProgressListener, CallbackInfo ci) {
        ServerLevel serverWorld = this.arclight$capturedLevel;
        this.arclight$capturedLevel = null;
        if (serverWorld != null) {
            if (((CraftServer)Bukkit.getServer()).scoreboardManager == null) {
                ((CraftServer)Bukkit.getServer()).scoreboardManager = new CraftScoreboardManager((MinecraftServer)this, (Scoreboard)serverWorld.m_6188_());
            }
            if (((WorldBridge)serverWorld).bridge$getGenerator() != null) {
                ((WorldBridge)serverWorld).bridge$getWorld().getPopulators().addAll(((WorldBridge)serverWorld).bridge$getGenerator().getDefaultPopulators(((WorldBridge)serverWorld).bridge$getWorld()));
            }
            Bukkit.getPluginManager().callEvent(new WorldInitEvent(((WorldBridge)serverWorld).bridge$getWorld()));
        }
    }

    @Overwrite
    public void m_129940_(ChunkProgressListener listener) {
        ServerLevel serverworld = this.m_129783_();
        this.forceTicks = true;
        f_129750_.info("Preparing start region for dimension {}", (Object)serverworld.m_46472_().m_135782_());
        BlockPos blockpos = serverworld.m_220360_();
        listener.m_7647_(new ChunkPos(blockpos));
        ServerChunkCache serverchunkprovider = serverworld.m_7726_();
        this.f_129726_ = Util.m_137550_();
        serverchunkprovider.m_8387_(TicketType.f_9442_, new ChunkPos(blockpos), 11, (Object)Unit.INSTANCE);
        while (serverchunkprovider.m_8427_() < 441) {
            this.executeModerately();
        }
        this.executeModerately();
        for (ServerLevel serverWorld : this.f_129762_.values()) {
            ForcedChunksSavedData forcedchunkssavedata;
            if (((WorldBridge)serverWorld).bridge$getWorld().getKeepSpawnInMemory() && (forcedchunkssavedata = (ForcedChunksSavedData)serverWorld.m_8895_().m_164858_(ForcedChunksSavedData::m_151483_, "chunks")) != null) {
                LongIterator longiterator = forcedchunkssavedata.m_46116_().iterator();
                while (longiterator.hasNext()) {
                    long i = longiterator.nextLong();
                    ChunkPos chunkpos = new ChunkPos(i);
                    serverWorld.m_7726_().m_6692_(chunkpos, true);
                }
                ForgeChunkManager.reinstatePersistentChunks((ServerLevel)serverWorld, (ForcedChunksSavedData)forcedchunkssavedata);
            }
            Bukkit.getPluginManager().callEvent(new WorldLoadEvent(((WorldBridge)serverWorld).bridge$getWorld()));
        }
        this.executeModerately();
        listener.m_7646_();
        this.m_129962_();
        this.forceTicks = false;
    }

    public void initWorld(ServerLevel serverWorld, ServerLevelData worldInfo, WorldData saveData, WorldOptions worldOptions) {
        boolean flag = saveData.m_7513_();
        if (((WorldBridge)serverWorld).bridge$getGenerator() != null) {
            ((WorldBridge)serverWorld).bridge$getWorld().getPopulators().addAll(((WorldBridge)serverWorld).bridge$getGenerator().getDefaultPopulators(((WorldBridge)serverWorld).bridge$getWorld()));
        }
        WorldBorder worldborder = serverWorld.m_6857_();
        worldborder.m_61931_(worldInfo.m_5813_());
        if (!worldInfo.m_6535_()) {
            try {
                MinecraftServerMixin.m_177896_(serverWorld, worldInfo, worldOptions.m_245100_(), flag);
                worldInfo.m_5555_(true);
                if (flag) {
                    this.m_129847_(this.f_129749_);
                }
            }
            catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.m_127521_((Throwable)throwable, (String)"Exception initializing level");
                try {
                    serverWorld.m_6026_(crashreport);
                }
                catch (Throwable throwable2) {
                    // empty catch block
                }
                throw new ReportedException(crashreport);
            }
            worldInfo.m_5555_(true);
        }
    }

    public void prepareLevels(ChunkProgressListener listener, ServerLevel serverWorld) {
        this.markWorldsDirty();
        MinecraftForge.EVENT_BUS.post((Event)new LevelEvent.Load((LevelAccessor)serverWorld));
        if (!((WorldBridge)serverWorld).bridge$getWorld().getKeepSpawnInMemory()) {
            return;
        }
        this.forceTicks = true;
        f_129750_.info("Preparing start region for dimension {}", (Object)serverWorld.m_46472_().m_135782_());
        BlockPos blockpos = serverWorld.m_220360_();
        listener.m_7647_(new ChunkPos(blockpos));
        ServerChunkCache serverchunkprovider = serverWorld.m_7726_();
        this.f_129726_ = Util.m_137550_();
        serverchunkprovider.m_8387_(TicketType.f_9442_, new ChunkPos(blockpos), 11, (Object)Unit.INSTANCE);
        while (serverchunkprovider.m_8427_() < 441) {
            this.executeModerately();
        }
        this.executeModerately();
        ForcedChunksSavedData forcedchunkssavedata = (ForcedChunksSavedData)serverWorld.m_8895_().m_164858_(ForcedChunksSavedData::m_151483_, "chunks");
        if (forcedchunkssavedata != null) {
            LongIterator longiterator = forcedchunkssavedata.m_46116_().iterator();
            while (longiterator.hasNext()) {
                long i = longiterator.nextLong();
                ChunkPos chunkpos = new ChunkPos(i);
                serverWorld.m_7726_().m_6692_(chunkpos, true);
            }
            ForgeChunkManager.reinstatePersistentChunks((ServerLevel)serverWorld, (ForcedChunksSavedData)forcedchunkssavedata);
        }
        this.executeModerately();
        listener.m_7646_();
        serverWorld.m_46703_(this.m_7004_(), this.m_6998_());
        this.forceTicks = false;
    }

    public void addLevel(ServerLevel level) {
        this.f_129762_.put((ResourceKey<Level>)level.m_46472_(), level);
        this.markWorldsDirty();
    }

    public void removeLevel(ServerLevel level) {
        MinecraftForge.EVENT_BUS.post((Event)new LevelEvent.Unload((LevelAccessor)level));
        this.f_129762_.remove(level.m_46472_());
        this.markWorldsDirty();
    }

    @Inject(method={"tickChildren"}, at={@At(value="HEAD")})
    public void arclight$runScheduler(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        ArclightConstants.currentTick = (int)(System.currentTimeMillis() / 50L);
        this.server.getScheduler().mainThreadHeartbeat(this.f_129766_);
        this.bridge$drainQueuedTasks();
    }

    @Inject(method={"saveAllChunks"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;overworld()Lnet/minecraft/server/level/ServerLevel;")})
    private void arclight$skipSave(boolean suppressLog, boolean flush, boolean forced, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((Object)(!this.f_129762_.isEmpty() ? 1 : 0));
    }

    @Inject(method={"desc=/V$/"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/packs/repository/PackRepository;setSelected(Ljava/util/Collection;)V")})
    private void arclight$syncCommand(CallbackInfo ci) {
        this.server.syncCommands();
    }

    @Overwrite
    @DontObfuscate
    public String getServerModName() {
        return BrandingControl.getServerBranding() + " arclight/" + ArclightVersion.current().getReleaseName();
    }

    @Override
    public void bridge$setAutosavePeriod(int autosavePeriod) {
        this.autosavePeriod = autosavePeriod;
    }

    @Override
    public void bridge$setConsole(ConsoleCommandSender console) {
        this.console = console;
    }

    @Override
    public void bridge$setServer(CraftServer server) {
        this.server = server;
    }

    @Override
    public RemoteConsoleCommandSender bridge$getRemoteConsole() {
        return this.remoteConsole;
    }

    @Override
    public void bridge$setRemoteConsole(RemoteConsoleCommandSender sender) {
        this.remoteConsole = sender;
    }

    @Override
    public void bridge$queuedProcess(Runnable runnable) {
        this.processQueue.add(runnable);
    }

    public CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return this.console;
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.getBukkitSender(wrapper);
    }

    @Override
    public Commands bridge$getVanillaCommands() {
        return this.vanillaCommandDispatcher;
    }

    public boolean isDebugging() {
        return false;
    }

    private static MinecraftServer getServer() {
        return Bukkit.getServer() instanceof CraftServer ? ((CraftServer)Bukkit.getServer()).getServer() : null;
    }

    static {
        currentTick = (int)(System.currentTimeMillis() / 50L);
    }
}

