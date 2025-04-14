package net.sqvizers.forgeborncore.gtbrucke;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class FBRecipeTypes {
    public final static GTRecipeType INDUSTRIAL_PRIMITIVE_BLAST_FURNACE_RECIPES = register(
            "industrial_primitive_blast_furnace", MULTIBLOCK)
            .setMaxIOSize(3, 3, 1, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1)
            .setSound(GTSoundEntries.FIRE);
}
