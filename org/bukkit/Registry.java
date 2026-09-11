/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Predicates
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.bukkit.Art;
import org.bukkit.Bukkit;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.MusicInstrument;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.loot.LootTables;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Registry<T extends Keyed>
extends Iterable<T> {
    public static final Registry<Advancement> ADVANCEMENT = new Registry<Advancement>(){

        @Override
        @Nullable
        public Advancement get(@NotNull NamespacedKey key) {
            return Bukkit.getAdvancement(key);
        }

        @Override
        @NotNull
        public Stream<Advancement> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }

        @Override
        @NotNull
        public Iterator<Advancement> iterator() {
            return Bukkit.advancementIterator();
        }
    };
    public static final Registry<Art> ART = new SimpleRegistry<Art>(Art.class);
    public static final Registry<Attribute> ATTRIBUTE = new SimpleRegistry<Attribute>(Attribute.class);
    public static final Registry<Biome> BIOME = new SimpleRegistry<Biome>(Biome.class);
    public static final Registry<KeyedBossBar> BOSS_BARS = new Registry<KeyedBossBar>(){

        @Override
        @Nullable
        public KeyedBossBar get(@NotNull NamespacedKey key) {
            return Bukkit.getBossBar(key);
        }

        @Override
        @NotNull
        public Stream<KeyedBossBar> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }

        @Override
        @NotNull
        public Iterator<KeyedBossBar> iterator() {
            return Bukkit.getBossBars();
        }
    };
    public static final Registry<Enchantment> ENCHANTMENT = new Registry<Enchantment>(){

        @Override
        @Nullable
        public Enchantment get(@NotNull NamespacedKey key) {
            return Enchantment.getByKey(key);
        }

        @Override
        @NotNull
        public Stream<Enchantment> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }

        @Override
        @NotNull
        public Iterator<Enchantment> iterator() {
            return Arrays.asList(Enchantment.values()).iterator();
        }
    };
    public static final Registry<EntityType> ENTITY_TYPE = new SimpleRegistry<EntityType>(EntityType.class, entity -> entity != EntityType.UNKNOWN);
    public static final Registry<MusicInstrument> INSTRUMENT = Objects.requireNonNull(Bukkit.getRegistry(MusicInstrument.class), "No registry present for MusicInstrument. This is a bug.");
    public static final Registry<LootTables> LOOT_TABLES = new SimpleRegistry<LootTables>(LootTables.class);
    public static final Registry<Material> MATERIAL = new SimpleRegistry<Material>(Material.class, mat -> !mat.isLegacy());
    public static final Registry<Statistic> STATISTIC = new SimpleRegistry<Statistic>(Statistic.class);
    public static final Registry<Structure> STRUCTURE = Bukkit.getRegistry(Structure.class);
    public static final Registry<StructureType> STRUCTURE_TYPE = Bukkit.getRegistry(StructureType.class);
    public static final Registry<Sound> SOUNDS = new SimpleRegistry<Sound>(Sound.class);
    @ApiStatus.Experimental
    public static final Registry<TrimMaterial> TRIM_MATERIAL = Bukkit.getRegistry(TrimMaterial.class);
    @ApiStatus.Experimental
    public static final Registry<TrimPattern> TRIM_PATTERN = Bukkit.getRegistry(TrimPattern.class);
    public static final Registry<Villager.Profession> VILLAGER_PROFESSION = new SimpleRegistry<Villager.Profession>(Villager.Profession.class);
    public static final Registry<Villager.Type> VILLAGER_TYPE = new SimpleRegistry<Villager.Type>(Villager.Type.class);
    public static final Registry<MemoryKey> MEMORY_MODULE_TYPE = new Registry<MemoryKey>(){

        @Override
        @NotNull
        public Iterator iterator() {
            return MemoryKey.values().iterator();
        }

        @Override
        @Nullable
        public MemoryKey get(@NotNull NamespacedKey key) {
            return MemoryKey.getByKey(key);
        }

        @Override
        @NotNull
        public Stream<MemoryKey> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }
    };
    public static final Registry<Fluid> FLUID = new SimpleRegistry<Fluid>(Fluid.class);
    public static final Registry<Frog.Variant> FROG_VARIANT = new SimpleRegistry<Frog.Variant>(Frog.Variant.class);
    public static final Registry<GameEvent> GAME_EVENT = Objects.requireNonNull(Bukkit.getRegistry(GameEvent.class), "No registry present for GameEvent. This is a bug.");

    @Nullable
    public T get(@NotNull NamespacedKey var1);

    @NotNull
    public Stream<T> stream();

    @Nullable
    default public T match(@NotNull String input) {
        Preconditions.checkArgument((input != null ? 1 : 0) != 0, (Object)"input must not be null");
        String filtered = input.toLowerCase().replaceAll("\\s+", "_");
        NamespacedKey namespacedKey = NamespacedKey.fromString(filtered);
        return namespacedKey != null ? (T)this.get(namespacedKey) : null;
    }

    public static final class SimpleRegistry<T extends Enum<T>>
    implements Registry<T> {
        private final Map<NamespacedKey, T> map;

        protected SimpleRegistry(@NotNull Class<T> type) {
            this(type, (Predicate<T>)Predicates.alwaysTrue());
        }

        protected SimpleRegistry(@NotNull Class<T> type, @NotNull Predicate<T> predicate) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            Enum[] enumArray = (Enum[])type.getEnumConstants();
            int n = enumArray.length;
            int n2 = 0;
            while (n2 < n) {
                Enum entry = enumArray[n2];
                if (predicate.test(entry)) {
                    builder.put((Object)((Keyed)((Object)entry)).getKey(), (Object)entry);
                }
                ++n2;
            }
            this.map = builder.build();
        }

        @Override
        @Nullable
        public T get(@NotNull NamespacedKey key) {
            return (T)((Enum)this.map.get(key));
        }

        @Override
        @NotNull
        public Stream<T> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }

        @Override
        @NotNull
        public Iterator<T> iterator() {
            return this.map.values().iterator();
        }
    }
}

