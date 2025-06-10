package net.sqvizers.forgeborncore.api.item.gun;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sqvizers.forgeborncore.common.entity.projectile.BulletEntity;


import javax.annotation.Nullable;

public interface IBullet {
    /**
     * Creates a projectile and set its stats and stuff. The gun will give it velocity and spawn it in the world.
     */
    BulletEntity createProjectile(Level world, ItemStack stack, LivingEntity shooter);

    void consume(ItemStack stack, Player player);

    default boolean hasAmmo(ItemStack stack) {
        return !stack.isEmpty();
    }

    /**
     * Called on server only when a default projectile (or one that extends it) sucessfully damages a LivingEntity (so after damage).
     * <br/>May change that later.
     */
    default void onLivingEntityHit(BulletEntity projectile, LivingEntity target, @Nullable Entity shooter, Level world) {}

    /**
     * Called on server only as damage is being applied when a bullet carrying this item hits. The target may not be a LivingEntity.
     * <br/>May change that later.
     */
    default double modifyDamage(double damage, BulletEntity projectile, Entity target, @Nullable Entity shooter, Level world) {
        return damage;
    }
}
