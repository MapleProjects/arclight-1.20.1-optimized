/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  io.izzel.arclight.api.PluginPatcher
 *  io.izzel.arclight.api.Unsafe
 *  net.md_5.specialsource.InheritanceMap
 *  net.md_5.specialsource.JarMapping
 *  net.md_5.specialsource.JarRemapper
 *  net.md_5.specialsource.provider.ClassLoaderProvider
 *  net.md_5.specialsource.provider.InheritanceProvider
 *  net.md_5.specialsource.provider.JointProvider
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.jul.LogManager
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.izzel.arclight.api.PluginPatcher;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.util.log.ArclightI18nLogger;
import io.izzel.arclight.common.mod.util.remapper.ArclightInterfaceInvokerGen;
import io.izzel.arclight.common.mod.util.remapper.ArclightRedirectAdapter;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderAdapter;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.LenientJarRemapper;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import io.izzel.arclight.common.mod.util.remapper.patcher.ArclightPluginPatcher;
import io.izzel.arclight.common.mod.util.remapper.patcher.PluginLoggerTransformer;
import io.izzel.arclight.common.mod.util.remapper.resource.RemapSourceHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.md_5.specialsource.InheritanceMap;
import net.md_5.specialsource.JarMapping;
import net.md_5.specialsource.JarRemapper;
import net.md_5.specialsource.provider.ClassLoaderProvider;
import net.md_5.specialsource.provider.InheritanceProvider;
import net.md_5.specialsource.provider.JointProvider;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.jul.LogManager;

public class ArclightRemapper {
    public static final ArclightRemapper INSTANCE;
    public static final File DUMP;
    public static final Function<byte[], byte[]> SWITCH_TABLE_FIXER;
    private final JarMapping toNmsMapping;
    private final JarMapping toBukkitMapping;
    public final InheritanceMap inheritanceMap;
    private final List<PluginTransformer> transformerList = new ArrayList<PluginTransformer>();
    private final JarRemapper toBukkitRemapper;
    private final JarRemapper toNmsRemapper;
    private final List<PluginPatcher> patchers;
    private static long pkgOffset;
    private static long clOffset;
    private static long mdOffset;
    private static long fdOffset;
    private static long mapOffset;

