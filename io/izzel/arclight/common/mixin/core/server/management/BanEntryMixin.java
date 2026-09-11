/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.players.BanListEntry
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.server.management;

import io.izzel.arclight.common.bridge.core.server.management.BanEntryBridge;
import java.util.Date;
import net.minecraft.server.players.BanListEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={BanListEntry.class})
public class BanEntryMixin
implements BanEntryBridge {
    @Shadow
    @Final
    protected Date f_10944_;

    public Date getCreated() {
        return this.f_10944_;
    }

    @Override
    public Date bridge$getCreated() {
        return this.getCreated();
    }
}

