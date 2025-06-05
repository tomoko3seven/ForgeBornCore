package net.sqvizers.forgeborncore.common.data;

import net.sqvizers.forgeborncore.common.FBItems;
import net.sqvizers.forgeborncore.forgeborncore;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;

import com.tterrag.registrate.util.entry.RegistryEntry;

import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;

public class FBCCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> FORGE_BORN = REGISTRATE.defaultCreativeTab(forgeborncore.MOD_ID,
            builder -> builder
                    .displayItems(
                            new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(forgeborncore.MOD_ID, REGISTRATE))
                    .title(REGISTRATE.addLang("itemGroup", forgeborncore.id("creative_tab"), "ForgeBorn Core"))
                    .icon(FBItems.LV_BOTANICAL_CIRCUIT::asStack)
                    .build())
            .register();

    public static void init() {}
}
