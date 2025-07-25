package net.sqvizers.forgeborncore.gtbrucke;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class FBRecipeTypes {

    public final static GTRecipeType SAWMILLRECIPES = register(
            "sawmill", MULTIBLOCK)
            .setMaxIOSize(4, 3, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1)
            .setSound(GTSoundEntries.CUT);

    public final static GTRecipeType AGGLOMERATION_RECIPES = register(
            "agglomeration", MULTIBLOCK)
            .setMaxIOSize(5, 1, 1, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1)
            .setSound(GTSoundEntries.COOLING);

    public final static GTRecipeType DRONE_MISSIONS = register(
            "drone_missions", MULTIBLOCK)
            .setMaxIOSize(6, 10, 3, 3)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1);

    public static void init() {}
}
