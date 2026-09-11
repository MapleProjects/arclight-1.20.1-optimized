/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.boss.EnderDragonPart
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraftforge.common.util.FakePlayer
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.mod.server.entity.ArclightFakePlayer;
import io.izzel.arclight.common.mod.server.entity.EntityClassLookup;
import java.util.function.BiFunction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraftforge.common.util.FakePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftComplexPart;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderDragonPart;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftEntity.class}, remap=false)
public abstract class CraftEntityMixin
implements Entity {
    @Shadow
    protected net.minecraft.world.entity.Entity entity;
    @Shadow
    @Final
    protected CraftServer server;

    @Inject(method={"getEntity"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$fakePlayer(CraftServer server, net.minecraft.world.entity.Entity entity, CallbackInfoReturnable<CraftEntity> cir) {
        if (entity instanceof FakePlayer) {
            cir.setReturnValue((Object)new ArclightFakePlayer(server, (ServerPlayer)((FakePlayer)entity)));
            return;
        }
        if (entity instanceof EnderDragonPart) {
            EnderDragonPart part = (EnderDragonPart)entity;
            if (part.f_31010_ instanceof EnderDragon) {
                cir.setReturnValue((Object)new CraftEnderDragonPart(server, (EnderDragonPart)entity));
                return;
            }
            cir.setReturnValue((Object)new CraftComplexPart(server, (EnderDragonPart)entity));
            return;
        }
        BiFunction<CraftServer, net.minecraft.world.entity.Entity, Entity> convert = EntityClassLookup.getConvert(entity);
        cir.setReturnValue((Object)((CraftEntity)convert.apply(server, entity)));
    }
}

