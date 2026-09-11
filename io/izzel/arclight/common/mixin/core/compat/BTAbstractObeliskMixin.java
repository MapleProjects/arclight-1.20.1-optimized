/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.brass_amber.ba_bt.BABattleTowers
 *  com.brass_amber.ba_bt.block.block.BTSpawnerBlock
 *  com.brass_amber.ba_bt.block.blockentity.BTChestBlockEntity
 *  com.brass_amber.ba_bt.entity.block.BTAbstractObelisk
 *  com.brass_amber.ba_bt.util.BTUtil
 *  com.brass_amber.ba_bt.util.GolemType
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.LootContext$Builder
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootPool$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.compat;

import com.brass_amber.ba_bt.BABattleTowers;
import com.brass_amber.ba_bt.block.block.BTSpawnerBlock;
import com.brass_amber.ba_bt.block.blockentity.BTChestBlockEntity;
import com.brass_amber.ba_bt.entity.block.BTAbstractObelisk;
import com.brass_amber.ba_bt.util.BTUtil;
import com.brass_amber.ba_bt.util.GolemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets={"com.brass_amber.ba_bt.entity.block.BTAbstractObelisk"}, remap=false)
public abstract class BTAbstractObeliskMixin {
    @Shadow
    private List<BlockPos> CHESTS;
    @Shadow
    private List<List<BlockPos>> SPAWNERS;
    @Shadow
    private boolean chestsFound;
    @Shadow
    private boolean justSpawnedKey;
    @Shadow
    private List<Integer> keySpawnerAmounts;
    @Shadow
    private List<Integer> spawnerAmounts;
    @Shadow
    protected int checkLayer;
    @Shadow
    protected GolemType golemType;
    @Shadow
    protected ArrayList<String> towerChestLootTypes;

    @Shadow
    public abstract void setSpawnersDestroyed(int var1);

    @Shadow
    public abstract int getSpawnersDestroyed();

    @Shadow
    private void chestUnlockingSound(Level level) {
    }

    @Inject(method={"findChestsAndSpawners"}, at={@At(value="HEAD")}, require=0)
    private void arclight_ensureSpawnersInit(Level level, CallbackInfo callbackInfo) {
        if (this.SPAWNERS == null) {
            this.SPAWNERS = new ArrayList<List<BlockPos>>();
        }
        while (this.SPAWNERS.size() < 8) {
            this.SPAWNERS.add(new ArrayList());
        }
        for (int i = 0; i < 8; ++i) {
            if (this.SPAWNERS.get(i) != null) continue;
            this.SPAWNERS.set(i, new ArrayList());
        }
        if (this.CHESTS == null) {
            this.CHESTS = new ArrayList<BlockPos>();
        }
    }

    @Inject(method={"checkPos"}, at={@At(value="HEAD")}, require=0)
    private void arclight_ensureLayerInit(BlockPos blockPos, Level level, CallbackInfo callbackInfo) {
        if (this.SPAWNERS == null) {
            this.SPAWNERS = new ArrayList<List<BlockPos>>();
        }
        while (this.SPAWNERS.size() < 8) {
            this.SPAWNERS.add(new ArrayList());
        }
        if (this.checkLayer >= 1 && this.checkLayer <= this.SPAWNERS.size() && this.SPAWNERS.get(this.checkLayer - 1) == null) {
            this.SPAWNERS.set(this.checkLayer - 1, new ArrayList());
        }
        if (this.CHESTS == null) {
            this.CHESTS = new ArrayList<BlockPos>();
        }
    }

