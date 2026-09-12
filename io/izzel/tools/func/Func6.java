/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;

public interface Func6<T1, T2, T3, T4, T5, T6, R>
extends Func<R> {
    public R apply6(T1 var1, T2 var2, T3 var3, T4 var4, T5 var5, T6 var6) throws Throwable;

    default public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        try {
            return this.apply6(t1, t2, t3, t4, t5, t6);
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        if (args.length < 6) {
            throw new IllegalArgumentException();
        }
        return this.apply(args[0], args[1], args[2], args[3], args[4], args[5]);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Func6<T1, T2, T3, T4, T5, T6, T7> y(Func1<Func6<T1, T2, T3, T4, T5, T6, T7>, Func6<T1, T2, T3, T4, T5, T6, T7>> comp) {
        return comp.apply((p1, p2, p3, p4, p5, p6) -> Func6.y(comp).apply(p1, p2, p3, p4, p5, p6));
    }
}

