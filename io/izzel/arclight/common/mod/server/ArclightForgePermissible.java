/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.server.permission.PermissionAPI
 *  net.minecraftforge.server.permission.handler.IPermissionHandler
 *  net.minecraftforge.server.permission.nodes.PermissionDynamicContext
 *  net.minecraftforge.server.permission.nodes.PermissionDynamicContextKey
 *  net.minecraftforge.server.permission.nodes.PermissionNode
 *  net.minecraftforge.server.permission.nodes.PermissionNode$PermissionResolver
 *  net.minecraftforge.server.permission.nodes.PermissionType
 *  net.minecraftforge.server.permission.nodes.PermissionTypes
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package io.izzel.arclight.common.mod.server;

import io.izzel.arclight.api.Unsafe;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.handler.IPermissionHandler;
import net.minecraftforge.server.permission.nodes.PermissionDynamicContext;
import net.minecraftforge.server.permission.nodes.PermissionDynamicContextKey;
import net.minecraftforge.server.permission.nodes.PermissionNode;
import net.minecraftforge.server.permission.nodes.PermissionType;
import net.minecraftforge.server.permission.nodes.PermissionTypes;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.ServerOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArclightForgePermissible
extends PermissibleBase {
    private final CraftHumanEntity player;
    private static final MethodHandle H_handler;
    private static final MethodHandle H_newNode;

    public ArclightForgePermissible(@Nullable ServerOperator opable) {
        super(opable);
        this.player = (CraftHumanEntity)opable;
    }

    @Override
    public boolean hasPermission(@NotNull String inName) {
        PermissionNode node = ArclightForgePermissible.newNode(inName, (player, playerUUID, context) -> super.hasPermission(inName), new PermissionDynamicContextKey[0]);
        Player player2 = this.player.getHandle();
        if (player2 instanceof ServerPlayer) {
            ServerPlayer player3 = (ServerPlayer)player2;
            return (Boolean)ArclightForgePermissible.getHandler().getPermission(player3, node, new PermissionDynamicContext[0]);
        }
        return (Boolean)ArclightForgePermissible.getHandler().getOfflinePermission(this.player.getUniqueId(), node, new PermissionDynamicContext[0]);
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        PermissionNode node = ArclightForgePermissible.newNode(perm.getName(), (player, playerUUID, context) -> super.hasPermission(perm), new PermissionDynamicContextKey[0]);
        Player player2 = this.player.getHandle();
        if (player2 instanceof ServerPlayer) {
            ServerPlayer player3 = (ServerPlayer)player2;
            return (Boolean)ArclightForgePermissible.getHandler().getPermission(player3, node, new PermissionDynamicContext[0]);
        }
        return (Boolean)ArclightForgePermissible.getHandler().getOfflinePermission(this.player.getUniqueId(), node, new PermissionDynamicContext[0]);
    }

    private static IPermissionHandler getHandler() {
        try {
            return H_handler.invokeExact();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> PermissionNode<T> newNode(String nodeName, PermissionNode.PermissionResolver<T> defaultResolver, PermissionDynamicContextKey ... dynamics) {
        try {
            return H_newNode.invokeExact(nodeName, PermissionTypes.BOOLEAN, defaultResolver, dynamics);
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            H_handler = Unsafe.lookup().findStaticGetter(PermissionAPI.class, "activeHandler", IPermissionHandler.class);
            H_newNode = Unsafe.lookup().findConstructor(PermissionNode.class, MethodType.methodType(Void.TYPE, String.class, PermissionType.class, PermissionNode.PermissionResolver.class, PermissionDynamicContextKey[].class));
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}

