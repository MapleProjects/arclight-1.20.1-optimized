/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
 *  net.minecraft.network.protocol.game.ServerboundPlayerActionPacket$Action
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.ServerPlayerGameMode
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.DebugStickItem
 *  net.minecraft.world.item.DoubleHighBlockItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.CakeBlock
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.TrapDoorBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.DoubleBlockHalf
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickBlock
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.eventbus.api.Event$Result
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server.management;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.server.management.PlayerInteractionManagerBridge;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import java.util.ArrayList;
import java.util.Objects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DebugStickItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ServerPlayerGameMode.class})
public abstract class ServerPlayerGameModeMixin
implements PlayerInteractionManagerBridge {
    @Shadow
    protected ServerLevel f_9244_;
    @Shadow
    @Final
    protected ServerPlayer f_9245_;
    @Shadow
    private GameType f_9247_;
    @Shadow
    private int f_9250_;
    @Shadow
    private int f_9252_;
    @Shadow
    private boolean f_9249_;
    @Shadow
    private BlockPos f_9251_;
    @Shadow
    private int f_9256_;
    @Shadow
    private boolean f_9253_;
    @Shadow
    private BlockPos f_9254_;
    @Shadow
    private int f_9255_;
    public boolean interactResult = false;
    public boolean firedInteract = false;

    @Shadow
    public abstract boolean m_9295_();

    @Shadow
    public abstract void m_215116_(BlockPos var1, int var2, String var3);

    @Shadow
    protected abstract void m_215125_(BlockPos var1, boolean var2, int var3, String var4);

    @Inject(method={"changeGameModeForPlayer"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayerGameMode;setGameModeForPlayer(Lnet/minecraft/world/level/GameType;Lnet/minecraft/world/level/GameType;)V")})
    private void arclight$gameModeEvent(GameType gameType, CallbackInfoReturnable<Boolean> cir) {
        PlayerGameModeChangeEvent event = new PlayerGameModeChangeEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)this.f_9245_).bridge$getBukkitEntity(), GameMode.getByValue(gameType.m_46392_()));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Overwrite
    public void m_214168_(BlockPos blockPos, ServerboundPlayerActionPacket.Action action, Direction direction, int i, int j) {
        if (!this.f_9244_.m_46805_(blockPos)) {
            return;
        }
        PlayerInteractEvent.LeftClickBlock forgeEvent = ForgeHooks.onLeftClickBlock((Player)this.f_9245_, (BlockPos)blockPos, (Direction)direction, (ServerboundPlayerActionPacket.Action)action);
        if (forgeEvent.isCanceled() || !this.m_9295_() && forgeEvent.getUseItem() == Event.Result.DENY) {
            this.f_9244_.m_7260_(blockPos, this.f_9244_.m_8055_(blockPos), this.f_9244_.m_8055_(blockPos), 3);
            return;
        }
        if (!this.f_9245_.canReach(blockPos, 1.5)) {
            this.m_215125_(blockPos, false, j, "too far");
        } else if (blockPos.m_123342_() >= i) {
            this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket(blockPos, this.f_9244_.m_8055_(blockPos)));
            this.m_215125_(blockPos, false, j, "too high");
        } else if (action == ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK) {
            if (!this.f_9244_.m_7966_((Player)this.f_9245_, blockPos)) {
                CraftEventFactory.callPlayerInteractEvent((Player)this.f_9245_, Action.LEFT_CLICK_BLOCK, blockPos, direction, this.f_9245_.m_150109_().m_36056_(), InteractionHand.MAIN_HAND);
                this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket(blockPos, this.f_9244_.m_8055_(blockPos)));
                this.m_215125_(blockPos, false, j, "may not interact");
                BlockEntity tileentity = this.f_9244_.m_7702_(blockPos);
                if (tileentity != null) {
                    this.f_9245_.f_8906_.m_9829_(tileentity.m_58483_());
                }
                return;
            }
            PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((Player)this.f_9245_, Action.LEFT_CLICK_BLOCK, blockPos, direction, this.f_9245_.m_150109_().m_36056_(), InteractionHand.MAIN_HAND);
            if (event.isCancelled()) {
                this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockPos));
                BlockEntity tileentity2 = this.f_9244_.m_7702_(blockPos);
                if (tileentity2 != null) {
                    this.f_9245_.f_8906_.m_9829_(tileentity2.m_58483_());
                }
                return;
            }
            if (this.m_9295_()) {
                this.m_215116_(blockPos, j, "creative destroy");
                return;
            }
            if (this.f_9245_.m_21205_().m_150930_(Items.f_42751_) && ((DebugStickItem)Items.f_42751_).m_150802_((Player)this.f_9245_, this.f_9244_.m_8055_(blockPos), (LevelAccessor)this.f_9244_, blockPos, false, this.f_9245_.m_21205_())) {
                this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockPos));
                return;
            }
            if (this.f_9245_.m_36187_((Level)this.f_9244_, blockPos, this.f_9247_)) {
                this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket(blockPos, this.f_9244_.m_8055_(blockPos)));
                this.m_215125_(blockPos, false, j, "block action restricted");
                return;
            }
            this.f_9250_ = this.f_9252_;
            float f = 1.0f;
            net.minecraft.world.level.block.state.BlockState iblockdata = this.f_9244_.m_8055_(blockPos);
            if (event.useInteractedBlock() == Event.Result.DENY) {
                net.minecraft.world.level.block.state.BlockState data = this.f_9244_.m_8055_(blockPos);
                if (data.m_60734_() instanceof DoorBlock) {
                    boolean bottom = data.m_61143_((Property)DoorBlock.f_52730_) == DoubleBlockHalf.LOWER;
                    this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockPos));
                    this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, bottom ? blockPos.m_7494_() : blockPos.m_7495_()));
                } else if (data.m_60734_() instanceof TrapDoorBlock) {
                    this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockPos));
                }
            } else if (!iblockdata.m_60795_()) {
                if (forgeEvent.getUseBlock() != Event.Result.DENY) {
                    iblockdata.m_60686_((Level)this.f_9244_, blockPos, (Player)this.f_9245_);
                }
                f = iblockdata.m_60625_((Player)this.f_9245_, (BlockGetter)this.f_9245_.m_9236_(), blockPos);
            }
            if (event.useItemInHand() == Event.Result.DENY) {
                if (f > 1.0f) {
                    this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockPos));
                }
                return;
            }
            BlockDamageEvent blockEvent = CraftEventFactory.callBlockDamageEvent(this.f_9245_, blockPos, this.f_9245_.m_150109_().m_36056_(), f >= 1.0f);
            if (blockEvent.isCancelled()) {
                this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockPos));
                return;
            }
            if (blockEvent.getInstaBreak()) {
                f = 2.0f;
            }
            if (!iblockdata.m_60795_() && f >= 1.0f) {
                this.m_215116_(blockPos, j, "insta mine");
            } else {
                if (this.f_9249_) {
                    this.f_9245_.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket(this.f_9251_, this.f_9244_.m_8055_(this.f_9251_)));
                    this.m_215125_(blockPos, false, j, "abort destroying since another started (client insta mine, server disagreed)");
                }
                this.f_9249_ = true;
                this.f_9251_ = blockPos;
                int state = (int)(f * 10.0f);
                this.f_9244_.m_6801_(this.f_9245_.m_19879_(), blockPos, state);
                this.m_215125_(blockPos, true, j, "actual start of destroying");
                CraftEventFactory.callBlockDamageAbortEvent(this.f_9245_, blockPos, this.f_9245_.m_150109_().m_36056_());
                this.f_9256_ = state;
            }
        } else if (action == ServerboundPlayerActionPacket.Action.STOP_DESTROY_BLOCK) {
            if (blockPos.equals((Object)this.f_9251_)) {
                int k = this.f_9252_ - this.f_9250_;
                net.minecraft.world.level.block.state.BlockState iblockdata = this.f_9244_.m_8055_(blockPos);
                if (!iblockdata.m_60795_()) {
                    float f2 = iblockdata.m_60625_((Player)this.f_9245_, (BlockGetter)this.f_9245_.m_9236_(), blockPos) * (float)(k + 1);
                    if (f2 >= 0.7f) {
                        this.f_9249_ = false;
                        this.f_9244_.m_6801_(this.f_9245_.m_19879_(), blockPos, -1);
                        this.m_215116_(blockPos, j, "destroyed");
                        return;
                    }
                    if (!this.f_9253_) {
                        this.f_9249_ = false;
                        this.f_9253_ = true;
                        this.f_9254_ = blockPos;
                        this.f_9255_ = this.f_9250_;
                    }
                }
            }
            this.m_215125_(blockPos, true, j, "stopped destroying");
        } else if (action == ServerboundPlayerActionPacket.Action.ABORT_DESTROY_BLOCK) {
            this.f_9249_ = false;
            if (!Objects.equals(this.f_9251_, blockPos)) {
                ArclightMod.LOGGER.debug("Mismatch in destroy block pos: " + String.valueOf(this.f_9251_) + " " + String.valueOf(blockPos));
                this.f_9244_.m_6801_(this.f_9245_.m_19879_(), this.f_9251_, -1);
                this.m_215125_(blockPos, true, j, "aborted mismatched destroying");
            }
            this.f_9244_.m_6801_(this.f_9245_.m_19879_(), blockPos, -1);
            this.m_215125_(blockPos, true, j, "aborted destroying");
        }
    }

    @Inject(method={"destroyBlock"}, remap=true, at={@At(value="INVOKE", remap=false, target="Lnet/minecraftforge/common/ForgeHooks;onBlockBreakEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/GameType;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/core/BlockPos;)I")})
    public void arclight$beforePrimaryEventFired(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        ArclightCaptures.captureNextBlockBreakEventAsPrimaryEvent();
    }

    @Inject(method={"destroyBlock"}, remap=true, at={@At(value="INVOKE_ASSIGN", remap=false, target="Lnet/minecraftforge/common/ForgeHooks;onBlockBreakEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/GameType;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/core/BlockPos;)I")})
    public void arclight$handleSecondaryBlockBreakEvents(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        ArclightCaptures.BlockBreakEventContext breakEventContext = ArclightCaptures.popSecondaryBlockBreakEvent();
        while (breakEventContext != null) {
            Block block = breakEventContext.getEvent().getBlock();
            this.handleBlockDrop(breakEventContext, new BlockPos(block.getX(), block.getY(), block.getZ()));
            breakEventContext = ArclightCaptures.popSecondaryBlockBreakEvent();
        }
    }

    @Inject(method={"destroyBlock"}, at={@At(value="RETURN")})
    public void arclight$resetBlockBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        ArclightCaptures.BlockBreakEventContext breakEventContext = ArclightCaptures.popPrimaryBlockBreakEvent();
        if (breakEventContext != null) {
            this.handleBlockDrop(breakEventContext, pos);
        }
    }

    @Inject(method={"tick", "destroyAndAck"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayerGameMode;destroyBlock(Lnet/minecraft/core/BlockPos;)Z")})
    public void arclight$clearCaptures(CallbackInfo ci) {
        ArclightCaptures.clearBlockBreakEventContexts();
    }

    private void handleBlockDrop(ArclightCaptures.BlockBreakEventContext breakEventContext, BlockPos pos) {
        BlockBreakEvent breakEvent = breakEventContext.getEvent();
        ArrayList<ItemEntity> blockDrops = breakEventContext.getBlockDrops();
        BlockState state = breakEventContext.getBlockBreakPlayerState();
        if (blockDrops != null && (breakEvent == null || breakEvent.isDropItems())) {
            CraftBlock craftBlock = CraftBlock.at((LevelAccessor)this.f_9244_, pos);
            CraftEventFactory.handleBlockDropItemEvent(craftBlock, state, this.f_9245_, blockDrops);
        }
    }

    @Override
    public boolean bridge$isFiredInteract() {
        return this.firedInteract;
    }

    @Override
    public void bridge$setFiredInteract(boolean b) {
        this.firedInteract = b;
    }

    @Override
    public boolean bridge$getInteractResult() {
        return this.interactResult;
    }

    @Override
    public void bridge$setInteractResult(boolean b) {
        this.interactResult = b;
    }

    @Overwrite
    public InteractionResult m_7179_(ServerPlayer playerIn, Level worldIn, ItemStack stackIn, InteractionHand handIn, BlockHitResult blockRaytraceResultIn) {
        InteractionResult result;
        BlockPos blockpos = blockRaytraceResultIn.m_82425_();
        net.minecraft.world.level.block.state.BlockState blockstate = worldIn.m_8055_(blockpos);
        boolean cancelledBlock = false;
        if (!blockstate.m_60734_().m_245993_(worldIn.m_246046_())) {
            return InteractionResult.FAIL;
        }
        if (this.f_9247_ == GameType.SPECTATOR) {
            MenuProvider provider = blockstate.m_60750_(worldIn, blockpos);
            boolean bl = cancelledBlock = !(provider instanceof MenuProvider);
        }
        if (playerIn.m_36335_().m_41519_(stackIn.m_41720_())) {
            cancelledBlock = true;
        }
        PlayerInteractEvent bukkitEvent = CraftEventFactory.callPlayerInteractEvent((Player)playerIn, Action.RIGHT_CLICK_BLOCK, blockpos, blockRaytraceResultIn.m_82434_(), stackIn, cancelledBlock, handIn, blockRaytraceResultIn.m_82450_());
        this.bridge$setFiredInteract(true);
        this.bridge$setInteractResult(bukkitEvent.useItemInHand() == Event.Result.DENY);
        if (bukkitEvent.useInteractedBlock() == Event.Result.DENY) {
            if (blockstate.m_60734_() instanceof DoorBlock) {
                boolean bottom = blockstate.m_61143_((Property)DoorBlock.f_52730_) == DoubleBlockHalf.LOWER;
                playerIn.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, bottom ? blockpos.m_7494_() : blockpos.m_7495_()));
            } else if (blockstate.m_60734_() instanceof CakeBlock) {
                ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().sendHealthUpdate();
            } else if (stackIn.m_41720_() instanceof DoubleHighBlockItem) {
                playerIn.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockpos.m_121945_(blockRaytraceResultIn.m_82434_()).m_7494_()));
                playerIn.f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.f_9244_, blockpos.m_7494_()));
            }
            ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().updateInventory();
            return bukkitEvent.useItemInHand() != Event.Result.ALLOW ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
        if (this.f_9247_ == GameType.SPECTATOR) {
            MenuProvider inamedcontainerprovider = blockstate.m_60750_(worldIn, blockpos);
            if (inamedcontainerprovider != null) {
                playerIn.m_5893_(inamedcontainerprovider);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        PlayerInteractEvent.RightClickBlock event = ForgeHooks.onRightClickBlock((Player)playerIn, (InteractionHand)handIn, (BlockPos)blockpos, (BlockHitResult)blockRaytraceResultIn);
        if (event.isCanceled()) {
            return event.getCancellationResult();
        }
        UseOnContext itemusecontext = new UseOnContext((Player)playerIn, handIn, blockRaytraceResultIn);
        if (event.getUseItem() != Event.Result.DENY && (result = stackIn.onItemUseFirst(itemusecontext)) != InteractionResult.PASS) {
            return result;
        }
        boolean flag = !playerIn.m_21205_().m_41619_() || !playerIn.m_21206_().m_41619_();
        boolean flag1 = playerIn.m_36341_() && flag && (!playerIn.m_21205_().doesSneakBypassUse((LevelReader)worldIn, blockpos, (Player)playerIn) || !playerIn.m_21206_().doesSneakBypassUse((LevelReader)worldIn, blockpos, (Player)playerIn));
        ItemStack itemstack = stackIn.m_41777_();
        InteractionResult resultType = InteractionResult.PASS;
        if ((event.getUseBlock() == Event.Result.ALLOW || event.getUseBlock() != Event.Result.DENY && !flag1) && (resultType = blockstate.m_60664_(worldIn, (Player)playerIn, handIn, blockRaytraceResultIn)).m_19077_()) {
            CriteriaTriggers.f_10562_.m_285767_(playerIn, blockpos, itemstack);
            return resultType;
        }
        if (event.getUseItem() == Event.Result.ALLOW || !stackIn.m_41619_() && resultType != InteractionResult.SUCCESS && !this.bridge$getInteractResult()) {
            if (event.getUseItem() == Event.Result.DENY) {
                return InteractionResult.PASS;
            }
            if (this.m_9295_()) {
                int i = stackIn.m_41613_();
                resultType = stackIn.m_41661_(itemusecontext);
                stackIn.m_41764_(i);
            } else {
                resultType = stackIn.m_41661_(itemusecontext);
            }
            if (resultType.m_19077_()) {
                CriteriaTriggers.f_10562_.m_285767_(playerIn, blockpos, itemstack);
            }
            return resultType;
        }
        return resultType;
    }
}

