package net.sqvizers.forgeborncore.common.data;

import com.gregtechceu.gtceu.common.item.armor.GTArmorMaterials;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.config.ConfigHolder;
import earth.terrarium.adastra.common.tags.ModItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.sqvizers.forgeborncore.api.item.HungerCharmItem;
import net.sqvizers.forgeborncore.api.item.armor.NanoMuscleSpaceSuite;
import net.sqvizers.forgeborncore.api.item.armor.SpaceGearItem;
import net.sqvizers.forgeborncore.api.item.gun.BulletItem;
import net.sqvizers.forgeborncore.api.item.gun.GunItem;
import net.sqvizers.forgeborncore.api.item.gun.ShotgunItem;
import net.sqvizers.forgeborncore.api.item.sword.FrostmourneItem;
import net.sqvizers.forgeborncore.common.data.tag.item.FBItemTags;
import net.sqvizers.forgeborncore.forgeborncore;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;

public class FBItems {

    static {
        GTRegistration.REGISTRATE.creativeModeTab(() -> FBCreativeModeTabs.FB_ITEMS);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            forgeborncore.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



    // Custom Circuits
    // Botania Circuits

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

    // Blood Magic Circuits

    // Ars Noveau Circuits
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

    // Craft-Materials

    public static ItemEntry<SpaceGearItem> SPACE_NANO_CHESTPLATE = REGISTRATE
            .item("space_nanomuscle_chestplate",
                    (p) -> new SpaceGearItem(GTArmorMaterials.ARMOR, ArmorItem.Type.CHESTPLATE, 5000, p)
                            .setArmorLogic(new NanoMuscleSpaceSuite(ArmorItem.Type.CHESTPLATE, 512,
                                    6_400_000L * (long) Math.max(1,
                                            Math.pow(4, ConfigHolder.INSTANCE.tools.voltageTierNanoSuit - 3)),
                                    ConfigHolder.INSTANCE.tools.voltageTierNanoSuit)))
            .tag(FBItemTags.NANOMUSCLE_SPACE_SUITE, ModItemTags.SPACE_SUITS, ModItemTags.FREEZE_RESISTANT_ARMOR,
                    ModItemTags.HEAT_RESISTANT_ARMOR)
            .lang("NanoMuscle™ Space Suite Chestplate")
            .properties(p -> p.rarity(Rarity.RARE))
            .register();

    public static ItemEntry<HungerCharmItem> HUNGER_CHARM = REGISTRATE
            .item("hunger_charm", HungerCharmItem::new)
            .lang("Hunger Charm")
            .properties(p -> p.stacksTo(1))
            .defaultModel()
            .register();

    public static final ItemEntry<BulletItem> SIMPLE_BULLET = REGISTRATE
            .item("simple_bullet", props -> new BulletItem(new Item.Properties(), 7))
            .lang("Simple Bullet")
            .properties(p -> p.stacksTo(64))
            .defaultModel()
            .register();

    public static final ItemEntry<GunItem> RIFLE = REGISTRATE
            .item("rifle", props -> new GunItem(new Item.Properties().durability(2014), 15, 3, 60, 1.2))
            .lang("Rifle")
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ShotgunItem> PULSE_SHOTGUN = REGISTRATE
            .item("pulse_shotgun", props -> new ShotgunItem(new Item.Properties().durability(2014), 0, 1, 30, 2, 5))
            .lang("Pulse Shotgun")
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<FrostmourneItem> FROSTMOURNE = REGISTRATE
            .item("frostmourne", props -> new FrostmourneItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties()))
            .lang("Frostmourne")
            .properties(p -> p.stacksTo(1))
            .register();

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {}
}
