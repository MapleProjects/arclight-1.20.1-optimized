/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.level.block.entity.SkullBlockEntity
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import com.mojang.authlib.GameProfile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Rotatable;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerProfile;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.Nullable;

public class CraftSkull
extends CraftBlockEntityState<SkullBlockEntity>
implements Skull {
    private static final int MAX_OWNER_LENGTH = 16;
    private GameProfile profile;

    public CraftSkull(World world, SkullBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void load(SkullBlockEntity skull) {
        super.load(skull);
        this.profile = skull.f_59757_;
    }

    static int getSkullType(SkullType type) {
        switch (type) {
            default: {
                return 0;
            }
            case WITHER: {
                return 1;
            }
            case ZOMBIE: {
                return 2;
            }
            case PLAYER: {
                return 3;
            }
            case CREEPER: {
                return 4;
            }
            case DRAGON: 
        }
        return 5;
    }

    @Override
    public boolean hasOwner() {
        return this.profile != null;
    }

    @Override
    public String getOwner() {
        return this.hasOwner() ? this.profile.getName() : null;
    }

    @Override
    public boolean setOwner(String name) {
        if (name == null || name.length() > 16) {
            return false;
        }
        GameProfile profile = MinecraftServer.getServer().m_129927_().m_10996_(name).orElse(null);
        if (profile == null) {
            return false;
        }
        this.profile = profile;
        return true;
    }

    @Override
    public OfflinePlayer getOwningPlayer() {
        if (this.profile != null) {
            if (this.profile.getId() != null) {
                return Bukkit.getOfflinePlayer(this.profile.getId());
            }
            if (this.profile.getName() != null) {
                return Bukkit.getOfflinePlayer(this.profile.getName());
            }
        }
        return null;
    }

    @Override
    public void setOwningPlayer(OfflinePlayer player) {
        Preconditions.checkNotNull((Object)player, (Object)"player");
        this.profile = player instanceof CraftPlayer ? ((CraftPlayer)player).getProfile() : new GameProfile(player.getUniqueId(), player.getName());
    }

    @Override
    public PlayerProfile getOwnerProfile() {
        if (!this.hasOwner()) {
            return null;
        }
        return new CraftPlayerProfile(this.profile);
    }

    @Override
    public void setOwnerProfile(PlayerProfile profile) {
        this.profile = profile == null ? null : CraftPlayerProfile.validateSkullProfile(((CraftPlayerProfile)profile).buildGameProfile());
    }

    @Override
    public NamespacedKey getNoteBlockSound() {
        ResourceLocation key = ((SkullBlockEntity)this.getSnapshot()).m_262374_();
        return key != null ? CraftNamespacedKey.fromMinecraft(key) : null;
    }

    @Override
    public void setNoteBlockSound(@Nullable NamespacedKey namespacedKey) {
        if (namespacedKey == null) {
            ((SkullBlockEntity)this.getSnapshot()).f_262250_ = null;
            return;
        }
        ((SkullBlockEntity)this.getSnapshot()).f_262250_ = CraftNamespacedKey.toMinecraft(namespacedKey);
    }

    @Override
    public BlockFace getRotation() {
        BlockData blockData = this.getBlockData();
        return blockData instanceof Rotatable ? ((Rotatable)blockData).getRotation() : ((Directional)blockData).getFacing();
    }

    @Override
    public void setRotation(BlockFace rotation) {
        BlockData blockData = this.getBlockData();
        if (blockData instanceof Rotatable) {
            ((Rotatable)blockData).setRotation(rotation);
        } else {
            ((Directional)blockData).setFacing(rotation);
        }
        this.setBlockData(blockData);
    }

    @Override
    public SkullType getSkullType() {
        switch (this.getType()) {
            case SKELETON_SKULL: 
            case SKELETON_WALL_SKULL: {
                return SkullType.SKELETON;
            }
            case WITHER_SKELETON_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: {
                return SkullType.WITHER;
            }
            case ZOMBIE_HEAD: 
            case ZOMBIE_WALL_HEAD: {
                return SkullType.ZOMBIE;
            }
            case PIGLIN_HEAD: 
            case PIGLIN_WALL_HEAD: {
                return SkullType.PIGLIN;
            }
            case PLAYER_HEAD: 
            case PLAYER_WALL_HEAD: {
                return SkullType.PLAYER;
            }
            case CREEPER_HEAD: 
            case CREEPER_WALL_HEAD: {
                return SkullType.CREEPER;
            }
            case DRAGON_HEAD: 
            case DRAGON_WALL_HEAD: {
                return SkullType.DRAGON;
            }
        }
        throw new IllegalArgumentException("Unknown SkullType for " + this.getType());
    }

    @Override
    public void setSkullType(SkullType skullType) {
        throw new UnsupportedOperationException("Must change block type");
    }

    @Override
    public void applyTo(SkullBlockEntity skull) {
        super.applyTo(skull);
        if (this.getSkullType() == SkullType.PLAYER) {
            skull.m_59769_(this.profile);
        }
    }
}

