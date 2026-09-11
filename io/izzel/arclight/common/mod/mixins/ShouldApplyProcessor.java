/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.mixins;

import io.izzel.arclight.common.mod.mixins.LoadIfModProcessor;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

public class ShouldApplyProcessor {
    private static final List<Predicate<ClassNode>> PREDICATES = List.of(LoadIfModProcessor::shouldApply);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean shouldApply(String mixinClass) {
        try {
            Predicate<ClassNode> predicate;
            InputStream stream;
            block12: {
                stream = LoadIfModProcessor.class.getClassLoader().getResourceAsStream(mixinClass.replace('.', '/') + ".class");
                if (stream == null) {
                    System.out.println(mixinClass);
                    boolean bl = true;
                    return bl;
                }
                break block12;
                finally {
                    if (stream != null) {
                        stream.close();
                    }
                }
            }
            byte[] bytes = stream.readAllBytes();
            ClassReader cr = new ClassReader(bytes);
            ClassNode node = new ClassNode();
            cr.accept((ClassVisitor)node, 1);
            Iterator<Predicate<ClassNode>> iterator = PREDICATES.iterator();
            do {
                if (iterator.hasNext()) continue;
                boolean bl = true;
                return bl;
            } while ((predicate = iterator.next()).test(node));
            boolean bl = false;
            return bl;
        }
        catch (IOException e) {
            return true;
        }
    }
}

