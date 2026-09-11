/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.ArclightConfig
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.mod.server.ArclightForgePermissible;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.i18n.ArclightConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryPlayer;
import org.bukkit.inventory.InventoryView;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.ServerOperator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftHumanEntity.class}, remap=false)
public abstract class CraftHumanEntityMixin
extends CraftEntity {
    @Shadow
    private CraftInventoryPlayer inventory;

    @Shadow
    public abstract Player getHandle();

    public CraftHumanEntityMixin(CraftServer server, Entity entity) {
        super(server, entity);
    }

    @Redirect(method={"<init>"}, at=@At(value="NEW", target="org/bukkit/permissions/PermissibleBase"))
    private PermissibleBase arclight$forwardPerm(ServerOperator opable) {
        if (ArclightConfig.spec().getCompat().isForwardPermissionReverse()) {
            return new ArclightForgePermissible(opable);
        }
        return new PermissibleBase(opable);
    }

    @Inject(method={"getOpenInventory"}, at={@At(value="HEAD")})
    private void arclight$capturePlayer(CallbackInfoReturnable<InventoryView> cir) {
        ArclightCaptures.captureContainerOwner(this.getHandle());
    }

    @Inject(method={"getOpenInventory"}, at={@At(value="RETURN")})
    private void arclight$resetPlayer(CallbackInfoReturnable<InventoryView> cir) {
        ArclightCaptures.resetContainerOwner();
    }

    @Override
    public void setHandle(Entity entity) {
        super.setHandle(entity);
        this.inventory = new CraftInventoryPlayer(((Player)entity).m_150109_());
    }
}

