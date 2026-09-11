/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.ByteStreams
 *  joptsimple.ArgumentAcceptingOptionSpec
 *  joptsimple.OptionParser
 *  joptsimple.OptionSet
 *  joptsimple.OptionSpec
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.bukkit.Material;
import org.bukkit.plugin.AuthorNagException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class Commodore {
    private static final Set<String> EVIL = new HashSet<String>(Arrays.asList("org/bukkit/World (III)I getBlockTypeIdAt", "org/bukkit/World (Lorg/bukkit/Location;)I getBlockTypeIdAt", "org/bukkit/block/Block ()I getTypeId", "org/bukkit/block/Block (I)Z setTypeId", "org/bukkit/block/Block (IZ)Z setTypeId", "org/bukkit/block/Block (IBZ)Z setTypeIdAndData", "org/bukkit/block/Block (B)V setData", "org/bukkit/block/Block (BZ)V setData", "org/bukkit/inventory/ItemStack ()I getTypeId", "org/bukkit/inventory/ItemStack (I)V setTypeId"));

    public static void main(String[] args) {
        OptionParser parser = new OptionParser();
        ArgumentAcceptingOptionSpec inputFlag = parser.acceptsAll(Arrays.asList("i", "input")).withRequiredArg().ofType(File.class).required();
        ArgumentAcceptingOptionSpec outputFlag = parser.acceptsAll(Arrays.asList("o", "output")).withRequiredArg().ofType(File.class).required();
        OptionSet options = parser.parse(args);
        File input = (File)options.valueOf((OptionSpec)inputFlag);
        File output = (File)options.valueOf((OptionSpec)outputFlag);
        if (input.isDirectory()) {
            if (!output.isDirectory()) {
                System.err.println("If input directory specified, output directory required too");
                return;
            }
            File[] fileArray = input.listFiles();
            int n = fileArray.length;
            int n2 = 0;
            while (n2 < n) {
                File in = fileArray[n2];
                if (in.getName().endsWith(".jar")) {
                    Commodore.convert(in, new File(output, in.getName()));
                }
                ++n2;
            }
        } else {
            Commodore.convert(input, output);
        }
    }

    private static void convert(File in, File out) {
        System.out.println("Attempting to convert " + in + " to " + out);
        try {
            Throwable throwable = null;
            Object var3_5 = null;
            try (JarFile inJar = new JarFile(in, false);){
                JarEntry entry = inJar.getJarEntry(".commodore");
                if (entry != null) {
                    return;
                }
                Throwable throwable2 = null;
                Object var7_11 = null;
                try (JarOutputStream outJar = new JarOutputStream(new FileOutputStream(out));){
                    Enumeration<JarEntry> entries = inJar.entries();
                    while (entries.hasMoreElements()) {
                        entry = entries.nextElement();
                        Throwable throwable3 = null;
                        Object var11_17 = null;
                        try (InputStream is = inJar.getInputStream(entry);){
                            byte[] b = ByteStreams.toByteArray((InputStream)is);
                            if (entry.getName().endsWith(".class")) {
                                b = Commodore.convert(b, false);
                                entry = new JarEntry(entry.getName());
                            }
                            outJar.putNextEntry(entry);
                            outJar.write(b);
                        }
                        catch (Throwable throwable4) {
                            if (throwable3 == null) {
                                throwable3 = throwable4;
                            } else if (throwable3 != throwable4) {
                                throwable3.addSuppressed(throwable4);
                            }
                            throw throwable3;
                        }
                    }
                    outJar.putNextEntry(new ZipEntry(".commodore"));
                }
                catch (Throwable throwable5) {
                    if (throwable2 == null) {
                        throwable2 = throwable5;
                    } else if (throwable2 != throwable5) {
                        throwable2.addSuppressed(throwable5);
                    }
                    throw throwable2;
                }
            }
            catch (Throwable throwable6) {
                if (throwable == null) {
                    throwable = throwable6;
                } else if (throwable != throwable6) {
                    throwable.addSuppressed(throwable6);
                }
                throw throwable;
            }
        }
        catch (Exception ex) {
            System.err.println("Fatal error trying to convert " + in);
            ex.printStackTrace();
        }
    }

    public static byte[] convert(byte[] b, final boolean modern) {
        ClassReader cr = new ClassReader(b);
        ClassWriter cw = new ClassWriter(cr, 0);
        cr.accept(new ClassVisitor(589824, (ClassVisitor)cw){

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                return new MethodVisitor(this.api, super.visitMethod(access, name, desc, signature, exceptions)){

                    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                        block124: {
                            block122: {
                                block120: {
                                    block117: {
                                        block118: {
                                            block115: {
                                                block113: {
                                                    block111: {
                                                        if (!owner.equals("org/bukkit/block/Biome")) break block111;
                                                        switch (name) {
                                                            case "NETHER": {
                                                                super.visitFieldInsn(opcode, owner, "NETHER_WASTES", desc);
                                                                return;
                                                            }
                                                            case "TALL_BIRCH_FOREST": {
                                                                super.visitFieldInsn(opcode, owner, "OLD_GROWTH_BIRCH_FOREST", desc);
                                                                return;
                                                            }
                                                            case "GIANT_TREE_TAIGA": {
                                                                super.visitFieldInsn(opcode, owner, "OLD_GROWTH_PINE_TAIGA", desc);
                                                                return;
                                                            }
                                                            case "GIANT_SPRUCE_TAIGA": {
                                                                super.visitFieldInsn(opcode, owner, "OLD_GROWTH_SPRUCE_TAIGA", desc);
                                                                return;
                                                            }
                                                            case "SNOWY_TUNDRA": {
                                                                super.visitFieldInsn(opcode, owner, "SNOWY_PLAINS", desc);
                                                                return;
                                                            }
                                                            case "JUNGLE_EDGE": {
                                                                super.visitFieldInsn(opcode, owner, "SPARSE_JUNGLE", desc);
                                                                return;
                                                            }
                                                            case "STONE_SHORE": {
                                                                super.visitFieldInsn(opcode, owner, "STONY_SHORE", desc);
                                                                return;
                                                            }
                                                            case "MOUNTAINS": {
                                                                super.visitFieldInsn(opcode, owner, "WINDSWEPT_HILLS", desc);
                                                                return;
                                                            }
                                                            case "WOODED_MOUNTAINS": {
                                                                super.visitFieldInsn(opcode, owner, "WINDSWEPT_FOREST", desc);
                                                                return;
                                                            }
                                                            case "GRAVELLY_MOUNTAINS": {
                                                                super.visitFieldInsn(opcode, owner, "WINDSWEPT_GRAVELLY_HILLS", desc);
                                                                return;
                                                            }
                                                            case "SHATTERED_SAVANNA": {
                                                                super.visitFieldInsn(opcode, owner, "WINDSWEPT_SAVANNA", desc);
                                                                return;
                                                            }
                                                            case "WOODED_BADLANDS_PLATEAU": {
                                                                super.visitFieldInsn(opcode, owner, "WOODED_BADLANDS", desc);
                                                                return;
                                                            }
                                                        }
                                                    }
                                                    if (!owner.equals("org/bukkit/entity/EntityType")) break block113;
                                                    switch (name) {
                                                        case "PIG_ZOMBIE": {
                                                            super.visitFieldInsn(opcode, owner, "ZOMBIFIED_PIGLIN", desc);
                                                            return;
                                                        }
                                                    }
                                                }
                                                if (!owner.equals("org/bukkit/loot/LootTables")) break block115;
                                                switch (name) {
                                                    case "ZOMBIE_PIGMAN": {
                                                        super.visitFieldInsn(opcode, owner, "ZOMBIFIED_PIGLIN", desc);
                                                        return;
                                                    }
                                                }
                                            }
                                            if (owner.equals("org/bukkit/entity/TextDisplay$TextAligment")) {
                                                super.visitFieldInsn(opcode, "org/bukkit/entity/TextDisplay$TextAlignment", name, desc);
                                                return;
                                            }
                                            if (!modern) break block117;
                                            if (!owner.equals("org/bukkit/Material")) break block118;
                                            switch (name) {
                                                case "CACTUS_GREEN": {
                                                    name = "GREEN_DYE";
                                                    break;
                                                }
                                                case "DANDELION_YELLOW": {
                                                    name = "YELLOW_DYE";
                                                    break;
                                                }
                                                case "ROSE_RED": {
                                                    name = "RED_DYE";
                                                    break;
                                                }
                                                case "SIGN": {
                                                    name = "OAK_SIGN";
                                                    break;
                                                }
                                                case "WALL_SIGN": {
                                                    name = "OAK_WALL_SIGN";
                                                    break;
                                                }
                                                case "ZOMBIE_PIGMAN_SPAWN_EGG": {
                                                    name = "ZOMBIFIED_PIGLIN_SPAWN_EGG";
                                                    break;
                                                }
                                                case "GRASS_PATH": {
                                                    name = "DIRT_PATH";
                                                }
                                            }
                                        }
                                        super.visitFieldInsn(opcode, owner, name, desc);
                                        return;
                                    }
                                    if (owner.equals("org/bukkit/Material")) {
                                        try {
                                            Material.valueOf("LEGACY_" + name);
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw new AuthorNagException("No legacy enum constant for " + name + ". Did you forget to define a modern (1.13+) api-version in your plugin.yml?");
                                        }
                                        super.visitFieldInsn(opcode, owner, "LEGACY_" + name, desc);
                                        return;
                                    }
                                    if (!owner.equals("org/bukkit/Art")) break block120;
                                    switch (name) {
                                        case "BURNINGSKULL": {
                                            super.visitFieldInsn(opcode, owner, "BURNING_SKULL", desc);
                                            return;
                                        }
                                        case "DONKEYKONG": {
                                            super.visitFieldInsn(opcode, owner, "DONKEY_KONG", desc);
                                            return;
                                        }
                                    }
                                }
                                if (!owner.equals("org/bukkit/DyeColor")) break block122;
                                switch (name) {
                                    case "SILVER": {
                                        super.visitFieldInsn(opcode, owner, "LIGHT_GRAY", desc);
                                        return;
                                    }
                                }
                            }
                            if (!owner.equals("org/bukkit/Particle")) break block124;
                            switch (name) {
                                case "FALLING_DUST": 
                                case "BLOCK_CRACK": 
                                case "BLOCK_DUST": {
                                    super.visitFieldInsn(opcode, owner, "LEGACY_" + name, desc);
                                    return;
                                }
                            }
                        }
                        super.visitFieldInsn(opcode, owner, name, desc);
                    }

                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                        Type retType;
                        block44: {
                            block41: {
                                block42: {
                                    if (owner.equals("org/bukkit/map/MapView") && name.equals("getId") && desc.equals("()S")) {
                                        super.visitMethodInsn(opcode, owner, name, "()I", itf);
                                        return;
                                    }
                                    if ((owner.equals("org/bukkit/Bukkit") || owner.equals("org/bukkit/Server")) && name.equals("getMap") && desc.equals("(S)Lorg/bukkit/map/MapView;")) {
                                        super.visitMethodInsn(opcode, owner, name, "(I)Lorg/bukkit/map/MapView;", itf);
                                        return;
                                    }
                                    if (owner.equals("org/bukkit/entity/TextDisplay$TextAligment")) {
                                        super.visitMethodInsn(opcode, "org/bukkit/entity/TextDisplay$TextAlignment", name, desc, itf);
                                        return;
                                    }
                                    if (desc.equals("(Lorg/bukkit/entity/TextDisplay$TextAligment;)V")) {
                                        super.visitMethodInsn(opcode, owner, name, "(Lorg/bukkit/entity/TextDisplay$TextAlignment;)V", itf);
                                        return;
                                    }
                                    if (desc.equals("()Lorg/bukkit/entity/TextDisplay$TextAligment;")) {
                                        super.visitMethodInsn(opcode, owner, name, "()Lorg/bukkit/entity/TextDisplay$TextAlignment;", itf);
                                        return;
                                    }
                                    if (!modern) break block41;
                                    if (!owner.equals("org/bukkit/Material")) break block42;
                                    switch (name) {
                                        case "values": {
                                            super.visitMethodInsn(opcode, "org/bukkit/craftbukkit/v1_20_R1/util/CraftLegacy", "modern_" + name, desc, itf);
                                            return;
                                        }
                                        case "ordinal": {
                                            super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_20_R1/util/CraftLegacy", "modern_" + name, "(Lorg/bukkit/Material;)I", false);
                                            return;
                                        }
                                    }
                                }
                                super.visitMethodInsn(opcode, owner, name, desc, itf);
                                return;
                            }
                            if (owner.equals("org/bukkit/ChunkSnapshot") && name.equals("getBlockData") && desc.equals("(III)I")) {
                                super.visitMethodInsn(opcode, owner, "getData", desc, itf);
                                return;
                            }
                            retType = Type.getReturnType((String)desc);
                            if (EVIL.contains(String.valueOf(owner) + " " + desc + " " + name) || owner.startsWith("org/bukkit/block/") && (String.valueOf(desc) + " " + name).equals("()I getTypeId") || owner.startsWith("org/bukkit/block/") && (String.valueOf(desc) + " " + name).equals("(I)Z setTypeId") || owner.startsWith("org/bukkit/block/") && (String.valueOf(desc) + " " + name).equals("()Lorg/bukkit/Material; getType")) {
                                Type[] args = Type.getArgumentTypes((String)desc);
                                Type[] newArgs = new Type[args.length + 1];
                                newArgs[0] = Type.getObjectType((String)owner);
                                System.arraycopy(args, 0, newArgs, 1, args.length);
                                super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_20_R1/legacy/CraftEvil", name, Type.getMethodDescriptor((Type)retType, (Type[])newArgs), false);
                                return;
                            }
                            if (owner.equals("org/bukkit/DyeColor") && name.equals("valueOf") && desc.equals("(Ljava/lang/String;)Lorg/bukkit/DyeColor;")) {
                                super.visitMethodInsn(opcode, owner, "legacyValueOf", desc, itf);
                                return;
                            }
                            if (!owner.equals("org/bukkit/Material")) break block44;
                            if (name.equals("getMaterial") && desc.equals("(I)Lorg/bukkit/Material;")) {
                                super.visitMethodInsn(opcode, "org/bukkit/craftbukkit/v1_20_R1/legacy/CraftEvil", name, desc, itf);
                                return;
                            }
                            switch (name) {
                                case "getMaterial": 
                                case "matchMaterial": 
                                case "values": 
                                case "valueOf": {
                                    super.visitMethodInsn(opcode, "org/bukkit/craftbukkit/v1_20_R1/legacy/CraftLegacy", name, desc, itf);
                                    return;
                                }
                                case "ordinal": {
                                    super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_20_R1/legacy/CraftLegacy", "ordinal", "(Lorg/bukkit/Material;)I", false);
                                    return;
                                }
                                case "toString": 
                                case "name": {
                                    super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_20_R1/legacy/CraftLegacy", name, "(Lorg/bukkit/Material;)Ljava/lang/String;", false);
                                    return;
                                }
                            }
                        }
                        if (retType.getSort() == 10 && retType.getInternalName().equals("org/bukkit/Material") && owner.startsWith("org/bukkit")) {
                            super.visitMethodInsn(opcode, owner, name, desc, itf);
                            super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_20_R1/legacy/CraftLegacy", "toLegacy", "(Lorg/bukkit/Material;)Lorg/bukkit/Material;", false);
                            return;
                        }
                        super.visitMethodInsn(opcode, owner, name, desc, itf);
                    }

                    public void visitLdcInsn(Object value) {
                        if (value instanceof String && ((String)value).equals("com.mysql.jdbc.Driver")) {
                            super.visitLdcInsn((Object)"com.mysql.cj.jdbc.Driver");
                            return;
                        }
                        super.visitLdcInsn(value);
                    }
                };
            }
        }, 0);
        return cw.toByteArray();
    }
}

