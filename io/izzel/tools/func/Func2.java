/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;
import java.util.function.BiFunction;

public interface Func2<T1, T2, R>
extends Func<R>,
BiFunction<T1, T2, R> {
    public R apply2(T1 var1, T2 var2) throws Throwable;

    @Override
    default public R apply(T1 t1, T2 t2) {
        try {
            return this.apply2(t1, t2);
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        return this.apply((T1)args[0], (T2)args[1]);
    }

    public static <T1, T2, T3> Func2<T1, T2, T3> y(Func1<Func2<T1, T2, T3>, Func2<T1, T2, T3>> comp) {
        return comp.apply((p1, p2) -> Func2.y(comp).apply((T1)p1, (T2)p2));
    }
}

