/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  io.izzel.arclight.api.ArclightVersion
 *  io.izzel.arclight.api.EnumHelper
 *  io.izzel.arclight.api.Unsafe
 *  io.izzel.arclight.i18n.ArclightConfig
 *  io.izzel.arclight.i18n.conf.EntityPropertySpec
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.stats.StatType
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase
 *  net.minecraft.world.entity.decoration.PaintingVariant
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.item.crafting.CookingBookCategory
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraftforge.fml.CrashReportCallables
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.ForgeRegistry
 *  net.minecraftforge.registries.IForgeRegistry
 */
package io.izzel.arclight.common.mod.server;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.EnumHelper;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.bridge.bukkit.EntityTypeBridge;
import io.izzel.arclight.common.bridge.bukkit.MaterialBridge;
import io.izzel.arclight.common.bridge.bukkit.SimpleRegistryBridge;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.entity.EntityClassLookup;
import io.izzel.arclight.common.mod.util.ResourceLocationUtil;
import io.izzel.arclight.common.mod.util.types.ArclightEnchantment;
import io.izzel.arclight.common.mod.util.types.ArclightPotionEffect;
import io.izzel.arclight.i18n.ArclightConfig;
import io.izzel.arclight.i18n.conf.EntityPropertySpec;
import io.izzel.arclight.i18n.conf.MaterialPropertySpec;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.CrashReportCallables;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.bukkit.Art;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_20_R1.CraftCrashReport;
import org.bukkit.craftbukkit.v1_20_R1.CraftStatistic;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftSpawnCategory;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pose;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class BukkitRegistry {
    private static final List<Class<?>> MAT_CTOR = ImmutableList.of(Integer.TYPE);
    private static final List<Class<?>> ENTITY_CTOR = ImmutableList.of(String.class, Class.class, Integer.TYPE);
    private static final List<Class<?>> ENV_CTOR = ImmutableList.of(Integer.TYPE);
    private static final Map<String, Material> BY_NAME = (Map)Unsafe.getStatic(Material.class, (String)"BY_NAME");
    private static final Map<Block, Material> BLOCK_MATERIAL = (Map)Unsafe.getStatic(CraftMagicNumbers.class, (String)"BLOCK_MATERIAL");
    private static final Map<Item, Material> ITEM_MATERIAL = (Map)Unsafe.getStatic(CraftMagicNumbers.class, (String)"ITEM_MATERIAL");
    private static final Map<Material, Item> MATERIAL_ITEM = (Map)Unsafe.getStatic(CraftMagicNumbers.class, (String)"MATERIAL_ITEM");
    private static final Map<Material, Block> MATERIAL_BLOCK = (Map)Unsafe.getStatic(CraftMagicNumbers.class, (String)"MATERIAL_BLOCK");
    private static final Map<String, EntityType> ENTITY_NAME_MAP = (Map)Unsafe.getStatic(EntityType.class, (String)"NAME_MAP");
    private static final Map<Integer, World.Environment> ENVIRONMENT_MAP = (Map)Unsafe.getStatic(World.Environment.class, (String)"lookup");
    static final BiMap<ResourceKey<LevelStem>, World.Environment> DIM_MAP = HashBiMap.create((Map)ImmutableMap.builder().put((Object)LevelStem.f_63971_, (Object)World.Environment.NORMAL).put((Object)LevelStem.f_63972_, (Object)World.Environment.NETHER).put((Object)LevelStem.f_63973_, (Object)World.Environment.THE_END).build());
    private static final Map<String, Art> ART_BY_NAME = (Map)Unsafe.getStatic(Art.class, (String)"BY_NAME");
    private static final Map<Integer, Art> ART_BY_ID = (Map)Unsafe.getStatic(Art.class, (String)"BY_ID");
    private static final BiMap<ResourceLocation, Statistic> STATS = HashBiMap.create((Map)((Map)Unsafe.getStatic(CraftStatistic.class, (String)"statistics")));
    private static final BiMap<Fluid, org.bukkit.Fluid> FLUIDTYPE_FLUID = (BiMap)Unsafe.getStatic(CraftMagicNumbers.class, (String)"FLUIDTYPE_FLUID");

    public static void registerAll(DedicatedServer console) {
        CrashReportCallables.registerCrashCallable((String)"Arclight Release", () -> ((ArclightVersion)ArclightVersion.current()).getReleaseName());
        CrashReportCallables.registerCrashCallable((String)"Arclight", (Supplier)new CraftCrashReport());
        BukkitRegistry.loadMaterials();
        BukkitRegistry.loadPotions();
        BukkitRegistry.loadEnchantments();
        BukkitRegistry.loadEntities();
        BukkitRegistry.loadVillagerProfessions();
        BukkitRegistry.loadBiomes(console);
        BukkitRegistry.loadArts();
        BukkitRegistry.loadStats();
        BukkitRegistry.loadSpawnCategory();
        BukkitRegistry.loadEndDragonPhase();
        BukkitRegistry.loadCookingBookCategory();
        BukkitRegistry.loadFluids();
        try {
            for (Field field : Registry.class.getFields()) {
                Object object;
                if (!Modifier.isStatic(field.getModifiers()) || !((object = field.get(null)) instanceof Registry.SimpleRegistry)) continue;
                Registry.SimpleRegistry registry = (Registry.SimpleRegistry)object;
                ((SimpleRegistryBridge)((Object)registry)).bridge$reload();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static void loadFluids() {
        int id = org.bukkit.Fluid.values().length;
        ArrayList<org.bukkit.Fluid> newTypes = new ArrayList<org.bukkit.Fluid>();
        Field keyField = Arrays.stream(org.bukkit.Fluid.class.getDeclaredFields()).filter(it -> it.getName().equals("key")).findAny().orElse(null);
        long keyOffset = Unsafe.objectFieldOffset((Field)keyField);
        for (Fluid fluidType : ForgeRegistries.FLUIDS) {
            if (FLUIDTYPE_FLUID.containsKey((Object)fluidType)) continue;
            ResourceLocation key = ForgeRegistries.FLUIDS.getKey((Object)fluidType);
            String name = ResourceLocationUtil.standardize(key);
            org.bukkit.Fluid bukkit = (org.bukkit.Fluid)EnumHelper.makeEnum(org.bukkit.Fluid.class, (String)name, (int)id++, List.of(), List.of());
            Unsafe.putObject((Object)bukkit, (long)keyOffset, (Object)CraftNamespacedKey.fromMinecraft(key));
            newTypes.add(bukkit);
            FLUIDTYPE_FLUID.put((Object)fluidType, (Object)bukkit);
            ArclightMod.LOGGER.debug("Registered {} as fluid {}", (Object)key, (Object)bukkit);
        }
        EnumHelper.addEnums(org.bukkit.Fluid.class, newTypes);
    }

    private static void loadCookingBookCategory() {
        int id = net.minecraft.world.item.crafting.CookingBookCategory.values().length;
        ArrayList<CookingBookCategory> newTypes = new ArrayList<CookingBookCategory>();
        for (net.minecraft.world.item.crafting.CookingBookCategory category : net.minecraft.world.item.crafting.CookingBookCategory.values()) {
            try {
                CraftRecipe.getCategory(category);
            }
            catch (Exception e) {
                String name = category.name();
                CookingBookCategory bukkit = (CookingBookCategory)((Object)EnumHelper.makeEnum(CookingBookCategory.class, (String)name, (int)id++, List.of(), List.of()));
                newTypes.add(bukkit);
                ArclightMod.LOGGER.debug("Registered {} as cooking category {}", (Object)name, (Object)bukkit);
            }
        }
        EnumHelper.addEnums(CookingBookCategory.class, newTypes);
    }

    private static void loadEndDragonPhase() {
        int max = EnderDragonPhase.m_31406_();
        ArrayList<EnderDragon.Phase> newTypes = new ArrayList<EnderDragon.Phase>();
        for (int id = EnderDragon.Phase.values().length; id < max; ++id) {
            String name = "MOD_PHASE_" + id;
            EnderDragon.Phase newPhase = (EnderDragon.Phase)((Object)EnumHelper.makeEnum(EnderDragon.Phase.class, (String)name, (int)id, List.of(), List.of()));
            newTypes.add(newPhase);
            ArclightMod.LOGGER.debug("Registered {} as ender dragon phase {}", (Object)name, (Object)newPhase);
        }
        EnumHelper.addEnums(EnderDragon.Phase.class, newTypes);
    }

    private static void loadSpawnCategory() {
        int id = SpawnCategory.values().length;
        ArrayList<SpawnCategory> newTypes = new ArrayList<SpawnCategory>();
        for (MobCategory category : MobCategory.values()) {
            try {
                CraftSpawnCategory.toBukkit(category);
            }
            catch (Exception e) {
                String name = category.name();
                SpawnCategory spawnCategory = (SpawnCategory)((Object)EnumHelper.makeEnum(SpawnCategory.class, (String)name, (int)id++, List.of(), List.of()));
                newTypes.add(spawnCategory);
                ArclightMod.LOGGER.debug("Registered {} as spawn category {}", (Object)name, (Object)spawnCategory);
            }
        }
        EnumHelper.addEnums(SpawnCategory.class, newTypes);
    }

    private static void loadStats() {
        int i = Statistic.values().length;
        ArrayList<Statistic> newTypes = new ArrayList<Statistic>();
        Field key = Arrays.stream(Statistic.class.getDeclaredFields()).filter(it -> it.getName().equals("key")).findAny().orElse(null);
        long keyOffset = Unsafe.objectFieldOffset((Field)key);
        for (StatType statType : ForgeRegistries.STAT_TYPES) {
            ResourceLocation location;
            Statistic statistic;
            if (statType == Stats.f_12988_ || (statistic = (Statistic)STATS.get((Object)(location = ForgeRegistries.STAT_TYPES.getKey((Object)statType)))) != null) continue;
            String standardName = ResourceLocationUtil.standardize(location);
            Statistic.Type type = statType.m_12893_() == BuiltInRegistries.f_256780_ ? Statistic.Type.ENTITY : (statType.m_12893_() == BuiltInRegistries.f_256975_ ? Statistic.Type.BLOCK : (statType.m_12893_() == BuiltInRegistries.f_257033_ ? Statistic.Type.ITEM : Statistic.Type.UNTYPED));
            statistic = (Statistic)EnumHelper.makeEnum(Statistic.class, (String)standardName, (int)i, (List)ImmutableList.of(Statistic.Type.class), (List)ImmutableList.of((Object)((Object)type)));
            Unsafe.putObject((Object)statistic, (long)keyOffset, (Object)location);
            newTypes.add(statistic);
            STATS.put((Object)location, (Object)statistic);
            ArclightMod.LOGGER.debug("Registered {} as stats {}", (Object)location, (Object)statistic);
            ++i;
        }
        for (ResourceLocation location : BuiltInRegistries.f_256771_) {
            Statistic statistic = (Statistic)STATS.get((Object)location);
            if (statistic != null) continue;
            String standardName = ResourceLocationUtil.standardize(location);
            statistic = (Statistic)EnumHelper.makeEnum(Statistic.class, (String)standardName, (int)i, (List)ImmutableList.of(), (List)ImmutableList.of());
            Unsafe.putObject((Object)statistic, (long)keyOffset, (Object)location);
            newTypes.add(statistic);
            STATS.put((Object)location, (Object)statistic);
            ArclightMod.LOGGER.debug("Registered {} as custom stats {}", (Object)location, (Object)statistic);
            ++i;
        }
        EnumHelper.addEnums(Statistic.class, newTypes);
        BukkitRegistry.putStatic(CraftStatistic.class, "statistics", STATS);
    }

    private static void loadArts() {
        int i = Art.values().length;
        ArrayList<Art> newTypes = new ArrayList<Art>();
        Field key = Arrays.stream(Art.class.getDeclaredFields()).filter(it -> it.getName().equals("key")).findAny().orElse(null);
        long keyOffset = Unsafe.objectFieldOffset((Field)key);
        for (PaintingVariant paintingType : ForgeRegistries.PAINTING_VARIANTS) {
            ResourceLocation location = ForgeRegistries.PAINTING_VARIANTS.getKey((Object)paintingType);
            String lookupName = location.m_135815_().toLowerCase(Locale.ROOT);
            Art bukkit = Art.getByName(lookupName);
            if (bukkit != null) continue;
            String standardName = ResourceLocationUtil.standardize(location);
            bukkit = (Art)EnumHelper.makeEnum(Art.class, (String)standardName, (int)i, (List)ImmutableList.of(Integer.TYPE, Integer.TYPE, Integer.TYPE), (List)ImmutableList.of((Object)i, (Object)paintingType.m_218908_(), (Object)paintingType.m_218909_()));
            newTypes.add(bukkit);
            Unsafe.putObject((Object)bukkit, (long)keyOffset, (Object)CraftNamespacedKey.fromMinecraft(location));
            ART_BY_ID.put(i, bukkit);
            ART_BY_NAME.put(lookupName, bukkit);
            ArclightMod.LOGGER.debug("Registered {} as art {}", (Object)location, (Object)bukkit);
            ++i;
        }
        EnumHelper.addEnums(Art.class, newTypes);
    }

    private static void loadBiomes(DedicatedServer console) {
        int i = Biome.values().length;
        ArrayList<Biome> newTypes = new ArrayList<Biome>();
        Field key = Arrays.stream(Biome.class.getDeclaredFields()).filter(it -> it.getName().equals("key")).findAny().orElse(null);
        long keyOffset = Unsafe.objectFieldOffset((Field)key);
        net.minecraft.core.Registry registry = console.m_206579_().m_175515_(Registries.f_256952_);
        for (net.minecraft.world.level.biome.Biome biome : registry) {
            Biome bukkit;
            ResourceLocation location = registry.m_7981_((Object)biome);
            String name = ResourceLocationUtil.standardize(location);
            try {
                bukkit = Biome.valueOf(name);
            }
            catch (Throwable t) {
                bukkit = null;
            }
            if (bukkit != null) continue;
            bukkit = (Biome)EnumHelper.makeEnum(Biome.class, (String)name, (int)i++, (List)ImmutableList.of(), (List)ImmutableList.of());
            newTypes.add(bukkit);
            Unsafe.putObject((Object)bukkit, (long)keyOffset, (Object)CraftNamespacedKey.fromMinecraft(location));
            ArclightMod.LOGGER.debug("Registered {} as biome {}", (Object)location, (Object)bukkit);
        }
        EnumHelper.addEnums(Biome.class, newTypes);
        ArclightMod.LOGGER.info("registry.biome", (Object)newTypes.size());
    }

    private static void loadVillagerProfessions() {
        int i = Villager.Profession.values().length;
        ArrayList<Villager.Profession> newTypes = new ArrayList<Villager.Profession>();
        Field key = Arrays.stream(Villager.Profession.class.getDeclaredFields()).filter(it -> it.getName().equals("key")).findAny().orElse(null);
        long keyOffset = Unsafe.objectFieldOffset((Field)key);
        for (VillagerProfession villagerProfession : ForgeRegistries.VILLAGER_PROFESSIONS) {
            Villager.Profession profession;
            ResourceLocation location = ForgeRegistries.VILLAGER_PROFESSIONS.getKey((Object)villagerProfession);
            String name = ResourceLocationUtil.standardize(location);
            try {
                profession = Villager.Profession.valueOf(name);
            }
            catch (Throwable t) {
                profession = null;
            }
            if (profession != null) continue;
            profession = (Villager.Profession)EnumHelper.makeEnum(Villager.Profession.class, (String)name, (int)i++, (List)ImmutableList.of(), (List)ImmutableList.of());
            newTypes.add(profession);
            Unsafe.putObject((Object)profession, (long)keyOffset, (Object)CraftNamespacedKey.fromMinecraft(location));
            ArclightMod.LOGGER.debug("Registered {} as villager profession {}", (Object)location, (Object)profession);
        }
        EnumHelper.addEnums(Villager.Profession.class, newTypes);
        ArclightMod.LOGGER.info("registry.villager-profession", (Object)newTypes.size());
    }

    public static void registerEnvironments(net.minecraft.core.Registry<LevelStem> registry) {
        int i = World.Environment.values().length;
        ArrayList<World.Environment> newTypes = new ArrayList<World.Environment>();
        for (Map.Entry entry : registry.m_6579_()) {
            ResourceKey key = (ResourceKey)entry.getKey();
            World.Environment environment = (World.Environment)((Object)DIM_MAP.get((Object)key));
            if (environment != null) continue;
            String name = ResourceLocationUtil.standardize(key.m_135782_());
            environment = (World.Environment)((Object)EnumHelper.makeEnum(World.Environment.class, (String)name, (int)i, ENV_CTOR, (List)ImmutableList.of((Object)(i - 1))));
            newTypes.add(environment);
            ENVIRONMENT_MAP.put(i - 1, environment);
            DIM_MAP.put((Object)key, (Object)environment);
            ArclightMod.LOGGER.debug("Registered {} as environment {}", (Object)key.m_135782_(), (Object)environment);
            ++i;
        }
        EnumHelper.addEnums(World.Environment.class, newTypes);
        ArclightMod.LOGGER.info("registry.environment", (Object)newTypes.size());
    }

    private static void loadEntities() {
        int origin;
        int i = origin = EntityType.values().length;
        ArrayList<EntityType> newTypes = new ArrayList<EntityType>(ForgeRegistries.ENTITY_TYPES.getEntries().size() - origin + 1);
        for (net.minecraft.world.entity.EntityType type : ForgeRegistries.ENTITY_TYPES) {
            ResourceLocation location = ForgeRegistries.ENTITY_TYPES.getKey((Object)type);
            EntityType entityType = null;
            boolean found = false;
            if (location.m_135827_().equals("minecraft")) {
                entityType = EntityType.fromName(location.m_135815_());
                if (entityType != null) {
                    found = true;
                    ((EntityTypeBridge)((Object)entityType)).bridge$setHandle(type);
                } else {
                    ArclightMod.LOGGER.warn("Not found {} in {}", (Object)location, EntityType.class);
                }
            }
            if (!found) {
                String name = ResourceLocationUtil.standardize(location);
                entityType = (EntityType)EnumHelper.makeEnum(EntityType.class, (String)name, (int)i++, ENTITY_CTOR, (List)ImmutableList.of((Object)location.m_135815_(), Entity.class, (Object)-1));
                ((EntityTypeBridge)((Object)entityType)).bridge$setup(location, type, BukkitRegistry.entitySpec(location));
                newTypes.add(entityType);
                ArclightMod.LOGGER.debug("Registered {} as entity {}", (Object)location, (Object)entityType);
            }
            ENTITY_NAME_MAP.put(location.toString(), entityType);
        }
        EnumHelper.addEnums(EntityType.class, newTypes);
        EntityClassLookup.init();
        ArclightMod.LOGGER.info("registry.entity-type", (Object)newTypes.size());
    }

    private static void loadEnchantments() {
        int origin = org.bukkit.enchantments.Enchantment.values().length;
        int size = ForgeRegistries.ENCHANTMENTS.getEntries().size();
        BukkitRegistry.putBool(org.bukkit.enchantments.Enchantment.class, "acceptingNew", true);
        for (Enchantment enc : ForgeRegistries.ENCHANTMENTS) {
            try {
                ResourceLocation location = ForgeRegistries.ENCHANTMENTS.getKey((Object)enc);
                String name = ResourceLocationUtil.standardize(location);
                ArclightEnchantment enchantment = new ArclightEnchantment(enc, name);
                org.bukkit.enchantments.Enchantment.registerEnchantment(enchantment);
                ArclightMod.LOGGER.debug("Registered {} as enchantment {}", (Object)location, (Object)enchantment);
            }
            catch (Exception e) {
                ArclightMod.LOGGER.error("Failed to register enchantment {}: {}", (Object)enc, (Object)e);
            }
        }
        org.bukkit.enchantments.Enchantment.stopAcceptingRegistrations();
        ArclightMod.LOGGER.info("registry.enchantment", (Object)(size - origin));
    }

    private static void loadPotions() {
        int origin = PotionEffectType.values().length;
        int size = ForgeRegistries.MOB_EFFECTS.getEntries().size();
        int maxId = ForgeRegistries.MOB_EFFECTS.getValues().stream().mapToInt(MobEffect::m_19459_).max().orElse(0);
        PotionEffectType[] types = new PotionEffectType[maxId + 1];
        BukkitRegistry.putStatic(PotionEffectType.class, "byId", types);
        BukkitRegistry.putBool(PotionEffectType.class, "acceptingNew", true);
        for (MobEffect eff : ForgeRegistries.MOB_EFFECTS) {
            try {
                ResourceLocation location = ForgeRegistries.MOB_EFFECTS.getKey((Object)eff);
                String name = ResourceLocationUtil.standardize(location);
                ArclightPotionEffect effect = new ArclightPotionEffect(eff, name);
                PotionEffectType.registerPotionEffectType(effect);
                ArclightMod.LOGGER.debug("Registered {} as potion {}", (Object)location, (Object)effect);
            }
            catch (Exception e) {
                ArclightMod.LOGGER.error("Failed to register potion type {}: {}", (Object)eff, (Object)e);
            }
        }
        PotionEffectType.stopAcceptingRegistrations();
        ArclightMod.LOGGER.info("registry.potion", (Object)(size - origin));
        int typeId = PotionType.values().length;
        ArrayList<PotionType> newTypes = new ArrayList<PotionType>();
        HashBiMap map = HashBiMap.create((Map)((Map)Unsafe.getStatic(CraftPotionUtil.class, (String)"regular")));
        BukkitRegistry.putStatic(CraftPotionUtil.class, "regular", map);
        for (Potion potion : ForgeRegistries.POTIONS) {
            ResourceLocation location = ForgeRegistries.POTIONS.getKey((Object)potion);
            if (CraftPotionUtil.toBukkit(location.toString()).getType() != PotionType.UNCRAFTABLE || potion == Potions.f_43598_) continue;
            String name = ResourceLocationUtil.standardize(location);
            MobEffectInstance effectInstance = potion.m_43488_().isEmpty() ? null : (MobEffectInstance)potion.m_43488_().get(0);
            PotionType potionType = (PotionType)((Object)EnumHelper.makeEnum(PotionType.class, (String)name, (int)typeId++, Arrays.asList(PotionEffectType.class, Boolean.TYPE, Boolean.TYPE), Arrays.asList(effectInstance == null ? null : PotionEffectType.getById(MobEffect.m_19459_((MobEffect)effectInstance.m_19544_())), false, false)));
            newTypes.add(potionType);
            map.put((Object)potionType, (Object)location.toString());
            ArclightMod.LOGGER.debug("Registered {} as potion type {}", (Object)location, (Object)potionType);
        }
        EnumHelper.addEnums(PotionType.class, newTypes);
    }

    private static void loadMaterials() {
        Item value;
        Material material;
        String name;
        ResourceLocation location;
        int i;
        int blocks = 0;
        int items = 0;
        int origin = i = Material.values().length;
        ArrayList<Material> list = new ArrayList<Material>();
        for (Block block : ForgeRegistries.BLOCKS) {
            location = ForgeRegistries.BLOCKS.getKey((Object)block);
            name = ResourceLocationUtil.standardize(location);
            material = BY_NAME.get(name);
            if (material == null) {
                material = (Material)EnumHelper.makeEnum(Material.class, (String)name, (int)i, MAT_CTOR, (List)ImmutableList.of((Object)i));
                ((MaterialBridge)((Object)material)).bridge$setupBlock(location, block, BukkitRegistry.matSpec(location));
                BY_NAME.put(name, material);
                ++i;
                ++blocks;
                ArclightMod.LOGGER.debug("Registered {} as block {}", (Object)location, (Object)material);
                list.add(material);
            } else {
                ((MaterialBridge)((Object)material)).bridge$setupVanillaBlock(BukkitRegistry.matSpec(location));
            }
            BLOCK_MATERIAL.put(block, material);
            MATERIAL_BLOCK.put(material, block);
            value = (Item)ForgeRegistries.ITEMS.getValue(location);
            if (value == null || value == Items.f_41852_) continue;
            ((MaterialBridge)((Object)material)).bridge$setItem();
            ITEM_MATERIAL.put(value, material);
            MATERIAL_ITEM.put(material, value);
        }
        for (Item item : ForgeRegistries.ITEMS) {
            location = ForgeRegistries.ITEMS.getKey((Object)item);
            name = ResourceLocationUtil.standardize(location);
            material = BY_NAME.get(name);
            if (material == null) {
                material = (Material)EnumHelper.makeEnum(Material.class, (String)name, (int)i, MAT_CTOR, (List)ImmutableList.of((Object)i));
                ((MaterialBridge)((Object)material)).bridge$setupItem(location, item, BukkitRegistry.matSpec(location));
                BY_NAME.put(name, material);
                ++i;
                ++items;
                ArclightMod.LOGGER.debug("Registered {} as item {}", (Object)location, (Object)material);
                list.add(material);
            }
            ITEM_MATERIAL.put(item, material);
            MATERIAL_ITEM.put(material, item);
            value = (Block)ForgeRegistries.BLOCKS.getValue(location);
            if (value == null || value == Blocks.f_50016_) continue;
            ((MaterialBridge)((Object)material)).bridge$setBlock();
            BLOCK_MATERIAL.put((Block)value, material);
            MATERIAL_BLOCK.put(material, (Block)value);
        }
        EnumHelper.addEnums(Material.class, list);
        ArclightMod.LOGGER.info("registry.material", (Object)(i - origin), (Object)blocks, (Object)items);
    }

    private static MaterialPropertySpec matSpec(ResourceLocation location) {
        return ArclightConfig.spec().getCompat().getMaterial(location.toString()).orElse(MaterialPropertySpec.EMPTY);
    }

    private static EntityPropertySpec entitySpec(ResourceLocation location) {
        return ArclightConfig.spec().getCompat().getEntity(location.toString()).orElse(EntityPropertySpec.EMPTY);
    }

    public static Pose toBukkitPose(net.minecraft.world.entity.Pose nms) {
        if (Pose.values().length <= nms.ordinal()) {
            ArrayList<Pose> newTypes = new ArrayList<Pose>();
            int forgeCount = net.minecraft.world.entity.Pose.values().length;
            for (int id = Pose.values().length; id < forgeCount; ++id) {
                String name = net.minecraft.world.entity.Pose.values()[id].name();
                Pose newPhase = (Pose)((Object)EnumHelper.makeEnum(Pose.class, (String)name, (int)id, List.of(), List.of()));
                newTypes.add(newPhase);
                ArclightMod.LOGGER.debug("Registered {} as pose {}", (Object)name, (Object)newPhase);
            }
            EnumHelper.addEnums(Pose.class, newTypes);
        }
        return Pose.values()[nms.ordinal()];
    }

    private static void putStatic(Class<?> cl, String name, Object o) {
        try {
            Unsafe.ensureClassInitialized(cl);
            Field field = cl.getDeclaredField(name);
            Object materialByNameBase = Unsafe.staticFieldBase((Field)field);
            long materialByNameOffset = Unsafe.staticFieldOffset((Field)field);
            Unsafe.putObject((Object)materialByNameBase, (long)materialByNameOffset, (Object)o);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void putBool(Class<?> cl, String name, boolean b) {
        try {
            Unsafe.ensureClassInitialized(cl);
            Field field = cl.getDeclaredField(name);
            Object materialByNameBase = Unsafe.staticFieldBase((Field)field);
            long materialByNameOffset = Unsafe.staticFieldOffset((Field)field);
            Unsafe.putBoolean((Object)materialByNameBase, (long)materialByNameOffset, (boolean)b);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Set<IForgeRegistry<?>> registries() {
        return ImmutableSet.of((Object)ForgeRegistries.BLOCKS, (Object)ForgeRegistries.ITEMS, (Object)ForgeRegistries.MOB_EFFECTS, (Object)ForgeRegistries.POTIONS, (Object)ForgeRegistries.ENTITY_TYPES, (Object)ForgeRegistries.BLOCK_ENTITY_TYPES, (Object[])new IForgeRegistry[]{ForgeRegistries.BIOMES});
    }

    public static void unlockRegistries() {
        for (IForgeRegistry<?> registry : BukkitRegistry.registries()) {
            if (!(registry instanceof ForgeRegistry)) continue;
            ((ForgeRegistry)registry).unfreeze();
        }
    }

    public static void lockRegistries() {
        for (IForgeRegistry<?> registry : BukkitRegistry.registries()) {
            if (!(registry instanceof ForgeRegistry)) continue;
            ((ForgeRegistry)registry).freeze();
        }
    }
}

