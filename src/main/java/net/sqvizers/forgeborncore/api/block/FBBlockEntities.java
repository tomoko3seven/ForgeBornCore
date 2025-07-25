package net.sqvizers.forgeborncore.api.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.sqvizers.forgeborncore.common.data.FBBlocks;

public class FBBlockEntities {
    public static final RegistryObject<BlockEntityType<BrickFurnaceEntity>> BRICK_FURNACE =
            BRICK_FURNACE.register("brick_furnace",
                    () -> BlockEntityType.Builder.of(BrickFurnaceEntity::new, FBBlocks.BRICK_FURNACE.get()).build(null));
}
