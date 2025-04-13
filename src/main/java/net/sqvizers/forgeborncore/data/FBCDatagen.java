package net.sqvizers.forgeborncore.data;

import net.sqvizers.forgeborncore.api.registries.FBCRegistries;
import com.tterrag.registrate.providers.ProviderType;
import net.sqvizers.forgeborncore.data.lang.LangHandler;

public class FBCDatagen {
    public static void init() {

        FBCRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}
