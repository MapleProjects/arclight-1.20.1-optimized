/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.entity.SkullBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerProfile;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaSkull
extends CraftMetaItem
implements SkullMeta {
    private static final Set<Material> SKULL_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.CREEPER_HEAD, Material.CREEPER_WALL_HEAD, Material.DRAGON_HEAD, Material.DRAGON_WALL_HEAD, Material.PIGLIN_HEAD, Material.PIGLIN_WALL_HEAD, Material.PLAYER_HEAD, Material.PLAYER_WALL_HEAD, Material.SKELETON_SKULL, Material.SKELETON_WALL_SKULL, Material.WITHER_SKELETON_SKULL, Material.WITHER_SKELETON_WALL_SKULL, Material.ZOMBIE_HEAD, Material.ZOMBIE_WALL_HEAD});
    static final CraftMetaItem.ItemMetaKey SKULL_PROFILE = new CraftMetaItem.ItemMetaKey("SkullProfile");
    static final CraftMetaItem.ItemMetaKey SKULL_OWNER = new CraftMetaItem.ItemMetaKey("SkullOwner", "skull-owner");
    static final CraftMetaItem.ItemMetaKey BLOCK_ENTITY_TAG = new CraftMetaItem.ItemMetaKey("BlockEntityTag");
    static final CraftMetaItem.ItemMetaKey NOTE_BLOCK_SOUND = new CraftMetaItem.ItemMetaKey("note_block_sound");
    static final int MAX_OWNER_LENGTH = 16;
    private GameProfile profile;
    private CompoundTag serializedProfile;
    private ResourceLocation noteBlockSound;

    public CraftMetaSkull(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaSkull)) {
            return;
        }
        CraftMetaSkull skullMeta = (CraftMetaSkull)meta;
        this.setProfile(skullMeta.profile);
        this.noteBlockSound = skullMeta.noteBlockSound;
    }

    CraftMetaSkull(CompoundTag tag) {
        super(tag);
        CompoundTag nbtTagCompound;
        if (tag.m_128425_(CraftMetaSkull.SKULL_OWNER.NBT, 10)) {
            this.setProfile(NbtUtils.m_129228_((CompoundTag)tag.m_128469_(CraftMetaSkull.SKULL_OWNER.NBT)));
        } else if (tag.m_128425_(CraftMetaSkull.SKULL_OWNER.NBT, 8) && !tag.m_128461_(CraftMetaSkull.SKULL_OWNER.NBT).isEmpty()) {
            this.setProfile(new GameProfile(null, tag.m_128461_(CraftMetaSkull.SKULL_OWNER.NBT)));
        }
        if (tag.m_128425_(CraftMetaSkull.BLOCK_ENTITY_TAG.NBT, 10) && (nbtTagCompound = tag.m_128469_(CraftMetaSkull.BLOCK_ENTITY_TAG.NBT).m_6426_()).m_128425_(CraftMetaSkull.NOTE_BLOCK_SOUND.NBT, 8)) {
            this.noteBlockSound = ResourceLocation.m_135820_((String)nbtTagCompound.m_128461_(CraftMetaSkull.NOTE_BLOCK_SOUND.NBT));
        }
    }

    CraftMetaSkull(Map<String, Object> map) {
        super(map);
        Object object;
        if (this.profile == null) {
            object = map.get(CraftMetaSkull.SKULL_OWNER.BUKKIT);
            if (object instanceof PlayerProfile) {
                this.setOwnerProfile((PlayerProfile)object);
            } else {
                this.setOwner(CraftMetaItem.SerializableMeta.getString(map, CraftMetaSkull.SKULL_OWNER.BUKKIT, true));
            }
        }
        if (this.noteBlockSound == null) {
            object = map.get(CraftMetaSkull.NOTE_BLOCK_SOUND.BUKKIT);
            if (object instanceof NamespacedKey) {
                this.setNoteBlockSound((NamespacedKey)object);
            } else {
                this.setNoteBlockSound(CraftMetaItem.SerializableMeta.getObject(NamespacedKey.class, map, CraftMetaSkull.NOTE_BLOCK_SOUND.BUKKIT, true));
            }
        }
    }

    @Override
    void deserializeInternal(CompoundTag tag, Object context) {
        CompoundTag nbtTagCompound;
        super.deserializeInternal(tag, context);
        if (tag.m_128425_(CraftMetaSkull.SKULL_PROFILE.NBT, 10)) {
            CompoundTag skullTag = tag.m_128469_(CraftMetaSkull.SKULL_PROFILE.NBT);
            if (skullTag.m_128425_("Id", 8)) {
                UUID uuid = UUID.fromString(skullTag.m_128461_("Id"));
                skullTag.m_128362_("Id", uuid);
            }
            this.setProfile(NbtUtils.m_129228_((CompoundTag)skullTag));
        }
        if (tag.m_128425_(CraftMetaSkull.BLOCK_ENTITY_TAG.NBT, 10) && (nbtTagCompound = tag.m_128469_(CraftMetaSkull.BLOCK_ENTITY_TAG.NBT).m_6426_()).m_128425_(CraftMetaSkull.NOTE_BLOCK_SOUND.NBT, 8)) {
            this.noteBlockSound = ResourceLocation.m_135820_((String)nbtTagCompound.m_128461_(CraftMetaSkull.NOTE_BLOCK_SOUND.NBT));
        }
    }

    private void setProfile(GameProfile profile) {
        this.profile = profile;
        this.serializedProfile = profile == null ? null : NbtUtils.m_129230_((CompoundTag)new CompoundTag(), (GameProfile)profile);
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.profile != null) {
            tag.m_128365_(CraftMetaSkull.SKULL_OWNER.NBT, (Tag)this.serializedProfile);
            SkullBlockEntity.m_155738_((GameProfile)this.profile, filledProfile -> {
                this.setProfile((GameProfile)filledProfile);
                tag.m_128365_(CraftMetaSkull.SKULL_OWNER.NBT, (Tag)this.serializedProfile);
            });
        }
        if (this.noteBlockSound != null) {
            CompoundTag nbtTagCompound = new CompoundTag();
            nbtTagCompound.m_128359_(CraftMetaSkull.NOTE_BLOCK_SOUND.NBT, this.noteBlockSound.toString());
            tag.m_128365_(CraftMetaSkull.BLOCK_ENTITY_TAG.NBT, (Tag)nbtTagCompound);
        }
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isSkullEmpty();
    }

    boolean isSkullEmpty() {
        return this.profile == null && this.noteBlockSound == null;
    }

    @Override
    boolean applicableTo(Material type) {
        return SKULL_MATERIALS.contains(type);
    }

    @Override
    public CraftMetaSkull clone() {
        return (CraftMetaSkull)super.clone();
    }

    @Override
    public boolean hasOwner() {
        return this.profile != null && this.profile.getName() != null;
    }

    @Override
    public String getOwner() {
        return this.hasOwner() ? this.profile.getName() : null;
    }

    @Override
    public OfflinePlayer getOwningPlayer() {
        if (this.hasOwner()) {
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
    public boolean setOwner(String name) {
        if (name != null && name.length() > 16) {
            return false;
        }
        if (name == null) {
            this.setProfile(null);
        } else {
            this.setProfile(new GameProfile(null, name));
        }
        return true;
    }

    @Override
    public boolean setOwningPlayer(OfflinePlayer owner) {
        if (owner == null) {
            this.setProfile(null);
        } else if (owner instanceof CraftPlayer) {
            this.setProfile(((CraftPlayer)owner).getProfile());
        } else {
            this.setProfile(new GameProfile(owner.getUniqueId(), owner.getName()));
        }
        return true;
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
        if (profile == null) {
            this.setProfile(null);
        } else {
            this.setProfile(CraftPlayerProfile.validateSkullProfile(((CraftPlayerProfile)profile).buildGameProfile()));
        }
    }

    @Override
    public void setNoteBlockSound(NamespacedKey noteBlockSound) {
        this.noteBlockSound = noteBlockSound == null ? null : CraftNamespacedKey.toMinecraft(noteBlockSound);
    }

    @Override
    public NamespacedKey getNoteBlockSound() {
        return this.noteBlockSound == null ? null : CraftNamespacedKey.fromMinecraft(this.noteBlockSound);
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasOwner()) {
            hash = 61 * hash + this.profile.hashCode();
        }
        if (this.noteBlockSound != null) {
            hash = 61 * hash + this.noteBlockSound.hashCode();
        }
        return original != hash ? CraftMetaSkull.class.hashCode() ^ hash : hash;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaSkull) {
            CraftMetaSkull that = (CraftMetaSkull)meta;
            return (this.profile != null ? that.profile != null && this.serializedProfile.equals((Object)that.serializedProfile) : that.profile == null) && Objects.equals(this.noteBlockSound, that.noteBlockSound);
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaSkull || this.isSkullEmpty());
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.profile != null) {
            return builder.put((Object)CraftMetaSkull.SKULL_OWNER.BUKKIT, (Object)new CraftPlayerProfile(this.profile));
        }
        NamespacedKey namespacedKeyNB = this.getNoteBlockSound();
        if (namespacedKeyNB != null) {
            return builder.put((Object)CraftMetaSkull.NOTE_BLOCK_SOUND.BUKKIT, (Object)namespacedKeyNB);
        }
        return builder;
    }
}

