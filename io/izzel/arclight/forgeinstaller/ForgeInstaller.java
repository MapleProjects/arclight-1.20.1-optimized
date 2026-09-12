/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package io.izzel.arclight.forgeinstaller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.forgeinstaller.FileDownloader;
import io.izzel.arclight.forgeinstaller.InstallInfo;
import io.izzel.arclight.forgeinstaller.MavenDownloader;
import io.izzel.arclight.forgeinstaller.Mirrors;
import io.izzel.arclight.forgeinstaller.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.lang.module.ResolvedModule;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.security.AccessControlContext;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

public class ForgeInstaller {
    private static final MethodHandles.Lookup IMPL_LOOKUP = Unsafe.lookup();

    public static List<Path> modInstall(Consumer<String> logger) throws Throwable {
        InputStream stream = ForgeInstaller.class.getModule().getResourceAsStream("/META-INF/installer.json");
        InstallInfo installInfo = (InstallInfo)new Gson().fromJson((Reader)new InputStreamReader(stream), InstallInfo.class);
        List<Supplier<Path>> suppliers = ForgeInstaller.checkMavenNoSource(installInfo.libraries);
        if (!suppliers.isEmpty()) {
            logger.accept("Downloading missing libraries ...");
            ExecutorService pool = Executors.newFixedThreadPool(8);
            CompletableFuture[] array = (CompletableFuture[])suppliers.stream().map(ForgeInstaller.reportSupply(pool, logger)).toArray(CompletableFuture[]::new);
            ForgeInstaller.handleFutures(logger, array);
            pool.shutdownNow();
        }
        return installInfo.libraries.keySet().stream().map(it -> Paths.get("libraries", new String[0]).resolve(Util.mavenToPath(it))).collect(Collectors.toList());
    }

    public static Map.Entry<String, List<String>> applicationInstall() throws Throwable {
        boolean installForge;
        InputStream stream = ForgeInstaller.class.getResourceAsStream("/META-INF/installer.json");
        InstallInfo installInfo = (InstallInfo)new Gson().fromJson((Reader)new InputStreamReader(stream), InstallInfo.class);
        List<Supplier<Path>> suppliers = ForgeInstaller.checkMavenNoSource(installInfo.libraries);
        String sysType = File.pathSeparatorChar == ';' ? "win" : "unix";
        Path path = Paths.get("libraries", "net", "minecraftforge", "forge", installInfo.installer.minecraft + "-" + installInfo.installer.forge, sysType + "_args.txt");
        boolean bl = installForge = !Files.exists(path, new LinkOption[0]) || ForgeInstaller.forgeClasspathMissing(path);
        if (!suppliers.isEmpty() || installForge) {
            System.out.println("Downloading missing libraries ...");
            ExecutorService pool = Executors.newWorkStealingPool(8);
            CompletableFuture[] array = (CompletableFuture[])suppliers.stream().map(ForgeInstaller.reportSupply(pool, System.out::println)).toArray(CompletableFuture[]::new);
            if (installForge) {
                CompletableFuture<Path>[] futures = ForgeInstaller.installForge(installInfo, pool, System.out::println);
                ForgeInstaller.handleFutures(System.out::println, futures);
                System.out.println("Forge installation is starting, please wait... ");
                try {
                    ProcessBuilder builder = new ProcessBuilder(new String[0]);
                    File file = new File(System.getProperty("java.home"), "bin/java");
                    builder.command(file.getCanonicalPath(), "-Djava.net.useSystemProxies=true", "-jar", futures[0].join().toString(), "--installServer", ".", "--debug");
                    builder.inheritIO();
                    Process process = builder.start();
                    if (process.waitFor() > 0) {
                        throw new Exception("Forge installation failed");
                    }
                }
                catch (IOException e) {
                    try (URLClassLoader loader = new URLClassLoader(new URL[]{new File(String.format("forge-%s-%s-installer.jar", installInfo.installer.minecraft, installInfo.installer.forge)).toURI().toURL()}, ForgeInstaller.class.getClassLoader().getParent());){
                        Method method = loader.loadClass("net.minecraftforge.installer.SimpleInstaller").getMethod("main", String[].class);
                        method.invoke(null, new Object[]{new String[]{"--installServer", ".", "--debug"}});
                    }
                }
            }
            ForgeInstaller.handleFutures(System.out::println, array);
            pool.shutdownNow();
        }
        return ForgeInstaller.classpath(path, installInfo);
    }

