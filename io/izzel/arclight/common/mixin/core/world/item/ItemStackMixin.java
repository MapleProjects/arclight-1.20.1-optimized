/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.ArclightConfig
 *  net.minecraft.core.Holder$Reference
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.common.capabilities.CapabilityProvider
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.item.ItemStackBridge;
import io.izzel.arclight.i18n.ArclightConfig;
import java.util.Objects;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemStack.class})
public abstract class ItemStackMixin
extends CapabilityProvider<ItemStack>
implements ItemStackBridge {
    @Shadow
    @Deprecated
    private Item f_41589_;
    @Shadow
    private int f_41587_;
    @Shadow(remap=false)
    private CompoundTag capNBT;
    @Mutable
    @Shadow(remap=false)
    @Final
    private Holder.Reference<Item> delegate;
    private static final Logger LOG = LogManager.getLogger((String)"Arclight");

    protected ItemStackMixin(Class<ItemStack> baseClass) {
        super(baseClass);
    }

    @Override
    public CompoundTag bridge$getForgeCaps() {
        return this.serializeCaps();
    }

    @Override
    public void bridge$setForgeCaps(CompoundTag caps) {
        this.capNBT = caps;
        if (caps != null) {
            this.deserializeCaps(caps);
        }
    }

    public void convertStack(int version) {
        if (0 < version && version < CraftMagicNumbers.INSTANCE.getDataVersion()) {
            LOG.warn("Legacy ItemStack being used, updates will not applied: {}", (Object)this);
        }
    }

    @Override
    public void bridge$convertStack(int version) {
        this.convertStack(version);
    }

    @ModifyVariable(method={"hurt"}, index=1, at=@At(value="JUMP", opcode=157, ordinal=0))
    private int arclight$itemDamage(int i, int amount, RandomSource rand, ServerPlayer damager) {
        if (damager != null) {
            PlayerItemDamageEvent event = new PlayerItemDamageEvent(((ServerPlayerEntityBridge)damager).bridge$getBukkitEntity(), CraftItemStack.asCraftMirror((ItemStack)this), i);
            event.getPlayer().getServer().getPluginManager().callEvent(event);
            if (i != event.getDamage() || event.isCancelled()) {
                event.getPlayer().updateInventory();
            }
            if (event.isCancelled()) {
                return -1;
            }
            return event.getDamage();
        }
        return i;
    }

    @Inject(method={"hurtAndBreak"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/item/ItemStack;shrink(I)V")})
    private <T extends LivingEntity> void arclight$itemBreak(int amount, T entityIn, Consumer<T> onBroken, CallbackInfo ci) {
        if (this.f_41587_ == 1 && entityIn instanceof Player) {
            CraftEventFactory.callPlayerItemBreakEvent((Player)entityIn, (ItemStack)this);
        }
    }

    @Deprecated
    public void setItem(Item item) {
        this.f_41589_ = item;
        this.delegate = ForgeRegistries.ITEMS.getDelegateOrThrow((Object)item);
    }

    @Redirect(method={"isSameItemSameTags"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/Objects;equals(Ljava/lang/Object;Ljava/lang/Object;)Z"))
    private static boolean arclight$lenientItemMatch(Object a, Object b) {
        if (ArclightConfig.spec().getCompat().isLenientItemTagMatch()) {
            CompoundTag tagA = (CompoundTag)a;
            CompoundTag tagB = (CompoundTag)b;
            if (tagB != null) {
                CompoundTag tmp = tagA;
                tagA = tagB;
                tagB = tmp;
            }
            return tagA == null || (tagA.m_128456_() ? tagB == null || tagB.m_128456_() : tagA.equals((Object)tagB));
        }
        return Objects.equals(a, b);
    }
}

