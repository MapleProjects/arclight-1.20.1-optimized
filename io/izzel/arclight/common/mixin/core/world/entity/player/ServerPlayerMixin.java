/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  javax.annotation.Nullable
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.GlobalPos
 *  net.minecraft.core.Position
 *  net.minecraft.core.PositionImpl
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.PacketSendListener
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.HoverEvent
 *  net.minecraft.network.chat.HoverEvent$Action
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundHorseScreenOpenPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket
 *  net.minecraft.network.protocol.game.ClientboundRespawnPacket
 *  net.minecraft.network.protocol.game.ClientboundSetHealthPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.network.protocol.game.ServerboundClientInformationPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.PlayerRespawnLogic
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.ServerPlayerGameMode
 *  net.minecraft.server.network.ServerGamePacketListenerImpl
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.stats.Stat
 *  net.minecraft.stats.Stats
 *  net.minecraft.util.Mth
 *  net.minecraft.util.Unit
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.damagesource.CombatTracker
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.RelativeMovement
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.player.Player$BedSleepingProblem
 *  net.minecraft.world.food.FoodData
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.HorseInventoryMenu
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.block.NetherPortalBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.portal.PortalInfo
 *  net.minecraft.world.level.storage.LevelData
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.scores.Score
 *  net.minecraft.world.scores.Scoreboard
 *  net.minecraft.world.scores.Team
 *  net.minecraft.world.scores.Team$Visibility
 *  net.minecraft.world.scores.criteria.ObjectiveCriteria
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ITeleporter
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.PlayerContainerEvent$Open
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.player;

