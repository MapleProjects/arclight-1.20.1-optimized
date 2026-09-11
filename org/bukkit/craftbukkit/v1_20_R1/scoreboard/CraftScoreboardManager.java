/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetObjectivePacket
 *  net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.ServerScoreboard
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.scores.Objective
 *  net.minecraft.world.scores.PlayerTeam
 *  net.minecraft.world.scores.Score
 *  net.minecraft.world.scores.Scoreboard
 *  net.minecraft.world.scores.criteria.ObjectiveCriteria
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboard;
import org.bukkit.craftbukkit.v1_20_R1.util.WeakCollection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;
import org.spigotmc.AsyncCatcher;

public final class CraftScoreboardManager
implements ScoreboardManager {
    private final CraftScoreboard mainScoreboard;
    private final MinecraftServer server;
    private final Collection<CraftScoreboard> scoreboards = new WeakCollection<CraftScoreboard>();
    private final Map<CraftPlayer, CraftScoreboard> playerBoards = new HashMap<CraftPlayer, CraftScoreboard>();

    public CraftScoreboardManager(MinecraftServer minecraftserver, Scoreboard scoreboardServer) {
        this.mainScoreboard = new CraftScoreboard(scoreboardServer);
        this.server = minecraftserver;
        this.scoreboards.add(this.mainScoreboard);
    }

    @Override
    public CraftScoreboard getMainScoreboard() {
        return this.mainScoreboard;
    }

    @Override
    public CraftScoreboard getNewScoreboard() {
        AsyncCatcher.catchOp("scoreboard creation");
        CraftScoreboard scoreboard = new CraftScoreboard((Scoreboard)new ServerScoreboard(this.server));
        this.scoreboards.add(scoreboard);
        return scoreboard;
    }

    public CraftScoreboard getPlayerBoard(CraftPlayer player) {
        CraftScoreboard board = this.playerBoards.get(player);
        return board == null ? this.getMainScoreboard() : board;
    }

    public void setPlayerBoard(CraftPlayer player, org.bukkit.scoreboard.Scoreboard bukkitScoreboard) {
        Preconditions.checkArgument((boolean)(bukkitScoreboard instanceof CraftScoreboard), (Object)"Cannot set player scoreboard to an unregistered Scoreboard");
        CraftScoreboard scoreboard = (CraftScoreboard)bukkitScoreboard;
        Scoreboard oldboard = this.getPlayerBoard(player).getHandle();
        Scoreboard newboard = scoreboard.getHandle();
        ServerPlayer entityplayer = player.getHandle();
        if (oldboard == newboard) {
            return;
        }
        if (scoreboard == this.mainScoreboard) {
            this.playerBoards.remove(player);
        } else {
            this.playerBoards.put(player, scoreboard);
        }
        HashSet<Objective> removed = new HashSet<Objective>();
        int i = 0;
        while (i < 3) {
            Objective scoreboardobjective = oldboard.m_83416_(i);
            if (scoreboardobjective != null && !removed.contains(scoreboardobjective)) {
                entityplayer.f_8906_.m_9829_((Packet)new ClientboundSetObjectivePacket(scoreboardobjective, 1));
                removed.add(scoreboardobjective);
            }
            ++i;
        }
        for (PlayerTeam scoreboardteam : oldboard.m_83491_()) {
            entityplayer.f_8906_.m_9829_((Packet)ClientboundSetPlayerTeamPacket.m_179326_((PlayerTeam)scoreboardteam));
        }
        this.server.m_6846_().m_11273_((ServerScoreboard)newboard, player.getHandle());
    }

    public void removePlayer(Player player) {
        this.playerBoards.remove(player);
    }

    public void getScoreboardScores(ObjectiveCriteria criteria, String name, Consumer<Score> consumer) {
        for (CraftScoreboard scoreboard : this.scoreboards) {
            Scoreboard board = scoreboard.board;
            board.m_83427_(criteria, name, score -> consumer.accept((Score)score));
        }
    }
}

