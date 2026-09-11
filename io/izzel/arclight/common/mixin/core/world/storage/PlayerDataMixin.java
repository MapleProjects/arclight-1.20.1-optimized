/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtIo
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.storage.PlayerDataStorage
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.storage;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.storage.PlayerDataBridge;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PlayerDataStorage.class})
public class PlayerDataMixin
implements PlayerDataBridge {
    @Shadow
    @Final
    private File f_78427_;
    @Shadow
    @Final
    private static Logger f_78426_;

    @Inject(method={"load"}, at={@At(value="INVOKE", target="Lnet/minecraft/nbt/NbtUtils;getDataVersion(Lnet/minecraft/nbt/CompoundTag;I)I")})
    private void arclight$lastSeenTime(Player player, CallbackInfoReturnable<CompoundTag> cir) {
        if (player instanceof ServerPlayer) {
            CraftPlayer craftPlayer = ((ServerPlayerEntityBridge)player).bridge$getBukkitEntity();
            long modified = new File(this.f_78427_, String.valueOf(player.m_20148_()) + ".dat").lastModified();
            if (modified < craftPlayer.getFirstPlayed()) {
                craftPlayer.setFirstPlayed(modified);
            }
        }
    }

    public File getPlayerDir() {
        return this.f_78427_;
    }

    public CompoundTag getPlayerData(String uuid) {
        try {
            File file1 = new File(this.f_78427_, uuid + ".dat");
            if (file1.exists()) {
                return NbtIo.m_128939_((InputStream)new FileInputStream(file1));
            }
        }
        catch (Exception exception) {
            f_78426_.warn("Failed to load player data for " + uuid);
        }
        return null;
    }

    @Override
    public File bridge$getPlayerDir() {
        return this.getPlayerDir();
    }

    @Override
    public CompoundTag bridge$getPlayerData(String uuid) {
        return this.getPlayerData(uuid);
    }
}

