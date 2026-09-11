/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.collect.ImmutableMap;
import io.izzel.arclight.common.bridge.bukkit.SimpleRegistryBridge;
import java.util.Map;
import java.util.function.Predicate;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Registry.SimpleRegistry.class}, remap=false)
public class Registry_SimpleRegistryMixin<T extends Enum<T>>
implements SimpleRegistryBridge {
    @Shadow
    @Final
    @Mutable
    private Map<NamespacedKey, T> map;
    private Runnable arclight$reloadCallback;

    @Inject(method={"<init>(Ljava/lang/Class;Ljava/util/function/Predicate;)V"}, at={@At(value="RETURN")})
    private void arclight$init(Class<T> type, Predicate<T> predicate, CallbackInfo ci) {
        this.arclight$reloadCallback = () -> {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Enum entry : (Enum[])type.getEnumConstants()) {
                if (!predicate.test(entry)) continue;
                builder.put((Object)((Keyed)((Object)entry)).getKey(), (Object)entry);
            }
            this.map = builder.build();
        };
    }

    @Override
    public void bridge$reload() {
        this.arclight$reloadCallback.run();
    }
}

