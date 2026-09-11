/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.behavior.AssignProfessionFromJobSite
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.npc.VillagerData
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import net.minecraft.world.entity.ai.behavior.AssignProfessionFromJobSite;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={AssignProfessionFromJobSite.class})
public class AssignProfessionFromJobSiteMixin {
    @Redirect(method={"*"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/npc/Villager;setVillagerData(Lnet/minecraft/world/entity/npc/VillagerData;)V"))
    private static void arclight$jobChange(Villager instance, VillagerData villagerData) {
        VillagerCareerChangeEvent event = CraftEventFactory.callVillagerCareerChangeEvent(instance, CraftVillager.nmsToBukkitProfession(villagerData.m_35571_()), VillagerCareerChangeEvent.ChangeReason.EMPLOYED);
        if (!event.isCancelled()) {
            VillagerData newData = villagerData.m_35565_(CraftVillager.bukkitToNmsProfession(event.getProfession()));
            instance.m_34375_(newData);
        }
    }
}

