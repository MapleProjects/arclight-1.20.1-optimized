/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.trading.Merchant
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchant;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;

public class CraftMerchantCustom
extends CraftMerchant {
    public CraftMerchantCustom(String title) {
        super(new MinecraftMerchant(title));
        this.getMerchant().craftMerchant = this;
    }

    public String toString() {
        return "CraftMerchantCustom";
    }

    @Override
    public MinecraftMerchant getMerchant() {
        return (MinecraftMerchant)super.getMerchant();
    }

    public static class MinecraftMerchant
    implements Merchant {
        private final Component title;
        private final MerchantOffers trades = new MerchantOffers();
        private Player tradingPlayer;
        protected CraftMerchant craftMerchant;

        public MinecraftMerchant(String title) {
            Preconditions.checkArgument((title != null ? 1 : 0) != 0, (Object)"Title cannot be null");
            this.title = CraftChatMessage.fromString(title)[0];
        }

        public CraftMerchant getCraftMerchant() {
            return this.craftMerchant;
        }

        public void m_7189_(Player entityhuman) {
            this.tradingPlayer = entityhuman;
        }

        public Player m_7962_() {
            return this.tradingPlayer;
        }

        public MerchantOffers m_6616_() {
            return this.trades;
        }

        public void m_6996_(MerchantOffer merchantrecipe) {
            merchantrecipe.m_45374_();
        }

        public void m_7713_(ItemStack itemstack) {
        }

        public Component getScoreboardDisplayName() {
            return this.title;
        }

        public int m_7809_() {
            return 0;
        }

        public void m_6621_(int i) {
        }

        public boolean m_7826_() {
            return false;
        }

        public SoundEvent m_7596_() {
            return SoundEvents.f_12509_;
        }

        public void m_6255_(MerchantOffers merchantrecipelist) {
        }

        public boolean m_183595_() {
            return false;
        }
    }
}

