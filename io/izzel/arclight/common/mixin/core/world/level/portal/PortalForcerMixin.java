/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.portal.PortalForcer
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.portal;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.TeleporterBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.portal.PortalForcer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.util.BlockStateListPopulator;
import org.bukkit.event.world.PortalCreateEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PortalForcer.class})
public abstract class PortalForcerMixin
implements TeleporterBridge {
    @Shadow
    @Final
    protected ServerLevel f_77648_;
    private transient int arclight$searchRadius = -1;
    private transient BlockStateListPopulator arclight$populator;
    private transient Entity arclight$entity;
    private transient int arclight$createRadius = -1;

    @Shadow
    public abstract Optional<BlockUtil.FoundRectangle> m_77666_(BlockPos var1, Direction.Axis var2);

    @Shadow
    public abstract Optional<BlockUtil.FoundRectangle> m_192985_(BlockPos var1, boolean var2, WorldBorder var3);

    @ModifyVariable(method={"findPortalAround"}, require=0, index=5, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/ai/village/poi/PoiManager;ensureLoadedAndValid(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;I)V"))
    private int arclight$useSearchRadius(int i) {
        return this.arclight$searchRadius == -1 ? i : this.arclight$searchRadius;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Optional<BlockUtil.FoundRectangle> findPortalAround(BlockPos pos, WorldBorder worldBorder, int searchRadius) {
        this.arclight$searchRadius = searchRadius;
        try {
            Optional<BlockUtil.FoundRectangle> optional = this.m_192985_(pos, false, worldBorder);
            return optional;
        }
        finally {
            this.arclight$searchRadius = -1;
        }
    }

    @Override
    public Optional<BlockUtil.FoundRectangle> bridge$findPortal(BlockPos pos, WorldBorder worldborder, int searchRadius) {
        return this.findPortalAround(pos, worldborder, searchRadius);
    }

    @ModifyArg(method={"createPortal"}, require=0, index=1, at=@At(value="INVOKE", target="Lnet/minecraft/core/BlockPos;spiralAround(Lnet/minecraft/core/BlockPos;ILnet/minecraft/core/Direction;Lnet/minecraft/core/Direction;)Ljava/lang/Iterable;"))
    private int arclight$changeRadius(int i) {
        return this.arclight$createRadius == -1 ? i : this.arclight$createRadius;
    }

    @Redirect(method={"createPortal"}, require=0, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean arclight$captureBlocks1(ServerLevel serverWorld, BlockPos pos, BlockState state) {
        if (this.arclight$populator == null) {
            this.arclight$populator = new BlockStateListPopulator((LevelAccessor)serverWorld);
        }
        return this.arclight$populator.m_7731_(pos, state, 3);
    }

    @Redirect(method={"createPortal"}, require=0, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean arclight$captureBlocks2(ServerLevel serverWorld, BlockPos pos, BlockState state, int flags) {
        if (this.arclight$populator == null) {
            this.arclight$populator = new BlockStateListPopulator((LevelAccessor)serverWorld);
        }
        return this.arclight$populator.m_7731_(pos, state, flags);
    }

    @Inject(method={"createPortal"}, require=0, cancellable=true, at={@At(value="RETURN")})
    private void arclight$portalCreate(BlockPos pos, Direction.Axis axis, CallbackInfoReturnable<Optional<BlockUtil.FoundRectangle>> cir) {
        CraftWorld craftWorld = ((WorldBridge)this.f_77648_).bridge$getWorld();
        List<Object> blockStates = this.arclight$populator == null ? new ArrayList() : this.arclight$populator.getList();
        PortalCreateEvent event = new PortalCreateEvent(blockStates, craftWorld, this.arclight$entity == null ? null : ((EntityBridge)this.arclight$entity).bridge$getBukkitEntity(), PortalCreateEvent.CreateReason.NETHER_PAIR);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue(Optional.empty());
            return;
        }
        if (this.arclight$populator != null) {
            this.arclight$populator.updateList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis, Entity entity, int createRadius) {
        this.arclight$entity = entity;
        this.arclight$createRadius = createRadius;
        try {
            Optional<BlockUtil.FoundRectangle> optional = this.m_77666_(pos, axis);
            return optional;
        }
        finally {
            this.arclight$entity = null;
            this.arclight$createRadius = -1;
        }
    }

    @Override
    public Optional<BlockUtil.FoundRectangle> bridge$createPortal(BlockPos pos, Direction.Axis axis, Entity entity, int createRadius) {
        return this.createPortal(pos, axis, entity, createRadius);
    }
}

