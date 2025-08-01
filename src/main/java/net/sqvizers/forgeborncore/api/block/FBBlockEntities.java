package net.sqvizers.forgeborncore.api.block;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.sqvizers.forgeborncore.common.data.FBBlocks;

import static net.sqvizers.forgeborncore.api.registries.FBCRegistries.REGISTRATE;

public class FBBlockEntities {

    /*@SuppressWarnings("unchecked")
    public static final BlockEntityEntry<BrickFurnaceEntity> CABLE = REGISTRATE
            .blockEntity("brick_furnace", BrickFurnaceEntity::create)
            .onRegister(BrickFurnaceEntity::onBlockEntityRegister)
            .validBlocks(FBBlocks.BRICK_FURNACE.values().toArray(BlockEntry[]::new))
            .register();

    public static final RegistryObject<BlockEntityType<BrickFurnaceEntity>> BRICK_FURNACE =
            forgeborncore.registrate()
                    .blockEntity("brick_furnace", BrickFurnaceEntity::new)
                    .validBlocks(FBBlocks.BRICK_FURNACE.get())
                    .register();*/
}
