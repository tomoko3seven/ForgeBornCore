/*package net.sqvizers.forgeborncore.common.tool;

import net.minecraft.world.item.*;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public class VanillaTierReplacer {

    public static void replaceVanillaToolTiers() {
        overrideToolTier(Items.IRON_PICKAXE, ToolTiers.IRON);
        overrideToolTier(Items.IRON_PICKAXE, ToolTiers.IRON);
        overrideToolTier(Items.STONE_PICKAXE, ToolTiers.FLINT);
        overrideToolTier(Items.DIAMOND_PICKAXE, ToolTiers.DIAMOND);
    }

    private static void overrideToolTier(Item item, Tier newTier) {
        if (item instanceof TieredItem tieredItem) {
            try {
                Field tierField = ObfuscationReflectionHelper.findField(TieredItem.class, "f_41388_");
                tierField.setAccessible(true);
                tierField.set(tieredItem, newTier);
            } catch (Exception e) {
                System.err.println("Не удалось заменить Tier у: " + item);
                e.printStackTrace();
            }
        }
    }
}*/