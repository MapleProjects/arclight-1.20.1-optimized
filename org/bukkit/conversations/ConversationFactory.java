/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ConversationCanceller;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;
import org.bukkit.conversations.ExactMatchConversationCanceller;
import org.bukkit.conversations.InactivityConversationCanceller;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.NullConversationPrefix;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConversationFactory {
    protected Plugin plugin;
    protected boolean isModal;
    protected boolean localEchoEnabled;
    protected ConversationPrefix prefix;
    protected Prompt firstPrompt;
    protected Map<Object, Object> initialSessionData;
    protected String playerOnlyMessage;
    protected List<ConversationCanceller> cancellers;
    protected List<ConversationAbandonedListener> abandonedListeners;

    public ConversationFactory(@NotNull Plugin plugin) {
        this.plugin = plugin;
        this.isModal = true;
        this.localEchoEnabled = true;
        this.prefix = new NullConversationPrefix();
        this.firstPrompt = Prompt.END_OF_CONVERSATION;
        this.initialSessionData = new HashMap<Object, Object>();
        this.playerOnlyMessage = null;
        this.cancellers = new ArrayList<ConversationCanceller>();
        this.abandonedListeners = new ArrayList<ConversationAbandonedListener>();
    }

    @NotNull
    public ConversationFactory withModality(boolean modal) {
        this.isModal = modal;
        return this;
    }

    @NotNull
    public ConversationFactory withLocalEcho(boolean localEchoEnabled) {
        this.localEchoEnabled = localEchoEnabled;
        return this;
    }

    @NotNull
    public ConversationFactory withPrefix(@NotNull ConversationPrefix prefix) {
        this.prefix = prefix;
        return this;
    }

    @NotNull
    public ConversationFactory withTimeout(int timeoutSeconds) {
        return this.withConversationCanceller(new InactivityConversationCanceller(this.plugin, timeoutSeconds));
    }

    @NotNull
    public ConversationFactory withFirstPrompt(@Nullable Prompt firstPrompt) {
        this.firstPrompt = firstPrompt;
        return this;
    }

    @NotNull
    public ConversationFactory withInitialSessionData(@NotNull Map<Object, Object> initialSessionData) {
        this.initialSessionData = initialSessionData;
        return this;
    }

    @NotNull
    public ConversationFactory withEscapeSequence(@NotNull String escapeSequence) {
        return this.withConversationCanceller(new ExactMatchConversationCanceller(escapeSequence));
    }

    @NotNull
    public ConversationFactory withConversationCanceller(@NotNull ConversationCanceller canceller) {
        this.cancellers.add(canceller);
        return this;
    }

    @NotNull
    public ConversationFactory thatExcludesNonPlayersWithMessage(@Nullable String playerOnlyMessage) {
        this.playerOnlyMessage = playerOnlyMessage;
        return this;
    }

    @NotNull
    public ConversationFactory addConversationAbandonedListener(@NotNull ConversationAbandonedListener listener) {
        this.abandonedListeners.add(listener);
        return this;
    }

    @NotNull
    public Conversation buildConversation(@NotNull Conversable forWhom) {
        if (this.playerOnlyMessage != null && !(forWhom instanceof Player)) {
            return new Conversation(this.plugin, forWhom, new NotPlayerMessagePrompt());
        }
        HashMap<Object, Object> copiedInitialSessionData = new HashMap<Object, Object>();
        copiedInitialSessionData.putAll(this.initialSessionData);
        Conversation conversation = new Conversation(this.plugin, forWhom, this.firstPrompt, copiedInitialSessionData);
        conversation.setModal(this.isModal);
        conversation.setLocalEchoEnabled(this.localEchoEnabled);
        conversation.setPrefix(this.prefix);
        for (ConversationCanceller canceller : this.cancellers) {
            conversation.addConversationCanceller(canceller.clone());
        }
        for (ConversationAbandonedListener listener : this.abandonedListeners) {
            conversation.addConversationAbandonedListener(listener);
        }
        return conversation;
    }

    private class NotPlayerMessagePrompt
    extends MessagePrompt {
        private NotPlayerMessagePrompt() {
        }

        @Override
        @NotNull
        public String getPromptText(@NotNull ConversationContext context) {
            return ConversationFactory.this.playerOnlyMessage;
        }

        @Override
        @Nullable
        protected Prompt getNextPrompt(@NotNull ConversationContext context) {
            return Prompt.END_OF_CONVERSATION;
        }
    }
}

