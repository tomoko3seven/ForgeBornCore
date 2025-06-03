package net.sqvizers.forgeborncore.common;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sqvizers.forgeborncore.forgeborncore;

import static dev.architectury.loom.forgeruntime.mixin.ForgeLoomMixinRemapperInjectorServiceImpl.attach;
import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;

public class FBItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, forgeborncore.MOD_ID);




    public static void register(IEventBus eventBus) {


        ITEMS.register(eventBus);
    }

    //Custom Circuits
    //Botania Circuits

    public static final ItemEntry<ComponentItem> LV_BOTANICAL_CIRCUIT = REGISTRATE
            .item("lv_botanical_circuit", ComponentItem::create)
            .lang("Basic Botanical Circuit")
            .properties(p -> p.stacksTo(64))
            .onRegister(attach(new TooltipBehavior(tooltips -> {
                tooltips.add(Component.translatable("forgeborncore.lv_botanical_circuit.tooltip.1"));
                tooltips.add(Component.translatable("forgeborncore.lv_botanical_circuit.tooltip.2"));
            })))
            .defaultModel()
            .register();
    public static final ItemEntry<ComponentItem> MV_BOTANICAL_CIRCUIT = REGISTRATE
            .item("mv_botanical_circuit", ComponentItem::create)
            .lang("Elementium Botanical Circuit")
            .properties(p -> p.stacksTo(64))
            .defaultModel()
            .register();
    public static final ItemEntry<ComponentItem> HV_BOTANICAL_CIRCUIT = REGISTRATE
            .item("hv_botanical_circuit", ComponentItem::create)
            .lang("Terrasteel Botanical Circuit")
            .properties(p -> p.stacksTo(64))
            .register();
    public static final ItemEntry<ComponentItem> EV_BOTANICAL_CIRCUIT = REGISTRATE
            .item("ev_botanical_circuit", ComponentItem::create)
            .lang("Adamantium Botanical Circuit")
            .properties(p -> p.stacksTo(64))
            .defaultModel()
            .register();

    //Blood Magic Circuits



    //Ars Noveau Circuits
    public static final ItemEntry<ComponentItem> LV_WIRARD_CIRCUIT = REGISTRATE
            .item("lv_wizard_circuit", ComponentItem::create)
            .lang("Basic Wizard Circuit")
            .properties(p -> p.stacksTo(64))
            .onRegister(attach(new TooltipBehavior(tooltips -> {
                tooltips.add(Component.translatable("forgeborncore.lv_wizard_circuit.tooltip.1"));
                tooltips.add(Component.translatable("forgeborncore.lv_wizard_circuit.tooltip.2"));
            })))
            .defaultModel()
            .register();

    //Craft-Materials
    public static final ItemEntry<ComponentItem> STONE_GEAR = REGISTRATE
            .item("stone_gear", ComponentItem::create)
            .lang("Stone Gear")
            .properties(p -> p.stacksTo(64))
            .register();

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {}

}
