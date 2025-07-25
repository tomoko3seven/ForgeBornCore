package net.sqvizers.forgeborncore.common.data;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.sqvizers.forgeborncore.data.material.ForgeMaterials;
import net.sqvizers.forgeborncore.forgeborncore;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;

import com.tterrag.registrate.util.entry.RegistryEntry;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType.ingot;
import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;

public class FBCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> FB_BLOCKS = REGISTRATE.defaultCreativeTab(forgeborncore.MOD_ID,
                    builder -> builder
                            .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(forgeborncore.MOD_ID, REGISTRATE))
                            .title(REGISTRATE.addLang("itemGroup", forgeborncore.id("fb_blocks"), "FB Blocks"))
                            .icon(FBBlocks.MANASTEEL_CASING::asStack)
                            .build())
            .register();

    public static RegistryEntry<CreativeModeTab> FB_ITEMS = REGISTRATE.defaultCreativeTab(forgeborncore.MOD_ID,
                    builder -> builder
                            .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(forgeborncore.MOD_ID, REGISTRATE))
                            .title(REGISTRATE.addLang("itemGroup", forgeborncore.id("fb_items"), "FB Items"))
                            .icon(FBItems.LV_BOTANICAL_CIRCUIT::asStack)
                            .build())
            .register();

    public static RegistryEntry<CreativeModeTab> FB_MACHINES = REGISTRATE.defaultCreativeTab(forgeborncore.MOD_ID,
                    builder -> builder
                            .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(forgeborncore.MOD_ID, REGISTRATE))
                            .title(REGISTRATE.addLang("itemGroup", forgeborncore.id("fb_machines"), "FB Machines"))
                            .icon(FBCMachines.HIGH_PRESSURE_ASSEMBLER::asStack)
                            .build())
            .register();

    public static RegistryEntry<CreativeModeTab> FB_MATERIALS = REGISTRATE.defaultCreativeTab(forgeborncore.MOD_ID,
                    builder -> builder
                            .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(forgeborncore.MOD_ID, REGISTRATE))
                            .title(REGISTRATE.addLang("itemGroup", forgeborncore.id("fb_materials"), "FB Materials"))
                            .icon(FBItems.SIMPLE_BULLET::asStack)
                            .build())
            .register();


    public static void init() {}
}
