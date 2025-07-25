package net.sqvizers.forgeborncore.common.data;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.common.data.*;
import net.sqvizers.forgeborncore.api.machine.part.SteamFluidHatchPartMachine;
import net.sqvizers.forgeborncore.common.data.machine.multiblock.steam.WeakSteamParallelMultiBlockMachine;
import net.sqvizers.forgeborncore.forgeborncore;
import net.sqvizers.forgeborncore.gtbrucke.FBRecipeTypes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidType;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.CASING_INDUSTRIAL_STEAM;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static net.minecraft.world.level.block.Blocks.*;
import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;
import static net.sqvizers.forgeborncore.common.data.FBBlocks.MANASTEEL_CASING;
import static vazkii.botania.common.block.BotaniaBlocks.*;

public class FBCMachines {

    static {
        REGISTRATE.creativeModeTab(() -> FBCreativeModeTabs.FB_MACHINES);
    }

    public final static int[] ELECTRIC_TIERS = GTValues.tiersBetween(LV, GTCEuAPI.isHighTier() ? OpV : UV);
    public final static int[] LOW_TIERS = GTValues.tiersBetween(LV, EV);
    public final static int[] HIGH_TIERS = GTValues.tiersBetween(IV, GTCEuAPI.isHighTier() ? OpV : UHV);
    public static final Int2IntFunction defaultTankSizeFunction = tier -> (tier <= GTValues.LV ? 4 :
            tier == GTValues.MV ? 12 : tier == GTValues.HV ? 16 : tier == GTValues.EV ? 32 : 64) *
            FluidType.BUCKET_VOLUME;


    ///////////////////////////
    /// MULTIBLOCK MACHINES ///
    ///////////////////////////

