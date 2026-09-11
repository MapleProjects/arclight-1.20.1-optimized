/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.common.bridge.bukkit;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.SimplePluginManager;

public interface PluginClassLoaderBridge {
    public PluginDescriptionFile arclight$desc();

    public Class<?> arclight$loadFromExternal(String var1, boolean var2, boolean var3) throws ClassNotFoundException;

    public SimplePluginManager arclight$getPluginManager();

    public Logger arclight$systemLogger();
}

