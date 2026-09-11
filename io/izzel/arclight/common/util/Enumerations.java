/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 */
package io.izzel.arclight.common.util;

import io.izzel.arclight.api.Unsafe;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.function.Function;

public class Enumerations {
    public static <A, B> Enumeration<B> transform(final Enumeration<A> enumeration, final Function<A, B> mapper) {
        return new Enumeration<B>(){

            @Override
            public boolean hasMoreElements() {
                return enumeration.hasMoreElements();
            }

            @Override
            public B nextElement() {
                return mapper.apply(enumeration.nextElement());
            }
        };
    }

    public static Enumeration<URL> remapped(Enumeration<URL> enumeration) {
        return Enumerations.transform(enumeration, url -> {
            try {
                return new URL("remap:" + String.valueOf(url));
            }
            catch (MalformedURLException e) {
                Unsafe.throwException((Throwable)e);
                return null;
            }
        });
    }
}