import com.mojang.datafixers.util.Either;
import io.izzel.arclight.common.bridge.core.block.PortalInfoBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.ContainerBridge;
import io.izzel.arclight.common.bridge.core.network.play.ServerPlayNetHandlerBridge;
import io.izzel.arclight.common.bridge.core.util.FoodStatsBridge;
import io.izzel.arclight.common.bridge.core.world.TeleporterBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.player.PlayerMixin;
import io.izzel.arclight.common.mod.server.block.ChestBlockDoubleInventoryHacks;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.BlockUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Position;
import net.minecraft.core.PositionImpl;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundHorseScreenOpenPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ServerboundClientInformationPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.PlayerRespawnLogic;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.HorseInventoryMenu;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorldBorder;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftPortalEvent;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardManager;
import org.bukkit.craftbukkit.v1_20_R1.util.BlockStateListPopulator;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ServerPlayer.class})
public abstract class ServerPlayerMixin
extends PlayerMixin
implements ServerPlayerEntityBridge {
    @Shadow
    @Final
    public MinecraftServer f_8924_;
    @Shadow
    @Final
    public ServerPlayerGameMode f_8941_;
    @Shadow
    public ServerGamePacketListenerImpl f_8906_;
    @Shadow
    public boolean f_8927_;
    @Shadow
    public boolean f_8944_;
    @Shadow
    private boolean f_8928_;
    @Shadow
    @Nullable
    private Vec3 f_8933_;
    @Shadow
    public int f_8920_;
    @Shadow
    private float f_8917_;
    @Shadow
    private int f_8918_;
    @Shadow
    public int f_8940_;
    @Shadow(remap=false)
    private String language;
    @Shadow
    private ResourceKey<Level> f_8935_;
    @Shadow(remap=false)
    private boolean hasTabListName;
    @Shadow(remap=false)
    private Component tabListDisplayName;
    @Shadow
    @Nullable
    private BlockPos f_8936_;
    @Shadow
    private float f_8938_;
    @Shadow
    private boolean f_8937_;
    public String displayName;
    public Component listName;
    public Location compassTarget;
    public int newExp = 0;
    public int newLevel = 0;
    public int newTotalExp = 0;
    public boolean keepLevel = false;
    public double maxHealthCache;
    public boolean joining = true;
    public boolean sentListPacket = false;
    public Integer clientViewDistance;
    public long timeOffset = 0L;
    public boolean relativeTime = true;
    public WeatherType weather = null;
    private float pluginRainPosition;
    private float pluginRainPositionPrevious;
    public String locale = "en_us";
    private boolean arclight$initialized = false;
    private transient PlayerTeleportEvent.TeleportCause arclight$cause;
    private transient BlockStateListPopulator arclight$populator;
    private transient PlayerSpawnChangeEvent.Cause arclight$spawnChangeCause;

    @Shadow
    protected abstract int m_9237_(int var1);

    @Override
    @Shadow
    public abstract boolean m_5833_();

    @Shadow
    public abstract void m_7166_(Stat<?> var1);

    @Shadow
    public abstract void m_6915_();

    @Shadow
    public abstract void m_9213_(Entity var1);

    @Shadow
    public abstract ServerLevel m_284548_();

    @Shadow
    public abstract void m_9209_(ServerLevel var1);

    @Shadow
    public abstract void m_8999_(ServerLevel var1, double var2, double var4, double var6, float var8, float var9);

    @Shadow
    public abstract void m_6756_(int var1);

    @Shadow
    @Nullable
    public abstract BlockPos m_8961_();

    @Shadow
    public abstract float m_8962_();

    @Shadow
    protected abstract void m_9215_();

    @Shadow
    protected abstract void m_9006_(ServerLevel var1, BlockPos var2);

    @Shadow
    public abstract boolean m_7500_();

    @Shadow
    protected abstract boolean m_9191_(BlockPos var1, Direction var2);

    @Shadow
    protected abstract boolean m_9116_(BlockPos var1, Direction var2);

    @Shadow
    public abstract void m_183634_();

    @Shadow
    public abstract void shadow$m_9217_();

    @Shadow
    public abstract void m_143399_(AbstractContainerMenu var1);

    @Override
    @Shadow
    public abstract boolean m_264318_(ServerLevel var1, double var2, double var4, double var6, Set<RelativeMovement> var8, float var9, float var10);

    @Shadow
    public abstract void m_213846_(Component var1);

    @Shadow
    public abstract void m_284127_(ServerLevel var1);

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void arclight$init(CallbackInfo ci) {
        this.displayName = this.m_36316_() != null ? this.m_6302_() : "~FakePlayer~";
        this.bukkitPickUpLoot = true;
        this.maxHealthCache = this.m_21233_();
        this.arclight$initialized = true;
    }

    @Override
    public boolean bridge$initialized() {
        return this.arclight$initialized;
    }

    public final BlockPos getSpawnPoint(ServerLevel worldserver) {
        BlockPos blockposition = worldserver.m_220360_();
        if (worldserver.m_6042_().f_223549_() && worldserver.f_8549_.m_5464_() != GameType.ADVENTURE) {
            long k;
            long l;
            int i = Math.max(0, this.f_8924_.m_129803_(worldserver));
            int j = Mth.m_14107_((double)worldserver.m_6857_().m_61941_((double)blockposition.m_123341_(), (double)blockposition.m_123343_()));
            if (j < i) {
                i = j;
            }
            if (j <= 1) {
                i = 1;
            }
            int i1 = (l = (k = (long)(i * 2 + 1)) * k) > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)l;
            int j1 = this.m_9237_(i1);
            int k1 = new Random().nextInt(i1);
            for (int l1 = 0; l1 < i1; ++l1) {
                int i2 = (k1 + j1 * l1) % i1;
                int j2 = i2 % (i * 2 + 1);
                int k2 = i2 / (i * 2 + 1);
                BlockPos blockposition1 = PlayerRespawnLogic.m_183928_((ServerLevel)worldserver, (int)(blockposition.m_123341_() + j2 - i), (int)(blockposition.m_123343_() + k2 - i));
                if (blockposition1 == null) continue;
                return blockposition1;
            }
        }
        return blockposition;
    }

    @Override
    public BlockPos bridge$getSpawnPoint(ServerLevel world) {
        return this.getSpawnPoint(world);
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$readExtra(CompoundTag compound, CallbackInfo ci) {
        this.getBukkitEntity().readExtraData(compound);
        String spawnWorld = compound.m_128461_("SpawnWorld");
        CraftWorld oldWorld = (CraftWorld)Bukkit.getWorld(spawnWorld);
        if (oldWorld != null) {
            this.f_8935_ = oldWorld.getHandle().m_46472_();
        }
    }

    @Redirect(method={"addAdditionalSaveData"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;hasExactlyOnePlayerPassenger()Z"))
    private boolean arclight$nonPersistVehicle(Entity entity) {
        Entity entity1 = this.m_20202_();
        boolean persistVehicle = true;
        if (entity1 != null) {
            for (Entity vehicle = entity1; vehicle != null; vehicle = vehicle.m_20202_()) {
                if (((EntityBridge)vehicle).bridge$isPersist()) continue;
                persistVehicle = false;
                break;
            }
        }
        return persistVehicle && entity.m_146898_();
    }

    @Inject(method={"addAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$writeExtra(CompoundTag compound, CallbackInfo ci) {
        this.getBukkitEntity().setExtraData(compound);
    }

    public void spawnIn(Level world) {
        this.m_284535_(world);
        if (world == null) {
            this.revive();
            Vec3 position = null;
            if (this.f_8935_ != null && (world = ServerLifecycleHooks.getCurrentServer().m_129880_(this.f_8935_)) != null && this.m_8961_() != null) {
                position = Player.m_36130_((ServerLevel)((ServerLevel)world), (BlockPos)this.m_8961_(), (float)this.m_8962_(), (boolean)false, (boolean)false).orElse(null);
            }
            if (world == null || position == null) {
                world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
                position = Vec3.m_82512_((Vec3i)((ServerLevel)world).m_220360_());
            }
            this.m_284535_(world);
            this.m_6034_(position.m_7096_(), position.m_7098_(), position.m_7094_());
        }
        this.f_8941_.m_9260_((ServerLevel)world);
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void arclight$joining(CallbackInfo ci) {
        if (this.joining) {
            this.joining = false;
        }
    }

    @Redirect(method={"doTick"}, at=@At(value="NEW", target="net/minecraft/network/protocol/game/ClientboundSetHealthPacket"))
    private ClientboundSetHealthPacket arclight$useScaledHealth(float healthIn, int foodLevelIn, float saturationLevelIn) {
        return new ClientboundSetHealthPacket(this.getBukkitEntity().getScaledHealth(), foodLevelIn, saturationLevelIn);
    }

    @Inject(method={"doTick"}, at={@At(value="FIELD", target="Lnet/minecraft/server/level/ServerPlayer;tickCount:I")})
    private void arclight$updateHealthAndExp(CallbackInfo ci) {
        if (this.maxHealthCache != (double)this.m_21233_()) {
            this.getBukkitEntity().updateScaledHealth();
        }
        if (this.oldLevel == -1) {
            this.oldLevel = this.f_36078_;
        }
        if (this.oldLevel != this.f_36078_) {
            CraftEventFactory.callPlayerLevelChangeEvent(this.getBukkitEntity(), this.oldLevel, this.f_36078_);
            this.oldLevel = this.f_36078_;
        }
        if (this.getBukkitEntity().hasClientWorldBorder()) {
            ((CraftWorldBorder)this.getBukkitEntity().getWorldBorder()).getHandle().m_61969_();
        }
    }

    @Override
    @Overwrite
    public void m_6667_(DamageSource damagesource) {
        String deathMessage;
        Inventory copyInv;
        boolean keepInventory;
        this.m_146850_(GameEvent.f_223707_);
        if (ForgeHooks.onLivingDeath((LivingEntity)((ServerPlayer)this), (DamageSource)damagesource)) {
            return;
        }
        boolean flag = this.m_9236_().m_46469_().m_46207_(GameRules.f_46142_);
        if (this.m_213877_()) {
            return;
        }
        boolean bl = keepInventory = this.m_9236_().m_46469_().m_46207_(GameRules.f_46133_) || this.m_5833_();
        if (keepInventory) {
            copyInv = this.m_150109_();
        } else {
            copyInv = new Inventory((Player)((ServerPlayer)this));
            copyInv.m_36006_(this.m_150109_());
        }
        this.m_6668_(damagesource);
        Component defaultMessage = this.m_21231_().m_19293_();
        String deathmessage = defaultMessage.getString();
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        Collection<ItemEntity> drops = this.captureDrops(null);
        if (drops != null) {
            for (ItemEntity entity : drops) {
                CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(entity.m_32055_());
                loot.add(craftItemStack);
            }
        }
        this.keepLevel = keepInventory;
        if (!keepInventory) {
            this.m_150109_().m_36006_(copyInv);
        }
        PlayerDeathEvent event = CraftEventFactory.callPlayerDeathEvent((ServerPlayer)this, loot, deathmessage, keepInventory);
        if (this.f_36096_ != this.f_36095_) {
            this.m_6915_();
        }
        if ((deathMessage = event.getDeathMessage()) != null && deathMessage.length() > 0 && flag) {
            Component itextcomponent = deathMessage.equals(deathmessage) ? this.m_21231_().m_19293_() : CraftChatMessage.fromStringOrNull(deathMessage);
            this.f_8906_.m_243119_((Packet)new ClientboundPlayerCombatKillPacket(this.m_19879_(), itextcomponent), PacketSendListener.m_243073_(() -> {
                String s = itextcomponent.m_130668_(256);
                MutableComponent component1 = Component.m_237110_((String)"death.attack.message_too_long", (Object[])new Object[]{Component.m_237113_((String)s).m_130940_(ChatFormatting.YELLOW)});
                MutableComponent component2 = Component.m_237110_((String)"death.attack.even_more_magic", (Object[])new Object[]{this.m_5446_()}).m_130938_(arg_0 -> ServerPlayerMixin.lambda$die$0((Component)component1, arg_0));
                return new ClientboundPlayerCombatKillPacket(this.m_19879_(), (Component)component2);
            }));
            Team scoreboardteambase = this.m_5647_();
            if (scoreboardteambase != null && scoreboardteambase.m_7468_() != Team.Visibility.ALWAYS) {
                if (scoreboardteambase.m_7468_() == Team.Visibility.HIDE_FOR_OTHER_TEAMS) {
                    this.f_8924_.m_6846_().m_215621_((Player)((ServerPlayer)this), itextcomponent);
                } else if (scoreboardteambase.m_7468_() == Team.Visibility.HIDE_FOR_OWN_TEAM) {
                    this.f_8924_.m_6846_().m_215649_((Player)((ServerPlayer)this), itextcomponent);
                }
            } else {
                this.f_8924_.m_6846_().m_240416_(itextcomponent, false);
            }
        } else {
            this.f_8906_.m_9829_((Packet)new ClientboundPlayerCombatKillPacket(this.m_19879_(), CommonComponents.f_237098_));
        }
        this.m_36328_();
        if (this.m_9236_().m_46469_().m_46207_(GameRules.f_46126_)) {
            this.m_9215_();
        }
        this.m_21226_();
        if (!event.getKeepInventory()) {
            this.m_150109_().m_6211_();
        }
        this.m_9213_((Entity)((ServerPlayer)this));
        ((CraftScoreboardManager)Bukkit.getScoreboardManager()).getScoreboardScores(ObjectiveCriteria.f_83590_, this.m_6302_(), Score::m_83392_);
        LivingEntity entityliving = this.m_21232_();
        if (entityliving != null) {
            this.m_36246_(Stats.f_12987_.m_12902_((Object)entityliving.m_6095_()));
            entityliving.m_5993_((Entity)((ServerPlayer)this), this.f_20897_, damagesource);
            this.m_21268_(entityliving);
        }
        this.m_9236_().m_7605_((Entity)((ServerPlayer)this), (byte)3);
        this.m_36220_(Stats.f_12935_);
        this.m_7166_(Stats.f_12988_.m_12902_((Object)Stats.f_12991_));
        this.m_7166_(Stats.f_12988_.m_12902_((Object)Stats.f_12992_));
        this.m_20095_();
        this.m_146917_(0);
        this.m_146868_(false);
        this.m_21231_().m_19296_();
        this.m_219749_(Optional.of(GlobalPos.m_122643_((ResourceKey)this.m_9236_().m_46472_(), (BlockPos)this.m_20183_())));
    }

    @Redirect(method={"awardKillScore"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/scores/Scoreboard;forAllObjectives(Lnet/minecraft/world/scores/criteria/ObjectiveCriteria;Ljava/lang/String;Ljava/util/function/Consumer;)V"))
    private void arclight$useCustomScoreboard(Scoreboard scoreboard, ObjectiveCriteria p_197893_1_, String p_197893_2_, Consumer<Score> p_197893_3_) {
        ((CraftServer)Bukkit.getServer()).getScoreboardManager().getScoreboardScores(p_197893_1_, p_197893_2_, p_197893_3_);
    }

    @Redirect(method={"handleTeamKill"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/scores/Scoreboard;forAllObjectives(Lnet/minecraft/world/scores/criteria/ObjectiveCriteria;Ljava/lang/String;Ljava/util/function/Consumer;)V"))
    private void arclight$teamKill(Scoreboard scoreboard, ObjectiveCriteria p_197893_1_, String p_197893_2_, Consumer<Score> p_197893_3_) {
        ((CraftServer)Bukkit.getServer()).getScoreboardManager().getScoreboardScores(p_197893_1_, p_197893_2_, p_197893_3_);
    }

    @Inject(method={"isPvpAllowed"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$pvpMode(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((Object)((WorldBridge)this.m_9236_()).bridge$isPvpMode());
    }

    @Override
    @Nullable
    @Overwrite
    protected PortalInfo m_7937_(ServerLevel level) {
        PortalInfo portalinfo = super.m_7937_(level);
        ServerLevel serverLevel = level = portalinfo == null || ((PortalInfoBridge)portalinfo).bridge$getWorld() == null ? level : ((PortalInfoBridge)portalinfo).bridge$getWorld();
        if (portalinfo != null && ((WorldBridge)this.m_9236_()).bridge$getTypeKey() == LevelStem.f_63971_ && ((WorldBridge)level).bridge$getTypeKey() == LevelStem.f_63973_) {
            Vec3 vector3d = portalinfo.f_77676_.m_82520_(0.0, -1.0, 0.0);
            PortalInfo newInfo = new PortalInfo(vector3d, Vec3.f_82478_, 90.0f, 0.0f);
            ((PortalInfoBridge)newInfo).bridge$setWorld(level);
            ((PortalInfoBridge)newInfo).bridge$setPortalEventInfo(((PortalInfoBridge)portalinfo).bridge$getPortalEventInfo());
            return newInfo;
        }
        return portalinfo;
    }

    @Override
    public Entity bridge$changeDimension(ServerLevel world, PlayerTeleportEvent.TeleportCause cause) {
        this.arclight$cause = cause;
        return this.m_5489_(world);
    }

    @Override
    public boolean teleportTo(ServerLevel worldserver, double d0, double d1, double d2, Set<RelativeMovement> set, float f, float f1, PlayerTeleportEvent.TeleportCause cause) {
        this.arclight$cause = cause;
        return this.m_264318_(worldserver, d0, d1, d2, set, f, f1);
    }

    @Inject(method={"teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/network/ServerGamePacketListenerImpl;teleport(DDDFFLjava/util/Set;)V")})
    private void arclight$forwardReason(ServerLevel p_265564_, double p_265424_, double p_265680_, double p_265312_, Set<RelativeMovement> p_265192_, float p_265059_, float p_265266_, CallbackInfoReturnable<Boolean> cir) {
        PlayerTeleportEvent.TeleportCause teleportCause = this.arclight$cause;
        this.arclight$cause = null;
        ((ServerPlayNetHandlerBridge)this.f_8906_).bridge$pushTeleportCause(teleportCause);
    }

    @Override
    @Overwrite(remap=false)
    @Nullable
    public Entity changeDimension(ServerLevel server, ITeleporter teleporter) {
        if (this.m_5803_()) {
            return (ServerPlayer)this;
        }
        if (!ForgeHooks.onTravelToDimension((Entity)((ServerPlayer)this), (ResourceKey)server.m_46472_())) {
            return null;
        }
        PlayerTeleportEvent.TeleportCause cause = this.arclight$cause == null ? PlayerTeleportEvent.TeleportCause.UNKNOWN : this.arclight$cause;
        this.arclight$cause = null;
        ServerLevel serverworld = this.m_284548_();
        ResourceKey<LevelStem> registrykey = ((WorldBridge)serverworld).bridge$getTypeKey();
        if (registrykey == LevelStem.f_63973_ && ((WorldBridge)server).bridge$getTypeKey() == LevelStem.f_63971_ && teleporter.isVanilla()) {
            this.f_8927_ = true;
            this.m_19877_();
            this.m_284548_().m_143261_((ServerPlayer)this, Entity.RemovalReason.CHANGED_DIMENSION);
            if (!this.f_8944_) {
                this.f_8944_ = true;
                this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132157_, this.f_8928_ ? 0.0f : 1.0f));
                this.f_8928_ = true;
            }
            return (ServerPlayer)this;
        }
        PortalInfo portalinfo = teleporter.getPortalInfo((Entity)((ServerPlayer)this), server, this::m_7937_);
        if (portalinfo != null) {
            if (((PortalInfoBridge)portalinfo).bridge$getWorld() != null) {
                server = ((PortalInfoBridge)portalinfo).bridge$getWorld();
            }
            ServerLevel[] exitWorld = new ServerLevel[]{server};
            LevelData iworldinfo = server.m_6106_();
            this.f_8906_.m_9829_((Packet)new ClientboundRespawnPacket(server.m_220362_(), server.m_46472_(), BiomeManager.m_47877_((long)server.m_7328_()), this.f_8941_.m_9290_(), this.f_8941_.m_9293_(), server.m_46659_(), server.m_8584_(), 3, this.m_219759_(), this.m_287157_()));
            this.f_8906_.m_9829_((Packet)new ClientboundChangeDifficultyPacket(iworldinfo.m_5472_(), iworldinfo.m_5474_()));
            PlayerList playerlist = this.f_8924_.m_6846_();
            playerlist.m_11289_((ServerPlayer)this);
            this.m_284548_().m_143261_((ServerPlayer)this, Entity.RemovalReason.CHANGED_DIMENSION);
            this.revive();
            Entity e = teleporter.placeEntity((Entity)((ServerPlayer)this), serverworld, exitWorld[0], this.m_146908_(), spawnPortal -> {
                serverworld.m_46473_().m_6180_("moving");
                if (exitWorld[0] != null) {
                    if (registrykey == LevelStem.f_63971_ && ((WorldBridge)exitWorld[0]).bridge$getTypeKey() == LevelStem.f_63972_) {
                        this.f_8933_ = this.m_20182_();
                    } else if (spawnPortal.booleanValue() && ((WorldBridge)exitWorld[0]).bridge$getTypeKey() == LevelStem.f_63973_ && (((PortalInfoBridge)portalinfo).bridge$getPortalEventInfo() == null || ((PortalInfoBridge)portalinfo).bridge$getPortalEventInfo().getCanCreatePortal())) {
                        this.m_9006_(exitWorld[0], BlockPos.m_274446_((Position)portalinfo.f_77676_));
                    }
                }
                Location enter = this.getBukkitEntity().getLocation();
                Location exit = exitWorld[0] == null ? null : new Location(((WorldBridge)exitWorld[0]).bridge$getWorld(), portalinfo.f_77676_.f_82479_, portalinfo.f_77676_.f_82480_, portalinfo.f_77676_.f_82481_, portalinfo.f_77678_, portalinfo.f_77679_);
                PlayerTeleportEvent tpEvent = new PlayerTeleportEvent(this.getBukkitEntity(), enter, exit, cause);
                Bukkit.getServer().getPluginManager().callEvent(tpEvent);
                if (tpEvent.isCancelled() || tpEvent.getTo() == null) {
                    return null;
                }
                exit = tpEvent.getTo();
                serverworld.m_46473_().m_7238_();
                serverworld.m_46473_().m_6180_("placing");
                this.f_8927_ = true;
                ServerLevel newWorld = ((CraftWorld)exit.getWorld()).getHandle();
                if (newWorld != exitWorld[0]) {
                    exitWorld[0] = newWorld;
                    LevelData newWorldInfo = exitWorld[0].m_6106_();
                    this.f_8906_.m_9829_((Packet)new ClientboundRespawnPacket(exitWorld[0].m_220362_(), exitWorld[0].m_46472_(), BiomeManager.m_47877_((long)exitWorld[0].m_7328_()), this.f_8941_.m_9290_(), this.f_8941_.m_9293_(), exitWorld[0].m_46659_(), exitWorld[0].m_8584_(), 3, this.m_219759_(), this.m_287157_()));
                    this.f_8906_.m_9829_((Packet)new ClientboundChangeDifficultyPacket(newWorldInfo.m_5472_(), newWorldInfo.m_5474_()));
                }
                this.m_284127_(exitWorld[0]);
                exitWorld[0].m_8817_((ServerPlayer)this);
                ((ServerPlayNetHandlerBridge)this.f_8906_).bridge$teleport(exit);
                this.f_8906_.m_9953_();
                serverworld.m_46473_().m_7238_();
                this.m_9209_(exitWorld[0]);
                return (ServerPlayer)this;
            });
            if (e == null) {
                serverworld.m_8817_((ServerPlayer)this);
                return (ServerPlayer)this;
            }
            if (e != this) {
                throw new IllegalArgumentException(String.format("Teleporter %s returned not the player entity but instead %s, expected PlayerEntity %s", teleporter, e, this));
            }
            this.f_8941_.m_9260_(exitWorld[0]);
            this.f_8906_.m_9829_((Packet)new ClientboundPlayerAbilitiesPacket(this.m_150110_()));
            playerlist.m_11229_((ServerPlayer)this, exitWorld[0]);
            playerlist.m_11292_((ServerPlayer)this);
            for (MobEffectInstance effectinstance : this.m_21220_()) {
                this.f_8906_.m_9829_((Packet)new ClientboundUpdateMobEffectPacket(this.m_19879_(), effectinstance));
            }
            if (teleporter.playTeleportSound((ServerPlayer)this, serverworld, exitWorld[0])) {
                this.f_8906_.m_9829_((Packet)new ClientboundLevelEventPacket(1032, BlockPos.f_121853_, 0, false));
            }
            this.f_8920_ = -1;
            this.f_8917_ = -1.0f;
            this.f_8918_ = -1;
            ForgeEventFactory.firePlayerChangedDimensionEvent((Player)((ServerPlayer)this), (ResourceKey)serverworld.m_46472_(), (ResourceKey)exitWorld[0].m_46472_());
            PlayerChangedWorldEvent changeEvent = new PlayerChangedWorldEvent((org.bukkit.entity.Player)this.getBukkitEntity(), ((WorldBridge)serverworld).bridge$getWorld());
            Bukkit.getPluginManager().callEvent(changeEvent);
        }
        return (ServerPlayer)this;
    }

    @Override
    protected CraftPortalEvent callPortalEvent(Entity entity, ServerLevel exitWorldServer, PositionImpl exitPosition, PlayerTeleportEvent.TeleportCause cause, int searchRadius, int creationRadius) {
        Location enter = this.getBukkitEntity().getLocation();
        Location exit = new Location(((WorldBridge)exitWorldServer).bridge$getWorld(), exitPosition.m_7096_(), exitPosition.m_7098_(), exitPosition.m_7094_(), this.m_146908_(), this.m_146909_());
        PlayerPortalEvent event = new PlayerPortalEvent(this.getBukkitEntity(), enter, exit, cause, 128, true, creationRadius);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled() || event.getTo() == null || event.getTo().getWorld() == null) {
            return null;
        }
        return new CraftPortalEvent(event);
    }

    @Override
    protected Optional<BlockUtil.FoundRectangle> getExitPortal(ServerLevel worldserver, BlockPos blockposition, boolean flag, WorldBorder worldborder, int searchRadius, boolean canCreatePortal, int createRadius) {
        Optional<BlockUtil.FoundRectangle> optional = super.getExitPortal(worldserver, blockposition, flag, worldborder, searchRadius, canCreatePortal, createRadius);
        if (optional.isPresent() || !canCreatePortal) {
            return optional;
        }
        Direction.Axis enumdirection_enumaxis = this.m_9236_().m_8055_(this.f_19819_).m_61145_((Property)NetherPortalBlock.f_54904_).orElse(Direction.Axis.X);
        Optional<BlockUtil.FoundRectangle> optional1 = ((TeleporterBridge)worldserver.m_8871_()).bridge$createPortal(blockposition, enumdirection_enumaxis, (Entity)((ServerPlayer)this), createRadius);
        if (!optional1.isPresent()) {
            // empty if block
        }
        return optional1;
    }

    @Inject(method={"createEndPlatform"}, at={@At(value="HEAD")})
    private void arclight$playerCreatePortalBegin(ServerLevel level, BlockPos pos, CallbackInfo ci) {
        this.arclight$populator = new BlockStateListPopulator((LevelAccessor)level);
    }

    @Redirect(method={"createEndPlatform"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean arclight$playerCreatePortal(ServerLevel instance, BlockPos pos, BlockState blockState) {
        return this.arclight$populator.m_7731_(pos, blockState, 3);
    }

    @Inject(method={"createEndPlatform"}, at={@At(value="RETURN")})
    private void arclight$playerCreatePortalEnd(ServerLevel level, BlockPos pos, CallbackInfo ci) {
        BlockStateListPopulator blockList = this.arclight$populator;
        this.arclight$populator = null;
        PortalCreateEvent portalEvent = new PortalCreateEvent(blockList.getList(), ((WorldBridge)level).bridge$getWorld(), this.getBukkitEntity(), PortalCreateEvent.CreateReason.END_PLATFORM);
        Bukkit.getPluginManager().callEvent(portalEvent);
        if (!portalEvent.isCancelled()) {
            blockList.updateList();
        }
    }

    private Either<Player.BedSleepingProblem, Unit> getBedResult(BlockPos blockposition, Direction enumdirection) {
        if (!this.m_5803_() && this.m_6084_()) {
            if (!this.m_9236_().m_6042_().f_63858_() || !this.m_9236_().m_6042_().f_63862_()) {
                return Either.left((Object)Player.BedSleepingProblem.NOT_POSSIBLE_HERE);
            }
            if (!this.m_9116_(blockposition, enumdirection)) {
                return Either.left((Object)Player.BedSleepingProblem.TOO_FAR_AWAY);
            }
            if (this.m_9191_(blockposition, enumdirection)) {
                return Either.left((Object)Player.BedSleepingProblem.OBSTRUCTED);
            }
            this.setRespawnPosition((ResourceKey<Level>)this.m_9236_().m_46472_(), blockposition, this.m_146908_(), false, true, PlayerSpawnChangeEvent.Cause.BED);
            if (this.m_9236_().m_46461_()) {
                return Either.left((Object)Player.BedSleepingProblem.NOT_POSSIBLE_NOW);
            }
            if (!this.m_7500_()) {
                double d0 = 8.0;
                double d1 = 5.0;
                Vec3 vec3d = Vec3.m_82539_((Vec3i)blockposition);
                List list = this.m_9236_().m_6443_(Monster.class, new AABB(vec3d.m_7096_() - 8.0, vec3d.m_7098_() - 5.0, vec3d.m_7094_() - 8.0, vec3d.m_7096_() + 8.0, vec3d.m_7098_() + 5.0, vec3d.m_7094_() + 8.0), entitymonster -> entitymonster.m_6935_((Player)((ServerPlayer)this)));
                if (!list.isEmpty()) {
                    return Either.left((Object)Player.BedSleepingProblem.NOT_SAFE);
                }
            }
            return Either.right((Object)Unit.INSTANCE);
        }
        return Either.left((Object)Player.BedSleepingProblem.OTHER_PROBLEM);
    }

    @Redirect(method={"startSleepInBed"}, at=@At(value="INVOKE", remap=false, target="Lcom/mojang/datafixers/util/Either;left(Ljava/lang/Object;)Lcom/mojang/datafixers/util/Either;"))
    private <L, R> Either<L, R> arclight$failSleep(L value, BlockPos pos) {
        Either either = Either.left(value);
        return this.arclight$fireBedEvent(either, pos);
    }

    @Redirect(method={"startSleepInBed"}, at=@At(value="INVOKE", remap=false, target="Lcom/mojang/datafixers/util/Either;ifRight(Ljava/util/function/Consumer;)Lcom/mojang/datafixers/util/Either;"))
    private <L, R> Either<L, R> arclight$successSleep(Either<L, R> either, Consumer<? super R> consumer, BlockPos pos) {
        return this.arclight$fireBedEvent(either, pos).ifRight(consumer);
    }

    @Inject(method={"startSleepInBed"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;setRespawnPosition(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/BlockPos;FZZ)V")})
    private void arclight$bedCause(BlockPos p_9115_, CallbackInfoReturnable<Either<Player.BedSleepingProblem, Unit>> cir) {
        this.bridge$pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause.BED);
    }

    private <L, R> Either<L, R> arclight$fireBedEvent(Either<L, R> e, BlockPos pos) {
        Either either = e;
        if (either.left().orElse(null) == Player.BedSleepingProblem.OTHER_PROBLEM) {
            return either;
        }
        if (this.arclight$forceSleep) {
            either = Either.right((Object)Unit.INSTANCE);
        }
        return CraftEventFactory.callPlayerBedEnterEvent((Player)((ServerPlayer)this), pos, (Either<Player.BedSleepingProblem, Unit>)either);
    }

    @Inject(method={"stopSleepInBed"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$wakeupOutBed(boolean flag, boolean flag1, CallbackInfo ci) {
        if (!this.m_5803_()) {
            ci.cancel();
            return;
        }
        CraftPlayer player = this.getBukkitEntity();
        BlockPos bedPosition = this.m_21257_().orElse(null);
        Block bed = bedPosition != null ? CraftBlock.at((LevelAccessor)this.m_9236_(), bedPosition) : player.getLocation().getBlock();
        PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed, true);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        } else if (this.f_8906_ != null) {
            ((ServerPlayNetHandlerBridge)this.f_8906_).bridge$pushTeleportCause(PlayerTeleportEvent.TeleportCause.EXIT_BED);
        }
    }

    public int nextContainerCounter() {
        this.shadow$m_9217_();
        return this.f_8940_;
    }

    @Redirect(method={"openMenu"}, require=0, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;closeContainer()V"))
    private void arclight$skipSwitch(ServerPlayer serverPlayer) {
    }

    @Inject(method={"openMenu"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE_ASSIGN", target="Lnet/minecraft/world/MenuProvider;createMenu(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/inventory/AbstractContainerMenu;")})
    private void arclight$invOpen(MenuProvider itileinventory, CallbackInfoReturnable<OptionalInt> cir, AbstractContainerMenu container) {
        if (container != null) {
            ((ContainerBridge)container).bridge$setTitle(itileinventory.m_5446_());
            boolean cancelled = false;
            ArclightCaptures.captureContainerOwner((Player)((ServerPlayer)this));
            container = CraftEventFactory.callInventoryOpenEvent((ServerPlayer)this, container, cancelled);
            ArclightCaptures.resetContainerOwner();
            if (container == null && !cancelled) {
                if (itileinventory instanceof Container) {
                    ((Container)itileinventory).m_5785_((Player)((ServerPlayer)this));
                } else if (ChestBlockDoubleInventoryHacks.isInstance(itileinventory)) {
                    ChestBlockDoubleInventoryHacks.get(itileinventory).m_5785_((Player)((ServerPlayer)this));
                }
                cir.setReturnValue((Object)OptionalInt.empty());
            }
        }
    }

    @Overwrite
    public void m_6658_(AbstractHorse entityhorseabstract, Container iinventory) {
        this.nextContainerCounter();
        HorseInventoryMenu container = new HorseInventoryMenu(this.f_8940_, this.m_150109_(), iinventory, entityhorseabstract);
        ((ContainerBridge)container).bridge$setTitle(entityhorseabstract.m_5446_());
        container = CraftEventFactory.callInventoryOpenEvent((ServerPlayer)this, (AbstractContainerMenu)container);
        if (container == null) {
            iinventory.m_5785_((Player)((ServerPlayer)this));
            return;
        }
        if (this.f_36096_ != this.f_36095_) {
            this.m_6915_();
        }
        this.f_8906_.m_9829_((Packet)new ClientboundHorseScreenOpenPacket(this.f_8940_, iinventory.m_6643_(), entityhorseabstract.m_19879_()));
        this.f_36096_ = container;
        this.m_143399_(this.f_36096_);
        MinecraftForge.EVENT_BUS.post((Event)new PlayerContainerEvent.Open((Player)((ServerPlayer)this), this.f_36096_));
    }

    @Inject(method={"doCloseContainer"}, at={@At(value="HEAD")})
    private void arclight$invClose(CallbackInfo ci) {
        if (this.f_36096_ != this.f_36095_) {
            Player old = ArclightCaptures.getContainerOwner();
            ArclightCaptures.captureContainerOwner((Player)((ServerPlayer)this));
            CraftEventFactory.handleInventoryCloseEvent((Player)((ServerPlayer)this));
            ArclightCaptures.captureContainerOwner(old);
        }
    }

    @Inject(method={"setPlayerInput"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;setShiftKeyDown(Z)V")})
    private void arclight$toggleSneak(float p_8981_, float p_8982_, boolean p_8983_, boolean shift, CallbackInfo ci) {
        if (shift != this.m_6144_()) {
            PlayerToggleSneakEvent event = new PlayerToggleSneakEvent(this.getBukkitEntity(), shift);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Redirect(method={"awardStat"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/scores/Scoreboard;forAllObjectives(Lnet/minecraft/world/scores/criteria/ObjectiveCriteria;Ljava/lang/String;Ljava/util/function/Consumer;)V"))
    private void arclight$addStats(Scoreboard scoreboard, ObjectiveCriteria p_197893_1_, String p_197893_2_, Consumer<Score> p_197893_3_) {
        ((CraftScoreboardManager)Bukkit.getScoreboardManager()).getScoreboardScores(p_197893_1_, p_197893_2_, p_197893_3_);
    }

    @Redirect(method={"resetStat"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/scores/Scoreboard;forAllObjectives(Lnet/minecraft/world/scores/criteria/ObjectiveCriteria;Ljava/lang/String;Ljava/util/function/Consumer;)V"))
    private void arclight$takeStats(Scoreboard scoreboard, ObjectiveCriteria p_197893_1_, String p_197893_2_, Consumer<Score> p_197893_3_) {
        ((CraftScoreboardManager)Bukkit.getScoreboardManager()).getScoreboardScores(p_197893_1_, p_197893_2_, p_197893_3_);
    }

    @Inject(method={"resetSentInfo"}, at={@At(value="HEAD")})
    private void arclight$setExpUpdate(CallbackInfo ci) {
        this.f_8920_ = -1;
    }

    @Inject(method={"updateOptions"}, at={@At(value="HEAD")})
    private void arclight$settingChange(ServerboundClientInformationPacket packetIn, CallbackInfo ci) {
        if (this.m_5737_() != packetIn.f_133868_()) {
            PlayerChangedMainHandEvent event = new PlayerChangedMainHandEvent((org.bukkit.entity.Player)this.getBukkitEntity(), this.m_5737_() == HumanoidArm.LEFT ? MainHand.LEFT : MainHand.RIGHT);
            Bukkit.getPluginManager().callEvent(event);
        }
        if (!this.language.equals(packetIn.f_133863_())) {
            PlayerLocaleChangeEvent event2 = new PlayerLocaleChangeEvent((org.bukkit.entity.Player)this.getBukkitEntity(), packetIn.f_133863_());
            Bukkit.getPluginManager().callEvent(event2);
        }
        this.locale = packetIn.f_133863_();
        this.clientViewDistance = packetIn.f_133864_();
    }

    @Inject(method={"setCamera"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z")})
    private void arclight$spectatorReason(Entity entityToSpectate, CallbackInfo ci) {
        this.bridge$pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause.SPECTATE);
    }

    @Overwrite
    @Nullable
    public Component m_8957_() {
        if (this.listName != null) {
            return this.listName;
        }
        if (!this.hasTabListName) {
            this.tabListDisplayName = ForgeEventFactory.getPlayerTabListDisplayName((Player)((ServerPlayer)this));
            this.hasTabListName = true;
        }
        return this.tabListDisplayName;
    }

    @Inject(method={"teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDFF)V"}, cancellable=true, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/server/level/ServerPlayer;stopRiding()V")})
    private void arclight$handleBy(ServerLevel world, double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        PlayerTeleportEvent.TeleportCause cause = this.arclight$cause == null ? PlayerTeleportEvent.TeleportCause.UNKNOWN : this.arclight$cause;
        this.arclight$cause = null;
        this.getBukkitEntity().teleport(new Location(((WorldBridge)world).bridge$getWorld(), x, y, z, yaw, pitch), cause);
        ci.cancel();
    }

    public void teleportTo(ServerLevel worldserver, double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) {
        this.bridge$pushChangeDimensionCause(cause);
        this.m_8999_(worldserver, d0, d1, d2, f, f1);
    }

    @Override
    public CraftPlayer getBukkitEntity() {
        return (CraftPlayer)this.internal$getBukkitEntity();
    }

    @Override
    public CraftPlayer bridge$getBukkitEntity() {
        return (CraftPlayer)this.internal$getBukkitEntity();
    }

    @Override
    public void bridge$pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause cause) {
        this.arclight$cause = cause;
    }

    @Override
    public Optional<PlayerTeleportEvent.TeleportCause> bridge$getTeleportCause() {
        try {
            Optional<PlayerTeleportEvent.TeleportCause> optional = Optional.ofNullable(this.arclight$cause);
            return optional;
        }
        finally {
            this.arclight$cause = null;
        }
    }

    public long getPlayerTime() {
        if (this.relativeTime) {
            return this.m_9236_().m_46468_() + this.timeOffset;
        }
        return this.m_9236_().m_46468_() - this.m_9236_().m_46468_() % 24000L + this.timeOffset;
    }

    public WeatherType getPlayerWeather() {
        return this.weather;
    }

    public void setPlayerWeather(WeatherType type, boolean plugin) {
        if (!plugin && this.weather != null) {
            return;
        }
        if (plugin) {
            this.weather = type;
        }
        if (type == WeatherType.DOWNFALL) {
            this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132155_, 0.0f));
        } else {
            this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132154_, 0.0f));
        }
    }

    public void updateWeather(float oldRain, float newRain, float oldThunder, float newThunder) {
        if (this.weather == null) {
            if (oldRain != newRain) {
                this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132160_, newRain));
            }
        } else if (this.pluginRainPositionPrevious != this.pluginRainPosition) {
            this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132160_, this.pluginRainPosition));
        }
        if (oldThunder != newThunder) {
            if (this.weather == WeatherType.DOWNFALL || this.weather == null) {
                this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132161_, newThunder));
            } else {
                this.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132161_, 0.0f));
            }
        }
    }

    public void tickWeather() {
        if (this.weather == null) {
            return;
        }
        this.pluginRainPositionPrevious = this.pluginRainPosition;
        this.pluginRainPosition = this.weather == WeatherType.DOWNFALL ? (this.pluginRainPosition += 0.01f) : (this.pluginRainPosition -= 0.01f);
        this.pluginRainPosition = Mth.m_14036_((float)this.pluginRainPosition, (float)0.0f, (float)1.0f);
    }

    public void resetPlayerWeather() {
        this.weather = null;
        this.setPlayerWeather(this.m_9236_().m_6106_().m_6533_() ? WeatherType.DOWNFALL : WeatherType.CLEAR, false);
    }

    public String toString() {
        return super.toString() + "(" + this.m_6302_() + " at " + this.m_20185_() + "," + this.m_20186_() + "," + this.m_20189_() + ")";
    }

    public void forceSetPositionRotation(double x, double y, double z, float yaw, float pitch) {
        this.m_7678_(x, y, z, yaw, pitch);
        this.f_8906_.m_9953_();
    }

    @Override
    public boolean m_6107_() {
        return super.m_6107_() || !this.getBukkitEntity().isOnline();
    }

    @Override
    public void bridge$pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause cause) {
        this.arclight$spawnChangeCause = cause;
    }

    public void setRespawnPosition(ResourceKey<Level> p_9159_, @Nullable BlockPos p_9160_, float p_9161_, boolean p_9162_, boolean p_9163_, PlayerSpawnChangeEvent.Cause cause) {
        this.arclight$spawnChangeCause = cause;
        this.m_9158_(p_9159_, p_9160_, p_9161_, p_9162_, p_9163_);
    }

    @Overwrite
    public void m_9158_(ResourceKey<Level> p_9159_, @Nullable BlockPos p_9160_, float p_9161_, boolean p_9162_, boolean p_9163_) {
        if (ForgeEventFactory.onPlayerSpawnSet((Player)((ServerPlayer)this), p_9160_ == null ? Level.f_46428_ : p_9159_, (BlockPos)p_9160_, (boolean)p_9162_)) {
            return;
        }
        PlayerSpawnChangeEvent.Cause cause = this.arclight$spawnChangeCause == null ? PlayerSpawnChangeEvent.Cause.UNKNOWN : this.arclight$spawnChangeCause;
        this.arclight$spawnChangeCause = null;
        ServerLevel newWorld = this.f_8924_.m_129880_(p_9159_);
        Location newSpawn = p_9160_ != null ? new Location(((ServerWorldBridge)newWorld).bridge$getWorld(), p_9160_.m_123341_(), p_9160_.m_123342_(), p_9160_.m_123343_(), p_9161_, 0.0f) : null;
        PlayerSpawnChangeEvent event = new PlayerSpawnChangeEvent(this.getBukkitEntity(), newSpawn, p_9162_, cause);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        newSpawn = event.getNewSpawn();
        p_9162_ = event.isForced();
        if (newSpawn != null) {
            p_9159_ = ((CraftWorld)newSpawn.getWorld()).getHandle().m_46472_();
            p_9160_ = BlockPos.m_274561_((double)newSpawn.getX(), (double)newSpawn.getY(), (double)newSpawn.getZ());
            p_9161_ = newSpawn.getYaw();
        } else {
            p_9159_ = Level.f_46428_;
            p_9160_ = null;
            p_9161_ = 0.0f;
        }
        if (p_9160_ != null) {
            boolean flag;
            boolean bl = flag = p_9160_.equals((Object)this.f_8936_) && p_9159_.equals(this.f_8935_);
            if (p_9163_ && !flag) {
                this.m_213846_((Component)Component.m_237115_((String)"block.minecraft.set_spawn"));
            }
            this.f_8936_ = p_9160_;
            this.f_8935_ = p_9159_;
            this.f_8938_ = p_9161_;
            this.f_8937_ = p_9162_;
        } else {
            this.f_8936_ = null;
            this.f_8935_ = Level.f_46428_;
            this.f_8938_ = 0.0f;
            this.f_8937_ = false;
        }
    }

    @Override
    public Scoreboard m_36329_() {
        return this.getBukkitEntity().getScoreboard().getHandle();
    }

    public void reset() {
        float exp = 0.0f;
        if (this.keepLevel) {
            exp = this.f_36080_;
            this.newTotalExp = this.f_36079_;
            this.newLevel = this.f_36078_;
        }
        this.m_21153_(this.m_21233_());
        this.m_5810_();
        this.m_7311_(0);
        this.m_183634_();
        this.f_36097_ = new FoodData();
        ((FoodStatsBridge)this.f_36097_).bridge$setEntityHuman((Player)((ServerPlayer)this));
        this.f_36078_ = this.newLevel;
        this.f_36079_ = this.newTotalExp;
        this.f_36080_ = 0.0f;
        this.f_20919_ = 0;
        this.setArrowCount(0, true);
        this.removeAllEffects(EntityPotionEffectEvent.Cause.DEATH);
        this.f_20948_ = true;
        this.f_36096_ = this.f_36095_;
        this.f_20888_ = null;
        this.f_20949_ = null;
        this.f_20944_ = new CombatTracker((LivingEntity)((ServerPlayer)this));
        this.f_8920_ = -1;
        if (this.keepLevel) {
            this.f_36080_ = exp;
        } else {
            this.m_6756_(this.newExp);
        }
        this.keepLevel = false;
        this.m_20334_(0.0, 0.0, 0.0);
    }

    @Override
    public boolean bridge$isMovementBlocked() {
        return this.m_6107_();
    }

    @Override
    public void bridge$setCompassTarget(Location location) {
        this.compassTarget = location;
    }

    @Override
    public boolean bridge$isJoining() {
        return this.joining;
    }

    @Override
    public void bridge$reset() {
        this.reset();
    }

    private static /* synthetic */ Style lambda$die$0(Component component1, Style p_143420_) {
        return p_143420_.m_131144_(new HoverEvent(HoverEvent.Action.f_130831_, (Object)component1));
    }
}

