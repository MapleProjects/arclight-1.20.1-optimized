/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ObjectArrays
 *  com.google.common.primitives.Ints
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 *  org.spongepowered.asm.mixin.injection.InjectionPoint$RestrictTargetLevel
 *  org.spongepowered.asm.mixin.injection.code.Injector
 *  org.spongepowered.asm.mixin.injection.code.Injector$InjectorData
 *  org.spongepowered.asm.mixin.injection.struct.InjectionInfo
 *  org.spongepowered.asm.mixin.injection.struct.InjectionNodes$InjectionNode
 *  org.spongepowered.asm.mixin.injection.struct.Target
 *  org.spongepowered.asm.mixin.injection.struct.Target$Extension
 *  org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException
 *  org.spongepowered.asm.util.Bytecode
 *  org.spongepowered.asm.util.SignaturePrinter
 */
package io.izzel.arclight.mixin.injector;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Ints;
import java.util.Arrays;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.SignaturePrinter;

public class Ejector
extends Injector {
    private String callbackInfoClass;
    private int callbackInfoVar;

    public Ejector(InjectionInfo info) {
        super(info, "@Eject");
    }

    protected void inject(Target target, InjectionNodes.InjectionNode node) {
        if (node.isReplaced()) {
            throw new UnsupportedOperationException("Indirect target failure for " + this.info);
        }
        this.checkTargetForNode(target, node, InjectionPoint.RestrictTargetLevel.CONSTRUCTORS_AFTER_DELEGATE);
        if (node.getCurrentTarget() instanceof MethodInsnNode) {
            this.checkTargetForNode(target, node, InjectionPoint.RestrictTargetLevel.ALLOW_ALL);
            this.injectAtInvoke(target, node);
            return;
        }
        throw new InvalidInjectionException(this.info, String.format("%s annotation on is targeting an invalid insn in %s in %s", new Object[]{this.annotationType, target, this}));
    }

    private void injectAtInvoke(Target target, InjectionNodes.InjectionNode node) {
        EjectInvokeData data = new EjectInvokeData(target, (MethodInsnNode)node.getCurrentTarget());
        this.validateIndirectParams(data, data.returnType, data.handlerArgs);
        InsnList insnList = new InsnList();
        Target.Extension extraLocals = target.extendLocals();
        Target.Extension extraStack = target.extendStack();
        this.instanceCallbackInfo(insnList, target);
        AbstractInsnNode insnNode = this.invokeCallback(target, insnList, data, extraLocals, extraStack);
        this.injectCancellationCode(insnList, target);
        target.replaceNode((AbstractInsnNode)data.node, insnNode, insnList);
        extraLocals.apply();
        extraStack.apply();
    }

    protected void injectCancellationCode(InsnList callback, Target target) {
        callback.add((AbstractInsnNode)new VarInsnNode(25, this.callbackInfoVar));
        callback.add((AbstractInsnNode)new MethodInsnNode(182, this.callbackInfoClass, "isCancelled", "()Z", false));
        LabelNode notCancelled = new LabelNode();
        callback.add((AbstractInsnNode)new JumpInsnNode(153, notCancelled));
        this.injectReturnCode(callback, target);
        callback.add((AbstractInsnNode)notCancelled);
    }

    protected void injectReturnCode(InsnList callback, Target target) {
        if (target.returnType.equals((Object)Type.VOID_TYPE)) {
            callback.add((AbstractInsnNode)new InsnNode(177));
        } else {
            callback.add((AbstractInsnNode)new VarInsnNode(25, this.callbackInfoVar));
            String accessor = Ejector.getReturnAccessor(target.returnType);
            String descriptor = Ejector.getReturnDescriptor(target.returnType);
            callback.add((AbstractInsnNode)new MethodInsnNode(182, this.callbackInfoClass, accessor, descriptor, false));
            if (target.returnType.getSort() >= 9) {
                callback.add((AbstractInsnNode)new TypeInsnNode(192, target.returnType.getInternalName()));
            }
            callback.add((AbstractInsnNode)new InsnNode(target.returnType.getOpcode(172)));
        }
    }

    protected AbstractInsnNode invokeCallback(Target target, InsnList insnList, EjectInvokeData data, Target.Extension extraLocals, Target.Extension extraStack) {
        extraLocals.add(data.handlerArgs).add(2);
        extraStack.add(2);
        int[] argMap = this.storeArgs(target, data.handlerArgs, insnList, 0);
        argMap = Ints.concat((int[][])new int[][]{argMap, {this.callbackInfoVar}});
        if (data.captureTargetArgs > 0) {
            int argSize = Bytecode.getArgsSize((Type[])target.arguments, (int)0, (int)data.captureTargetArgs);
            extraLocals.add(argSize);
            extraStack.add(argSize);
            argMap = Ints.concat((int[][])new int[][]{argMap, target.getArgIndices()});
        }
        AbstractInsnNode champion = this.invokeHandlerWithArgs(this.methodArgs, insnList, argMap);
        if (data.coerceReturnType && data.returnType.getSort() >= 9) {
            insnList.add((AbstractInsnNode)new TypeInsnNode(192, data.returnType.getInternalName()));
        }
        return champion;
    }

    protected void instanceCallbackInfo(InsnList callback, Target target) {
        callback.add((AbstractInsnNode)new TypeInsnNode(187, this.callbackInfoClass));
        callback.add((AbstractInsnNode)new InsnNode(89));
        callback.add((AbstractInsnNode)new LdcInsnNode((Object)target.method.name));
        callback.add((AbstractInsnNode)new InsnNode(4));
        callback.add((AbstractInsnNode)new MethodInsnNode(183, this.callbackInfoClass, "<init>", String.format("(%sZ)V", "Ljava/lang/String;"), false));
        this.callbackInfoVar = target.allocateLocal();
        target.addLocalVariable(this.callbackInfoVar, "callbackInfo" + this.callbackInfoVar, "L" + this.callbackInfoClass + ";");
        callback.add((AbstractInsnNode)new VarInsnNode(58, this.callbackInfoVar));
    }

    protected final void validateIndirectParams(EjectInvokeData injector, Type returnType, Type ... args) {
        String description = String.format("%s %s method %s from %s", new Object[]{this.annotationType, injector, this, this.info.toString()});
        int argIndex = 0;
        try {
            injector.coerceReturnType = this.checkCoerce(-1, returnType, description, injector.allowCoerceArgs);
            for (Type arg : args) {
                if (arg == null) continue;
                this.checkCoerce(argIndex, arg, description, injector.allowCoerceArgs);
                ++argIndex;
            }
            if (argIndex >= this.methodArgs.length) {
                throw new InvalidInjectionException(this.info, "Not enough arguments, expect CallbackInfo or CallbackInfoReturnable, found " + this.methodNode.desc);
            }
            if (this.callbackInfoClass == null) {
                this.callbackInfoClass = injector.target.getCallbackInfoClass();
            }
            this.checkCoerce(argIndex, Type.getObjectType((String)this.callbackInfoClass), description, false);
            ++argIndex;
            for (int targetArg = 0; targetArg < injector.target.arguments.length && argIndex < this.methodArgs.length; ++targetArg, ++argIndex) {
                this.checkCoerce(argIndex, injector.target.arguments[targetArg], description, true);
                ++injector.captureTargetArgs;
            }
        }
        catch (InvalidInjectionException ex) {
            String expected = this.methodArgs.length > args.length ? Bytecode.generateDescriptor((Type)returnType, (Type[])((Type[])ObjectArrays.concat((Object[])args, (Object[])injector.target.arguments, Type.class))) : Bytecode.generateDescriptor((Type)returnType, (Type[])args);
            throw new InvalidInjectionException(this.info, String.format("%s. Handler signature: %s Expected signature: %s", ex.getMessage(), this.methodNode.desc, expected));
        }
        if (argIndex < this.methodArgs.length) {
            Type[] extraArgs = Arrays.copyOfRange(this.methodArgs, argIndex, this.methodArgs.length);
            throw new InvalidInjectionException(this.info, String.format("%s has an invalid signature. Found %d unexpected additional method arguments: %s", description, this.methodArgs.length - argIndex, new SignaturePrinter(extraArgs).getFormattedArgs()));
        }
    }

    static String getReturnAccessor(Type returnType) {
        if (returnType.getSort() == 10 || returnType.getSort() == 9) {
            return "getReturnValue";
        }
        return String.format("getReturnValue%s", returnType.getDescriptor());
    }

    static String getReturnDescriptor(Type returnType) {
        if (returnType.getSort() == 10 || returnType.getSort() == 9) {
            return String.format("()%s", "Ljava/lang/Object;");
        }
        return String.format("()%s", returnType.getDescriptor());
    }

    static class EjectInvokeData
    extends Injector.InjectorData {
        final MethodInsnNode node;
        final Type returnType;
        final Type[] targetArgs;
        final Type[] handlerArgs;

        EjectInvokeData(Target target, MethodInsnNode node) {
            super(target);
            this.node = node;
            this.returnType = Type.getReturnType((String)node.desc);
            this.targetArgs = Type.getArgumentTypes((String)node.desc);
            this.handlerArgs = node.getOpcode() == 184 ? this.targetArgs : (Type[])ObjectArrays.concat((Object)Type.getObjectType((String)node.owner), (Object[])this.targetArgs);
        }
    }
}

