/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.NetherPortalBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$StatePredicate
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.portal.PortalInfo
 *  net.minecraft.world.level.portal.PortalShape
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.block.PortalInfoBridge;
import io.izzel.arclight.common.bridge.core.block.PortalSizeBridge;
import io.izzel.arclight.common.bridge.core.world.IWorldBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PortalShape.class})
public abstract class PortalShapeMixin
implements PortalSizeBridge {
    @Shadow
    @Final
    private LevelAccessor f_77686_;
    @Shadow
    @Final
    private Direction.Axis f_77687_;
    @Shadow
    @Nullable
    private BlockPos f_77690_;
    @Shadow
    private int f_77691_;
    @Shadow
    @Final
    private Direction f_77688_;
    @Shadow
    @Final
    private int f_77692_;
    List<BlockState> blocks = new ArrayList<BlockState>();
    private transient boolean arclight$ret;

    @Shadow
    public abstract void shadow$m_77743_();

    @Shadow
    public static PortalInfo m_257966_(ServerLevel p_259301_, BlockUtil.FoundRectangle p_259931_, Direction.Axis p_259901_, Vec3 p_259630_, Entity p_259166_, Vec3 p_260043_, float p_259853_, float p_259667_) {
        return null;
    }

    @Redirect(method={"getDistanceUntilEdgeAboveFrame"}, require=0, at=@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/level/block/state/BlockBehaviour$StatePredicate;test(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean arclight$captureBlock(BlockBehaviour.StatePredicate predicate, net.minecraft.world.level.block.state.BlockState p_test_1_, BlockGetter p_test_2_, BlockPos pos) {
        boolean test = predicate.m_61035_(p_test_1_, p_test_2_, pos);
        if (test) {
            this.blocks.add(CraftBlock.at(this.f_77686_, pos).getState());
        }
        return test;
    }

    @Inject(method={"createPortalBlocks"}, require=0, cancellable=true, at={@At(value="HEAD")})
    private void arclight$buildPortal(CallbackInfo ci) {
        CraftWorld world = ((WorldBridge)((IWorldBridge)this.f_77686_).bridge$getMinecraftWorld()).bridge$getWorld();
        net.minecraft.world.level.block.state.BlockState blockState = (net.minecraft.world.level.block.state.BlockState)Blocks.f_50142_.m_49966_().m_61124_((Property)NetherPortalBlock.f_54904_, (Comparable)this.f_77687_);
        BlockPos.m_121940_((BlockPos)this.f_77690_, (BlockPos)this.f_77690_.m_5484_(Direction.UP, this.f_77691_ - 1).m_5484_(this.f_77688_, this.f_77692_ - 1)).forEach(pos -> {
            CraftBlockState state = CraftBlockStates.getBlockState((LevelAccessor)((IWorldBridge)this.f_77686_).bridge$getMinecraftWorld(), pos, 18);
            state.setData(blockState);
            this.blocks.add(state);
        });
        PortalCreateEvent event = new PortalCreateEvent(this.blocks, world, null, PortalCreateEvent.CreateReason.FIRE);
        Bukkit.getPluginManager().callEvent(event);
        boolean bl = this.arclight$ret = !event.isCancelled();
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    public boolean createPortalBlocks() {
        this.shadow$m_77743_();
        return this.arclight$ret;
    }

    @Override
    public boolean bridge$createPortal() {
        return this.createPortalBlocks();
    }

    @Redirect(method={"createPortalInfo"}, require=0, at=@At(value="NEW", target="net/minecraft/world/level/portal/PortalInfo"))
    private static PortalInfo arclight$setPortalInfo(Vec3 pos, Vec3 motion, float rotationYaw, float rotationPitch, ServerLevel world) {
        PortalInfo portalInfo = new PortalInfo(pos, motion, rotationYaw, rotationPitch);
        ((PortalInfoBridge)portalInfo).bridge$setWorld(world);
        ((PortalInfoBridge)portalInfo).bridge$setPortalEventInfo(ArclightCaptures.getCraftPortalEvent());
        return portalInfo;
    }

    private static PortalInfo createPortalInfo(ServerLevel world, BlockUtil.FoundRectangle result, Direction.Axis axis, Vec3 offsetVector, Entity entity, Vec3 motion, float rotationYaw, float rotationPitch, CraftPortalEvent event) {
        ArclightCaptures.captureCraftPortalEvent(event);
        return PortalShapeMixin.m_257966_(world, result, axis, offsetVector, entity, motion, rotationYaw, rotationPitch);
    }
}

