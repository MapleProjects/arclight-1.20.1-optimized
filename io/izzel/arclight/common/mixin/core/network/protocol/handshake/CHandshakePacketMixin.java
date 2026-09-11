/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.mojang.authlib.properties.Property
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.protocol.handshake.ClientIntentionPacket
 *  net.minecraftforge.network.NetworkHooks
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.network.protocol.handshake;

import com.google.gson.Gson;
import com.mojang.authlib.properties.Property;
import java.util.Objects;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraftforge.network.NetworkHooks;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientIntentionPacket.class})
public class CHandshakePacketMixin {
    private static final String EXTRA_DATA = "extraData";
    private static final Gson GSON = new Gson();
    @Shadow
    public String f_134721_;
    private transient String arclight$host;

    @Redirect(method={"<init>(Lnet/minecraft/network/FriendlyByteBuf;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/network/FriendlyByteBuf;readUtf(I)Ljava/lang/String;"))
    private String arclight$bungeeHostname(FriendlyByteBuf packetBuffer, int maxLength) {
        return packetBuffer.m_130136_(Short.MAX_VALUE);
    }

    @Redirect(method={"<init>(Lnet/minecraft/network/FriendlyByteBuf;)V"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraftforge/network/NetworkHooks;getFMLVersion(Ljava/lang/String;)Ljava/lang/String;"))
    private String arclight$readFromProfile(String ip) {
        String[] split;
        String fmlVersion = NetworkHooks.getFMLVersion((String)ip);
        if (SpigotConfig.bungee && !Objects.equals(fmlVersion, "FML3") && (split = ip.split("\u0000")).length == 4) {
            Property[] properties;
            for (Property property : properties = (Property[])GSON.fromJson(split[3], Property[].class)) {
                if (!Objects.equals(property.getName(), EXTRA_DATA)) continue;
                String extraData = property.getValue().replace("\u0001", "\u0000");
                this.arclight$host = ip;
                return NetworkHooks.getFMLVersion((String)(split[0] + extraData));
            }
        }
        return fmlVersion;
    }

    @Inject(method={"<init>(Lnet/minecraft/network/FriendlyByteBuf;)V"}, at={@At(value="RETURN")})
    private void arclight$writeBack(FriendlyByteBuf p_179801_, CallbackInfo ci) {
        if (this.arclight$host != null) {
            this.f_134721_ = this.arclight$host;
            this.arclight$host = null;
        }
    }
}

