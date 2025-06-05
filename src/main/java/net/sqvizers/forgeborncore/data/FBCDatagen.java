package net.sqvizers.forgeborncore.data;

import net.sqvizers.forgeborncore.api.registries.FBCRegistries;
import net.sqvizers.forgeborncore.data.lang.LangHandler;

import com.tterrag.registrate.providers.ProviderType;

public class FBCDatagen {

    public static void init() {
        FBCRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}
