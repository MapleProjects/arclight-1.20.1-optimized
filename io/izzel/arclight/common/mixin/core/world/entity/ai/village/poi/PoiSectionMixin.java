/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.village.poi.PoiSection
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.village.poi;

import net.minecraft.world.entity.ai.village.poi.PoiSection;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={PoiSection.class})
public abstract class PoiSectionMixin {
    @Redirect(method={"m_27279_", "remove"}, at=@At(value="INVOKE", target="Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", remap=false), require=0)
    private void arclight$silencePoiMismatch(Logger logger, String string, Object object) {
    }
}

