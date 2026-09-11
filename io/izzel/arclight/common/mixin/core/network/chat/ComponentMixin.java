/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Streams
 *  net.minecraft.network.chat.Component
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network.chat;

import com.google.common.collect.Streams;
import io.izzel.arclight.common.bridge.core.util.text.ITextComponentBridge;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Component.class})
public interface ComponentMixin
extends ITextComponentBridge,
Iterable<Component> {
    @Shadow
    public List<Component> m_7360_();

    default public Stream<Component> stream() {
        class Func
        implements Function<Component, Stream<? extends Component>> {
            Func() {
            }

            @Override
            public Stream<? extends Component> apply(Component component) {
                return ((ITextComponentBridge)component).bridge$stream();
            }
        }
        return Streams.concat((Stream[])new Stream[]{Stream.of((Component)this), this.m_7360_().stream().flatMap(new Func())});
    }

    @Override
    @NotNull
    default public Iterator<Component> iterator() {
        return this.stream().iterator();
    }

    @Override
    default public Stream<Component> bridge$stream() {
        return this.stream();
    }

    @Override
    default public Iterator<Component> bridge$iterator() {
        return this.iterator();
    }
}

