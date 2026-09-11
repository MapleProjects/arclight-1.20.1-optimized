/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  net.minecraft.network.protocol.game.ClientboundBossEventPacket
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.BossEvent$BossBarColor
 *  net.minecraft.world.BossEvent$BossBarOverlay
 */
package org.bukkit.craftbukkit.v1_20_R1.boss;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.network.protocol.game.ClientboundBossEventPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

public class CraftBossBar
implements BossBar {
    private final ServerBossEvent handle;
    private Map<BarFlag, FlagContainer> flags;

    public CraftBossBar(String title, BarColor color, BarStyle style, BarFlag ... flags) {
        this.handle = new ServerBossEvent(CraftChatMessage.fromString(title, true)[0], this.convertColor(color), this.convertStyle(style));
        this.initialize();
        BarFlag[] barFlagArray = flags;
        int n = flags.length;
        int n2 = 0;
        while (n2 < n) {
            BarFlag flag = barFlagArray[n2];
            this.addFlag(flag);
            ++n2;
        }
        this.setColor(color);
        this.setStyle(style);
    }

    public CraftBossBar(ServerBossEvent bossBattleServer) {
        this.handle = bossBattleServer;
        this.initialize();
    }

    private void initialize() {
        this.flags = new HashMap<BarFlag, FlagContainer>();
        this.flags.put(BarFlag.DARKEN_SKY, new FlagContainer(() -> ((ServerBossEvent)this.handle).m_18864_(), arg_0 -> ((ServerBossEvent)this.handle).m_7003_(arg_0)));
        this.flags.put(BarFlag.PLAY_BOSS_MUSIC, new FlagContainer(() -> ((ServerBossEvent)this.handle).m_18865_(), arg_0 -> ((ServerBossEvent)this.handle).m_7005_(arg_0)));
        this.flags.put(BarFlag.CREATE_FOG, new FlagContainer(() -> ((ServerBossEvent)this.handle).m_18866_(), arg_0 -> ((ServerBossEvent)this.handle).m_7006_(arg_0)));
    }

    private BarColor convertColor(BossEvent.BossBarColor color) {
        BarColor bukkitColor = BarColor.valueOf(color.name());
        return bukkitColor == null ? BarColor.WHITE : bukkitColor;
    }

    private BossEvent.BossBarColor convertColor(BarColor color) {
        BossEvent.BossBarColor nmsColor = BossEvent.BossBarColor.valueOf((String)color.name());
        return nmsColor == null ? BossEvent.BossBarColor.WHITE : nmsColor;
    }

    private BossEvent.BossBarOverlay convertStyle(BarStyle style) {
        switch (style) {
            default: {
                return BossEvent.BossBarOverlay.PROGRESS;
            }
            case SEGMENTED_6: {
                return BossEvent.BossBarOverlay.NOTCHED_6;
            }
            case SEGMENTED_10: {
                return BossEvent.BossBarOverlay.NOTCHED_10;
            }
            case SEGMENTED_12: {
                return BossEvent.BossBarOverlay.NOTCHED_12;
            }
            case SEGMENTED_20: 
        }
        return BossEvent.BossBarOverlay.NOTCHED_20;
    }

    private BarStyle convertStyle(BossEvent.BossBarOverlay style) {
        switch (style) {
            default: {
                return BarStyle.SOLID;
            }
            case NOTCHED_6: {
                return BarStyle.SEGMENTED_6;
            }
            case NOTCHED_10: {
                return BarStyle.SEGMENTED_10;
            }
            case NOTCHED_12: {
                return BarStyle.SEGMENTED_12;
            }
            case NOTCHED_20: 
        }
        return BarStyle.SEGMENTED_20;
    }

    @Override
    public String getTitle() {
        return CraftChatMessage.fromComponent(this.handle.f_18840_);
    }

    @Override
    public void setTitle(String title) {
        this.handle.f_18840_ = CraftChatMessage.fromString(title, true)[0];
        this.handle.m_143224_(ClientboundBossEventPacket::m_178651_);
    }

    @Override
    public BarColor getColor() {
        return this.convertColor(this.handle.f_18842_);
    }

    @Override
    public void setColor(BarColor color) {
        this.handle.f_18842_ = this.convertColor(color);
        this.handle.m_143224_(ClientboundBossEventPacket::m_178653_);
    }

    @Override
    public BarStyle getStyle() {
        return this.convertStyle(this.handle.f_18843_);
    }

    @Override
    public void setStyle(BarStyle style) {
        this.handle.f_18843_ = this.convertStyle(style);
        this.handle.m_143224_(ClientboundBossEventPacket::m_178653_);
    }

    @Override
    public void addFlag(BarFlag flag) {
        FlagContainer flagContainer = this.flags.get((Object)flag);
        if (flagContainer != null) {
            flagContainer.set.accept(true);
        }
    }

    @Override
    public void removeFlag(BarFlag flag) {
        FlagContainer flagContainer = this.flags.get((Object)flag);
        if (flagContainer != null) {
            flagContainer.set.accept(false);
        }
    }

    @Override
    public boolean hasFlag(BarFlag flag) {
        FlagContainer flagContainer = this.flags.get((Object)flag);
        if (flagContainer != null) {
            return flagContainer.get.get();
        }
        return false;
    }

    @Override
    public void setProgress(double progress) {
        Preconditions.checkArgument((progress >= 0.0 && progress <= 1.0 ? 1 : 0) != 0, (String)"Progress must be between 0.0 and 1.0 (%s)", (Object)progress);
        this.handle.m_142711_((float)progress);
    }

    @Override
    public double getProgress() {
        return this.handle.m_142717_();
    }

    @Override
    public void addPlayer(Player player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"player == null");
        Preconditions.checkArgument((((CraftPlayer)player).getHandle().f_8906_ != null ? 1 : 0) != 0, (Object)"player is not fully connected (wait for PlayerJoinEvent)");
        this.handle.m_6543_(((CraftPlayer)player).getHandle());
    }

    @Override
    public void removePlayer(Player player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"player == null");
        this.handle.m_6539_(((CraftPlayer)player).getHandle());
    }

    @Override
    public List<Player> getPlayers() {
        ImmutableList.Builder players = ImmutableList.builder();
        for (ServerPlayer p : this.handle.m_8324_()) {
            players.add((Object)p.getBukkitEntity());
        }
        return players.build();
    }

    @Override
    public void setVisible(boolean visible) {
        this.handle.m_8321_(visible);
    }

    @Override
    public boolean isVisible() {
        return this.handle.f_8298_;
    }

    @Override
    public void show() {
        this.handle.m_8321_(true);
    }

    @Override
    public void hide() {
        this.handle.m_8321_(false);
    }

    @Override
    public void removeAll() {
        for (Player player : this.getPlayers()) {
            this.removePlayer(player);
        }
    }

    public ServerBossEvent getHandle() {
        return this.handle;
    }

    private final class FlagContainer {
        private Supplier<Boolean> get;
        private Consumer<Boolean> set;

        private FlagContainer(Supplier<Boolean> get, Consumer<Boolean> set) {
            this.get = get;
            this.set = set;
        }
    }
}

