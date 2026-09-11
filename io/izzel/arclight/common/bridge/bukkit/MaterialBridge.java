/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec$MaterialType
 *  javax.annotation.Nullable
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 */
package io.izzel.arclight.common.bridge.bukkit;

import io.izzel.arclight.i18n.conf.MaterialPropertySpec;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.meta.ItemMeta;

public interface MaterialBridge {
    public void bridge$setupBlock(ResourceLocation var1, Block var2, MaterialPropertySpec var3);

    public void bridge$setupVanillaBlock(MaterialPropertySpec var1);

    public void bridge$setupItem(ResourceLocation var1, Item var2, MaterialPropertySpec var3);

    public void bridge$setBlock();

    public void bridge$setItem();

    @Nullable
    public MaterialPropertySpec bridge$getSpec();

    public MaterialPropertySpec.MaterialType bridge$getType();

    public Function<CraftMetaItem, ItemMeta> bridge$itemMetaFactory();

    public void bridge$setItemMetaFactory(Function<CraftMetaItem, ItemMeta> var1);

    public Function<CraftBlock, BlockState> bridge$blockStateFactory();

    public void bridge$setBlockStateFactory(Function<CraftBlock, BlockState> var1);

    public boolean bridge$shouldApplyStateFactory();
}

