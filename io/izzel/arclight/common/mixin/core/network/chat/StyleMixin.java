/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.ClickEvent
 *  net.minecraft.network.chat.HoverEvent
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.chat.TextColor
 *  net.minecraft.resources.ResourceLocation
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network.chat;

import javax.annotation.Nullable;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Style.class})
public class StyleMixin {
    @Shadow
    @Final
    @Nullable
    private TextColor f_131101_;
    @Shadow
    @Final
    @Nullable
    private Boolean f_131102_;
    @Shadow
    @Final
    @Nullable
    private Boolean f_131103_;
    @Shadow
    @Final
    @Nullable
    private Boolean f_131104_;
    @Shadow
    @Final
    @Nullable
    private Boolean f_131105_;
    @Shadow
    @Final
    @Nullable
    private Boolean f_131106_;
    @Shadow
    @Final
    @Nullable
    private ClickEvent f_131107_;
    @Shadow
    @Final
    @Nullable
    private HoverEvent f_131108_;
    @Shadow
    @Final
    @Nullable
    private String f_131109_;
    @Shadow
    @Final
    @Nullable
    private ResourceLocation f_131110_;

    public Style setStrikethrough(Boolean b) {
        return new Style(this.f_131101_, this.f_131102_, this.f_131103_, this.f_131104_, b, this.f_131106_, this.f_131107_, this.f_131108_, this.f_131109_, this.f_131110_);
    }

    public Style setUnderline(Boolean b) {
        return new Style(this.f_131101_, this.f_131102_, this.f_131103_, b, this.f_131105_, this.f_131106_, this.f_131107_, this.f_131108_, this.f_131109_, this.f_131110_);
    }

    public Style setRandom(Boolean b) {
        return new Style(this.f_131101_, this.f_131102_, this.f_131103_, this.f_131104_, this.f_131105_, b, this.f_131107_, this.f_131108_, this.f_131109_, this.f_131110_);
    }
}

