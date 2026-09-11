/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.properties.Property
 */
package io.izzel.arclight.common.bridge.core.network;

import com.mojang.authlib.properties.Property;
import java.util.UUID;

public interface NetworkManagerBridge {
    public UUID bridge$getSpoofedUUID();

    public void bridge$setSpoofedUUID(UUID var1);

    public Property[] bridge$getSpoofedProfile();

    public void bridge$setSpoofedProfile(Property[] var1);

    public String bridge$getHostname();

    public void bridge$setHostname(String var1);
}

