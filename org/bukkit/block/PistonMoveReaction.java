/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public enum PistonMoveReaction {
    MOVE(0),
    BREAK(1),
    BLOCK(2),
    IGNORE(3),
    PUSH_ONLY(4);

    private int id;
    private static Map<Integer, PistonMoveReaction> byId;

    static {
        byId = new HashMap<Integer, PistonMoveReaction>();
        PistonMoveReaction[] pistonMoveReactionArray = PistonMoveReaction.values();
        int n = pistonMoveReactionArray.length;
        int n2 = 0;
        while (n2 < n) {
            PistonMoveReaction reaction = pistonMoveReactionArray[n2];
            byId.put(reaction.id, reaction);
            ++n2;
        }
    }

    private PistonMoveReaction(int id) {
        this.id = id;
    }

    @Deprecated
    public int getId() {
        return this.id;
    }

    @Deprecated
    @Nullable
    public static PistonMoveReaction getById(int id) {
        return byId.get(id);
    }
}

