/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.product;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func4;
import io.izzel.tools.product.Product;
import java.util.Objects;

public class Product4<T1, T2, T3, T4>
implements Product {
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;

    Product4(T1 t1, T2 t2, T3 t3, T4 t4) {
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;
        this._4 = t4;
    }

    public <R> R map(Func4<T1, T2, T3, T4, R> func) {
        return func.apply(this._1, this._2, this._3, this._4);
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func4) {
            return ((Func4)func).apply(this._1, this._2, this._3, this._4);
        }
        return func.applyArray(this._1, this._2, this._3, this._4);
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
            case 3: {
                return this._4;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Max: 4");
    }

    @Override
    public int productArity() {
        return 4;
    }

    public String toString() {
        return "Product4[" + this._1 + "," + this._2 + "," + this._3 + "," + this._4 + "]";
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Product4 p = (Product4)that;
        return Objects.equals(this._1, p._1) && Objects.equals(this._2, p._2) && Objects.equals(this._3, p._3) && Objects.equals(this._4, p._4);
    }

    public int hashCode() {
        return Objects.hash(this._1, this._2, this._3, this._4);
    }
}

