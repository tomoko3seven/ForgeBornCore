package net.sqvizers.forgeborncore.common.data.tag;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.sqvizers.forgeborncore.common.data.flags.FBFlags;

import static net.sqvizers.forgeborncore.api.data.FBMaterialIconType.*;

public class FBTagPrefix{

    public static final TagPrefix reinforced;
    public static final TagPrefix infused;

    public static final TagPrefix nanites;

    static {
        reinforced = new TagPrefix("reinforced_plates")
                .defaultTagPath("reinforced/%s")
                .unformattedTagPath("reinforced")
                .materialAmount(GTValues.L)
                .unificationEnabled(true)
                .generateItem(true)
                .maxStackSize(64)
                .materialIconType(REINFORCED)
                .generationCondition(mat -> mat.hasFlag(FBFlags.GENERATE_REINFORCED));
        infused = new TagPrefix("infused_plates")
                .materialAmount(GTValues.L)
                .unificationEnabled(true)
                .generateItem(true)
                .maxStackSize(64)
                .materialIconType(INFUSED)
                .generationCondition(mat -> mat.hasFlag(FBFlags.GENERATE_INFUSED));

        nanites = new TagPrefix("nanites")
                .idPattern("%s_nanites")
                .defaultTagPath("nanites/%s")
                .unformattedTagPath("nanites")
                .materialAmount(GTValues.M)
                .materialIconType(NANITES)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(mat -> mat.hasFlag(FBFlags.GENERATE_NANITES));
    }
    public static void init() {}
}