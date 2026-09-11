/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.behavior.ResetProfession
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.npc.VillagerData
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import net.minecraft.world.entity.ai.behavior.ResetProfession;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ResetProfession.class})
public class ResetProfessionMixin {
    @Redirect(method={"*"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/npc/Villager;setVillagerData(Lnet/minecraft/world/entity/npc/VillagerData;)V"))
    private static void arclight$careerChangeHook(Villager villagerEntity, VillagerData villagerData) {
        VillagerCareerChangeEvent event = CraftEventFactory.callVillagerCareerChangeEvent(villagerEntity, CraftVillager.nmsToBukkitProfession(VillagerProfession.f_35585_), VillagerCareerChangeEvent.ChangeReason.LOSING_JOB);
        if (!event.isCancelled()) {
            VillagerData newData = villagerEntity.m_7141_().m_35565_(CraftVillager.bukkitToNmsProfession(event.getProfession()));
            villagerEntity.m_34375_(newData);
        }
    }
}

