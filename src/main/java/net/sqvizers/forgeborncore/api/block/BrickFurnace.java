package net.sqvizers.forgeborncore.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.property.Properties;

public class BrickFurnace extends FurnaceBlock {
    public BrickFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrickFurnaceEntity(pos, state);
    }
}
