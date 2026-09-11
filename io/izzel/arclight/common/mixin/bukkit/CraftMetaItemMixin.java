/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtIo
 *  net.minecraft.nbt.Tag
 *  org.apache.commons.codec.binary.Base64
 *  org.apache.logging.log4j.LogManager
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.izzel.arclight.common.bridge.bukkit.ItemMetaBridge;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={CraftMetaItem.class}, remap=false)
public class CraftMetaItemMixin
implements ItemMetaBridge {
    @Shadow(remap=false)
    @Final
    private Map<String, Tag> unhandledTags;
    @Shadow(remap=false)
    private CompoundTag internalTag;
    private static final Set<String> EXTEND_TAGS = ImmutableSet.of((Object)"map_is_scaling", (Object)"map", (Object)"CustomPotionEffects", (Object)"Potion", (Object)"CustomPotionColor", (Object)"SkullOwner", (Object[])new String[]{"SkullProfile", "EntityTag", "BlockEntityTag", "title", "author", "pages", "resolved", "generation", "Fireworks", "StoredEnchantments", "Explosion", "Recipes", "BucketVariantTag", "Charged", "ChargedProjectiles", "Effects", "LodestoneDimension", "LodestonePos", "LodestoneTracked", "Items", "instrument"});
    private CompoundTag forgeCaps;

    @ModifyVariable(method={"<init>(Lnet/minecraft/nbt/CompoundTag;)V"}, at=@At(value="INVOKE", target="Lorg/bukkit/UnsafeValues;getDataVersion()I"))
    private CompoundTag arclight$provideTag(CompoundTag tag) {
        return tag == null ? new CompoundTag() : tag;
    }

    @Redirect(method={"<init>(Ljava/util/Map;)V"}, at=@At(value="INVOKE", target="Ljava/util/Set;contains(Ljava/lang/Object;)Z"))
    private boolean arclight$forceDeserializeInternalTags(Set<String> handledTags, Object key) {
        if (this instanceof CraftMetaItem) {
            return false;
        }
        return handledTags.contains((String)key);
    }

    @Override
    public CompoundTag bridge$getForgeCaps() {
        return this.forgeCaps;
    }

    @Override
    public void bridge$setForgeCaps(CompoundTag nbt) {
        this.forgeCaps = nbt;
    }

    @Override
    public void bridge$offerUnhandledTags(CompoundTag nbt) {
        if (this.getClass().equals(CraftMetaItem.class)) {
            for (String s : nbt.m_128431_()) {
                if (!EXTEND_TAGS.contains(s)) continue;
                this.unhandledTags.put(s, nbt.m_128423_(s));
            }
        }
    }

    @Override
    public Map<String, Tag> bridge$getUnhandledTags() {
        return this.unhandledTags;
    }

    @Override
    public void bridge$setUnhandledTags(Map<String, Tag> tags) {
        this.unhandledTags.putAll(tags);
    }

    @Inject(method={"serialize(Lcom/google/common/collect/ImmutableMap$Builder;)Lcom/google/common/collect/ImmutableMap$Builder;"}, at={@At(value="RETURN")})
    private void arclight$serializeForgeCaps(ImmutableMap.Builder<String, Object> builder, CallbackInfoReturnable<ImmutableMap.Builder<String, Object>> cir) throws IOException {
        if (this.forgeCaps != null) {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            NbtIo.m_128947_((CompoundTag)this.forgeCaps, (OutputStream)buf);
            builder.put((Object)"forgeCaps", (Object)Base64.encodeBase64String((byte[])buf.toByteArray()));
        }
    }

    @Inject(method={"clone"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")})
    private void arclight$cloneTags(CallbackInfoReturnable<CraftMetaItem> cir, CraftMetaItem clone) {
        if (this.unhandledTags != null) {
            ((ItemMetaBridge)((Object)clone)).bridge$getUnhandledTags().putAll(this.unhandledTags);
        }
        if (this.forgeCaps != null) {
            ((ItemMetaBridge)((Object)clone)).bridge$setForgeCaps(this.forgeCaps.m_6426_());
        }
    }

    @ModifyVariable(method={"applyHash"}, index=1, at=@At(value="RETURN"))
    private int arclight$applyForgeCapsHash(int hash) {
        return 61 * hash + (this.forgeCaps != null ? this.forgeCaps.hashCode() : 0);
    }

    @Inject(method={"isEmpty"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$forgeCapsEmpty(CallbackInfoReturnable<Boolean> cir) {
        if (this.forgeCaps != null && !this.forgeCaps.m_128456_()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"equalsCommon"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$forgeCapsEquals(CraftMetaItem that, CallbackInfoReturnable<Boolean> cir) {
        boolean ret;
        CompoundTag forgeCaps = ((ItemMetaBridge)((Object)that)).bridge$getForgeCaps();
        if (this.forgeCaps == null) {
            ret = forgeCaps != null && forgeCaps.m_128440_() != 0;
        } else {
            boolean bl = forgeCaps == null ? this.forgeCaps.m_128440_() != 0 : (ret = !this.forgeCaps.equals((Object)forgeCaps));
        }
        if (ret) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"<init>(Ljava/util/Map;)V"}, at={@At(value="RETURN")})
    private void arclight$extractForgeCaps(Map<String, Object> map, CallbackInfo ci) {
        if (map.containsKey("forgeCaps")) {
            Object forgeCaps = map.get("forgeCaps");
            try {
                ByteArrayInputStream buf = new ByteArrayInputStream(Base64.decodeBase64((String)forgeCaps.toString()));
                this.forgeCaps = NbtIo.m_128939_((InputStream)buf);
            }
            catch (IOException e) {
                LogManager.getLogger(this.getClass()).error("Reading forge caps", (Throwable)e);
            }
        }
    }

    @Inject(method={"<init>*"}, at={@At(value="RETURN")})
    private void arclight$copyForgeCaps(CraftMetaItem meta, CallbackInfo ci) {
        CompoundTag forgeCaps;
        if (meta != null && (forgeCaps = ((ItemMetaBridge)((Object)meta)).bridge$getForgeCaps()) != null) {
            this.forgeCaps = forgeCaps.m_6426_();
        }
    }
}

