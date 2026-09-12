/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;

public interface Func4<T1, T2, T3, T4, R>
extends Func<R> {
    public R apply4(T1 var1, T2 var2, T3 var3, T4 var4) throws Throwable;

    default public R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
        try {
            return this.apply4(t1, t2, t3, t4);
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        if (args.length < 4) {
            throw new IllegalArgumentException();
        }
        return this.apply(args[0], args[1], args[2], args[3]);
    }

    public static <T1, T2, T3, T4, T5> Func4<T1, T2, T3, T4, T5> y(Func1<Func4<T1, T2, T3, T4, T5>, Func4<T1, T2, T3, T4, T5>> comp) {
        return comp.apply((p1, p2, p3, p4) -> Func4.y(comp).apply(p1, p2, p3, p4));
    }
}

