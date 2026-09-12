/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.i18n;

public interface LocalizedException {
    public String node();

    public Object[] args();

    public static <T extends Exception> T checked(final String node, final Object ... args) {
        class Checked
        extends Exception
        implements LocalizedException {
            Checked() {
            }

            @Override
            public String node() {
                return node;
            }

            @Override
            public Object[] args() {
                return args;
            }
        }
        return (T)new Checked();
    }

    public static <T extends RuntimeException> T unchecked(final String node, final Object ... args) {
        class Unchecked
        extends RuntimeException
        implements LocalizedException {
            Unchecked() {
            }

            @Override
            public String node() {
                return node;
            }

            @Override
            public Object[] args() {
                return args;
            }
        }
        return (T)new Unchecked();
    }
}

