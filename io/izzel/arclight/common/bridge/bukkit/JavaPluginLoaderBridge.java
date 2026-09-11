/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.common.bridge.bukkit;

import java.net.URLClassLoader;
import java.util.List;
import org.bukkit.plugin.PluginDescriptionFile;

public interface JavaPluginLoaderBridge {
    public <T extends URLClassLoader> List<T> arclight$getLoaders();

    public void bridge$setClass(String var1, Class<?> var2);

    public Class<?> arclight$getClassByName(String var1, boolean var2, PluginDescriptionFile var3);
}

