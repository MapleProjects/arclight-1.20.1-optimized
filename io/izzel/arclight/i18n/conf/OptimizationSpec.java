/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ninja.leaping.configurate.objectmapping.Setting
 *  ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
 */
package io.izzel.arclight.i18n.conf;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class OptimizationSpec {
    @Setting(value="cache-plugin-class")
    private boolean cachePluginClass;
    @Setting(value="goal-selector-update-interval")
    private int goalSelectorInterval;
    @Setting(value="use-activation-and-tracking-range")
    private boolean useActivationAndTrackingRange;

    public boolean useActivationAndTrackingRange() {
        return this.useActivationAndTrackingRange;
    }

    public boolean isCachePluginClass() {
        return this.cachePluginClass;
    }

    public int getGoalSelectorInterval() {
        return this.goalSelectorInterval;
    }
}

