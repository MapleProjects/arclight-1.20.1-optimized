/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.NoteBlockInstrument
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.base.Function;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.common.mod.util.DistValidate;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerSignOpenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftEventFactory.class}, remap=false)
public class CraftEventFactoryMixin {
    @Shadow
    public static Entity entityDamage;
    @Shadow
    public static Block blockDamage;

    @Inject(method={"handleEntityDamageEvent(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Ljava/util/Map;Ljava/util/Map;Z)Lorg/bukkit/event/entity/EntityDamageEvent;"}, at={@At(value="HEAD")})
    private static void arclight$captureSource(Entity entity, DamageSource source, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions, boolean cancelled, CallbackInfoReturnable<EntityDamageEvent> cir) {
        Entity damageEventEntity = ArclightCaptures.getDamageEventEntity();
        BlockPos damageEventBlock = ArclightCaptures.getDamageEventBlock();
        if (damageEventEntity != null && entityDamage == null && source.m_276093_(DamageTypes.f_268450_)) {
            entityDamage = damageEventEntity;
        }
        if (damageEventBlock != null && blockDamage == null && (source.m_276093_(DamageTypes.f_268585_) || source.m_276093_(DamageTypes.f_268469_) || source.m_276093_(DamageTypes.f_268434_))) {
            blockDamage = CraftBlock.at((LevelAccessor)entity.m_20193_(), damageEventBlock);
        }
    }

