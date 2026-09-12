/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.product;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func2;
import io.izzel.tools.product.Product;
import java.util.Objects;

public class Product2<T1, T2>
implements Product {
    public final T1 _1;
    public final T2 _2;

    Product2(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }

    public <R> R map(Func2<T1, T2, R> func) {
        return func.apply(this._1, this._2);
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func2) {
            return ((Func2)func).apply(this._1, this._2);
        }
        return func.applyArray(this._1, this._2);
    }

    @Override
    public Object productElement(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0: {
                return this._1;
            }
            case 1: {
                return this._2;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Max: 2");
    }

    @Override
    public int productArity() {
        return 2;
    }

    public String toString() {
        return "Product2[" + this._1 + "," + this._2 + "]";
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Product2 p = (Product2)that;
        return Objects.equals(this._1, p._1) && Objects.equals(this._2, p._2);
    }

    public int hashCode() {
        return Objects.hash(this._1, this._2);
    }
}

