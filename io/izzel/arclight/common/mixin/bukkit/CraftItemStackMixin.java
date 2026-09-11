/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.CraftItemStackBridge;
import io.izzel.arclight.common.bridge.bukkit.ItemMetaBridge;
import io.izzel.arclight.common.bridge.core.item.ItemStackBridge;
import java.util.Objects;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.legacy.CraftLegacy;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftItemStack.class}, remap=false)
public abstract class CraftItemStackMixin
implements CraftItemStackBridge {
    @Shadow
    net.minecraft.world.item.ItemStack handle;

    @Shadow
    public abstract Material getType();

    @Shadow
    public abstract short getDurability();

    @Shadow
    public abstract boolean hasItemMeta();

    @Shadow
    static Material getType(net.minecraft.world.item.ItemStack item) {
        throw new RuntimeException();
    }

    @Inject(method={"getItemMeta(Lnet/minecraft/world/item/ItemStack;)Lorg/bukkit/inventory/meta/ItemMeta;"}, cancellable=true, at={@At(value="INVOKE", target="Lorg/bukkit/Material;ordinal()I")})
    private static void arclight$noTag(net.minecraft.world.item.ItemStack item, CallbackInfoReturnable<ItemMeta> cir) {
        if (item.m_41783_() == null) {
            ItemMeta meta = CraftItemFactory.instance().getItemMeta(CraftItemStackMixin.getType(item));
            ((ItemMetaBridge)((Object)meta)).bridge$setForgeCaps(((ItemStackBridge)item).bridge$getForgeCaps());
            cir.setReturnValue((Object)meta);
        }
    }

    @Inject(method={"getItemMeta(Lnet/minecraft/world/item/ItemStack;)Lorg/bukkit/inventory/meta/ItemMeta;"}, at={@At(value="RETURN")})
    private static void arclight$offerCaps(net.minecraft.world.item.ItemStack item, CallbackInfoReturnable<ItemMeta> cir) {
        if (item == null) {
            return;
        }
        ItemMeta meta = (ItemMeta)cir.getReturnValue();
        CompoundTag tag = item.m_41783_();
        if (tag != null) {
            ((ItemMetaBridge)((Object)meta)).bridge$offerUnhandledTags(tag);
        }
        ((ItemMetaBridge)((Object)meta)).bridge$setForgeCaps(((ItemStackBridge)item).bridge$getForgeCaps());
    }

    @Inject(method={"setItemMeta(Lnet/minecraft/world/item/ItemStack;Lorg/bukkit/inventory/meta/ItemMeta;)Z"}, at={@At(value="INVOKE", ordinal=1, remap=true, target="Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;")})
    private static void arclight$setCaps(net.minecraft.world.item.ItemStack item, ItemMeta itemMeta, CallbackInfoReturnable<Boolean> cir) {
        CompoundTag forgeCaps = ((ItemMetaBridge)((Object)itemMeta)).bridge$getForgeCaps();
        if (forgeCaps != null) {
            ((ItemStackBridge)item).bridge$setForgeCaps(forgeCaps.m_6426_());
        }
    }

    @Overwrite
    public boolean isSimilar(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack == this) {
            return true;
        }
        if (!(stack instanceof CraftItemStack)) {
            return stack.getClass() == ItemStack.class && stack.isSimilar((ItemStack)((Object)this));
        }
        CraftItemStack that = (CraftItemStack)stack;
        if (this.handle == ((CraftItemStackBridge)((Object)that)).bridge$getHandle()) {
            return true;
        }
        if (this.handle == null || ((CraftItemStackBridge)((Object)that)).bridge$getHandle() == null) {
            return false;
        }
        Material comparisonType = CraftLegacy.fromLegacy(that.getType());
        if (comparisonType != this.getType() || this.getDurability() != that.getDurability()) {
            return false;
        }
        return this.hasItemMeta() ? that.hasItemMeta() && Objects.equals(this.handle.m_41783_(), ((CraftItemStackBridge)((Object)that)).bridge$getHandle().m_41783_()) && Objects.equals(((ItemStackBridge)this.handle).bridge$getForgeCaps(), ((ItemStackBridge)((CraftItemStackBridge)((Object)that)).bridge$getHandle()).bridge$getForgeCaps()) : !that.hasItemMeta();
    }

    @Inject(method={"hasItemMeta(Lnet/minecraft/world/item/ItemStack;)Z"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$hasMeta(net.minecraft.world.item.ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        CompoundTag forgeCaps;
        if (item != null && (forgeCaps = ((ItemStackBridge)item).bridge$getForgeCaps()) != null && !forgeCaps.m_128456_()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Override
    public net.minecraft.world.item.ItemStack bridge$getHandle() {
        return this.handle;
    }
}

