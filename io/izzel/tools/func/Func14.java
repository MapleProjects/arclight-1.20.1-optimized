/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;

public interface Func14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R>
extends Func<R> {
    public R apply14(T1 var1, T2 var2, T3 var3, T4 var4, T5 var5, T6 var6, T7 var7, T8 var8, T9 var9, T10 var10, T11 var11, T12 var12, T13 var13, T14 var14) throws Throwable;

    default public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14) {
        try {
            return this.apply14(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14);
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        if (args.length < 14) {
            throw new IllegalArgumentException();
        }
        return this.apply(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13]);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Func14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> y(Func1<Func14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>, Func14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> comp) {
        return comp.apply((p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) -> Func14.y(comp).apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14));
    }
}

