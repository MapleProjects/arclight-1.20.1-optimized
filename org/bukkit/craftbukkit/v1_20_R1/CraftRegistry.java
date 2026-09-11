/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.item.Instrument
 *  net.minecraft.world.item.armortrim.TrimMaterial
 *  net.minecraft.world.item.armortrim.TrimPattern
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.StructureType
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.bukkit.GameEvent;
import org.bukkit.Keyed;
import org.bukkit.MusicInstrument;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.CraftGameEvent;
import org.bukkit.craftbukkit.v1_20_R1.CraftMusicInstrument;
import org.bukkit.craftbukkit.v1_20_R1.generator.structure.CraftStructure;
import org.bukkit.craftbukkit.v1_20_R1.generator.structure.CraftStructureType;
import org.bukkit.craftbukkit.v1_20_R1.inventory.trim.CraftTrimMaterial;
import org.bukkit.craftbukkit.v1_20_R1.inventory.trim.CraftTrimPattern;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.generator.structure.Structure;
import org.jetbrains.annotations.NotNull;

public class CraftRegistry<B extends Keyed, M>
implements Registry<B> {
    private static RegistryAccess registry;
    private final Map<NamespacedKey, B> cache = new HashMap<NamespacedKey, B>();
    private final net.minecraft.core.Registry<M> minecraftRegistry;
    private final BiFunction<NamespacedKey, M, B> minecraftToBukkit;

    public static void setMinecraftRegistry(RegistryAccess registry) {
        Preconditions.checkState((CraftRegistry.registry == null ? 1 : 0) != 0, (Object)"Registry already set");
        CraftRegistry.registry = registry;
    }

    public static RegistryAccess getMinecraftRegistry() {
        return registry;
    }

    public static <E> net.minecraft.core.Registry<E> getMinecraftRegistry(ResourceKey<net.minecraft.core.Registry<E>> key) {
        return CraftRegistry.getMinecraftRegistry().m_175515_(key);
    }

    public static <B extends Keyed> Registry<?> createRegistry(Class<B> bukkitClass, RegistryAccess registryHolder) {
        if (bukkitClass == GameEvent.class) {
            return new CraftRegistry<CraftGameEvent, net.minecraft.world.level.gameevent.GameEvent>(registryHolder.m_175515_(Registries.f_256827_), CraftGameEvent::new);
        }
        if (bukkitClass == MusicInstrument.class) {
            return new CraftRegistry<CraftMusicInstrument, Instrument>(registryHolder.m_175515_(Registries.f_257010_), CraftMusicInstrument::new);
        }
        if (bukkitClass == Structure.class) {
            return new CraftRegistry<CraftStructure, net.minecraft.world.level.levelgen.structure.Structure>(registryHolder.m_175515_(Registries.f_256944_), CraftStructure::new);
        }
        if (bukkitClass == org.bukkit.generator.structure.StructureType.class) {
            return new CraftRegistry<CraftStructureType, StructureType>(BuiltInRegistries.f_256763_, CraftStructureType::new);
        }
        if (bukkitClass == org.bukkit.inventory.meta.trim.TrimMaterial.class) {
            return new CraftRegistry<CraftTrimMaterial, TrimMaterial>(registryHolder.m_175515_(Registries.f_266076_), CraftTrimMaterial::new);
        }
        if (bukkitClass == org.bukkit.inventory.meta.trim.TrimPattern.class) {
            return new CraftRegistry<CraftTrimPattern, TrimPattern>(registryHolder.m_175515_(Registries.f_266063_), CraftTrimPattern::new);
        }
        return null;
    }

    public CraftRegistry(net.minecraft.core.Registry<M> minecraftRegistry, BiFunction<NamespacedKey, M, B> minecraftToBukkit) {
        this.minecraftRegistry = minecraftRegistry;
        this.minecraftToBukkit = minecraftToBukkit;
    }

    @Override
    public B get(NamespacedKey namespacedKey) {
        Keyed cached = (Keyed)this.cache.get(namespacedKey);
        if (cached != null) {
            return (B)cached;
        }
        B bukkit = this.createBukkit(namespacedKey, this.minecraftRegistry.m_6612_(CraftNamespacedKey.toMinecraft(namespacedKey)).orElse(null));
        if (bukkit == null) {
            return null;
        }
        this.cache.put(namespacedKey, bukkit);
        return bukkit;
    }

    @Override
    @NotNull
    public Stream<B> stream() {
        return this.minecraftRegistry.m_6566_().stream().map(minecraftKey -> this.get(CraftNamespacedKey.fromMinecraft(minecraftKey)));
    }

    @Override
    public Iterator<B> iterator() {
        return this.stream().iterator();
    }

    public B createBukkit(NamespacedKey namespacedKey, M minecraft) {
        if (minecraft == null) {
            return null;
        }
        return (B)((Keyed)this.minecraftToBukkit.apply(namespacedKey, minecraft));
    }
}

