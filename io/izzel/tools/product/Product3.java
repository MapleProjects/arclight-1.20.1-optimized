/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.product;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func3;
import io.izzel.tools.product.Product;
import java.util.Objects;

public class Product3<T1, T2, T3>
implements Product {
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;

    Product3(T1 t1, T2 t2, T3 t3) {
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;
    }

    public <R> R map(Func3<T1, T2, T3, R> func) {
        return func.apply(this._1, this._2, this._3);
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func3) {
            return ((Func3)func).apply(this._1, this._2, this._3);
        }
        return func.applyArray(this._1, this._2, this._3);
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
            case 2: {
                return this._3;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Max: 3");
    }

    @Override
    public int productArity() {
        return 3;
    }

    public String toString() {
        return "Product3[" + this._1 + "," + this._2 + "," + this._3 + "]";
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Product3 p = (Product3)that;
        return Objects.equals(this._1, p._1) && Objects.equals(this._2, p._2) && Objects.equals(this._3, p._3);
    }

    public int hashCode() {
        return Objects.hash(this._1, this._2, this._3);
    }
}

