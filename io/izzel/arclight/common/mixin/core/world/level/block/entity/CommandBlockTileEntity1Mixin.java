/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.CommandBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftBlockCommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net/minecraft/world/level/block/entity/CommandBlockEntity$1"})
public class CommandBlockTileEntity1Mixin
implements ICommandSourceBridge {
    @Shadow(aliases={"this$0", "f_59153_"}, remap=false)
    private CommandBlockEntity outerThis;

    public CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return new CraftBlockCommandSender(wrapper, (BlockEntity)this.outerThis);
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.getBukkitSender(wrapper);
    }
}

