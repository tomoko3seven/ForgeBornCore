package net.sqvizers.forgeborncore.common.data;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.sqvizers.forgeborncore.data.recipe.MiscRecipes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class FBCRecipes {

    /*public final static GTRecipeType CONCRETE_MIXER_RECIPES = register(
            "concrete_mixer", MULTIBLOCK)
            .setMaxIOSize(3, 3, 1, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1)
            .setSound(GTSoundEntries.CENTRIFUGE);*/

    public static void init(Consumer<FinishedRecipe> provider) {
        MiscRecipes.init(provider);
    }
}
