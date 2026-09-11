/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Stopwatch
 *  com.google.common.base.Ticker
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.Util
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.arguments.ResourceOrTagArgument$Result
 *  net.minecraft.commands.arguments.ResourceOrTagKeyArgument$Result
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.HolderSet
 *  net.minecraft.core.HolderSet$ListBacked
 *  net.minecraft.core.Position
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.commands.LocateCommand
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.ai.village.poi.PoiManager$Occupancy
 *  net.minecraft.world.entity.ai.village.poi.PoiType
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.datafixers.util.Pair;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagArgument;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Position;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.LocateCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LocateCommand.class})
public abstract class LocateCommandMixin {
    @Shadow
    @Final
    private static DynamicCommandExceptionType f_214452_;
    @Shadow
    @Final
    private static DynamicCommandExceptionType f_214451_;
    @Shadow
    @Final
    private static DynamicCommandExceptionType f_214453_;
    @Shadow
    @Final
    private static DynamicCommandExceptionType f_214455_;

    @Shadow
    private static Optional<? extends HolderSet.ListBacked<Structure>> m_245526_(ResourceOrTagKeyArgument.Result<Structure> p_251212_, Registry<Structure> p_249691_) {
        return null;
    }

    @Shadow
    public static int m_262810_(CommandSourceStack p_263019_, ResourceOrTagKeyArgument.Result<?> p_263031_, BlockPos p_262989_, Pair<BlockPos, ? extends Holder<?>> p_262959_, String p_263045_, boolean p_262934_, Duration p_262960_) {
        return 0;
    }

    @Shadow
    public static int m_262830_(CommandSourceStack p_263098_, ResourceOrTagArgument.Result<?> p_262956_, BlockPos p_262917_, Pair<BlockPos, ? extends Holder<?>> p_263074_, String p_262937_, boolean p_263051_, Duration p_263028_) {
        return 0;
    }

    @Overwrite
    private static int m_214471_(CommandSourceStack source, ResourceOrTagKeyArgument.Result<Structure> structure) throws CommandSyntaxException {
        Registry registry = source.m_81372_().m_9598_().m_175515_(Registries.f_256944_);
        HolderSet holderset = (HolderSet)LocateCommandMixin.m_245526_(structure, (Registry<Structure>)registry).orElseThrow(() -> f_214452_.create((Object)structure.m_245390_()));
        BlockPos blockpos = BlockPos.m_274446_((Position)source.m_81371_());
        ServerLevel serverlevel = source.m_81372_();
        Stopwatch stopwatch = Stopwatch.createStarted((Ticker)Util.f_211544_);
        CompletableFuture.supplyAsync(() -> serverlevel.m_7726_().m_8481_().m_223037_(serverlevel, holderset, blockpos, 100, false), Util.m_183991_()).thenAcceptAsync(pair -> {
            stopwatch.stop();
            if (pair == null) {
                source.m_81352_((Component)Component.m_237110_((String)"commands.locate.structure.not_found", (Object[])new Object[]{structure.m_245390_()}));
            } else {
                LocateCommandMixin.m_262810_(source, structure, blockpos, pair, "commands.locate.structure.success", false, stopwatch.elapsed());
            }
        }, (Executor)serverlevel.m_7654_());
        return 1;
    }

    @Overwrite
    private static int m_247543_(CommandSourceStack source, ResourceOrTagArgument.Result<Biome> biome) throws CommandSyntaxException {
        BlockPos blockpos = BlockPos.m_274446_((Position)source.m_81371_());
        ServerLevel serverlevel = source.m_81372_();
        Stopwatch stopwatch = Stopwatch.createStarted((Ticker)Util.f_211544_);
        CompletableFuture.supplyAsync(() -> serverlevel.m_215069_((Predicate)biome, blockpos, 6400, 32, 64), Util.m_183991_()).thenAcceptAsync(pair -> {
            stopwatch.stop();
            if (pair == null) {
                source.m_81352_((Component)Component.m_237110_((String)"commands.locate.biome.not_found", (Object[])new Object[]{biome.m_245443_()}));
            } else {
                LocateCommandMixin.m_262830_(source, biome, blockpos, pair, "commands.locate.biome.success", true, stopwatch.elapsed());
            }
        }, (Executor)serverlevel.m_7654_());
        return 1;
    }

    @Overwrite
    private static int m_245206_(CommandSourceStack source, ResourceOrTagArgument.Result<PoiType> poi) throws CommandSyntaxException {
        BlockPos blockpos = BlockPos.m_274446_((Position)source.m_81371_());
        ServerLevel serverlevel = source.m_81372_();
        Stopwatch stopwatch = Stopwatch.createStarted((Ticker)Util.f_211544_);
        CompletableFuture.supplyAsync(() -> serverlevel.m_8904_().m_218002_((Predicate)poi, blockpos, 256, PoiManager.Occupancy.ANY), Util.m_183991_()).thenAcceptAsync(optional -> {
            stopwatch.stop();
            if (optional.isEmpty()) {
                source.m_81352_((Component)Component.m_237110_((String)"commands.locate.poi.not_found", (Object[])new Object[]{poi.m_245443_()}));
            } else {
                LocateCommandMixin.m_262830_(source, poi, blockpos, ((Pair)optional.get()).swap(), "commands.locate.poi.success", false, stopwatch.elapsed());
            }
        }, (Executor)serverlevel.m_7654_());
        return 1;
    }
}

