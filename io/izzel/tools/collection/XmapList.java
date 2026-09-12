/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.collection;

import io.izzel.tools.func.Func1;
import java.util.AbstractList;
import java.util.List;

public class XmapList<A, B>
extends AbstractList<B> {
    private final List<A> list;
    private final Func1<? super A, ? extends B> from;
    private final Func1<? super B, ? extends A> to;

    public XmapList(List<A> list, Func1<? super A, ? extends B> from, Func1<? super B, ? extends A> to) {
        this.list = list;
        this.from = from;
        this.to = to;
    }

    @Override
    public B get(int index) {
        A ret = this.list.get(index);
        return ret == null ? null : (B)this.from.apply((A)ret);
    }

    @Override
    public B set(int index, B element) {
        A ret = this.list.set(index, this.to.apply(element));
        return ret == null ? null : (B)this.from.apply((A)ret);
    }

    @Override
    public void add(int index, B element) {
        this.list.add(index, this.to.apply(element));
    }

    @Override
    public boolean add(B b) {
        return this.list.add(this.to.apply(b));
    }

    @Override
    public B remove(int index) {
        A ret = this.list.remove(index);
        return ret == null ? null : (B)this.from.apply((A)ret);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(this.to.apply((B)o));
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public static <A, B> XmapList<A, B> create(List<A> list, Class<B> target, Func1<? super A, ? extends B> from, Func1<? super B, ? extends A> to) {
        return list instanceof java.util.RandomAccess ? new RandomAccess<A, B>(list, from, to) : new XmapList<A, B>(list, from, to);
    }

    private static class RandomAccess<A, B>
    extends XmapList<A, B>
    implements java.util.RandomAccess {
        public RandomAccess(List<A> list, Func1<? super A, ? extends B> from, Func1<? super B, ? extends A> to) {
            super(list, from, to);
        }
    }
}

