/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.util.Unit
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package io.izzel.arclight.common.mixin.core.server.level;

import io.izzel.arclight.common.bridge.core.world.server.TicketTypeBridge;
import java.util.Comparator;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Unit;
import org.bukkit.plugin.Plugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={TicketType.class})
public abstract class TicketTypeMixin
implements TicketTypeBridge {
    private static final TicketType<Unit> PLUGIN = TicketType.m_9462_((String)"plugin", (a, b) -> 0);
    private static final TicketType<Plugin> PLUGIN_TICKET = TicketType.m_9462_((String)"plugin_ticket", Comparator.comparing(it -> it.getClass().getName()));

    @Override
    @Accessor(value="timeout")
    public abstract void bridge$setLifespan(long var1);
}

