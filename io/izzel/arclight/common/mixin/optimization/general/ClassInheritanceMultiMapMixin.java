/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ClassInstanceMultiMap
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.optimization.general;

import io.izzel.arclight.common.mod.mixins.annotation.LoadIfMod;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.ClassInstanceMultiMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ClassInstanceMultiMap.class})
@LoadIfMod(modid={"recruits"}, condition=LoadIfMod.ModCondition.ABSENT)
public class ClassInheritanceMultiMapMixin<T> {
    @Shadow
    @Final
    private Class<T> f_13528_;
    @Shadow
    @Final
    @Mutable
    private Map<Class<?>, List<T>> f_13527_;
    @Shadow
    @Final
    @Mutable
    private List<T> f_13529_;
    private static final ArrayList<?> EMPTY_LIST = new ArrayList();

    @Redirect(method={"<init>"}, at=@At(value="INVOKE", remap=false, target="Lcom/google/common/collect/Maps;newHashMap()Ljava/util/HashMap;"))
    private HashMap<Class<?>, List<T>> optimization$dropClass() {
        return null;
    }

    @Redirect(method={"<init>"}, at=@At(value="INVOKE", remap=false, target="Lcom/google/common/collect/Lists;newArrayList()Ljava/util/ArrayList;"))
    private ArrayList<T> optimization$dropList() {
        return EMPTY_LIST;
    }

    @Redirect(method={"<init>"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object optimization$dropPut(Map<?, ?> map, Object key, Object value) {
        return null;
    }

    @Overwrite
    public boolean add(T p_add_1_) {
        if (this.f_13527_ != null) {
            boolean flag = false;
            for (Map.Entry<Class<?>, List<T>> entry : this.f_13527_.entrySet()) {
                if (!entry.getKey().isInstance(p_add_1_)) continue;
                flag |= entry.getValue().add(p_add_1_);
            }
            return flag;
        }
        this.f_13527_ = new HashMap();
        this.f_13529_ = new ArrayList<T>();
        this.f_13529_.add(p_add_1_);
        this.f_13527_.put(this.f_13528_, this.f_13529_);
        return true;
    }

    @Overwrite
    public boolean remove(Object p_remove_1_) {
        if (this.f_13527_ == null) {
            return false;
        }
        boolean flag = false;
        for (Map.Entry<Class<?>, List<T>> entry : this.f_13527_.entrySet()) {
            if (!entry.getKey().isInstance(p_remove_1_)) continue;
            List<T> list = entry.getValue();
            flag |= list.remove(p_remove_1_);
        }
        return flag;
    }

    @Overwrite
    public boolean contains(Object p_contains_1_) {
        return this.f_13527_ != null && this.m_13533_(p_contains_1_.getClass()).contains(p_contains_1_);
    }

    @Overwrite
    public <S> Collection<S> m_13533_(Class<S> p_219790_1_) {
        if (p_219790_1_ == this.f_13528_) {
            return Collections.unmodifiableCollection(this.f_13529_);
        }
        if (this.f_13527_ == null) {
            return Collections.emptyList();
        }
        Collection<T> collection = (Collection<T>)this.f_13527_.get(p_219790_1_);
        if (collection == null) {
            collection = this.createList(p_219790_1_);
        }
        return Collections.unmodifiableCollection(collection);
    }

    private <S> Collection<T> createList(Class<S> p_219790_1_) {
        if (!this.f_13528_.isAssignableFrom(p_219790_1_)) {
            throw new IllegalArgumentException("Don't know how to search for " + String.valueOf(p_219790_1_));
        }
        ArrayList<T> list = new ArrayList<T>();
        for (T value : this.f_13529_) {
            if (!p_219790_1_.isInstance(value)) continue;
            list.add(value);
        }
        this.f_13527_.put(p_219790_1_, list);
        return list;
    }
}

