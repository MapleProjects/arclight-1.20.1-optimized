/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.tree.CommandNode
 *  io.izzel.arclight.api.Unsafe
 */
package io.izzel.arclight.common.mod.compat;

import com.mojang.brigadier.tree.CommandNode;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.bridge.core.command.CommandSourceBridge;
import java.lang.reflect.Field;
import java.util.Map;

public class CommandNodeHooks {
    private static final long CHILDREN;
    private static final long LITERALS;
    private static final long ARGUMENTS;
    private static final long CURRENT;
    private static final Object CURRENT_BASE;

    public static void removeCommand(CommandNode<?> node, String command) {
        ((Map)Unsafe.getObject(node, (long)CHILDREN)).remove(command);
        ((Map)Unsafe.getObject(node, (long)LITERALS)).remove(command);
        ((Map)Unsafe.getObject(node, (long)ARGUMENTS)).remove(command);
    }

    public static CommandNode<?> getCurrent() {
        return (CommandNode)Unsafe.getObjectVolatile((Object)CURRENT_BASE, (long)CURRENT);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static <S> boolean canUse(CommandNode<S> node, S source) {
        if (source instanceof CommandSourceBridge) {
            CommandSourceBridge s = (CommandSourceBridge)source;
            try {
                s.bridge$setCurrentCommand(node);
                boolean bl = node.canUse(source);
                return bl;
            }
            finally {
                s.bridge$setCurrentCommand(null);
            }
        }
        return node.canUse(source);
    }

    static {
        try {
            CHILDREN = Unsafe.objectFieldOffset((Field)CommandNode.class.getDeclaredField("children"));
            LITERALS = Unsafe.objectFieldOffset((Field)CommandNode.class.getDeclaredField("literals"));
            ARGUMENTS = Unsafe.objectFieldOffset((Field)CommandNode.class.getDeclaredField("arguments"));
            CURRENT_BASE = Unsafe.staticFieldBase((Field)CommandNode.class.getDeclaredField("CURRENT_COMMAND"));
            CURRENT = Unsafe.staticFieldOffset((Field)CommandNode.class.getDeclaredField("CURRENT_COMMAND"));
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}

