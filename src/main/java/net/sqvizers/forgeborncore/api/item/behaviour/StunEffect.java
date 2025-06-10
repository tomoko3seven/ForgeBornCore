package net.sqvizers.forgeborncore.api.item.behaviour;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class StunEffect extends MobEffect {
    public StunEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity p_19467_, int p_19468_) {

        if (!p_19467_.level().isClientSide()) {
            double x = p_19467_.getX();
            double y = p_19467_.getY();
            double z = p_19467_.getZ();

            p_19467_.teleportTo(x, y, z);
            p_19467_.setDeltaMovement(0, 0, 0);
        }

        super.applyEffectTick(p_19467_, p_19468_);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
