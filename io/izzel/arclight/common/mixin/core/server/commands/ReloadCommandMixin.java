/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.commands.ReloadCommand
 *  net.minecraft.server.packs.repository.PackRepository
 *  net.minecraft.world.level.storage.WorldData
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import java.util.Collection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.ReloadCommand;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ReloadCommand.class})
public abstract class ReloadCommandMixin {
    @Shadow
    private static Collection<String> m_138222_(PackRepository p_241058_0_, WorldData p_241058_1_, Collection<String> p_241058_2_) {
        return null;
    }

    private static void reload(MinecraftServer minecraftserver) {
        PackRepository resourcePackList = minecraftserver.m_129891_();
        WorldData configuration = minecraftserver.m_129910_();
        Collection collection = resourcePackList.m_10523_();
        Collection<String> collection2 = ReloadCommandMixin.m_138222_(resourcePackList, configuration, collection);
        minecraftserver.m_129861_(collection2);
    }
}

