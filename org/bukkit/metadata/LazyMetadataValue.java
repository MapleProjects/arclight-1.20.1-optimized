/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.metadata;

import com.google.common.base.Preconditions;
import java.lang.ref.SoftReference;
import java.util.concurrent.Callable;
import org.bukkit.metadata.MetadataEvaluationException;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LazyMetadataValue
extends MetadataValueAdapter {
    private Callable<Object> lazyValue;
    private CacheStrategy cacheStrategy;
    private SoftReference<Object> internalValue;
    private static final Object ACTUALLY_NULL = new Object();

    public LazyMetadataValue(@NotNull Plugin owningPlugin, @NotNull Callable<Object> lazyValue) {
        this(owningPlugin, CacheStrategy.CACHE_AFTER_FIRST_EVAL, lazyValue);
    }

    public LazyMetadataValue(@NotNull Plugin owningPlugin, @NotNull CacheStrategy cacheStrategy, @NotNull Callable<Object> lazyValue) {
        super(owningPlugin);
        Preconditions.checkArgument((cacheStrategy != null ? 1 : 0) != 0, (Object)"cacheStrategy cannot be null");
        Preconditions.checkArgument((lazyValue != null ? 1 : 0) != 0, (Object)"lazyValue cannot be null");
        this.internalValue = new SoftReference<Object>(null);
        this.lazyValue = lazyValue;
        this.cacheStrategy = cacheStrategy;
    }

    protected LazyMetadataValue(@NotNull Plugin owningPlugin) {
        super(owningPlugin);
    }

    @Override
    @Nullable
    public Object value() {
        this.eval();
        Object value = this.internalValue.get();
        if (value == ACTUALLY_NULL) {
            return null;
        }
        return value;
    }

    private synchronized void eval() throws MetadataEvaluationException {
        if (this.cacheStrategy == CacheStrategy.NEVER_CACHE || this.internalValue.get() == null) {
            try {
                Object value = this.lazyValue.call();
                if (value == null) {
                    value = ACTUALLY_NULL;
                }
                this.internalValue = new SoftReference<Object>(value);
            }
            catch (Exception e) {
                throw new MetadataEvaluationException(e);
            }
        }
    }

    @Override
    public synchronized void invalidate() {
        if (this.cacheStrategy != CacheStrategy.CACHE_ETERNALLY) {
            this.internalValue.clear();
        }
    }

    public static enum CacheStrategy {
        CACHE_AFTER_FIRST_EVAL,
        NEVER_CACHE,
        CACHE_ETERNALLY;

    }
}

