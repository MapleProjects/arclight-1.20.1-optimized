/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Axis;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.potion.Potion;

public class CraftEffect {
    public static <T> int getDataValue(Effect effect, T data) {
        return switch (effect) {
            case Effect.VILLAGER_PLANT_GROW -> (Integer)data;
            case Effect.POTION_BREAK -> ((Potion)data).toDamageValue() & 0x3F;
            case Effect.INSTANT_POTION_BREAK -> ((Color)data).asRGB();
            case Effect.RECORD_PLAY -> {
                Preconditions.checkArgument((data == Material.AIR || ((Material)data).isRecord() ? 1 : 0) != 0, (String)"Invalid record type for Material %s!", data);
                yield Item.m_41393_((Item)CraftMagicNumbers.getItem((Material)data));
            }
            case Effect.SMOKE -> {
                switch ((BlockFace)((Object)data)) {
                    case DOWN: 
                    case NORTH_EAST: 
                    case NORTH_WEST: 
                    case SOUTH_EAST: 
                    case SOUTH_WEST: 
                    case SELF: {
                        yield 0;
                    }
                    case UP: {
                        yield 1;
                    }
                    case NORTH: {
                        yield 2;
                    }
                    case SOUTH: {
                        yield 3;
                    }
                    case WEST: {
                        yield 4;
                    }
                    case EAST: {
                        yield 5;
                    }
                }
                throw new IllegalArgumentException("Bad smoke direction!");
            }
            case Effect.STEP_SOUND -> {
                Preconditions.checkArgument((boolean)((Material)data).isBlock(), (String)"Material %s is not a block!", data);
                yield Block.m_49956_((BlockState)CraftMagicNumbers.getBlock((Material)data).m_49966_());
            }
            case Effect.COMPOSTER_FILL_ATTEMPT -> (Boolean)data != false ? 1 : 0;
            case Effect.BONE_MEAL_USE -> (Integer)data;
            case Effect.ELECTRIC_SPARK -> {
                if (data == null) {
                    yield -1;
                }
                switch ((Axis)((Object)data)) {
                    case X: {
                        yield 0;
                    }
                    case Y: {
                        yield 1;
                    }
                    case Z: {
                        yield 2;
                    }
                }
                throw new IllegalArgumentException("Bad electric spark axis!");
            }
            default -> 0;
        };
    }
}

