/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SupportType
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.SupportType;
import org.bukkit.block.BlockSupport;

public final class CraftBlockSupport {
    private CraftBlockSupport() {
    }

    public static BlockSupport toBukkit(SupportType support) {
        return switch (support) {
            case SupportType.FULL -> BlockSupport.FULL;
            case SupportType.CENTER -> BlockSupport.CENTER;
            case SupportType.RIGID -> BlockSupport.RIGID;
            default -> throw new IllegalArgumentException("Unsupported EnumBlockSupport type: " + support + ". This is a bug.");
        };
    }

    public static SupportType toNMS(BlockSupport support) {
        return switch (support) {
            case BlockSupport.FULL -> SupportType.FULL;
            case BlockSupport.CENTER -> SupportType.CENTER;
            case BlockSupport.RIGID -> SupportType.RIGID;
            default -> throw new IllegalArgumentException("Unsupported BlockSupport type: " + (Object)((Object)support) + ". This is a bug.");
        };
    }
}

