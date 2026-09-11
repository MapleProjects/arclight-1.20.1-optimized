/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData$HoldingPlayer
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.saveddata.maps;

import io.izzel.arclight.common.bridge.core.world.storage.MapDataBridge;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={MapItemSavedData.class})
public abstract class MapDataMixin
implements MapDataBridge {
    @Shadow
    @Final
    public ResourceKey<Level> f_77887_;
    @Shadow
    @Final
    private List<MapItemSavedData.HoldingPlayer> f_77893_;
    public CraftMapView mapView;
    private CraftServer server;
    public UUID uniqueId;
    public String id;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void arclight$init(int p_164768_, int p_164769_, byte p_164770_, boolean p_164771_, boolean p_164772_, boolean p_164773_, ResourceKey<Level> p_164774_, CallbackInfo ci) {
        this.mapView = new CraftMapView((MapItemSavedData)this);
        this.server = (CraftServer)Bukkit.getServer();
    }

    @Redirect(method={"load"}, at=@At(value="INVOKE", target="Ljava/util/Optional;orElseThrow(Ljava/util/function/Supplier;)Ljava/lang/Object;"))
    private static Object arclight$customDimension(Optional<ResourceKey<Level>> optional, Supplier<?> exceptionSupplier, CompoundTag nbt) {
        return optional.orElseGet(() -> {
            UUID uniqueId;
            CraftWorld world;
            long least = nbt.m_128454_("UUIDLeast");
            long most = nbt.m_128454_("UUIDMost");
            if (least != 0L && most != 0L && (world = (CraftWorld)Bukkit.getWorld(uniqueId = new UUID(most, least))) != null) {
                return world.getHandle().m_46472_();
            }
            throw new IllegalArgumentException("Invalid map dimension: " + String.valueOf(nbt.m_128423_("dimension")));
        });
    }

    @Inject(method={"save"}, at={@At(value="HEAD")})
    public void arclight$storeDimension(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        if (this.uniqueId == null) {
            for (World world : this.server.getWorlds()) {
                CraftWorld cWorld = (CraftWorld)world;
                if (cWorld.getHandle().m_46472_() != this.f_77887_) continue;
                this.uniqueId = cWorld.getUID();
                break;
            }
        }
        if (this.uniqueId != null) {
            compound.m_128356_("UUIDLeast", this.uniqueId.getLeastSignificantBits());
            compound.m_128356_("UUIDMost", this.uniqueId.getMostSignificantBits());
        }
    }

    @Override
    public void bridge$setId(String id) {
        this.id = id;
    }

    @Override
    public List<MapItemSavedData.HoldingPlayer> bridge$getCarriedBy() {
        return this.f_77893_;
    }

    @Override
    public CraftMapView bridge$getMapView() {
        return this.mapView;
    }
}

