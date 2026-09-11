/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.common.bridge.core.network.play;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerTeleportEvent;

public interface ServerPlayNetHandlerBridge {
    public void bridge$pushTeleportCause(PlayerTeleportEvent.TeleportCause var1);

    public void bridge$disconnect(String var1);

    public void bridge$teleport(Location var1);

    public boolean bridge$processedDisconnect();

    public boolean bridge$isDisconnected();
}

