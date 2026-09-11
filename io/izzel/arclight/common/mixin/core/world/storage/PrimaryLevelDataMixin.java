/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Lifecycle
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelSettings
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.levelgen.WorldDimensions
 *  net.minecraft.world.level.levelgen.WorldGenSettings
 *  net.minecraft.world.level.levelgen.WorldOptions
 *  net.minecraft.world.level.storage.PrimaryLevelData
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.storage;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import io.izzel.arclight.common.bridge.core.world.storage.WorldInfoBridge;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldDimensions;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PrimaryLevelData.class})
public abstract class PrimaryLevelDataMixin
implements WorldInfoBridge {
    @Shadow
    private boolean f_78460_;
    @Shadow
    private boolean f_78458_;
    @Shadow
    public LevelSettings f_78443_;
    @Shadow
    @Final
    private Lifecycle f_78445_;
    public ServerLevel world;
    public Registry<LevelStem> customDimensions;

    @Shadow
    public abstract String m_5462_();

    @Shadow
    public abstract boolean m_5474_();

    @Redirect(method={"setTagData"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/levelgen/WorldGenSettings;encode(Lcom/mojang/serialization/DynamicOps;Lnet/minecraft/world/level/levelgen/WorldOptions;Lnet/minecraft/core/RegistryAccess;)Lcom/mojang/serialization/DataResult;"))
    private <T extends Tag> DataResult<T> arclight$customDim(DynamicOps<T> ops, WorldOptions options, RegistryAccess registry) {
        return WorldGenSettings.m_246823_(ops, (WorldOptions)options, (WorldDimensions)new WorldDimensions(this.customDimensions != null ? this.customDimensions : registry.m_175515_(Registries.f_256862_)));
    }

    @Inject(method={"setThundering"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$thunder(boolean thunderingIn, CallbackInfo ci) {
        if (this.f_78460_ == thunderingIn) {
            return;
        }
        World world = Bukkit.getWorld(this.m_5462_());
        if (world != null) {
            ThunderChangeEvent event = new ThunderChangeEvent(world, thunderingIn);
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"setRaining"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$storm(boolean isRaining, CallbackInfo ci) {
        if (this.f_78458_ == isRaining) {
            return;
        }
        World world = Bukkit.getWorld(this.m_5462_());
        if (world != null) {
            WeatherChangeEvent event = new WeatherChangeEvent(world, isRaining);
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"setDifficulty"}, at={@At(value="RETURN")})
    private void arclight$sendDiffChange(Difficulty newDifficulty, CallbackInfo ci) {
        ClientboundChangeDifficultyPacket packet = new ClientboundChangeDifficultyPacket(newDifficulty, this.m_5474_());
        for (Player player : this.world.m_6907_()) {
            ((ServerPlayer)player).f_8906_.m_9829_((Packet)packet);
        }
    }

    @Override
    public void bridge$setWorld(ServerLevel world) {
        this.world = world;
    }

    @Override
    public ServerLevel bridge$getWorld() {
        return this.world;
    }

    public void checkName(String name) {
        if (!this.f_78443_.f_46902_.equals(name)) {
            this.f_78443_.f_46902_ = name;
        }
    }

    @Override
    public LevelSettings bridge$getWorldSettings() {
        return this.f_78443_;
    }

    @Override
    public Lifecycle bridge$getLifecycle() {
        return this.f_78445_;
    }
}

