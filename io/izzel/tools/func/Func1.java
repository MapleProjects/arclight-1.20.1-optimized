/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import java.util.function.Function;

public interface Func1<T1, R>
extends Func<R>,
Function<T1, R> {
    public R apply1(T1 var1) throws Throwable;

    @Override
    default public R apply(T1 t1) {
        try {
            return this.apply1(t1);
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        return this.apply((T1)args[0]);
    }

    public static <T1, T2> Func1<T1, T2> y(Func1<Func1<T1, T2>, Func1<T1, T2>> comp) {
        return comp.apply(p1 -> Func1.y(comp).apply((T1)p1));
    }
}

