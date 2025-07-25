package net.sqvizers.forgeborncore.api.data;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import net.sqvizers.forgeborncore.data.material.ForgeMaterials;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class FBMaterialFlagAddition {

    public static void register() {
        OreProperty oreProp = ForgeMaterials.Froststeel.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Iron, Iron, Aluminium);
        oreProp.setWashedIn(ForgeMaterials.Cryotheum);
}
}