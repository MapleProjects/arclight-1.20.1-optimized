/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.FilteredText
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.entity.SignText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.command.CommandSourceBridge;
import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.tileentity.SignTileEntityBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.UnaryOperator;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.FilteredText;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import org.bukkit.Bukkit;
import org.bukkit.block.sign.Side;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftBlockCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.event.block.SignChangeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SignBlockEntity.class})
public abstract class SignBlockEntityMixin
extends BlockEntityMixin
implements SignTileEntityBridge,
CommandSource,
ICommandSourceBridge {
    @Shadow
    @Final
    private static Logger f_276608_;

    @Shadow
    public abstract ClientboundBlockEntityDataPacket m_58483_();

    @Shadow
    private static CommandSourceStack m_278808_(@Nullable Player p_279428_, Level p_279359_, BlockPos p_279430_) {
        return null;
    }

    @Shadow
    public abstract boolean m_277118_();

    @Shadow
    @javax.annotation.Nullable
    public abstract UUID m_155726_();

    @Shadow
    public abstract boolean m_277073_(UnaryOperator<SignText> var1, boolean var2);

    @Shadow
    public abstract void m_155713_(@Nullable UUID var1);

    @Overwrite
    public void m_277134_(Player p_278048_, boolean p_278103_, List<FilteredText> p_277990_) {
        if (!this.m_277118_() && p_278048_.m_20148_().equals(this.m_155726_()) && this.f_58857_ != null) {
            this.m_277073_(p_277776_ -> this.setMessages(p_278048_, p_277990_, (SignText)p_277776_, p_278103_), p_278103_);
            this.m_155713_(null);
            this.f_58857_.m_7260_(this.m_58899_(), this.m_58900_(), this.m_58900_(), 3);
        } else {
            f_276608_.warn("Player {} just tried to change non-editable sign", (Object)p_278048_.m_7755_().getString());
            ((ServerPlayer)p_278048_).f_8906_.m_9829_((Packet)this.m_58483_());
        }
    }

    private SignText setMessages(Player entityhuman, List<FilteredText> list, SignText signtext, boolean front) {
        SignText orig = signtext;
        for (int i = 0; i < list.size(); ++i) {
            FilteredText filteredtext = list.get(i);
            Style chatmodifier = signtext.m_277138_(i, entityhuman.m_143387_()).m_7383_();
            signtext = entityhuman.m_143387_() ? signtext.m_276913_(i, (Component)Component.m_237113_((String)filteredtext.m_243113_()).m_6270_(chatmodifier)) : signtext.m_276948_(i, (Component)Component.m_237113_((String)filteredtext.f_215168_()).m_6270_(chatmodifier), (Component)Component.m_237113_((String)filteredtext.m_243113_()).m_6270_(chatmodifier));
        }
        CraftPlayer player = ((ServerPlayerEntityBridge)entityhuman).bridge$getBukkitEntity();
        String[] lines = new String[4];
        for (int j = 0; j < list.size(); ++j) {
            lines[j] = CraftChatMessage.fromComponent(signtext.m_277138_(j, entityhuman.m_143387_()));
        }
        SignChangeEvent event = new SignChangeEvent(CraftBlock.at((LevelAccessor)this.f_58857_, this.f_58858_), player, Arrays.copyOf(lines, lines.length), front ? Side.FRONT : Side.BACK);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return orig;
        }
        Component[] components = CraftSign.sanitizeLines(event.getLines());
        for (int j = 0; j < components.length; ++j) {
            if (Objects.equals(lines[j], event.getLine(j))) continue;
            signtext = signtext.m_276913_(j, components[j]);
        }
        return signtext;
    }

    @Redirect(method={"executeClickCommandsIfPresent"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/SignBlockEntity;createCommandSourceStack(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/commands/CommandSourceStack;"))
    private CommandSourceStack arclight$setSource(Player p_279428_, Level p_279359_, BlockPos p_279430_) {
        CommandSourceStack stack = SignBlockEntityMixin.m_278808_(p_279428_, p_279359_, p_279430_);
        ((CommandSourceBridge)stack).bridge$setSource(this);
        return stack;
    }

    @Inject(method={"markUpdated"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;sendBlockUpdated(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V")})
    public void arclight$setColor(CallbackInfo ci) {
        if (this.f_58857_ == null) {
            ci.cancel();
        }
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