    @Inject(method={"checkSpawners"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void arclight_safeCheckSpawners(Level level, CallbackInfo callbackInfo) {
        if (!this.chestsFound || this.SPAWNERS == null || this.CHESTS == null) {
            callbackInfo.cancel();
            return;
        }
        BTAbstractObelisk bTAbstractObelisk = (BTAbstractObelisk)this;
        for (int i = 0; i < this.SPAWNERS.size(); ++i) {
            BTChestBlockEntity bTChestBlockEntity;
            BlockEntity blockEntity;
            BTChestBlockEntity bTChestBlockEntity2;
            BlockPos blockPos;
            List<BlockPos> list = this.SPAWNERS.get(i);
            if (list == null) continue;
            list.removeIf(Objects::isNull);
            if (list.isEmpty()) {
                BlockPos blockPos2;
                if (i < this.CHESTS.size() && (blockPos2 = this.CHESTS.get(i)) != null && (blockPos = level.m_7702_(blockPos2)) instanceof BTChestBlockEntity && !(bTChestBlockEntity2 = (BTChestBlockEntity)blockPos).isUnlocked()) {
                    int n;
                    bTChestBlockEntity2.setUnlocked(true);
                    blockEntity = new LootParams.Builder((ServerLevel)bTAbstractObelisk.m_9236_()).m_287286_(LootContextParams.f_81460_, (Object)Vec3.m_82512_((Vec3i)blockPos2)).m_287235_(LootContextParamSets.f_81411_);
                    bTChestBlockEntity = new LootContext.Builder((LootParams)blockEntity).m_287259_(null);
                    String string = GolemType.getTowerChestPool((GolemType)this.golemType, (int)i);
                    int n2 = n = i < 2 ? 0 : i / 2;
                    if (!string.isEmpty()) {
                        BTUtil.btFill((LootTable)bTAbstractObelisk.m_20194_().m_278653_().m_278676_(new ResourceLocation(string)), (Container)bTChestBlockEntity2, (LootContext)bTChestBlockEntity, (LootParams)blockEntity);
                    } else {
                        LootPool.Builder builder = BTUtil.createItems((int)n, (List)BTUtil.getPools(this.towerChestLootTypes), (RandomSource)level.m_213780_(), (boolean)false);
                        BTUtil.btFill((LootTable)LootTable.m_79147_().m_79161_(builder).m_79167_(), (Container)bTChestBlockEntity2, (LootContext)bTChestBlockEntity, (LootParams)blockEntity);
                    }
                    this.chestUnlockingSound(level);
                    this.CHESTS.set(i, null);
                }
                this.SPAWNERS.set(i, null);
                continue;
            }
            for (int j = 0; j < list.size(); ++j) {
                blockPos = list.get(j);
                if (blockPos != null && !(level.m_8055_(blockPos).m_60734_() instanceof BTSpawnerBlock)) {
                    list.set(j, null);
                    this.setSpawnersDestroyed(this.getSpawnersDestroyed() + 1);
                    BABattleTowers.LOGGER.debug("Spawners Destroyed: {}", (Object)this.getSpawnersDestroyed());
                }
                if (this.keySpawnerAmounts == null || !this.keySpawnerAmounts.contains(this.getSpawnersDestroyed()) || this.justSpawnedKey) continue;
                BlockPos blockPos3 = bTChestBlockEntity2 = i < this.CHESTS.size() ? this.CHESTS.get(i) : null;
                if (bTChestBlockEntity2 != null) {
                    blockEntity = level.m_7702_((BlockPos)bTChestBlockEntity2);
                    if (blockEntity instanceof BTChestBlockEntity) {
                        bTChestBlockEntity = (BTChestBlockEntity)blockEntity;
                        bTChestBlockEntity.m_6836_(13, GolemType.getKeyFor((GolemType)this.golemType).m_7968_());
                    }
                } else {
                    BTUtil.doNoOutputPostionedCommand((Entity)bTAbstractObelisk, (String)("/give @p ba_bt:" + GolemType.getKeyFor((GolemType)this.golemType).m_5524_()), (Vec3)new Vec3((double)bTAbstractObelisk.m_20183_().m_123341_(), (double)(bTAbstractObelisk.m_20183_().m_123342_() + 11 * i), (double)bTAbstractObelisk.m_20183_().m_123343_()));
                }
                this.justSpawnedKey = true;
            }
        }
        callbackInfo.cancel();
    }
}

