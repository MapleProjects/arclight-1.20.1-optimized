/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.TextColor
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.network.chat;

import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TextColor.class})
public class TextColorMixin {
    @Shadow
    @Final
    @Mutable
    @Nullable
    public String f_131258_;
    public ChatFormatting format;

    public void arclight$constructor(int color) {
        throw new RuntimeException();
    }

    public void arclight$constructor(int color, String name, ChatFormatting textFormatting) {
        this.arclight$constructor(color);
        this.f_131258_ = name;
        this.format = textFormatting;
    }

    @Inject(method={"<init>(ILjava/lang/String;)V"}, at={@At(value="RETURN")})
    private void arclight$withFormat(int color, String name, CallbackInfo ci) {
        this.format = ChatFormatting.m_126657_((String)name);
    }
}

