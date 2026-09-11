/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.IExtensionPoint$DisplayTest
 *  net.minecraftforge.fml.ModList
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
 *  net.minecraftforge.fml.loading.FMLLoader
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package io.izzel.arclight.common.mod;

import io.izzel.arclight.common.mod.server.event.ArclightEventDispatcherRegistry;
import io.izzel.arclight.common.mod.util.log.ArclightI18nLogger;
import java.io.OutputStream;
import java.io.PrintStream;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod(value="arclight")
public class ArclightMod {
    public static final Logger LOGGER = ArclightI18nLogger.getLogger("Arclight");

    public ArclightMod(FMLJavaModLoadingContext context) {
        LOGGER.info("mod-load");
        System.setOut(new LoggingPrintStream("STDOUT", System.out, Level.INFO));
        System.setErr(new LoggingPrintStream("STDERR", System.err, Level.ERROR));
        ArclightEventDispatcherRegistry.registerAllEventDispatchers();
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "OHNOES\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31\ud83d\ude31", (a, b) -> true));
    }

    public static boolean isModLoaded(String modid) {
        return ModList.get() != null ? ModList.get().isLoaded(modid) : FMLLoader.getLoadingModList().getModFileById(modid) != null;
    }

    private static class LoggingPrintStream
    extends PrintStream {
        private final Logger logger;
        private final Level level;

        public LoggingPrintStream(String name, @NotNull OutputStream out, Level level) {
            super(out);
            this.logger = LogManager.getLogger((String)name);
            this.level = level;
        }

        @Override
        public void println(@Nullable String x) {
            this.logger.log(this.level, x);
        }

        @Override
        public void println(@Nullable Object x) {
            this.logger.log(this.level, String.valueOf(x));
        }
    }
}

