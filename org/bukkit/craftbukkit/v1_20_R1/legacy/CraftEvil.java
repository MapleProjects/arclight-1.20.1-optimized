/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 */
package org.bukkit.craftbukkit.v1_20_R1.legacy;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.legacy.CraftLegacy;
import org.bukkit.inventory.ItemStack;

@Deprecated
public final class CraftEvil {
    private static final Int2ObjectMap<Material> byId = new Int2ObjectLinkedOpenHashMap();

    static {
        Material[] materialArray = Material.values();
        int n = materialArray.length;
        int n2 = 0;
        while (n2 < n) {
            Material material = materialArray[n2];
            if (material.isLegacy()) {
                Preconditions.checkState((!byId.containsKey(material.getId()) ? 1 : 0) != 0, (String)"Duplicate material ID for", (Object)material);
                byId.put(material.getId(), (Object)material);
            }
            ++n2;
        }
    }

    private CraftEvil() {
    }

    public static int getBlockTypeIdAt(World world, int x, int y, int z) {
        return CraftEvil.getId(world.getBlockAt(x, y, z).getType());
    }

    public static int getBlockTypeIdAt(World world, Location location) {
        return CraftEvil.getId(world.getBlockAt(location).getType());
    }

    public static Material getType(Block block) {
        return CraftLegacy.toLegacyMaterial(((CraftBlock)block).getNMS());
    }

    public static Material getType(BlockState block) {
        return CraftLegacy.toLegacyMaterial(((CraftBlockState)block).getHandle());
    }

    public static int getTypeId(Block block) {
        return CraftEvil.getId(block.getType());
    }

    public static boolean setTypeId(Block block, int type) {
        block.setType(CraftEvil.getMaterial(type));
        return true;
    }

    public static boolean setTypeId(Block block, int type, boolean applyPhysics) {
        block.setType(CraftEvil.getMaterial(type), applyPhysics);
        return true;
    }

    public static boolean setTypeIdAndData(Block block, int type, byte data, boolean applyPhysics) {
        block.setType(CraftEvil.getMaterial(type), applyPhysics);
        CraftEvil.setData(block, data);
        return true;
    }

    public static void setData(Block block, byte data) {
        ((CraftBlock)block).setData(data);
    }

    public static void setData(Block block, byte data, boolean applyPhysics) {
        ((CraftBlock)block).setData(data, applyPhysics);
    }

    public static int getTypeId(BlockState state) {
        return CraftEvil.getId(state.getType());
    }

    public static boolean setTypeId(BlockState state, int type) {
        state.setType(CraftEvil.getMaterial(type));
        return true;
    }

    public static int getTypeId(ItemStack stack) {
        return CraftEvil.getId(stack.getType());
    }

    public static void setTypeId(ItemStack stack, int type) {
        stack.setType(CraftEvil.getMaterial(type));
    }

    public static Material getMaterial(int id) {
        return (Material)byId.get(id);
    }

    public static int getId(Material material) {
        return CraftLegacy.toLegacy(material).getId();
    }
}

