/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.AnvilMenu
 *  net.minecraft.world.inventory.BeaconMenu
 *  net.minecraft.world.inventory.BlastFurnaceMenu
 *  net.minecraft.world.inventory.BrewingStandMenu
 *  net.minecraft.world.inventory.CartographyTableMenu
 *  net.minecraft.world.inventory.ChestMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.CraftingMenu
 *  net.minecraft.world.inventory.DispenserMenu
 *  net.minecraft.world.inventory.EnchantmentMenu
 *  net.minecraft.world.inventory.FurnaceMenu
 *  net.minecraft.world.inventory.GrindstoneMenu
 *  net.minecraft.world.inventory.HopperMenu
 *  net.minecraft.world.inventory.LecternMenu
 *  net.minecraft.world.inventory.LoomMenu
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.inventory.MerchantMenu
 *  net.minecraft.world.inventory.ShulkerBoxMenu
 *  net.minecraft.world.inventory.SimpleContainerData
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.inventory.SmokerMenu
 *  net.minecraft.world.inventory.StonecutterMenu
 *  net.minecraft.world.item.ItemStack
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.BeaconMenu;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.CartographyTableMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.inventory.LecternMenu;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryCustom;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class CraftContainer
extends AbstractContainerMenu {
    private final InventoryView view;
    private InventoryType cachedType;
    private AbstractContainerMenu delegate;

    public CraftContainer(InventoryView view, Player player, int id) {
        super(CraftContainer.getNotchInventoryType(view.getTopInventory()), id);
        this.view = view;
        Container top = ((CraftInventory)view.getTopInventory()).getInventory();
        net.minecraft.world.entity.player.Inventory bottom = (net.minecraft.world.entity.player.Inventory)((CraftInventory)view.getBottomInventory()).getInventory();
        this.cachedType = view.getType();
        this.setupSlots(top, bottom, player);
    }

    public CraftContainer(final Inventory inventory, final Player player, int id) {
        this(new InventoryView(){
            private final String originalTitle;
            private String title;
            {
                this.title = this.originalTitle = inventory2 instanceof CraftInventoryCustom ? ((CraftInventoryCustom.MinecraftInventory)((CraftInventory)inventory2).getInventory()).getTitle() : inventory2.getType().getDefaultTitle();
            }

            @Override
            public Inventory getTopInventory() {
                return inventory;
            }

            @Override
            public Inventory getBottomInventory() {
                return this.getPlayer().getInventory();
            }

            @Override
            public HumanEntity getPlayer() {
                return player.getBukkitEntity();
            }

            @Override
            public InventoryType getType() {
                return inventory.getType();
            }

            @Override
            public String getTitle() {
                return this.title;
            }

            @Override
            public String getOriginalTitle() {
                return this.originalTitle;
            }

            @Override
            public void setTitle(String title) {
                CraftInventoryView.sendInventoryTitleChange(this, title);
                this.title = title;
            }
        }, player, id);
    }

    public InventoryView getBukkitView() {
        return this.view;
    }

    public static MenuType getNotchInventoryType(Inventory inventory) {
        switch (inventory.getType()) {
            case CHEST: 
            case PLAYER: 
            case ENDER_CHEST: 
            case BARREL: {
                switch (inventory.getSize()) {
                    case 9: {
                        return MenuType.f_39957_;
                    }
                    case 18: {
                        return MenuType.f_39958_;
                    }
                    case 27: {
                        return MenuType.f_39959_;
                    }
                    case 36: 
                    case 41: {
                        return MenuType.f_39960_;
                    }
                    case 45: {
                        return MenuType.f_39961_;
                    }
                    case 54: {
                        return MenuType.f_39962_;
                    }
                }
                throw new IllegalArgumentException("Unsupported custom inventory size " + inventory.getSize());
            }
            case WORKBENCH: {
                return MenuType.f_39968_;
            }
            case FURNACE: {
                return MenuType.f_39970_;
            }
            case DISPENSER: {
                return MenuType.f_39963_;
            }
            case ENCHANTING: {
                return MenuType.f_39969_;
            }
            case BREWING: {
                return MenuType.f_39967_;
            }
            case BEACON: {
                return MenuType.f_39965_;
            }
            case ANVIL: {
                return MenuType.f_39964_;
            }
            case HOPPER: {
                return MenuType.f_39972_;
            }
            case DROPPER: {
                return MenuType.f_39963_;
            }
            case SHULKER_BOX: {
                return MenuType.f_39976_;
            }
            case BLAST_FURNACE: {
                return MenuType.f_39966_;
            }
            case LECTERN: {
                return MenuType.f_39973_;
            }
            case SMOKER: {
                return MenuType.f_39978_;
            }
            case LOOM: {
                return MenuType.f_39974_;
            }
            case CARTOGRAPHY: {
                return MenuType.f_39979_;
            }
            case GRINDSTONE: {
                return MenuType.f_39971_;
            }
            case STONECUTTER: {
                return MenuType.f_39980_;
            }
            case SMITHING: 
            case SMITHING_NEW: {
                return MenuType.f_39977_;
            }
            case CRAFTING: 
            case CREATIVE: 
            case MERCHANT: {
                throw new IllegalArgumentException("Can't open a " + (Object)((Object)inventory.getType()) + " inventory!");
            }
        }
        return MenuType.f_39959_;
    }

    private void setupSlots(Container top, net.minecraft.world.entity.player.Inventory bottom, Player entityhuman) {
        int windowId = -1;
        switch (this.cachedType) {
            case CREATIVE: {
                break;
            }
            case CHEST: 
            case PLAYER: 
            case ENDER_CHEST: 
            case BARREL: {
                this.delegate = new ChestMenu(MenuType.f_39959_, windowId, bottom, top, top.m_6643_() / 9);
                break;
            }
            case DISPENSER: 
            case DROPPER: {
                this.delegate = new DispenserMenu(windowId, bottom, top);
                break;
            }
            case FURNACE: {
                this.delegate = new FurnaceMenu(windowId, bottom, top, (ContainerData)new SimpleContainerData(4));
                break;
            }
            case WORKBENCH: 
            case CRAFTING: {
                this.setupWorkbench(top, (Container)bottom);
                break;
            }
            case ENCHANTING: {
                this.delegate = new EnchantmentMenu(windowId, bottom);
                break;
            }
            case BREWING: {
                this.delegate = new BrewingStandMenu(windowId, bottom, top, (ContainerData)new SimpleContainerData(2));
                break;
            }
            case HOPPER: {
                this.delegate = new HopperMenu(windowId, bottom, top);
                break;
            }
            case ANVIL: {
                this.setupAnvil(top, (Container)bottom);
                break;
            }
            case BEACON: {
                this.delegate = new BeaconMenu(windowId, (Container)bottom);
                break;
            }
            case SHULKER_BOX: {
                this.delegate = new ShulkerBoxMenu(windowId, bottom, top);
                break;
            }
            case BLAST_FURNACE: {
                this.delegate = new BlastFurnaceMenu(windowId, bottom, top, (ContainerData)new SimpleContainerData(4));
                break;
            }
            case LECTERN: {
                this.delegate = new LecternMenu(windowId, top, (ContainerData)new SimpleContainerData(1), bottom);
                break;
            }
            case SMOKER: {
                this.delegate = new SmokerMenu(windowId, bottom, top, (ContainerData)new SimpleContainerData(4));
                break;
            }
            case LOOM: {
                this.delegate = new LoomMenu(windowId, bottom);
                break;
            }
            case CARTOGRAPHY: {
                this.delegate = new CartographyTableMenu(windowId, bottom);
                break;
            }
            case GRINDSTONE: {
                this.delegate = new GrindstoneMenu(windowId, bottom);
                break;
            }
            case STONECUTTER: {
                this.delegate = new StonecutterMenu(windowId, bottom);
                break;
            }
            case MERCHANT: {
                this.delegate = new MerchantMenu(windowId, bottom);
                break;
            }
            case SMITHING: 
            case SMITHING_NEW: {
                this.setupSmithing(top, (Container)bottom);
            }
        }
        if (this.delegate != null) {
            this.f_38841_ = this.delegate.f_38841_;
            this.f_38839_ = this.delegate.f_38839_;
            this.f_150394_ = this.delegate.f_150394_;
        }
        switch (this.cachedType) {
            case WORKBENCH: {
                this.delegate = new CraftingMenu(windowId, bottom);
                break;
            }
            case ANVIL: {
                this.delegate = new AnvilMenu(windowId, bottom);
            }
        }
    }

    private void setupWorkbench(Container top, Container bottom) {
        int col;
        this.m_38897_(new Slot(top, 0, 124, 35));
        int row = 0;
        while (row < 3) {
            col = 0;
            while (col < 3) {
                this.m_38897_(new Slot(top, 1 + col + row * 3, 30 + col * 18, 17 + row * 18));
                ++col;
            }
            ++row;
        }
        row = 0;
        while (row < 3) {
            col = 0;
            while (col < 9) {
                this.m_38897_(new Slot(bottom, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
                ++col;
            }
            ++row;
        }
        col = 0;
        while (col < 9) {
            this.m_38897_(new Slot(bottom, col, 8 + col * 18, 142));
            ++col;
        }
    }

    private void setupAnvil(Container top, Container bottom) {
        this.m_38897_(new Slot(top, 0, 27, 47));
        this.m_38897_(new Slot(top, 1, 76, 47));
        this.m_38897_(new Slot(top, 2, 134, 47));
        int row = 0;
        while (row < 3) {
            int col = 0;
            while (col < 9) {
                this.m_38897_(new Slot(bottom, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
                ++col;
            }
            ++row;
        }
        row = 0;
        while (row < 9) {
            this.m_38897_(new Slot(bottom, row, 8 + row * 18, 142));
            ++row;
        }
    }

    private void setupSmithing(Container top, Container bottom) {
        this.m_38897_(new Slot(top, 0, 8, 48));
        this.m_38897_(new Slot(top, 1, 26, 48));
        this.m_38897_(new Slot(top, 2, 44, 48));
        this.m_38897_(new Slot(top, 3, 98, 48));
        int row = 0;
        while (row < 3) {
            int col = 0;
            while (col < 9) {
                this.m_38897_(new Slot(bottom, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
                ++col;
            }
            ++row;
        }
        row = 0;
        while (row < 9) {
            this.m_38897_(new Slot(bottom, row, 8 + row * 18, 142));
            ++row;
        }
    }

    public ItemStack m_7648_(Player entityhuman, int i) {
        return this.delegate != null ? this.delegate.m_7648_(entityhuman, i) : ItemStack.f_41583_;
    }

    public boolean m_6875_(Player entity) {
        return true;
    }

    public MenuType<?> m_6772_() {
        return CraftContainer.getNotchInventoryType(this.view.getTopInventory());
    }
}

