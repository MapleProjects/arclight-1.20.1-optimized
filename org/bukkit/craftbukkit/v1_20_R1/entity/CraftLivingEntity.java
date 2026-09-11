/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Sets
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.entity.projectile.DragonFireball
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.entity.projectile.LargeFireball
 *  net.minecraft.world.entity.projectile.LlamaSpit
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  net.minecraft.world.entity.projectile.SmallFireball
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.SpectralArrow
 *  net.minecraft.world.entity.projectile.ThrowableProjectile
 *  net.minecraft.world.entity.projectile.ThrownEgg
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 *  net.minecraft.world.entity.projectile.ThrownExperienceBottle
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.entity.projectile.WitherSkull
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.memory.CraftMemoryKey;
import org.bukkit.craftbukkit.v1_20_R1.entity.memory.CraftMemoryMapper;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftEntityEquipment;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LingeringPotion;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.Trident;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class CraftLivingEntity
extends CraftEntity
implements LivingEntity {
    private CraftEntityEquipment equipment;

    public CraftLivingEntity(CraftServer server, net.minecraft.world.entity.LivingEntity entity) {
        super(server, (net.minecraft.world.entity.Entity)entity);
        if (entity instanceof Mob || entity instanceof ArmorStand) {
            this.equipment = new CraftEntityEquipment(this);
        }
    }

    @Override
    public double getHealth() {
        return Math.min((double)Math.max(0.0f, this.getHandle().m_21223_()), this.getMaxHealth());
    }

    @Override
    public void setHealth(double health) {
        Preconditions.checkArgument(((health = (double)((float)health)) >= 0.0 && health <= this.getMaxHealth() ? 1 : 0) != 0, (String)"Health value (%s) must be between 0 and %s", (Object)health, (Object)this.getMaxHealth());
        if (this.getHandle().generation && health == 0.0) {
            this.getHandle().m_146870_();
            return;
        }
        this.getHandle().m_21153_((float)health);
        if (health == 0.0) {
            this.getHandle().m_6667_(this.getHandle().m_269291_().m_269264_());
        }
    }

    @Override
    public double getAbsorptionAmount() {
        return this.getHandle().m_6103_();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        Preconditions.checkArgument((amount >= 0.0 && Double.isFinite(amount) ? 1 : 0) != 0, (Object)"amount < 0 or non-finite");
        this.getHandle().m_7911_((float)amount);
    }

    @Override
    public double getMaxHealth() {
        return this.getHandle().m_21233_();
    }

    @Override
    public void setMaxHealth(double amount) {
        Preconditions.checkArgument((amount > 0.0 ? 1 : 0) != 0, (String)"Max health amount (%s) must be greater than 0", (Object)amount);
        this.getHandle().m_21051_(Attributes.f_22276_).m_22100_(amount);
        if (this.getHealth() > amount) {
            this.setHealth(amount);
        }
    }

    @Override
    public void resetMaxHealth() {
        this.setMaxHealth(this.getHandle().m_21051_(Attributes.f_22276_).m_22099_().m_22082_());
    }

    @Override
    public double getEyeHeight() {
        return this.getHandle().m_20192_();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return this.getEyeHeight();
    }

    private List<Block> getLineOfSight(Set<Material> transparent, int maxDistance, int maxLength) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot get line of sight during world generation");
        if (transparent == null) {
            transparent = Sets.newHashSet((Object[])new Material[]{Material.AIR, Material.CAVE_AIR, Material.VOID_AIR});
        }
        if (maxDistance > 120) {
            maxDistance = 120;
        }
        ArrayList<Block> blocks = new ArrayList<Block>();
        BlockIterator itr = new BlockIterator(this, maxDistance);
        while (itr.hasNext()) {
            Material material;
            Block block = (Block)itr.next();
            blocks.add(block);
            if (maxLength != 0 && blocks.size() > maxLength) {
                blocks.remove(0);
            }
            if (!transparent.contains(material = block.getType())) break;
        }
        return blocks;
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        return this.getLineOfSight(transparent, maxDistance, 0);
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        List<Block> blocks = this.getLineOfSight(transparent, maxDistance, 1);
        return blocks.get(0);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        return this.getLineOfSight(transparent, maxDistance, 2);
    }

    @Override
    public Block getTargetBlockExact(int maxDistance) {
        return this.getTargetBlockExact(maxDistance, FluidCollisionMode.NEVER);
    }

    @Override
    public Block getTargetBlockExact(int maxDistance, FluidCollisionMode fluidCollisionMode) {
        RayTraceResult hitResult = this.rayTraceBlocks(maxDistance, fluidCollisionMode);
        return hitResult != null ? hitResult.getHitBlock() : null;
    }

    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance) {
        return this.rayTraceBlocks(maxDistance, FluidCollisionMode.NEVER);
    }

    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance, FluidCollisionMode fluidCollisionMode) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot ray tray blocks during world generation");
        Location eyeLocation = this.getEyeLocation();
        Vector direction = eyeLocation.getDirection();
        return this.getWorld().rayTraceBlocks(eyeLocation, direction, maxDistance, fluidCollisionMode, false);
    }

    @Override
    public int getRemainingAir() {
        return this.getHandle().m_20146_();
    }

    @Override
    public void setRemainingAir(int ticks) {
        this.getHandle().m_20301_(ticks);
    }

    @Override
    public int getMaximumAir() {
        return this.getHandle().maxAirTicks;
    }

    @Override
    public void setMaximumAir(int ticks) {
        this.getHandle().maxAirTicks = ticks;
    }

    @Override
    public int getArrowCooldown() {
        return this.getHandle().f_20914_;
    }

    @Override
    public void setArrowCooldown(int ticks) {
        this.getHandle().f_20914_ = ticks;
    }

    @Override
    public int getArrowsInBody() {
        return this.getHandle().m_21234_();
    }

    @Override
    public void setArrowsInBody(int count) {
        Preconditions.checkArgument((count >= 0 ? 1 : 0) != 0, (Object)"New arrow amount must be >= 0");
        this.getHandle().m_20088_().m_135381_(net.minecraft.world.entity.LivingEntity.f_20940_, (Object)count);
    }

    @Override
    public void damage(double amount) {
        this.damage(amount, null);
    }

    @Override
    public void damage(double amount, Entity source) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot damage entity during world generation");
        DamageSource reason = this.getHandle().m_269291_().m_269264_();
        if (source instanceof HumanEntity) {
            reason = this.getHandle().m_269291_().m_269075_(((CraftHumanEntity)source).getHandle());
        } else if (source instanceof LivingEntity) {
            reason = this.getHandle().m_269291_().m_269333_(((CraftLivingEntity)source).getHandle());
        }
        this.entity.m_6469_(reason, (float)amount);
    }

    @Override
    public Location getEyeLocation() {
        Location loc = this.getLocation();
        loc.setY(loc.getY() + this.getEyeHeight());
        return loc;
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return this.getHandle().f_20926_;
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        this.getHandle().f_20926_ = ticks;
    }

    @Override
    public double getLastDamage() {
        return this.getHandle().f_20898_;
    }

    @Override
    public void setLastDamage(double damage) {
        this.getHandle().f_20898_ = (float)damage;
    }

    @Override
    public int getNoDamageTicks() {
        return this.getHandle().f_19802_;
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        this.getHandle().f_19802_ = ticks;
    }

    @Override
    public int getNoActionTicks() {
        return this.getHandle().m_21216_();
    }

    @Override
    public void setNoActionTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks must be >= 0");
        this.getHandle().m_21310_(ticks);
    }

    public net.minecraft.world.entity.LivingEntity getHandle() {
        return (net.minecraft.world.entity.LivingEntity)this.entity;
    }

    public void setHandle(net.minecraft.world.entity.LivingEntity entity) {
        super.setHandle((net.minecraft.world.entity.Entity)entity);
    }

    @Override
    public String toString() {
        return "CraftLivingEntity{id=" + this.getEntityId() + '}';
    }

    @Override
    public org.bukkit.entity.Player getKiller() {
        return this.getHandle().f_20888_ == null ? null : (org.bukkit.entity.Player)((Object)this.getHandle().f_20888_.getBukkitEntity());
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return this.addPotionEffect(effect, false);
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        this.getHandle().addEffect(new MobEffectInstance(MobEffect.m_19453_((int)effect.getType().getId()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles()), EntityPotionEffectEvent.Cause.PLUGIN);
        return true;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        boolean success = true;
        for (PotionEffect effect : effects) {
            success &= this.addPotionEffect(effect);
        }
        return success;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return this.getHandle().m_21023_(MobEffect.m_19453_((int)type.getId()));
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        MobEffectInstance handle = this.getHandle().m_21124_(MobEffect.m_19453_((int)type.getId()));
        return handle == null ? null : new PotionEffect(PotionEffectType.getById(MobEffect.m_19459_((MobEffect)handle.m_19544_())), handle.m_19557_(), handle.m_19564_(), handle.m_19571_(), handle.m_19572_());
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        this.getHandle().removeEffect(MobEffect.m_19453_((int)type.getId()), EntityPotionEffectEvent.Cause.PLUGIN);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
        for (MobEffectInstance handle : this.getHandle().f_20945_.values()) {
            effects.add(new PotionEffect(PotionEffectType.getById(MobEffect.m_19459_((MobEffect)handle.m_19544_())), handle.m_19557_(), handle.m_19564_(), handle.m_19571_(), handle.m_19572_()));
        }
        return effects;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return this.launchProjectile(projectile, null);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot launch projectile during world generation");
        ServerLevel world = ((CraftWorld)this.getWorld()).getHandle();
        Object launch = null;
        if (org.bukkit.entity.Snowball.class.isAssignableFrom(projectile)) {
            launch = new Snowball((Level)world, this.getHandle());
            ((ThrowableProjectile)launch).m_37251_((net.minecraft.world.entity.Entity)this.getHandle(), this.getHandle().m_146909_(), this.getHandle().m_146908_(), 0.0f, 1.5f, 1.0f);
        } else if (Egg.class.isAssignableFrom(projectile)) {
            launch = new ThrownEgg((Level)world, this.getHandle());
            ((ThrowableProjectile)launch).m_37251_((net.minecraft.world.entity.Entity)this.getHandle(), this.getHandle().m_146909_(), this.getHandle().m_146908_(), 0.0f, 1.5f, 1.0f);
        } else if (EnderPearl.class.isAssignableFrom(projectile)) {
            launch = new ThrownEnderpearl((Level)world, this.getHandle());
            ((ThrowableProjectile)launch).m_37251_((net.minecraft.world.entity.Entity)this.getHandle(), this.getHandle().m_146909_(), this.getHandle().m_146908_(), 0.0f, 1.5f, 1.0f);
        } else if (AbstractArrow.class.isAssignableFrom(projectile)) {
            if (TippedArrow.class.isAssignableFrom(projectile)) {
                launch = new Arrow((Level)world, this.getHandle());
                ((org.bukkit.entity.Arrow)((Object)launch.getBukkitEntity())).setBasePotionData(new PotionData(PotionType.WATER, false, false));
            } else {
                launch = SpectralArrow.class.isAssignableFrom(projectile) ? new net.minecraft.world.entity.projectile.SpectralArrow((Level)world, this.getHandle()) : (Trident.class.isAssignableFrom(projectile) ? new ThrownTrident((Level)world, this.getHandle(), new net.minecraft.world.item.ItemStack((ItemLike)Items.f_42713_)) : new Arrow((Level)world, this.getHandle()));
            }
            ((net.minecraft.world.entity.projectile.AbstractArrow)launch).m_37251_((net.minecraft.world.entity.Entity)this.getHandle(), this.getHandle().m_146909_(), this.getHandle().m_146908_(), 0.0f, 3.0f, 1.0f);
        } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
            if (LingeringPotion.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.ThrownPotion((Level)world, this.getHandle());
                ((net.minecraft.world.entity.projectile.ThrownPotion)launch).m_37446_(CraftItemStack.asNMSCopy(new ItemStack(Material.LINGERING_POTION, 1)));
            } else {
                launch = new net.minecraft.world.entity.projectile.ThrownPotion((Level)world, this.getHandle());
                ((net.minecraft.world.entity.projectile.ThrownPotion)launch).m_37446_(CraftItemStack.asNMSCopy(new ItemStack(Material.SPLASH_POTION, 1)));
            }
            ((ThrowableProjectile)launch).m_37251_((net.minecraft.world.entity.Entity)this.getHandle(), this.getHandle().m_146909_(), this.getHandle().m_146908_(), -20.0f, 0.5f, 1.0f);
        } else if (ThrownExpBottle.class.isAssignableFrom(projectile)) {
            launch = new ThrownExperienceBottle((Level)world, this.getHandle());
            ((ThrowableProjectile)launch).m_37251_((net.minecraft.world.entity.Entity)this.getHandle(), this.getHandle().m_146909_(), this.getHandle().m_146908_(), -20.0f, 0.7f, 1.0f);
        } else if (FishHook.class.isAssignableFrom(projectile) && this.getHandle() instanceof Player) {
            launch = new FishingHook((Player)this.getHandle(), (Level)world, 0, 0);
        } else if (Fireball.class.isAssignableFrom(projectile)) {
            Location location = this.getEyeLocation();
            Vector direction = location.getDirection().multiply(10);
            launch = org.bukkit.entity.SmallFireball.class.isAssignableFrom(projectile) ? new SmallFireball((Level)world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ()) : (org.bukkit.entity.WitherSkull.class.isAssignableFrom(projectile) ? new WitherSkull((Level)world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ()) : (DragonFireball.class.isAssignableFrom(projectile) ? new net.minecraft.world.entity.projectile.DragonFireball((Level)world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ()) : new LargeFireball((Level)world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ(), 1)));
            ((AbstractHurtingProjectile)launch).projectileSource = this;
            launch.m_7678_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } else if (org.bukkit.entity.LlamaSpit.class.isAssignableFrom(projectile)) {
            Location location = this.getEyeLocation();
            Vector direction = location.getDirection();
            launch = EntityType.f_20467_.m_20615_((Level)world);
            ((LlamaSpit)launch).m_5602_((net.minecraft.world.entity.Entity)this.getHandle());
            ((LlamaSpit)launch).m_6686_(direction.getX(), direction.getY(), direction.getZ(), 1.5f, 10.0f);
            launch.m_7678_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } else if (ShulkerBullet.class.isAssignableFrom(projectile)) {
            Location location = this.getEyeLocation();
            launch = new net.minecraft.world.entity.projectile.ShulkerBullet((Level)world, this.getHandle(), null, null);
            launch.m_7678_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } else if (Firework.class.isAssignableFrom(projectile)) {
            Location location = this.getEyeLocation();
            launch = new FireworkRocketEntity((Level)world, net.minecraft.world.item.ItemStack.f_41583_, this.getHandle());
            launch.m_7678_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }
        Preconditions.checkArgument((launch != null ? 1 : 0) != 0, (String)"Projectile (%s) not supported", (Object)projectile.getName());
        if (velocity != null) {
            ((Projectile)((Object)launch.getBukkitEntity())).setVelocity(velocity);
        }
        world.m_7967_((net.minecraft.world.entity.Entity)launch);
        return (T)((Projectile)((Object)launch.getBukkitEntity()));
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot check line of sight during world generation");
        return this.getHandle().m_142582_(((CraftEntity)other).getHandle());
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return this.getHandle() instanceof Mob && !((Mob)this.getHandle()).m_21532_();
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        if (this.getHandle() instanceof Mob) {
            ((Mob)this.getHandle()).setPersistenceRequired(!remove);
        }
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.equipment;
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        if (this.getHandle() instanceof Mob) {
            ((Mob)this.getHandle()).m_21553_(pickup);
        } else {
            this.getHandle().bukkitPickUpLoot = pickup;
        }
    }

    @Override
    public boolean getCanPickupItems() {
        if (this.getHandle() instanceof Mob) {
            return ((Mob)this.getHandle()).m_21531_();
        }
        return this.getHandle().bukkitPickUpLoot;
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        if (this.getHealth() == 0.0) {
            return false;
        }
        return super.teleport(location, cause);
    }

    @Override
    public boolean isLeashed() {
        if (!(this.getHandle() instanceof Mob)) {
            return false;
        }
        return ((Mob)this.getHandle()).m_21524_() != null;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        Preconditions.checkState((boolean)this.isLeashed(), (Object)"Entity not leashed");
        return ((Mob)this.getHandle()).m_21524_().getBukkitEntity();
    }

    private boolean unleash() {
        if (!this.isLeashed()) {
            return false;
        }
        ((Mob)this.getHandle()).m_21455_(true, false);
        return true;
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        if (this.getHandle().generation || this.getHandle() instanceof WitherBoss || !(this.getHandle() instanceof Mob)) {
            return false;
        }
        if (holder == null) {
            return this.unleash();
        }
        if (holder.isDead()) {
            return false;
        }
        this.unleash();
        ((Mob)this.getHandle()).m_21463_(((CraftEntity)holder).getHandle(), true);
        return true;
    }

    @Override
    public boolean isGliding() {
        return this.getHandle().m_20291_(7);
    }

    @Override
    public void setGliding(boolean gliding) {
        this.getHandle().m_20115_(7, gliding);
    }

    @Override
    public boolean isSwimming() {
        return this.getHandle().m_6069_();
    }

    @Override
    public void setSwimming(boolean swimming) {
        this.getHandle().m_20282_(swimming);
    }

    @Override
    public boolean isRiptiding() {
        return this.getHandle().m_21209_();
    }

    @Override
    public boolean isSleeping() {
        return this.getHandle().m_5803_();
    }

    @Override
    public boolean isClimbing() {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot check if climbing during world generation");
        return this.getHandle().m_6147_();
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return this.getHandle().craftAttributes.getAttribute(attribute);
    }

    @Override
    public void setAI(boolean ai) {
        if (this.getHandle() instanceof Mob) {
            ((Mob)this.getHandle()).m_21557_(!ai);
        }
    }

    @Override
    public boolean hasAI() {
        return this.getHandle() instanceof Mob ? !((Mob)this.getHandle()).m_21525_() : false;
    }

    @Override
    public void attack(Entity target) {
        Preconditions.checkArgument((target != null ? 1 : 0) != 0, (Object)"target == null");
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot attack during world generation");
        if (this.getHandle() instanceof Player) {
            ((Player)this.getHandle()).m_5706_(((CraftEntity)target).getHandle());
        } else {
            this.getHandle().m_7327_(((CraftEntity)target).getHandle());
        }
    }

    @Override
    public void swingMainHand() {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot swing hand during world generation");
        this.getHandle().m_21011_(InteractionHand.MAIN_HAND, true);
    }

    @Override
    public void swingOffHand() {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot swing hand during world generation");
        this.getHandle().m_21011_(InteractionHand.OFF_HAND, true);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void playHurtAnimation(float yaw) {
        Level level = this.getHandle().m_9236_();
        if (level instanceof ServerLevel var2_3) {
            void world;
            float actualYaw = yaw + 90.0f;
            ClientboundHurtAnimationPacket packet = new ClientboundHurtAnimationPacket(this.getEntityId(), actualYaw);
            world.m_7726_().m_8394_((net.minecraft.world.entity.Entity)this.getHandle(), (Packet)packet);
        }
    }

    @Override
    public void setCollidable(boolean collidable) {
        this.getHandle().collides = collidable;
    }

    @Override
    public boolean isCollidable() {
        return this.getHandle().collides;
    }

    @Override
    public Set<UUID> getCollidableExemptions() {
        return this.getHandle().collidableExemptions;
    }

    @Override
    public <T> T getMemory(MemoryKey<T> memoryKey) {
        return this.getHandle().m_6274_().m_21952_(CraftMemoryKey.fromMemoryKey(memoryKey)).map(CraftMemoryMapper::fromNms).orElse(null);
    }

    @Override
    public <T> void setMemory(MemoryKey<T> memoryKey, T t) {
        this.getHandle().m_6274_().m_21879_(CraftMemoryKey.fromMemoryKey(memoryKey), CraftMemoryMapper.toNms(t));
    }

    @Override
    public Sound getHurtSound() {
        SoundEvent sound = this.getHandle().getHurtSound0(this.getHandle().m_269291_().m_269264_());
        return sound != null ? CraftSound.getBukkit(sound) : null;
    }

    @Override
    public Sound getDeathSound() {
        SoundEvent sound = this.getHandle().getDeathSound0();
        return sound != null ? CraftSound.getBukkit(sound) : null;
    }

    @Override
    public Sound getFallDamageSound(int fallHeight) {
        return CraftSound.getBukkit(this.getHandle().getFallDamageSound0(fallHeight));
    }

    @Override
    public Sound getFallDamageSoundSmall() {
        return CraftSound.getBukkit(this.getHandle().m_196493_().f_196626_());
    }

    @Override
    public Sound getFallDamageSoundBig() {
        return CraftSound.getBukkit(this.getHandle().m_196493_().f_196627_());
    }

    @Override
    public Sound getDrinkingSound(ItemStack itemStack) {
        Preconditions.checkArgument((itemStack != null ? 1 : 0) != 0, (Object)"itemStack must not be null");
        return CraftSound.getBukkit(this.getHandle().getDrinkingSound0(CraftItemStack.asNMSCopy(itemStack)));
    }

    @Override
    public Sound getEatingSound(ItemStack itemStack) {
        Preconditions.checkArgument((itemStack != null ? 1 : 0) != 0, (Object)"itemStack must not be null");
        return CraftSound.getBukkit(this.getHandle().getEatingSound0(CraftItemStack.asNMSCopy(itemStack)));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return this.getHandle().m_6040_();
    }

    @Override
    public EntityCategory getCategory() {
        MobType type = this.getHandle().m_6336_();
        if (type == MobType.f_21640_) {
            return EntityCategory.NONE;
        }
        if (type == MobType.f_21641_) {
            return EntityCategory.UNDEAD;
        }
        if (type == MobType.f_21642_) {
            return EntityCategory.ARTHROPOD;
        }
        if (type == MobType.f_21643_) {
            return EntityCategory.ILLAGER;
        }
        if (type == MobType.f_21644_) {
            return EntityCategory.WATER;
        }
        throw new UnsupportedOperationException("Unsupported monster type: " + type + ". This is a bug, report this to Spigot.");
    }

    @Override
    public boolean isInvisible() {
        return this.getHandle().m_20145_();
    }

    @Override
    public void setInvisible(boolean invisible) {
        this.getHandle().persistentInvisibility = invisible;
        this.getHandle().m_20115_(5, invisible);
    }
}

