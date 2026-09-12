/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func0;
import io.izzel.tools.product.Product;

public enum Unit implements Product
{
    INSTANCE;


    public String toString() {
        return "Unit";
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func0) {
            return ((Func0)func).apply();
        }
        return func.applyArray(new Object[0]);
    }

    @Override
    public Object productElement(int i) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int productArity() {
        return 0;
    }
}

