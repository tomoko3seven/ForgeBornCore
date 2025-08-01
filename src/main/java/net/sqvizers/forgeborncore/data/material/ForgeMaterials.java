package net.sqvizers.forgeborncore.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import net.sqvizers.forgeborncore.common.data.flags.FBFlags;
import net.sqvizers.forgeborncore.common.data.materials.FBMaterialSet;
import net.sqvizers.forgeborncore.forgeborncore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.ORE;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Aluminium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Iron;
import static net.sqvizers.forgeborncore.common.data.flags.FBFlags.GENERATE_NANITES;

public class ForgeMaterials {

    // ForgeBorn
    public static Material SpiritSteel;
    public static Material Mythril;
    public static Material Blooming;
    public static Material Draconium;

    public static Material Cryotheum;

    public static Material Ichor;

    // Undergarden
    public static Material Cloggrum;
    public static Material Utherium;
    public static Material Froststeel;
    public static Material Forgotten;

    // Nature`s Aura
    public static Material InfusedIron;
    public static Material TaintedGold;
    public static Material SkyIngot;
    public static Material DepthsIngot;

    // Ars Noveau
    public static Material SourceMetall;

    // Botania
    public static Material Livingwood;
    public static Material Livingrock;
    public static Material Manasteel;
    public static Material AnnealedManasteel;
    public static Material Elementium;
    public static Material Terrasteel;
    public static Material Adamantium;

    public static void register() {
        // Nature`s Aura
        InfusedIron = new Material.Builder(forgeborncore.id("infusediron"))
                .ingot()
                .color(0x3ea248).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_GEAR, GENERATE_ROD, GENERATE_PLATE, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("?Fe");
        TaintedGold = new Material.Builder(forgeborncore.id("taintedgold"))
                .ingot()
                .color(0xb06a2e).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_GEAR, GENERATE_ROD, GENERATE_PLATE, GENERATE_SMALL_GEAR, GENERATE_RING)
                .buildAndRegister().setFormula("?Au");
        SkyIngot = new Material.Builder(forgeborncore.id("skyingot"))
                .ingot()
                .color(0x9ee3f0).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_GEAR, GENERATE_ROD, GENERATE_PLATE, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("?");
        DepthsIngot = new Material.Builder(forgeborncore.id("depthsingot"))
                .ingot()
                .color(0x5e456b).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_GEAR, GENERATE_ROD, GENERATE_PLATE, GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("?");

        // Botania
        Livingwood = new Material.Builder(forgeborncore.id("livingwood"))
                .color(0x34140c).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_PLATE, GENERATE_BOLT_SCREW)
                .buildAndRegister();
        Livingrock = new Material.Builder(forgeborncore.id("livingrock"))
                .color(0xd7d7c8).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_PLATE)
                .buildAndRegister();
        Manasteel = new Material.Builder(forgeborncore.id("manasteel"))
                .ingot()
                .ore()
                .color(0x3BAFEA).secondaryColor(0x1E7FCB).iconSet(MaterialIconSet.BRIGHT)
                .cableProperties(GTValues.V[GTValues.LV], 8, 0, true)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR, GENERATE_PLATE)
                .buildAndRegister().setFormula("Ms");
        AnnealedManasteel = new Material.Builder(forgeborncore.id("annealed_manasteel"))
                .ingot()
                .color(0x6A8FA4).secondaryColor(0x4F6D84).iconSet(MaterialIconSet.BRIGHT)
                .cableProperties(GTValues.V[GTValues.LV], 8, 0, true)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR, GENERATE_PLATE)
                .buildAndRegister().setFormula("Ms");
        Elementium = new Material.Builder(forgeborncore.id("elementium"))
                .ingot()
                .color(0xE04AC0).secondaryColor(0xC038A0).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .cableProperties(GTValues.V[GTValues.EV], 3, 4, false)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("Em");
        Terrasteel = new Material.Builder(forgeborncore.id("terrasteel"))
                .ingot()
                .color(0x47FF8D).secondaryColor(0x2ECF78).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
            .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_NANITES, GENERATE_ROD, GENERATE_DENSE, GENERATE_LONG_ROD, FBFlags.GENERATE_REINFORCED, FBFlags.GENERATE_INFUSED,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("Ts");
        Blooming = new Material.Builder(forgeborncore.id("blooming"))
                .ingot()
                .iconSet(FBMaterialSet.BLOOMING)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("?");
        Adamantium = new Material.Builder(forgeborncore.id("adamantium"))
                .ingot()
                .color(0x8B0000).secondaryColor(0x4B0000).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("Ad");
        // ForgeBorn
        Draconium = new Material.Builder(forgeborncore.id("draconium"))
                .ingot()
                .ore()
                .color(0x6f42cf).secondaryColor(0x701068).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .toolStats(ToolProperty.Builder.of(4.0F, 2.0F, 130, 1)
                        .attackSpeed(0.2F).enchantability(3).build())
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister().setFormula("Dc");
        SpiritSteel = new Material.Builder(forgeborncore.id("spiritsteel"))
                .ingot()
                .color(0x86684f).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_ROD, GENERATE_PLATE, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Mythril = new Material.Builder(forgeborncore.id("mythril"))
                .ingot()
                .color(0x657EA3).secondaryColor(0x6A93FF).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_ROD, GENERATE_PLATE, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister();



        Ichor = new Material.Builder(forgeborncore.id("ichor"))
                .color(0x6f42cf).secondaryColor(0x701068).iconSet(MaterialIconSet.BRIGHT)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .buildAndRegister().setFormula("Ic");
        Cryotheum = new Material.Builder(forgeborncore.id("cryotheum"))
                .dust()
                .color(0x6f42cf).secondaryColor(0x701068)
                .liquid(new FluidBuilder().state(FluidState.LIQUID).customStill())
                .buildAndRegister().setFormula("NH₄[BF₄]·H₂O");
        // Ars Noveau
        SourceMetall = new Material.Builder(forgeborncore.id("sourcemetall"))
                .ingot()
                .color(0x9B5FA9).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_ROD, GENERATE_PLATE, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister();
        // Undergarden
        Cloggrum = new Material.Builder(forgeborncore.id("cloggrum"))
                .ingot()
                .ore()
                .addOreByproducts(Iron)
                .color(0x8a735a).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_ROD, GENERATE_PLATE, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Froststeel = new Material.Builder(forgeborncore.id("froststeel"))
                .ingot()
                .ore()
                .addOreByproducts(Iron, Iron, Aluminium)
                .washedIn(ForgeMaterials.Cryotheum)
                .color(0xb3d0e2).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_ROD, GENERATE_PLATE, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Forgotten = new Material.Builder(forgeborncore.id("forgotten"))
                .ingot()
                .color(0x3dd5a2).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_GEAR, GENERATE_RING, GENERATE_ROD, GENERATE_PLATE, GENERATE_LONG_ROD,
                        GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Utherium = new Material.Builder(forgeborncore.id("utherium"))
                .gem()
                .ore()
                .color(0xA4433B).iconSet(MaterialIconSet.DIAMOND)
                .flags(GENERATE_LENS)
                .buildAndRegister();
    }
}
