/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.loading.targets.ForgeServerLaunchHandler
 */
package io.izzel.arclight.boot.application;

import net.minecraftforge.fml.loading.targets.ForgeServerLaunchHandler;

public class ArclightLaunchHandler
extends ForgeServerLaunchHandler {
    public String name() {
        return "arclightserver";
    }

    protected String[] preLaunch(String[] arguments, ModuleLayer layer) {
        return arguments;
    }
}

