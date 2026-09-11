/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.players.StoredUserEntry
 *  net.minecraft.server.players.StoredUserList
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.server.management;

import io.izzel.arclight.common.bridge.core.server.management.UserListBridge;
import java.util.Collection;
import java.util.Map;
import net.minecraft.server.players.StoredUserEntry;
import net.minecraft.server.players.StoredUserList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={StoredUserList.class})
public class UserListMixin<K, V extends StoredUserEntry<K>>
implements UserListBridge<V> {
    @Shadow
    @Final
    private Map<String, V> f_11377_;

    public Collection<V> getValues() {
        return this.f_11377_.values();
    }

    @Override
    public Collection<V> bridge$getValues() {
        return this.getValues();
    }
}

