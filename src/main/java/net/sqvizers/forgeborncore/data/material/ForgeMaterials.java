package net.sqvizers.forgeborncore.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import net.sqvizers.forgeborncore.forgeborncore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

public class ForgeMaterials {

    public static Material Draconium;
    public static Material Elementium;
    public static Material Terrasteel;
    public static Material Manasteel;
    public static Material Adamantium;

    public static Material Ichor;

    public static void register() {
        Draconium = new Material.Builder(forgeborncore.id("draconium"))
                .ingot()
                .color(0x6f42cf).secondaryColor(0x701068).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .toolStats(ToolProperty.Builder.of(4.0F, 2.0F, 130, 1)
                        .attackSpeed(0.2F).enchantability(3).build())
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("Dc");
        Adamantium = new Material.Builder(forgeborncore.id("adamantium"))
                .ingot()
                .color(0x8B0000).secondaryColor(0x4B0000).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("Ad");
        Ichor = new Material.Builder(forgeborncore.id("ichor"))
                .color(0x6f42cf).secondaryColor(0x701068).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .buildAndRegister().setFormula("Ic");

    }
}