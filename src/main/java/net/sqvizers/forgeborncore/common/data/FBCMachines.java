package net.sqvizers.forgeborncore.common.data;

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
import com.gregtechceu.gtceu.client.renderer.machine.QuantumTankRenderer;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.common.machine.storage.QuantumTankMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;
import net.sqvizers.forgeborncore.api.machine.part.SteamFluidHatchPartMachine;
import net.sqvizers.forgeborncore.common.data.machine.multiblock.steam.WeakSteamParallelMultiBlockMachine;
import net.sqvizers.forgeborncore.gtbrucke.FBRecipeTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.api.pattern.Predicates.frames;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.CASING_INDUSTRIAL_STEAM;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.TANK_TOOLTIPS;
import static com.gregtechceu.gtceu.common.machine.multiblock.steam.BoilerType.BRONZE;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static net.minecraft.world.level.block.Blocks.*;
import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;

public class FBCMachines {
    public final static int[] ELECTRIC_TIERS = GTValues.tiersBetween(LV, GTCEuAPI.isHighTier() ? OpV : UV);
    public final static int[] LOW_TIERS = GTValues.tiersBetween(LV, EV);
    public final static int[] HIGH_TIERS = GTValues.tiersBetween(IV, GTCEuAPI.isHighTier() ? OpV : UHV);
    public static final Int2IntFunction defaultTankSizeFunction = tier -> (tier <= GTValues.LV ? 4 : tier == GTValues.MV ? 12 : tier == GTValues.HV ? 16 : tier == GTValues.EV ? 32 : 64) * FluidType.BUCKET_VOLUME;

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
                    .where(' ', Predicates.any())
                    .where('Y', Predicates.controller(blocks(definition.getBlock())))
                    .where('A', blocks(STEEL_HULL.get()).setMinGlobalLimited(11)
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('D', blocks(CASING_STEEL_GEARBOX.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
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
                    .where(' ', Predicates.any())
                    .where('C', Predicates.controller(blocks(definition.getBlock())))
                    .where('B', blocks(BRONZE_HULL.get()))
                    .where('A', blocks(CASING_BRONZE_PIPE.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
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
                    .where(' ', Predicates.any())
                    .where('C', Predicates.controller(blocks(definition.getBlock())))
                    .where('B', blocks(BRONZE_BRICKS_HULL.get()))
                    .where('F', Predicates.frames(GTMaterials.Bronze))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/canner"))
            .tooltips(Component.translatable("forgeborncore.multiblock.sawmill.tooltip"))
                    .tooltips(Component.translatable("forgeborncore.multiblock.sawmill.tooltip.2"))
            .register();

    public static final MachineDefinition STEAM_FORGE_HAMMER = REGISTRATE.multiblock("steam_forge_hammer", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.FORGE_HAMMER_RECIPES)
            .recipeModifier(WeakSteamParallelMultiBlockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" XXX ",  "     ", "     ", "     ", "     ", "     ", "     ")
                    .aisle("XXXXX",  " XXX ", "     ", "     ", "     ", "  X  ", "     ")
                    .aisle("XXXXX",  "XX XX", "X   X", "X I X", "X I X", "XXIXX", "  G  ")
                    .aisle("XXXXX",  " XSX ", "     ", "     ", "     ", "  X  ", "     ")
                    .aisle(" XXX ",  "     ", "     ", "     ", "     ", "     ", "     ")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('#', Predicates.air())
                    .where(' ', Predicates.any())
                    .where('I', Predicates.blocks(IRON_BLOCK))
                    .where('G', Predicates.blocks(CASING_BRONZE_GEARBOX.get()))
                    .where('X', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),

                    GTCEu.id("block/machines/forge_hammer"), false)
            .tooltips(Component.translatable("block.forgeborncore.steam_forge_hammer.tooltip"))
            .register();

    public static final MachineDefinition STEAM_IMPORT_HATCH = GTRegistration.REGISTRATE
            .machine("steam_fluid_input_hatch", holder -> new SteamFluidHatchPartMachine(holder, IO.IN, 4000, 1))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.IMPORT_FLUIDS)
            .overlaySteamHullRenderer("fluid_hatch.import")
            .tooltips(Component.translatable("gtceu.machine.steam_fluid_hatch_notice"))
            .langValue("Fluid Input Hatch (Steam)")
            .register();
    public static final MachineDefinition STEAM_EXPORT_HATCH = GTRegistration.REGISTRATE
            .machine("steam_fluid_output_hatch", holder -> new SteamFluidHatchPartMachine(holder, IO.OUT, 4000, 1))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.EXPORT_FLUIDS)
            .overlaySteamHullRenderer("fluid_hatch.export")
            .langValue("Fluid Output Hatch (Steam)")
            .register();

    /*public static final MachineDefinition[] SUPER_MANA_TANK = registerTieredMachines("super_tank",
            (holder, tier) -> new QuantumTankMachine(holder, tier,
                    4000 * FluidType.BUCKET_VOLUME * (long) Math.pow(2, tier - 1)),
            (tier, builder) -> builder
                    .langValue("Super Tank " + LVT[tier])
                    .blockProp(BlockBehaviour.Properties::dynamicShape)
                    .rotationState(RotationState.ALL)
                    .renderer(() -> new QuantumTankRenderer(tier))
                    .hasTESR(true)
                    .tooltipBuilder(TANK_TOOLTIPS)
                    .tooltips(Component.translatable("gtceu.machine.quantum_tank.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity",
                                    FormattingUtil.formatNumbers(4_000_000 * (long) Math.pow(2, tier - 1))))
                    .register(),
            LOW_TIERS);*/

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             int... tiers) {
        return registerTieredMachines(name, (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> builder
                .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(recipeType)
                .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                .workableTieredHullRenderer(GTCEu.id("block/machines/" + name))
                .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType, tankScalingFunction.apply(tier), true))
                .register(), tiers);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType, Int2IntFunction tankScalingFunction) {
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
            var register =  REGISTRATE.machine(GTValues.VN[tier].toLowerCase() + "_" + name, holder -> factory.apply(holder, tier))
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

    public static Component[] workableTiered(int tier, long voltage, long energyCapacity, GTRecipeType recipeType, long tankCapacity, boolean input) {
        List<Component> tooltipComponents = new ArrayList<>();
        tooltipComponents.add(input ? Component.translatable("gtceu.universal.tooltip.voltage_in", voltage, GTValues.VNF[tier]) :
                Component.translatable("gtceu.universal.tooltip.voltage_out", voltage, GTValues.VNF[tier]));
        tooltipComponents.add(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity", energyCapacity));
        if (recipeType.getMaxInputs(FluidRecipeCapability.CAP) > 0 || recipeType.getMaxOutputs(FluidRecipeCapability.CAP) > 0)
            tooltipComponents.add(Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", tankCapacity));
        return tooltipComponents.toArray(Component[]::new);
    }

    public static void init() {

    }
}
