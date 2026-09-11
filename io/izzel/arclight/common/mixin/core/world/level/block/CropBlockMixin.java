/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.CropBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CropBlock.class})
public class CropBlockMixin {
    @Redirect(method={"growCrops(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    public boolean arclight$blockGrowGrow(Level world, BlockPos pos, BlockState newState, int flags) {
        return CraftEventFactory.handleBlockGrowEvent(world, pos, newState, flags);
    }

    @Redirect(method={"entityInside"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z"))
    public boolean arclight$entityChangeBlock(Level world, Entity entity, BlockState state, Level worldIn, BlockPos pos) {
        boolean result = ForgeEventFactory.getMobGriefingEvent((Level)world, (Entity)entity);
        return !CraftEventFactory.callEntityChangeBlockEvent(entity, pos, state, result);
    }

    @Redirect(method={"randomTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    public boolean arclight$blockGrowTick(ServerLevel world, BlockPos pos, BlockState newState, int flags) {
        return CraftEventFactory.handleBlockGrowEvent((Level)world, pos, newState, flags);
    }

    @Inject(method={"getGrowthSpeed"}, cancellable=true, at={@At(value="RETURN")})
    private static void arclight$spigotModifier(Block block, BlockGetter blockGetter, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (blockGetter instanceof WorldBridge) {
            WorldBridge bridge = (WorldBridge)blockGetter;
            int modifier = block == Blocks.f_50444_ ? bridge.bridge$spigotConfig().beetrootModifier : (block == Blocks.f_50249_ ? bridge.bridge$spigotConfig().carrotModifier : (block == Blocks.f_50250_ ? bridge.bridge$spigotConfig().potatoModifier : bridge.bridge$spigotConfig().wheatModifier));
            float f = cir.getReturnValueF();
            cir.setReturnValue((Object)Float.valueOf(f /= 100.0f / (float)modifier));
        }
    }
}

