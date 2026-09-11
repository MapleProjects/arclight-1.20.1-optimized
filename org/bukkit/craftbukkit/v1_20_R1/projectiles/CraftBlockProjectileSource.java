/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockSource
 *  net.minecraft.core.BlockSourceImpl
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Position
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.SmallFireball
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.SpectralArrow
 *  net.minecraft.world.entity.projectile.ThrowableProjectile
 *  net.minecraft.world.entity.projectile.ThrownEgg
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 *  net.minecraft.world.entity.projectile.ThrownExperienceBottle
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.DispenserBlock
 *  net.minecraft.world.level.block.entity.DispenserBlockEntity
 *  net.minecraft.world.level.block.state.properties.Property
 */
package org.bukkit.craftbukkit.v1_20_R1.projectiles;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockSource;
import net.minecraft.core.BlockSourceImpl;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LingeringPotion;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.projectiles.BlockProjectileSource;
import org.bukkit.util.Vector;

public class CraftBlockProjectileSource
implements BlockProjectileSource {
    private final DispenserBlockEntity dispenserBlock;

    public CraftBlockProjectileSource(DispenserBlockEntity dispenserBlock) {
        this.dispenserBlock = dispenserBlock;
    }

    @Override
    public Block getBlock() {
        return this.dispenserBlock.m_58904_().getWorld().getBlockAt(this.dispenserBlock.m_58899_().m_123341_(), this.dispenserBlock.m_58899_().m_123342_(), this.dispenserBlock.m_58899_().m_123343_());
    }

    @Override
    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> projectile) {
        return this.launchProjectile(projectile, null);
    }

    @Override
    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        Preconditions.checkArgument((this.getBlock().getType() == Material.DISPENSER ? 1 : 0) != 0, (Object)"Block is no longer dispenser");
        BlockSourceImpl isourceblock = new BlockSourceImpl((ServerLevel)this.dispenserBlock.m_58904_(), this.dispenserBlock.m_58899_());
        Position iposition = DispenserBlock.m_52720_((BlockSource)isourceblock);
        Direction enumdirection = (Direction)isourceblock.m_6414_().m_61143_((Property)DispenserBlock.f_52659_);
        Level world = this.dispenserBlock.m_58904_();
        Object launch = null;
        if (org.bukkit.entity.Snowball.class.isAssignableFrom(projectile)) {
            launch = new Snowball(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
        } else if (Egg.class.isAssignableFrom(projectile)) {
            launch = new ThrownEgg(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
        } else if (EnderPearl.class.isAssignableFrom(projectile)) {
            launch = new ThrownEnderpearl(world, null);
            launch.m_6034_(iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
        } else if (ThrownExpBottle.class.isAssignableFrom(projectile)) {
            launch = new ThrownExperienceBottle(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
        } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
            if (LingeringPotion.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.ThrownPotion(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
                ((net.minecraft.world.entity.projectile.ThrownPotion)launch).m_37446_(CraftItemStack.asNMSCopy(new ItemStack(Material.LINGERING_POTION, 1)));
            } else {
                launch = new net.minecraft.world.entity.projectile.ThrownPotion(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
                ((net.minecraft.world.entity.projectile.ThrownPotion)launch).m_37446_(CraftItemStack.asNMSCopy(new ItemStack(Material.SPLASH_POTION, 1)));
            }
        } else if (AbstractArrow.class.isAssignableFrom(projectile)) {
            if (TippedArrow.class.isAssignableFrom(projectile)) {
                launch = new Arrow(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
                ((org.bukkit.entity.Arrow)((Object)launch.getBukkitEntity())).setBasePotionData(new PotionData(PotionType.WATER, false, false));
            } else {
                launch = SpectralArrow.class.isAssignableFrom(projectile) ? new net.minecraft.world.entity.projectile.SpectralArrow(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_()) : new Arrow(world, iposition.m_7096_(), iposition.m_7098_(), iposition.m_7094_());
            }
            ((net.minecraft.world.entity.projectile.AbstractArrow)launch).f_36705_ = AbstractArrow.Pickup.ALLOWED;
            ((net.minecraft.world.entity.projectile.AbstractArrow)launch).projectileSource = this;
        } else if (Fireball.class.isAssignableFrom(projectile)) {
            double d0 = iposition.m_7096_() + (double)((float)enumdirection.m_122429_() * 0.3f);
            double d1 = iposition.m_7098_() + (double)((float)enumdirection.m_122430_() * 0.3f);
            double d2 = iposition.m_7094_() + (double)((float)enumdirection.m_122431_() * 0.3f);
            RandomSource random = world.f_46441_;
            double d3 = random.m_188583_() * 0.05 + (double)enumdirection.m_122429_();
            double d4 = random.m_188583_() * 0.05 + (double)enumdirection.m_122430_();
            double d5 = random.m_188583_() * 0.05 + (double)enumdirection.m_122431_();
            if (SmallFireball.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.SmallFireball(world, null, d0, d1, d2);
            } else if (WitherSkull.class.isAssignableFrom(projectile)) {
                launch = EntityType.f_20498_.m_20615_(world);
                launch.m_6034_(d0, d1, d2);
                double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                ((AbstractHurtingProjectile)launch).f_36813_ = d3 / d6 * 0.1;
                ((AbstractHurtingProjectile)launch).f_36814_ = d4 / d6 * 0.1;
                ((AbstractHurtingProjectile)launch).f_36815_ = d5 / d6 * 0.1;
            } else {
                launch = EntityType.f_20463_.m_20615_(world);
                launch.m_6034_(d0, d1, d2);
                double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                ((AbstractHurtingProjectile)launch).f_36813_ = d3 / d6 * 0.1;
                ((AbstractHurtingProjectile)launch).f_36814_ = d4 / d6 * 0.1;
                ((AbstractHurtingProjectile)launch).f_36815_ = d5 / d6 * 0.1;
            }
            ((AbstractHurtingProjectile)launch).projectileSource = this;
        }
        Preconditions.checkArgument((launch != null ? 1 : 0) != 0, (Object)"Projectile not supported");
        if (launch instanceof Projectile) {
            if (launch instanceof ThrowableProjectile) {
                ((ThrowableProjectile)launch).projectileSource = this;
            }
            float a = 6.0f;
            float b = 1.1f;
            if (launch instanceof net.minecraft.world.entity.projectile.ThrownPotion || launch instanceof ThrownExpBottle) {
                a *= 0.5f;
                b *= 1.25f;
            }
            ((Projectile)launch).m_6686_((double)enumdirection.m_122429_(), (double)((float)enumdirection.m_122430_() + 0.1f), (double)enumdirection.m_122431_(), b, a);
        }
        if (velocity != null) {
            ((org.bukkit.entity.Projectile)((Object)launch.getBukkitEntity())).setVelocity(velocity);
        }
        world.m_7967_((Entity)launch);
        return (T)((org.bukkit.entity.Projectile)((Object)launch.getBukkitEntity()));
    }
}

