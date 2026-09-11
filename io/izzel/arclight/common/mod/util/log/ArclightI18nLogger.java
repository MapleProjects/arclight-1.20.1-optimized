/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 *  org.apache.logging.log4j.Logger
 */
package io.izzel.arclight.common.mod.util.log;

import io.izzel.arclight.api.Unsafe;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import org.apache.logging.log4j.Logger;

public class ArclightI18nLogger {
    private static final MethodHandle MH_GET_LOGGER;

    public static Logger getLogger(String name) {
        try {
            return MH_GET_LOGGER.invokeExact(name);
        }
        catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    static {
        try {
            MH_GET_LOGGER = Unsafe.lookup().findStatic(Class.forName("io.izzel.arclight.boot.log.ArclightI18nLogger"), "getLogger", MethodType.methodType(Logger.class, String.class));
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