    private static Function<Supplier<Path>, CompletableFuture<Path>> reportSupply(ExecutorService service, Consumer<String> logger) {
        return it -> CompletableFuture.supplyAsync(it, service).thenApply(path -> {
            logger.accept("Downloaded " + String.valueOf(path));
            return path;
        });
    }

    private static CompletableFuture<Path>[] installForge(InstallInfo info, ExecutorService pool, Consumer<String> logger) {
        CompletableFuture<MinecraftData> minecraftData = CompletableFuture.supplyAsync(() -> {
            logger.accept("Downloading mc version manifest...");
            Iterator<Map.Entry<String, String>> iterator = Mirrors.getVersionManifest().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    InputStream stream = FileDownloader.read(entry.getValue());
                    try {
                        byte[] bytes = stream.readAllBytes();
                        JsonObject element = new JsonParser().parse(new String(bytes, StandardCharsets.UTF_8)).getAsJsonObject();
                        JsonArray versions = element.getAsJsonArray("versions");
                        for (JsonElement version : versions) {
                            String id = version.getAsJsonObject().get("id").getAsString();
                            if (!Objects.equals(id, info.installer.minecraft)) continue;
                            String url = version.getAsJsonObject().get("url").getAsString();
                            try (InputStream versionStream = FileDownloader.read(url);){
                                JsonObject object = new JsonParser().parse(new String(versionStream.readAllBytes(), StandardCharsets.UTF_8)).getAsJsonObject();
                                JsonObject downloads = object.getAsJsonObject("downloads");
                                JsonObject server = downloads.getAsJsonObject("server");
                                String serverUrl = server.get("url").getAsString();
                                String serverHash = server.get("sha1").getAsString();
                                JsonObject mapping = downloads.getAsJsonObject("server_mappings");
                                String mappingUrl = mapping.get("url").getAsString();
                                String mappingHash = mapping.get("sha1").getAsString();
                                logger.accept("Minecraft version: %s, server: %s, mappings: %s".formatted(info.installer.minecraft, serverHash, mappingHash));
                                MinecraftData minecraftData = new MinecraftData(entry.getKey(), Mirrors.mapMojangMirror(serverUrl, entry.getKey()), serverHash, Mirrors.mapMojangMirror(mappingUrl, entry.getKey()), mappingHash);
                                return minecraftData;
                            }
                        }
                        logger.accept("Version %s not available in %s".formatted(info.installer.minecraft, entry.getKey()));
                    }
                    finally {
                        if (stream == null) continue;
                        stream.close();
                    }
                }
                catch (Exception e) {
                    logger.accept("Failed to download manifest from " + entry.getKey() + "\n  " + String.valueOf(e));
                }
            }
            return null;
        }, pool);
        String coord = String.format("net.minecraftforge:forge:%s-%s:installer", info.installer.minecraft, info.installer.forge);
        String dist = String.format("forge-%s-%s-installer.jar", info.installer.minecraft, info.installer.forge);
        MavenDownloader forge = new MavenDownloader(Mirrors.getMavenRepo(), coord, dist, info.installer.hash);
        CompletionStage installerFuture = ForgeInstaller.reportSupply(pool, logger).apply(forge).thenCombineAsync(minecraftData, (path, data) -> {
            try (JarFile jarFile = new JarFile(path.toFile());){
                HashMap<String, Map.Entry<String, String>> map = new HashMap<String, Map.Entry<String, String>>();
                ZipEntry profile = jarFile.getEntry("install_profile.json");
                map.putAll(ForgeInstaller.profileLibraries(new InputStreamReader(jarFile.getInputStream(profile)), info.installer.minecraft, data));
                ZipEntry version = jarFile.getEntry("version.json");
                map.putAll(ForgeInstaller.profileLibraries(new InputStreamReader(jarFile.getInputStream(version)), info.installer.minecraft, data));
                List<Supplier<Path>> suppliers = ForgeInstaller.checkMaven(map);
                CompletableFuture[] array = (CompletableFuture[])suppliers.stream().map(ForgeInstaller.reportSupply(pool, logger)).toArray(CompletableFuture[]::new);
                ForgeInstaller.handleFutures(logger, array);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return ForgeInstaller.stripDownloadMapping(path, logger);
        });
        CompletionStage serverFuture = minecraftData.thenCompose(data -> ForgeInstaller.reportSupply(pool, logger).apply(new FileDownloader(String.format(data.serverUrl, info.installer.minecraft), String.format("libraries/net/minecraft/server/%1$s/server-%1$s.jar", info.installer.minecraft), data.serverHash)));
        return new CompletableFuture[]{installerFuture, serverFuture};
    }

    private static Path stripDownloadMapping(Path installer, Consumer<String> logger) {
        try {
            logger.accept("Processing forge installer...");
            Path path = Paths.get(".arclight", "installer_stripped.jar");
            if (!Files.isDirectory(path.getParent(), new LinkOption[0])) {
                Files.createDirectories(path.getParent(), new FileAttribute[0]);
            }
            Files.deleteIfExists(path);
            try (JarFile from = new JarFile(installer.toFile());
                 JarOutputStream to = new JarOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE));){
                Enumeration<JarEntry> entries = from.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.endsWith(".SF") || name.endsWith(".DSA") || name.endsWith(".RSA")) continue;
                    if (name.equals("install_profile.json")) {
                        JsonElement element = new JsonParser().parse((Reader)new InputStreamReader(from.getInputStream(entry)));
                        JsonArray processors = element.getAsJsonObject().getAsJsonArray("processors");
                        block13: for (int i = 0; i < processors.size(); ++i) {
                            JsonObject processor = processors.get(i).getAsJsonObject();
                            JsonArray args = processor.getAsJsonArray("args");
                            for (JsonElement arg : args) {
                                if (!arg.getAsString().equals("DOWNLOAD_MOJMAPS")) continue;
                                processors.remove(i);
                                break block13;
                            }
                        }
                        to.putNextEntry(entry);
                        to.write(new Gson().toJson(element).getBytes(StandardCharsets.UTF_8));
                        continue;
                    }
                    to.putNextEntry(entry);
                    from.getInputStream(entry).transferTo(to);
                }
            }
            return path;
        }
        catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    private static void handleFutures(Consumer<String> logger, CompletableFuture<?> ... futures) {
        for (CompletableFuture<?> future : futures) {
            try {
                future.join();
            }
            catch (CompletionException e) {
                logger.accept(e.getCause().toString());
                Unsafe.throwException(e.getCause());
            }
            catch (Exception e) {
                e.printStackTrace();
                Unsafe.throwException(e);
            }
        }
    }

    private static Map<String, Map.Entry<String, String>> profileLibraries(Reader reader, String minecraft, MinecraftData minecraftData) throws IOException {
        JsonObject data;
        HashMap<String, Map.Entry<String, String>> ret = new HashMap<String, Map.Entry<String, String>>();
        JsonObject object = new JsonParser().parse(reader).getAsJsonObject();
        JsonArray array = object.getAsJsonArray("libraries");
        for (JsonElement element : array) {
            String name = element.getAsJsonObject().get("name").getAsString();
            JsonObject artifact = element.getAsJsonObject().getAsJsonObject("downloads").getAsJsonObject("artifact");
            String hash = artifact.get("sha1").getAsString();
            String url = artifact.get("url").getAsString();
            if (url == null || url.trim().isEmpty()) continue;
            ret.put(name, new AbstractMap.SimpleImmutableEntry<String, String>(hash, url));
        }
        if (object.has("data") && (data = object.getAsJsonObject("data")).has("MOJMAPS")) {
            String serverMapping = data.getAsJsonObject("MOJMAPS").get("server").getAsString();
            ret.put(serverMapping.substring(1, serverMapping.length() - 1), new AbstractMap.SimpleImmutableEntry<String, String>(minecraftData.mappingHash, minecraftData.mappingUrl));
        }
        return ret;
    }

    private static List<Supplier<Path>> checkMavenNoSource(Map<String, String> map) {
        LinkedHashMap<String, Map.Entry<String, String>> hashMap = new LinkedHashMap<String, Map.Entry<String, String>>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), new AbstractMap.SimpleImmutableEntry<String, Object>(entry.getValue(), null));
        }
        return ForgeInstaller.checkMaven(hashMap);
    }

    private static List<Supplier<Path>> checkMaven(Map<String, Map.Entry<String, String>> map) {
        ArrayList<Supplier<Path>> incomplete = new ArrayList<Supplier<Path>>();
        for (Map.Entry<String, Map.Entry<String, String>> entry : map.entrySet()) {
            String maven = entry.getKey();
            String hash = entry.getValue().getKey();
            String url = entry.getValue().getValue();
            String path = "libraries/" + Util.mavenToPath(maven);
            if (new File(path).exists()) {
                try {
                    String fileHash = Util.hash(path);
                    if (fileHash.equals(hash)) continue;
                    incomplete.add(new MavenDownloader(Mirrors.getMavenRepo(), maven, path, hash, url));
                }
                catch (Exception e) {
                    incomplete.add(new MavenDownloader(Mirrors.getMavenRepo(), maven, path, hash, url));
                }
                continue;
            }
            incomplete.add(new MavenDownloader(Mirrors.getMavenRepo(), maven, path, hash, url));
        }
        return incomplete;
    }

    private static boolean forgeClasspathMissing(Path path) throws Exception {
        for (String arg : Files.lines(path).toList()) {
            String classpath;
            String modules;
            if (!(arg.startsWith("-p ") ? !Arrays.stream((modules = arg.substring(2).trim()).split(File.pathSeparator)).map(x$0 -> Paths.get(x$0, new String[0])).allMatch(x$0 -> Files.exists(x$0, new LinkOption[0])) : arg.startsWith("-DlegacyClassPath") && !Arrays.stream((classpath = arg.substring("-DlegacyClassPath=".length()).trim()).split(File.pathSeparator)).map(x$0 -> Paths.get(x$0, new String[0])).allMatch(x$0 -> Files.exists(x$0, new LinkOption[0])))) continue;
            return true;
        }
        return false;
    }

    private static Map.Entry<String, List<String>> classpath(Path path, InstallInfo installInfo) throws Throwable {
        boolean jvmArgs = true;
        String mainClass = null;
        ArrayList<String> userArgs = new ArrayList<String>();
        ArrayList<String> opens = new ArrayList<String>();
        ArrayList<String> exports = new ArrayList<String>();
        exports.add("cpw.mods.bootstraplauncher/cpw.mods.bootstraplauncher=ALL-UNNAMED");
        ArrayList<String> ignores = new ArrayList<String>();
        ArrayList merges = new ArrayList();
        Path self = new File(ForgeInstaller.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toPath();
        for (String arg : Files.lines(path).toList()) {
            if (jvmArgs && arg.startsWith("-")) {
                if (arg.startsWith("-p ")) {
                    ForgeInstaller.addModules(arg.substring(2).trim());
                    continue;
                }
                if (arg.startsWith("--add-opens ")) {
                    opens.add(arg.substring("--add-opens ".length()).trim());
                    continue;
                }
                if (arg.startsWith("--add-exports ")) {
                    exports.add(arg.substring("--add-exports ".length()).trim());
                    continue;
                }
                if (!arg.startsWith("-D")) continue;
                String[] split = arg.substring(2).split("=", 2);
                if (split[0].equals("legacyClassPath")) {
                    split[1] = Stream.concat(Stream.concat(Stream.concat(Stream.of(self.toString()), Arrays.stream(split[1].split(File.pathSeparator))), installInfo.libraries.keySet().stream().map(it -> Paths.get("libraries", Util.mavenToPath(it))).peek(it -> {
                        String name = it.getFileName().toString();
                        if (name.contains("maven-model")) {
                            merges.add(name);
                        }
                    }).map(Path::toString)), Stream.empty()).sorted((a, b) -> {
                        if (a.contains("maven-repository-metadata")) {
                            return -1;
                        }
                        if (b.contains("maven-repository-metadata")) {
                            return 1;
                        }
                        return 0;
                    }).collect(Collectors.joining(File.pathSeparator));
                } else if (split[0].equals("ignoreList")) {
                    ignores.addAll(Arrays.asList(split[1].split(",")));
                }
                System.setProperty(split[0], split[1]);
                continue;
            }
            if (jvmArgs) {
                jvmArgs = false;
                mainClass = arg;
                continue;
            }
            userArgs.addAll(Arrays.asList(arg.split(" ")));
        }
        String merge = String.join((CharSequence)",", merges);
        String mergeModules = System.getProperty("mergeModules");
        if (mergeModules != null) {
            System.setProperty("mergeModules", mergeModules + ";" + merge);
        } else {
            System.setProperty("mergeModules", merge);
        }
        ForgeInstaller.addOpens(opens);
        ForgeInstaller.addExports(exports);
        return Map.entry(Objects.requireNonNull(mainClass, "No main class found"), userArgs);
    }

    public static void addToPath(Path path) {
        try {
            Field ucpField;
            ClassLoader loader = ClassLoader.getPlatformClassLoader();
            try {
                ucpField = loader.getClass().getDeclaredField("ucp");
            }
            catch (NoSuchFieldException e) {
                ucpField = loader.getClass().getSuperclass().getDeclaredField("ucp");
            }
            long offset = Unsafe.objectFieldOffset(ucpField);
            Object ucp = Unsafe.getObject(loader, offset);
            if (ucp == null) {
                Class<?> cl = Class.forName("jdk.internal.loader.URLClassPath");
                MethodHandle handle = Unsafe.lookup().findConstructor(cl, MethodType.methodType(Void.TYPE, URL[].class, AccessControlContext.class));
                ucp = handle.invoke(new URL[0], null);
                Unsafe.putObjectVolatile(loader, offset, ucp);
            }
            Method method = ucp.getClass().getDeclaredMethod("addURL", URL.class);
            Unsafe.lookup().unreflect(method).invoke(ucp, path.toUri().toURL());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void addExports(List<String> exports) throws Throwable {
        MethodHandle implAddExportsMH = IMPL_LOOKUP.findVirtual(Module.class, "implAddExports", MethodType.methodType(Void.TYPE, String.class, Module.class));
        MethodHandle implAddExportsToAllUnnamedMH = IMPL_LOOKUP.findVirtual(Module.class, "implAddExportsToAllUnnamed", MethodType.methodType(Void.TYPE, String.class));
        ForgeInstaller.addExtra(exports, implAddExportsMH, implAddExportsToAllUnnamedMH);
    }

    public static void addOpens(List<String> opens) throws Throwable {
        MethodHandle implAddOpensMH = IMPL_LOOKUP.findVirtual(Module.class, "implAddOpens", MethodType.methodType(Void.TYPE, String.class, Module.class));
        MethodHandle implAddOpensToAllUnnamedMH = IMPL_LOOKUP.findVirtual(Module.class, "implAddOpensToAllUnnamed", MethodType.methodType(Void.TYPE, String.class));
        ForgeInstaller.addExtra(opens, implAddOpensMH, implAddOpensToAllUnnamedMH);
    }

    private static ParserData parseModuleExtra(String extra) {
        String[] all = extra.split("=", 2);
        if (all.length < 2) {
            return null;
        }
        String[] source = all[0].split("/", 2);
        if (source.length < 2) {
            return null;
        }
        return new ParserData(source[0], source[1], all[1]);
    }

    private static void addExtra(List<String> extras, MethodHandle implAddExtraMH, MethodHandle implAddExtraToAllUnnamedMH) {
        extras.forEach(extra -> {
            ParserData data = ForgeInstaller.parseModuleExtra(extra);
            if (data != null) {
                ModuleLayer.boot().findModule(data.module).ifPresent(m -> {
                    try {
                        if ("ALL-UNNAMED".equals(data.target)) {
                            implAddExtraToAllUnnamedMH.invokeWithArguments(m, data.packages);
                        } else {
                            ModuleLayer.boot().findModule(data.target).ifPresent(tm -> {
                                try {
                                    implAddExtraMH.invokeWithArguments(m, data.packages, tm);
                                }
                                catch (Throwable t) {
                                    throw new RuntimeException(t);
                                }
                            });
                        }
                    }
                    catch (Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
            }
        });
    }

    private static void addModules(String modulePath) throws Throwable {
        ModuleFinder finder = ModuleFinder.of((Path[])Arrays.stream(modulePath.split(File.pathSeparator)).map(x$0 -> Paths.get(x$0, new String[0])).peek(ForgeInstaller::addToPath).toArray(Path[]::new));
        MethodHandle loadModuleMH = IMPL_LOOKUP.findVirtual(Class.forName("jdk.internal.loader.BuiltinClassLoader"), "loadModule", MethodType.methodType(Void.TYPE, ModuleReference.class));
        Configuration config = Configuration.resolveAndBind(finder, List.of(ModuleLayer.boot().configuration()), finder, finder.findAll().stream().peek(mref -> {
            try {
                loadModuleMH.invokeWithArguments(ClassLoader.getSystemClassLoader(), mref);
            }
            catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }).map(ModuleReference::descriptor).map(ModuleDescriptor::name).collect(Collectors.toList()));
        MethodHandle graphGetter = IMPL_LOOKUP.findGetter(Configuration.class, "graph", Map.class);
        HashMap<ResolvedModule, Set> graphMap = new HashMap<ResolvedModule, Set>((Map)graphGetter.invokeWithArguments(config));
        MethodHandle cfSetter = IMPL_LOOKUP.findSetter(ResolvedModule.class, "cf", Configuration.class);
        graphMap.forEach((k, v) -> {
            try {
                cfSetter.invokeWithArguments(k, ModuleLayer.boot().configuration());
                v.forEach(m -> {
                    try {
                        cfSetter.invokeWithArguments(m, ModuleLayer.boot().configuration());
                    }
                    catch (Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                });
            }
            catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        graphMap.putAll((Map)graphGetter.invokeWithArguments(ModuleLayer.boot().configuration()));
        IMPL_LOOKUP.findSetter(Configuration.class, "graph", Map.class).invokeWithArguments(ModuleLayer.boot().configuration(), new HashMap(graphMap));
        Set<ResolvedModule> oldBootModules = ModuleLayer.boot().configuration().modules();
        MethodHandle modulesSetter = IMPL_LOOKUP.findSetter(Configuration.class, "modules", Set.class);
        HashSet<ResolvedModule> modulesSet = new HashSet<ResolvedModule>(config.modules());
        modulesSetter.invokeWithArguments(ModuleLayer.boot().configuration(), new HashSet<ResolvedModule>(modulesSet));
        MethodHandle nameToModuleGetter = IMPL_LOOKUP.findGetter(Configuration.class, "nameToModule", Map.class);
        HashMap nameToModuleMap = new HashMap((Map)nameToModuleGetter.invokeWithArguments(ModuleLayer.boot().configuration()));
        nameToModuleMap.putAll((Map)nameToModuleGetter.invokeWithArguments(config));
        IMPL_LOOKUP.findSetter(Configuration.class, "nameToModule", Map.class).invokeWithArguments(ModuleLayer.boot().configuration(), new HashMap(nameToModuleMap));
        ((Map)IMPL_LOOKUP.findGetter(ModuleLayer.class, "nameToModule", Map.class).invokeWithArguments(ModuleLayer.boot())).putAll((Map)IMPL_LOOKUP.findStatic(Module.class, "defineModules", MethodType.methodType(Map.class, Configuration.class, Function.class, ModuleLayer.class)).invokeWithArguments(ModuleLayer.boot().configuration(), name -> ClassLoader.getSystemClassLoader(), ModuleLayer.boot()));
        modulesSet.addAll(oldBootModules);
        modulesSetter.invokeWithArguments(ModuleLayer.boot().configuration(), new HashSet<ResolvedModule>(modulesSet));
        IMPL_LOOKUP.findSetter(ModuleLayer.class, "modules", Set.class).invokeWithArguments(ModuleLayer.boot(), null);
        IMPL_LOOKUP.findSetter(ModuleLayer.class, "servicesCatalog", Class.forName("jdk.internal.module.ServicesCatalog")).invokeWithArguments(ModuleLayer.boot(), null);
        MethodHandle implAddReadsMH = IMPL_LOOKUP.findVirtual(Module.class, "implAddReads", MethodType.methodType(Void.TYPE, Module.class));
        config.modules().forEach(rm -> ModuleLayer.boot().findModule(rm.name()).ifPresent(m -> oldBootModules.forEach(brm -> ModuleLayer.boot().findModule(brm.name()).ifPresent(bm -> {
            try {
                implAddReadsMH.invokeWithArguments(m, bm);
            }
            catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }))));
    }

    private record MinecraftData(String mirror, String serverUrl, String serverHash, String mappingUrl, String mappingHash) {
    }

    private record ParserData(String module, String packages, String target) {
    }
}

