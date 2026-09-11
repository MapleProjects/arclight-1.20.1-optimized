/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  io.izzel.arclight.i18n.LocalizedException
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec
 *  io.izzel.arclight.i18n.conf.MaterialPropertySpec$MaterialType
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FallingBlock
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.collect.ImmutableMap;
import io.izzel.arclight.common.bridge.bukkit.MaterialBridge;
import io.izzel.arclight.common.bridge.core.block.FireBlockBridge;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.i18n.LocalizedException;
import io.izzel.arclight.i18n.conf.MaterialPropertySpec;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraftforge.registries.ForgeRegistries;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaArmorStand;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBanner;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBlockState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBookSigned;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaCharge;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaCrossbow;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaEnchantedBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaFirework;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaKnowledgeBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaLeatherArmor;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaMap;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaPotion;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSkull;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSpawnEgg;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSuspiciousStew;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaTropicalFishBucket;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Material.class}, remap=false)
public abstract class MaterialMixin
implements MaterialBridge {
    @Shadow
    @Mutable
    @Final
    private NamespacedKey key;
    @Shadow
    @Mutable
    @Final
    private Constructor<? extends MaterialData> ctor;
    @Shadow
    @Mutable
    @Final
    public Class<?> data;
    private static final Map<String, BiFunction<Material, CraftMetaItem, ItemMeta>> TYPES = ImmutableMap.builder().put((Object)"ARMOR_STAND", (a, b) -> b instanceof CraftMetaArmorStand ? b : new CraftMetaArmorStand((CraftMetaItem)b)).put((Object)"BANNER", (a, b) -> b instanceof CraftMetaBanner ? b : new CraftMetaBanner((CraftMetaItem)b)).put((Object)"TILE_ENTITY", (a, b) -> new CraftMetaBlockState((CraftMetaItem)b, (Material)a)).put((Object)"BOOK", (a, b) -> b != null && b.getClass().equals(CraftMetaBook.class) ? b : new CraftMetaBook((CraftMetaItem)b)).put((Object)"BOOK_SIGNED", (a, b) -> b instanceof CraftMetaBookSigned ? b : new CraftMetaBookSigned((CraftMetaItem)b)).put((Object)"SKULL", (a, b) -> b instanceof CraftMetaSkull ? b : new CraftMetaSkull((CraftMetaItem)b)).put((Object)"LEATHER_ARMOR", (a, b) -> b instanceof CraftMetaLeatherArmor ? b : new CraftMetaLeatherArmor((CraftMetaItem)b)).put((Object)"MAP", (a, b) -> b instanceof CraftMetaMap ? b : new CraftMetaMap((CraftMetaItem)b)).put((Object)"POTION", (a, b) -> b instanceof CraftMetaPotion ? b : new CraftMetaPotion((CraftMetaItem)b)).put((Object)"SPAWN_EGG", (a, b) -> b instanceof CraftMetaSpawnEgg ? b : new CraftMetaSpawnEgg((CraftMetaItem)b)).put((Object)"ENCHANTED", (a, b) -> b instanceof CraftMetaEnchantedBook ? b : new CraftMetaEnchantedBook((CraftMetaItem)b)).put((Object)"FIREWORK", (a, b) -> b instanceof CraftMetaFirework ? b : new CraftMetaFirework((CraftMetaItem)b)).put((Object)"FIREWORK_EFFECT", (a, b) -> b instanceof CraftMetaCharge ? b : new CraftMetaCharge((CraftMetaItem)b)).put((Object)"KNOWLEDGE_BOOK", (a, b) -> b instanceof CraftMetaKnowledgeBook ? b : new CraftMetaKnowledgeBook((CraftMetaItem)b)).put((Object)"TROPICAL_FISH_BUCKET", (a, b) -> b instanceof CraftMetaTropicalFishBucket ? b : new CraftMetaTropicalFishBucket((CraftMetaItem)b)).put((Object)"CROSSBOW", (a, b) -> b instanceof CraftMetaCrossbow ? b : new CraftMetaCrossbow((CraftMetaItem)b)).put((Object)"SUSPICIOUS_STEW", (a, b) -> b instanceof CraftMetaSuspiciousStew ? b : new CraftMetaSuspiciousStew((CraftMetaItem)b)).put((Object)"UNSPECIFIC", (a, b) -> new CraftMetaItem((CraftMetaItem)b)).put((Object)"NULL", (a, b) -> null).build();
    private MaterialPropertySpec.MaterialType arclight$type = MaterialPropertySpec.MaterialType.VANILLA;
    private MaterialPropertySpec arclight$spec;
    private boolean arclight$block = false;
    private boolean arclight$item = false;
    private Function<CraftMetaItem, ItemMeta> arclight$metaFunc;
    private Function<CraftBlock, BlockState> arclight$stateFunc;

    @Shadow
    public abstract boolean isBlock();

    @Override
    public void bridge$setBlock() {
        this.arclight$block = true;
    }

    @Override
    public void bridge$setItem() {
        this.arclight$item = true;
    }

    @Inject(method={"isBlock"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isBlock(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$type != MaterialPropertySpec.MaterialType.VANILLA) {
            cir.setReturnValue((Object)this.arclight$block);
        }
    }

    @Inject(method={"isItem"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isItem(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$type != MaterialPropertySpec.MaterialType.VANILLA) {
            cir.setReturnValue((Object)this.arclight$item);
        }
    }

    @Inject(method={"isEdible"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isEdible(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.edible);
        }
    }

    @Inject(method={"isRecord"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isRecord(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.record);
        }
    }

    @Inject(method={"isSolid"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isSolid(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.solid);
        }
    }

    @Inject(method={"isAir"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isAir(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.air);
        }
    }

    @Inject(method={"isTransparent"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isTransparent(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.transparent);
        }
    }

    @Inject(method={"isFlammable"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isFlammable(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.flammable);
        }
    }

    @Inject(method={"isBurnable"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isBurnable(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.burnable);
        }
    }

    @Inject(method={"isFuel"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isFuel(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.fuel);
        }
    }

    @Inject(method={"isOccluding"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isOccluding(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.occluding);
        }
    }

    @Inject(method={"hasGravity"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$hasGravity(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.gravity);
        }
    }

    @Inject(method={"isInteractable"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$isInteractable(CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.interactable);
        }
    }

    @Inject(method={"getHardness"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$getHardness(CallbackInfoReturnable<Float> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.hardness);
        }
    }

    @Inject(method={"getBlastResistance"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$getBlastResistance(CallbackInfoReturnable<Float> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.blastResistance);
        }
    }

    @Inject(method={"getCraftingRemainingItem"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$getCraftingRemainingItem(CallbackInfoReturnable<Material> cir) {
        if (this.arclight$spec != null && this.arclight$spec.craftingRemainingItem != null) {
            cir.setReturnValue((Object)CraftMagicNumbers.getMaterial((Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.arclight$spec.craftingRemainingItem))));
        }
    }

    @Inject(method={"getMaxStackSize"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$getMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        if (this.arclight$spec != null) {
            cir.setReturnValue((Object)this.arclight$spec.maxStack);
        }
    }

    @Inject(method={"getMaxDurability"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$getMaxDurability(CallbackInfoReturnable<Short> cir) {
        if (this.arclight$spec != null && this.arclight$spec.maxDurability != null) {
            cir.setReturnValue((Object)this.arclight$spec.maxDurability.shortValue());
        }
    }

    @Override
    public MaterialPropertySpec bridge$getSpec() {
        return this.arclight$spec;
    }

    @Override
    public MaterialPropertySpec.MaterialType bridge$getType() {
        return this.arclight$type;
    }

    @Override
    public Function<CraftMetaItem, ItemMeta> bridge$itemMetaFactory() {
        return this.arclight$metaFunc;
    }

    @Override
    public void bridge$setItemMetaFactory(Function<CraftMetaItem, ItemMeta> func) {
        this.arclight$metaFunc = func;
    }

    @Override
    public Function<CraftBlock, BlockState> bridge$blockStateFactory() {
        return this.arclight$stateFunc;
    }

    @Override
    public void bridge$setBlockStateFactory(Function<CraftBlock, BlockState> func) {
        this.arclight$stateFunc = func;
    }

    @Override
    public void bridge$setupBlock(ResourceLocation key, Block block, MaterialPropertySpec spec) {
        this.arclight$spec = spec.clone();
        this.arclight$type = MaterialPropertySpec.MaterialType.FORGE;
        this.arclight$block = true;
        this.arclight$setupCommon(key, block, block.m_5456_());
    }

    @Override
    public void bridge$setupVanillaBlock(MaterialPropertySpec spec) {
        if (spec != MaterialPropertySpec.EMPTY) {
            this.arclight$spec = spec.clone();
            this.setupBlockStateFunc();
        }
    }

    @Override
    public void bridge$setupItem(ResourceLocation key, Item item, MaterialPropertySpec spec) {
        this.arclight$spec = spec.clone();
        this.arclight$type = MaterialPropertySpec.MaterialType.FORGE;
        this.arclight$item = true;
        this.arclight$setupCommon(key, null, item);
    }

    @Override
    public boolean bridge$shouldApplyStateFactory() {
        return this.arclight$type != MaterialPropertySpec.MaterialType.VANILLA || this.arclight$spec != null && this.arclight$spec.blockStateClass != null;
    }

    private void arclight$setupCommon(ResourceLocation key, Block block, Item item) {
        BiFunction<Material, CraftMetaItem, ItemMeta> function;
        this.key = CraftNamespacedKey.fromMinecraft(key);
        if (this.arclight$spec.materialDataClass != null) {
            try {
                Class<?> data = Class.forName(this.arclight$spec.materialDataClass);
                if (MaterialData.class.isAssignableFrom(data)) {
                    this.data = data;
                    this.ctor = data.getConstructor(Material.class, Byte.TYPE);
                }
            }
            catch (Exception e) {
                ArclightMod.LOGGER.warn("Bad material data class {} for {}", (Object)this.arclight$spec.materialDataClass, (Object)this);
                ArclightMod.LOGGER.warn((Object)e);
            }
        }
        if (this.arclight$spec.maxStack == null) {
            this.arclight$spec.maxStack = MaterialMixin.tryGetMaxStackSize(item);
        }
        if (this.arclight$spec.maxDurability == null) {
            this.arclight$spec.maxDurability = MaterialMixin.tryGetDurability(item);
        }
        if (this.arclight$spec.edible == null) {
            this.arclight$spec.edible = false;
        }
        if (this.arclight$spec.record == null) {
            this.arclight$spec.record = false;
        }
        if (this.arclight$spec.solid == null) {
            this.arclight$spec.solid = block != null && block.m_49966_().m_60815_();
        }
        if (this.arclight$spec.air == null) {
            this.arclight$spec.air = block != null && block.m_49966_().m_60795_();
        }
        if (this.arclight$spec.transparent == null) {
            this.arclight$spec.transparent = block != null && block.m_49966_().m_60787_();
        }
        if (this.arclight$spec.flammable == null) {
            this.arclight$spec.flammable = block != null && ((FireBlockBridge)Blocks.f_50083_).bridge$canBurn(block);
        }
        if (this.arclight$spec.burnable == null) {
            this.arclight$spec.burnable = block != null && ((FireBlockBridge)Blocks.f_50083_).bridge$canBurn(block);
        }
        if (this.arclight$spec.fuel == null) {
            this.arclight$spec.fuel = item != null && new ItemStack((ItemLike)item).getBurnTime(null) > 0;
        }
        if (this.arclight$spec.occluding == null) {
            this.arclight$spec.occluding = this.arclight$spec.solid;
        }
        if (this.arclight$spec.gravity == null) {
            this.arclight$spec.gravity = block instanceof FallingBlock;
        }
        if (this.arclight$spec.interactable == null) {
            this.arclight$spec.interactable = true;
        }
        if (this.arclight$spec.hardness == null) {
            this.arclight$spec.hardness = Float.valueOf(block != null ? block.m_49966_().f_60599_ : 0.0f);
        }
        if (this.arclight$spec.blastResistance == null) {
            this.arclight$spec.blastResistance = Float.valueOf(block != null ? block.m_7325_() : 0.0f);
        }
        if (this.arclight$spec.craftingRemainingItem == null) {
            String string = this.arclight$spec.craftingRemainingItem = item != null && item.m_41470_() ? ForgeRegistries.ITEMS.getKey((Object)item.m_41469_()).toString() : null;
        }
        if (this.arclight$spec.itemMetaType == null) {
            this.arclight$spec.itemMetaType = "UNSPECIFIC";
        }
        this.arclight$metaFunc = (function = TYPES.get(this.arclight$spec.itemMetaType)) != null ? meta -> (ItemMeta)function.apply((Material)((Object)this), (CraftMetaItem)meta) : this.dynamicMetaCreator(this.arclight$spec.itemMetaType);
        this.setupBlockStateFunc();
    }

    private void setupBlockStateFunc() {
        if (this.arclight$spec.blockStateClass != null && !this.arclight$spec.blockStateClass.equalsIgnoreCase("auto")) {
            try {
                Class<?> cl = Class.forName(this.arclight$spec.blockStateClass);
                if (!CraftBlockState.class.isAssignableFrom(cl)) {
                    throw LocalizedException.checked((String)"registry.block-state.not-subclass", (Object[])new Object[]{cl, CraftBlockState.class});
                }
                for (Constructor<?> constructor : cl.getDeclaredConstructors()) {
                    if (constructor.getParameterTypes().length != 1 || !org.bukkit.block.Block.class.isAssignableFrom(constructor.getParameterTypes()[0])) continue;
                    constructor.setAccessible(true);
                    this.arclight$stateFunc = b -> {
                        try {
                            return (BlockState)constructor.newInstance(b);
                        }
                        catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    };
                }
            }
            catch (Exception e) {
                if (e instanceof LocalizedException) {
                    ArclightMod.LOGGER.warn(((LocalizedException)e).node(), ((LocalizedException)e).args());
                }
                ArclightMod.LOGGER.warn("registry.block-state.error", (Object)this, (Object)this.arclight$spec.blockStateClass, (Object)e);
            }
            if (this.arclight$stateFunc == null) {
                ArclightMod.LOGGER.warn("registry.block-state.no-candidate", (Object)this, (Object)this.arclight$spec.blockStateClass);
            }
        }
        if (this.arclight$stateFunc == null) {
            this.arclight$stateFunc = CraftBlockStates::getBlockState;
        }
    }

    private Function<CraftMetaItem, ItemMeta> dynamicMetaCreator(String type) {
        Function<CraftMetaItem, ItemMeta> candidate = null;
        try {
            Class<?> cl = Class.forName(type);
            if (!CraftMetaItem.class.isAssignableFrom(cl)) {
                throw LocalizedException.checked((String)"registry.meta-type.not-subclass", (Object[])new Object[]{cl, CraftMetaItem.class});
            }
            for (Constructor<?> constructor : cl.getDeclaredConstructors()) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 1) {
                    if (parameterTypes[0] == Material.class) {
                        constructor.setAccessible(true);
                        candidate = meta -> {
                            try {
                                return (ItemMeta)constructor.newInstance(this);
                            }
                            catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        };
                    } else {
                        if (!CraftMetaItem.class.isAssignableFrom(parameterTypes[0])) continue;
                        constructor.setAccessible(true);
                        candidate = meta -> {
                            try {
                                return (ItemMeta)constructor.newInstance(meta);
                            }
                            catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        };
                    }
                } else {
                    if (parameterTypes.length != 2) continue;
                    if (parameterTypes[0] == Material.class && CraftMetaItem.class.isAssignableFrom(parameterTypes[1])) {
                        constructor.setAccessible(true);
                        candidate = meta -> {
                            try {
                                return (ItemMeta)constructor.newInstance(this, meta);
                            }
                            catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        };
                    } else {
                        if (parameterTypes[1] != Material.class || !CraftMetaItem.class.isAssignableFrom(parameterTypes[0])) continue;
                        constructor.setAccessible(true);
                        candidate = meta -> {
                            try {
                                return (ItemMeta)constructor.newInstance(meta, this);
                            }
                            catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        };
                    }
                }
                break;
            }
        }
        catch (Exception e) {
            if (e instanceof LocalizedException) {
                ArclightMod.LOGGER.warn(((LocalizedException)e).node(), ((LocalizedException)e).args());
            }
            ArclightMod.LOGGER.warn("registry.meta-type.error", (Object)this, (Object)type, (Object)e);
        }
        if (candidate == null) {
            ArclightMod.LOGGER.warn("registry.meta-type.no-candidate", (Object)this, (Object)type);
            candidate = CraftMetaItem::new;
        }
        return candidate;
    }

    private static int tryGetMaxStackSize(Item item) {
        try {
            return item.getMaxStackSize(new ItemStack((ItemLike)item));
        }
        catch (Throwable t) {
            try {
                return item.m_41459_();
            }
            catch (Throwable t1) {
                return 64;
            }
        }
    }

    private static int tryGetDurability(Item item) {
        try {
            return item.getMaxDamage(new ItemStack((ItemLike)item));
        }
        catch (Throwable t) {
            try {
                return item.m_41462_();
            }
            catch (Throwable t1) {
                return 0;
            }
        }
    }
}

