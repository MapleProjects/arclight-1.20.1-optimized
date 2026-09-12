/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

public interface Func<R> {
    public R applyArray(Object ... var1);

    public static <E extends Throwable> void throwException(Throwable t) throws E {
        throw t;
    }
}

