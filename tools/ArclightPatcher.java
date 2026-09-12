import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.io.*;
import java.nio.file.*;
import java.util.jar.*;
import java.util.zip.*;

public class ArclightPatcher {
    public static void main(String[] args) throws Exception {
        File outerJar = new File("/home/maple/Server1-20-1/arclight-forge-1.20.1-1.0.6-SNAPSHOT.jar");
        File origJar = new File("/home/maple/Server1-20-1/arclight-forge-1.20.1-1.0.6-SNAPSHOT.jar.orig");
        if (!origJar.exists()) {
            Files.copy(outerJar.toPath(), origJar.toPath());
        }

        File tempDir = new File("/tmp/arclight_patch_workspace");
        deleteDir(tempDir);
        tempDir.mkdirs();

        // 1. Unpack outer jar from orig backup
        System.out.println("Unpacking original outer jar...");
        unzip(origJar, tempDir);

        // 2. Patch ApplicationBootstrap.class
        File appBootstrapFile = new File(tempDir, "io/izzel/arclight/boot/application/ApplicationBootstrap.class");
        if (appBootstrapFile.exists()) {
            System.out.println("Patching ApplicationBootstrap.class...");
            byte[] bytes = Files.readAllBytes(appBootstrapFile.toPath());
            byte[] patched = patchApplicationBootstrap(bytes);
            Files.write(appBootstrapFile.toPath(), patched);
        }

        // 3. Unpack common.jar
        File commonJarFile = new File(tempDir, "common.jar");
        File commonDir = new File(tempDir, "common_extracted");
        deleteDir(commonDir);
        commonDir.mkdirs();
        System.out.println("Unpacking common.jar...");
        unzip(commonJarFile, commonDir);

        // 4. Patch BootstrapMixin.class in common.jar
        File bootstrapMixinFile = new File(commonDir, "io/izzel/arclight/common/mixin/core/server/BootstrapMixin.class");
        if (bootstrapMixinFile.exists()) {
            System.out.println("Patching BootstrapMixin.class...");
            byte[] bytes = Files.readAllBytes(bootstrapMixinFile.toPath());
            byte[] patched = patchBootstrapMixin(bytes);
            Files.write(bootstrapMixinFile.toPath(), patched);
        }

        // 5. Patch ServerChunkCache_MainThreadExecutorMixin.class in common.jar
        File mainThreadExecMixinFile = new File(commonDir, "io/izzel/arclight/common/mixin/core/server/level/ServerChunkCache_MainThreadExecutorMixin.class");
        if (mainThreadExecMixinFile.exists()) {
            System.out.println("Patching ServerChunkCache_MainThreadExecutorMixin.class with 128-batch drain...");
            byte[] bytes = Files.readAllBytes(mainThreadExecMixinFile.toPath());
            byte[] patched = patchMainThreadExecutorMixin(bytes);
            Files.write(mainThreadExecMixinFile.toPath(), patched);
        }

        // 5.1 Patch ArclightCallbackExecutor.class in common.jar
        File callbackExecFile = new File(commonDir, "io/izzel/arclight/common/mod/util/ArclightCallbackExecutor.class");
        if (callbackExecFile.exists()) {
            System.out.println("Patching ArclightCallbackExecutor.class to 512 tasks and 20ms limit...");
            byte[] bytes = Files.readAllBytes(callbackExecFile.toPath());
            byte[] patched = patchArclightCallbackExecutor(bytes);
            Files.write(callbackExecFile.toPath(), patched);
        }

        // 5.2 Patch StructureTemplate_PaletteMixin.class in common.jar
        File paletteMixinFile = new File(commonDir, "io/izzel/arclight/common/mixin/core/world/level/levelgen/structure/templatesystem/StructureTemplate_PaletteMixin.class");
        if (paletteMixinFile.exists()) {
            System.out.println("Patching StructureTemplate_PaletteMixin.class for zero-stream allocations...");
            byte[] bytes = Files.readAllBytes(paletteMixinFile.toPath());
            byte[] patched = patchStructureTemplatePaletteMixin(bytes);
            Files.write(paletteMixinFile.toPath(), patched);
        }

        // 6. Generate and add PoiSectionMixin to common.jar
        File poiMixinClassFile = new File(commonDir, "io/izzel/arclight/common/mixin/core/world/entity/ai/village/poi/PoiSectionMixin.class");
        poiMixinClassFile.getParentFile().mkdirs();
        byte[] poiMixinBytes = createPoiSectionMixinBytes();
        Files.write(poiMixinClassFile.toPath(), poiMixinBytes);
        System.out.println("Created PoiSectionMixin.class");

        // 6.1 Generate Network Packet & Payload Fixer mixins
        File serverPayloadMixin = new File(commonDir, "io/izzel/arclight/common/mixin/core/network/protocol/game/ServerboundCustomPayloadPacketMixin.class");
        serverPayloadMixin.getParentFile().mkdirs();
        Files.write(serverPayloadMixin.toPath(), createPayloadPacketMixin("io/izzel/arclight/common/mixin/core/network/protocol/game/ServerboundCustomPayloadPacketMixin", "net/minecraft/network/protocol/game/ServerboundCustomPayloadPacket"));

        File clientPayloadMixin = new File(commonDir, "io/izzel/arclight/common/mixin/core/network/protocol/game/ClientboundCustomPayloadPacketMixin.class");
        Files.write(clientPayloadMixin.toPath(), createPayloadPacketMixin("io/izzel/arclight/common/mixin/core/network/protocol/game/ClientboundCustomPayloadPacketMixin", "net/minecraft/network/protocol/game/ClientboundCustomPayloadPacket"));

        File compressMixin = new File(commonDir, "io/izzel/arclight/common/mixin/core/network/CompressionDecoderMixin.class");
        compressMixin.getParentFile().mkdirs();
        Files.write(compressMixin.toPath(), createCompressionDecoderMixinBytes());

        File byteBufMixin = new File(commonDir, "io/izzel/arclight/common/mixin/core/network/FriendlyByteBufMixin.class");
        Files.write(byteBufMixin.toPath(), createFriendlyByteBufMixinBytes());
        System.out.println("Created Network Packet & Payload Fixer mixins (2GB limits)");

        // 7. Register mixins in mixins.arclight.core.json
        File coreMixinJson = new File(commonDir, "mixins.arclight.core.json");
        if (coreMixinJson.exists()) {
            String jsonContent = Files.readString(coreMixinJson.toPath());
            if (!jsonContent.contains("world.entity.ai.village.poi.PoiSectionMixin")) {
                jsonContent = jsonContent.replace(
                    "\"world.BlockGetterMixin\",",
                    "\"world.entity.ai.village.poi.PoiSectionMixin\",\n    \"world.BlockGetterMixin\","
                );
            }
            if (!jsonContent.contains("network.protocol.game.ServerboundCustomPayloadPacketMixin")) {
                jsonContent = jsonContent.replace(
                    "\"network.ConnectionMixin\",",
                    "\"network.ConnectionMixin\",\n    \"network.CompressionDecoderMixin\",\n    \"network.FriendlyByteBufMixin\",\n    \"network.protocol.game.ServerboundCustomPayloadPacketMixin\",\n    \"network.protocol.game.ClientboundCustomPayloadPacketMixin\","
                );
            }
            Files.writeString(coreMixinJson.toPath(), jsonContent);
            System.out.println("Registered custom mixins in mixins.arclight.core.json");
        }

        // 8. Repack common.jar
        System.out.println("Repacking common.jar...");
        commonJarFile.delete();
        zip(commonDir, commonJarFile);

        // 9. Repack outer jar
        System.out.println("Repacking outer jar...");
        deleteDir(commonDir);
        File tempOuterJar = new File(tempDir, "repacked_outer.jar");
        zip(tempDir, tempOuterJar);

        // Copy over original jar
        Files.copy(tempOuterJar.toPath(), outerJar.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Successfully updated: " + outerJar);

        // Clean up extracted runtime caches
        File dotArclightModFile = new File("/home/maple/Server1-20-1/.arclight/mod_file");
        if (dotArclightModFile.exists()) {
            deleteDir(dotArclightModFile);
            System.out.println("Cleared .arclight/mod_file cache.");
        }
    }

    private static byte[] patchApplicationBootstrap(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (MethodNode mn : cn.methods) {
            if (mn.name.equals("accept") && mn.desc.equals("([Ljava/lang/String;)V")) {
                InsnList insns = new InsnList();

                // System.setProperty("max.bg.threads", String.valueOf(Math.max(Runtime.getRuntime().availableProcessors(), 16)));
                insns.add(new LdcInsnNode("max.bg.threads"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Runtime", "availableProcessors", "()I", false));
                insns.add(new IntInsnNode(Opcodes.BIPUSH, 16));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("paper.max-chunk-sends-per-tick", "1024");
                insns.add(new LdcInsnNode("paper.max-chunk-sends-per-tick"));
                insns.add(new LdcInsnNode("1024"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("Paper.asyncChunks", "true");
                insns.add(new LdcInsnNode("Paper.asyncChunks"));
                insns.add(new LdcInsnNode("true"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("paper.tickEmptyWorlds", "false");
                insns.add(new LdcInsnNode("paper.tickEmptyWorlds"));
                insns.add(new LdcInsnNode("false"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("paper.explicit-flush", "true");
                insns.add(new LdcInsnNode("paper.explicit-flush"));
                insns.add(new LdcInsnNode("true"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("io.netty.allocator.type", "pooled");
                insns.add(new LdcInsnNode("io.netty.allocator.type"));
                insns.add(new LdcInsnNode("pooled"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("arclight.alwaysExtract", "true");
                insns.add(new LdcInsnNode("arclight.alwaysExtract"));
                insns.add(new LdcInsnNode("true"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                mn.instructions.insert(insns);
                System.out.println("Injected max.bg.threads and chunk sending auto-config into ApplicationBootstrap.accept");
                break;
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }

    private static byte[] patchBootstrapMixin(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (MethodNode mn : cn.methods) {
            if (mn.name.startsWith("arclight$replaceWhitelist")) {
                InsnList insns = new InsnList();

                // System.setProperty("max.bg.threads", String.valueOf(Math.max(Runtime.getRuntime().availableProcessors(), 16)));
                insns.add(new LdcInsnNode("max.bg.threads"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Runtime", "availableProcessors", "()I", false));
                insns.add(new IntInsnNode(Opcodes.BIPUSH, 16));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("paper.max-chunk-sends-per-tick", "512");
                insns.add(new LdcInsnNode("paper.max-chunk-sends-per-tick"));
                insns.add(new LdcInsnNode("512"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                mn.instructions.insert(insns);
                System.out.println("Injected max.bg.threads auto-config into BootstrapMixin.arclight$replaceWhitelist");
                break;
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }

    private static byte[] patchMainThreadExecutorMixin(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (MethodNode mn : cn.methods) {
            if (mn.name.equals("m_7245_") && mn.desc.equals("()Z")) {
                for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                    if (insn instanceof MethodInsnNode) {
                        MethodInsnNode minsn = (MethodInsnNode) insn;
                        if (minsn.owner.equals("net/minecraft/util/thread/BlockableEventLoop") && minsn.name.equals("m_7245_")) {
                            AbstractInsnNode nextNode = insn.getNext();
                            if (nextNode instanceof VarInsnNode && nextNode.getOpcode() == Opcodes.ISTORE) {
                                // Add 127 consecutive drains without branching
                                InsnList drainList = new InsnList();
                                for (int i = 0; i < 127; i++) {
                                    drainList.add(new VarInsnNode(Opcodes.ALOAD, 0));
                                    drainList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "net/minecraft/util/thread/BlockableEventLoop", "m_7245_", "()Z", false));
                                    drainList.add(new InsnNode(Opcodes.POP));
                                }

                                mn.instructions.insert(nextNode, drainList);
                                System.out.println("Injected 128-task batch chunk event loop drain into ServerChunkCache_MainThreadExecutorMixin.m_7245_");
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }

    private static byte[] patchArclightCallbackExecutor(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (MethodNode mn : cn.methods) {
            if (mn.name.equals("run") && mn.desc.equals("()V")) {
                for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                    if (insn.getOpcode() == Opcodes.BIPUSH) {
                        IntInsnNode iin = (IntInsnNode) insn;
                        if (iin.operand == 64) {
                            mn.instructions.set(insn, new IntInsnNode(Opcodes.SIPUSH, 512));
                            System.out.println("Updated ArclightCallbackExecutor max tasks from 64 to 512");
                        }
                    } else if (insn.getOpcode() == Opcodes.LDC) {
                        LdcInsnNode ldc = (LdcInsnNode) insn;
                        if (ldc.cst instanceof Long && (Long) ldc.cst == 5000000L) {
                            ldc.cst = 20000000L;
                            System.out.println("Updated ArclightCallbackExecutor time limit from 5ms to 20ms");
                        }
                    }
                }
                break;
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }

    private static byte[] patchStructureTemplatePaletteMixin(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (MethodNode mn : cn.methods) {
            if (mn.name.startsWith("lambda$blocks$1") && mn.desc.equals("(Lnet/minecraft/world/level/block/Block;)Ljava/util/List;")) {
                mn.instructions.clear();
                InsnList insns = new InsnList();

                // List<StructureBlockInfo> result = new ArrayList<>();
                insns.add(new TypeInsnNode(Opcodes.NEW, "java/util/ArrayList"));
                insns.add(new InsnNode(Opcodes.DUP));
                insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false));
                insns.add(new VarInsnNode(Opcodes.ASTORE, 2));

                // Iterator iter = this.f_74645_.iterator();
                insns.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insns.add(new FieldInsnNode(Opcodes.GETFIELD, "io/izzel/arclight/common/mixin/core/world/level/levelgen/structure/templatesystem/StructureTemplate_PaletteMixin", "f_74645_", "Ljava/util/List;"));
                insns.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true));
                insns.add(new VarInsnNode(Opcodes.ASTORE, 3));

                LabelNode loopStart = new LabelNode();
                LabelNode loopEnd = new LabelNode();

                insns.add(loopStart);
                insns.add(new VarInsnNode(Opcodes.ALOAD, 3));
                insns.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true));
                insns.add(new JumpInsnNode(Opcodes.IFEQ, loopEnd));

                insns.add(new VarInsnNode(Opcodes.ALOAD, 3));
                insns.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true));
                insns.add(new TypeInsnNode(Opcodes.CHECKCAST, "net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate$StructureBlockInfo"));
                insns.add(new VarInsnNode(Opcodes.ASTORE, 4));

                insns.add(new VarInsnNode(Opcodes.ALOAD, 4));
                insns.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate$StructureBlockInfo", "f_74676_", "()Lnet/minecraft/world/level/block/state/BlockState;", false));
                insns.add(new VarInsnNode(Opcodes.ALOAD, 1));
                insns.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/level/block/state/BlockState", "m_60713_", "(Lnet/minecraft/world/level/block/Block;)Z", false));
                insns.add(new JumpInsnNode(Opcodes.IFEQ, loopStart));

                insns.add(new VarInsnNode(Opcodes.ALOAD, 2));
                insns.add(new VarInsnNode(Opcodes.ALOAD, 4));
                insns.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true));
                insns.add(new InsnNode(Opcodes.POP));
                insns.add(new JumpInsnNode(Opcodes.GOTO, loopStart));

                insns.add(loopEnd);
                insns.add(new VarInsnNode(Opcodes.ALOAD, 2));
                insns.add(new InsnNode(Opcodes.ARETURN));

                mn.instructions.add(insns);
                System.out.println("Rewrote StructureTemplate_PaletteMixin.lambda$blocks$1 with zero-stream loop");
                break;
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }

    private static byte[] createPoiSectionMixinBytes() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V17, Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT,
                "io/izzel/arclight/common/mixin/core/world/entity/ai/village/poi/PoiSectionMixin",
                null, "java/lang/Object", null);

        // @Mixin(PoiSection.class)
        AnnotationVisitor av = cw.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        AnnotationVisitor avTargets = av.visitArray("value");
        avTargets.visit(null, Type.getType("Lnet/minecraft/world/entity/ai/village/poi/PoiSection;"));
        avTargets.visitEnd();
        av.visitEnd();

        // Default constructor
        MethodVisitor mvConstructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mvConstructor.visitCode();
        mvConstructor.visitVarInsn(Opcodes.ALOAD, 0);
        mvConstructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mvConstructor.visitInsn(Opcodes.RETURN);
        mvConstructor.visitMaxs(1, 1);
        mvConstructor.visitEnd();

        // Redirect method
        // @Redirect(method = "m_27279_", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", remap = false), require = 0)
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PRIVATE, "arclight$silencePoiMismatch",
                "(Lorg/slf4j/Logger;Ljava/lang/String;Ljava/lang/Object;)V", null, null);

        AnnotationVisitor redirAv = mv.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/Redirect;", false);
        AnnotationVisitor redirMethod = redirAv.visitArray("method");
        redirMethod.visit(null, "m_27279_");
        redirMethod.visit(null, "remove");
        redirMethod.visitEnd();

        AnnotationVisitor atAv = redirAv.visitAnnotation("at", "Lorg/spongepowered/asm/mixin/injection/At;");
        atAv.visit("value", "INVOKE");
        atAv.visit("target", "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V");
        atAv.visit("remap", false);
        atAv.visitEnd();

        redirAv.visit("require", 0);
        redirAv.visitEnd();

        mv.visitCode();
        // Do nothing: silence error log
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 4);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static byte[] createPayloadPacketMixin(String mixinClassName, String targetClass) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V17, Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT,
                mixinClassName, null, "java/lang/Object", null);

        // @Mixin(Target.class)
        AnnotationVisitor av = cw.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        AnnotationVisitor avTargets = av.visitArray("value");
        avTargets.visit(null, Type.getType("L" + targetClass + ";"));
        avTargets.visitEnd();
        av.visitEnd();

        // Default constructor
        MethodVisitor mvConstructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mvConstructor.visitCode();
        mvConstructor.visitVarInsn(Opcodes.ALOAD, 0);
        mvConstructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mvConstructor.visitInsn(Opcodes.RETURN);
        mvConstructor.visitMaxs(1, 1);
        mvConstructor.visitEnd();

        // @ModifyConstant(method = "<init>", constant = @Constant(intValue = 32767), remap = false, require = 0)
        // private int arclight$increaseMaxPayload(int constant) { return Integer.MAX_VALUE; }
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PRIVATE, "arclight$increaseMaxPayload", "(I)I", null, null);

        AnnotationVisitor mcAv = mv.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/ModifyConstant;", false);
        AnnotationVisitor methodArray = mcAv.visitArray("method");
        methodArray.visit(null, "<init>");
        methodArray.visitEnd();

        AnnotationVisitor constantAv = mcAv.visitAnnotation("constant", "Lorg/spongepowered/asm/mixin/injection/Constant;");
        constantAv.visit("intValue", 32767);
        constantAv.visitEnd();

        mcAv.visit("remap", false);
        mcAv.visit("require", 0);
        mcAv.visitEnd();

        mv.visitCode();
        mv.visitLdcInsn(Integer.MAX_VALUE);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(1, 2);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static byte[] createCompressionDecoderMixinBytes() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V17, Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT,
                "io/izzel/arclight/common/mixin/core/network/CompressionDecoderMixin", null, "java/lang/Object", null);

        // @Mixin(CompressionDecoder.class)
        AnnotationVisitor av = cw.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        AnnotationVisitor avTargets = av.visitArray("value");
        avTargets.visit(null, Type.getType("Lnet/minecraft/network/CompressionDecoder;"));
        avTargets.visitEnd();
        av.visitEnd();

        // Default constructor
        MethodVisitor mvConstructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mvConstructor.visitCode();
        mvConstructor.visitVarInsn(Opcodes.ALOAD, 0);
        mvConstructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mvConstructor.visitInsn(Opcodes.RETURN);
        mvConstructor.visitMaxs(1, 1);
        mvConstructor.visitEnd();

        // @ModifyConstant(method = {"decode", "m_6926_"}, constant = @Constant(intValue = 2097152), remap = false, require = 0)
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PRIVATE, "arclight$increaseMaxDecompress", "(I)I", null, null);

        AnnotationVisitor mcAv = mv.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/ModifyConstant;", false);
        AnnotationVisitor methodArray = mcAv.visitArray("method");
        methodArray.visit(null, "decode");
        methodArray.visit(null, "m_6926_");
        methodArray.visitEnd();

        AnnotationVisitor constantAv = mcAv.visitAnnotation("constant", "Lorg/spongepowered/asm/mixin/injection/Constant;");
        constantAv.visit("intValue", 2097152);
        constantAv.visitEnd();

        mcAv.visit("remap", false);
        mcAv.visit("require", 0);
        mcAv.visitEnd();

        mv.visitCode();
        mv.visitLdcInsn(Integer.MAX_VALUE);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(1, 2);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static byte[] createFriendlyByteBufMixinBytes() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V17, Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT,
                "io/izzel/arclight/common/mixin/core/network/FriendlyByteBufMixin", null, "java/lang/Object", null);

        // @Mixin(FriendlyByteBuf.class)
        AnnotationVisitor av = cw.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        AnnotationVisitor avTargets = av.visitArray("value");
        avTargets.visit(null, Type.getType("Lnet/minecraft/network/FriendlyByteBuf;"));
        avTargets.visitEnd();
        av.visitEnd();

        // Default constructor
        MethodVisitor mvConstructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mvConstructor.visitCode();
        mvConstructor.visitVarInsn(Opcodes.ALOAD, 0);
        mvConstructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mvConstructor.visitInsn(Opcodes.RETURN);
        mvConstructor.visitMaxs(1, 1);
        mvConstructor.visitEnd();

        // 1. @ModifyConstant(method = {"readUtf", "m_130277_"}, constant = @Constant(intValue = 32767), remap = false, require = 0)
        MethodVisitor mvUtf = cw.visitMethod(Opcodes.ACC_PRIVATE, "arclight$increaseMaxUtf", "(I)I", null, null);
        AnnotationVisitor mcAvUtf = mvUtf.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/ModifyConstant;", false);
        AnnotationVisitor methodArrayUtf = mcAvUtf.visitArray("method");
        methodArrayUtf.visit(null, "readUtf");
        methodArrayUtf.visit(null, "m_130277_");
        methodArrayUtf.visitEnd();

        AnnotationVisitor constantAvUtf = mcAvUtf.visitAnnotation("constant", "Lorg/spongepowered/asm/mixin/injection/Constant;");
        constantAvUtf.visit("intValue", 32767);
        constantAvUtf.visitEnd();

        mcAvUtf.visit("remap", false);
        mcAvUtf.visit("require", 0);
        mcAvUtf.visitEnd();

        mvUtf.visitCode();
        mvUtf.visitLdcInsn(536870911); // Integer.MAX_VALUE / 4
        mvUtf.visitInsn(Opcodes.IRETURN);
        mvUtf.visitMaxs(1, 2);
        mvUtf.visitEnd();

        // 2. @ModifyConstant(method = {"readNbt", "m_130260_"}, constant = @Constant(longValue = 2097152L), remap = false, require = 0)
        MethodVisitor mvNbt = cw.visitMethod(Opcodes.ACC_PRIVATE, "arclight$increaseMaxNbt", "(J)J", null, null);
        AnnotationVisitor mcAvNbt = mvNbt.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/ModifyConstant;", false);
        AnnotationVisitor methodArrayNbt = mcAvNbt.visitArray("method");
        methodArrayNbt.visit(null, "readNbt");
        methodArrayNbt.visit(null, "m_130260_");
        methodArrayNbt.visitEnd();

        AnnotationVisitor constantAvNbt = mcAvNbt.visitAnnotation("constant", "Lorg/spongepowered/asm/mixin/injection/Constant;");
        constantAvNbt.visit("longValue", 2097152L);
        constantAvNbt.visitEnd();

        mcAvNbt.visit("remap", false);
        mcAvNbt.visit("require", 0);
        mcAvNbt.visitEnd();

        mvNbt.visitCode();
        mvNbt.visitLdcInsn(2147483647L);
        mvNbt.visitInsn(Opcodes.LRETURN);
        mvNbt.visitMaxs(2, 3);
        mvNbt.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static void unzip(File zipFile, File destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            byte[] buffer = new byte[8192];
            while ((entry = zis.getNextEntry()) != null) {
                File target = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    target.mkdirs();
                } else {
                    target.getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(target)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }

    private static void zip(File sourceDir, File zipFile) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            zipDir(sourceDir, sourceDir, zos);
        }
    }

    private static void zipDir(File rootDir, File currentDir, ZipOutputStream zos) throws IOException {
        File[] files = currentDir.listFiles();
        if (files == null) return;
        byte[] buffer = new byte[8192];
        for (File file : files) {
            if (file.getName().equals(zipFile_exclude(file))) continue;
            String relative = rootDir.toPath().relativize(file.toPath()).toString().replace('\\', '/');
            if (file.isDirectory()) {
                if (!relative.isEmpty()) {
                    ZipEntry entry = new ZipEntry(relative + "/");
                    zos.putNextEntry(entry);
                    zos.closeEntry();
                }
                zipDir(rootDir, file, zos);
            } else {
                ZipEntry entry = new ZipEntry(relative);
                zos.putNextEntry(entry);
                try (FileInputStream fis = new FileInputStream(file)) {
                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                }
                zos.closeEntry();
            }
        }
    }

    private static String zipFile_exclude(File f) {
        return "repacked_outer.jar";
    }

    private static void deleteDir(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) deleteDir(f);
                    else f.delete();
                }
            }
            dir.delete();
        }
    }
}
