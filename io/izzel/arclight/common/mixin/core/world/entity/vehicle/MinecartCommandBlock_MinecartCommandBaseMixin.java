/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.world.entity.vehicle.MinecartCommandBlock
 *  net.minecraft.world.entity.vehicle.MinecartCommandBlock$MinecartCommandBase
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.vehicle;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import org.bukkit.command.CommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={MinecartCommandBlock.MinecartCommandBase.class})
public abstract class MinecartCommandBlock_MinecartCommandBaseMixin
implements ICommandSourceBridge {
    @Shadow(aliases={"this$0", "f_38537_"}, remap=false)
    private MinecartCommandBlock outerThis;

    public CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return ((EntityBridge)this.outerThis).bridge$getBukkitEntity();
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.getBukkitSender(wrapper);
    }
}

