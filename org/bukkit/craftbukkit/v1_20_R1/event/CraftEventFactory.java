/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.base.Functions
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Either
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.protocol.game.ServerboundContainerClosePacket
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.Stat
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Unit
 *  net.minecraft.world.Container
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Ghast
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Slime
 *  net.minecraft.world.entity.monster.SpellcasterIllager
 *  net.minecraft.world.entity.monster.SpellcasterIllager$IllagerSpell
 *  net.minecraft.world.entity.monster.Strider
 *  net.minecraft.world.entity.monster.piglin.Piglin
 *  net.minecraft.world.entity.npc.Npc
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.player.Player$BedSleepingProblem
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.entity.raid.Raid
 *  net.minecraft.world.entity.raid.Raider
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.MerchantMenu
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.NoteBlockInstrument
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package org.bukkit.craftbukkit.v1_20_R1.event;

import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Unit;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.craftbukkit.v1_20_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.CraftLootTable;
import org.bukkit.craftbukkit.v1_20_R1.CraftRaid;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftStatistic;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRaider;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpellcaster;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBook;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftVector;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BellResonateEvent;
import org.bukkit.event.block.BellRingEvent;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockShearEntityEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.FluidLevelChangeEvent;
import org.bukkit.event.block.MoistureChangeEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.entity.BatToggleSleepEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.entity.StriderTemperatureChangeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerBucketFishEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRecipeBookClickEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerSignOpenEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.event.raid.RaidStopEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.event.world.EntitiesUnloadEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class CraftEventFactory {
    public static Block blockDamage;
    public static net.minecraft.world.entity.Entity entityDamage;
    public static BlockPos sourceBlockOverride;
    private static final com.google.common.base.Function<? super Double, Double> ZERO;

    static {
        sourceBlockOverride = null;
        ZERO = Functions.constant((Object)-0.0);
    }

    private static boolean canBuild(ServerLevel world, org.bukkit.entity.Player player, int x, int z) {
        int spawnSize = Bukkit.getServer().getSpawnRadius();
        if (world.m_46472_() != Level.f_46428_) {
            return true;
        }
        if (spawnSize <= 0) {
            return true;
        }
        if (((CraftServer)Bukkit.getServer()).getHandle().m_11307_().m_11390_()) {
            return true;
        }
        if (player.isOp()) {
            return true;
        }
        BlockPos chunkcoordinates = world.m_220360_();
        int distanceFromSpawn = Math.max(Math.abs(x - chunkcoordinates.m_123341_()), Math.abs(z - chunkcoordinates.m_123343_()));
        return distanceFromSpawn > spawnSize;
    }

    public static <T extends Event> T callEvent(T event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static boolean callPlayerSignOpenEvent(Player player, SignBlockEntity tileEntitySign, boolean front, PlayerSignOpenEvent.Cause cause) {
        CraftBlock block = CraftBlock.at((LevelAccessor)tileEntitySign.m_58904_(), tileEntitySign.m_58899_());
        Sign sign = (Sign)CraftBlockStates.getBlockState(block);
        Side side = front ? Side.FRONT : Side.BACK;
        return CraftEventFactory.callPlayerSignOpenEvent((org.bukkit.entity.Player)((Object)player.getBukkitEntity()), sign, side, cause);
    }

    public static boolean callPlayerSignOpenEvent(org.bukkit.entity.Player player, Sign sign, Side side, PlayerSignOpenEvent.Cause cause) {
        PlayerSignOpenEvent event = new PlayerSignOpenEvent(player, sign, side, cause);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static Either<Player.BedSleepingProblem, Unit> callPlayerBedEnterEvent(Player player, BlockPos bed, Either<Player.BedSleepingProblem, Unit> nmsBedResult) {
        PlayerBedEnterEvent.BedEnterResult bedEnterResult = (PlayerBedEnterEvent.BedEnterResult)((Object)nmsBedResult.mapBoth((Function)new com.google.common.base.Function<Player.BedSleepingProblem, PlayerBedEnterEvent.BedEnterResult>(){

            public PlayerBedEnterEvent.BedEnterResult apply(Player.BedSleepingProblem t) {
                switch (t) {
                    case NOT_POSSIBLE_HERE: {
                        return PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_HERE;
                    }
                    case NOT_POSSIBLE_NOW: {
                        return PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_NOW;
                    }
                    case TOO_FAR_AWAY: {
                        return PlayerBedEnterEvent.BedEnterResult.TOO_FAR_AWAY;
                    }
                    case NOT_SAFE: {
                        return PlayerBedEnterEvent.BedEnterResult.NOT_SAFE;
                    }
                }
                return PlayerBedEnterEvent.BedEnterResult.OTHER_PROBLEM;
            }
        }, t -> PlayerBedEnterEvent.BedEnterResult.OK).map(Function.identity(), Function.identity()));
        PlayerBedEnterEvent event = new PlayerBedEnterEvent((org.bukkit.entity.Player)((Object)player.getBukkitEntity()), CraftBlock.at((LevelAccessor)player.m_9236_(), bed), bedEnterResult);
        Bukkit.getServer().getPluginManager().callEvent(event);
        Event.Result result = event.useBed();
        if (result == Event.Result.ALLOW) {
            return Either.right((Object)Unit.INSTANCE);
        }
        if (result == Event.Result.DENY) {
            return Either.left((Object)Player.BedSleepingProblem.OTHER_PROBLEM);
        }
        return nmsBedResult;
    }

    public static EntityEnterLoveModeEvent callEntityEnterLoveModeEvent(Player entityHuman, Animal entityAnimal, int loveTicks) {
        EntityEnterLoveModeEvent entityEnterLoveModeEvent = new EntityEnterLoveModeEvent((Animals)((Object)entityAnimal.getBukkitEntity()), entityHuman != null ? entityHuman.getBukkitEntity() : null, loveTicks);
        Bukkit.getPluginManager().callEvent(entityEnterLoveModeEvent);
        return entityEnterLoveModeEvent;
    }

    public static PlayerHarvestBlockEvent callPlayerHarvestBlockEvent(Level world, BlockPos blockposition, Player who, InteractionHand enumhand, List<ItemStack> itemsToHarvest) {
        ArrayList<org.bukkit.inventory.ItemStack> bukkitItemsToHarvest = new ArrayList<org.bukkit.inventory.ItemStack>(itemsToHarvest.stream().map(CraftItemStack::asBukkitCopy).collect(Collectors.toList()));
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)who.getBukkitEntity());
        PlayerHarvestBlockEvent playerHarvestBlockEvent = new PlayerHarvestBlockEvent(player, CraftBlock.at((LevelAccessor)world, blockposition), CraftEquipmentSlot.getHand(enumhand), bukkitItemsToHarvest);
        Bukkit.getPluginManager().callEvent(playerHarvestBlockEvent);
        return playerHarvestBlockEvent;
    }

    public static PlayerBucketEntityEvent callPlayerFishBucketEvent(net.minecraft.world.entity.LivingEntity fish, Player entityHuman, ItemStack originalBucket, ItemStack entityBucket, InteractionHand enumhand) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)entityHuman.getBukkitEntity());
        EquipmentSlot hand = CraftEquipmentSlot.getHand(enumhand);
        PlayerBucketEntityEvent event = fish instanceof AbstractFish ? new PlayerBucketFishEvent(player, (Fish)((Object)fish.getBukkitEntity()), CraftItemStack.asBukkitCopy(originalBucket), CraftItemStack.asBukkitCopy(entityBucket), hand) : new PlayerBucketEntityEvent(player, fish.getBukkitEntity(), CraftItemStack.asBukkitCopy(originalBucket), CraftItemStack.asBukkitCopy(entityBucket), hand);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static TradeSelectEvent callTradeSelectEvent(ServerPlayer player, int newIndex, MerchantMenu merchant) {
        TradeSelectEvent tradeSelectEvent = new TradeSelectEvent(merchant.getBukkitView(), newIndex);
        Bukkit.getPluginManager().callEvent(tradeSelectEvent);
        return tradeSelectEvent;
    }

    public static boolean handleBellRingEvent(Level world, BlockPos position, Direction direction, net.minecraft.world.entity.Entity entity) {
        CraftBlock block = CraftBlock.at((LevelAccessor)world, position);
        BlockFace bukkitDirection = CraftBlock.notchToBlockFace(direction);
        BellRingEvent event = new BellRingEvent(block, bukkitDirection, entity != null ? entity.getBukkitEntity() : null);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static Stream<net.minecraft.world.entity.LivingEntity> handleBellResonateEvent(Level world, BlockPos position, List<LivingEntity> bukkitEntities) {
        CraftBlock block = CraftBlock.at((LevelAccessor)world, position);
        BellResonateEvent event = new BellResonateEvent(block, bukkitEntities);
        Bukkit.getPluginManager().callEvent(event);
        return event.getResonatedEntities().stream().map(bukkitEntity -> ((CraftLivingEntity)bukkitEntity).getHandle());
    }

    public static BlockMultiPlaceEvent callBlockMultiPlaceEvent(ServerLevel world, Player who, InteractionHand hand, List<org.bukkit.block.BlockState> blockStates, int clickedX, int clickedY, int clickedZ) {
        CraftWorld craftWorld = world.getWorld();
        CraftServer craftServer = world.getCraftServer();
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)who.getBukkitEntity());
        Block blockClicked = craftWorld.getBlockAt(clickedX, clickedY, clickedZ);
        boolean canBuild = true;
        int i = 0;
        while (i < blockStates.size()) {
            if (!CraftEventFactory.canBuild(world, player, blockStates.get(i).getX(), blockStates.get(i).getZ())) {
                canBuild = false;
                break;
            }
            ++i;
        }
        org.bukkit.inventory.ItemStack item = hand == InteractionHand.MAIN_HAND ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand();
        BlockMultiPlaceEvent event = new BlockMultiPlaceEvent(blockStates, blockClicked, item, player, canBuild);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static BlockPlaceEvent callBlockPlaceEvent(ServerLevel world, Player who, InteractionHand hand, org.bukkit.block.BlockState replacedBlockState, int clickedX, int clickedY, int clickedZ) {
        EquipmentSlot equipmentSlot;
        org.bukkit.inventory.ItemStack item;
        CraftWorld craftWorld = world.getWorld();
        CraftServer craftServer = world.getCraftServer();
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)who.getBukkitEntity());
        Block blockClicked = craftWorld.getBlockAt(clickedX, clickedY, clickedZ);
        Block placedBlock = replacedBlockState.getBlock();
        boolean canBuild = CraftEventFactory.canBuild(world, player, placedBlock.getX(), placedBlock.getZ());
        if (hand == InteractionHand.MAIN_HAND) {
            item = player.getInventory().getItemInMainHand();
            equipmentSlot = EquipmentSlot.HAND;
        } else {
            item = player.getInventory().getItemInOffHand();
            equipmentSlot = EquipmentSlot.OFF_HAND;
        }
        BlockPlaceEvent event = new BlockPlaceEvent(placedBlock, replacedBlockState, blockClicked, item, player, canBuild, equipmentSlot);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static void handleBlockDropItemEvent(Block block, org.bukkit.block.BlockState state, ServerPlayer player, List<ItemEntity> items) {
        BlockDropItemEvent event = new BlockDropItemEvent(block, state, player.getBukkitEntity(), Lists.transform(items, item -> (Item)((Object)item.getBukkitEntity())));
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            for (ItemEntity item2 : items) {
                item2.m_9236_().m_7967_((net.minecraft.world.entity.Entity)item2);
            }
        }
    }

    public static EntityPlaceEvent callEntityPlaceEvent(UseOnContext itemactioncontext, net.minecraft.world.entity.Entity entity) {
        return CraftEventFactory.callEntityPlaceEvent(itemactioncontext.m_43725_(), itemactioncontext.m_8083_(), itemactioncontext.m_43719_(), itemactioncontext.m_43723_(), entity, itemactioncontext.m_43724_());
    }

    public static EntityPlaceEvent callEntityPlaceEvent(Level world, BlockPos clickPosition, Direction clickedFace, Player human, net.minecraft.world.entity.Entity entity, InteractionHand enumhand) {
        org.bukkit.entity.Player who = human == null ? null : (org.bukkit.entity.Player)((Object)human.getBukkitEntity());
        CraftBlock blockClicked = CraftBlock.at((LevelAccessor)world, clickPosition);
        BlockFace blockFace = CraftBlock.notchToBlockFace(clickedFace);
        EntityPlaceEvent event = new EntityPlaceEvent(entity.getBukkitEntity(), who, blockClicked, blockFace, CraftEquipmentSlot.getHand(enumhand));
        entity.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static PlayerBucketEmptyEvent callPlayerBucketEmptyEvent(ServerLevel world, Player who, BlockPos changed, BlockPos clicked, Direction clickedFace, ItemStack itemInHand, InteractionHand enumhand) {
        return (PlayerBucketEmptyEvent)CraftEventFactory.getPlayerBucketEvent(false, world, who, changed, clicked, clickedFace, itemInHand, Items.f_42446_, enumhand);
    }

    public static PlayerBucketFillEvent callPlayerBucketFillEvent(ServerLevel world, Player who, BlockPos changed, BlockPos clicked, Direction clickedFace, ItemStack itemInHand, net.minecraft.world.item.Item bucket, InteractionHand enumhand) {
        return (PlayerBucketFillEvent)CraftEventFactory.getPlayerBucketEvent(true, world, who, clicked, changed, clickedFace, itemInHand, bucket, enumhand);
    }

    private static PlayerEvent getPlayerBucketEvent(boolean isFilling, ServerLevel world, Player who, BlockPos changed, BlockPos clicked, Direction clickedFace, ItemStack itemstack, net.minecraft.world.item.Item item, InteractionHand enumhand) {
        PlayerBucketEvent event;
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)who.getBukkitEntity());
        CraftItemStack itemInHand = CraftItemStack.asNewCraftStack(item);
        Material bucket = CraftMagicNumbers.getMaterial(itemstack.m_41720_());
        CraftServer craftServer = (CraftServer)player.getServer();
        CraftBlock block = CraftBlock.at((LevelAccessor)world, changed);
        CraftBlock blockClicked = CraftBlock.at((LevelAccessor)world, clicked);
        BlockFace blockFace = CraftBlock.notchToBlockFace(clickedFace);
        EquipmentSlot hand = CraftEquipmentSlot.getHand(enumhand);
        if (isFilling) {
            event = new PlayerBucketFillEvent(player, block, blockClicked, blockFace, bucket, itemInHand, hand);
            event.setCancelled(!CraftEventFactory.canBuild(world, player, changed.m_123341_(), changed.m_123343_()));
        } else {
            event = new PlayerBucketEmptyEvent(player, block, blockClicked, blockFace, bucket, itemInHand, hand);
            ((PlayerBucketEmptyEvent)event).setCancelled(!CraftEventFactory.canBuild(world, player, changed.m_123341_(), changed.m_123343_()));
        }
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static PlayerInteractEvent callPlayerInteractEvent(Player who, Action action, ItemStack itemstack, InteractionHand hand) {
        if (action != Action.LEFT_CLICK_AIR && action != Action.RIGHT_CLICK_AIR) {
            throw new AssertionError((Object)String.format("%s performing %s with %s", new Object[]{who, action, itemstack}));
        }
        return CraftEventFactory.callPlayerInteractEvent(who, action, null, Direction.SOUTH, itemstack, hand);
    }

    public static PlayerInteractEvent callPlayerInteractEvent(Player who, Action action, BlockPos position, Direction direction, ItemStack itemstack, InteractionHand hand) {
        return CraftEventFactory.callPlayerInteractEvent(who, action, position, direction, itemstack, false, hand, null);
    }

    public static PlayerInteractEvent callPlayerInteractEvent(Player who, Action action, BlockPos position, Direction direction, ItemStack itemstack, boolean cancelledBlock, InteractionHand hand, Vec3 targetPos) {
        org.bukkit.entity.Player player = who == null ? null : (org.bukkit.entity.Player)((Object)who.getBukkitEntity());
        CraftItemStack itemInHand = CraftItemStack.asCraftMirror(itemstack);
        Vector clickedPos = null;
        if (position != null && targetPos != null) {
            clickedPos = CraftVector.toBukkit(targetPos.m_82546_(Vec3.m_82528_((Vec3i)position)));
        }
        CraftWorld craftWorld = (CraftWorld)player.getWorld();
        CraftServer craftServer = (CraftServer)player.getServer();
        Block blockClicked = null;
        if (position != null) {
            blockClicked = craftWorld.getBlockAt(position.m_123341_(), position.m_123342_(), position.m_123343_());
        } else {
            switch (action) {
                case LEFT_CLICK_BLOCK: {
                    action = Action.LEFT_CLICK_AIR;
                    break;
                }
                case RIGHT_CLICK_BLOCK: {
                    action = Action.RIGHT_CLICK_AIR;
                }
            }
        }
        BlockFace blockFace = CraftBlock.notchToBlockFace(direction);
        if (itemInHand.getType() == Material.AIR || itemInHand.getAmount() == 0) {
            itemInHand = null;
        }
        PlayerInteractEvent event = new PlayerInteractEvent(player, action, itemInHand, blockClicked, blockFace, hand == null ? null : (hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND), clickedPos);
        if (cancelledBlock) {
            event.setUseInteractedBlock(Event.Result.DENY);
        }
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static EntityTransformEvent callEntityTransformEvent(net.minecraft.world.entity.LivingEntity original, net.minecraft.world.entity.LivingEntity coverted, EntityTransformEvent.TransformReason transformReason) {
        return CraftEventFactory.callEntityTransformEvent(original, Collections.singletonList(coverted), transformReason);
    }

    public static EntityTransformEvent callEntityTransformEvent(net.minecraft.world.entity.LivingEntity original, List<net.minecraft.world.entity.LivingEntity> convertedList, EntityTransformEvent.TransformReason convertType) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for (net.minecraft.world.entity.LivingEntity entityLiving : convertedList) {
            list.add(entityLiving.getBukkitEntity());
        }
        EntityTransformEvent event = new EntityTransformEvent(original.getBukkitEntity(), list, convertType);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static EntityShootBowEvent callEntityShootBowEvent(net.minecraft.world.entity.LivingEntity who, ItemStack bow, ItemStack consumableItem, net.minecraft.world.entity.Entity entityArrow, InteractionHand hand, float force, boolean consumeItem) {
        EquipmentSlot handSlot;
        LivingEntity shooter = (LivingEntity)((Object)who.getBukkitEntity());
        CraftItemStack itemInHand = CraftItemStack.asCraftMirror(bow);
        CraftItemStack itemConsumable = CraftItemStack.asCraftMirror(consumableItem);
        CraftEntity arrow = entityArrow.getBukkitEntity();
        EquipmentSlot equipmentSlot = handSlot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
        if (itemInHand != null && (itemInHand.getType() == Material.AIR || itemInHand.getAmount() == 0)) {
            itemInHand = null;
        }
        EntityShootBowEvent event = new EntityShootBowEvent(shooter, itemInHand, itemConsumable, arrow, handSlot, force, consumeItem);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static VillagerCareerChangeEvent callVillagerCareerChangeEvent(net.minecraft.world.entity.npc.Villager vilager, Villager.Profession future, VillagerCareerChangeEvent.ChangeReason reason) {
        VillagerCareerChangeEvent event = new VillagerCareerChangeEvent((Villager)((Object)vilager.getBukkitEntity()), future, reason);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static BlockDamageEvent callBlockDamageEvent(ServerPlayer who, BlockPos pos, ItemStack itemstack, boolean instaBreak) {
        CraftPlayer player = who.getBukkitEntity();
        CraftItemStack itemInHand = CraftItemStack.asCraftMirror(itemstack);
        CraftBlock blockClicked = CraftBlock.at((LevelAccessor)who.m_9236_(), pos);
        BlockDamageEvent event = new BlockDamageEvent(player, blockClicked, itemInHand, instaBreak);
        player.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static BlockDamageAbortEvent callBlockDamageAbortEvent(ServerPlayer who, BlockPos pos, ItemStack itemstack) {
        CraftPlayer player = who.getBukkitEntity();
        CraftItemStack itemInHand = CraftItemStack.asCraftMirror(itemstack);
        CraftBlock blockClicked = CraftBlock.at((LevelAccessor)who.m_9236_(), pos);
        BlockDamageAbortEvent event = new BlockDamageAbortEvent(player, blockClicked, itemInHand);
        player.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static boolean doEntityAddEventCalling(Level world, net.minecraft.world.entity.Entity entity, CreatureSpawnEvent.SpawnReason spawnReason) {
        if (entity == null) {
            return false;
        }
        Event event = null;
        if (entity instanceof net.minecraft.world.entity.LivingEntity && !(entity instanceof ServerPlayer)) {
            boolean isAnimal = entity instanceof Animal || entity instanceof WaterAnimal || entity instanceof AbstractGolem;
            boolean isMonster = entity instanceof Monster || entity instanceof Ghast || entity instanceof Slime;
            boolean isNpc = entity instanceof Npc;
            if (spawnReason != CreatureSpawnEvent.SpawnReason.CUSTOM && (isAnimal && !world.getWorld().getAllowAnimals() || isMonster && !world.getWorld().getAllowMonsters() || isNpc && !world.getCraftServer().getServer().m_6997_())) {
                entity.m_146870_();
                return false;
            }
            event = CraftEventFactory.callCreatureSpawnEvent((net.minecraft.world.entity.LivingEntity)entity, spawnReason);
        } else if (entity instanceof ItemEntity) {
            event = CraftEventFactory.callItemSpawnEvent((ItemEntity)entity);
        } else if (entity.getBukkitEntity() instanceof org.bukkit.entity.Projectile) {
            event = CraftEventFactory.callProjectileLaunchEvent(entity);
        } else if (entity.getBukkitEntity() instanceof Vehicle) {
            event = CraftEventFactory.callVehicleCreateEvent(entity);
        } else if (entity.getBukkitEntity() instanceof LightningStrike) {
            LightningStrikeEvent.Cause cause = LightningStrikeEvent.Cause.UNKNOWN;
            switch (spawnReason) {
                case COMMAND: {
                    cause = LightningStrikeEvent.Cause.COMMAND;
                    break;
                }
                case CUSTOM: {
                    cause = LightningStrikeEvent.Cause.CUSTOM;
                    break;
                }
                case SPAWNER: {
                    cause = LightningStrikeEvent.Cause.SPAWNER;
                }
            }
            if (cause == LightningStrikeEvent.Cause.UNKNOWN && spawnReason == CreatureSpawnEvent.SpawnReason.DEFAULT) {
                return true;
            }
            event = CraftEventFactory.callLightningStrikeEvent((LightningStrike)((Object)entity.getBukkitEntity()), cause);
        } else if (entity instanceof ExperienceOrb) {
            ExperienceOrb xp = (ExperienceOrb)entity;
            double radius = world.spigotConfig.expMerge;
            if (radius > 0.0) {
                List entities = world.m_45933_(entity, entity.m_20191_().m_82377_(radius, radius, radius));
                for (net.minecraft.world.entity.Entity e : entities) {
                    ExperienceOrb loopItem;
                    if (!(e instanceof ExperienceOrb) || (loopItem = (ExperienceOrb)e).m_213877_()) continue;
                    xp.f_20770_ += loopItem.f_20770_;
                    loopItem.m_146870_();
                }
            }
        } else if (!(entity instanceof ServerPlayer)) {
            event = CraftEventFactory.callEntitySpawnEvent(entity);
        }
        if (event != null && (event.isCancelled() || entity.m_213877_())) {
            net.minecraft.world.entity.Entity vehicle = entity.m_20202_();
            if (vehicle != null) {
                vehicle.m_146870_();
            }
            for (net.minecraft.world.entity.Entity passenger : entity.m_146897_()) {
                passenger.m_146870_();
            }
            entity.m_146870_();
            return false;
        }
        return true;
    }

    public static EntitySpawnEvent callEntitySpawnEvent(net.minecraft.world.entity.Entity entity) {
        CraftEntity bukkitEntity = entity.getBukkitEntity();
        EntitySpawnEvent event = new EntitySpawnEvent(bukkitEntity);
        bukkitEntity.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static CreatureSpawnEvent callCreatureSpawnEvent(net.minecraft.world.entity.LivingEntity entityliving, CreatureSpawnEvent.SpawnReason spawnReason) {
        LivingEntity entity = (LivingEntity)((Object)entityliving.getBukkitEntity());
        CraftServer craftServer = (CraftServer)entity.getServer();
        CreatureSpawnEvent event = new CreatureSpawnEvent(entity, spawnReason);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static EntityTameEvent callEntityTameEvent(Mob entity, Player tamer) {
        CraftEntity bukkitEntity = entity.getBukkitEntity();
        CraftHumanEntity bukkitTamer = tamer != null ? tamer.getBukkitEntity() : null;
        CraftServer craftServer = (CraftServer)bukkitEntity.getServer();
        EntityTameEvent event = new EntityTameEvent((LivingEntity)((Object)bukkitEntity), bukkitTamer);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static ItemSpawnEvent callItemSpawnEvent(ItemEntity entityitem) {
        Item entity = (Item)((Object)entityitem.getBukkitEntity());
        CraftServer craftServer = (CraftServer)entity.getServer();
        ItemSpawnEvent event = new ItemSpawnEvent(entity);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static ItemDespawnEvent callItemDespawnEvent(ItemEntity entityitem) {
        Item entity = (Item)((Object)entityitem.getBukkitEntity());
        ItemDespawnEvent event = new ItemDespawnEvent(entity, entity.getLocation());
        entity.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static boolean callItemMergeEvent(ItemEntity merging, ItemEntity mergingWith) {
        Item entityMerging = (Item)((Object)merging.getBukkitEntity());
        Item entityMergingWith = (Item)((Object)mergingWith.getBukkitEntity());
        ItemMergeEvent event = new ItemMergeEvent(entityMerging, entityMergingWith);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static PotionSplashEvent callPotionSplashEvent(net.minecraft.world.entity.projectile.ThrownPotion potion, Map<LivingEntity, Double> affectedEntities) {
        ThrownPotion thrownPotion = (ThrownPotion)((Object)potion.getBukkitEntity());
        PotionSplashEvent event = new PotionSplashEvent(thrownPotion, affectedEntities);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static LingeringPotionSplashEvent callLingeringPotionSplashEvent(net.minecraft.world.entity.projectile.ThrownPotion potion, AreaEffectCloud cloud) {
        ThrownPotion thrownPotion = (ThrownPotion)((Object)potion.getBukkitEntity());
        org.bukkit.entity.AreaEffectCloud effectCloud = (org.bukkit.entity.AreaEffectCloud)((Object)cloud.getBukkitEntity());
        LingeringPotionSplashEvent event = new LingeringPotionSplashEvent(thrownPotion, effectCloud);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static BlockFadeEvent callBlockFadeEvent(LevelAccessor world, BlockPos pos, BlockState newBlock) {
        CraftBlockState state = CraftBlockStates.getBlockState(world, pos);
        state.setData(newBlock);
        BlockFadeEvent event = new BlockFadeEvent(state.getBlock(), state);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static boolean handleMoistureChangeEvent(Level world, BlockPos pos, BlockState newBlock, int flag) {
        CraftBlockState state = CraftBlockStates.getBlockState((LevelAccessor)world, pos, flag);
        state.setData(newBlock);
        MoistureChangeEvent event = new MoistureChangeEvent(state.getBlock(), state);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            state.update(true);
        }
        return !event.isCancelled();
    }

    public static boolean handleBlockSpreadEvent(Level world, BlockPos source, BlockPos target, BlockState block) {
        return CraftEventFactory.handleBlockSpreadEvent((LevelAccessor)world, source, target, block, 2);
    }

    public static boolean handleBlockSpreadEvent(LevelAccessor world, BlockPos source, BlockPos target, BlockState block, int flag) {
        if (!(world instanceof Level)) {
            world.m_7731_(target, block, flag);
            return true;
        }
        CraftBlockState state = CraftBlockStates.getBlockState(world, target, flag);
        state.setData(block);
        BlockSpreadEvent event = new BlockSpreadEvent(state.getBlock(), CraftBlock.at(world, sourceBlockOverride != null ? sourceBlockOverride : source), state);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            state.update(true);
        }
        return !event.isCancelled();
    }

    public static EntityDeathEvent callEntityDeathEvent(net.minecraft.world.entity.LivingEntity victim) {
        return CraftEventFactory.callEntityDeathEvent(victim, new ArrayList<org.bukkit.inventory.ItemStack>(0));
    }

    public static EntityDeathEvent callEntityDeathEvent(net.minecraft.world.entity.LivingEntity victim, List<org.bukkit.inventory.ItemStack> drops) {
        CraftLivingEntity entity = (CraftLivingEntity)victim.getBukkitEntity();
        EntityDeathEvent event = new EntityDeathEvent(entity, drops, victim.getExpReward());
        CraftWorld world = (CraftWorld)entity.getWorld();
        Bukkit.getServer().getPluginManager().callEvent(event);
        victim.expToDrop = event.getDroppedExp();
        for (org.bukkit.inventory.ItemStack stack : event.getDrops()) {
            if (stack == null || stack.getType() == Material.AIR || stack.getAmount() == 0) continue;
            world.dropItem(entity.getLocation(), stack);
        }
        return event;
    }

    public static PlayerDeathEvent callPlayerDeathEvent(ServerPlayer victim, List<org.bukkit.inventory.ItemStack> drops, String deathMessage, boolean keepInventory) {
        CraftPlayer entity = victim.getBukkitEntity();
        PlayerDeathEvent event = new PlayerDeathEvent(entity, drops, victim.getExpReward(), 0, deathMessage);
        event.setKeepInventory(keepInventory);
        event.setKeepLevel(victim.keepLevel);
        World world = entity.getWorld();
        Bukkit.getServer().getPluginManager().callEvent(event);
        victim.keepLevel = event.getKeepLevel();
        victim.newLevel = event.getNewLevel();
        victim.newTotalExp = event.getNewTotalExp();
        victim.expToDrop = event.getDroppedExp();
        victim.newExp = event.getNewExp();
        for (org.bukkit.inventory.ItemStack stack : event.getDrops()) {
            if (stack == null || stack.getType() == Material.AIR) continue;
            world.dropItem(entity.getLocation(), stack);
        }
        return event;
    }

    public static ServerListPingEvent callServerListPingEvent(Server craftServer, InetAddress address, String motd, int numPlayers, int maxPlayers) {
        ServerListPingEvent event = new ServerListPingEvent("", address, motd, numPlayers, maxPlayers);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    private static EntityDamageEvent handleEntityDamageEvent(net.minecraft.world.entity.Entity entity, DamageSource source, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>> modifierFunctions) {
        return CraftEventFactory.handleEntityDamageEvent(entity, source, modifiers, modifierFunctions, false);
    }

    private static EntityDamageEvent handleEntityDamageEvent(net.minecraft.world.entity.Entity entity, DamageSource source, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>> modifierFunctions, boolean cancelled) {
        if (source.m_269533_(DamageTypeTags.f_268415_)) {
            EntityDamageEvent event;
            net.minecraft.world.entity.Entity damager = entityDamage;
            entityDamage = null;
            if (damager == null) {
                event = new EntityDamageByBlockEvent(null, entity.getBukkitEntity(), EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, modifiers, modifierFunctions);
            } else {
                boolean cfr_ignored_0 = entity instanceof EnderDragon;
                EntityDamageEvent.DamageCause damageCause = damager instanceof TNTPrimed ? EntityDamageEvent.DamageCause.BLOCK_EXPLOSION : EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
                event = new EntityDamageByEntityEvent(damager.getBukkitEntity(), entity.getBukkitEntity(), damageCause, modifiers, modifierFunctions);
            }
            event.setCancelled(cancelled);
            CraftEventFactory.callEvent(event);
            if (!event.isCancelled()) {
                event.getEntity().setLastDamageCause(event);
            } else {
                entity.lastDamageCancelled = true;
            }
            return event;
        }
        if (source.m_7639_() != null || source.m_7640_() != null) {
            EntityDamageEvent.DamageCause cause;
            net.minecraft.world.entity.Entity damager = source.m_7639_();
            EntityDamageEvent.DamageCause damageCause = cause = source.isSweep() ? EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK : EntityDamageEvent.DamageCause.ENTITY_ATTACK;
            if (source.m_269014_() && source.m_7640_() != null) {
                damager = source.m_7640_();
            }
            if (damager instanceof Projectile) {
                if (damager.getBukkitEntity() instanceof ThrownPotion) {
                    cause = EntityDamageEvent.DamageCause.MAGIC;
                } else if (damager.getBukkitEntity() instanceof org.bukkit.entity.Projectile) {
                    cause = EntityDamageEvent.DamageCause.PROJECTILE;
                }
            } else if (source.m_276093_(DamageTypes.f_268440_)) {
                cause = EntityDamageEvent.DamageCause.THORNS;
            } else if (source.m_276093_(DamageTypes.f_268679_)) {
                cause = EntityDamageEvent.DamageCause.SONIC_BOOM;
            }
            return CraftEventFactory.callEntityDamageEvent(damager, entity, cause, modifiers, modifierFunctions, cancelled);
        }
        if (source.m_276093_(DamageTypes.f_268724_)) {
            EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(null, entity.getBukkitEntity(), EntityDamageEvent.DamageCause.VOID, modifiers, modifierFunctions);
            event.setCancelled(cancelled);
            CraftEventFactory.callEvent(event);
            if (!event.isCancelled()) {
                event.getEntity().setLastDamageCause(event);
            } else {
                entity.lastDamageCancelled = true;
            }
            return event;
        }
        if (source.m_276093_(DamageTypes.f_268546_)) {
            EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(blockDamage, entity.getBukkitEntity(), EntityDamageEvent.DamageCause.LAVA, modifiers, modifierFunctions);
            event.setCancelled(cancelled);
            Block damager = blockDamage;
            blockDamage = null;
            CraftEventFactory.callEvent(event);
            blockDamage = damager;
            if (!event.isCancelled()) {
                event.getEntity().setLastDamageCause(event);
            } else {
                entity.lastDamageCancelled = true;
            }
            return event;
        }
        if (blockDamage != null) {
            EntityDamageEvent.DamageCause cause = null;
            Block damager = blockDamage;
            if (source.m_276093_(DamageTypes.f_268585_) || source.m_276093_(DamageTypes.f_268469_) || source.m_276093_(DamageTypes.f_268669_) || source.m_276093_(DamageTypes.f_268513_) || source.m_276093_(DamageTypes.f_268526_)) {
                cause = EntityDamageEvent.DamageCause.CONTACT;
            } else if (source.m_276093_(DamageTypes.f_268434_)) {
                cause = EntityDamageEvent.DamageCause.HOT_FLOOR;
            } else if (source.m_276093_(DamageTypes.f_268515_)) {
                cause = EntityDamageEvent.DamageCause.MAGIC;
            } else if (source.m_276093_(DamageTypes.f_268631_)) {
                cause = EntityDamageEvent.DamageCause.FIRE;
            } else {
                throw new IllegalStateException(String.format("Unhandled damage of %s by %s from %s", entity, damager, source.m_19385_()));
            }
            EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(damager, entity.getBukkitEntity(), cause, modifiers, modifierFunctions);
            event.setCancelled(cancelled);
            blockDamage = null;
            CraftEventFactory.callEvent(event);
            blockDamage = damager;
            if (!event.isCancelled()) {
                event.getEntity().setLastDamageCause(event);
            } else {
                entity.lastDamageCancelled = true;
            }
            return event;
        }
        if (entityDamage != null) {
            EntityDamageEvent.DamageCause cause = null;
            CraftEntity damager = entityDamage.getBukkitEntity();
            entityDamage = null;
            if (source.m_276093_(DamageTypes.f_268513_) || source.m_276093_(DamageTypes.f_268659_) || source.m_276093_(DamageTypes.f_268526_)) {
                cause = EntityDamageEvent.DamageCause.FALLING_BLOCK;
            } else if (damager instanceof LightningStrike) {
                cause = EntityDamageEvent.DamageCause.LIGHTNING;
            } else if (source.m_276093_(DamageTypes.f_268671_)) {
                cause = EntityDamageEvent.DamageCause.FALL;
            } else if (source.m_276093_(DamageTypes.f_268482_)) {
                cause = EntityDamageEvent.DamageCause.DRAGON_BREATH;
            } else if (source.m_276093_(DamageTypes.f_268515_)) {
                cause = EntityDamageEvent.DamageCause.MAGIC;
            } else {
                throw new IllegalStateException(String.format("Unhandled damage of %s by %s from %s", entity, damager.getHandle(), source.m_19385_()));
            }
            EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, entity.getBukkitEntity(), cause, modifiers, modifierFunctions);
            event.setCancelled(cancelled);
            CraftEventFactory.callEvent(event);
            if (!event.isCancelled()) {
                event.getEntity().setLastDamageCause(event);
            } else {
                entity.lastDamageCancelled = true;
            }
            return event;
        }
        EntityDamageEvent.DamageCause cause = null;
        cause = source.m_276093_(DamageTypes.f_268631_) ? EntityDamageEvent.DamageCause.FIRE : (source.m_276093_(DamageTypes.f_268441_) ? EntityDamageEvent.DamageCause.STARVATION : (source.m_276093_(DamageTypes.f_268493_) ? EntityDamageEvent.DamageCause.WITHER : (source.m_276093_(DamageTypes.f_268612_) ? EntityDamageEvent.DamageCause.SUFFOCATION : (source.m_276093_(DamageTypes.f_268722_) ? EntityDamageEvent.DamageCause.DROWNING : (source.m_276093_(DamageTypes.f_268468_) ? EntityDamageEvent.DamageCause.FIRE_TICK : (source.isMelting() ? EntityDamageEvent.DamageCause.MELTING : (source.isPoison() ? EntityDamageEvent.DamageCause.POISON : (source.m_276093_(DamageTypes.f_268515_) ? EntityDamageEvent.DamageCause.MAGIC : (source.m_276093_(DamageTypes.f_268671_) ? EntityDamageEvent.DamageCause.FALL : (source.m_276093_(DamageTypes.f_268576_) ? EntityDamageEvent.DamageCause.FLY_INTO_WALL : (source.m_276093_(DamageTypes.f_268613_) ? EntityDamageEvent.DamageCause.CRAMMING : (source.m_276093_(DamageTypes.f_268752_) ? EntityDamageEvent.DamageCause.DRYOUT : (source.m_276093_(DamageTypes.f_268444_) ? EntityDamageEvent.DamageCause.FREEZE : (source.m_276093_(DamageTypes.f_286979_) ? EntityDamageEvent.DamageCause.KILL : (source.m_276093_(DamageTypes.f_286973_) ? EntityDamageEvent.DamageCause.WORLD_BORDER : EntityDamageEvent.DamageCause.CUSTOM)))))))))))))));
        if (cause != null) {
            return CraftEventFactory.callEntityDamageEvent(null, entity, cause, modifiers, modifierFunctions, cancelled);
        }
        throw new IllegalStateException(String.format("Unhandled damage of %s from %s", entity, source.m_19385_()));
    }

    private static EntityDamageEvent callEntityDamageEvent(net.minecraft.world.entity.Entity damager, net.minecraft.world.entity.Entity damagee, EntityDamageEvent.DamageCause cause, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>> modifierFunctions) {
        return CraftEventFactory.callEntityDamageEvent(damager, damagee, cause, modifiers, modifierFunctions, false);
    }

    private static EntityDamageEvent callEntityDamageEvent(net.minecraft.world.entity.Entity damager, net.minecraft.world.entity.Entity damagee, EntityDamageEvent.DamageCause cause, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>> modifierFunctions, boolean cancelled) {
        EntityDamageEvent event = damager != null ? new EntityDamageByEntityEvent(damager.getBukkitEntity(), damagee.getBukkitEntity(), cause, modifiers, modifierFunctions) : new EntityDamageEvent(damagee.getBukkitEntity(), cause, modifiers, modifierFunctions);
        event.setCancelled(cancelled);
        CraftEventFactory.callEvent(event);
        if (!event.isCancelled()) {
            event.getEntity().setLastDamageCause(event);
        } else {
            damagee.lastDamageCancelled = true;
        }
        return event;
    }

    public static EntityDamageEvent handleLivingEntityDamageEvent(net.minecraft.world.entity.Entity damagee, DamageSource source, double rawDamage, double hardHatModifier, double blockingModifier, double armorModifier, double resistanceModifier, double magicModifier, double absorptionModifier, com.google.common.base.Function<Double, Double> hardHat, com.google.common.base.Function<Double, Double> blocking, com.google.common.base.Function<Double, Double> armor, com.google.common.base.Function<Double, Double> resistance, com.google.common.base.Function<Double, Double> magic, com.google.common.base.Function<Double, Double> absorption) {
        EnumMap<EntityDamageEvent.DamageModifier, Double> modifiers = new EnumMap<EntityDamageEvent.DamageModifier, Double>(EntityDamageEvent.DamageModifier.class);
        EnumMap<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>> modifierFunctions = new EnumMap<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>>(EntityDamageEvent.DamageModifier.class);
        modifiers.put(EntityDamageEvent.DamageModifier.BASE, rawDamage);
        modifierFunctions.put(EntityDamageEvent.DamageModifier.BASE, ZERO);
        if (source.m_276093_(DamageTypes.f_268659_) || source.m_276093_(DamageTypes.f_268526_)) {
            modifiers.put(EntityDamageEvent.DamageModifier.HARD_HAT, hardHatModifier);
            modifierFunctions.put(EntityDamageEvent.DamageModifier.HARD_HAT, hardHat);
        }
        if (damagee instanceof Player) {
            modifiers.put(EntityDamageEvent.DamageModifier.BLOCKING, blockingModifier);
            modifierFunctions.put(EntityDamageEvent.DamageModifier.BLOCKING, blocking);
        }
        modifiers.put(EntityDamageEvent.DamageModifier.ARMOR, armorModifier);
        modifierFunctions.put(EntityDamageEvent.DamageModifier.ARMOR, armor);
        modifiers.put(EntityDamageEvent.DamageModifier.RESISTANCE, resistanceModifier);
        modifierFunctions.put(EntityDamageEvent.DamageModifier.RESISTANCE, resistance);
        modifiers.put(EntityDamageEvent.DamageModifier.MAGIC, magicModifier);
        modifierFunctions.put(EntityDamageEvent.DamageModifier.MAGIC, magic);
        modifiers.put(EntityDamageEvent.DamageModifier.ABSORPTION, absorptionModifier);
        modifierFunctions.put(EntityDamageEvent.DamageModifier.ABSORPTION, absorption);
        return CraftEventFactory.handleEntityDamageEvent(damagee, source, modifiers, modifierFunctions);
    }

    public static boolean handleNonLivingEntityDamageEvent(net.minecraft.world.entity.Entity entity, DamageSource source, double damage) {
        return CraftEventFactory.handleNonLivingEntityDamageEvent(entity, source, damage, true);
    }

    public static boolean handleNonLivingEntityDamageEvent(net.minecraft.world.entity.Entity entity, DamageSource source, double damage, boolean cancelOnZeroDamage) {
        return CraftEventFactory.handleNonLivingEntityDamageEvent(entity, source, damage, cancelOnZeroDamage, false);
    }

    public static EntityDamageEvent callNonLivingEntityDamageEvent(net.minecraft.world.entity.Entity entity, DamageSource source, double damage, boolean cancelled) {
        EnumMap<EntityDamageEvent.DamageModifier, Double> modifiers = new EnumMap<EntityDamageEvent.DamageModifier, Double>(EntityDamageEvent.DamageModifier.class);
        EnumMap<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>> functions = new EnumMap<EntityDamageEvent.DamageModifier, com.google.common.base.Function<? super Double, Double>>(EntityDamageEvent.DamageModifier.class);
        modifiers.put(EntityDamageEvent.DamageModifier.BASE, damage);
        functions.put(EntityDamageEvent.DamageModifier.BASE, ZERO);
        return CraftEventFactory.handleEntityDamageEvent(entity, source, modifiers, functions, cancelled);
    }

    public static boolean handleNonLivingEntityDamageEvent(net.minecraft.world.entity.Entity entity, DamageSource source, double damage, boolean cancelOnZeroDamage, boolean cancelled) {
        EntityDamageEvent event = CraftEventFactory.callNonLivingEntityDamageEvent(entity, source, damage, cancelled);
        if (event == null) {
            return false;
        }
        return event.isCancelled() || cancelOnZeroDamage && event.getDamage() == 0.0;
    }

    public static PlayerLevelChangeEvent callPlayerLevelChangeEvent(org.bukkit.entity.Player player, int oldLevel, int newLevel) {
        PlayerLevelChangeEvent event = new PlayerLevelChangeEvent(player, oldLevel, newLevel);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static PlayerExpChangeEvent callPlayerExpChangeEvent(Player entity, int expAmount) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)entity.getBukkitEntity());
        PlayerExpChangeEvent event = new PlayerExpChangeEvent(player, expAmount);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static PlayerExpCooldownChangeEvent callPlayerXpCooldownEvent(Player entity, int newCooldown, PlayerExpCooldownChangeEvent.ChangeReason changeReason) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)entity.getBukkitEntity());
        PlayerExpCooldownChangeEvent event = new PlayerExpCooldownChangeEvent(player, newCooldown, changeReason);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static PlayerItemMendEvent callPlayerItemMendEvent(Player entity, ExperienceOrb orb, ItemStack nmsMendedItem, net.minecraft.world.entity.EquipmentSlot slot, int repairAmount) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)entity.getBukkitEntity());
        CraftItemStack bukkitStack = CraftItemStack.asCraftMirror(nmsMendedItem);
        PlayerItemMendEvent event = new PlayerItemMendEvent(player, bukkitStack, CraftEquipmentSlot.getSlot(slot), (org.bukkit.entity.ExperienceOrb)((Object)orb.getBukkitEntity()), repairAmount);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static boolean handleBlockGrowEvent(Level world, BlockPos pos, BlockState block) {
        return CraftEventFactory.handleBlockGrowEvent(world, pos, block, 3);
    }

    public static boolean handleBlockGrowEvent(Level world, BlockPos pos, BlockState newData, int flag) {
        Block block = world.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
        CraftBlockState state = (CraftBlockState)block.getState();
        state.setData(newData);
        BlockGrowEvent event = new BlockGrowEvent(block, state);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            state.update(true);
        }
        return !event.isCancelled();
    }

    public static FluidLevelChangeEvent callFluidLevelChangeEvent(Level world, BlockPos block, BlockState newData) {
        FluidLevelChangeEvent event = new FluidLevelChangeEvent(CraftBlock.at((LevelAccessor)world, block), CraftBlockData.fromData(newData));
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static FoodLevelChangeEvent callFoodLevelChangeEvent(Player entity, int level) {
        return CraftEventFactory.callFoodLevelChangeEvent(entity, level, null);
    }

    public static FoodLevelChangeEvent callFoodLevelChangeEvent(Player entity, int level, ItemStack item) {
        FoodLevelChangeEvent event = new FoodLevelChangeEvent(entity.getBukkitEntity(), level, item == null ? null : CraftItemStack.asBukkitCopy(item));
        entity.getBukkitEntity().getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static PigZapEvent callPigZapEvent(net.minecraft.world.entity.Entity pig, net.minecraft.world.entity.Entity lightning, net.minecraft.world.entity.Entity pigzombie) {
        PigZapEvent event = new PigZapEvent((Pig)((Object)pig.getBukkitEntity()), (LightningStrike)((Object)lightning.getBukkitEntity()), (PigZombie)((Object)pigzombie.getBukkitEntity()));
        pig.getBukkitEntity().getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static boolean callHorseJumpEvent(net.minecraft.world.entity.Entity horse, float power) {
        HorseJumpEvent event = new HorseJumpEvent((AbstractHorse)((Object)horse.getBukkitEntity()), power);
        horse.getBukkitEntity().getServer().getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static boolean callEntityChangeBlockEvent(net.minecraft.world.entity.Entity entity, BlockPos position, BlockState newBlock) {
        return CraftEventFactory.callEntityChangeBlockEvent(entity, position, newBlock, false);
    }

    public static boolean callEntityChangeBlockEvent(net.minecraft.world.entity.Entity entity, BlockPos position, BlockState newBlock, boolean cancelled) {
        Block block = entity.m_9236_().getWorld().getBlockAt(position.m_123341_(), position.m_123342_(), position.m_123343_());
        EntityChangeBlockEvent event = new EntityChangeBlockEvent(entity.getBukkitEntity(), block, CraftBlockData.fromData(newBlock));
        event.setCancelled(cancelled);
        event.getEntity().getServer().getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static CreeperPowerEvent callCreeperPowerEvent(net.minecraft.world.entity.Entity creeper, net.minecraft.world.entity.Entity lightning, CreeperPowerEvent.PowerCause cause) {
        CreeperPowerEvent event = new CreeperPowerEvent((Creeper)((Object)creeper.getBukkitEntity()), (LightningStrike)((Object)lightning.getBukkitEntity()), cause);
        creeper.getBukkitEntity().getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static EntityTargetEvent callEntityTargetEvent(net.minecraft.world.entity.Entity entity, net.minecraft.world.entity.Entity target, EntityTargetEvent.TargetReason reason) {
        EntityTargetEvent event = new EntityTargetEvent(entity.getBukkitEntity(), target == null ? null : target.getBukkitEntity(), reason);
        entity.getBukkitEntity().getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static EntityTargetLivingEntityEvent callEntityTargetLivingEvent(net.minecraft.world.entity.Entity entity, net.minecraft.world.entity.LivingEntity target, EntityTargetEvent.TargetReason reason) {
        EntityTargetLivingEntityEvent event = new EntityTargetLivingEntityEvent((Entity)entity.getBukkitEntity(), target == null ? null : (LivingEntity)((Object)target.getBukkitEntity()), reason);
        entity.getBukkitEntity().getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static EntityBreakDoorEvent callEntityBreakDoorEvent(net.minecraft.world.entity.Entity entity, BlockPos pos) {
        CraftEntity entity1 = entity.getBukkitEntity();
        CraftBlock block = CraftBlock.at((LevelAccessor)entity.m_9236_(), pos);
        EntityBreakDoorEvent event = new EntityBreakDoorEvent((LivingEntity)((Object)entity1), block);
        entity1.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static AbstractContainerMenu callInventoryOpenEvent(ServerPlayer player, AbstractContainerMenu container) {
        return CraftEventFactory.callInventoryOpenEvent(player, container, false);
    }

    public static AbstractContainerMenu callInventoryOpenEvent(ServerPlayer player, AbstractContainerMenu container, boolean cancelled) {
        if (player.f_36096_ != player.f_36095_) {
            player.f_8906_.m_7951_(new ServerboundContainerClosePacket(player.f_36096_.f_38840_));
        }
        CraftServer server = player.m_9236_().getCraftServer();
        CraftPlayer craftPlayer = player.getBukkitEntity();
        player.f_36096_.transferTo(container, (CraftHumanEntity)craftPlayer);
        InventoryOpenEvent event = new InventoryOpenEvent(container.getBukkitView());
        event.setCancelled(cancelled);
        server.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            container.transferTo(player.f_36096_, (CraftHumanEntity)craftPlayer);
            return null;
        }
        return container;
    }

    public static ItemStack callPreCraftEvent(Container matrix, Container resultInventory, ItemStack result, InventoryView lastCraftView, boolean isRepair) {
        CraftInventoryCrafting inventory = new CraftInventoryCrafting(matrix, resultInventory);
        inventory.setResult(CraftItemStack.asCraftMirror(result));
        PrepareItemCraftEvent event = new PrepareItemCraftEvent(inventory, lastCraftView, isRepair);
        Bukkit.getPluginManager().callEvent(event);
        org.bukkit.inventory.ItemStack bitem = event.getInventory().getResult();
        return CraftItemStack.asNMSCopy(bitem);
    }

    public static ProjectileLaunchEvent callProjectileLaunchEvent(net.minecraft.world.entity.Entity entity) {
        org.bukkit.entity.Projectile bukkitEntity = (org.bukkit.entity.Projectile)((Object)entity.getBukkitEntity());
        ProjectileLaunchEvent event = new ProjectileLaunchEvent(bukkitEntity);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static ProjectileHitEvent callProjectileHitEvent(net.minecraft.world.entity.Entity entity, HitResult position) {
        if (position.m_6662_() == HitResult.Type.MISS) {
            return null;
        }
        CraftBlock hitBlock = null;
        BlockFace hitFace = null;
        if (position.m_6662_() == HitResult.Type.BLOCK) {
            BlockHitResult positionBlock = (BlockHitResult)position;
            hitBlock = CraftBlock.at((LevelAccessor)entity.m_9236_(), positionBlock.m_82425_());
            hitFace = CraftBlock.notchToBlockFace(positionBlock.m_82434_());
        }
        CraftEntity hitEntity = null;
        if (position.m_6662_() == HitResult.Type.ENTITY) {
            hitEntity = ((EntityHitResult)position).m_82443_().getBukkitEntity();
        }
        ProjectileHitEvent event = new ProjectileHitEvent((org.bukkit.entity.Projectile)((Object)entity.getBukkitEntity()), hitEntity, hitBlock, hitFace);
        entity.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static ExpBottleEvent callExpBottleEvent(net.minecraft.world.entity.Entity entity, int exp) {
        ThrownExpBottle bottle = (ThrownExpBottle)((Object)entity.getBukkitEntity());
        ExpBottleEvent event = new ExpBottleEvent(bottle, exp);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static BlockRedstoneEvent callRedstoneChange(Level world, BlockPos pos, int oldCurrent, int newCurrent) {
        BlockRedstoneEvent event = new BlockRedstoneEvent(world.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()), oldCurrent, newCurrent);
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static NotePlayEvent callNotePlayEvent(Level world, BlockPos pos, NoteBlockInstrument instrument, int note) {
        NotePlayEvent event = new NotePlayEvent(world.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()), Instrument.getByType((byte)instrument.ordinal()), new Note(note));
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static void callPlayerItemBreakEvent(Player human, ItemStack brokenItem) {
        CraftItemStack item = CraftItemStack.asCraftMirror(brokenItem);
        PlayerItemBreakEvent event = new PlayerItemBreakEvent((org.bukkit.entity.Player)((Object)human.getBukkitEntity()), item);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static BlockIgniteEvent callBlockIgniteEvent(Level world, BlockPos block, BlockPos source) {
        CraftWorld bukkitWorld = world.getWorld();
        Block igniter = bukkitWorld.getBlockAt(source.m_123341_(), source.m_123342_(), source.m_123343_());
        BlockIgniteEvent event = new BlockIgniteEvent(bukkitWorld.getBlockAt(block.m_123341_(), block.m_123342_(), block.m_123343_()), switch (igniter.getType()) {
            case Material.LAVA -> BlockIgniteEvent.IgniteCause.LAVA;
            case Material.DISPENSER -> BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL;
            default -> BlockIgniteEvent.IgniteCause.SPREAD;
        }, igniter);
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static BlockIgniteEvent callBlockIgniteEvent(Level world, BlockPos pos, net.minecraft.world.entity.Entity igniter) {
        net.minecraft.world.entity.Entity shooter;
        CraftWorld bukkitWorld = world.getWorld();
        CraftEntity bukkitIgniter = igniter.getBukkitEntity();
        BlockIgniteEvent.IgniteCause cause = switch (bukkitIgniter.getType()) {
            case EntityType.ENDER_CRYSTAL -> BlockIgniteEvent.IgniteCause.ENDER_CRYSTAL;
            case EntityType.LIGHTNING -> BlockIgniteEvent.IgniteCause.LIGHTNING;
            case EntityType.FIREBALL, EntityType.SMALL_FIREBALL -> BlockIgniteEvent.IgniteCause.FIREBALL;
            case EntityType.ARROW -> BlockIgniteEvent.IgniteCause.ARROW;
            default -> BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL;
        };
        if (igniter instanceof Projectile && (shooter = ((Projectile)igniter).m_19749_()) != null) {
            bukkitIgniter = shooter.getBukkitEntity();
        }
        BlockIgniteEvent event = new BlockIgniteEvent(bukkitWorld.getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()), cause, bukkitIgniter);
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static BlockIgniteEvent callBlockIgniteEvent(Level world, int x, int y, int z, Explosion explosion) {
        CraftWorld bukkitWorld = world.getWorld();
        CraftEntity igniter = explosion.f_46016_ == null ? null : explosion.f_46016_.getBukkitEntity();
        BlockIgniteEvent event = new BlockIgniteEvent(bukkitWorld.getBlockAt(x, y, z), BlockIgniteEvent.IgniteCause.EXPLOSION, igniter);
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static BlockIgniteEvent callBlockIgniteEvent(Level world, BlockPos pos, BlockIgniteEvent.IgniteCause cause, net.minecraft.world.entity.Entity igniter) {
        BlockIgniteEvent event = new BlockIgniteEvent(world.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_()), cause, igniter.getBukkitEntity());
        world.getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static void handleInventoryCloseEvent(Player human) {
        InventoryCloseEvent event = new InventoryCloseEvent(human.f_36096_.getBukkitView());
        human.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        human.f_36096_.transferTo((AbstractContainerMenu)human.f_36095_, human.getBukkitEntity());
    }

    public static ItemStack handleEditBookEvent(ServerPlayer player, int itemInHandIndex, ItemStack itemInHand, ItemStack newBookItem) {
        PlayerEditBookEvent editBookEvent = new PlayerEditBookEvent(player.getBukkitEntity(), itemInHandIndex >= 0 && itemInHandIndex <= 8 ? itemInHandIndex : -1, (BookMeta)CraftItemStack.getItemMeta(itemInHand), (BookMeta)CraftItemStack.getItemMeta(newBookItem), newBookItem.m_41720_() == Items.f_42615_);
        player.m_9236_().getCraftServer().getPluginManager().callEvent(editBookEvent);
        if (itemInHand != null && itemInHand.m_41720_() == Items.f_42614_ && !editBookEvent.isCancelled()) {
            if (editBookEvent.isSigning()) {
                itemInHand.setItem(Items.f_42615_);
            }
            CraftMetaBook meta = (CraftMetaBook)editBookEvent.getNewBookMeta();
            CraftItemStack.setItemMeta(itemInHand, meta);
        }
        return itemInHand;
    }

    public static PlayerUnleashEntityEvent callPlayerUnleashEntityEvent(Mob entity, Player player, InteractionHand enumhand) {
        PlayerUnleashEntityEvent event = new PlayerUnleashEntityEvent(entity.getBukkitEntity(), (org.bukkit.entity.Player)((Object)player.getBukkitEntity()), CraftEquipmentSlot.getHand(enumhand));
        entity.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static PlayerLeashEntityEvent callPlayerLeashEntityEvent(Mob entity, net.minecraft.world.entity.Entity leashHolder, Player player, InteractionHand enumhand) {
        PlayerLeashEntityEvent event = new PlayerLeashEntityEvent(entity.getBukkitEntity(), leashHolder.getBukkitEntity(), (org.bukkit.entity.Player)((Object)player.getBukkitEntity()), CraftEquipmentSlot.getHand(enumhand));
        entity.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static BlockShearEntityEvent callBlockShearEntityEvent(net.minecraft.world.entity.Entity animal, Block dispenser, CraftItemStack is) {
        BlockShearEntityEvent bse = new BlockShearEntityEvent(dispenser, animal.getBukkitEntity(), is);
        Bukkit.getPluginManager().callEvent(bse);
        return bse;
    }

    public static boolean handlePlayerShearEntityEvent(Player player, net.minecraft.world.entity.Entity sheared, ItemStack shears, InteractionHand hand) {
        if (!(player instanceof ServerPlayer)) {
            return true;
        }
        PlayerShearEntityEvent event = new PlayerShearEntityEvent((org.bukkit.entity.Player)((Object)player.getBukkitEntity()), sheared.getBukkitEntity(), CraftItemStack.asCraftMirror(shears), hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static Cancellable handleStatisticsIncrease(Player entityHuman, Stat<?> statistic, int current, int newValue) {
        PlayerStatisticIncrementEvent event;
        CraftPlayer player = ((ServerPlayer)entityHuman).getBukkitEntity();
        Statistic stat = CraftStatistic.getBukkitStatistic(statistic);
        if (stat == null) {
            System.err.println("Unhandled statistic: " + statistic);
            return null;
        }
        switch (stat) {
            case PLAY_ONE_MINUTE: 
            case TOTAL_WORLD_TIME: 
            case WALK_ONE_CM: 
            case WALK_ON_WATER_ONE_CM: 
            case FALL_ONE_CM: 
            case SNEAK_TIME: 
            case CLIMB_ONE_CM: 
            case FLY_ONE_CM: 
            case WALK_UNDER_WATER_ONE_CM: 
            case MINECART_ONE_CM: 
            case BOAT_ONE_CM: 
            case PIG_ONE_CM: 
            case HORSE_ONE_CM: 
            case SPRINT_ONE_CM: 
            case CROUCH_ONE_CM: 
            case AVIATE_ONE_CM: 
            case TIME_SINCE_DEATH: 
            case TIME_SINCE_REST: 
            case SWIM_ONE_CM: 
            case STRIDER_ONE_CM: {
                return null;
            }
        }
        if (stat.getType() == Statistic.Type.UNTYPED) {
            event = new PlayerStatisticIncrementEvent(player, stat, current, newValue);
        } else if (stat.getType() == Statistic.Type.ENTITY) {
            EntityType entityType = CraftStatistic.getEntityTypeFromStatistic(statistic);
            event = new PlayerStatisticIncrementEvent((org.bukkit.entity.Player)player, stat, current, newValue, entityType);
        } else {
            Material material = CraftStatistic.getMaterialFromStatistic(statistic);
            event = new PlayerStatisticIncrementEvent((org.bukkit.entity.Player)player, stat, current, newValue, material);
        }
        entityHuman.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static FireworkExplodeEvent callFireworkExplodeEvent(FireworkRocketEntity firework) {
        FireworkExplodeEvent event = new FireworkExplodeEvent((Firework)((Object)firework.getBukkitEntity()));
        firework.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static PrepareAnvilEvent callPrepareAnvilEvent(InventoryView view, ItemStack item) {
        PrepareAnvilEvent event = new PrepareAnvilEvent(view, CraftItemStack.asCraftMirror(item).clone());
        event.getView().getPlayer().getServer().getPluginManager().callEvent(event);
        event.getInventory().setItem(2, event.getResult());
        return event;
    }

    public static PrepareGrindstoneEvent callPrepareGrindstoneEvent(InventoryView view, ItemStack item) {
        PrepareGrindstoneEvent event = new PrepareGrindstoneEvent(view, CraftItemStack.asCraftMirror(item).clone());
        event.getView().getPlayer().getServer().getPluginManager().callEvent(event);
        event.getInventory().setItem(2, event.getResult());
        return event;
    }

    public static PrepareSmithingEvent callPrepareSmithingEvent(InventoryView view, ItemStack item) {
        PrepareSmithingEvent event = new PrepareSmithingEvent(view, CraftItemStack.asCraftMirror(item).clone());
        event.getView().getPlayer().getServer().getPluginManager().callEvent(event);
        event.getInventory().setResult(event.getResult());
        return event;
    }

    public static SpawnerSpawnEvent callSpawnerSpawnEvent(net.minecraft.world.entity.Entity spawnee, BlockPos pos) {
        CraftEntity entity = spawnee.getBukkitEntity();
        org.bukkit.block.BlockState state = CraftBlock.at((LevelAccessor)spawnee.m_9236_(), pos).getState();
        if (!(state instanceof CreatureSpawner)) {
            state = null;
        }
        SpawnerSpawnEvent event = new SpawnerSpawnEvent(entity, (CreatureSpawner)state);
        entity.getServer().getPluginManager().callEvent(event);
        return event;
    }

    public static EntityToggleGlideEvent callToggleGlideEvent(net.minecraft.world.entity.LivingEntity entity, boolean gliding) {
        EntityToggleGlideEvent event = new EntityToggleGlideEvent((LivingEntity)((Object)entity.getBukkitEntity()), gliding);
        entity.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static EntityToggleSwimEvent callToggleSwimEvent(net.minecraft.world.entity.LivingEntity entity, boolean swimming) {
        EntityToggleSwimEvent event = new EntityToggleSwimEvent((LivingEntity)((Object)entity.getBukkitEntity()), swimming);
        entity.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static AreaEffectCloudApplyEvent callAreaEffectCloudApplyEvent(AreaEffectCloud cloud, List<LivingEntity> entities) {
        AreaEffectCloudApplyEvent event = new AreaEffectCloudApplyEvent((org.bukkit.entity.AreaEffectCloud)((Object)cloud.getBukkitEntity()), entities);
        cloud.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static VehicleCreateEvent callVehicleCreateEvent(net.minecraft.world.entity.Entity entity) {
        Vehicle bukkitEntity = (Vehicle)((Object)entity.getBukkitEntity());
        VehicleCreateEvent event = new VehicleCreateEvent(bukkitEntity);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static EntityBreedEvent callEntityBreedEvent(net.minecraft.world.entity.LivingEntity child, net.minecraft.world.entity.LivingEntity mother, net.minecraft.world.entity.LivingEntity father, net.minecraft.world.entity.LivingEntity breeder, ItemStack bredWith, int experience) {
        LivingEntity breederEntity = (LivingEntity)((Object)(breeder == null ? null : breeder.getBukkitEntity()));
        CraftItemStack bredWithStack = bredWith == null ? null : CraftItemStack.asCraftMirror(bredWith).clone();
        EntityBreedEvent event = new EntityBreedEvent((LivingEntity)((Object)child.getBukkitEntity()), (LivingEntity)((Object)mother.getBukkitEntity()), (LivingEntity)((Object)father.getBukkitEntity()), breederEntity, bredWithStack, experience);
        child.m_9236_().getCraftServer().getPluginManager().callEvent(event);
        return event;
    }

    public static BlockPhysicsEvent callBlockPhysicsEvent(LevelAccessor world, BlockPos blockposition) {
        CraftBlock block = CraftBlock.at(world, blockposition);
        BlockPhysicsEvent event = new BlockPhysicsEvent(block, block.getBlockData());
        if (world instanceof Level) {
            ((Level)world).m_7654_().server.getPluginManager().callEvent(event);
        }
        return event;
    }

    public static boolean handleBlockFormEvent(Level world, BlockPos pos, BlockState block) {
        return CraftEventFactory.handleBlockFormEvent(world, pos, block, 3);
    }

    public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(net.minecraft.world.entity.LivingEntity entity, @Nullable MobEffectInstance oldEffect, @Nullable MobEffectInstance newEffect, EntityPotionEffectEvent.Cause cause) {
        return CraftEventFactory.callEntityPotionEffectChangeEvent(entity, oldEffect, newEffect, cause, true);
    }

    public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(net.minecraft.world.entity.LivingEntity entity, @Nullable MobEffectInstance oldEffect, @Nullable MobEffectInstance newEffect, EntityPotionEffectEvent.Cause cause, EntityPotionEffectEvent.Action action) {
        return CraftEventFactory.callEntityPotionEffectChangeEvent(entity, oldEffect, newEffect, cause, action, true);
    }

    public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(net.minecraft.world.entity.LivingEntity entity, @Nullable MobEffectInstance oldEffect, @Nullable MobEffectInstance newEffect, EntityPotionEffectEvent.Cause cause, boolean willOverride) {
        EntityPotionEffectEvent.Action action = EntityPotionEffectEvent.Action.CHANGED;
        if (oldEffect == null) {
            action = EntityPotionEffectEvent.Action.ADDED;
        } else if (newEffect == null) {
            action = EntityPotionEffectEvent.Action.REMOVED;
        }
        return CraftEventFactory.callEntityPotionEffectChangeEvent(entity, oldEffect, newEffect, cause, action, willOverride);
    }

    public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(net.minecraft.world.entity.LivingEntity entity, @Nullable MobEffectInstance oldEffect, @Nullable MobEffectInstance newEffect, EntityPotionEffectEvent.Cause cause, EntityPotionEffectEvent.Action action, boolean willOverride) {
        PotionEffect bukkitOldEffect = oldEffect == null ? null : CraftPotionUtil.toBukkit(oldEffect);
        PotionEffect bukkitNewEffect = newEffect == null ? null : CraftPotionUtil.toBukkit(newEffect);
        Preconditions.checkState((bukkitOldEffect != null || bukkitNewEffect != null ? 1 : 0) != 0, (Object)"Old and new potion effect are both null");
        EntityPotionEffectEvent event = new EntityPotionEffectEvent((LivingEntity)((Object)entity.getBukkitEntity()), bukkitOldEffect, bukkitNewEffect, cause, action, willOverride);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static boolean handleBlockFormEvent(Level world, BlockPos pos, BlockState block, @Nullable net.minecraft.world.entity.Entity entity) {
        return CraftEventFactory.handleBlockFormEvent(world, pos, block, 3, entity);
    }

    public static boolean handleBlockFormEvent(Level world, BlockPos pos, BlockState block, int flag) {
        return CraftEventFactory.handleBlockFormEvent(world, pos, block, flag, null);
    }

    public static boolean handleBlockFormEvent(Level world, BlockPos pos, BlockState block, int flag, @Nullable net.minecraft.world.entity.Entity entity) {
        CraftBlockState blockState = CraftBlockStates.getBlockState((LevelAccessor)world, pos, flag);
        blockState.setData(block);
        BlockFormEvent event = entity == null ? new BlockFormEvent(blockState.getBlock(), blockState) : new EntityBlockFormEvent(entity.getBukkitEntity(), blockState.getBlock(), blockState);
        world.getCraftServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            blockState.update(true);
        }
        return !event.isCancelled();
    }

    public static boolean handleBatToggleSleepEvent(net.minecraft.world.entity.Entity bat, boolean awake) {
        BatToggleSleepEvent event = new BatToggleSleepEvent((Bat)((Object)bat.getBukkitEntity()), awake);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static boolean handlePlayerRecipeListUpdateEvent(Player who, ResourceLocation recipe) {
        PlayerRecipeDiscoverEvent event = new PlayerRecipeDiscoverEvent((org.bukkit.entity.Player)((Object)who.getBukkitEntity()), CraftNamespacedKey.fromMinecraft(recipe));
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static EntityPickupItemEvent callEntityPickupItemEvent(net.minecraft.world.entity.Entity who, ItemEntity item, int remaining, boolean cancelled) {
        EntityPickupItemEvent event = new EntityPickupItemEvent((LivingEntity)((Object)who.getBukkitEntity()), (Item)((Object)item.getBukkitEntity()), remaining);
        event.setCancelled(cancelled);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static LightningStrikeEvent callLightningStrikeEvent(LightningStrike entity, LightningStrikeEvent.Cause cause) {
        LightningStrikeEvent event = new LightningStrikeEvent(entity.getWorld(), entity, cause);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static boolean callRaidTriggerEvent(Raid raid, ServerPlayer player) {
        RaidTriggerEvent event = new RaidTriggerEvent(new CraftRaid(raid), raid.m_37769_().getWorld(), player.getBukkitEntity());
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static void callRaidFinishEvent(Raid raid, List<org.bukkit.entity.Player> players) {
        RaidFinishEvent event = new RaidFinishEvent(new CraftRaid(raid), raid.m_37769_().getWorld(), players);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void callRaidStopEvent(Raid raid, RaidStopEvent.Reason reason) {
        RaidStopEvent event = new RaidStopEvent(new CraftRaid(raid), raid.m_37769_().getWorld(), reason);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void callRaidSpawnWaveEvent(Raid raid, Raider leader, List<Raider> raiders) {
        CraftRaider craftLeader = (CraftRaider)leader.getBukkitEntity();
        ArrayList<org.bukkit.entity.Raider> craftRaiders = new ArrayList<org.bukkit.entity.Raider>();
        for (Raider entityRaider : raiders) {
            craftRaiders.add((org.bukkit.entity.Raider)((Object)entityRaider.getBukkitEntity()));
        }
        RaidSpawnWaveEvent event = new RaidSpawnWaveEvent(new CraftRaid(raid), raid.m_37769_().getWorld(), craftLeader, craftRaiders);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static LootGenerateEvent callLootGenerateEvent(Container inventory, LootTable lootTable, LootContext lootInfo, List<ItemStack> loot, boolean plugin) {
        CraftWorld world = lootInfo.m_78952_().getWorld();
        net.minecraft.world.entity.Entity entity = (net.minecraft.world.entity.Entity)lootInfo.m_78953_(LootContextParams.f_81455_);
        NamespacedKey key = CraftNamespacedKey.fromMinecraft((ResourceLocation)world.getHandle().m_7654_().m_278653_().lootTableToKey.get(lootTable));
        CraftLootTable craftLootTable = new CraftLootTable(key, lootTable);
        List bukkitLoot = loot.stream().map(CraftItemStack::asCraftMirror).collect(Collectors.toCollection(ArrayList::new));
        LootGenerateEvent event = new LootGenerateEvent(world, entity != null ? entity.getBukkitEntity() : null, inventory.getOwner(), craftLootTable, CraftLootTable.convertContext(lootInfo), bukkitLoot, plugin);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static boolean callStriderTemperatureChangeEvent(Strider strider, boolean shivering) {
        StriderTemperatureChangeEvent event = new StriderTemperatureChangeEvent((org.bukkit.entity.Strider)((Object)strider.getBukkitEntity()), shivering);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static boolean handleEntitySpellCastEvent(SpellcasterIllager caster, SpellcasterIllager.IllagerSpell spell) {
        EntitySpellCastEvent event = new EntitySpellCastEvent((Spellcaster)((Object)caster.getBukkitEntity()), CraftSpellcaster.toBukkitSpell(spell));
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static ArrowBodyCountChangeEvent callArrowBodyCountChangeEvent(net.minecraft.world.entity.LivingEntity entity, int oldAmount, int newAmount, boolean isReset) {
        LivingEntity bukkitEntity = (LivingEntity)((Object)entity.getBukkitEntity());
        ArrowBodyCountChangeEvent event = new ArrowBodyCountChangeEvent(bukkitEntity, oldAmount, newAmount, isReset);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static EntityExhaustionEvent callPlayerExhaustionEvent(Player humanEntity, EntityExhaustionEvent.ExhaustionReason exhaustionReason, float exhaustion) {
        EntityExhaustionEvent event = new EntityExhaustionEvent(humanEntity.getBukkitEntity(), exhaustionReason, exhaustion);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static PiglinBarterEvent callPiglinBarterEvent(net.minecraft.world.entity.monster.piglin.Piglin piglin, List<ItemStack> outcome, ItemStack input) {
        PiglinBarterEvent event = new PiglinBarterEvent((Piglin)((Object)piglin.getBukkitEntity()), CraftItemStack.asBukkitCopy(input), outcome.stream().map(CraftItemStack::asBukkitCopy).collect(Collectors.toList()));
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    /*
     * WARNING - void declaration
     */
    public static void callEntitiesLoadEvent(Level level, ChunkPos chunkPos, List<net.minecraft.world.entity.Entity> list) {
        void coords;
        Level world;
        void entities;
        if (EntitiesLoadEvent.getHandlerList().getRegisteredListeners().length == 0) {
            return;
        }
        List<Entity> bukkitEntities = Collections.unmodifiableList(entities.stream().map(net.minecraft.world.entity.Entity::getBukkitEntity).collect(Collectors.toList()));
        EntitiesLoadEvent event = new EntitiesLoadEvent(new CraftChunk((ServerLevel)world, coords.f_45578_, coords.f_45579_), bukkitEntities);
        Bukkit.getPluginManager().callEvent(event);
    }

    /*
     * WARNING - void declaration
     */
    public static void callEntitiesUnloadEvent(Level level, ChunkPos chunkPos, List<net.minecraft.world.entity.Entity> list) {
        void coords;
        Level world;
        void entities;
        if (EntitiesUnloadEvent.getHandlerList().getRegisteredListeners().length == 0) {
            return;
        }
        List<Entity> bukkitEntities = Collections.unmodifiableList(entities.stream().map(net.minecraft.world.entity.Entity::getBukkitEntity).collect(Collectors.toList()));
        EntitiesUnloadEvent event = new EntitiesUnloadEvent(new CraftChunk((ServerLevel)world, coords.f_45578_, coords.f_45579_), bukkitEntities);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static boolean callTNTPrimeEvent(Level world, BlockPos pos, TNTPrimeEvent.PrimeCause cause, net.minecraft.world.entity.Entity causingEntity, BlockPos causePosition) {
        CraftEntity bukkitEntity = causingEntity == null ? null : causingEntity.getBukkitEntity();
        CraftBlock bukkitBlock = causePosition == null ? null : CraftBlock.at((LevelAccessor)world, causePosition);
        TNTPrimeEvent event = new TNTPrimeEvent(CraftBlock.at((LevelAccessor)world, pos), cause, bukkitEntity, bukkitBlock);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static PlayerRecipeBookClickEvent callRecipeBookClickEvent(ServerPlayer player, Recipe recipe, boolean shiftClick) {
        PlayerRecipeBookClickEvent event = new PlayerRecipeBookClickEvent(player.getBukkitEntity(), recipe, shiftClick);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static EntityTeleportEvent callEntityTeleportEvent(net.minecraft.world.entity.Entity nmsEntity, double x, double y, double z) {
        CraftEntity entity = nmsEntity.getBukkitEntity();
        Location to = new Location(entity.getWorld(), x, y, z, nmsEntity.m_146908_(), nmsEntity.m_146909_());
        EntityTeleportEvent event = new EntityTeleportEvent(entity, entity.getLocation(), to);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static boolean callEntityInteractEvent(net.minecraft.world.entity.Entity nmsEntity, Block block) {
        EntityInteractEvent event = new EntityInteractEvent(nmsEntity.getBukkitEntity(), block);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    public static ExplosionPrimeEvent callExplosionPrimeEvent(Explosive explosive) {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(explosive);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static ExplosionPrimeEvent callExplosionPrimeEvent(net.minecraft.world.entity.Entity nmsEntity, float size, boolean fire) {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(nmsEntity.getBukkitEntity(), size, fire);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }
}

