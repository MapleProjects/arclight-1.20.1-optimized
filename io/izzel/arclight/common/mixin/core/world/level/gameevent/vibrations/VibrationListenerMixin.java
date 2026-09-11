/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.gameevent.vibrations.VibrationSystem
 *  net.minecraft.world.level.gameevent.vibrations.VibrationSystem$Data
 *  net.minecraft.world.level.gameevent.vibrations.VibrationSystem$Listener
 *  net.minecraft.world.level.gameevent.vibrations.VibrationSystem$User
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.gameevent.vibrations;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftGameEvent;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockReceiveGameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={VibrationSystem.Listener.class})
public abstract class VibrationListenerMixin {
    @Shadow
    @Final
    private VibrationSystem f_279547_;

    @Shadow
    private static boolean m_280258_(Level p_223776_, Vec3 p_223777_, Vec3 p_223778_) {
        return false;
    }

    @Shadow
    protected abstract void m_280099_(ServerLevel var1, VibrationSystem.Data var2, GameEvent var3, GameEvent.Context var4, Vec3 var5, Vec3 var6);

    @Overwrite
    public boolean m_214068_(ServerLevel worldserver, GameEvent gameevent, GameEvent.Context gameevent_a, Vec3 vec3d) {
        VibrationSystem.Data vibrationsystem_a = this.f_279547_.m_280002_();
        VibrationSystem.User vibrationsystem_d = this.f_279547_.m_280445_();
        if (vibrationsystem_a.m_280602_() != null) {
            return false;
        }
        if (!vibrationsystem_d.m_280612_(gameevent, gameevent_a)) {
            return false;
        }
        Optional optional = vibrationsystem_d.m_280010_().m_142502_((Level)worldserver);
        if (optional.isEmpty()) {
            return false;
        }
        Vec3 vec3d1 = (Vec3)optional.get();
        boolean defaultCancel = !vibrationsystem_d.m_280080_(worldserver, BlockPos.m_274446_((Position)vec3d), gameevent, gameevent_a);
        Entity entity = gameevent_a.f_223711_();
        BlockReceiveGameEvent event = new BlockReceiveGameEvent(CraftGameEvent.minecraftToBukkit(gameevent), CraftBlock.at((LevelAccessor)worldserver, BlockPos.m_274446_((Position)vec3d1)), entity == null ? null : ((EntityBridge)entity).bridge$getBukkitEntity());
        event.setCancelled(defaultCancel);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return false;
        }
        if (VibrationListenerMixin.m_280258_((Level)worldserver, vec3d, vec3d1)) {
            return false;
        }
        this.m_280099_(worldserver, vibrationsystem_a, gameevent, gameevent_a, vec3d, vec3d1);
        return true;
    }
}

