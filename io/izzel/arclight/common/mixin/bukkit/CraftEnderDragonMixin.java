/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.EnumHelper
 *  net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.api.EnumHelper;
import io.izzel.arclight.common.mod.ArclightMod;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderDragon;
import org.bukkit.entity.EnderDragon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftEnderDragon.class}, remap=false)
public class CraftEnderDragonMixin {
    @Inject(method={"getPhase"}, at={@At(value="HEAD")})
    public void arclight$getPhase(CallbackInfoReturnable<EnderDragon.Phase> cir) {
        CraftEnderDragonMixin.checkAndUpdateDragonPhase();
    }

    @Inject(method={"getBukkitPhase"}, at={@At(value="HEAD")})
    private static void arclight$getBukkitPhase(EnderDragonPhase phase, CallbackInfoReturnable<EnderDragon.Phase> cir) {
        CraftEnderDragonMixin.checkAndUpdateDragonPhase();
    }

    private static void checkAndUpdateDragonPhase() {
        int forgeCount = EnderDragonPhase.m_31406_();
        if (EnderDragon.Phase.values().length != forgeCount) {
            ArrayList<EnderDragon.Phase> newTypes = new ArrayList<EnderDragon.Phase>();
            for (int id = EnderDragon.Phase.values().length; id < forgeCount; ++id) {
                String name = "MOD_PHASE_" + id;
                EnderDragon.Phase newPhase = (EnderDragon.Phase)((Object)EnumHelper.makeEnum(EnderDragon.Phase.class, (String)name, (int)id, List.of(), List.of()));
                newTypes.add(newPhase);
                ArclightMod.LOGGER.debug("Registered {} as ender dragon phase {}", (Object)name, (Object)newPhase);
            }
            EnumHelper.addEnums(EnderDragon.Phase.class, newTypes);
        }
    }
}

