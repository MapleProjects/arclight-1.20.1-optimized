/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.spongepowered.asm.mixin.injection.code.Injector
 *  org.spongepowered.asm.mixin.injection.struct.InjectionInfo
 *  org.spongepowered.asm.mixin.injection.struct.InjectionInfo$AnnotationType
 *  org.spongepowered.asm.mixin.injection.struct.InjectionInfo$HandlerPrefix
 *  org.spongepowered.asm.mixin.transformer.MixinTargetContext
 */
package io.izzel.arclight.mixin.injector;

import io.izzel.arclight.mixin.Eject;
import io.izzel.arclight.mixin.injector.Ejector;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

@InjectionInfo.AnnotationType(value=Eject.class)
@InjectionInfo.HandlerPrefix(value="eject")
public class EjectorInfo
extends InjectionInfo {
    public EjectorInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        super(mixin, method, annotation);
    }

    protected Injector parseInjector(AnnotationNode injectAnnotation) {
        return new Ejector(this);
    }

    protected String getDescription() {
        return "Eject";
    }
}

