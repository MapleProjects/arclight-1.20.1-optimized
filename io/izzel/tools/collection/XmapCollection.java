/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.collection;

import io.izzel.tools.func.Func1;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class XmapCollection<A, B>
extends AbstractCollection<B> {
    private final Collection<A> collection;
    private final Func1<? super A, ? extends B> from;
    private final Func1<? super B, ? extends A> to;

    protected XmapCollection(Collection<A> collection, Func1<? super A, ? extends B> from, Func1<? super B, ? extends A> to) {
        this.collection = collection;
        this.from = from;
        this.to = to;
    }

    @Override
    public int size() {
        return this.collection.size();
    }

    @Override
    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    @Override
    public Iterator<B> iterator() {
        final Iterator<A> iterator = this.collection.iterator();
        return new Iterator<B>(){

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public B next() {
                Object ret = iterator.next();
                return ret == null ? null : (Object)XmapCollection.this.from.apply(ret);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(this.collection.toArray()).map(this.from).toArray();
    }

    @Override
    public boolean add(B b) {
        return this.collection.add(this.to.apply(b));
    }

    @Override
    public boolean remove(Object o) {
        return this.collection.remove(this.to.apply((B)o));
    }

    @Override
    public void clear() {
        this.collection.clear();
    }

    public static <A, B> XmapCollection<A, B> create(Collection<A> collection, Class<B> target, Func1<? super A, ? extends B> from, Func1<? super B, ? extends A> to) {
        return new XmapCollection<A, B>(collection, from, to);
    }
}

