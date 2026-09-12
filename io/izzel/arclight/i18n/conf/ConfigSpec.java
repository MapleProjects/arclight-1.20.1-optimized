/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ninja.leaping.configurate.objectmapping.Setting
 *  ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
 */
package io.izzel.arclight.i18n.conf;

import io.izzel.arclight.i18n.conf.AsyncCatcherSpec;
import io.izzel.arclight.i18n.conf.CompatSpec;
import io.izzel.arclight.i18n.conf.LocaleSpec;
import io.izzel.arclight.i18n.conf.OptimizationSpec;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ConfigSpec {
    @Setting(value="_v")
    private int version;
    @Setting(value="optimization")
    private OptimizationSpec optimizationSpec;
    @Setting(value="locale")
    private LocaleSpec localeSpec;
    @Setting(value="compatibility")
    private CompatSpec compatSpec;
    @Setting(value="async-catcher")
    private AsyncCatcherSpec asyncCatcherSpec;

    public int getVersion() {
        return this.version;
    }

    public OptimizationSpec getOptimization() {
        return this.optimizationSpec;
    }

    public LocaleSpec getLocale() {
        return this.localeSpec;
    }

    public CompatSpec getCompat() {
        return this.compatSpec;
    }

    public AsyncCatcherSpec getAsyncCatcher() {
        return this.asyncCatcherSpec;
    }
}

