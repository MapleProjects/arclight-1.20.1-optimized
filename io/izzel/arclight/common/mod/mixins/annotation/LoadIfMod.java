/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.common.mod.mixins.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.CLASS)
public @interface LoadIfMod {
    public String[] modid();

    public ModCondition condition();

    public static enum ModCondition {
        ABSENT,
        PRESENT;

    }
}