    @Inject(method={"handleEntityDamageEvent(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Ljava/util/Map;Ljava/util/Map;Z)Lorg/bukkit/event/entity/EntityDamageEvent;"}, cancellable=true, at={@At(value="NEW", target="java/lang/IllegalStateException")})
    private static void arclight$unhandledDamage(Entity entity, DamageSource source, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions, boolean cancelled, CallbackInfoReturnable<EntityDamageEvent> cir) {
        EntityDamageEvent event = source.m_7639_() != null ? new EntityDamageByEntityEvent(((EntityBridge)source.m_7639_()).bridge$getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), EntityDamageEvent.DamageCause.CUSTOM, modifiers, modifierFunctions) : new EntityDamageEvent(((EntityBridge)entity).bridge$getBukkitEntity(), EntityDamageEvent.DamageCause.CUSTOM, modifiers, modifierFunctions);
        event.setCancelled(cancelled);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            ((EntityBridge)entity).bridge$getBukkitEntity().setLastDamageCause(event);
        }
        cir.setReturnValue((Object)event);
    }

    @Overwrite
    public static boolean handleBlockSpreadEvent(LevelAccessor world, BlockPos source, BlockPos target, net.minecraft.world.level.block.state.BlockState block, int flag) {
        if (!(world instanceof Level) || !DistValidate.isValid(world)) {
            world.m_7731_(target, block, flag);
            return true;
        }
        CraftBlockState state = CraftBlockStates.getBlockState(world, target, flag);
        state.setData(block);
        BlockSpreadEvent event = new BlockSpreadEvent(state.getBlock(), CraftBlock.at(world, source), state);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            state.update(true);
        }
        return !event.isCancelled();
    }

    @Overwrite
    public static boolean handleBlockGrowEvent(Level world, BlockPos pos, net.minecraft.world.level.block.state.BlockState newData, int flag) {
        if (!DistValidate.isValid((LevelAccessor)world)) {
            world.m_7731_(pos, newData, flag);
            return true;
        }
        Block block = ((WorldBridge)world).bridge$getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
        CraftBlockState state = (CraftBlockState)block.getState();
        state.setData(newData);
        BlockGrowEvent event = new BlockGrowEvent(block, state);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            state.update(true);
        }
        return !event.isCancelled();
    }

    @Overwrite
    public static boolean handleBlockFormEvent(Level world, BlockPos pos, net.minecraft.world.level.block.state.BlockState block, int flag, @Nullable Entity entity) {
        if (!DistValidate.isValid((LevelAccessor)world)) {
            world.m_7731_(pos, block, flag);
            return true;
        }
        CraftBlockState blockState = CraftBlockStates.getBlockState((LevelAccessor)world, pos, flag);
        blockState.setData(block);
        BlockFormEvent event = entity == null ? new BlockFormEvent(blockState.getBlock(), blockState) : new EntityBlockFormEvent(((EntityBridge)entity).bridge$getBukkitEntity(), blockState.getBlock(), blockState);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            blockState.update(true);
        }
        return !event.isCancelled();
    }

    @Overwrite
    public static BlockFadeEvent callBlockFadeEvent(LevelAccessor world, BlockPos pos, net.minecraft.world.level.block.state.BlockState newBlock) {
        if (!(world instanceof Level) || !DistValidate.isValid(world)) {
            return new BlockFadeEvent(CraftBlock.at(world, pos), CraftBlockStates.getBlockState(CraftMagicNumbers.getMaterial(newBlock.m_60734_()), null));
        }
        CraftBlockState state = CraftBlockStates.getBlockState(world, pos);
        state.setData(newBlock);
        BlockFadeEvent event = new BlockFadeEvent(state.getBlock(), state);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    @Overwrite
    public static BlockPhysicsEvent callBlockPhysicsEvent(LevelAccessor world, BlockPos blockposition) {
        CraftBlock block = CraftBlock.at(world, blockposition);
        BlockPhysicsEvent event = new BlockPhysicsEvent(block, block.getBlockData());
        if (world instanceof Level && DistValidate.isValid(world)) {
            Bukkit.getPluginManager().callEvent(event);
        }
        return event;
    }

    @Overwrite
    public static boolean callEntityChangeBlockEvent(Entity entity, BlockPos position, net.minecraft.world.level.block.state.BlockState newBlock, boolean cancelled) {
        CraftBlock block = CraftBlock.at((LevelAccessor)entity.m_9236_(), position);
        EntityChangeBlockEvent event = new EntityChangeBlockEvent(((EntityBridge)entity).bridge$getBukkitEntity(), block, CraftBlockData.fromData(newBlock));
        event.setCancelled(cancelled);
        if (DistValidate.isValid((LevelAccessor)entity.m_9236_())) {
            Bukkit.getPluginManager().callEvent(event);
        }
        return !event.isCancelled();
    }

    @Overwrite
    public static BlockRedstoneEvent callRedstoneChange(Level world, BlockPos pos, int oldCurrent, int newCurrent) {
        BlockRedstoneEvent event = new BlockRedstoneEvent(CraftBlock.at((LevelAccessor)world, pos), oldCurrent, newCurrent);
        if (DistValidate.isValid((LevelAccessor)world)) {
            Bukkit.getPluginManager().callEvent(event);
        }
        return event;
    }

    @Overwrite
    public static NotePlayEvent callNotePlayEvent(Level world, BlockPos pos, NoteBlockInstrument instrument, int note) {
        NotePlayEvent event = new NotePlayEvent(CraftBlock.at((LevelAccessor)world, pos), Instrument.getByType((byte)instrument.ordinal()), new Note(note));
        if (DistValidate.isValid((LevelAccessor)world)) {
            Bukkit.getPluginManager().callEvent(event);
        }
        return event;
    }

    @Inject(method={"callItemSpawnEvent"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$noAirDrops(ItemEntity itemEntity, CallbackInfoReturnable<ItemSpawnEvent> cir) {
        if (itemEntity.m_32055_().m_41619_()) {
            Item entity = (Item)((Object)((EntityBridge)itemEntity).bridge$getBukkitEntity());
            ItemSpawnEvent event = new ItemSpawnEvent(entity);
            event.setCancelled(true);
            cir.setReturnValue((Object)event);
        }
    }

    @Overwrite
    public static boolean callPlayerSignOpenEvent(Player player, SignBlockEntity tileEntitySign, boolean front, PlayerSignOpenEvent.Cause cause) {
        CraftSign<SignBlockEntity> sign1;
        CraftBlock block = CraftBlock.at((LevelAccessor)tileEntitySign.m_58904_(), tileEntitySign.m_58899_());
        BlockState blockState = CraftBlockStates.getBlockState(block);
        CraftSign<SignBlockEntity> sign = blockState instanceof Sign ? (sign1 = (CraftSign<SignBlockEntity>)blockState) : new CraftSign<SignBlockEntity>(((WorldBridge)tileEntitySign.m_58904_()).bridge$getWorld(), tileEntitySign);
        Side side = front ? Side.FRONT : Side.BACK;
        return CraftEventFactoryMixin.callPlayerSignOpenEvent(((ServerPlayerEntityBridge)player).bridge$getBukkitEntity(), sign, side, cause);
    }

    @Overwrite
    public static boolean callPlayerSignOpenEvent(org.bukkit.entity.Player player, Sign sign, Side side, PlayerSignOpenEvent.Cause cause) {
        PlayerSignOpenEvent event = new PlayerSignOpenEvent(player, sign, side, cause);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    @Inject(method={"callInventoryOpenEvent(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/inventory/AbstractContainerMenu;Z)Lnet/minecraft/world/inventory/AbstractContainerMenu;"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$asyncCheck(ServerPlayer player, AbstractContainerMenu container, boolean cancelled, CallbackInfoReturnable<AbstractContainerMenu> cir) {
        if (!Bukkit.isPrimaryThread()) {
            cir.setReturnValue((Object)container);
        }
    }
}

