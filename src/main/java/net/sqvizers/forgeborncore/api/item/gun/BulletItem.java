package net.sqvizers.forgeborncore.api.item.gun;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sqvizers.forgeborncore.common.entity.projectile.BulletEntity;

import javax.annotation.Nullable;
import java.util.List;

public class BulletItem extends Item implements IBullet {
    private int damage;

    public BulletItem(Properties properties, int damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public BulletEntity createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
        BulletEntity bulletEntity = new BulletEntity(world, shooter);

        bulletEntity.setItem(stack);
        bulletEntity.setDamage(damage);

        return bulletEntity;
    }

    @Override
    public void consume(ItemStack stack, Player player) {
        stack.shrink(1);
        if (stack.isEmpty()) {
            player.getInventory().removeItem(stack);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.forgeborncore.bullet.damage", damage).withStyle(ChatFormatting.DARK_GREEN));
    }

    public static boolean isShot(ItemStack stack) {
        return !stack.isEmpty() && stack.getOrCreateTag().contains("shot") && stack.getOrCreateTag().getBoolean("shot");
    }
}
