/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.product;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func9;
import io.izzel.tools.product.Product;
import java.util.Objects;

public class Product9<T1, T2, T3, T4, T5, T6, T7, T8, T9>
implements Product {
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    public final T5 _5;
    public final T6 _6;
    public final T7 _7;
    public final T8 _8;
    public final T9 _9;

    Product9(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;
        this._4 = t4;
        this._5 = t5;
        this._6 = t6;
        this._7 = t7;
        this._8 = t8;
        this._9 = t9;
    }

    public <R> R map(Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> func) {
        return func.apply(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9);
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func9) {
            return ((Func9)func).apply(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9);
        }
        return func.applyArray(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9);
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
            case 4: {
                return this._5;
            }
            case 5: {
                return this._6;
            }
            case 6: {
                return this._7;
            }
            case 7: {
                return this._8;
            }
            case 8: {
                return this._9;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Max: 9");
    }

    @Override
    public int productArity() {
        return 9;
    }

    public String toString() {
        return "Product9[" + this._1 + "," + this._2 + "," + this._3 + "," + this._4 + "," + this._5 + "," + this._6 + "," + this._7 + "," + this._8 + "," + this._9 + "]";
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Product9 p = (Product9)that;
        return Objects.equals(this._1, p._1) && Objects.equals(this._2, p._2) && Objects.equals(this._3, p._3) && Objects.equals(this._4, p._4) && Objects.equals(this._5, p._5) && Objects.equals(this._6, p._6) && Objects.equals(this._7, p._7) && Objects.equals(this._8, p._8) && Objects.equals(this._9, p._9);
    }

    public int hashCode() {
        return Objects.hash(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9);
    }
}

