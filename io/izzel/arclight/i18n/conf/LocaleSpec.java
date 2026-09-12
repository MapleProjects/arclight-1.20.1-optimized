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
public class LocaleSpec {
    @Setting(value="current")
    private String current;
    @Setting(value="fallback")
    private String fallback;

    public String getCurrent() {
        return this.current;
    }

    public String getFallback() {
        return this.fallback;
    }
}

