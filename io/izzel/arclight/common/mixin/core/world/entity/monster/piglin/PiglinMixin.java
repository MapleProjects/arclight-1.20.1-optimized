/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.DefaultedRegistry
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.monster.piglin.Piglin
 *  net.minecraft.world.entity.monster.piglin.PiglinAi
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster.piglin;

import io.izzel.arclight.common.bridge.core.entity.monster.piglin.PiglinBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Piglin.class})
public abstract class PiglinMixin
extends PathfinderMobMixin
implements PiglinBridge {
    public Set<Item> allowedBarterItems = new HashSet<Item>();
    public Set<Item> interestItems = new HashSet<Item>();

    @Override
    public Set<Item> bridge$getAllowedBarterItems() {
        return this.allowedBarterItems;
    }

    @Override
    public Set<Item> bridge$getInterestItems() {
        return this.interestItems;
    }

    @Inject(method={"addAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$writeAdditional(CompoundTag compound, CallbackInfo ci) {
        ListTag barterList = new ListTag();
        this.allowedBarterItems.stream().map(arg_0 -> ((DefaultedRegistry)BuiltInRegistries.f_257033_).m_7981_(arg_0)).map(ResourceLocation::toString).map(StringTag::m_129297_).forEach(arg_0 -> barterList.add(arg_0));
        compound.m_128365_("Bukkit.BarterList", (Tag)barterList);
        ListTag interestList = new ListTag();
        this.interestItems.stream().map(arg_0 -> ((DefaultedRegistry)BuiltInRegistries.f_257033_).m_7981_(arg_0)).map(ResourceLocation::toString).map(StringTag::m_129297_).forEach(arg_0 -> interestList.add(arg_0));
        compound.m_128365_("Bukkit.InterestList", (Tag)interestList);
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$readAdditional(CompoundTag compound, CallbackInfo ci) {
        this.allowedBarterItems = compound.m_128437_("Bukkit.BarterList", 8).stream().map(Tag::m_7916_).map(ResourceLocation::m_135820_).map(arg_0 -> ((DefaultedRegistry)BuiltInRegistries.f_257033_).m_7745_(arg_0)).collect(Collectors.toCollection(HashSet::new));
        this.interestItems = compound.m_128437_("Bukkit.InterestList", 8).stream().map(Tag::m_7916_).map(ResourceLocation::m_135820_).map(arg_0 -> ((DefaultedRegistry)BuiltInRegistries.f_257033_).m_7745_(arg_0)).collect(Collectors.toCollection(HashSet::new));
    }

    @Redirect(method={"holdInOffHand"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/ItemStack;isPiglinCurrency()Z"))
    private boolean arclight$customBarter(ItemStack itemStack) {
        return itemStack.isPiglinCurrency() || this.allowedBarterItems.contains(itemStack.m_41720_());
    }

    @Redirect(method={"canReplaceCurrentItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/piglin/PiglinAi;isLovedItem(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean arclight$customLoved(ItemStack stack) {
        return PiglinAi.m_149965_((ItemStack)stack) || this.interestItems.contains(stack.m_41720_()) || this.allowedBarterItems.contains(stack.m_41720_());
    }
}

