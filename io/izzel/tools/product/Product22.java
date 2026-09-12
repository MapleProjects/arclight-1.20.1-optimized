/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.product;

import io.izzel.tools.func.Func;
import io.izzel.tools.func.Func22;
import io.izzel.tools.product.Product;
import java.util.Objects;

public class Product22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>
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
    public final T10 _10;
    public final T11 _11;
    public final T12 _12;
    public final T13 _13;
    public final T14 _14;
    public final T15 _15;
    public final T16 _16;
    public final T17 _17;
    public final T18 _18;
    public final T19 _19;
    public final T20 _20;
    public final T21 _21;
    public final T22 _22;

    Product22(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22) {
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;
        this._4 = t4;
        this._5 = t5;
        this._6 = t6;
        this._7 = t7;
        this._8 = t8;
        this._9 = t9;
        this._10 = t10;
        this._11 = t11;
        this._12 = t12;
        this._13 = t13;
        this._14 = t14;
        this._15 = t15;
        this._16 = t16;
        this._17 = t17;
        this._18 = t18;
        this._19 = t19;
        this._20 = t20;
        this._21 = t21;
        this._22 = t22;
    }

    public <R> R map(Func22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> func) {
        return func.apply(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10, this._11, this._12, this._13, this._14, this._15, this._16, this._17, this._18, this._19, this._20, this._21, this._22);
    }

    @Override
    public <R> R map(Func<R> func) {
        if (func instanceof Func22) {
            return ((Func22)func).apply(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10, this._11, this._12, this._13, this._14, this._15, this._16, this._17, this._18, this._19, this._20, this._21, this._22);
        }
        return func.applyArray(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10, this._11, this._12, this._13, this._14, this._15, this._16, this._17, this._18, this._19, this._20, this._21, this._22);
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
            case 9: {
                return this._10;
            }
            case 10: {
                return this._11;
            }
            case 11: {
                return this._12;
            }
            case 12: {
                return this._13;
            }
            case 13: {
                return this._14;
            }
            case 14: {
                return this._15;
            }
            case 15: {
                return this._16;
            }
            case 16: {
                return this._17;
            }
            case 17: {
                return this._18;
            }
            case 18: {
                return this._19;
            }
            case 19: {
                return this._20;
            }
            case 20: {
                return this._21;
            }
            case 21: {
                return this._22;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Max: 22");
    }

    @Override
    public int productArity() {
        return 22;
    }

    public String toString() {
        return "Product22[" + this._1 + "," + this._2 + "," + this._3 + "," + this._4 + "," + this._5 + "," + this._6 + "," + this._7 + "," + this._8 + "," + this._9 + "," + this._10 + "," + this._11 + "," + this._12 + "," + this._13 + "," + this._14 + "," + this._15 + "," + this._16 + "," + this._17 + "," + this._18 + "," + this._19 + "," + this._20 + "," + this._21 + "," + this._22 + "]";
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        Product22 p = (Product22)that;
        return Objects.equals(this._1, p._1) && Objects.equals(this._2, p._2) && Objects.equals(this._3, p._3) && Objects.equals(this._4, p._4) && Objects.equals(this._5, p._5) && Objects.equals(this._6, p._6) && Objects.equals(this._7, p._7) && Objects.equals(this._8, p._8) && Objects.equals(this._9, p._9) && Objects.equals(this._10, p._10) && Objects.equals(this._11, p._11) && Objects.equals(this._12, p._12) && Objects.equals(this._13, p._13) && Objects.equals(this._14, p._14) && Objects.equals(this._15, p._15) && Objects.equals(this._16, p._16) && Objects.equals(this._17, p._17) && Objects.equals(this._18, p._18) && Objects.equals(this._19, p._19) && Objects.equals(this._20, p._20) && Objects.equals(this._21, p._21) && Objects.equals(this._22, p._22);
    }

    public int hashCode() {
        return Objects.hash(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10, this._11, this._12, this._13, this._14, this._15, this._16, this._17, this._18, this._19, this._20, this._21, this._22);
    }
}

