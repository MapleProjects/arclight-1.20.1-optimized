/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ninja.leaping.configurate.objectmapping.Setting
 *  ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
 */
package io.izzel.arclight.i18n.conf;

import java.util.Map;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class AsyncCatcherSpec {
    @Setting(value="dump")
    private boolean dump;
    @Setting(value="warn")
    private boolean warn;
    @Setting(value="defaultOperation")
    private Operation defaultOp;
    @Setting(value="overrides")
    private Map<String, Operation> overrides;

    public boolean isDump() {
        return this.dump;
    }

    public boolean isWarn() {
        return this.warn;
    }

    public Operation getDefaultOp() {
        return this.defaultOp;
    }

    public Map<String, Operation> getOverrides() {
        return this.overrides;
    }

    public static enum Operation {
        NONE,
        DISPATCH,
        BLOCK,
        EXCEPTION;

    }
}

