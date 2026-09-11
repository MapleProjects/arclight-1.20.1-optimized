/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.ImmutableBiMap
 *  com.google.common.collect.ImmutableBiMap$Builder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.stats.ServerStatsCounter
 *  net.minecraft.stats.Stat
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public enum CraftStatistic {
    DAMAGE_DEALT(Stats.f_12928_),
    DAMAGE_TAKEN(Stats.f_12931_),
    DEATHS(Stats.f_12935_),
    MOB_KILLS(Stats.f_12936_),
    PLAYER_KILLS(Stats.f_12938_),
    FISH_CAUGHT(Stats.f_12939_),
    ANIMALS_BRED(Stats.f_12937_),
    LEAVE_GAME(Stats.f_12989_),
    JUMP(Stats.f_12926_),
    DROP_COUNT(Stats.f_12927_),
    DROP(new ResourceLocation("dropped")),
    PICKUP(new ResourceLocation("picked_up")),
    PLAY_ONE_MINUTE(Stats.f_144255_),
    TOTAL_WORLD_TIME(Stats.f_144256_),
    WALK_ONE_CM(Stats.f_12994_),
    WALK_ON_WATER_ONE_CM(Stats.f_12997_),
    FALL_ONE_CM(Stats.f_12998_),
    SNEAK_TIME(Stats.f_12993_),
    CLIMB_ONE_CM(Stats.f_12999_),
    FLY_ONE_CM(Stats.f_13000_),
    WALK_UNDER_WATER_ONE_CM(Stats.f_13001_),
    MINECART_ONE_CM(Stats.f_13002_),
    BOAT_ONE_CM(Stats.f_13003_),
    PIG_ONE_CM(Stats.f_13004_),
    HORSE_ONE_CM(Stats.f_13005_),
    SPRINT_ONE_CM(Stats.f_12996_),
    CROUCH_ONE_CM(Stats.f_12995_),
    AVIATE_ONE_CM(Stats.f_12923_),
    MINE_BLOCK(new ResourceLocation("mined")),
    USE_ITEM(new ResourceLocation("used")),
    BREAK_ITEM(new ResourceLocation("broken")),
    CRAFT_ITEM(new ResourceLocation("crafted")),
    KILL_ENTITY(new ResourceLocation("killed")),
    ENTITY_KILLED_BY(new ResourceLocation("killed_by")),
    TIME_SINCE_DEATH(Stats.f_12991_),
    TALKED_TO_VILLAGER(Stats.f_12940_),
    TRADED_WITH_VILLAGER(Stats.f_12941_),
    CAKE_SLICES_EATEN(Stats.f_12942_),
    CAULDRON_FILLED(Stats.f_12943_),
    CAULDRON_USED(Stats.f_12944_),
    ARMOR_CLEANED(Stats.f_12945_),
    BANNER_CLEANED(Stats.f_12946_),
    BREWINGSTAND_INTERACTION(Stats.f_12948_),
    BEACON_INTERACTION(Stats.f_12955_),
    DROPPER_INSPECTED(Stats.f_12956_),
    HOPPER_INSPECTED(Stats.f_12957_),
    DISPENSER_INSPECTED(Stats.f_12958_),
    NOTEBLOCK_PLAYED(Stats.f_12959_),
    NOTEBLOCK_TUNED(Stats.f_12960_),
    FLOWER_POTTED(Stats.f_12961_),
    TRAPPED_CHEST_TRIGGERED(Stats.f_12962_),
    ENDERCHEST_OPENED(Stats.f_12963_),
    ITEM_ENCHANTED(Stats.f_12964_),
    RECORD_PLAYED(Stats.f_12965_),
    FURNACE_INTERACTION(Stats.f_12966_),
    CRAFTING_TABLE_INTERACTION(Stats.f_12967_),
    CHEST_OPENED(Stats.f_12968_),
    SLEEP_IN_BED(Stats.f_12969_),
    SHULKER_BOX_OPENED(Stats.f_12970_),
    TIME_SINCE_REST(Stats.f_12992_),
    SWIM_ONE_CM(Stats.f_12924_),
    DAMAGE_DEALT_ABSORBED(Stats.f_12929_),
    DAMAGE_DEALT_RESISTED(Stats.f_12930_),
    DAMAGE_BLOCKED_BY_SHIELD(Stats.f_12932_),
    DAMAGE_ABSORBED(Stats.f_12933_),
    DAMAGE_RESISTED(Stats.f_12934_),
    CLEAN_SHULKER_BOX(Stats.f_12947_),
    OPEN_BARREL(Stats.f_12971_),
    INTERACT_WITH_BLAST_FURNACE(Stats.f_12972_),
    INTERACT_WITH_SMOKER(Stats.f_12973_),
    INTERACT_WITH_LECTERN(Stats.f_12974_),
    INTERACT_WITH_CAMPFIRE(Stats.f_12975_),
    INTERACT_WITH_CARTOGRAPHY_TABLE(Stats.f_12976_),
    INTERACT_WITH_LOOM(Stats.f_12977_),
    INTERACT_WITH_STONECUTTER(Stats.f_12978_),
    BELL_RING(Stats.f_12979_),
    RAID_TRIGGER(Stats.f_12980_),
    RAID_WIN(Stats.f_12950_),
    INTERACT_WITH_ANVIL(Stats.f_12951_),
    INTERACT_WITH_GRINDSTONE(Stats.f_12952_),
    TARGET_HIT(Stats.f_12953_),
    INTERACT_WITH_SMITHING_TABLE(Stats.f_12954_),
    STRIDER_ONE_CM(Stats.f_12925_);

    private final ResourceLocation minecraftKey;
    private final Statistic bukkit;
    private static final BiMap<ResourceLocation, Statistic> statistics;

    static {
        ImmutableBiMap.Builder statisticBuilder = ImmutableBiMap.builder();
        CraftStatistic[] craftStatisticArray = CraftStatistic.values();
        int n = craftStatisticArray.length;
        int n2 = 0;
        while (n2 < n) {
            CraftStatistic statistic = craftStatisticArray[n2];
            statisticBuilder.put((Object)statistic.minecraftKey, (Object)statistic.bukkit);
            ++n2;
        }
        statistics = statisticBuilder.build();
    }

    private CraftStatistic(ResourceLocation minecraftKey) {
        this.minecraftKey = minecraftKey;
        this.bukkit = Statistic.valueOf(this.name());
        Preconditions.checkState((this.bukkit != null ? 1 : 0) != 0, (String)"Bukkit statistic %s does not exist", (Object)this.name());
    }

    public static Statistic getBukkitStatistic(Stat<?> statistic) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"NMS Statistic cannot be null");
        Registry statRegistry = statistic.m_12859_().m_12893_();
        ResourceLocation nmsKey = BuiltInRegistries.f_256899_.m_7981_((Object)statistic.m_12859_());
        if (statRegistry == BuiltInRegistries.f_256771_) {
            nmsKey = (ResourceLocation)statistic.m_12867_();
        }
        return (Statistic)statistics.get((Object)nmsKey);
    }

    public static Stat getNMSStatistic(Statistic bukkit) {
        Preconditions.checkArgument((bukkit.getType() == Statistic.Type.UNTYPED ? 1 : 0) != 0, (Object)"This method only accepts untyped statistics");
        Stat nms = Stats.f_12988_.m_12902_((Object)((ResourceLocation)statistics.inverse().get((Object)bukkit)));
        Preconditions.checkArgument((nms != null ? 1 : 0) != 0, (String)"NMS Statistic %s does not exist", (Object)bukkit);
        return nms;
    }

    public static Stat getMaterialStatistic(Statistic stat, Material material) {
        try {
            if (stat == Statistic.MINE_BLOCK) {
                return Stats.f_12949_.m_12902_((Object)CraftMagicNumbers.getBlock(material));
            }
            if (stat == Statistic.CRAFT_ITEM) {
                return Stats.f_12981_.m_12902_((Object)CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.USE_ITEM) {
                return Stats.f_12982_.m_12902_((Object)CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.BREAK_ITEM) {
                return Stats.f_12983_.m_12902_((Object)CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.PICKUP) {
                return Stats.f_12984_.m_12902_((Object)CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.DROP) {
                return Stats.f_12985_.m_12902_((Object)CraftMagicNumbers.getItem(material));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    public static Stat getEntityStatistic(Statistic stat, org.bukkit.entity.EntityType entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"EntityType cannot be null");
        if (entity.getName() != null) {
            EntityType nmsEntity = (EntityType)BuiltInRegistries.f_256780_.m_7745_(new ResourceLocation(entity.getName()));
            if (stat == Statistic.KILL_ENTITY) {
                return Stats.f_12986_.m_12902_((Object)nmsEntity);
            }
            if (stat == Statistic.ENTITY_KILLED_BY) {
                return Stats.f_12987_.m_12902_((Object)nmsEntity);
            }
        }
        return null;
    }

    public static org.bukkit.entity.EntityType getEntityTypeFromStatistic(Stat<EntityType<?>> statistic) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"NMS Statistic cannot be null");
        ResourceLocation name = EntityType.m_20613_((EntityType)((EntityType)statistic.m_12867_()));
        return org.bukkit.entity.EntityType.fromName(name.m_135815_());
    }

    /*
     * WARNING - void declaration
     */
    public static Material getMaterialFromStatistic(Stat<?> statistic) {
        Object object = statistic.m_12867_();
        if (object instanceof Item var1_2) {
            void statisticItemValue;
            return CraftMagicNumbers.getMaterial((Item)statisticItemValue);
        }
        Object object2 = statistic.m_12867_();
        if (object2 instanceof Block var4_4) {
            void statisticBlockValue;
            return CraftMagicNumbers.getMaterial((Block)statisticBlockValue);
        }
        return null;
    }

    public static void incrementStatistic(ServerStatsCounter manager, Statistic statistic) {
        CraftStatistic.incrementStatistic(manager, statistic, 1);
    }

    public static void decrementStatistic(ServerStatsCounter manager, Statistic statistic) {
        CraftStatistic.decrementStatistic(manager, statistic, 1);
    }

    public static int getStatistic(ServerStatsCounter manager, Statistic statistic) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"Statistic cannot be null");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.UNTYPED ? 1 : 0) != 0, (Object)"Must supply additional parameter for this statistic");
        return manager.m_13015_(CraftStatistic.getNMSStatistic(statistic));
    }

    public static void incrementStatistic(ServerStatsCounter manager, Statistic statistic, int amount) {
        Preconditions.checkArgument((amount > 0 ? 1 : 0) != 0, (Object)"Amount must be greater than 0");
        CraftStatistic.setStatistic(manager, statistic, CraftStatistic.getStatistic(manager, statistic) + amount);
    }

    public static void decrementStatistic(ServerStatsCounter manager, Statistic statistic, int amount) {
        Preconditions.checkArgument((amount > 0 ? 1 : 0) != 0, (Object)"Amount must be greater than 0");
        CraftStatistic.setStatistic(manager, statistic, CraftStatistic.getStatistic(manager, statistic) - amount);
    }

    public static void setStatistic(ServerStatsCounter manager, Statistic statistic, int newValue) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"Statistic cannot be null");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.UNTYPED ? 1 : 0) != 0, (Object)"Must supply additional parameter for this statistic");
        Preconditions.checkArgument((newValue >= 0 ? 1 : 0) != 0, (Object)"Value must be greater than or equal to 0");
        Stat nmsStatistic = CraftStatistic.getNMSStatistic(statistic);
        manager.m_6085_(null, nmsStatistic, newValue);
    }

    public static void incrementStatistic(ServerStatsCounter manager, Statistic statistic, Material material) {
        CraftStatistic.incrementStatistic(manager, statistic, material, 1);
    }

    public static void decrementStatistic(ServerStatsCounter manager, Statistic statistic, Material material) {
        CraftStatistic.decrementStatistic(manager, statistic, material, 1);
    }

    public static int getStatistic(ServerStatsCounter manager, Statistic statistic, Material material) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"Statistic cannot be null");
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.BLOCK || statistic.getType() == Statistic.Type.ITEM ? 1 : 0) != 0, (Object)"This statistic does not take a Material parameter");
        Stat nmsStatistic = CraftStatistic.getMaterialStatistic(statistic, material);
        Preconditions.checkArgument((nmsStatistic != null ? 1 : 0) != 0, (String)"The supplied Material %s does not have a corresponding statistic", (Object)material);
        return manager.m_13015_(nmsStatistic);
    }

    public static void incrementStatistic(ServerStatsCounter manager, Statistic statistic, Material material, int amount) {
        Preconditions.checkArgument((amount > 0 ? 1 : 0) != 0, (Object)"Amount must be greater than 0");
        CraftStatistic.setStatistic(manager, statistic, material, CraftStatistic.getStatistic(manager, statistic, material) + amount);
    }

    public static void decrementStatistic(ServerStatsCounter manager, Statistic statistic, Material material, int amount) {
        Preconditions.checkArgument((amount > 0 ? 1 : 0) != 0, (Object)"Amount must be greater than 0");
        CraftStatistic.setStatistic(manager, statistic, material, CraftStatistic.getStatistic(manager, statistic, material) - amount);
    }

    public static void setStatistic(ServerStatsCounter manager, Statistic statistic, Material material, int newValue) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"Statistic cannot be null");
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((newValue >= 0 ? 1 : 0) != 0, (Object)"Value must be greater than or equal to 0");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.BLOCK || statistic.getType() == Statistic.Type.ITEM ? 1 : 0) != 0, (Object)"This statistic does not take a Material parameter");
        Stat nmsStatistic = CraftStatistic.getMaterialStatistic(statistic, material);
        Preconditions.checkArgument((nmsStatistic != null ? 1 : 0) != 0, (String)"The supplied Material %s does not have a corresponding statistic", (Object)material);
        manager.m_6085_(null, nmsStatistic, newValue);
    }

    public static void incrementStatistic(ServerStatsCounter manager, Statistic statistic, org.bukkit.entity.EntityType entityType) {
        CraftStatistic.incrementStatistic(manager, statistic, entityType, 1);
    }

    public static void decrementStatistic(ServerStatsCounter manager, Statistic statistic, org.bukkit.entity.EntityType entityType) {
        CraftStatistic.decrementStatistic(manager, statistic, entityType, 1);
    }

    public static int getStatistic(ServerStatsCounter manager, Statistic statistic, org.bukkit.entity.EntityType entityType) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"Statistic cannot be null");
        Preconditions.checkArgument((entityType != null ? 1 : 0) != 0, (Object)"EntityType cannot be null");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.ENTITY ? 1 : 0) != 0, (Object)"This statistic does not take an EntityType parameter");
        Stat nmsStatistic = CraftStatistic.getEntityStatistic(statistic, entityType);
        Preconditions.checkArgument((nmsStatistic != null ? 1 : 0) != 0, (String)"The supplied EntityType %s does not have a corresponding statistic", (Object)entityType);
        return manager.m_13015_(nmsStatistic);
    }

    public static void incrementStatistic(ServerStatsCounter manager, Statistic statistic, org.bukkit.entity.EntityType entityType, int amount) {
        Preconditions.checkArgument((amount > 0 ? 1 : 0) != 0, (Object)"Amount must be greater than 0");
        CraftStatistic.setStatistic(manager, statistic, entityType, CraftStatistic.getStatistic(manager, statistic, entityType) + amount);
    }

    public static void decrementStatistic(ServerStatsCounter manager, Statistic statistic, org.bukkit.entity.EntityType entityType, int amount) {
        Preconditions.checkArgument((amount > 0 ? 1 : 0) != 0, (Object)"Amount must be greater than 0");
        CraftStatistic.setStatistic(manager, statistic, entityType, CraftStatistic.getStatistic(manager, statistic, entityType) - amount);
    }

    public static void setStatistic(ServerStatsCounter manager, Statistic statistic, org.bukkit.entity.EntityType entityType, int newValue) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"Statistic cannot be null");
        Preconditions.checkArgument((entityType != null ? 1 : 0) != 0, (Object)"EntityType cannot be null");
        Preconditions.checkArgument((newValue >= 0 ? 1 : 0) != 0, (Object)"Value must be greater than or equal to 0");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.ENTITY ? 1 : 0) != 0, (Object)"This statistic does not take an EntityType parameter");
        Stat nmsStatistic = CraftStatistic.getEntityStatistic(statistic, entityType);
        Preconditions.checkArgument((nmsStatistic != null ? 1 : 0) != 0, (String)"The supplied EntityType %s does not have a corresponding statistic", (Object)entityType);
        manager.m_6085_(null, nmsStatistic, newValue);
    }
}

