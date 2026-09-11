/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.server.permission.handler.IPermissionHandler
 *  net.minecraftforge.server.permission.nodes.PermissionDynamicContext
 *  net.minecraftforge.server.permission.nodes.PermissionNode
 *  net.minecraftforge.server.permission.nodes.PermissionTypes
 */
package io.izzel.arclight.common.mod.server;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.permission.handler.IPermissionHandler;
import net.minecraftforge.server.permission.nodes.PermissionDynamicContext;
import net.minecraftforge.server.permission.nodes.PermissionNode;
import net.minecraftforge.server.permission.nodes.PermissionTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class ArclightPermissionHandler
implements IPermissionHandler {
    private final IPermissionHandler delegate;

    public ArclightPermissionHandler(IPermissionHandler delegate) {
        Objects.requireNonNull(delegate, "permission handler");
        this.delegate = delegate;
    }

    public ResourceLocation getIdentifier() {
        return new ResourceLocation("arclight", "permission");
    }

    public Set<PermissionNode<?>> getRegisteredNodes() {
        return this.delegate.getRegisteredNodes();
    }

    public <T> T getPermission(ServerPlayer player, PermissionNode<T> node, PermissionDynamicContext<?> ... context) {
        if (node.getType() == PermissionTypes.BOOLEAN) {
            return (T)Boolean.valueOf(((ServerPlayerEntityBridge)player).bridge$getBukkitEntity().hasPermission(node.getNodeName()));
        }
        return (T)this.delegate.getPermission(player, node, context);
    }

    public <T> T getOfflinePermission(UUID uuid, PermissionNode<T> node, PermissionDynamicContext<?> ... context) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null && node.getType() == PermissionTypes.BOOLEAN) {
            return (T)Boolean.valueOf(player.hasPermission(node.getNodeName()));
        }
        return (T)this.delegate.getOfflinePermission(uuid, node, context);
    }
}

