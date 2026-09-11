/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.LecternMenu
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.LecternContainerBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import javax.annotation.Nullable;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.LecternMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftBlockCommandSender;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={LecternBlockEntity.class})
public abstract class LecternBlockEntityMixin
extends BlockEntityMixin
implements CommandSource,
ICommandSourceBridge {
    @Shadow
    @Final
    public Container f_59525_;
    @Shadow
    @Final
    private ContainerData f_59526_;

    @Redirect(method={"createCommandSourceStack"}, at=@At(value="NEW", target="net/minecraft/commands/CommandSourceStack"))
    private CommandSourceStack arclight$source(CommandSource source, Vec3 vec3d, Vec2 vec2f, ServerLevel world, int i, String s, Component component, MinecraftServer server, @Nullable Entity entity) {
        return new CommandSourceStack((CommandSource)this, vec3d, vec2f, world, i, s, component, server, entity);
    }

    @Overwrite
    public AbstractContainerMenu m_7208_(int i, Inventory playerInventory, Player entity) {
        LecternMenu container = new LecternMenu(i, this.f_59525_, this.f_59526_);
        ((LecternContainerBridge)container).bridge$setPlayerInventory(playerInventory);
        return container;
    }

    public void m_213846_(@NotNull Component component) {
    }

    public boolean m_6999_() {
        return false;
    }

    public boolean m_7028_() {
        return false;
    }

    public boolean m_6102_() {
        return false;
    }

    public CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return wrapper.m_81373_() != null ? ((EntityBridge)wrapper.m_81373_()).bridge$getBukkitSender(wrapper) : new CraftBlockCommandSender(wrapper, (BlockEntity)this);
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.getBukkitSender(wrapper);
    }
}

