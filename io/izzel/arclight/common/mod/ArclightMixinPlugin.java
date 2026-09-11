/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 *  org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
 *  org.spongepowered.asm.mixin.extensibility.IMixinInfo
 */
package io.izzel.arclight.common.mod;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import io.izzel.arclight.common.mod.mixins.ShouldApplyProcessor;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class ArclightMixinPlugin
implements IMixinConfigPlugin {
    private final Map<String, Map.Entry<List<FieldNode>, List<MethodNode>>> accessTransformer = ImmutableMap.builder().put((Object)"net.minecraft.world.level.Level", (Object)Maps.immutableEntry((Object)ImmutableList.of((Object)new FieldNode(9, "lastPhysicsProblem", "Lnet/minecraft/core/BlockPos;", null, null)), (Object)ImmutableList.of())).put((Object)"net.minecraft.server.MinecraftServer", (Object)Maps.immutableEntry((Object)ImmutableList.of((Object)new FieldNode(9, "currentTick", "I", null, null)), (Object)ImmutableList.of((Object)new MethodNode(9, "getServer", "()Lnet/minecraft/server/MinecraftServer;", null, null)))).put((Object)"net.minecraft.server.level.TicketType", (Object)Maps.immutableEntry((Object)ImmutableList.of((Object)new FieldNode(25, "PLUGIN", "Lnet/minecraft/server/level/TicketType;", null, null), (Object)new FieldNode(25, "PLUGIN_TICKET", "Lnet/minecraft/server/level/TicketType;", null, null)), (Object)ImmutableList.of())).put((Object)"net.minecraft.world.level.storage.loot.parameters.LootContextParams", (Object)Maps.immutableEntry((Object)ImmutableList.of((Object)new FieldNode(25, "LOOTING_MOD", "Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;", null, null)), (Object)ImmutableList.of())).put((Object)"net.minecraft.world.item.BlockItem", (Object)Maps.immutableEntry((Object)ImmutableList.of(), (Object)ImmutableList.of((Object)new MethodNode(9, "getBlockState", "(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/level/block/state/BlockState;", null, null)))).put((Object)"net.minecraft.world.entity.decoration.HangingEntity", (Object)Maps.immutableEntry((Object)ImmutableList.of(), (Object)ImmutableList.of((Object)new MethodNode(9, "calculateBoundingBox", "(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;II)Lnet/minecraft/world/phys/AABB;", null, null)))).put((Object)"net.minecraft.world.entity.decoration.ItemFrame", (Object)Maps.immutableEntry((Object)ImmutableList.of(), (Object)ImmutableList.of((Object)new MethodNode(9, "calculateBoundingBox", "(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;II)Lnet/minecraft/world/phys/AABB;", null, null)))).put((Object)"net.minecraft.server.commands.ReloadCommand", (Object)Maps.immutableEntry((Object)ImmutableList.of(), (Object)ImmutableList.of((Object)new MethodNode(9, "reload", "(Lnet/minecraft/server/MinecraftServer;)V", null, null)))).put((Object)"net.minecraft.world.entity.monster.Zombie", Map.entry(List.of(), List.of(new MethodNode(9, "zombifyVillager", "(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/npc/Villager;Lnet/minecraft/core/BlockPos;ZLorg/bukkit/event/entity/CreatureSpawnEvent$SpawnReason;)Lnet/minecraft/world/entity/monster/ZombieVillager;", null, null)))).put((Object)"net.minecraft.world.entity.item.FallingBlockEntity", Map.entry(List.of(), List.of(new MethodNode(9, "fall", "(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lorg/bukkit/event/entity/CreatureSpawnEvent$SpawnReason;)Lnet/minecraft/world/entity/item/FallingBlockEntity;", null, null)))).build();
    private final Set<String> modifyConstructor = ImmutableSet.builder().add((Object)"net.minecraft.world.level.Level").add((Object)"net.minecraft.server.level.ServerLevel").add((Object)"net.minecraft.world.SimpleContainer").add((Object)"net.minecraft.world.level.block.ComposterBlock").add((Object)"net.minecraft.world.level.block.ComposterBlock$EmptyContainer").add((Object)"net.minecraft.world.food.FoodData").add((Object)"net.minecraft.world.inventory.CraftingContainer").add((Object)"net.minecraft.world.inventory.PlayerEnderChestContainer").add((Object)"net.minecraft.world.item.trading.MerchantOffer").add((Object)"net.minecraft.world.inventory.LecternMenu").add((Object)"net.minecraft.server.level.ServerEntity").add((Object)"net.minecraft.network.protocol.game.ServerboundContainerClosePacket").add((Object)"net.minecraft.network.chat.TextColor").add((Object)"net.minecraft.commands.Commands").add((Object)"net.minecraft.world.level.storage.LevelStorageSource.LevelStorageAccess").add((Object)"net.minecraft.network.protocol.game.ClientboundSystemChatPacket").add((Object)"net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket").add((Object)"net.minecraft.network.protocol.status.ServerStatus").build();

    public void onLoad(String mixinPackage) {
    }

    public String getRefMapperConfig() {
        return null;
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return ShouldApplyProcessor.shouldApply(mixinClassName);
    }

    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    public List<String> getMixins() {
        return null;
    }

    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        Map.Entry<List<FieldNode>, List<MethodNode>> entry = this.accessTransformer.get(targetClassName);
        if (entry != null) {
            List<FieldNode> fields = entry.getKey();
            for (FieldNode fieldNode : targetClass.fields) {
                this.tryTransform(fields, fieldNode);
            }
            List<MethodNode> methods = entry.getValue();
            for (MethodNode methodNode : targetClass.methods) {
                this.tryTransform(methods, methodNode);
            }
        }
        this.modifyConstructor(targetClassName, targetClass);
    }

    private void modifyConstructor(String targetClassName, ClassNode classNode) {
        if (this.modifyConstructor.contains(targetClassName)) {
            HashSet<String> presentCtor = new HashSet<String>();
            HashSet<String> overrideCtor = new HashSet<String>();
            for (MethodNode method : classNode.methods) {
                if (method.name.equals("<init>")) {
                    presentCtor.add(method.desc);
                }
                if (!method.name.equals("arclight$constructor$override")) continue;
                overrideCtor.add(method.desc);
            }
            ListIterator iterator = classNode.methods.listIterator();
            while (iterator.hasNext()) {
                MethodNode methodNode = (MethodNode)iterator.next();
                if (methodNode.name.equals("arclight$constructor")) {
                    String desc = methodNode.desc;
                    if (presentCtor.contains(desc)) {
                        iterator.remove();
                    } else {
                        methodNode.name = "<init>";
                        presentCtor.add(methodNode.desc);
                        this.remapCtor(classNode, methodNode);
                    }
                }
                if (methodNode.name.equals("arclight$constructor$super")) {
                    iterator.remove();
                }
                if (methodNode.name.equals("<init>") && overrideCtor.contains(methodNode.desc)) {
                    iterator.remove();
                    continue;
                }
                if (!methodNode.name.equals("arclight$constructor$override")) continue;
                methodNode.name = "<init>";
                this.remapCtor(classNode, methodNode);
            }
        }
    }

    private void remapCtor(ClassNode classNode, MethodNode methodNode) {
        boolean initialized = false;
        for (AbstractInsnNode node : methodNode.instructions) {
            if (!(node instanceof MethodInsnNode)) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)node;
            if (methodInsnNode.name.equals("arclight$constructor")) {
                if (initialized) {
                    throw new ClassFormatError("Duplicate constructor call");
                }
                methodInsnNode.setOpcode(183);
                methodInsnNode.name = "<init>";
                initialized = true;
            }
            if (!methodInsnNode.name.equals("arclight$constructor$super")) continue;
            if (initialized) {
                throw new ClassFormatError("Duplicate constructor call");
            }
            methodInsnNode.setOpcode(183);
            methodInsnNode.owner = classNode.superName;
            methodInsnNode.name = "<init>";
            initialized = true;
        }
        if (!initialized) {
            if (classNode.superName.equals("java/lang/Object")) {
                InsnList insnList = new InsnList();
                insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
                insnList.add((AbstractInsnNode)new MethodInsnNode(183, "java/lang/Object", "<init>", "()V", false));
                methodNode.instructions.insert(insnList);
            } else {
                throw new ClassFormatError("No super constructor call present: " + classNode.name);
            }
        }
    }

    private void tryTransform(List<FieldNode> fields, FieldNode fieldNode) {
        for (FieldNode field : fields) {
            if (!Objects.equals(fieldNode.name, field.name) || !Objects.equals(fieldNode.desc, field.desc)) continue;
            fieldNode.access = field.access;
        }
    }

    private void tryTransform(List<MethodNode> methods, MethodNode methodNode) {
        for (MethodNode method : methods) {
            if (!Objects.equals(methodNode.name, method.name) || !Objects.equals(methodNode.desc, method.desc)) continue;
            methodNode.access = method.access;
        }
    }
}

