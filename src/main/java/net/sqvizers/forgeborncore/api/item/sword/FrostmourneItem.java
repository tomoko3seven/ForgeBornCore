package net.sqvizers.forgeborncore.api.item.sword;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.sqvizers.forgeborncore.api.item.ModEffects;


public class FrostmourneItem extends SwordItem {
    public FrostmourneItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        Player player = (Player) p_43280_;

        if (!player.getCooldowns().isOnCooldown(this)) {
            p_43279_.addEffect(new MobEffectInstance(ModEffects.STUN.get(), 50, 20));
            player.getCooldowns().addCooldown(this, 50);
        }

        return super.hurtEnemy(p_43278_, p_43279_, p_43280_);
    }
}
