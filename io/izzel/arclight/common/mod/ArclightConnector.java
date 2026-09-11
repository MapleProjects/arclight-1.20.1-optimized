/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.injector.EjectorInfo
 *  org.apache.logging.log4j.Logger
 *  org.spongepowered.asm.mixin.Mixins
 *  org.spongepowered.asm.mixin.connect.IMixinConnector
 *  org.spongepowered.asm.mixin.injection.struct.InjectionInfo
 */
package io.izzel.arclight.common.mod;

import io.izzel.arclight.common.mod.util.log.ArclightI18nLogger;
import io.izzel.arclight.mixin.injector.EjectorInfo;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;

public class ArclightConnector
implements IMixinConnector {
    public static final Logger LOGGER = ArclightI18nLogger.getLogger("Arclight");

    public void connect() {
        InjectionInfo.register(EjectorInfo.class);
        Mixins.addConfiguration((String)"mixins.arclight.core.json");
        Mixins.addConfiguration((String)"mixins.arclight.bukkit.json");
        Mixins.addConfiguration((String)"mixins.arclight.forge.json");
        LOGGER.info("mixin-load.core");
        Mixins.addConfiguration((String)"mixins.arclight.impl.forge.optimization.json");
        LOGGER.info("mixin-load.optimization");
    }
}

