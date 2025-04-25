package net.sqvizers.forgeborncore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.LargeBoilerRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.PrimitiveBlastFurnaceRenderer;
import com.gregtechceu.gtceu.common.block.BoilerFireboxType;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveBlastFurnaceMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.config.ConfigHolder;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidType;
import net.sqvizers.forgeborncore.api.machine.multiblock.PrimitiveFurnaceMachine;
import net.sqvizers.forgeborncore.common.data.machine.multiblock.steam.WeakSteamParallelMultiBlockMachine;
import net.sqvizers.forgeborncore.gtbrucke.FBRecipeTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static net.minecraft.world.level.block.Blocks.*;
import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;
import static net.sqvizers.forgeborncore.common.FBBlocks.COBBLESTONE_BRICKS;

public class FBCMachines {
    public final static int[] ELECTRIC_TIERS = GTValues.tiersBetween(LV, GTCEuAPI.isHighTier() ? OpV : UV);
    public final static int[] LOW_TIERS = GTValues.tiersBetween(LV, EV);
    public final static int[] HIGH_TIERS = GTValues.tiersBetween(IV, GTCEuAPI.isHighTier() ? OpV : UHV);
    public static final Int2IntFunction defaultTankSizeFunction = tier -> (tier <= GTValues.LV ? 4 : tier == GTValues.MV ? 12 : tier == GTValues.HV ? 16 : tier == GTValues.EV ? 32 : 64) * FluidType.BUCKET_VOLUME;


    /*public static final MachineDefinition PRIMITIVE_FURNACE = REGISTRATE.multiblock("primitive_furnace", MultiblockControllerMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(COBBLESTONE_BRICKS)
            .recipeType(GTRecipeTypes.FURNACE_RECIPES)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX",  "XXX", "XXX", " X ", " X ")
                    .aisle("XXX",  "XCX", "X#X", "X#X", "X#X")
                    .aisle("XXX",  "XSX", "XXX", " X ", " X ")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('#', Predicates.air())
                    .where(' ', Predicates.any())
                    .where('C', Predicates.blocks(COAL_BLOCK))
                    .where('X', blocks(COBBLESTONE_BRICKS.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),

                    GTCEu.id("block/machines/furnace"), false)
            .tooltips(Component.translatable("block.forgeborncore.steam_forge_hammer.tooltip"))
            .register();*/

    /*public static final MultiblockMachineDefinition PRIMITIVE_FURNACE = REGISTRATE
            .multiblock("primitive_furnace", PrimitiveFurnaceMachine::new) //(a) -> new PrimitiveFurnaceMachine(a))
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.FURNACE_RECIPES)
            .renderer(() -> new PrimitiveBlastFurnaceRenderer(GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTCEu.id("block/multiblock/primitive_blast_furnace"))) //
            .hasTESR(true)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", "XXX", "XXX")
                    .aisle("XXX", "X#X", "X#X", "X#X")
                    .aisle("XXX", "XYX", "XXX", "XXX")
                    .where('X', blocks(CASING_PRIMITIVE_BRICKS.get()))
                    .where('#', Predicates.air())
                    .where('Y', Predicates.controller(blocks(definition.getBlock())))
                    .build())
            .register();*/

    public static final MultiblockMachineDefinition HIGH_PRESSURE_ASSEMBLER = GTRegistration.REGISTRATE
            .multiblock("high_pressure_assembler", WeakSteamParallelMultiBlockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ASSEMBLER_RECIPES)
            .recipeModifier(WeakSteamParallelMultiBlockMachine::recipeModifier, true)
            .appearanceBlock(STEEL_BRICKS_HULL)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BBBBB", " BBB ")
                    .aisle("AAAAA", "BDDDB", " BBB ")
                    .aisle("AAAAA", "BYBBB", " BBB ")
                    .where('B', blocks(STEEL_BRICKS_HULL.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1)
                                    .setExactLimit(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)
                                    .setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1).setExactLimit(1)))
                    .where(' ', Predicates.any())
                    .where('Y', Predicates.controller(blocks(definition.getBlock())))
                    .where('A', blocks(FIREBOX_STEEL.get()).setMinGlobalLimited(11)
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('D', blocks(CASING_STEEL_GEARBOX.get()))
                    .build())
            .renderer(() -> new LargeBoilerRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    BoilerFireboxType.STEEL_FIREBOX,
                    GTCEu.id("block/multiblock/implosion_compressor")))
            /*.tooltips(Component.translatable("cosmiccore.multiblock.hpsassem.tooltip.0"),
                    Component.translatable("cosmiccore.multiblock.hpsassem.tooltip.1"),
                    Component.translatable("cosmiccore.multiblock.hpsassem.tooltip.2"))*/
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

    public static final MachineDefinition STEEL_STEAM_FORGE_HAMMER = REGISTRATE.multiblock("steel_steam_forge_hammer", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.FORGE_HAMMER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
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
                    .where('G', Predicates.blocks(CASING_STEEL_GEARBOX.get()))
                    .where('X', blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),

                    GTCEu.id("block/machines/forge_hammer"), false)
            .tooltips(Component.translatable("forgeborncore.multiblock.steel_steam_forge_hammer.tooltip"))
            .register();

    /*public static final MachineDefinition STEAM_SIFTING_FUNNEL = REGISTRATE.multiblock("steam_sifting_funnel", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.SIFTER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("FFFFF", "BXXXB", "BXXXB", "BXXXB")
                    .aisle("FFFFF", "X   X", "X   X", "X   X")
                    .aisle("FFFFF", "X   X", "X   X", "X   X")
                    .aisle("FFFFF", "BXSXB", "BXXXB", "BXXXB")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('F', blocks(BRONZE_BRICKS_HULL.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .where(' ', Predicates.air())
                    .where('X', blocks(CASING_BRONZE_BRICKS.get()))
                    .where('B', blocks(BRONZE_HULL.get()))
                    .build())
            .tooltips(Component.translatable("block.forgeborncore.steam_sifting_funnel.tooltip"))
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),

                    GTCEu.id("block/machines/sifter"), false)
            .register();*/

    /*public static final MachineDefinition STEAM_CONTAINER = REGISTRATE.multiblock("steam_container", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.SIFTER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("FFFFF", "BXXXB", "BXXXB", "BXXXB")
                    .aisle("FFFFF", "X   X", "X   X", "X   X")
                    .aisle("FFFFF", "X   X", "X   X", "X   X")
                    .aisle("FFFFF", "BXSXB", "BXXXB", "BXXXB")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('F', blocks(BRONZE_BRICKS_HULL.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .where(' ', Predicates.air())
                    .where('X', blocks(CASING_BRONZE_BRICKS.get()))
                    .where('B', blocks(BRONZE_HULL.get()))
                    .build())
            .tooltips(Component.translatable("block.forgeborncore.steam_sifting_funnel.tooltip"))
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),

                    GTCEu.id("block/machines/tank"), false)
            .register();*/


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
