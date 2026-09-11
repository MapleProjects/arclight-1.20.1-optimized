/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraft.server.Bootstrap
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.server;

import io.izzel.arclight.api.Unsafe;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.server.Bootstrap;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLegacy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Bootstrap.class})
public class BootstrapMixin {
    @Inject(method={"bootStrap"}, at={@At(value="HEAD")})
    private static void arclight$replaceWhitelist(CallbackInfo ci) {
        if (new LinkageError().getStackTrace()[2].toString().contains("util.CraftLegacy")) {
            try {
                Field field = CraftLegacy.class.getDeclaredField("whitelistedStates");
                Object base = Unsafe.staticFieldBase((Field)field);
                long offset = Unsafe.staticFieldOffset((Field)field);
                Set prev = (Set)Unsafe.getObject((Object)base, (long)offset);
                class TrickSet
                extends HashSet<String> {
                    TrickSet() {
                    }

                    @Override
                    public boolean contains(Object o) {
                        this.add((String)o);
                        return super.contains(o);
                    }
                }
                TrickSet set = new TrickSet();
                set.addAll(prev);
                Unsafe.putObject((Object)base, (long)offset, (Object)set);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

