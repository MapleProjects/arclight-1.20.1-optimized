/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.HolderGetter
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.levelgen.structure.templatesystem;

import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={StructureTemplate.class})
public class StructureTemplateMixin {
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    public CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);

    @Inject(method={"save"}, at={@At(value="RETURN")})
    private void arclight$savePdc(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        if (!this.persistentDataContainer.isEmpty()) {
            tag.m_128365_("BukkitValues", (Tag)this.persistentDataContainer.toTagCompound());
        }
    }

    @Inject(method={"load"}, at={@At(value="RETURN")})
    private void arclight$loadPdc(HolderGetter<Block> reg, CompoundTag tag, CallbackInfo ci) {
        Tag base = tag.m_128423_("BukkitValues");
        if (base instanceof CompoundTag) {
            CompoundTag compoundTag = (CompoundTag)base;
            this.persistentDataContainer.putAll(compoundTag);
        }
    }
}

