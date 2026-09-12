/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;

public interface Func3<T1, T2, T3, R>
extends Func<R> {
    public R apply3(T1 var1, T2 var2, T3 var3) throws Throwable;

    default public R apply(T1 t1, T2 t2, T3 t3) {
        try {
            return this.apply3(t1, t2, t3);
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        return this.apply(args[0], args[1], args[2]);
    }

    public static <T1, T2, T3, T4> Func3<T1, T2, T3, T4> y(Func1<Func3<T1, T2, T3, T4>, Func3<T1, T2, T3, T4>> comp) {
        return comp.apply((p1, p2, p3) -> Func3.y(comp).apply(p1, p2, p3));
    }
}

