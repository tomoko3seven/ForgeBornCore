package net.sqvizers.forgeborncore.common.data.flags;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_DENSE;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_PLATE;

public class FBFlags {

    public static final MaterialFlag GENERATE_REINFORCED = new MaterialFlag.Builder("generate_reinforced").requireFlags(GENERATE_DENSE).requireProps(PropertyKey.DUST).build();
    public static final MaterialFlag GENERATE_INFUSED = new MaterialFlag.Builder("generate_infused").requireFlags(GENERATE_PLATE).requireProps(PropertyKey.DUST).build();

    public static final MaterialFlag GENERATE_NANITES = new MaterialFlag.Builder("generate_infused").build();

    public static void init() {}
}
