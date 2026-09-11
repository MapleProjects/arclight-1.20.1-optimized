/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.WorldLoader$DataLoadContext
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package io.izzel.arclight.common.mod.util;

import io.izzel.arclight.common.mod.ArclightConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.bukkit.TreeType;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftPortalEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class ArclightCaptures {
    private static Entity entityChangeBlock;
    public static boolean isPrimaryEvent;
    public static Stack<BlockBreakEventContext> blockBreakEventStack;
    private static String quitMessage;
    private static Direction placeEventDirection;
    private static InteractionHand placeEventHand;
    private static TreeType treeType;
    private static transient AbstractContainerMenu arclight$capturedContainer;
    private static transient Entity damageEventEntity;
    private static transient BlockPos damageEventBlock;
    private static transient Player containerOwner;
    private static transient CraftPortalEvent craftPortalEvent;
    private static transient Entity endPortalEntity;
    private static transient boolean spawnPortal;
    private static transient WorldLoader.DataLoadContext dataLoadContext;
    private static transient BlockEntity tickingBlockEntity;
    private static ServerLevel tickingLevel;
    private static BlockPos tickingPosition;
    private static Entity tickingEntity;
    private static EntityPotionEffectEvent.Cause effectCause;
    private static BlockPos spreadPos;

    public static void captureEntityChangeBlock(Entity entity) {
        entityChangeBlock = entity;
    }

    public static Entity getEntityChangeBlock() {
        try {
            Entity entity = entityChangeBlock;
            return entity;
        }
        finally {
            entityChangeBlock = null;
        }
    }

    public static void captureNextBlockBreakEventAsPrimaryEvent() {
        isPrimaryEvent = true;
    }

    public static void captureBlockBreakPlayer(BlockBreakEvent event) {
        blockBreakEventStack.push(new BlockBreakEventContext(event, isPrimaryEvent));
        isPrimaryEvent = false;
    }

    public static List<ItemEntity> getBlockDrops() {
        if (!blockBreakEventStack.empty()) {
            return blockBreakEventStack.peek().getBlockDrops();
        }
        return null;
    }

    public static BlockBreakEventContext popPrimaryBlockBreakEvent() {
        if (blockBreakEventStack.size() > 0) {
            BlockBreakEventContext eventContext = blockBreakEventStack.pop();
            ArrayList<BlockBreakEventContext> unhandledEvents = new ArrayList<BlockBreakEventContext>();
            while (!blockBreakEventStack.empty() && !eventContext.isPrimary()) {
                unhandledEvents.add(eventContext);
                eventContext = blockBreakEventStack.pop();
            }
            if (unhandledEvents.size() > 0) {
                eventContext.mergeAllDrops(unhandledEvents);
            }
            return eventContext;
        }
        return null;
    }

    public static BlockBreakEventContext popSecondaryBlockBreakEvent() {
        BlockBreakEventContext eventContext;
        if (blockBreakEventStack.size() > 0 && !(eventContext = blockBreakEventStack.peek()).isPrimary()) {
            return blockBreakEventStack.pop();
        }
        return null;
    }

    public static void clearBlockBreakEventContexts() {
        if (!blockBreakEventStack.empty()) {
            blockBreakEventStack.clear();
        }
    }

    public static void captureQuitMessage(String quitMessage) {
        ArclightCaptures.quitMessage = quitMessage;
    }

    public static String getQuitMessage() {
        try {
            String string = quitMessage;
            return string;
        }
        finally {
            quitMessage = null;
        }
    }

    public static void capturePlaceEventDirection(Direction direction) {
        placeEventDirection = direction;
    }

    public static Direction getPlaceEventDirection() {
        try {
            Direction direction = placeEventDirection;
            return direction;
        }
        finally {
            placeEventDirection = null;
        }
    }

    public static void capturePlaceEventHand(InteractionHand hand) {
        placeEventHand = hand;
    }

    public static InteractionHand getPlaceEventHand(InteractionHand hand) {
        try {
            InteractionHand interactionHand = placeEventHand == null ? hand : placeEventHand;
            return interactionHand;
        }
        finally {
            placeEventHand = null;
        }
    }

    public static void captureTreeType(TreeType treeType) {
        ArclightCaptures.treeType = treeType;
    }

    public static TreeType getTreeType() {
        try {
            TreeType treeType = ArclightCaptures.treeType == null ? ArclightConstants.MOD : ArclightCaptures.treeType;
            return treeType;
        }
        finally {
            treeType = null;
        }
    }

    public static void captureWorkbenchContainer(AbstractContainerMenu container) {
        arclight$capturedContainer = container;
    }

    public static AbstractContainerMenu getWorkbenchContainer() {
        try {
            AbstractContainerMenu abstractContainerMenu = arclight$capturedContainer;
            return abstractContainerMenu;
        }
        finally {
            arclight$capturedContainer = null;
        }
    }

    public static void captureDamageEventEntity(Entity entity) {
        damageEventEntity = entity;
    }

    public static Entity getDamageEventEntity() {
        try {
            Entity entity = damageEventEntity;
            return entity;
        }
        finally {
            damageEventEntity = null;
        }
    }

    public static void captureDamageEventBlock(BlockPos blockState) {
        damageEventBlock = blockState;
    }

    public static BlockPos getDamageEventBlock() {
        try {
            BlockPos blockPos = damageEventBlock;
            return blockPos;
        }
        finally {
            damageEventBlock = null;
        }
    }

    public static void captureContainerOwner(Player entity) {
        containerOwner = entity;
    }

    public static Player getContainerOwner() {
        return containerOwner;
    }

    public static void resetContainerOwner() {
        containerOwner = null;
    }

    public static void captureCraftPortalEvent(CraftPortalEvent event) {
        craftPortalEvent = event;
    }

    public static CraftPortalEvent getCraftPortalEvent() {
        try {
            CraftPortalEvent craftPortalEvent = ArclightCaptures.craftPortalEvent;
            return craftPortalEvent;
        }
        finally {
            craftPortalEvent = null;
        }
    }

    public static void captureEndPortalEntity(Entity entity, boolean portal) {
        endPortalEntity = entity;
        spawnPortal = portal;
    }

    public static boolean getEndPortalSpawn() {
        return spawnPortal;
    }

    public static Entity getEndPortalEntity() {
        try {
            Entity entity = endPortalEntity;
            return entity;
        }
        finally {
            endPortalEntity = null;
            spawnPortal = false;
        }
    }

    public static void captureDataLoadContext(WorldLoader.DataLoadContext context) {
        dataLoadContext = context;
    }

    public static WorldLoader.DataLoadContext getDataLoadContext() {
        try {
            WorldLoader.DataLoadContext dataLoadContext = Objects.requireNonNull(ArclightCaptures.dataLoadContext, "dataLoadContext");
            return dataLoadContext;
        }
        finally {
            dataLoadContext = null;
        }
    }

    public static void captureTickingBlockEntity(BlockEntity entity) {
        tickingBlockEntity = entity;
    }

    public static void resetTickingBlockEntity() {
        tickingBlockEntity = null;
    }

    public static <T extends BlockEntity> T getTickingBlockEntity() {
        return (T)tickingBlockEntity;
    }

    public static void captureTickingBlock(ServerLevel level, BlockPos pos) {
        tickingLevel = level;
        tickingPosition = pos;
    }

    public static ServerLevel getTickingLevel() {
        return tickingLevel;
    }

    public static BlockPos getTickingPosition() {
        return tickingPosition;
    }

    public static void resetTickingBlock() {
        tickingLevel = null;
        tickingPosition = null;
    }

    public static void captureTickingEntity(Entity entity) {
        tickingEntity = entity;
    }

    public static Entity getTickingEntity() {
        return tickingEntity;
    }

    public static void resetTickingEntity() {
        tickingEntity = null;
    }

    public static void captureEffectCause(EntityPotionEffectEvent.Cause cause) {
        effectCause = cause;
    }

    public static EntityPotionEffectEvent.Cause getEffectCause() {
        try {
            EntityPotionEffectEvent.Cause cause = effectCause;
            return cause;
        }
        finally {
            effectCause = null;
        }
    }

    public static void captureSpreadSource(BlockPos source) {
        spreadPos = source.m_7949_();
    }

    public static BlockPos getSpreadPos() {
        return spreadPos;
    }

    public static void resetSpreadSource() {
        spreadPos = null;
    }

    private static void recapture(String type) {
        throw new IllegalStateException("Recapturing " + type);
    }

    static {
        isPrimaryEvent = false;
        blockBreakEventStack = new Stack();
    }

    public static class BlockBreakEventContext {
        private final BlockBreakEvent blockBreakEvent;
        private final ArrayList<ItemEntity> blockDrops;
        private final BlockState blockBreakPlayerState;
        private final boolean primary;

        public BlockBreakEventContext(BlockBreakEvent event, boolean primary) {
            this.blockBreakEvent = event;
            this.blockDrops = new ArrayList();
            this.blockBreakPlayerState = event.getBlock().getState();
            this.primary = primary;
        }

        public BlockBreakEvent getEvent() {
            return this.blockBreakEvent;
        }

        public ArrayList<ItemEntity> getBlockDrops() {
            return this.blockDrops;
        }

        public BlockState getBlockBreakPlayerState() {
            return this.blockBreakPlayerState;
        }

        public void mergeAllDrops(List<BlockBreakEventContext> others) {
            for (BlockBreakEventContext other : others) {
                this.getBlockDrops().addAll(other.getBlockDrops());
            }
        }

        public boolean isPrimary() {
            return this.primary;
        }
    }
}

