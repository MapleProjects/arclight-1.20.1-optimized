/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.saveddata.maps.MapDecoration
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 */
package org.bukkit.craftbukkit.v1_20_R1.map;

import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapView;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class CraftMapRenderer
extends MapRenderer {
    private final MapItemSavedData worldMap;

    public CraftMapRenderer(CraftMapView mapView, MapItemSavedData worldMap) {
        super(false);
        this.worldMap = worldMap;
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        int x = 0;
        while (x < 128) {
            int y = 0;
            while (y < 128) {
                canvas.setPixel(x, y, this.worldMap.f_77891_[y * 128 + x]);
                ++y;
            }
            ++x;
        }
        MapCursorCollection cursors = canvas.getCursors();
        while (cursors.size() > 0) {
            cursors.removeCursor(cursors.getCursor(0));
        }
        for (String key : this.worldMap.f_77894_.keySet()) {
            Player other = Bukkit.getPlayerExact(key);
            if (other != null && !player.canSee(other)) continue;
            MapDecoration decoration = (MapDecoration)this.worldMap.f_77894_.get(key);
            cursors.addCursor(decoration.m_77804_(), decoration.m_77805_(), (byte)(decoration.m_77806_() & 0xF), decoration.m_77803_().m_77853_(), true, CraftChatMessage.fromComponent(decoration.m_77810_()));
        }
    }
}

