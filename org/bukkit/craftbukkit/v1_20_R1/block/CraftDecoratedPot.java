/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.block.entity.DecoratedPotBlockEntity
 *  net.minecraft.world.level.block.entity.DecoratedPotBlockEntity$Decorations
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.DecoratedPot;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public class CraftDecoratedPot
extends CraftBlockEntityState<DecoratedPotBlockEntity>
implements DecoratedPot {
    public CraftDecoratedPot(World world, DecoratedPotBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void setSherd(DecoratedPot.Side face, Material sherd) {
        Preconditions.checkArgument((face != null ? 1 : 0) != 0, (Object)"face must not be null");
        Preconditions.checkArgument((sherd == null || sherd == Material.BRICK || Tag.ITEMS_DECORATED_POT_SHERDS.isTagged(sherd) ? 1 : 0) != 0, (String)"sherd is not a valid sherd material: %s", (Object)sherd);
        Item sherdItem = sherd != null ? CraftMagicNumbers.getItem(sherd) : Items.f_42460_;
        DecoratedPotBlockEntity.Decorations decorations = ((DecoratedPotBlockEntity)this.getSnapshot()).m_284296_();
        switch (face) {
            case BACK: {
                ((DecoratedPotBlockEntity)this.getSnapshot()).f_283890_ = new DecoratedPotBlockEntity.Decorations(sherdItem, decorations.f_283809_(), decorations.f_283873_(), decorations.f_283810_());
                break;
            }
            case LEFT: {
                ((DecoratedPotBlockEntity)this.getSnapshot()).f_283890_ = new DecoratedPotBlockEntity.Decorations(decorations.f_283886_(), sherdItem, decorations.f_283873_(), decorations.f_283810_());
                break;
            }
            case RIGHT: {
                ((DecoratedPotBlockEntity)this.getSnapshot()).f_283890_ = new DecoratedPotBlockEntity.Decorations(decorations.f_283886_(), decorations.f_283809_(), sherdItem, decorations.f_283810_());
                break;
            }
            case FRONT: {
                ((DecoratedPotBlockEntity)this.getSnapshot()).f_283890_ = new DecoratedPotBlockEntity.Decorations(decorations.f_283886_(), decorations.f_283809_(), decorations.f_283873_(), sherdItem);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unexpected value: " + (Object)((Object)face));
            }
        }
    }

    @Override
    public Material getSherd(DecoratedPot.Side face) {
        Preconditions.checkArgument((face != null ? 1 : 0) != 0, (Object)"face must not be null");
        DecoratedPotBlockEntity.Decorations decorations = ((DecoratedPotBlockEntity)this.getSnapshot()).m_284296_();
        Item sherdItem = switch (face) {
            case DecoratedPot.Side.BACK -> decorations.f_283886_();
            case DecoratedPot.Side.LEFT -> decorations.f_283809_();
            case DecoratedPot.Side.RIGHT -> decorations.f_283873_();
            case DecoratedPot.Side.FRONT -> decorations.f_283810_();
            default -> throw new IllegalArgumentException("Unexpected value: " + (Object)((Object)face));
        };
        return CraftMagicNumbers.getMaterial(sherdItem);
    }

    @Override
    public Map<DecoratedPot.Side, Material> getSherds() {
        DecoratedPotBlockEntity.Decorations decorations = ((DecoratedPotBlockEntity)this.getSnapshot()).m_284296_();
        EnumMap<DecoratedPot.Side, Material> sherds = new EnumMap<DecoratedPot.Side, Material>(DecoratedPot.Side.class);
        sherds.put(DecoratedPot.Side.BACK, CraftMagicNumbers.getMaterial(decorations.f_283886_()));
        sherds.put(DecoratedPot.Side.LEFT, CraftMagicNumbers.getMaterial(decorations.f_283809_()));
        sherds.put(DecoratedPot.Side.RIGHT, CraftMagicNumbers.getMaterial(decorations.f_283873_()));
        sherds.put(DecoratedPot.Side.FRONT, CraftMagicNumbers.getMaterial(decorations.f_283810_()));
        return sherds;
    }

    @Override
    public List<Material> getShards() {
        return ((DecoratedPotBlockEntity)this.getSnapshot()).m_284296_().m_284195_().map(CraftMagicNumbers::getMaterial).collect(Collectors.toUnmodifiableList());
    }
}

