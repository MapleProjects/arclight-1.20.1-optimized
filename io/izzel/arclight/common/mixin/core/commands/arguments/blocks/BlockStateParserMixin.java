/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  net.minecraft.commands.arguments.blocks.BlockStateParser
 *  net.minecraft.core.HolderLookup
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.commands.arguments.blocks;

import com.mojang.brigadier.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BlockStateParser.class})
public class BlockStateParserMixin {
    @Shadow
    @Final
    @Mutable
    private Map<Property<?>, Comparable<?>> f_116751_;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(HolderLookup<Block> registry, StringReader readerIn, boolean forTesting, boolean allowTags, CallbackInfo ci) {
        this.f_116751_ = new LinkedHashMap(this.f_116751_);
    }
}

