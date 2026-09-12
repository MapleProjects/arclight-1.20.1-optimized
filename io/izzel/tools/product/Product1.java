/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.product;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func1;
import io.izzel.tools.product.Product;
import java.util.Objects;

public class Product1<T1>
implements Product {
    public final T1 _1;

    Product1(T1 t1) {
        this._1 = t1;
    }

    public <R> R map(Func1<T1, R> func) {
        return func.apply(this._1);
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func1) {
            return ((Func1)func).apply(this._1);
        }
        return func.applyArray(this._1);
    }

    @Override
    public Object productElement(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0: {
                return this._1;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Max: 1");
    }

    @Override
    public int productArity() {
        return 1;
    }

    public String toString() {
        return "Product1[" + this._1 + "]";
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Product1 p = (Product1)that;
        return Objects.equals(this._1, p._1);
    }

    public int hashCode() {
        return Objects.hash(this._1);
    }
}