    public static final MultiblockMachineDefinition HIGH_PRESSURE_ASSEMBLER = GTRegistration.REGISTRATE
            .multiblock("high_pressure_assembler", WeakSteamParallelMultiBlockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ASSEMBLER_RECIPES)
            .tier(ULV)
            .recipeModifier(WeakSteamParallelMultiBlockMachine::recipeModifier, true)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BBBBB", " BBB ")
                    .aisle("AAAAA", "BDDDB", " BBB ")
                    .aisle("AAAAA", "BBYBB", " BBB ")
                    .where('B', blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1)
                                    .setExactLimit(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)
                                    .setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1).setExactLimit(1)))
                    .where(' ', any())
                    .where('Y', Predicates.controller(blocks(definition.getBlock())))
                    .where('A', blocks(STEEL_HULL.get()).setMinGlobalLimited(11)
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('D', blocks(CASING_STEEL_GEARBOX.get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/implosion_compressor"))
            .tooltips(Component.translatable("forgeborncore.multiblock.hpa.tooltip.1"),
                    Component.translatable("forgeborncore.multiblock.hpa.tooltip.2"))
            .register();

    public static final MultiblockMachineDefinition STEAM_MIXER = GTRegistration.REGISTRATE
            .multiblock("steam_mixer", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.MIXER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .appearanceBlock(CASING_INDUSTRIAL_STEAM)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("III", "III", "III")
                    .aisle("   ", " A ", "   ")
                    .aisle(" B ", "B B", " B ")
                    .aisle(" B ", "B B", " B ")
                    .aisle(" B ", "B B", " B ")
                    .aisle("   ", " A ", "   ")
                    .aisle("III", "ICI", "III")
                    .where('I', Predicates.blocks(GCYMBlocks.CASING_INDUSTRIAL_STEAM.get()).setMinGlobalLimited(13)
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1)
                                    .setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1).setExactLimit(1)))
                    .where(' ', any())
                    .where('C', Predicates.controller(blocks(definition.getBlock())))
                    .where('B', blocks(BRONZE_HULL.get()))
                    .where('A', blocks(CASING_BRONZE_PIPE.get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/electrolyzer"))
            .tooltips(Component.translatable("forgeborncore.multiblock.steam_mixer.tooltip"))
            .register();

    public static final MultiblockMachineDefinition SAWMILL = GTRegistration.REGISTRATE
            .multiblock("sawmill", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(FBRecipeTypes.SAWMILLRECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .appearanceBlock(CASING_INDUSTRIAL_STEAM)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("IFI", "I I", "IFI")
                    .aisle(" B ", "   ", "III")
                    .aisle("FBF", "F F", "I I")
                    .aisle(" B ", "   ", "III")
                    .aisle("IFI", "C I", "IFI")
                    .where('I', Predicates.blocks(GCYMBlocks.CASING_INDUSTRIAL_STEAM.get()).setMinGlobalLimited(15)
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1)
                                    .setExactLimit(1)))
                    .where(' ', any())
                    .where('C', Predicates.controller(blocks(definition.getBlock())))
                    .where('B', blocks(BRONZE_BRICKS_HULL.get()))
                    .where('F', Predicates.frames(GTMaterials.Bronze))
                    .build())
            .workableCasingModel(forgeborncore.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/canner"))
            .tooltips(Component.translatable("forgeborncore.multiblock.sawmill.tooltip"))
            .tooltips(Component.translatable("forgeborncore.multiblock.sawmill.tooltip.2"))
            .register();

    public static final MachineDefinition STEAM_FORGE_HAMMER = REGISTRATE
            .multiblock("steam_forge_hammer", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.FORGE_HAMMER_RECIPES)
            .recipeModifier(WeakSteamParallelMultiBlockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" XXX ", "     ", "     ", "     ", "     ", "     ", "     ")
                    .aisle("XXXXX", " XXX ", "     ", "     ", "     ", "  X  ", "     ")
                    .aisle("XXXXX", "XX XX", "X   X", "X I X", "X I X", "XXIXX", "  G  ")
                    .aisle("XXXXX", " XSX ", "     ", "     ", "     ", "  X  ", "     ")
                    .aisle(" XXX ", "     ", "     ", "     ", "     ", "     ", "     ")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('#', Predicates.air())
                    .where(' ', any())
                    .where('I', Predicates.blocks(IRON_BLOCK))
                    .where('G', Predicates.blocks(CASING_BRONZE_GEARBOX.get()))
                    .where('X', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),

                    GTCEu.id("block/machines/forge_hammer"))
            .tooltips(Component.translatable("block.forgeborncore.steam_forge_hammer.tooltip"))
            .register();
    /*public static final MultiblockMachineDefinition AGGLOMERATION_PLATE = GTRegistration.REGISTRATE
            .multiblock("agglomeration_plate", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(FBRecipeTypes.AGGLOMERATION_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(MANASTEEL_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CC CC", "CCCCC", " CCC ", "     ", "     ")
                    .aisle("CCCCC", "CCCCC", "CCCCC", "R    ", "L    ")
                    .aisle("CC CC", "CCICC", " CCC ", "     ", "     ")
                    .where(' ', any())
                    .where("C", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1)))
                    .where('I', controller(blocks(definition.getBlock())))
                    .where('R', blocks(IRON_BARS))
                    .where('L', blocks(LIGHTNING_ROD))
                    .build())
            .workableCasingRenderer(forgeborncore.id("block/casings/manasteel_casing/manasteel_casing"),
                    GTCEu.id("block/machines/electrolyzer"))
            .register();*/

    public final static MultiblockMachineDefinition DRONE_BASE = REGISTRATE
            .multiblock("drone_base", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(FBRecipeTypes.DRONE_MISSIONS)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CC CC", "CCCCC", " CCC ", "     ", "     ")
                    .aisle("CCCCC", "CCCCC", "CCCCC", "R    ", "L    ")
                    .aisle("CC CC", "CCICC", " CCC ", "     ", "     ")
                    .where(' ', any())
                    .where("C", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1)))
                    .where('I', controller(blocks(definition.getBlock())))
                    .where('R', blocks(IRON_BARS))
                    .where('L', blocks(LIGHTNING_ROD))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/implosion_compressor"))
            .register();

    public static final MachineDefinition STEAM_IMPORT_HATCH = GTRegistration.REGISTRATE
            .machine("steam_fluid_input_hatch", holder -> new SteamFluidHatchPartMachine(holder, IO.IN, 4000, 1))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.IMPORT_FLUIDS)
            .overlaySteamHullModel("fluid_hatch.import")
            .tooltips(Component.translatable("gtceu.machine.steam_fluid_hatch_notice"))
            .langValue("Fluid Input Hatch (Steam)")
            .register();
    public static final MachineDefinition STEAM_EXPORT_HATCH = GTRegistration.REGISTRATE
            .machine("steam_fluid_output_hatch", holder -> new SteamFluidHatchPartMachine(holder, IO.OUT, 4000, 1))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.EXPORT_FLUIDS)
            .overlaySteamHullModel("fluid_hatch.export")
            .langValue("Fluid Output Hatch (Steam)")
            .register();


    ///////////////////////
    /// SIMPLE MACHINES ///
    ///////////////////////

    //Maybe in Future
    /*public static final Pair<MachineDefinition, MachineDefinition> STEAM_WIREMILL = registerSteamMachines(
            "steam_wiremill", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.WIREMILL_RECIPES)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/wiremill")))
                    .register());
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_BENDER = registerSteamMachines(
            "steam_bender", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.BENDER_RECIPES)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/bender")))
                    .register());
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_EXTRUDER = registerSteamMachines(
            "steam_extruder", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.EXTRUDER_RECIPES)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/extruder")))
                    .register());*/

    /// ////////////////////////////////////////////

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> builder
                        .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                        .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                        .rotationState(RotationState.NON_Y_AXIS)
                        .recipeType(recipeType)
                        .recipeModifier(
                                GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                        .workableTieredHullModel(GTCEu.id("block/machines/" + name))
                        .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                tankScalingFunction.apply(tier), true))
                        .register(),
                tiers);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, ELECTRIC_TIERS);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType) {
        return registerSimpleMachines(name, recipeType, defaultTankSizeFunction);
    }

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[tiers.length];
        for (int i = 0; i < tiers.length; i++) {
            int tier = tiers[i];
            var register = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase() + "_" + name, holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[i] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static Component explosion() {
        if (ConfigHolder.INSTANCE.machines.shouldWeatherOrTerrainExplosion)
            return Component.translatable("gtceu.universal.tooltip.terrain_resist");
        return null;
    }

    public static Component[] workableTiered(int tier, long voltage, long energyCapacity, GTRecipeType recipeType,
                                             long tankCapacity, boolean input) {
        List<Component> tooltipComponents = new ArrayList<>();
        tooltipComponents
                .add(input ? Component.translatable("gtceu.universal.tooltip.voltage_in", voltage, GTValues.VNF[tier]) :
                        Component.translatable("gtceu.universal.tooltip.voltage_out", voltage, GTValues.VNF[tier]));
        tooltipComponents
                .add(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity", energyCapacity));
        if (recipeType.getMaxInputs(FluidRecipeCapability.CAP) > 0 ||
                recipeType.getMaxOutputs(FluidRecipeCapability.CAP) > 0)
            tooltipComponents
                    .add(Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", tankCapacity));
        return tooltipComponents.toArray(Component[]::new);
    }

    public static void init() {}
}
