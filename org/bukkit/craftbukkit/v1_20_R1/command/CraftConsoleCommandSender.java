/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.command;

import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ManuallyAbandonedConversationCanceller;
import org.bukkit.craftbukkit.v1_20_R1.command.ServerCommandSender;
import org.bukkit.craftbukkit.v1_20_R1.conversations.ConversationTracker;

public class CraftConsoleCommandSender
extends ServerCommandSender
implements ConsoleCommandSender {
    protected final ConversationTracker conversationTracker = new ConversationTracker();

    protected CraftConsoleCommandSender() {
    }

    @Override
    public void sendMessage(String message) {
        this.sendRawMessage(message);
    }

    @Override
    public void sendRawMessage(String message) {
        System.out.println(ChatColor.stripColor(message));
    }

    @Override
    public void sendRawMessage(UUID sender, String message) {
        this.sendRawMessage(message);
    }

    @Override
    public void sendMessage(String ... messages) {
        String[] stringArray = messages;
        int n = messages.length;
        int n2 = 0;
        while (n2 < n) {
            String message = stringArray[n2];
            this.sendMessage(message);
            ++n2;
        }
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot change operator status of server console");
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return this.conversationTracker.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        this.conversationTracker.abandonConversation(conversation, new ConversationAbandonedEvent(conversation, new ManuallyAbandonedConversationCanceller()));
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        this.conversationTracker.abandonConversation(conversation, details);
    }

    @Override
    public void acceptConversationInput(String input) {
        this.conversationTracker.acceptConversationInput(input);
    }

    @Override
    public boolean isConversing() {
        return this.conversationTracker.isConversing();
    }
}

