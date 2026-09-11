/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.optimization.general;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Entity.class})
public abstract class EntityMixin_Optimize {
    @Shadow
    public ImmutableList<Entity> f_19823_;

    @Inject(method={"getIndirectPassengersStream"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$emptyPassenger(CallbackInfoReturnable<Stream<Entity>> cir) {
        if (this.f_19823_.isEmpty()) {
            cir.setReturnValue(Stream.empty());
        }
    }

    @Overwrite
    public Iterable<Entity> m_146897_() {
        if (this.f_19823_.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Entity> list = new ArrayList<Entity>();
        for (Entity entity : this.f_19823_) {
            list.add(entity);
            list.addAll((Collection)entity.m_146897_());
        }
        return list;
    }
}

