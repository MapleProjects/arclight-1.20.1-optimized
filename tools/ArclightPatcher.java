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
            System.out.println("Patching ServerChunkCache_MainThreadExecutorMixin.class with 512-batch drain...");
            byte[] bytes = Files.readAllBytes(mainThreadExecMixinFile.toPath());
            byte[] patched = patchMainThreadExecutorMixin(bytes);
            Files.write(mainThreadExecMixinFile.toPath(), patched);
        }

        // 5.1 Patch ArclightCallbackExecutor.class in common.jar
        File callbackExecFile = new File(commonDir, "io/izzel/arclight/common/mod/util/ArclightCallbackExecutor.class");
        if (callbackExecFile.exists()) {
            System.out.println("Patching ArclightCallbackExecutor.class to 1024 tasks and 50ms limit...");
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

        // 6.2 Compile and add Mathematical Chunk Engine & Feature Placement Optimization
        compileAndInjectChunkMathOptimizer(commonDir);

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
            if (!jsonContent.contains("world.level.levelgen.NoiseBasedChunkGeneratorMixin")) {
                jsonContent = jsonContent.replace(
                    "\"world.level.chunk.ChunkGeneratorMixin\",",
                    "\"world.level.chunk.ChunkGeneratorMixin\",\n    \"world.level.levelgen.NoiseBasedChunkGeneratorMixin\",\n    \"world.level.levelgen.SurfaceSystemMixin\",\n    \"world.level.levelgen.placement.PlacedFeatureMixin\","
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

                // System.setProperty("max.bg.threads", String.valueOf(Math.max(Runtime.getRuntime().availableProcessors() * 2, 32)));
                insns.add(new LdcInsnNode("max.bg.threads"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Runtime", "availableProcessors", "()I", false));
                insns.add(new InsnNode(Opcodes.ICONST_2));
                insns.add(new InsnNode(Opcodes.IMUL));
                insns.add(new IntInsnNode(Opcodes.BIPUSH, 32));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("paper.max-chunk-sends-per-tick", "4096");
                insns.add(new LdcInsnNode("paper.max-chunk-sends-per-tick"));
                insns.add(new LdcInsnNode("4096"));
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

                // System.setProperty("max.bg.threads", String.valueOf(Math.max(Runtime.getRuntime().availableProcessors() * 2, 32)));
                insns.add(new LdcInsnNode("max.bg.threads"));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Runtime", "availableProcessors", "()I", false));
                insns.add(new InsnNode(Opcodes.ICONST_2));
                insns.add(new InsnNode(Opcodes.IMUL));
                insns.add(new IntInsnNode(Opcodes.BIPUSH, 32));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false));
                insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/System", "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                insns.add(new InsnNode(Opcodes.POP));

                // System.setProperty("paper.max-chunk-sends-per-tick", "4096");
                insns.add(new LdcInsnNode("paper.max-chunk-sends-per-tick"));
                insns.add(new LdcInsnNode("4096"));
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
                                // Add 511 consecutive drains without branching
                                InsnList drainList = new InsnList();
                                for (int i = 0; i < 511; i++) {
                                    drainList.add(new VarInsnNode(Opcodes.ALOAD, 0));
                                    drainList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "net/minecraft/util/thread/BlockableEventLoop", "m_7245_", "()Z", false));
                                    drainList.add(new InsnNode(Opcodes.POP));
                                }

                                mn.instructions.insert(nextNode, drainList);
                                System.out.println("Injected 512-task batch chunk event loop drain into ServerChunkCache_MainThreadExecutorMixin.m_7245_");
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
                    if (insn.getOpcode() == Opcodes.BIPUSH || insn.getOpcode() == Opcodes.SIPUSH) {
                        IntInsnNode iin = (IntInsnNode) insn;
                        if (iin.operand == 64 || iin.operand == 512) {
                            mn.instructions.set(insn, new IntInsnNode(Opcodes.SIPUSH, 1024));
                            System.out.println("Updated ArclightCallbackExecutor max tasks to 1024");
                        }
                    } else if (insn.getOpcode() == Opcodes.LDC) {
                        LdcInsnNode ldc = (LdcInsnNode) insn;
                        if (ldc.cst instanceof Long && ((Long) ldc.cst == 5000000L || (Long) ldc.cst == 20000000L)) {
                            ldc.cst = 50000000L;
                            System.out.println("Updated ArclightCallbackExecutor time limit to 50ms");
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

    private static void compileAndInjectChunkMathOptimizer(File commonDir) {
        try {
            File srcDir = new File("/tmp/arclight_math_src");
            deleteDir(srcDir);
            srcDir.mkdirs();

            File optPkg = new File(srcDir, "io/izzel/arclight/common/mod/util");
            optPkg.mkdirs();
            File mixinPkg = new File(srcDir, "io/izzel/arclight/common/mixin/core/world/level/levelgen");
            mixinPkg.mkdirs();
            File mcMixinPkg = new File(srcDir, "net/minecraft/world/level/levelgen");
            mcMixinPkg.mkdirs();

            String optSrc = "package io.izzel.arclight.common.mod.util;\n\n" +
                "import net.minecraft.core.BlockPos;\n" +
                "import net.minecraft.core.Holder;\n" +
                "import net.minecraft.core.Registry;\n" +
                "import net.minecraft.world.level.biome.Biome;\n" +
                "import net.minecraft.world.level.block.Blocks;\n" +
                "import net.minecraft.world.level.block.state.BlockState;\n" +
                "import net.minecraft.world.level.chunk.ChunkAccess;\n" +
                "import net.minecraft.world.level.levelgen.Aquifer;\n" +
                "import net.minecraft.world.level.levelgen.NoiseChunk;\n" +
                "import net.minecraft.world.level.levelgen.RandomState;\n" +
                "import net.minecraft.world.level.levelgen.SurfaceRules;\n" +
                "import net.minecraft.world.level.levelgen.SurfaceSystem;\n" +
                "import net.minecraft.world.level.levelgen.WorldGenerationContext;\n" +
                "import sun.misc.Unsafe;\n" +
                "import java.lang.invoke.MethodHandle;\n" +
                "import java.lang.invoke.MethodHandles;\n" +
                "import java.lang.reflect.Constructor;\n" +
                "import java.lang.reflect.Field;\n" +
                "import java.lang.reflect.Method;\n" +
                "import java.util.List;\n" +
                "import java.util.function.Function;\n\n" +
                "public class ChunkGenMathOptimizer {\n" +
                "    private static final Unsafe UNSAFE;\n" +
                "    private static final long INTERPOLATORS_OFFSET;\n" +
                "    private static final long SLICE0_OFFSET;\n" +
                "    private static final long SLICE1_OFFSET;\n" +
                "    private static final MethodHandle MH_CELL_WIDTH;\n" +
                "    private static final MethodHandle MH_CELL_HEIGHT;\n" +
                "    private static final MethodHandle MH_COMPUTE_BLOCKSTATE;\n" +
                "    private static final MethodHandle MH_SURFACE_CONTEXT_INIT;\n" +
                "    private static final MethodHandle MH_SURFACE_UPDATE_XZ;\n" +
                "    private static final MethodHandle MH_SURFACE_UPDATE_Y;\n" +
                "    private static final MethodHandle MH_APPLY_SURFACE_RULE;\n" +
                "    private static final boolean INITIALIZED;\n\n" +
                "    public static final BlockState STONE_STATE;\n" +
                "    public static final BlockState DEEPSLATE_STATE;\n\n" +
                "    static {\n" +
                "        Unsafe u = null;\n" +
                "        long interpOff = 0;\n" +
                "        long s0Off = 0;\n" +
                "        long s1Off = 0;\n" +
                "        MethodHandle mhWidth = null;\n" +
                "        MethodHandle mhHeight = null;\n" +
                "        MethodHandle mhBlockState = null;\n" +
                "        MethodHandle mhSurfaceContext = null;\n" +
                "        MethodHandle mhUpdateXZ = null;\n" +
                "        MethodHandle mhUpdateY = null;\n" +
                "        MethodHandle mhApplyRule = null;\n" +
                "        boolean init = false;\n" +
                "        try {\n" +
                "            Field f = Unsafe.class.getDeclaredField(\"theUnsafe\");\n" +
                "            f.setAccessible(true);\n" +
                "            u = (Unsafe) f.get(null);\n\n" +
                "            Field interpolatorsField = NoiseChunk.class.getDeclaredField(\"f_188725_\");\n" +
                "            interpOff = u.objectFieldOffset(interpolatorsField);\n\n" +
                "            Class<?> interpClass = Class.forName(\"net.minecraft.world.level.levelgen.NoiseChunk$NoiseInterpolator\");\n" +
                "            Field s0Field = interpClass.getDeclaredField(\"f_188828_\");\n" +
                "            Field s1Field = interpClass.getDeclaredField(\"f_188829_\");\n" +
                "            s0Off = u.objectFieldOffset(s0Field);\n" +
                "            s1Off = u.objectFieldOffset(s1Field);\n\n" +
                "            MethodHandles.Lookup lookup = MethodHandles.lookup();\n" +
                "            Method mWidth = NoiseChunk.class.getDeclaredMethod(\"m_224362_\");\n" +
                "            mWidth.setAccessible(true);\n" +
                "            mhWidth = lookup.unreflect(mWidth);\n\n" +
                "            Method mHeight = NoiseChunk.class.getDeclaredMethod(\"m_224363_\");\n" +
                "            mHeight.setAccessible(true);\n" +
                "            mhHeight = lookup.unreflect(mHeight);\n\n" +
                "            Method mBlockState = NoiseChunk.class.getDeclaredMethod(\"m_209247_\");\n" +
                "            mBlockState.setAccessible(true);\n" +
                "            mhBlockState = lookup.unreflect(mBlockState);\n\n" +
                "            Class<?> ctxClass = Class.forName(\"net.minecraft.world.level.levelgen.SurfaceRules$Context\");\n" +
                "            Constructor<?> ctor = ctxClass.getDeclaredConstructors()[0];\n" +
                "            ctor.setAccessible(true);\n" +
                "            mhSurfaceContext = lookup.unreflectConstructor(ctor);\n\n" +
                "            Method mUpdateXZ = ctxClass.getDeclaredMethod(\"m_189569_\", int.class, int.class);\n" +
                "            mUpdateXZ.setAccessible(true);\n" +
                "            mhUpdateXZ = lookup.unreflect(mUpdateXZ);\n\n" +
                "            Method mUpdateY = ctxClass.getDeclaredMethod(\"m_189576_\", int.class, int.class, int.class, int.class, int.class, int.class);\n" +
                "            mUpdateY.setAccessible(true);\n" +
                "            mhUpdateY = lookup.unreflect(mUpdateY);\n\n" +
                "            Class<?> ruleClass = Class.forName(\"net.minecraft.world.level.levelgen.SurfaceRules$SurfaceRule\");\n" +
                "            Method mApply = ruleClass.getDeclaredMethod(\"m_183550_\", int.class, int.class, int.class);\n" +
                "            mApply.setAccessible(true);\n" +
                "            mhApplyRule = lookup.unreflect(mApply);\n\n" +
                "            init = true;\n" +
                "        } catch (Throwable t) {\n" +
                "            init = false;\n" +
                "        }\n" +
                "        UNSAFE = u;\n" +
                "        INTERPOLATORS_OFFSET = interpOff;\n" +
                "        SLICE0_OFFSET = s0Off;\n" +
                "        SLICE1_OFFSET = s1Off;\n" +
                "        MH_CELL_WIDTH = mhWidth;\n" +
                "        MH_CELL_HEIGHT = mhHeight;\n" +
                "        MH_COMPUTE_BLOCKSTATE = mhBlockState;\n" +
                "        MH_SURFACE_CONTEXT_INIT = mhSurfaceContext;\n" +
                "        MH_SURFACE_UPDATE_XZ = mhUpdateXZ;\n" +
                "        MH_SURFACE_UPDATE_Y = mhUpdateY;\n" +
                "        MH_APPLY_SURFACE_RULE = mhApplyRule;\n" +
                "        INITIALIZED = init;\n\n" +
                "        STONE_STATE = Blocks.f_50069_.m_49966_();\n" +
                "        DEEPSLATE_STATE = Blocks.f_152482_.m_49966_();\n" +
                "    }\n\n" +
                "    public static int getCellWidth(NoiseChunk noiseChunk) {\n" +
                "        if (MH_CELL_WIDTH != null) {\n" +
                "            try { return (int) MH_CELL_WIDTH.invokeExact(noiseChunk); } catch (Throwable ignored) {}\n" +
                "        }\n" +
                "        return 4;\n" +
                "    }\n\n" +
                "    public static int getCellHeight(NoiseChunk noiseChunk) {\n" +
                "        if (MH_CELL_HEIGHT != null) {\n" +
                "            try { return (int) MH_CELL_HEIGHT.invokeExact(noiseChunk); } catch (Throwable ignored) {}\n" +
                "        }\n" +
                "        return 8;\n" +
                "    }\n\n" +
                "    public static BlockState computeBlockState(NoiseChunk noiseChunk) {\n" +
                "        if (MH_COMPUTE_BLOCKSTATE != null) {\n" +
                "            try { return (BlockState) MH_COMPUTE_BLOCKSTATE.invokeExact(noiseChunk); } catch (Throwable ignored) {}\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n\n" +
                "    public static Object createSurfaceContext(SurfaceSystem system, RandomState randomState, ChunkAccess chunk, NoiseChunk noiseChunk, Function<BlockPos, Holder<Biome>> biomeGetter, Registry<Biome> biomes, WorldGenerationContext context) {\n" +
                "        if (MH_SURFACE_CONTEXT_INIT != null) {\n" +
                "            try { return MH_SURFACE_CONTEXT_INIT.invoke(system, randomState, chunk, noiseChunk, biomeGetter, biomes, context); } catch (Throwable t) { throw new RuntimeException(t); }\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n\n" +
                "    public static void updateSurfaceContextXZ(Object context, int x, int z) {\n" +
                "        if (MH_SURFACE_UPDATE_XZ != null && context != null) {\n" +
                "            try { MH_SURFACE_UPDATE_XZ.invoke(context, x, z); } catch (Throwable ignored) {}\n" +
                "        }\n" +
                "    }\n\n" +
                "    public static void updateSurfaceContextY(Object context, int stoneDepthAbove, int stoneDepthBelow, int waterHeight, int x, int y, int z) {\n" +
                "        if (MH_SURFACE_UPDATE_Y != null && context != null) {\n" +
                "            try { MH_SURFACE_UPDATE_Y.invoke(context, stoneDepthAbove, stoneDepthBelow, waterHeight, x, y, z); } catch (Throwable ignored) {}\n" +
                "        }\n" +
                "    }\n\n" +
                "    public static BlockState applySurfaceRule(Object surfaceRule, int x, int y, int z) {\n" +
                "        if (MH_APPLY_SURFACE_RULE != null && surfaceRule != null) {\n" +
                "            try { return (BlockState) MH_APPLY_SURFACE_RULE.invoke(surfaceRule, x, y, z); } catch (Throwable ignored) {}\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n\n" +
                "    @SuppressWarnings(\"unchecked\")\n" +
                "    public static int classifyCell(NoiseChunk noiseChunk, int cellY, int cellZ, int minCellY, int cellHeight, Aquifer aquifer) {\n" +
                "        if (!INITIALIZED) return 0;\n" +
                "        int worldYBase = (minCellY + cellY) * cellHeight;\n" +
                "        try {\n" +
                "            List<?> interpolators = (List<?>) UNSAFE.getObject(noiseChunk, INTERPOLATORS_OFFSET);\n" +
                "            if (interpolators == null || interpolators.isEmpty()) return 0;\n" +
                "            Object interp = interpolators.get(0);\n" +
                "            double[][] s0 = (double[][]) UNSAFE.getObject(interp, SLICE0_OFFSET);\n" +
                "            double[][] s1 = (double[][]) UNSAFE.getObject(interp, SLICE1_OFFSET);\n" +
                "            if (s0 == null || s1 == null) return 0;\n\n" +
                "            double v000 = s0[cellZ][cellY];\n" +
                "            double v001 = s0[cellZ + 1][cellY];\n" +
                "            double v100 = s1[cellZ][cellY];\n" +
                "            double v101 = s1[cellZ + 1][cellY];\n" +
                "            double v010 = s0[cellZ][cellY + 1];\n" +
                "            double v011 = s0[cellZ + 1][cellY + 1];\n" +
                "            double v110 = s1[cellZ][cellY + 1];\n" +
                "            double v111 = s1[cellZ + 1][cellY + 1];\n\n" +
                "            // 1. Pure Air Culling (above sea level)\n" +
                "            if (worldYBase >= 64 && (aquifer == null || !aquifer.m_142203_())) {\n" +
                "                double max0 = Math.max(Math.max(v000, v001), Math.max(v100, v101));\n" +
                "                double max1 = Math.max(Math.max(v010, v011), Math.max(v110, v111));\n" +
                "                if (Math.max(max0, max1) <= -0.0001) {\n" +
                "                    return 1;\n" +
                "                }\n" +
                "            }\n\n" +
                "            // 2. Pure Solid Deepslate (deep underground)\n" +
                "            if (worldYBase + cellHeight <= -8 && (aquifer == null || !aquifer.m_142203_())) {\n" +
                "                double min0 = Math.min(Math.min(v000, v001), Math.min(v100, v101));\n" +
                "                double min1 = Math.min(Math.min(v010, v011), Math.min(v110, v111));\n" +
                "                if (Math.min(min0, min1) >= 0.05) {\n" +
                "                    return 2;\n" +
                "                }\n" +
                "            }\n\n" +
                "            // 3. Pure Solid Stone (subsurface layer)\n" +
                "            if (worldYBase >= 0 && worldYBase + cellHeight <= 50 && (aquifer == null || !aquifer.m_142203_())) {\n" +
                "                double min0 = Math.min(Math.min(v000, v001), Math.min(v100, v101));\n" +
                "                double min1 = Math.min(Math.min(v010, v011), Math.min(v110, v111));\n" +
                "                if (Math.min(min0, min1) >= 0.05) {\n" +
                "                    return 3;\n" +
                "                }\n" +
                "            }\n" +
                "        } catch (Throwable ignored) {}\n" +
                "        return 0;\n" +
                "    }\n" +
                "}\n";

            String noiseMixinSrc = "package io.izzel.arclight.common.mixin.core.world.level.levelgen;\n\n" +
                "import io.izzel.arclight.common.mod.util.ChunkGenMathOptimizer;\n" +
                "import net.minecraft.core.BlockPos;\n" +
                "import net.minecraft.core.Holder;\n" +
                "import net.minecraft.world.level.ChunkPos;\n" +
                "import net.minecraft.world.level.StructureManager;\n" +
                "import net.minecraft.world.level.block.state.BlockState;\n" +
                "import net.minecraft.world.level.chunk.ChunkAccess;\n" +
                "import net.minecraft.world.level.chunk.LevelChunkSection;\n" +
                "import net.minecraft.world.level.levelgen.Aquifer;\n" +
                "import net.minecraft.world.level.levelgen.Heightmap;\n" +
                "import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;\n" +
                "import net.minecraft.world.level.levelgen.NoiseChunk;\n" +
                "import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;\n" +
                "import net.minecraft.world.level.levelgen.RandomState;\n" +
                "import net.minecraft.world.level.levelgen.blending.Blender;\n" +
                "import net.minecraft.world.level.material.FluidState;\n" +
                "import org.spongepowered.asm.mixin.Final;\n" +
                "import org.spongepowered.asm.mixin.Mixin;\n" +
                "import org.spongepowered.asm.mixin.Overwrite;\n" +
                "import org.spongepowered.asm.mixin.Shadow;\n\n" +
                "@Mixin(value = NoiseBasedChunkGenerator.class, priority = 500)\n" +
                "public abstract class NoiseBasedChunkGeneratorMixin {\n\n" +
                "    @Shadow @Final private Holder<NoiseGeneratorSettings> f_64318_;\n" +
                "    @Shadow @Final private static BlockState f_64321_;\n\n" +
                "    @Shadow protected abstract NoiseChunk m_224251_(StructureManager structureManager, Blender blender, RandomState randomState, ChunkAccess chunkAccess);\n" +
                "    @Shadow private native BlockState m_198231_(NoiseChunk noiseChunk, int x, int y, int z, BlockState blockState);\n\n" +
                "    /**\n" +
                "     * @author Maple Mathematical Engine Restructuring\n" +
                "     * @reason Bedrock-grade analytical horizon culling & interval bounding for nanosecond chunk generation\n" +
                "     */\n" +
                "    @Overwrite(remap = false)\n" +
                "    private ChunkAccess m_224284_(Blender blender, StructureManager structureManager, RandomState randomState, ChunkAccess chunk, int minCellY, int cellCountY) {\n" +
                "        NoiseChunk noiseChunk = chunk.m_223012_(generator -> this.m_224251_(structureManager, blender, randomState, chunk));\n" +
                "        Heightmap oceanFloor = chunk.m_6005_(Heightmap.Types.OCEAN_FLOOR_WG);\n" +
                "        Heightmap worldSurface = chunk.m_6005_(Heightmap.Types.WORLD_SURFACE_WG);\n" +
                "        ChunkPos chunkPos = chunk.m_7697_();\n" +
                "        int minBlockX = chunkPos.m_45604_();\n" +
                "        int minBlockZ = chunkPos.m_45605_();\n" +
                "        Aquifer aquifer = noiseChunk.m_188817_();\n" +
                "        noiseChunk.m_188791_();\n" +
                "        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();\n\n" +
                "        int cellWidth = ChunkGenMathOptimizer.getCellWidth(noiseChunk);\n" +
                "        int cellHeight = ChunkGenMathOptimizer.getCellHeight(noiseChunk);\n" +
                "        int cellCountX = 16 / cellWidth;\n" +
                "        int cellCountZ = 16 / cellWidth;\n\n" +
                "        BlockState defaultBlock = this.f_64318_.m_203334_().f_64440_();\n\n" +
                "        for (int cellX = 0; cellX < cellCountX; ++cellX) {\n" +
                "            noiseChunk.m_188749_(cellX);\n\n" +
                "            for (int cellZ = 0; cellZ < cellCountZ; ++cellZ) {\n" +
                "                int lastSectionIndex = -1;\n" +
                "                LevelChunkSection section = null;\n\n" +
                "                for (int cellY = cellCountY - 1; cellY >= 0; --cellY) {\n" +
                "                    noiseChunk.m_188810_(cellY, cellZ);\n" +
                "                    int worldYBase = (minCellY + cellY) * cellHeight;\n\n" +
                "                    int cellType = ChunkGenMathOptimizer.classifyCell(noiseChunk, cellY, cellZ, minCellY, cellHeight, aquifer);\n" +
                "                    if (cellType == 1) {\n" +
                "                        continue;\n" +
                "                    } else if (cellType == 2 || cellType == 3) {\n" +
                "                        BlockState solidState = (cellType == 2) ? ChunkGenMathOptimizer.DEEPSLATE_STATE : ChunkGenMathOptimizer.STONE_STATE;\n" +
                "                        for (int yInside = cellHeight - 1; yInside >= 0; --yInside) {\n" +
                "                            int worldY = worldYBase + yInside;\n" +
                "                            int localY = worldY & 15;\n" +
                "                            int sectionIndex = chunk.m_151564_(worldY);\n" +
                "                            if (lastSectionIndex != sectionIndex) {\n" +
                "                                lastSectionIndex = sectionIndex;\n" +
                "                                section = chunk.m_183278_(sectionIndex);\n" +
                "                            }\n" +
                "                            for (int xInside = 0; xInside < cellWidth; ++xInside) {\n" +
                "                                int localX = (minBlockX + cellX * cellWidth + xInside) & 15;\n" +
                "                                for (int zInside = 0; zInside < cellWidth; ++zInside) {\n" +
                "                                    int localZ = (minBlockZ + cellZ * cellWidth + zInside) & 15;\n" +
                "                                    section.m_62991_(localX, localY, localZ, solidState, false);\n" +
                "                                }\n" +
                "                            }\n" +
                "                        }\n" +
                "                        continue;\n" +
                "                    }\n\n" +
                "                    for (int yInside = cellHeight - 1; yInside >= 0; --yInside) {\n" +
                "                        int worldY = worldYBase + yInside;\n" +
                "                        int localY = worldY & 15;\n" +
                "                        int sectionIndex = chunk.m_151564_(worldY);\n\n" +
                "                        if (lastSectionIndex != sectionIndex) {\n" +
                "                            lastSectionIndex = sectionIndex;\n" +
                "                            section = chunk.m_183278_(sectionIndex);\n" +
                "                        }\n\n" +
                "                        double yRatio = (double) yInside / (double) cellHeight;\n" +
                "                        noiseChunk.m_209191_(worldY, yRatio);\n\n" +
                "                        for (int xInside = 0; xInside < cellWidth; ++xInside) {\n" +
                "                            int worldX = minBlockX + cellX * cellWidth + xInside;\n" +
                "                            int localX = worldX & 15;\n" +
                "                            double xRatio = (double) xInside / (double) cellWidth;\n" +
                "                            noiseChunk.m_209230_(worldX, xRatio);\n\n" +
                "                            for (int zInside = 0; zInside < cellWidth; ++zInside) {\n" +
                "                                int worldZ = minBlockZ + cellZ * cellWidth + zInside;\n" +
                "                                int localZ = worldZ & 15;\n" +
                "                                double zRatio = (double) zInside / (double) cellWidth;\n" +
                "                                noiseChunk.m_209241_(worldZ, zRatio);\n\n" +
                "                                BlockState blockState = ChunkGenMathOptimizer.computeBlockState(noiseChunk);\n" +
                "                                if (blockState == null) {\n" +
                "                                    blockState = defaultBlock;\n" +
                "                                }\n\n" +
                "                                blockState = this.m_198231_(noiseChunk, worldX, worldY, worldZ, blockState);\n" +
                "                                if (blockState != f_64321_ && !net.minecraft.SharedConstants.m_183707_(chunkPos)) {\n" +
                "                                    section.m_62991_(localX, localY, localZ, blockState, false);\n" +
                "                                    oceanFloor.m_64249_(localX, worldY, localZ, blockState);\n" +
                "                                    worldSurface.m_64249_(localX, worldY, localZ, blockState);\n" +
                "                                    if (aquifer.m_142203_()) {\n" +
                "                                        FluidState fluidState = blockState.m_60819_();\n" +
                "                                        if (!fluidState.m_76178_()) {\n" +
                "                                            mutablePos.m_122178_(worldX, worldY, worldZ);\n" +
                "                                            chunk.m_8113_(mutablePos);\n" +
                "                                        }\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            noiseChunk.m_188804_();\n" +
                "        }\n" +
                "        noiseChunk.m_209248_();\n" +
                "        return chunk;\n" +
                "    }\n" +
                "}\n";

            String surfaceMixinSrc = "package io.izzel.arclight.common.mixin.core.world.level.levelgen;\n\n" +
                "import io.izzel.arclight.common.mod.util.ChunkGenMathOptimizer;\n" +
                "import net.minecraft.core.BlockPos;\n" +
                "import net.minecraft.core.Holder;\n" +
                "import net.minecraft.core.Registry;\n" +
                "import net.minecraft.world.level.ChunkPos;\n" +
                "import net.minecraft.world.level.biome.Biome;\n" +
                "import net.minecraft.world.level.biome.BiomeManager;\n" +
                "import net.minecraft.world.level.biome.Biomes;\n" +
                "import net.minecraft.world.level.block.state.BlockState;\n" +
                "import net.minecraft.world.level.chunk.BlockColumn;\n" +
                "import net.minecraft.world.level.chunk.ChunkAccess;\n" +
                "import net.minecraft.world.level.dimension.DimensionType;\n" +
                "import net.minecraft.world.level.levelgen.Heightmap;\n" +
                "import net.minecraft.world.level.levelgen.NoiseChunk;\n" +
                "import net.minecraft.world.level.levelgen.RandomState;\n" +
                "import net.minecraft.world.level.levelgen.SurfaceRules;\n" +
                "import net.minecraft.world.level.levelgen.SurfaceSystem;\n" +
                "import net.minecraft.world.level.levelgen.WorldGenerationContext;\n" +
                "import net.minecraft.world.level.material.FluidState;\n" +
                "import org.spongepowered.asm.mixin.Final;\n" +
                "import org.spongepowered.asm.mixin.Mixin;\n" +
                "import org.spongepowered.asm.mixin.Overwrite;\n" +
                "import org.spongepowered.asm.mixin.Shadow;\n" +
                "import java.util.function.Function;\n\n" +
                "@Mixin(value = SurfaceSystem.class, priority = 500)\n" +
                "public abstract class SurfaceSystemMixin {\n\n" +
                "    @Shadow @Final private BlockState f_189904_;\n\n" +
                "    @Shadow protected abstract void m_189954_(BlockColumn column, int x, int z, int y, net.minecraft.world.level.LevelHeightAccessor accessor);\n" +
                "    @Shadow private native boolean m_189952_(BlockState state);\n\n" +
                "    /**\n" +
                "     * @author Maple Optimization\n" +
                "     * @reason High-performance surface rule evaluator with early-depth termination\n" +
                "     */\n" +
                "    @SuppressWarnings(\"unchecked\")\n" +
                "    @Overwrite(remap = false)\n" +
                "    public void m_224648_(RandomState randomState, BiomeManager biomeManager, Registry<Biome> biomes, boolean useLegacyRandom, WorldGenerationContext context, ChunkAccess chunk, NoiseChunk noiseChunk, SurfaceRules.RuleSource ruleSource) {\n" +
                "        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();\n" +
                "        ChunkPos chunkPos = chunk.m_7697_();\n" +
                "        int minBlockX = chunkPos.m_45604_();\n" +
                "        int minBlockZ = chunkPos.m_45605_();\n\n" +
                "        BlockColumn blockColumn = new BlockColumn() {\n" +
                "            @Override\n" +
                "            public BlockState m_183556_(int y) {\n" +
                "                return chunk.m_8055_(mutablePos.m_122178_(mutablePos.m_123341_(), y, mutablePos.m_123343_()));\n" +
                "            }\n" +
                "            @Override\n" +
                "            public void m_183639_(int y, BlockState state) {\n" +
                "                chunk.m_6978_(mutablePos.m_122178_(mutablePos.m_123341_(), y, mutablePos.m_123343_()), state, false);\n" +
                "            }\n" +
                "        };\n\n" +
                "        Function<BlockPos, Holder<Biome>> biomeGetter = biomeManager::m_204214_;\n" +
                "        Object ruleContext = ChunkGenMathOptimizer.createSurfaceContext((SurfaceSystem)(Object)this, randomState, chunk, noiseChunk, biomeGetter, biomes, context);\n" +
                "        Object surfaceRule = ((Function) ruleSource).apply(ruleContext);\n" +
                "        BlockPos.MutableBlockPos biomePos = new BlockPos.MutableBlockPos();\n" +
                "        int minY = chunk.m_141937_();\n\n" +
                "        for (int localX = 0; localX < 16; ++localX) {\n" +
                "            for (int localZ = 0; localZ < 16; ++localZ) {\n" +
                "                int worldX = minBlockX + localX;\n" +
                "                int worldZ = minBlockZ + localZ;\n" +
                "                int surfaceY = chunk.m_5885_(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ) + 1;\n\n" +
                "                mutablePos.m_142451_(worldX).m_142443_(worldZ);\n" +
                "                Holder<Biome> biomeHolder = biomeManager.m_204214_(biomePos.m_122178_(worldX, useLegacyRandom ? 0 : surfaceY, worldZ));\n\n" +
                "                if (biomeHolder.m_203565_(Biomes.f_48194_)) {\n" +
                "                    this.m_189954_(blockColumn, worldX, worldZ, surfaceY, chunk);\n" +
                "                }\n\n" +
                "                int topY = chunk.m_5885_(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ) + 1;\n" +
                "                ChunkGenMathOptimizer.updateSurfaceContextXZ(ruleContext, worldX, worldZ);\n\n" +
                "                int stoneDepthAbove = 0;\n" +
                "                int waterHeight = Integer.MIN_VALUE;\n" +
                "                int stoneDepthBelowMarker = Integer.MAX_VALUE;\n\n" +
                "                for (int currentY = topY; currentY >= minY; --currentY) {\n" +
                "                    BlockState currentBlock = blockColumn.m_183556_(currentY);\n" +
                "                    if (currentBlock.m_60795_()) {\n" +
                "                        stoneDepthAbove = 0;\n" +
                "                        waterHeight = Integer.MIN_VALUE;\n" +
                "                        continue;\n" +
                "                    }\n\n" +
                "                    FluidState fluidState = currentBlock.m_60819_();\n" +
                "                    if (!fluidState.m_76178_()) {\n" +
                "                        if (waterHeight == Integer.MIN_VALUE) {\n" +
                "                            waterHeight = currentY + 1;\n" +
                "                        }\n" +
                "                        continue;\n" +
                "                    }\n\n" +
                "                    if (stoneDepthBelowMarker >= currentY) {\n" +
                "                        stoneDepthBelowMarker = DimensionType.f_188294_;\n" +
                "                        for (int scanY = currentY - 1; scanY >= minY - 1; --scanY) {\n" +
                "                            BlockState scanBlock = blockColumn.m_183556_(scanY);\n" +
                "                            if (!this.m_189952_(scanBlock)) {\n" +
                "                                stoneDepthBelowMarker = scanY + 1;\n" +
                "                                break;\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n\n" +
                "                    ++stoneDepthAbove;\n" +
                "                    int stoneDepthBelow = currentY - stoneDepthBelowMarker + 1;\n\n" +
                "                    if (stoneDepthAbove > 32 && stoneDepthBelow > 32 && currentY < 50) {\n" +
                "                        break;\n" +
                "                    }\n\n" +
                "                    ChunkGenMathOptimizer.updateSurfaceContextY(ruleContext, stoneDepthAbove, stoneDepthBelow, waterHeight, worldX, currentY, worldZ);\n" +
                "                    if (currentBlock == this.f_189904_) {\n" +
                "                        BlockState ruleState = ChunkGenMathOptimizer.applySurfaceRule(surfaceRule, worldX, currentY, worldZ);\n" +
                "                        if (ruleState != null) {\n" +
                "                            blockColumn.m_183639_(currentY, ruleState);\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

            File placePkg = new File(srcDir, "io/izzel/arclight/common/mixin/core/world/level/levelgen/placement");
            placePkg.mkdirs();

            String placedFeatureMixinSrc = "package io.izzel.arclight.common.mixin.core.world.level.levelgen.placement;\n\n" +
                "import net.minecraft.core.BlockPos;\n" +
                "import net.minecraft.core.Holder;\n" +
                "import net.minecraft.util.RandomSource;\n" +
                "import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;\n" +
                "import net.minecraft.world.level.levelgen.placement.PlacedFeature;\n" +
                "import net.minecraft.world.level.levelgen.placement.PlacementContext;\n" +
                "import net.minecraft.world.level.levelgen.placement.PlacementModifier;\n" +
                "import org.spongepowered.asm.mixin.Final;\n" +
                "import org.spongepowered.asm.mixin.Mixin;\n" +
                "import org.spongepowered.asm.mixin.Overwrite;\n" +
                "import org.spongepowered.asm.mixin.Shadow;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.stream.Stream;\n\n" +
                "@Mixin(value = PlacedFeature.class, priority = 500)\n" +
                "public abstract class PlacedFeatureMixin {\n\n" +
                "    @Shadow @Final private Holder<ConfiguredFeature<?, ?>> f_191775_;\n" +
                "    @Shadow @Final private List<PlacementModifier> f_191776_;\n\n" +
                "    /**\n" +
                "     * @author Maple Optimization\n" +
                "     * @reason High-performance zero-stream iterative feature placement\n" +
                "     */\n" +
                "    @Overwrite(remap = false)\n" +
                "    private boolean m_226368_(PlacementContext context, RandomSource random, BlockPos origin) {\n" +
                "        List<PlacementModifier> modifiers = this.f_191776_;\n" +
                "        if (modifiers.isEmpty()) {\n" +
                "            ConfiguredFeature<?, ?> feature = this.f_191775_.m_203334_();\n" +
                "            return feature.m_224953_(context.m_191831_(), context.m_191833_(), random, origin);\n" +
                "        }\n\n" +
                "        List<BlockPos> inList = new ArrayList<>(8);\n" +
                "        List<BlockPos> outList = new ArrayList<>(8);\n" +
                "        inList.add(origin);\n\n" +
                "        int modCount = modifiers.size();\n" +
                "        for (int i = 0; i < modCount; ++i) {\n" +
                "            PlacementModifier modifier = modifiers.get(i);\n" +
                "            outList.clear();\n" +
                "            int inCount = inList.size();\n" +
                "            for (int j = 0; j < inCount; ++j) {\n" +
                "                BlockPos pos = inList.get(j);\n" +
                "                Stream<BlockPos> stream = modifier.m_213676_(context, random, pos);\n" +
                "                stream.forEach(outList::add);\n" +
                "            }\n" +
                "            if (outList.isEmpty()) {\n" +
                "                return false;\n" +
                "            }\n" +
                "            List<BlockPos> temp = inList;\n" +
                "            inList = outList;\n" +
                "            outList = temp;\n" +
                "        }\n\n" +
                "        if (inList.isEmpty()) {\n" +
                "            return false;\n" +
                "        }\n" +
                "        ConfiguredFeature<?, ?> feature = this.f_191775_.m_203334_();\n" +
                "        boolean placed = false;\n" +
                "        int finalCount = inList.size();\n" +
                "        for (int i = 0; i < finalCount; ++i) {\n" +
                "            if (feature.m_224953_(context.m_191831_(), context.m_191833_(), random, inList.get(i))) {\n" +
                "                placed = true;\n" +
                "            }\n" +
                "        }\n" +
                "        return placed;\n" +
                "    }\n" +
                "}\n";

            Files.writeString(new File(optPkg, "ChunkGenMathOptimizer.java").toPath(), optSrc);
            Files.writeString(new File(mixinPkg, "NoiseBasedChunkGeneratorMixin.java").toPath(), noiseMixinSrc);
            Files.writeString(new File(mixinPkg, "SurfaceSystemMixin.java").toPath(), surfaceMixinSrc);
            Files.writeString(new File(placePkg, "PlacedFeatureMixin.java").toPath(), placedFeatureMixinSrc);

            // Construct classpath from libraries and server jar
            File libDir = new File("/home/maple/Server1-20-1/libraries");
            StringBuilder cp = new StringBuilder();
            buildClasspath(libDir, cp);
            cp.append(File.pathSeparator).append("/home/maple/Server1-20-1/arclight-forge-1.20.1-1.0.6-SNAPSHOT.jar");
            cp.append(File.pathSeparator).append(commonDir.getAbsolutePath());

            ProcessBuilder pb = new ProcessBuilder(
                "javac", "-proc:none", "-cp", cp.toString(), "-d", commonDir.getAbsolutePath(),
                new File(optPkg, "ChunkGenMathOptimizer.java").getAbsolutePath(),
                new File(mixinPkg, "NoiseBasedChunkGeneratorMixin.java").getAbsolutePath(),
                new File(mixinPkg, "SurfaceSystemMixin.java").getAbsolutePath(),
                new File(placePkg, "PlacedFeatureMixin.java").getAbsolutePath()
            );
            pb.redirectErrorStream(true);
            Process p = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[javac-math] " + line);
                }
            }
            int code = p.waitFor();
            if (code == 0) {
                System.out.println("Successfully compiled and injected ChunkGenMathOptimizer, NoiseBasedChunkGeneratorMixin, SurfaceSystemMixin, and PlacedFeatureMixin into common.jar!");
            } else {
                throw new RuntimeException("Failed to compile chunk math optimizer mixins, exit code: " + code);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during chunk math optimizer compilation", e);
        }
    }

    private static void buildClasspath(File dir, StringBuilder cp) {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
                buildClasspath(f, cp);
            } else if (f.getName().endsWith(".jar")) {
                if (cp.length() > 0) cp.append(File.pathSeparator);
                cp.append(f.getAbsolutePath());
            }
        }
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
