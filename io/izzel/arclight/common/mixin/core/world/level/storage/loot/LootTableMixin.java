/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.LootContext$Builder
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.storage.loot;

import io.izzel.arclight.common.bridge.core.world.storage.loot.LootDataManagerBridge;
import io.izzel.arclight.common.bridge.core.world.storage.loot.LootTableBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import io.izzel.arclight.mixin.Eject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.world.LootGenerateEvent;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LootTable.class})
public abstract class LootTableMixin
implements LootTableBridge {
    @Shadow
    @Final
    @Nullable
    private ResourceLocation f_286958_;
    @Shadow
    @Final
    static Logger f_79107_;

    @Shadow
    protected abstract ObjectArrayList<ItemStack> m_230922_(LootContext var1);

    @Shadow
    protected abstract List<Integer> m_230919_(Container var1, RandomSource var2);

    @Shadow
    protected abstract void m_230924_(ObjectArrayList<ItemStack> var1, int var2, RandomSource var3);

    @Eject(method={"fill"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"))
    private ObjectArrayList<ItemStack> arclight$nonPluginEvent(LootTable lootTable, LootContext context, CallbackInfo ci, Container inv) {
        ObjectArrayList<ItemStack> list = this.m_230922_(context);
        if (!context.m_78936_(LootContextParams.f_81460_) && !context.m_78936_(LootContextParams.f_81455_)) {
            return list;
        }
        if (!((LootDataManagerBridge)ArclightServer.getMinecraftServer().m_278653_()).bridge$isRegistered((LootTable)this)) {
            return list;
        }
        LootGenerateEvent event = CraftEventFactory.callLootGenerateEvent(inv, (LootTable)this, context, list, false);
        if (event.isCancelled()) {
            ci.cancel();
            return null;
        }
        return (ObjectArrayList)event.getLoot().stream().map(CraftItemStack::asNMSCopy).collect(ObjectArrayList.toList());
    }

    public void fillInventory(Container inv, LootParams lootparams, long i, boolean plugin) {
        LootContext context = new LootContext.Builder(lootparams).m_78965_(i).m_287259_(this.f_286958_);
        ObjectArrayList objectarraylist = this.m_230922_(context);
        RandomSource randomsource = context.m_230907_();
        if (((LootDataManagerBridge)ArclightServer.getMinecraftServer().m_278653_()).bridge$isRegistered((LootTable)this)) {
            LootGenerateEvent event = CraftEventFactory.callLootGenerateEvent(inv, (LootTable)this, context, objectarraylist, plugin);
            if (event.isCancelled()) {
                return;
            }
            objectarraylist = (ObjectArrayList)event.getLoot().stream().map(CraftItemStack::asNMSCopy).collect(ObjectArrayList.toList());
        }
        List<Integer> list = this.m_230919_(inv, randomsource);
        this.m_230924_(objectarraylist, list.size(), randomsource);
        for (ItemStack itemstack : objectarraylist) {
            if (list.isEmpty()) {
                f_79107_.warn("Tried to over-fill a container");
                return;
            }
            if (itemstack.m_41619_()) {
                inv.m_6836_(list.remove(list.size() - 1).intValue(), ItemStack.f_41583_);
                continue;
            }
            inv.m_6836_(list.remove(list.size() - 1).intValue(), itemstack);
        }
    }
}

