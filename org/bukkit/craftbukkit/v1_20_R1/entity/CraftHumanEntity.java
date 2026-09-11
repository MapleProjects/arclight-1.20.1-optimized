/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundOpenScreenPacket
 *  net.minecraft.network.protocol.game.ServerboundContainerClosePacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.item.ItemCooldowns$CooldownInstance
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeManager
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BedBlock
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.CraftingTableBlock
 *  net.minecraft.world.level.block.EnchantmentTableBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_20_R1.entity.memory.CraftMemoryMapper;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryDoubleChest;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryLectern;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchantCustom;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class CraftHumanEntity
extends CraftLivingEntity
implements HumanEntity {
    private CraftInventoryPlayer inventory;
    private final CraftInventory enderChest;
    protected final PermissibleBase perm = new PermissibleBase(this);
    private boolean op;
    private GameMode mode;

    public CraftHumanEntity(CraftServer server, Player entity) {
        super(server, (LivingEntity)entity);
        this.mode = server.getDefaultGameMode();
        this.inventory = new CraftInventoryPlayer(entity.m_150109_());
        this.enderChest = new CraftInventory((Container)entity.m_36327_());
    }

    @Override
    public PlayerInventory getInventory() {
        return this.inventory;
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.inventory;
    }

    @Override
    public Inventory getEnderChest() {
        return this.enderChest;
    }

    @Override
    public MainHand getMainHand() {
        return this.getHandle().m_5737_() == HumanoidArm.LEFT ? MainHand.LEFT : MainHand.RIGHT;
    }

    @Override
    public ItemStack getItemInHand() {
        return this.getInventory().getItemInHand();
    }

    @Override
    public void setItemInHand(ItemStack item) {
        this.getInventory().setItemInHand(item);
    }

    @Override
    public ItemStack getItemOnCursor() {
        return CraftItemStack.asCraftMirror(this.getHandle().f_36096_.m_142621_());
    }

    @Override
    public void setItemOnCursor(ItemStack item) {
        net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
        this.getHandle().f_36096_.m_142503_(stack);
        if (this instanceof CraftPlayer) {
            this.getHandle().f_36096_.broadcastCarriedItem();
        }
    }

    @Override
    public int getSleepTicks() {
        return this.getHandle().f_36110_;
    }

    @Override
    public boolean sleep(Location location, boolean force) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((location.getWorld() != null ? 1 : 0) != 0, (Object)"Location needs to be in a world");
        Preconditions.checkArgument((boolean)location.getWorld().equals(this.getWorld()), (Object)"Cannot sleep across worlds");
        BlockPos blockposition = CraftLocation.toBlockPosition(location);
        BlockState iblockdata = this.getHandle().m_9236_().m_8055_(blockposition);
        if (!(iblockdata.m_60734_() instanceof BedBlock)) {
            return false;
        }
        if (this.getHandle().startSleepInBed(blockposition, force).left().isPresent()) {
            return false;
        }
        iblockdata = (BlockState)iblockdata.m_61124_((Property)BedBlock.f_49441_, (Comparable)Boolean.valueOf(true));
        this.getHandle().m_9236_().m_7731_(blockposition, iblockdata, 4);
        return true;
    }

    @Override
    public void wakeup(boolean setSpawnLocation) {
        Preconditions.checkState((boolean)this.isSleeping(), (Object)"Cannot wakeup if not sleeping");
        this.getHandle().m_6145_(true, setSpawnLocation);
    }

    @Override
    public Location getBedLocation() {
        Preconditions.checkState((boolean)this.isSleeping(), (Object)"Not sleeping");
        BlockPos bed = (BlockPos)this.getHandle().m_21257_().get();
        return CraftLocation.toBukkit(bed, this.getWorld());
    }

    @Override
    public String getName() {
        return this.getHandle().m_6302_();
    }

    @Override
    public boolean isOp() {
        return this.op;
    }

    @Override
    public boolean isPermissionSet(String name) {
        return this.perm.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return this.perm.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return this.perm.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return this.perm.hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return this.perm.addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return this.perm.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return this.perm.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return this.perm.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        this.perm.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        this.perm.recalculatePermissions();
    }

    @Override
    public void setOp(boolean value) {
        this.op = value;
        this.perm.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return this.perm.getEffectivePermissions();
    }

    @Override
    public GameMode getGameMode() {
        return this.mode;
    }

    @Override
    public void setGameMode(GameMode mode) {
        Preconditions.checkArgument((mode != null ? 1 : 0) != 0, (Object)"GameMode cannot be null");
        this.mode = mode;
    }

    public Player getHandle() {
        return (Player)this.entity;
    }

    public void setHandle(Player entity) {
        super.setHandle((LivingEntity)entity);
        this.inventory = new CraftInventoryPlayer(entity.m_150109_());
    }

    @Override
    public String toString() {
        return "CraftHumanEntity{id=" + this.getEntityId() + "name=" + this.getName() + '}';
    }

    @Override
    public InventoryView getOpenInventory() {
        return this.getHandle().f_36096_.getBukkitView();
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        BlockEntity te;
        CraftInventory craft;
        if (!(this.getHandle() instanceof ServerPlayer)) {
            return null;
        }
        ServerPlayer player = (ServerPlayer)this.getHandle();
        AbstractContainerMenu formerContainer = this.getHandle().f_36096_;
        MenuProvider iinventory = null;
        if (inventory instanceof CraftInventoryDoubleChest) {
            iinventory = ((CraftInventoryDoubleChest)inventory).tile;
        } else if (inventory instanceof CraftInventoryLectern) {
            iinventory = ((CraftInventoryLectern)inventory).tile;
        } else if (inventory instanceof CraftInventory && (craft = (CraftInventory)inventory).getInventory() instanceof MenuProvider) {
            iinventory = (MenuProvider)craft.getInventory();
        }
        if (iinventory instanceof MenuProvider && iinventory instanceof BlockEntity && !(te = (BlockEntity)iinventory).m_58898_()) {
            te.m_142339_(this.getHandle().m_9236_());
        }
        MenuType container = CraftContainer.getNotchInventoryType(inventory);
        if (iinventory instanceof MenuProvider) {
            this.getHandle().m_5893_(iinventory);
        } else {
            CraftHumanEntity.openCustomInventory(inventory, player, container);
        }
        if (this.getHandle().f_36096_ == formerContainer) {
            return null;
        }
        this.getHandle().f_36096_.checkReachable = false;
        return this.getHandle().f_36096_.getBukkitView();
    }

    private static void openCustomInventory(Inventory inventory, ServerPlayer player, MenuType<?> windowType) {
        if (player.f_8906_ == null) {
            return;
        }
        Preconditions.checkArgument((windowType != null ? 1 : 0) != 0, (Object)"Unknown windowType");
        CraftContainer container = new CraftContainer(inventory, (Player)player, player.nextContainerCounter());
        container = CraftEventFactory.callInventoryOpenEvent(player, container);
        if (container == null) {
            return;
        }
        String title = container.getBukkitView().getTitle();
        player.f_8906_.m_9829_((Packet)new ClientboundOpenScreenPacket(container.f_38840_, windowType, CraftChatMessage.fromString(title)[0]));
        player.f_36096_ = container;
        player.m_143399_((AbstractContainerMenu)container);
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        Block block;
        if (location == null) {
            location = this.getLocation();
        }
        if (!force && (block = location.getBlock()).getType() != Material.CRAFTING_TABLE) {
            return null;
        }
        this.getHandle().m_5893_(((CraftingTableBlock)Blocks.f_50091_).m_7246_(null, this.getHandle().m_9236_(), CraftLocation.toBlockPosition(location)));
        if (force) {
            this.getHandle().f_36096_.checkReachable = false;
        }
        return this.getHandle().f_36096_.getBukkitView();
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        Block block;
        if (location == null) {
            location = this.getLocation();
        }
        if (!force && (block = location.getBlock()).getType() != Material.ENCHANTING_TABLE) {
            return null;
        }
        BlockPos pos = CraftLocation.toBlockPosition(location);
        this.getHandle().m_5893_(((EnchantmentTableBlock)Blocks.f_50201_).m_7246_(null, this.getHandle().m_9236_(), pos));
        if (force) {
            this.getHandle().f_36096_.checkReachable = false;
        }
        return this.getHandle().f_36096_.getBukkitView();
    }

    @Override
    public void openInventory(InventoryView inventory) {
        if (!(this.getHandle() instanceof ServerPlayer)) {
            return;
        }
        if (((ServerPlayer)this.getHandle()).f_8906_ == null) {
            return;
        }
        if (this.getHandle().f_36096_ != this.getHandle().f_36095_) {
            ((ServerPlayer)this.getHandle()).f_8906_.m_7951_(new ServerboundContainerClosePacket(this.getHandle().f_36096_.f_38840_));
        }
        ServerPlayer player = (ServerPlayer)this.getHandle();
        Object container = inventory instanceof CraftInventoryView ? ((CraftInventoryView)inventory).getHandle() : new CraftContainer(inventory, this.getHandle(), player.nextContainerCounter());
        if ((container = CraftEventFactory.callInventoryOpenEvent(player, container)) == null) {
            return;
        }
        MenuType windowType = CraftContainer.getNotchInventoryType(inventory.getTopInventory());
        String title = inventory.getTitle();
        player.f_8906_.m_9829_((Packet)new ClientboundOpenScreenPacket(container.f_38840_, windowType, CraftChatMessage.fromString(title)[0]));
        player.f_36096_ = container;
        player.m_143399_(container);
    }

    @Override
    public InventoryView openMerchant(Villager villager, boolean force) {
        Preconditions.checkNotNull((Object)villager, (Object)"villager cannot be null");
        return this.openMerchant((Merchant)villager, force);
    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean force) {
        Component name;
        Object mcMerchant;
        Preconditions.checkNotNull((Object)merchant, (Object)"merchant cannot be null");
        if (!force && merchant.isTrading()) {
            return null;
        }
        if (merchant.isTrading()) {
            merchant.getTrader().closeInventory();
        }
        int level = 1;
        if (merchant instanceof CraftAbstractVillager) {
            mcMerchant = ((CraftAbstractVillager)merchant).getHandle();
            name = ((CraftAbstractVillager)merchant).getHandle().m_5446_();
            if (merchant instanceof CraftVillager) {
                level = ((CraftVillager)merchant).getHandle().m_7141_().m_35576_();
            }
        } else if (merchant instanceof CraftMerchantCustom) {
            mcMerchant = ((CraftMerchantCustom)merchant).getMerchant();
            name = ((CraftMerchantCustom)merchant).getMerchant().getScoreboardDisplayName();
        } else {
            throw new IllegalArgumentException("Can't open merchant " + merchant.toString());
        }
        mcMerchant.m_7189_(this.getHandle());
        mcMerchant.m_45301_(this.getHandle(), name, level);
        return this.getHandle().f_36096_.getBukkitView();
    }

    @Override
    public void closeInventory() {
        this.getHandle().m_6915_();
    }

    @Override
    public boolean isBlocking() {
        return this.getHandle().m_21254_();
    }

    @Override
    public boolean isHandRaised() {
        return this.getHandle().m_6117_();
    }

    @Override
    public ItemStack getItemInUse() {
        net.minecraft.world.item.ItemStack item = this.getHandle().m_21211_();
        return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        return false;
    }

    @Override
    public int getEnchantmentSeed() {
        return this.getHandle().f_36081_;
    }

    @Override
    public void setEnchantmentSeed(int i) {
        this.getHandle().f_36081_ = i;
    }

    @Override
    public int getExpToLevel() {
        return this.getHandle().m_36323_();
    }

    @Override
    public float getAttackCooldown() {
        return this.getHandle().m_36403_(0.5f);
    }

    @Override
    public boolean hasCooldown(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((boolean)material.isItem(), (String)"Material %s is not an item", (Object)material);
        return this.getHandle().m_36335_().m_41519_(CraftMagicNumbers.getItem(material));
    }

    @Override
    public int getCooldown(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((boolean)material.isItem(), (String)"Material %s is not an item", (Object)material);
        ItemCooldowns.CooldownInstance cooldown = (ItemCooldowns.CooldownInstance)this.getHandle().m_36335_().f_41515_.get(CraftMagicNumbers.getItem(material));
        return cooldown == null ? 0 : Math.max(0, cooldown.f_41534_ - this.getHandle().m_36335_().f_41516_);
    }

    @Override
    public void setCooldown(Material material, int ticks) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((boolean)material.isItem(), (String)"Material %s is not an item", (Object)material);
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"Cannot have negative cooldown");
        this.getHandle().m_36335_().m_41524_(CraftMagicNumbers.getItem(material), ticks);
    }

    @Override
    public boolean discoverRecipe(NamespacedKey recipe) {
        return this.discoverRecipes(Arrays.asList(recipe)) != 0;
    }

    @Override
    public int discoverRecipes(Collection<NamespacedKey> recipes) {
        return this.getHandle().m_7281_(this.bukkitKeysToMinecraftRecipes(recipes));
    }

    @Override
    public boolean undiscoverRecipe(NamespacedKey recipe) {
        return this.undiscoverRecipes(Arrays.asList(recipe)) != 0;
    }

    @Override
    public int undiscoverRecipes(Collection<NamespacedKey> recipes) {
        return this.getHandle().m_7279_(this.bukkitKeysToMinecraftRecipes(recipes));
    }

    @Override
    public boolean hasDiscoveredRecipe(NamespacedKey recipe) {
        return false;
    }

    @Override
    public Set<NamespacedKey> getDiscoveredRecipes() {
        return ImmutableSet.of();
    }

    private Collection<Recipe<?>> bukkitKeysToMinecraftRecipes(Collection<NamespacedKey> recipeKeys) {
        ArrayList recipes = new ArrayList();
        RecipeManager manager = this.getHandle().m_9236_().m_7654_().m_129894_();
        for (NamespacedKey recipeKey : recipeKeys) {
            Optional recipe = manager.m_44043_(CraftNamespacedKey.toMinecraft(recipeKey));
            if (!recipe.isPresent()) continue;
            recipes.add((Recipe)recipe.get());
        }
        return recipes;
    }

    @Override
    public Entity getShoulderEntityLeft() {
        if (!this.getHandle().m_36331_().m_128456_()) {
            Optional shoulder = EntityType.m_20642_((CompoundTag)this.getHandle().m_36331_(), (Level)this.getHandle().m_9236_());
            return !shoulder.isPresent() ? null : ((net.minecraft.world.entity.Entity)shoulder.get()).getBukkitEntity();
        }
        return null;
    }

    @Override
    public void setShoulderEntityLeft(Entity entity) {
        this.getHandle().m_36362_(entity == null ? new CompoundTag() : ((CraftEntity)entity).save());
        if (entity != null) {
            entity.remove();
        }
    }

    @Override
    public Entity getShoulderEntityRight() {
        if (!this.getHandle().m_36332_().m_128456_()) {
            Optional shoulder = EntityType.m_20642_((CompoundTag)this.getHandle().m_36332_(), (Level)this.getHandle().m_9236_());
            return !shoulder.isPresent() ? null : ((net.minecraft.world.entity.Entity)shoulder.get()).getBukkitEntity();
        }
        return null;
    }

    @Override
    public void setShoulderEntityRight(Entity entity) {
        this.getHandle().m_36364_(entity == null ? new CompoundTag() : ((CraftEntity)entity).save());
        if (entity != null) {
            entity.remove();
        }
    }

    @Override
    public boolean dropItem(boolean dropAll) {
        if (!(this.getHandle() instanceof ServerPlayer)) {
            return false;
        }
        return ((ServerPlayer)this.getHandle()).m_182294_(dropAll);
    }

    @Override
    public float getExhaustion() {
        return this.getHandle().m_36324_().f_38698_;
    }

    @Override
    public void setExhaustion(float value) {
        this.getHandle().m_36324_().f_38698_ = value;
    }

    @Override
    public float getSaturation() {
        return this.getHandle().m_36324_().f_38697_;
    }

    @Override
    public void setSaturation(float value) {
        this.getHandle().m_36324_().f_38697_ = value;
    }

    @Override
    public int getFoodLevel() {
        return this.getHandle().m_36324_().f_38696_;
    }

    @Override
    public void setFoodLevel(int value) {
        this.getHandle().m_36324_().f_38696_ = value;
    }

    @Override
    public int getSaturatedRegenRate() {
        return this.getHandle().m_36324_().saturatedRegenRate;
    }

    @Override
    public void setSaturatedRegenRate(int i) {
        this.getHandle().m_36324_().saturatedRegenRate = i;
    }

    @Override
    public int getUnsaturatedRegenRate() {
        return this.getHandle().m_36324_().unsaturatedRegenRate;
    }

    @Override
    public void setUnsaturatedRegenRate(int i) {
        this.getHandle().m_36324_().unsaturatedRegenRate = i;
    }

    @Override
    public int getStarvationRate() {
        return this.getHandle().m_36324_().starvationRate;
    }

    @Override
    public void setStarvationRate(int i) {
        this.getHandle().m_36324_().starvationRate = i;
    }

    @Override
    public Location getLastDeathLocation() {
        return this.getHandle().m_219759_().map(CraftMemoryMapper::fromNms).orElse(null);
    }

    @Override
    public void setLastDeathLocation(Location location) {
        if (location == null) {
            this.getHandle().m_219749_(Optional.empty());
        } else {
            this.getHandle().m_219749_(Optional.of(CraftMemoryMapper.toNms(location)));
        }
    }

    @Override
    public Firework fireworkBoost(ItemStack fireworkItemStack) {
        Preconditions.checkArgument((fireworkItemStack != null ? 1 : 0) != 0, (Object)"fireworkItemStack must not be null");
        Preconditions.checkArgument((fireworkItemStack.getType() == Material.FIREWORK_ROCKET ? 1 : 0) != 0, (String)"fireworkItemStack must be of type %s", (Object)Material.FIREWORK_ROCKET);
        FireworkRocketEntity fireworks = new FireworkRocketEntity(this.getHandle().m_9236_(), CraftItemStack.asNMSCopy(fireworkItemStack), (LivingEntity)this.getHandle());
        boolean success = this.getHandle().m_9236_().addFreshEntity((net.minecraft.world.entity.Entity)fireworks, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return success ? (Firework)((Object)fireworks.getBukkitEntity()) : null;
    }
}

