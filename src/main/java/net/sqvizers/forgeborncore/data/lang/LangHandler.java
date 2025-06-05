package net.sqvizers.forgeborncore.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class LangHandler {

    public static void init(RegistrateLangProvider provider) {
        replace(provider, "cosmiccore.multiblock.naqreactor.tooltip.0",
                "§cA massive reactor powered by explosions and reactive fuel");
        replace(provider, "cosmiccore.multiblock.naqreactor.tooltip.1",
                "§bWill always attempt to parallel to 16x output.");
        replace(provider, "cosmiccore.multiblock.naqreactor.tooltip.2", "§cOnly Accepts Laser hatches.");

        replace(provider, "forgeborncore.lv_botanical_circuit", "§aLV Botanical Circuit");
    }
}
