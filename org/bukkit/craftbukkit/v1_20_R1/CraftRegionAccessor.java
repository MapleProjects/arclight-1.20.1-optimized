/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.data.worldgen.features.TreeFeatures
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.entity.decoration.GlowItemFrame
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.entity.decoration.LeashFenceKnotEntity
 *  net.minecraft.world.entity.decoration.Painting
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.EvokerFangs
 *  net.minecraft.world.entity.projectile.EyeOfEnder
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.ThrownEgg
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.entity.vehicle.Minecart
 *  net.minecraft.world.entity.vehicle.MinecartChest
 *  net.minecraft.world.entity.vehicle.MinecartCommandBlock
 *  net.minecraft.world.entity.vehicle.MinecartFurnace
 *  net.minecraft.world.entity.vehicle.MinecartHopper
 *  net.minecraft.world.entity.vehicle.MinecartSpawner
 *  net.minecraft.world.entity.vehicle.MinecartTNT
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.ChorusFlowerBlock
 *  net.minecraft.world.level.block.DiodeBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.phys.AABB
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.AABB;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.RegionAccessor;
import org.bukkit.TreeType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftHeightMap;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.BlockStateListPopulator;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.RandomSourceWrapper;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AbstractSkeleton;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Camel;
import org.bukkit.entity.Cat;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.ChestBoat;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cod;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Display;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Egg;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.GlowSquid;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Golem;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illager;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LingeringPotion;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Mule;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Slime;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Sniffer;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Strider;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Tadpole;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Trident;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Warden;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zoglin;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

