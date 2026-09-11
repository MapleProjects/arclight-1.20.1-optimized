/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Internal
 */
package org.bukkit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.jetbrains.annotations.ApiStatus;

@Retention(value=RetentionPolicy.CLASS)
@Deprecated
@ApiStatus.Internal
public @interface UndefinedNullability {
    public String value() default "";
}

