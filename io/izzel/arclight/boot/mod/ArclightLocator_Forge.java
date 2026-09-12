/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.jarhandling.JarMetadata
 *  cpw.mods.jarhandling.SecureJar
 *  cpw.mods.jarhandling.SecureJar$Status
 *  cpw.mods.jarhandling.impl.SimpleJarMetadata
 *  net.minecraftforge.forgespi.language.IModFileInfo
 *  net.minecraftforge.forgespi.locating.IModFile
 *  net.minecraftforge.forgespi.locating.IModLocator
 *  net.minecraftforge.forgespi.locating.IModLocator$ModFileOrException
 *  net.minecraftforge.forgespi.locating.IModProvider
 *  net.minecraftforge.forgespi.locating.ModFileFactory$ModFileInfoParser
 */
package io.izzel.arclight.boot.mod;

import cpw.mods.jarhandling.JarMetadata;
import cpw.mods.jarhandling.SecureJar;
import cpw.mods.jarhandling.impl.SimpleJarMetadata;
import io.izzel.arclight.boot.mod.ArclightJarInJarAdaptor;
import io.izzel.arclight.boot.mod.ModBootstrap;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.forgespi.locating.IModLocator;
import net.minecraftforge.forgespi.locating.IModProvider;
import net.minecraftforge.forgespi.locating.ModFileFactory;

public class ArclightLocator_Forge
implements IModLocator {
    private final IModFile arclight;
    private static final Set<String> EXCLUDES = Set.of("net.minecraft.world.level.block");

    public ArclightLocator_Forge() {
        ModBootstrap.run();
        this.arclight = this.loadJar();
    }

    public List<IModLocator.ModFileOrException> scanMods() {
        ArclightJarInJarAdaptor.inject();
        return List.of(new IModLocator.ModFileOrException(this.arclight, null));
    }

    public String name() {
        return "arclight";
    }

    public void scanFile(IModFile file, Consumer<Path> pathConsumer) {
        Function<Path, SecureJar.Status> status = p -> file.getSecureJar().verifyPath(p);
        try (Stream<Path> files = Files.find(file.getSecureJar().getRootPath(), Integer.MAX_VALUE, (p, a) -> p.getNameCount() > 0 && p.getFileName().toString().endsWith(".class"), new FileVisitOption[0]);){
            file.setSecurityStatus(files.peek(pathConsumer).map(status).reduce((s1, s2) -> SecureJar.Status.values()[Math.min(s1.ordinal(), s2.ordinal())]).orElse(SecureJar.Status.INVALID));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initArguments(Map<String, ?> arguments) {
    }

    public boolean isValid(IModFile modFile) {
        return true;
    }

    protected IModFile loadJar() {
        try {
            Class<?> cl = Class.forName("net.minecraftforge.fml.loading.moddiscovery.ModFile");
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle handle = lookup.findConstructor(cl, MethodType.methodType(Void.TYPE, SecureJar.class, IModProvider.class, ModFileFactory.ModFileInfoParser.class));
            String version = System.getProperty("arclight.version");
            Path path = Paths.get(".arclight", "mod_file", version + ".jar");
            Class<?> parserCl = Class.forName("net.minecraftforge.fml.loading.moddiscovery.ModFileParser");
            MethodHandle modsToml = lookup.findStatic(parserCl, "modsTomlParser", MethodType.methodType(IModFileInfo.class, IModFile.class));
            ModFileFactory.ModFileInfoParser parser = modFile -> {
                try {
                    return modsToml.invoke(modFile);
                }
                catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            };
            return handle.invoke(SecureJar.from(it -> this.excludePackages((SecureJar)it, version), (Path[])new Path[]{path}), this, parser);
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private JarMetadata excludePackages(SecureJar secureJar, String version) {
        secureJar.getPackages().removeIf(it -> EXCLUDES.stream().anyMatch(it::startsWith));
        return new SimpleJarMetadata("arclight", version.substring(version.indexOf(45) + 1), secureJar.getPackages(), List.of());
    }
}

