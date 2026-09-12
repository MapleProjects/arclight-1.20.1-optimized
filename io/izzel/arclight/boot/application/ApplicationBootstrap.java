/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.boot.application;

import io.izzel.arclight.api.EnumHelper;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.boot.AbstractBootstrap;
import io.izzel.arclight.i18n.ArclightConfig;
import io.izzel.arclight.i18n.ArclightLocale;
import java.util.Arrays;
import java.util.ServiceLoader;
import java.util.function.Consumer;

public class ApplicationBootstrap
extends AbstractBootstrap
implements Consumer<String[]> {
    private static final int MIN_DEPRECATED_VERSION = 60;
    private static final int MIN_DEPRECATED_JAVA_VERSION = 16;

    /*
     * WARNING - void declaration
     */
    @Override
    public void accept(String[] stringArray) {
        System.setProperty("max.bg.threads", String.valueOf(Math.max(Runtime.getRuntime().availableProcessors(), 16)));
        System.setProperty("paper.max-chunk-sends-per-tick", "1024");
        System.setProperty("Paper.asyncChunks", "true");
        System.setProperty("paper.tickEmptyWorlds", "false");
        System.setProperty("paper.explicit-flush", "true");
        System.setProperty("io.netty.allocator.type", "pooled");
        System.setProperty("arclight.alwaysExtract", "true");
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        System.setProperty("log4j.jul.LoggerAdapter", "io.izzel.arclight.boot.log.ArclightLoggerAdapter");
        System.setProperty("log4j.configurationFile", "arclight-log4j2.xml");
        ArclightLocale.info("i18n.using-language", ArclightConfig.spec().getLocale().getCurrent(), ArclightConfig.spec().getLocale().getFallback());
        try {
            int javaVersion = (int)Float.parseFloat(System.getProperty("java.class.version"));
            if (javaVersion < 60) {
                ArclightLocale.error("java.deprecated", System.getProperty("java.version"), 16);
                Thread.sleep(3000L);
            }
            Unsafe.ensureClassInitialized(EnumHelper.class);
        }
        catch (Throwable t) {
            System.err.println("Your Java is not compatible with Arclight.");
            t.printStackTrace();
            return;
        }
        try {
            void args;
            this.setupMod();
            this.dirtyHacks();
            int targetIndex = Arrays.asList(args).indexOf("--launchTarget");
            if (targetIndex >= 0 && targetIndex < ((void)args).length - 1) {
                args[targetIndex + 1] = "arclightserver";
            }
            ((Consumer)ServiceLoader.load(this.getClass().getModule().getLayer(), Consumer.class).stream().filter(it -> !it.type().getName().contains("arclight")).findFirst().orElseThrow().get()).accept(args);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Fail to launch Arclight.");
        }
    }
}

