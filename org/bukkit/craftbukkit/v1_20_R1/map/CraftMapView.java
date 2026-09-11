/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 */
package org.bukkit.craftbukkit.v1_20_R1.map;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapCanvas;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapRenderer;
import org.bukkit.craftbukkit.v1_20_R1.map.RenderData;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public final class CraftMapView
implements MapView {
    private final Map<CraftPlayer, RenderData> renderCache = new HashMap<CraftPlayer, RenderData>();
    private final List<MapRenderer> renderers = new ArrayList<MapRenderer>();
    private final Map<MapRenderer, Map<CraftPlayer, CraftMapCanvas>> canvases = new HashMap<MapRenderer, Map<CraftPlayer, CraftMapCanvas>>();
    protected final MapItemSavedData worldMap;

    public CraftMapView(MapItemSavedData worldMap) {
        this.worldMap = worldMap;
        this.addRenderer(new CraftMapRenderer(this, worldMap));
    }

    @Override
    public int getId() {
        String text = this.worldMap.id;
        Preconditions.checkState((boolean)text.startsWith("map_"), (Object)"Map has a invalid ID");
        try {
            return Integer.parseInt(text.substring("map_".length()));
        }
        catch (NumberFormatException ex) {
            throw new IllegalStateException("Map has non-numeric ID");
        }
    }

    @Override
    public boolean isVirtual() {
        return this.renderers.size() > 0 && !(this.renderers.get(0) instanceof CraftMapRenderer);
    }

    @Override
    public MapView.Scale getScale() {
        return MapView.Scale.valueOf(this.worldMap.f_77890_);
    }

    @Override
    public void setScale(MapView.Scale scale) {
        this.worldMap.f_77890_ = scale.getValue();
    }

    @Override
    public World getWorld() {
        ResourceKey dimension = this.worldMap.f_77887_;
        ServerLevel world = MinecraftServer.getServer().m_129880_(dimension);
        if (world != null) {
            return world.getWorld();
        }
        if (this.worldMap.uniqueId != null) {
            return Bukkit.getServer().getWorld(this.worldMap.uniqueId);
        }
        return null;
    }

    @Override
    public void setWorld(World world) {
        this.worldMap.f_77887_ = ((CraftWorld)world).getHandle().m_46472_();
        this.worldMap.uniqueId = world.getUID();
    }

    @Override
    public int getCenterX() {
        return this.worldMap.f_256718_;
    }

    @Override
    public int getCenterZ() {
        return this.worldMap.f_256789_;
    }

    @Override
    public void setCenterX(int x) {
        this.worldMap.f_256718_ = x;
    }

    @Override
    public void setCenterZ(int z) {
        this.worldMap.f_256789_ = z;
    }

    @Override
    public List<MapRenderer> getRenderers() {
        return new ArrayList<MapRenderer>(this.renderers);
    }

    @Override
    public void addRenderer(MapRenderer renderer) {
        if (!this.renderers.contains(renderer)) {
            this.renderers.add(renderer);
            this.canvases.put(renderer, new HashMap());
            renderer.initialize(this);
        }
    }

    @Override
    public boolean removeRenderer(MapRenderer renderer) {
        if (this.renderers.contains(renderer)) {
            this.renderers.remove(renderer);
            for (Map.Entry<CraftPlayer, CraftMapCanvas> entry : this.canvases.get(renderer).entrySet()) {
                int x = 0;
                while (x < 128) {
                    int y = 0;
                    while (y < 128) {
                        entry.getValue().setPixel(x, y, (byte)-1);
                        ++y;
                    }
                    ++x;
                }
            }
            this.canvases.remove(renderer);
            return true;
        }
        return false;
    }

    private boolean isContextual() {
        for (MapRenderer renderer : this.renderers) {
            if (!renderer.isContextual()) continue;
            return true;
        }
        return false;
    }

    public RenderData render(CraftPlayer player) {
        boolean context = this.isContextual();
        RenderData render = this.renderCache.get(context ? player : null);
        if (render == null) {
            render = new RenderData();
            this.renderCache.put(context ? player : null, render);
        }
        if (context && this.renderCache.containsKey(null)) {
            this.renderCache.remove(null);
        }
        Arrays.fill(render.buffer, (byte)0);
        render.cursors.clear();
        Iterator<MapRenderer> iterator = this.renderers.iterator();
        while (iterator.hasNext()) {
            MapRenderer renderer;
            CraftMapCanvas canvas = this.canvases.get(renderer).get((renderer = iterator.next()).isContextual() ? player : null);
            if (canvas == null) {
                canvas = new CraftMapCanvas(this);
                this.canvases.get(renderer).put(renderer.isContextual() ? player : null, canvas);
            }
            canvas.setBase(render.buffer);
            try {
                renderer.render(this, canvas, player);
            }
            catch (Throwable ex) {
                Bukkit.getLogger().log(Level.SEVERE, "Could not render map using renderer " + renderer.getClass().getName(), ex);
            }
            byte[] buf = canvas.getBuffer();
            int i = 0;
            while (i < buf.length) {
                byte color = buf[i];
                if (color >= 0 || color <= -9) {
                    render.buffer[i] = color;
                }
                ++i;
            }
            i = 0;
            while (i < canvas.getCursors().size()) {
                render.cursors.add(canvas.getCursors().getCursor(i));
                ++i;
            }
        }
        return render;
    }

    @Override
    public boolean isTrackingPosition() {
        return this.worldMap.f_77888_;
    }

    @Override
    public void setTrackingPosition(boolean trackingPosition) {
        this.worldMap.f_77888_ = trackingPosition;
    }

    @Override
    public boolean isUnlimitedTracking() {
        return this.worldMap.f_77889_;
    }

    @Override
    public void setUnlimitedTracking(boolean unlimited) {
        this.worldMap.f_77889_ = unlimited;
    }

    @Override
    public boolean isLocked() {
        return this.worldMap.f_77892_;
    }

    @Override
    public void setLocked(boolean locked) {
        this.worldMap.f_77892_ = locked;
    }
}

