/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.StandingAndWallBlockItem
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={StandingAndWallBlockItem.class})
public class StandingAndWallBlockItemMixin {
    @Inject(method={"getPlacementState"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")})
    private void arclight$blockCanPlace(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir, BlockState place, BlockState defaultReturn) {
        if (defaultReturn != null) {
            CraftPlayer craftPlayer;
            boolean result = cir.getReturnValue() != null;
            Player player = context.m_43723_();
            if (player instanceof ServerPlayerEntityBridge) {
                ServerPlayerEntityBridge bridge = (ServerPlayerEntityBridge)player;
                craftPlayer = bridge.bridge$getBukkitEntity();
            } else {
                craftPlayer = null;
            }
            CraftPlayer player2 = craftPlayer;
            BlockCanBuildEvent event = new BlockCanBuildEvent(CraftBlock.at((LevelAccessor)context.m_43725_(), context.m_8083_()), player2, CraftBlockData.fromData(defaultReturn), result);
            Bukkit.getPluginManager().callEvent(event);
            cir.setReturnValue(event.isBuildable() ? defaultReturn : null);
        }
    }
}

