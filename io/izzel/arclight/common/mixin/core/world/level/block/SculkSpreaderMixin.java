/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.SculkSpreader
 *  net.minecraft.world.level.block.SculkSpreader$ChargeCursor
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.block.SculkSpreaderBridge;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SculkSpreader;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.SculkBloomEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SculkSpreader.class})
public abstract class SculkSpreaderMixin
implements SculkSpreaderBridge {
    private transient Level arclight$level;

    @Shadow
    public abstract boolean m_222282_();

    @Override
    public void bridge$setLevel(Level level) {
        this.arclight$level = level;
    }

    @Inject(method={"addCursor"}, cancellable=true, at={@At(value="INVOKE", remap=false, target="Ljava/util/List;add(Ljava/lang/Object;)Z")})
    private void arclight$bloomEvent(SculkSpreader.ChargeCursor cursor, CallbackInfo ci) {
        if (!this.m_222282_() && this.arclight$level != null) {
            CraftBlock bukkitBlock = CraftBlock.at((LevelAccessor)this.arclight$level, cursor.f_222288_);
            SculkBloomEvent event = new SculkBloomEvent(bukkitBlock, cursor.m_222341_());
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
            cursor.f_222289_ = event.getCharge();
        }
    }
}

