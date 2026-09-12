/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.func;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;

public interface Func0<R>
extends Func<R> {
    public R apply0() throws Throwable;

    default public R apply() {
        try {
            return this.apply0();
        }
        catch (Throwable t) {
            Func.throwException(t);
            throw new AssertionError();
        }
    }

    @Override
    default public R applyArray(Object ... args) {
        return this.apply();
    }

    public static <T1> Func0<T1> y(Func1<Func0<T1>, Func0<T1>> comp) {
        return comp.apply(() -> Func0.y(comp).apply());
    }
}