public abstract class CraftRegionAccessor
implements RegionAccessor {
    public abstract WorldGenLevel getHandle();

    public boolean isNormalWorld() {
        return this.getHandle() instanceof ServerLevel;
    }

    @Override
    public org.bukkit.block.Biome getBiome(Location location) {
        return this.getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public org.bukkit.block.Biome getBiome(int x, int y, int z) {
        return CraftBlock.biomeBaseToBiome((Registry<Biome>)this.getHandle().m_9598_().m_175515_(Registries.f_256952_), (Holder<Biome>)this.getHandle().m_203495_(x >> 2, y >> 2, z >> 2));
    }

    @Override
    public void setBiome(Location location, org.bukkit.block.Biome biome) {
        this.setBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ(), biome);
    }

    @Override
    public void setBiome(int x, int y, int z, org.bukkit.block.Biome biome) {
        Preconditions.checkArgument((biome != org.bukkit.block.Biome.CUSTOM ? 1 : 0) != 0, (String)"Cannot set the biome to %s", (Object)biome);
        Holder<Biome> biomeBase = CraftBlock.biomeToBiomeBase((Registry<Biome>)this.getHandle().m_9598_().m_175515_(Registries.f_256952_), biome);
        this.setBiome(x, y, z, biomeBase);
    }

    public abstract void setBiome(int var1, int var2, int var3, Holder<Biome> var4);

    @Override
    public org.bukkit.block.BlockState getBlockState(Location location) {
        return this.getBlockState(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public org.bukkit.block.BlockState getBlockState(int x, int y, int z) {
        return CraftBlock.at((LevelAccessor)this.getHandle(), new BlockPos(x, y, z)).getState();
    }

    @Override
    public BlockData getBlockData(Location location) {
        return this.getBlockData(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public BlockData getBlockData(int x, int y, int z) {
        return CraftBlockData.fromData(this.getData(x, y, z));
    }

    @Override
    public Material getType(Location location) {
        return this.getType(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Material getType(int x, int y, int z) {
        return CraftMagicNumbers.getMaterial(this.getData(x, y, z).m_60734_());
    }

    private BlockState getData(int x, int y, int z) {
        return this.getHandle().m_8055_(new BlockPos(x, y, z));
    }

    @Override
    public void setBlockData(Location location, BlockData blockData) {
        this.setBlockData(location.getBlockX(), location.getBlockY(), location.getBlockZ(), blockData);
    }

    @Override
    public void setBlockData(int x, int y, int z, BlockData blockData) {
        WorldGenLevel world = this.getHandle();
        BlockPos pos = new BlockPos(x, y, z);
        BlockState old = this.getHandle().m_8055_(pos);
        CraftBlock.setTypeAndData((LevelAccessor)world, pos, old, ((CraftBlockData)blockData).getState(), true);
    }

    @Override
    public void setType(Location location, Material material) {
        this.setType(location.getBlockX(), location.getBlockY(), location.getBlockZ(), material);
    }

    @Override
    public void setType(int x, int y, int z, Material material) {
        this.setBlockData(x, y, z, material.createBlockData());
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        return this.getHighestBlockYAt(x, z, HeightMap.MOTION_BLOCKING);
    }

    @Override
    public int getHighestBlockYAt(Location location) {
        return this.getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public int getHighestBlockYAt(int x, int z, HeightMap heightMap) {
        return this.getHandle().m_6924_(CraftHeightMap.toNMS(heightMap), x, z);
    }

    @Override
    public int getHighestBlockYAt(Location location, HeightMap heightMap) {
        return this.getHighestBlockYAt(location.getBlockX(), location.getBlockZ(), heightMap);
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType) {
        BlockPos pos = CraftLocation.toBlockPosition(location);
        return this.generateTree(this.getHandle(), this.getHandle().getMinecraftWorld().m_7726_().m_8481_(), pos, new RandomSourceWrapper(random), treeType);
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType, Consumer<org.bukkit.block.BlockState> consumer) {
        return this.generateTree(location, random, treeType, consumer == null ? null : block -> {
            consumer.accept((org.bukkit.block.BlockState)block);
            return true;
        });
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType, Predicate<org.bukkit.block.BlockState> predicate) {
        BlockPos pos = CraftLocation.toBlockPosition(location);
        BlockStateListPopulator populator = new BlockStateListPopulator((LevelAccessor)this.getHandle());
        boolean result = this.generateTree(populator, this.getHandle().getMinecraftWorld().m_7726_().m_8481_(), pos, new RandomSourceWrapper(random), treeType);
        populator.refreshTiles();
        for (org.bukkit.block.BlockState blockState : populator.getList()) {
            if (predicate != null && !predicate.test(blockState)) continue;
            blockState.update(true, true);
        }
        return result;
    }

    public boolean generateTree(WorldGenLevel access, ChunkGenerator chunkGenerator, BlockPos pos, RandomSource random, TreeType treeType) {
        ResourceKey gen;
        switch (treeType) {
            case BIG_TREE: {
                gen = TreeFeatures.f_195130_;
                break;
            }
            case BIRCH: {
                gen = TreeFeatures.f_195125_;
                break;
            }
            case REDWOOD: {
                gen = TreeFeatures.f_195127_;
                break;
            }
            case TALL_REDWOOD: {
                gen = TreeFeatures.f_195128_;
                break;
            }
            case JUNGLE: {
                gen = TreeFeatures.f_195132_;
                break;
            }
            case SMALL_JUNGLE: {
                gen = TreeFeatures.f_195131_;
                break;
            }
            case COCOA_TREE: {
                gen = TreeFeatures.f_195129_;
                break;
            }
            case JUNGLE_BUSH: {
                gen = TreeFeatures.f_195138_;
                break;
            }
            case RED_MUSHROOM: {
                gen = TreeFeatures.f_195122_;
                break;
            }
            case BROWN_MUSHROOM: {
                gen = TreeFeatures.f_195121_;
                break;
            }
            case SWAMP: {
                gen = TreeFeatures.f_195137_;
                break;
            }
            case ACACIA: {
                gen = TreeFeatures.f_195126_;
                break;
            }
            case DARK_OAK: {
                gen = TreeFeatures.f_195124_;
                break;
            }
            case MEGA_REDWOOD: {
                gen = TreeFeatures.f_195134_;
                break;
            }
            case TALL_BIRCH: {
                gen = TreeFeatures.f_195135_;
                break;
            }
            case CHORUS_PLANT: {
                ChorusFlowerBlock cfr_ignored_0 = (ChorusFlowerBlock)Blocks.f_50491_;
                ChorusFlowerBlock.m_220962_((LevelAccessor)access, (BlockPos)pos, (RandomSource)random, (int)8);
                return true;
            }
            case CRIMSON_FUNGUS: {
                gen = TreeFeatures.f_195118_;
                break;
            }
            case WARPED_FUNGUS: {
                gen = TreeFeatures.f_195120_;
                break;
            }
            case AZALEA: {
                gen = TreeFeatures.f_195139_;
                break;
            }
            case MANGROVE: {
                gen = TreeFeatures.f_236762_;
                break;
            }
            case TALL_MANGROVE: {
                gen = TreeFeatures.f_236763_;
                break;
            }
            case CHERRY: {
                gen = TreeFeatures.f_271469_;
                break;
            }
            default: {
                gen = TreeFeatures.f_195123_;
            }
        }
        Holder holder = access.m_9598_().m_175515_(Registries.f_256911_).m_203636_(gen).orElse(null);
        return holder != null ? ((ConfiguredFeature)holder.m_203334_()).m_224953_(access, chunkGenerator, random, pos) : false;
    }

    @Override
    public Entity spawnEntity(Location location, org.bukkit.entity.EntityType entityType) {
        return this.spawn(location, entityType.getEntityClass());
    }

    @Override
    public Entity spawnEntity(Location loc, org.bukkit.entity.EntityType type, boolean randomizeData) {
        return this.spawn(loc, type.getEntityClass(), null, CreatureSpawnEvent.SpawnReason.CUSTOM, randomizeData);
    }

    @Override
    public List<Entity> getEntities() {
        ArrayList<Entity> list = new ArrayList<Entity>();
        this.getNMSEntities().forEach(entity -> {
            CraftEntity bukkitEntity = entity.getBukkitEntity();
            if (bukkitEntity != null && (!this.isNormalWorld() || bukkitEntity.isValid())) {
                list.add(bukkitEntity);
            }
        });
        return list;
    }

    @Override
    public List<LivingEntity> getLivingEntities() {
        ArrayList<LivingEntity> list = new ArrayList<LivingEntity>();
        this.getNMSEntities().forEach(entity -> {
            CraftEntity bukkitEntity = entity.getBukkitEntity();
            if (bukkitEntity != null && bukkitEntity instanceof LivingEntity && (!this.isNormalWorld() || bukkitEntity.isValid())) {
                list.add((LivingEntity)((Object)bukkitEntity));
            }
        });
        return list;
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> clazz) {
        ArrayList list = new ArrayList();
        this.getNMSEntities().forEach(entity -> {
            CraftEntity bukkitEntity = entity.getBukkitEntity();
            if (bukkitEntity == null) {
                return;
            }
            Class<?> bukkitClass = bukkitEntity.getClass();
            if (clazz.isAssignableFrom(bukkitClass) && (!this.isNormalWorld() || bukkitEntity.isValid())) {
                list.add(bukkitEntity);
            }
        });
        return list;
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?> ... classes) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        this.getNMSEntities().forEach(entity -> {
            CraftEntity bukkitEntity = entity.getBukkitEntity();
            if (bukkitEntity == null) {
                return;
            }
            Class<?> bukkitClass = bukkitEntity.getClass();
            Class[] classArray2 = classes;
            int n = classes.length;
            int n2 = 0;
            while (n2 < n) {
                Class clazz = classArray2[n2];
                if (clazz.isAssignableFrom(bukkitClass)) {
                    if (this.isNormalWorld() && !bukkitEntity.isValid()) break;
                    list.add(bukkitEntity);
                    break;
                }
                ++n2;
            }
        });
        return list;
    }

    public abstract Iterable<net.minecraft.world.entity.Entity> getNMSEntities();

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
        return this.spawn(location, clazz, null, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function) throws IllegalArgumentException {
        return this.spawn(location, clazz, function, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz, boolean randomizeData, Consumer<T> function) throws IllegalArgumentException {
        return this.spawn(location, clazz, function, CreatureSpawnEvent.SpawnReason.CUSTOM, randomizeData);
    }

    public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function, CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
        return this.spawn(location, clazz, function, reason, true);
    }

    public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function, CreatureSpawnEvent.SpawnReason reason, boolean randomizeData) throws IllegalArgumentException {
        net.minecraft.world.entity.Entity entity = this.createEntity(location, clazz, randomizeData);
        return this.addEntity(entity, reason, function, randomizeData);
    }

    public <T extends Entity> T addEntity(net.minecraft.world.entity.Entity entity, CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
        return this.addEntity(entity, reason, null, true);
    }

    public <T extends Entity> T addEntity(net.minecraft.world.entity.Entity entity, CreatureSpawnEvent.SpawnReason reason, Consumer<T> function, boolean randomizeData) throws IllegalArgumentException {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Cannot spawn null entity");
        if (randomizeData && entity instanceof Mob) {
            ((Mob)entity).m_6518_((ServerLevelAccessor)this.getHandle(), this.getHandle().m_6436_(entity.m_20183_()), MobSpawnType.COMMAND, null, null);
        }
        if (!this.isNormalWorld()) {
            entity.generation = true;
        }
        if (function != null) {
            function.accept(entity.getBukkitEntity());
        }
        this.addEntityToWorld(entity, reason);
        return (T)entity.getBukkitEntity();
    }

    public abstract void addEntityToWorld(net.minecraft.world.entity.Entity var1, CreatureSpawnEvent.SpawnReason var2);

    public net.minecraft.world.entity.Entity createEntity(Location location, Class<? extends Entity> clazz) throws IllegalArgumentException {
        return this.createEntity(location, clazz, true);
    }

    public net.minecraft.world.entity.Entity createEntity(Location location, Class<? extends Entity> clazz, boolean randomizeData) throws IllegalArgumentException {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Entity class cannot be null");
        Object entity = null;
        ServerLevel world = this.getHandle().getMinecraftWorld();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();
        if (Boat.class.isAssignableFrom(clazz)) {
            entity = ChestBoat.class.isAssignableFrom(clazz) ? EntityType.f_217016_.m_20615_((Level)world) : EntityType.f_20552_.m_20615_((Level)world);
            entity.m_7678_(x, y, z, yaw, pitch);
        } else if (FallingBlock.class.isAssignableFrom(clazz)) {
            BlockPos pos = BlockPos.m_274561_((double)x, (double)y, (double)z);
            entity = FallingBlockEntity.m_201971_((Level)world, (BlockPos)pos, (BlockState)this.getHandle().m_8055_(pos));
        } else if (Projectile.class.isAssignableFrom(clazz)) {
            if (org.bukkit.entity.Snowball.class.isAssignableFrom(clazz)) {
                entity = new Snowball((Level)world, x, y, z);
            } else if (Egg.class.isAssignableFrom(clazz)) {
                entity = new ThrownEgg((Level)world, x, y, z);
            } else if (AbstractArrow.class.isAssignableFrom(clazz)) {
                if (TippedArrow.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20548_.m_20615_((Level)world);
                    ((Arrow)((Object)entity.getBukkitEntity())).setBasePotionData(new PotionData(PotionType.WATER, false, false));
                } else {
                    entity = SpectralArrow.class.isAssignableFrom(clazz) ? EntityType.f_20478_.m_20615_((Level)world) : (Trident.class.isAssignableFrom(clazz) ? EntityType.f_20487_.m_20615_((Level)world) : EntityType.f_20548_.m_20615_((Level)world));
                }
                entity.m_7678_(x, y, z, 0.0f, 0.0f);
            } else if (ThrownExpBottle.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20485_.m_20615_((Level)world);
                entity.m_7678_(x, y, z, 0.0f, 0.0f);
            } else if (EnderPearl.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20484_.m_20615_((Level)world);
                entity.m_7678_(x, y, z, 0.0f, 0.0f);
            } else if (ThrownPotion.class.isAssignableFrom(clazz)) {
                if (LingeringPotion.class.isAssignableFrom(clazz)) {
                    entity = new net.minecraft.world.entity.projectile.ThrownPotion((Level)world, x, y, z);
                    ((net.minecraft.world.entity.projectile.ThrownPotion)entity).m_37446_(CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.LINGERING_POTION, 1)));
                } else {
                    entity = new net.minecraft.world.entity.projectile.ThrownPotion((Level)world, x, y, z);
                    ((net.minecraft.world.entity.projectile.ThrownPotion)entity).m_37446_(CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.SPLASH_POTION, 1)));
                }
            } else if (Fireball.class.isAssignableFrom(clazz)) {
                entity = SmallFireball.class.isAssignableFrom(clazz) ? EntityType.f_20527_.m_20615_((Level)world) : (WitherSkull.class.isAssignableFrom(clazz) ? EntityType.f_20498_.m_20615_((Level)world) : (DragonFireball.class.isAssignableFrom(clazz) ? EntityType.f_20561_.m_20615_((Level)world) : EntityType.f_20463_.m_20615_((Level)world)));
                entity.m_7678_(x, y, z, yaw, pitch);
                Vector direction = location.getDirection().multiply(10);
                ((AbstractHurtingProjectile)entity).setDirection(direction.getX(), direction.getY(), direction.getZ());
            } else if (ShulkerBullet.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20522_.m_20615_((Level)world);
                entity.m_7678_(x, y, z, yaw, pitch);
            } else if (LlamaSpit.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20467_.m_20615_((Level)world);
                entity.m_7678_(x, y, z, yaw, pitch);
            } else if (Firework.class.isAssignableFrom(clazz)) {
                entity = new FireworkRocketEntity((Level)world, x, y, z, ItemStack.f_41583_);
            }
        } else if (org.bukkit.entity.Minecart.class.isAssignableFrom(clazz)) {
            entity = PoweredMinecart.class.isAssignableFrom(clazz) ? new MinecartFurnace((Level)world, x, y, z) : (StorageMinecart.class.isAssignableFrom(clazz) ? new MinecartChest((Level)world, x, y, z) : (ExplosiveMinecart.class.isAssignableFrom(clazz) ? new MinecartTNT((Level)world, x, y, z) : (HopperMinecart.class.isAssignableFrom(clazz) ? new MinecartHopper((Level)world, x, y, z) : (SpawnerMinecart.class.isAssignableFrom(clazz) ? new MinecartSpawner((Level)world, x, y, z) : (CommandMinecart.class.isAssignableFrom(clazz) ? new MinecartCommandBlock((Level)world, x, y, z) : new Minecart((Level)world, x, y, z))))));
        } else if (EnderSignal.class.isAssignableFrom(clazz)) {
            entity = new EyeOfEnder((Level)world, x, y, z);
        } else if (EnderCrystal.class.isAssignableFrom(clazz)) {
            entity = EntityType.f_20564_.m_20615_((Level)world);
            entity.m_7678_(x, y, z, 0.0f, 0.0f);
        } else if (LivingEntity.class.isAssignableFrom(clazz)) {
            if (Chicken.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20555_.m_20615_((Level)world);
            } else if (Cow.class.isAssignableFrom(clazz)) {
                entity = MushroomCow.class.isAssignableFrom(clazz) ? EntityType.f_20504_.m_20615_((Level)world) : EntityType.f_20557_.m_20615_((Level)world);
            } else if (Golem.class.isAssignableFrom(clazz)) {
                if (Snowman.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20528_.m_20615_((Level)world);
                } else if (IronGolem.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20460_.m_20615_((Level)world);
                } else if (Shulker.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20521_.m_20615_((Level)world);
                }
            } else if (Creeper.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20558_.m_20615_((Level)world);
            } else if (Ghast.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20453_.m_20615_((Level)world);
            } else if (Pig.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_20510_.m_20615_((Level)world);
            } else if (!Player.class.isAssignableFrom(clazz)) {
                if (Sheep.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20520_.m_20615_((Level)world);
                } else if (AbstractHorse.class.isAssignableFrom(clazz)) {
                    if (ChestedHorse.class.isAssignableFrom(clazz)) {
                        if (Donkey.class.isAssignableFrom(clazz)) {
                            entity = EntityType.f_20560_.m_20615_((Level)world);
                        } else if (Mule.class.isAssignableFrom(clazz)) {
                            entity = EntityType.f_20503_.m_20615_((Level)world);
                        } else if (Llama.class.isAssignableFrom(clazz)) {
                            entity = TraderLlama.class.isAssignableFrom(clazz) ? EntityType.f_20488_.m_20615_((Level)world) : EntityType.f_20466_.m_20615_((Level)world);
                        }
                    } else {
                        entity = SkeletonHorse.class.isAssignableFrom(clazz) ? EntityType.f_20525_.m_20615_((Level)world) : (ZombieHorse.class.isAssignableFrom(clazz) ? EntityType.f_20502_.m_20615_((Level)world) : (Camel.class.isAssignableFrom(clazz) ? EntityType.f_243976_.m_20615_((Level)world) : EntityType.f_20457_.m_20615_((Level)world)));
                    }
                } else if (AbstractSkeleton.class.isAssignableFrom(clazz)) {
                    if (Stray.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20481_.m_20615_((Level)world);
                    } else if (WitherSkeleton.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20497_.m_20615_((Level)world);
                    } else if (Skeleton.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20524_.m_20615_((Level)world);
                    }
                } else if (Slime.class.isAssignableFrom(clazz)) {
                    entity = MagmaCube.class.isAssignableFrom(clazz) ? EntityType.f_20468_.m_20615_((Level)world) : EntityType.f_20526_.m_20615_((Level)world);
                } else if (Spider.class.isAssignableFrom(clazz)) {
                    entity = CaveSpider.class.isAssignableFrom(clazz) ? EntityType.f_20554_.m_20615_((Level)world) : EntityType.f_20479_.m_20615_((Level)world);
                } else if (Squid.class.isAssignableFrom(clazz)) {
                    entity = GlowSquid.class.isAssignableFrom(clazz) ? EntityType.f_147034_.m_20615_((Level)world) : EntityType.f_20480_.m_20615_((Level)world);
                } else if (Tameable.class.isAssignableFrom(clazz)) {
                    if (Wolf.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20499_.m_20615_((Level)world);
                    } else if (Parrot.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20508_.m_20615_((Level)world);
                    } else if (Cat.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20553_.m_20615_((Level)world);
                    }
                } else if (PigZombie.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20531_.m_20615_((Level)world);
                } else if (org.bukkit.entity.Zombie.class.isAssignableFrom(clazz)) {
                    entity = Husk.class.isAssignableFrom(clazz) ? EntityType.f_20458_.m_20615_((Level)world) : (ZombieVillager.class.isAssignableFrom(clazz) ? EntityType.f_20530_.m_20615_((Level)world) : (Drowned.class.isAssignableFrom(clazz) ? EntityType.f_20562_.m_20615_((Level)world) : new Zombie((Level)world)));
                } else if (Giant.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20454_.m_20615_((Level)world);
                } else if (Silverfish.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20523_.m_20615_((Level)world);
                } else if (Enderman.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20566_.m_20615_((Level)world);
                } else if (Blaze.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20551_.m_20615_((Level)world);
                } else if (AbstractVillager.class.isAssignableFrom(clazz)) {
                    if (Villager.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20492_.m_20615_((Level)world);
                    } else if (WanderingTrader.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20494_.m_20615_((Level)world);
                    }
                } else if (Witch.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20495_.m_20615_((Level)world);
                } else if (Wither.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20496_.m_20615_((Level)world);
                } else if (ComplexLivingEntity.class.isAssignableFrom(clazz)) {
                    if (EnderDragon.class.isAssignableFrom(clazz)) {
                        Preconditions.checkArgument((boolean)this.isNormalWorld(), (String)"Cannot spawn entity %s during world generation", (Object)clazz.getName());
                        entity = EntityType.f_20565_.m_20615_((Level)this.getHandle().getMinecraftWorld());
                    }
                } else if (Ambient.class.isAssignableFrom(clazz)) {
                    if (Bat.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20549_.m_20615_((Level)world);
                    }
                } else if (Rabbit.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20517_.m_20615_((Level)world);
                } else if (Endermite.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20567_.m_20615_((Level)world);
                } else if (Guardian.class.isAssignableFrom(clazz)) {
                    entity = ElderGuardian.class.isAssignableFrom(clazz) ? EntityType.f_20563_.m_20615_((Level)world) : EntityType.f_20455_.m_20615_((Level)world);
                } else if (org.bukkit.entity.ArmorStand.class.isAssignableFrom(clazz)) {
                    entity = new ArmorStand((Level)world, x, y, z);
                } else if (PolarBear.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20514_.m_20615_((Level)world);
                } else if (Vex.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20491_.m_20615_((Level)world);
                } else if (Illager.class.isAssignableFrom(clazz)) {
                    if (Spellcaster.class.isAssignableFrom(clazz)) {
                        if (Evoker.class.isAssignableFrom(clazz)) {
                            entity = EntityType.f_20568_.m_20615_((Level)world);
                        } else if (Illusioner.class.isAssignableFrom(clazz)) {
                            entity = EntityType.f_20459_.m_20615_((Level)world);
                        }
                    } else if (Vindicator.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20493_.m_20615_((Level)world);
                    } else if (Pillager.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20513_.m_20615_((Level)world);
                    }
                } else if (Turtle.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20490_.m_20615_((Level)world);
                } else if (Phantom.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20509_.m_20615_((Level)world);
                } else if (Fish.class.isAssignableFrom(clazz)) {
                    if (Cod.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20556_.m_20615_((Level)world);
                    } else if (PufferFish.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20516_.m_20615_((Level)world);
                    } else if (Salmon.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20519_.m_20615_((Level)world);
                    } else if (TropicalFish.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_20489_.m_20615_((Level)world);
                    } else if (Tadpole.class.isAssignableFrom(clazz)) {
                        entity = EntityType.f_217013_.m_20615_((Level)world);
                    }
                } else if (Dolphin.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20559_.m_20615_((Level)world);
                } else if (Ocelot.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20505_.m_20615_((Level)world);
                } else if (Ravager.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20518_.m_20615_((Level)world);
                } else if (Panda.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20507_.m_20615_((Level)world);
                } else if (Fox.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20452_.m_20615_((Level)world);
                } else if (Bee.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20550_.m_20615_((Level)world);
                } else if (Hoglin.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20456_.m_20615_((Level)world);
                } else if (Piglin.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20511_.m_20615_((Level)world);
                } else if (PiglinBrute.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20512_.m_20615_((Level)world);
                } else if (Strider.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20482_.m_20615_((Level)world);
                } else if (Zoglin.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_20500_.m_20615_((Level)world);
                } else if (Axolotl.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_147039_.m_20615_((Level)world);
                } else if (Goat.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_147035_.m_20615_((Level)world);
                } else if (Allay.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_217014_.m_20615_((Level)world);
                } else if (Frog.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_217012_.m_20615_((Level)world);
                } else if (Warden.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_217015_.m_20615_((Level)world);
                } else if (Sniffer.class.isAssignableFrom(clazz)) {
                    entity = EntityType.f_271264_.m_20615_((Level)world);
                }
            }
            if (entity != null) {
                entity.m_19890_(x, y, z, yaw, pitch);
                entity.m_5616_(yaw);
            }
        } else if (Hanging.class.isAssignableFrom(clazz)) {
            if (LeashHitch.class.isAssignableFrom(clazz)) {
                entity = new LeashFenceKnotEntity((Level)world, BlockPos.m_274561_((double)x, (double)y, (double)z));
            } else {
                BlockFace dir;
                BlockFace face = BlockFace.SELF;
                BlockFace[] faces = new BlockFace[]{BlockFace.EAST, BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH};
                int width = 16;
                int height = 16;
                if (org.bukkit.entity.ItemFrame.class.isAssignableFrom(clazz)) {
                    width = 12;
                    height = 12;
                    faces = new BlockFace[]{BlockFace.EAST, BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH, BlockFace.UP, BlockFace.DOWN};
                }
                BlockPos pos = BlockPos.m_274561_((double)x, (double)y, (double)z);
                BlockFace[] blockFaceArray = faces;
                int n = faces.length;
                int n2 = 0;
                while (n2 < n) {
                    dir = blockFaceArray[n2];
                    BlockState nmsBlock = this.getHandle().m_8055_(pos.m_121945_(CraftBlock.blockFaceToNotch(dir)));
                    if (nmsBlock.m_280296_() || DiodeBlock.m_52586_((BlockState)nmsBlock)) {
                        boolean taken = false;
                        AABB bb = org.bukkit.entity.ItemFrame.class.isAssignableFrom(clazz) ? ItemFrame.calculateBoundingBox(null, (BlockPos)pos, (Direction)CraftBlock.blockFaceToNotch(dir).m_122424_(), (int)width, (int)height) : HangingEntity.calculateBoundingBox(null, (BlockPos)pos, (Direction)CraftBlock.blockFaceToNotch(dir).m_122424_(), (int)width, (int)height);
                        List list = this.getHandle().m_45933_(null, bb);
                        Iterator it = list.iterator();
                        while (!taken && it.hasNext()) {
                            net.minecraft.world.entity.Entity e = (net.minecraft.world.entity.Entity)it.next();
                            if (!(e instanceof HangingEntity)) continue;
                            taken = true;
                        }
                        if (!taken) {
                            face = dir;
                            break;
                        }
                    }
                    ++n2;
                }
                if (face == BlockFace.SELF) {
                    face = BlockFace.SOUTH;
                    randomizeData = false;
                }
                dir = CraftBlock.blockFaceToNotch(face).m_122424_();
                if (org.bukkit.entity.Painting.class.isAssignableFrom(clazz)) {
                    if (this.isNormalWorld() && randomizeData) {
                        entity = Painting.m_218887_((Level)world, (BlockPos)pos, (Direction)dir).orElse(null);
                    } else {
                        entity = new Painting(EntityType.f_20506_, (Level)this.getHandle().getMinecraftWorld());
                        entity.m_19890_(x, y, z, yaw, pitch);
                        ((Painting)entity).m_6022_((Direction)dir);
                    }
                } else if (org.bukkit.entity.ItemFrame.class.isAssignableFrom(clazz)) {
                    entity = GlowItemFrame.class.isAssignableFrom(clazz) ? new net.minecraft.world.entity.decoration.GlowItemFrame((Level)world, BlockPos.m_274561_((double)x, (double)y, (double)z), (Direction)dir) : new ItemFrame((Level)world, BlockPos.m_274561_((double)x, (double)y, (double)z), (Direction)dir);
                }
            }
        } else if (TNTPrimed.class.isAssignableFrom(clazz)) {
            entity = new PrimedTnt((Level)world, x, y, z, null);
        } else if (org.bukkit.entity.ExperienceOrb.class.isAssignableFrom(clazz)) {
            entity = new ExperienceOrb((Level)world, x, y, z, 0);
        } else if (LightningStrike.class.isAssignableFrom(clazz)) {
            entity = EntityType.f_20465_.m_20615_((Level)world);
            entity.m_6027_(location.getX(), location.getY(), location.getZ());
        } else if (org.bukkit.entity.AreaEffectCloud.class.isAssignableFrom(clazz)) {
            entity = new AreaEffectCloud((Level)world, x, y, z);
        } else if (org.bukkit.entity.EvokerFangs.class.isAssignableFrom(clazz)) {
            entity = new EvokerFangs((Level)world, x, y, z, (float)Math.toRadians(yaw), 0, null);
        } else if (Marker.class.isAssignableFrom(clazz)) {
            entity = EntityType.f_147036_.m_20615_((Level)world);
            entity.m_6034_(x, y, z);
        } else if (Interaction.class.isAssignableFrom(clazz)) {
            entity = EntityType.f_271243_.m_20615_((Level)world);
            entity.m_6034_(x, y, z);
        } else if (Display.class.isAssignableFrom(clazz)) {
            if (BlockDisplay.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_268573_.m_20615_((Level)world);
            } else if (ItemDisplay.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_268643_.m_20615_((Level)world);
            } else if (TextDisplay.class.isAssignableFrom(clazz)) {
                entity = EntityType.f_268607_.m_20615_((Level)world);
            }
            if (entity != null) {
                entity.m_6034_(x, y, z);
            }
        }
        if (entity != null) {
            return entity;
        }
        throw new IllegalArgumentException("Cannot spawn an entity for " + clazz.getName());
    }
}

