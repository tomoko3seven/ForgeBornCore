package net.sqvizers.forgeborncore.api.item.gun;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ShotgunItem extends GunItem {
    private final int bulletCount;

    public ShotgunItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy, int bulletCount) {
        super(properties, bonusDamage, damageMultiplier, fireDelay, inaccuracy);
        this.bulletCount = bulletCount;
    }

    @Override
    protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, IBullet bulletItem) {
        for (int i = 0; i < bulletCount; i++) super.shoot(world, player, gun, ammo, bulletItem);
    }

    @Override
    protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
        tooltip.add(Component.translatable("tooltip.forgeborncore.shotgun.shots", bulletCount).withStyle(ChatFormatting.GRAY));
    }
}
