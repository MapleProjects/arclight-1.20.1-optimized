/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.boot.application;

import io.izzel.arclight.boot.application.BootstrapTransformer;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Stream;

public class Main_Forge {
    public static void main(String[] args) throws Throwable {
        try {
            Main_Forge.verifyManifest();
            Map.Entry<String, List<String>> install = Main_Forge.forgeInstall();
            BootstrapTransformer cl = new BootstrapTransformer(Main_Forge.class.getClassLoader());
            Class<?> clazz = cl.loadClass(install.getKey(), true);
            Method method = clazz.getMethod("main", String[].class);
            String[] target = (String[])Stream.concat(install.getValue().stream(), Arrays.stream(args)).toArray(String[]::new);
            method.invoke(null, new Object[]{target});
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Fail to launch Arclight.");
            System.exit(-1);
        }
    }

    private static void verifyManifest() throws IOException, URISyntaxException {
        URL location = Main_Forge.class.getProtectionDomain().getCodeSource().getLocation();
        try (JarFile baseArchive = new JarFile(new File(location.toURI()));){
            Manifest mf = baseArchive.getManifest();
            if (mf == null || mf.getMainAttributes().isEmpty()) {
                System.err.println("Failed to verify completeness for Arclight installer.");
                System.err.println("The manifest data is corrupted, is the jar file modified?");
                System.err.println("Cannot proceed, Arclight will exit");
                throw new IOException("The installer jar file is corrupted");
            }
        }
    }

    private static Map.Entry<String, List<String>> forgeInstall() throws Throwable {
        Path path = Paths.get(".arclight", "gson.jar");
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            Files.copy(Objects.requireNonNull(Main_Forge.class.getResourceAsStream("/gson.jar")), path, new CopyOption[0]);
        }
        try (URLClassLoader loader = new URLClassLoader(new URL[]{path.toUri().toURL(), Main_Forge.class.getProtectionDomain().getCodeSource().getLocation()}, ClassLoader.getPlatformClassLoader());){
            Class<?> cl = loader.loadClass("io.izzel.arclight.forgeinstaller.ForgeInstaller");
            MethodHandle handle = MethodHandles.lookup().findStatic(cl, "applicationInstall", MethodType.methodType(Map.Entry.class));
            Map.Entry entry = handle.invoke();
            return entry;
        }
    }
}

