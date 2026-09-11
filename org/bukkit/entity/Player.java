/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.ApiStatus$Internal
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.BanEntry;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.WeatherType;
import org.bukkit.WorldBorder;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.conversations.Conversable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Player
extends HumanEntity,
Conversable,
OfflinePlayer,
PluginMessageRecipient {
    @Override
    @NotNull
    public String getName();

    @NotNull
    public String getDisplayName();

    public void setDisplayName(@Nullable String var1);

    @NotNull
    public String getPlayerListName();

    public void setPlayerListName(@Nullable String var1);

    @Nullable
    public String getPlayerListHeader();

    @Nullable
    public String getPlayerListFooter();

    public void setPlayerListHeader(@Nullable String var1);

    public void setPlayerListFooter(@Nullable String var1);

    public void setPlayerListHeaderFooter(@Nullable String var1, @Nullable String var2);

    public void setCompassTarget(@NotNull Location var1);

    @NotNull
    public Location getCompassTarget();

    @Nullable
    public InetSocketAddress getAddress();

    @Override
    public void sendRawMessage(@NotNull String var1);

    public void kickPlayer(@Nullable String var1);

    @Nullable
    public BanEntry<PlayerProfile> ban(@Nullable String var1, @Nullable Date var2, @Nullable String var3, boolean var4);

    @Nullable
    public BanEntry<PlayerProfile> ban(@Nullable String var1, @Nullable Instant var2, @Nullable String var3, boolean var4);

    @Nullable
    public BanEntry<PlayerProfile> ban(@Nullable String var1, @Nullable Duration var2, @Nullable String var3, boolean var4);

    @Nullable
    public BanEntry<InetAddress> banIp(@Nullable String var1, @Nullable Date var2, @Nullable String var3, boolean var4);

    @Nullable
    public BanEntry<InetAddress> banIp(@Nullable String var1, @Nullable Instant var2, @Nullable String var3, boolean var4);

    @Nullable
    public BanEntry<InetAddress> banIp(@Nullable String var1, @Nullable Duration var2, @Nullable String var3, boolean var4);

    public void chat(@NotNull String var1);

    public boolean performCommand(@NotNull String var1);

    @Override
    @Deprecated
    public boolean isOnGround();

    public boolean isSneaking();

    public void setSneaking(boolean var1);

    public boolean isSprinting();

    public void setSprinting(boolean var1);

    public void saveData();

    public void loadData();

    public void setSleepingIgnored(boolean var1);

    public boolean isSleepingIgnored();

    @Override
    @Nullable
    public Location getBedSpawnLocation();

    public void setBedSpawnLocation(@Nullable Location var1);

    public void setBedSpawnLocation(@Nullable Location var1, boolean var2);

    @Deprecated
    public void playNote(@NotNull Location var1, byte var2, byte var3);

    public void playNote(@NotNull Location var1, @NotNull Instrument var2, @NotNull Note var3);

    public void playSound(@NotNull Location var1, @NotNull Sound var2, float var3, float var4);

    public void playSound(@NotNull Location var1, @NotNull String var2, float var3, float var4);

    public void playSound(@NotNull Location var1, @NotNull Sound var2, @NotNull SoundCategory var3, float var4, float var5);

    public void playSound(@NotNull Location var1, @NotNull String var2, @NotNull SoundCategory var3, float var4, float var5);

    public void playSound(@NotNull Entity var1, @NotNull Sound var2, float var3, float var4);

    public void playSound(@NotNull Entity var1, @NotNull String var2, float var3, float var4);

    public void playSound(@NotNull Entity var1, @NotNull Sound var2, @NotNull SoundCategory var3, float var4, float var5);

    public void playSound(@NotNull Entity var1, @NotNull String var2, @NotNull SoundCategory var3, float var4, float var5);

    public void stopSound(@NotNull Sound var1);

    public void stopSound(@NotNull String var1);

    public void stopSound(@NotNull Sound var1, @Nullable SoundCategory var2);

    public void stopSound(@NotNull String var1, @Nullable SoundCategory var2);

    public void stopSound(@NotNull SoundCategory var1);

    public void stopAllSounds();

    @Deprecated
    public void playEffect(@NotNull Location var1, @NotNull Effect var2, int var3);

    public <T> void playEffect(@NotNull Location var1, @NotNull Effect var2, @Nullable T var3);

    public boolean breakBlock(@NotNull Block var1);

    @Deprecated
    public void sendBlockChange(@NotNull Location var1, @NotNull Material var2, byte var3);

    public void sendBlockChange(@NotNull Location var1, @NotNull BlockData var2);

    public void sendBlockChanges(@NotNull Collection<BlockState> var1);

    @Deprecated
    public void sendBlockChanges(@NotNull Collection<BlockState> var1, boolean var2);

    public void sendBlockDamage(@NotNull Location var1, float var2);

    public void sendBlockDamage(@NotNull Location var1, float var2, @NotNull Entity var3);

    public void sendBlockDamage(@NotNull Location var1, float var2, int var3);

    public void sendEquipmentChange(@NotNull LivingEntity var1, @NotNull EquipmentSlot var2, @Nullable ItemStack var3);

    public void sendEquipmentChange(@NotNull LivingEntity var1, @NotNull Map<EquipmentSlot, ItemStack> var2);

    public void sendSignChange(@NotNull Location var1, @Nullable String[] var2) throws IllegalArgumentException;

    public void sendSignChange(@NotNull Location var1, @Nullable String[] var2, @NotNull DyeColor var3) throws IllegalArgumentException;

    public void sendSignChange(@NotNull Location var1, @Nullable String[] var2, @NotNull DyeColor var3, boolean var4) throws IllegalArgumentException;

    @ApiStatus.Experimental
    public void sendBlockUpdate(@NotNull Location var1, @NotNull TileState var2) throws IllegalArgumentException;

    public void sendMap(@NotNull MapView var1);

    public void sendHurtAnimation(float var1);

    public void addCustomChatCompletions(@NotNull Collection<String> var1);

    public void removeCustomChatCompletions(@NotNull Collection<String> var1);

    public void setCustomChatCompletions(@NotNull Collection<String> var1);

    @ApiStatus.Internal
    public void updateInventory();

    @Nullable
    public GameMode getPreviousGameMode();

    public void setPlayerTime(long var1, boolean var3);

    public long getPlayerTime();

    public long getPlayerTimeOffset();

    public boolean isPlayerTimeRelative();

    public void resetPlayerTime();

    public void setPlayerWeather(@NotNull WeatherType var1);

    @Nullable
    public WeatherType getPlayerWeather();

    public void resetPlayerWeather();

    public int getExpCooldown();

    public void setExpCooldown(int var1);

    public void giveExp(int var1);

    public void giveExpLevels(int var1);

    public float getExp();

    public void setExp(float var1);

    public int getLevel();

    public void setLevel(int var1);

    public int getTotalExperience();

    public void setTotalExperience(int var1);

    public void sendExperienceChange(float var1);

    public void sendExperienceChange(float var1, int var2);

    public boolean getAllowFlight();

    public void setAllowFlight(boolean var1);

    @Deprecated
    public void hidePlayer(@NotNull Player var1);

    public void hidePlayer(@NotNull Plugin var1, @NotNull Player var2);

    @Deprecated
    public void showPlayer(@NotNull Player var1);

    public void showPlayer(@NotNull Plugin var1, @NotNull Player var2);

    public boolean canSee(@NotNull Player var1);

    @ApiStatus.Experimental
    public void hideEntity(@NotNull Plugin var1, @NotNull Entity var2);

    @ApiStatus.Experimental
    public void showEntity(@NotNull Plugin var1, @NotNull Entity var2);

    @ApiStatus.Experimental
    public boolean canSee(@NotNull Entity var1);

    public boolean isFlying();

    public void setFlying(boolean var1);

    public void setFlySpeed(float var1) throws IllegalArgumentException;

    public void setWalkSpeed(float var1) throws IllegalArgumentException;

    public float getFlySpeed();

    public float getWalkSpeed();

    @Deprecated
    public void setTexturePack(@NotNull String var1);

    public void setResourcePack(@NotNull String var1);

    public void setResourcePack(@NotNull String var1, @Nullable byte[] var2);

    public void setResourcePack(@NotNull String var1, @Nullable byte[] var2, @Nullable String var3);

    public void setResourcePack(@NotNull String var1, @Nullable byte[] var2, boolean var3);

    public void setResourcePack(@NotNull String var1, @Nullable byte[] var2, @Nullable String var3, boolean var4);

    @NotNull
    public Scoreboard getScoreboard();

    public void setScoreboard(@NotNull Scoreboard var1) throws IllegalArgumentException, IllegalStateException;

    @Nullable
    public WorldBorder getWorldBorder();

    public void setWorldBorder(@Nullable WorldBorder var1);

    public void sendHealthUpdate(double var1, int var3, float var4);

    public void sendHealthUpdate();

    public boolean isHealthScaled();

    public void setHealthScaled(boolean var1);

    public void setHealthScale(double var1) throws IllegalArgumentException;

    public double getHealthScale();

    @Nullable
    public Entity getSpectatorTarget();

    public void setSpectatorTarget(@Nullable Entity var1);

    @Deprecated
    public void sendTitle(@Nullable String var1, @Nullable String var2);

    public void sendTitle(@Nullable String var1, @Nullable String var2, int var3, int var4, int var5);

    public void resetTitle();

    public void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3);

    public void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, @Nullable T var4);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, @Nullable T var9);

    public void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8);

    public void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, @Nullable T var10);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, @Nullable T var15);

    public void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10);

    public void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10, @Nullable T var12);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, @Nullable T var17);

    @NotNull
    public AdvancementProgress getAdvancementProgress(@NotNull Advancement var1);

    public int getClientViewDistance();

    public int getPing();

    @NotNull
    public String getLocale();

    public void updateCommands();

    public void openBook(@NotNull ItemStack var1);

    public void openSign(@NotNull Sign var1);

    public void openSign(@NotNull Sign var1, @NotNull Side var2);

    public void showDemoScreen();

    public boolean isAllowingServerListings();

    @Override
    @NotNull
    public Spigot spigot();

    public static class Spigot
    extends Entity.Spigot {
        @NotNull
        public InetSocketAddress getRawAddress() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void respawn() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @NotNull
        public Set<Player> getHiddenPlayers() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendMessage(@NotNull BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void sendMessage(BaseComponent ... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(@NotNull ChatMessageType position, @NotNull BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(@NotNull ChatMessageType position, BaseComponent ... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(@NotNull ChatMessageType position, @Nullable UUID sender, @NotNull BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void sendMessage(@NotNull ChatMessageType position, @Nullable UUID sender, BaseComponent ... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

