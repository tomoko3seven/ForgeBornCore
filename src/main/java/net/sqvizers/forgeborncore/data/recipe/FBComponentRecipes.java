package net.sqvizers.forgeborncore.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Naquadria;
import static net.sqvizers.forgeborncore.common.data.FBItems.BASIC_CRAFTING_PATTERN;
import static net.sqvizers.forgeborncore.gtbrucke.FBRecipeTypes.PRIMITIVE_ASSEMBLY_LINE;

public class FBComponentRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        PRIMITIVE_ASSEMBLY_LINE.recipeBuilder("electric_motor_lv")
                .inputItems(rodLong, SamariumMagnetic)
                .inputItems(rodLong, Tritanium, 4)
                .inputItems(ring, Tritanium, 4)
                .inputItems(round, Tritanium, 8)
                .inputItems(wireFine, Americium, 64)
                .inputItems(wireFine, Americium, 64)
                .inputItems(cableGtSingle, YttriumBariumCuprate, 2)
                .inputFluids(SolderingAlloy, L * 4)
                .inputFluids(Lubricant, 1000)
                .outputItems(ELECTRIC_MOTOR_LV)
                .scannerResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_LV.asStack())
                        .EUt(VA[ULV]))
                .duration(30).EUt(16)
                .addMaterialInfo(true).save(provider);
    }
}
