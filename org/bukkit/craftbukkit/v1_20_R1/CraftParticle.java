/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.DustColorTransitionOptions
 *  net.minecraft.core.particles.DustParticleOptions
 *  net.minecraft.core.particles.ItemParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.core.particles.SculkChargeParticleOptions
 *  net.minecraft.core.particles.ShriekParticleOption
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.core.particles.VibrationParticleOption
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.gameevent.BlockPositionSource
 *  net.minecraft.world.level.gameevent.EntityPositionSource
 *  net.minecraft.world.level.gameevent.PositionSource
 *  org.joml.Vector3f
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.core.particles.ShriekParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.VibrationParticleOption;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.PositionSource;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.joml.Vector3f;

public enum CraftParticle {
    EXPLOSION_NORMAL("poof"),
    EXPLOSION_LARGE("explosion"),
    EXPLOSION_HUGE("explosion_emitter"),
    FIREWORKS_SPARK("firework"),
    WATER_BUBBLE("bubble"),
    WATER_SPLASH("splash"),
    WATER_WAKE("fishing"),
    SUSPENDED("underwater"),
    SUSPENDED_DEPTH("underwater"),
    CRIT("crit"),
    CRIT_MAGIC("enchanted_hit"),
    SMOKE_NORMAL("smoke"),
    SMOKE_LARGE("large_smoke"),
    SPELL("effect"),
    SPELL_INSTANT("instant_effect"),
    SPELL_MOB("entity_effect"),
    SPELL_MOB_AMBIENT("ambient_entity_effect"),
    SPELL_WITCH("witch"),
    DRIP_WATER("dripping_water"),
    DRIP_LAVA("dripping_lava"),
    VILLAGER_ANGRY("angry_villager"),
    VILLAGER_HAPPY("happy_villager"),
    TOWN_AURA("mycelium"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANTMENT_TABLE("enchant"),
    FLAME("flame"),
    LAVA("lava"),
    CLOUD("cloud"),
    REDSTONE("dust"),
    SNOWBALL("item_snowball"),
    SNOW_SHOVEL("item_snowball"),
    SLIME("item_slime"),
    HEART("heart"),
    ITEM_CRACK("item"),
    BLOCK_CRACK("block"),
    BLOCK_DUST("block"),
    WATER_DROP("rain"),
    MOB_APPEARANCE("elder_guardian"),
    DRAGON_BREATH("dragon_breath"),
    END_ROD("end_rod"),
    DAMAGE_INDICATOR("damage_indicator"),
    SWEEP_ATTACK("sweep_attack"),
    FALLING_DUST("falling_dust"),
    TOTEM("totem_of_undying"),
    SPIT("spit"),
    SQUID_INK("squid_ink"),
    BUBBLE_POP("bubble_pop"),
    CURRENT_DOWN("current_down"),
    BUBBLE_COLUMN_UP("bubble_column_up"),
    NAUTILUS("nautilus"),
    DOLPHIN("dolphin"),
    SNEEZE("sneeze"),
    CAMPFIRE_COSY_SMOKE("campfire_cosy_smoke"),
    CAMPFIRE_SIGNAL_SMOKE("campfire_signal_smoke"),
    COMPOSTER("composter"),
    FLASH("flash"),
    FALLING_LAVA("falling_lava"),
    LANDING_LAVA("landing_lava"),
    FALLING_WATER("falling_water"),
    DRIPPING_HONEY("dripping_honey"),
    FALLING_HONEY("falling_honey"),
    LANDING_HONEY("landing_honey"),
    FALLING_NECTAR("falling_nectar"),
    SOUL_FIRE_FLAME("soul_fire_flame"),
    ASH("ash"),
    CRIMSON_SPORE("crimson_spore"),
    WARPED_SPORE("warped_spore"),
    SOUL("soul"),
    DRIPPING_OBSIDIAN_TEAR("dripping_obsidian_tear"),
    FALLING_OBSIDIAN_TEAR("falling_obsidian_tear"),
    LANDING_OBSIDIAN_TEAR("landing_obsidian_tear"),
    REVERSE_PORTAL("reverse_portal"),
    WHITE_ASH("white_ash"),
    DUST_COLOR_TRANSITION("dust_color_transition"),
    VIBRATION("vibration"),
    FALLING_SPORE_BLOSSOM("falling_spore_blossom"),
    SPORE_BLOSSOM_AIR("spore_blossom_air"),
    SMALL_FLAME("small_flame"),
    SNOWFLAKE("snowflake"),
    DRIPPING_DRIPSTONE_LAVA("dripping_dripstone_lava"),
    FALLING_DRIPSTONE_LAVA("falling_dripstone_lava"),
    DRIPPING_DRIPSTONE_WATER("dripping_dripstone_water"),
    FALLING_DRIPSTONE_WATER("falling_dripstone_water"),
    GLOW_SQUID_INK("glow_squid_ink"),
    GLOW("glow"),
    WAX_ON("wax_on"),
    WAX_OFF("wax_off"),
    ELECTRIC_SPARK("electric_spark"),
    SCRAPE("scrape"),
    BLOCK_MARKER("block_marker"),
    SONIC_BOOM("sonic_boom"),
    SCULK_SOUL("sculk_soul"),
    SCULK_CHARGE("sculk_charge"),
    SCULK_CHARGE_POP("sculk_charge_pop"),
    SHRIEK("shriek"),
    CHERRY_LEAVES("cherry_leaves"),
    EGG_CRACK("egg_crack"),
    LEGACY_BLOCK_CRACK("block"),
    LEGACY_BLOCK_DUST("block"),
    LEGACY_FALLING_DUST("falling_dust");

    private final ResourceLocation minecraftKey;
    private final Particle bukkit;
    private static final BiMap<Particle, ResourceLocation> particles;
    private static final Map<Particle, Particle> aliases;

    static {
        particles = HashBiMap.create();
        aliases = new HashMap<Particle, Particle>();
        CraftParticle[] craftParticleArray = CraftParticle.values();
        int n = craftParticleArray.length;
        int n2 = 0;
        while (n2 < n) {
            CraftParticle particle = craftParticleArray[n2];
            if (particles.containsValue((Object)particle.minecraftKey)) {
                aliases.put(particle.bukkit, (Particle)((Object)particles.inverse().get((Object)particle.minecraftKey)));
            } else {
                particles.put((Object)particle.bukkit, (Object)particle.minecraftKey);
            }
            ++n2;
        }
    }

    private CraftParticle(String minecraftKey) {
        this.minecraftKey = new ResourceLocation(minecraftKey);
        this.bukkit = Particle.valueOf(this.name());
        Preconditions.checkState((this.bukkit != null ? 1 : 0) != 0, (String)"Bukkit particle %s does not exist", (Object)this.name());
    }

    public static ParticleOptions toNMS(Particle bukkit) {
        return CraftParticle.toNMS(bukkit, null);
    }

    public static <T> ParticleOptions toNMS(Particle particle, T obj) {
        ParticleType nms;
        Particle canonical = particle;
        if (aliases.containsKey((Object)particle)) {
            canonical = aliases.get((Object)particle);
        }
        Preconditions.checkArgument(((nms = (ParticleType)BuiltInRegistries.f_257034_.m_7745_((ResourceLocation)particles.get((Object)canonical))) != null ? 1 : 0) != 0, (String)"No NMS particle %s", (Object)((Object)particle));
        if (particle.getDataType().equals(Void.class)) {
            return (SimpleParticleType)nms;
        }
        Preconditions.checkArgument((obj != null ? 1 : 0) != 0, (String)"Particle %s requires data, null provided", (Object)((Object)particle));
        if (particle.getDataType().equals(ItemStack.class)) {
            ItemStack itemStack = (ItemStack)obj;
            return new ItemParticleOption(nms, CraftItemStack.asNMSCopy(itemStack));
        }
        if (particle.getDataType() == MaterialData.class) {
            MaterialData data = (MaterialData)obj;
            return new BlockParticleOption(nms, CraftMagicNumbers.getBlock(data));
        }
        if (particle.getDataType() == BlockData.class) {
            BlockData data = (BlockData)obj;
            return new BlockParticleOption(nms, ((CraftBlockData)data).getState());
        }
        if (particle.getDataType() == Particle.DustOptions.class) {
            Particle.DustOptions data = (Particle.DustOptions)obj;
            Color color = data.getColor();
            return new DustParticleOptions(new Vector3f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f), data.getSize());
        }
        if (particle.getDataType() == Particle.DustTransition.class) {
            Particle.DustTransition data = (Particle.DustTransition)obj;
            Color from = data.getColor();
            Color to = data.getToColor();
            return new DustColorTransitionOptions(new Vector3f((float)from.getRed() / 255.0f, (float)from.getGreen() / 255.0f, (float)from.getBlue() / 255.0f), new Vector3f((float)to.getRed() / 255.0f, (float)to.getGreen() / 255.0f, (float)to.getBlue() / 255.0f), data.getSize());
        }
        if (particle.getDataType() == Vibration.class) {
            BlockPositionSource source;
            Vibration vibration = (Vibration)obj;
            if (vibration.getDestination() instanceof Vibration.Destination.BlockDestination) {
                Location destination = ((Vibration.Destination.BlockDestination)vibration.getDestination()).getLocation();
                source = new BlockPositionSource(CraftLocation.toBlockPosition(destination));
            } else if (vibration.getDestination() instanceof Vibration.Destination.EntityDestination) {
                Entity destination = ((CraftEntity)((Vibration.Destination.EntityDestination)vibration.getDestination()).getEntity()).getHandle();
                source = new EntityPositionSource(destination, destination.m_20192_());
            } else {
                throw new IllegalArgumentException("Unknown vibration destination " + vibration.getDestination());
            }
            return new VibrationParticleOption((PositionSource)source, vibration.getArrivalTime());
        }
        if (particle.getDataType() == Float.class) {
            return new SculkChargeParticleOptions(((Float)obj).floatValue());
        }
        if (particle.getDataType() == Integer.class) {
            return new ShriekParticleOption(((Integer)obj).intValue());
        }
        throw new IllegalArgumentException(particle.getDataType().toString());
    }

    public static Particle toBukkit(ParticleOptions nms) {
        return CraftParticle.toBukkit(nms.m_6012_());
    }

    public static Particle toBukkit(ParticleType nms) {
        return (Particle)((Object)particles.inverse().get((Object)BuiltInRegistries.f_257034_.m_7981_((Object)nms)));
    }
}

