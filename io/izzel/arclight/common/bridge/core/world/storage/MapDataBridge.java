/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData$HoldingPlayer
 */
package io.izzel.arclight.common.bridge.core.world.storage;

import java.util.List;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapView;

public interface MapDataBridge {
    public CraftMapView bridge$getMapView();

    public void bridge$setId(String var1);

    public List<MapItemSavedData.HoldingPlayer> bridge$getCarriedBy();
}

