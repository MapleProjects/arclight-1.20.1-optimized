/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 */
package io.izzel.arclight.common.bridge.core.util.text;

import java.util.Iterator;
import java.util.stream.Stream;
import net.minecraft.network.chat.Component;

public interface ITextComponentBridge {
    public Stream<Component> bridge$stream();

    public Iterator<Component> bridge$iterator();
}

