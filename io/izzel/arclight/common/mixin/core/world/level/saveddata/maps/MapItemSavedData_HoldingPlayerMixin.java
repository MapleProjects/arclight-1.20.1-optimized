/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundMapItemDataPacket
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.saveddata.maps.MapDecoration
 *  net.minecraft.world.level.saveddata.maps.MapDecoration$Type
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData$HoldingPlayer
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData$MapPatch
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.saveddata.maps;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.storage.MapDataBridge;
import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.bukkit.craftbukkit.v1_20_R1.map.RenderData;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.map.MapCursor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={MapItemSavedData.HoldingPlayer.class})
public abstract class MapItemSavedData_HoldingPlayerMixin {
    @Shadow(aliases={"this$0", "f_77961_"}, remap=false)
    private MapItemSavedData outerThis;
    @Shadow
    private boolean f_77962_;
    @Shadow
    private int f_77963_;
    @Shadow
    private int f_77964_;
    @Shadow
    private int f_77965_;
    @Shadow
    private int f_77966_;
    @Shadow
    private int f_77967_;
    @Shadow
    @Final
    public Player f_77959_;
    @Shadow
    private boolean f_164813_;

    @Shadow
    protected abstract MapItemSavedData.MapPatch m_164814_();

    @Overwrite
    @Nullable
    public Packet<?> m_164815_(int i) {
        ArrayList<MapDecoration> icons;
        MapItemSavedData.MapPatch patch;
        RenderData render = ((MapDataBridge)this.outerThis).bridge$getMapView().render(((ServerPlayerEntityBridge)this.f_77959_).bridge$getBukkitEntity());
        if (this.f_77962_) {
            this.f_77962_ = false;
            byte[] colors = this.outerThis.f_77891_;
            this.outerThis.f_77891_ = render.buffer;
            patch = this.m_164814_();
            this.outerThis.f_77891_ = colors;
        } else {
            patch = null;
        }
        if (this.f_77967_++ % 5 == 0) {
            this.f_164813_ = false;
            icons = new ArrayList<MapDecoration>();
            for (MapCursor cursor : render.cursors) {
                if (!cursor.isVisible()) continue;
                icons.add(new MapDecoration(MapDecoration.Type.m_77854_((byte)cursor.getRawType()), cursor.getX(), cursor.getY(), cursor.getDirection(), CraftChatMessage.fromStringOrNull(cursor.getCaption())));
            }
        } else {
            icons = null;
        }
        return icons == null && patch == null ? null : new ClientboundMapItemDataPacket(i, this.outerThis.f_77890_, this.outerThis.f_77892_, icons, patch);
    }
}