    public ArclightRemapper() throws Exception {
        this.toNmsMapping = new JarMapping();
        this.toBukkitMapping = new JarMapping();
        this.inheritanceMap = new InheritanceMap();
        this.toNmsMapping.loadMappings(new BufferedReader(new InputStreamReader(ArclightRemapper.class.getResourceAsStream("/bukkit_srg.srg"))), null, null, false);
        String content = new String(ArclightRemapper.class.getResourceAsStream("/bukkit_srg.srg").readAllBytes(), StandardCharsets.UTF_8);
        int i = content.indexOf("net/minecraft/server/level/ChunkMap net/minecraft/server/level/ChunkTracker");
        String nextSection = content.substring(i).lines().skip(1L).dropWhile(it -> it.startsWith("\t")).findFirst().orElseThrow();
        int nextIndex = content.indexOf(nextSection);
        this.toBukkitMapping.loadMappings(new BufferedReader(new StringReader(content.substring(0, i) + content.substring(nextIndex))), null, null, true);
        this.toBukkitMapping.loadMappings(new BufferedReader(new StringReader(content.substring(i, nextIndex))), null, null, true);
        BiMap inverseClassMap = HashBiMap.create((Map)this.toNmsMapping.classes).inverse();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ArclightRemapper.class.getResourceAsStream("/inheritanceMap.txt")));){
            this.inheritanceMap.load(reader, inverseClassMap);
        }
        JointProvider inheritanceProvider = new JointProvider();
        inheritanceProvider.add((InheritanceProvider)this.inheritanceMap);
        inheritanceProvider.add((InheritanceProvider)new ClassLoaderProvider(ClassLoader.getSystemClassLoader()));
        this.toNmsMapping.setFallbackInheritanceProvider((InheritanceProvider)inheritanceProvider);
        this.toBukkitMapping.setFallbackInheritanceProvider((InheritanceProvider)inheritanceProvider);
        this.transformerList.add(ArclightInterfaceInvokerGen.INSTANCE);
        this.transformerList.add(ArclightRedirectAdapter.INSTANCE);
        this.transformerList.add(ClassLoaderAdapter.INSTANCE);
        if (!(java.util.logging.LogManager.getLogManager() instanceof LogManager)) {
            this.transformerList.add(new PluginLoggerTransformer());
        }
        this.patchers = ArclightPluginPatcher.load(this.transformerList);
        this.toBukkitMapping.setFallbackInheritanceProvider((InheritanceProvider)GlobalClassRepo.inheritanceProvider());
        this.toBukkitRemapper = new LenientJarRemapper(this.toBukkitMapping);
        this.toNmsRemapper = new LenientJarRemapper(this.toNmsMapping);
        RemapSourceHandler.register();
    }

    public static ClassLoaderRemapper createClassLoaderRemapper(ClassLoader classLoader) {
        return new ClassLoaderRemapper(INSTANCE.copyOf(ArclightRemapper.INSTANCE.toNmsMapping), INSTANCE.copyOf(ArclightRemapper.INSTANCE.toBukkitMapping), classLoader);
    }

    public static JarRemapper getResourceMapper() {
        return ArclightRemapper.INSTANCE.toBukkitRemapper;
    }

    public static JarRemapper getNmsMapper() {
        return ArclightRemapper.INSTANCE.toNmsRemapper;
    }

    public List<PluginTransformer> getTransformerList() {
        return this.transformerList;
    }

    public List<PluginPatcher> getPatchers() {
        return this.patchers;
    }

    private JarMapping copyOf(JarMapping mapping) {
        JarMapping jarMapping = new JarMapping();
        Unsafe.putObject((Object)jarMapping, (long)pkgOffset, (Object)Unsafe.getObject((Object)mapping, (long)pkgOffset));
        Unsafe.putObject((Object)jarMapping, (long)clOffset, (Object)Unsafe.getObject((Object)mapping, (long)clOffset));
        Unsafe.putObject((Object)jarMapping, (long)mdOffset, (Object)Unsafe.getObject((Object)mapping, (long)mdOffset));
        Unsafe.putObject((Object)jarMapping, (long)fdOffset, (Object)Unsafe.getObject((Object)mapping, (long)fdOffset));
        Unsafe.putObject((Object)jarMapping, (long)mapOffset, (Object)Unsafe.getObject((Object)mapping, (long)mapOffset));
        return jarMapping;
    }

    static {
        ArclightI18nLogger.getLogger("Arclight").info("loading-mapping");
        try {
            INSTANCE = new ArclightRemapper();
            String property = System.getProperty("arclight.remapper.dump");
            if (property != null) {
                DUMP = new File(property);
                if (!DUMP.exists()) {
                    DUMP.mkdirs();
                }
                try {
                    FileUtils.forceDelete((File)DUMP);
                }
                catch (IOException iOException) {}
            } else {
                DUMP = null;
            }
            SWITCH_TABLE_FIXER = (Function)Class.forName("io.izzel.arclight.boot.asm.SwitchTableFixer").getField("INSTANCE").get(null);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            pkgOffset = Unsafe.objectFieldOffset((Field)JarMapping.class.getField("packages"));
            clOffset = Unsafe.objectFieldOffset((Field)JarMapping.class.getField("classes"));
            mdOffset = Unsafe.objectFieldOffset((Field)JarMapping.class.getField("methods"));
            fdOffset = Unsafe.objectFieldOffset((Field)JarMapping.class.getField("fields"));
            mapOffset = Unsafe.objectFieldOffset((Field)JarMapping.class.getDeclaredField("inheritanceMap"));
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

