package net.sqvizers.forgeborncore.common.entity.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sqvizers.forgeborncore.api.item.gun.IBullet;
import net.sqvizers.forgeborncore.common.entity.ModEntities;

public class BulletEntity extends Fireball {
    protected double damage = 1;
    protected boolean ignoreInvulnerability = false;
    protected double knockbackStrength = 0;
    protected int ticksSinceFired;

    public BulletEntity(EntityType<? extends BulletEntity> p_i50160_1_, Level p_i50160_2_) {
        super(p_i50160_1_, p_i50160_2_);
    }

    public BulletEntity(Level worldIn, LivingEntity shooter) {
        this(worldIn, shooter, 0, 0, 0);
        setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
    }

    public BulletEntity(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(ModEntities.BULLET.get(), shooter, accelX, accelY, accelZ, worldIn);
    }

    public BulletEntity(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ModEntities.BULLET.get(), x, y, z, accelX, accelY, accelZ, worldIn);
    }

    private static final double STOP_TRESHOLD = 0.01;

    @Override
    public void tick() {
        // Using a thing I save so that bullets don't get clogged up on chunk borders
        ticksSinceFired++;

        if (ticksSinceFired > 100 || getDeltaMovement().lengthSqr() < STOP_TRESHOLD) {
            remove(RemovalReason.KILLED);
        }

        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        if (Minecraft.getInstance().level.isClientSide()) {
            Entity target = entityHitResult.getEntity();
            Entity shooter = getOwner();
            IBullet bullet = (IBullet) getItemRaw().getItem();

            if (isOnFire()) target.setSecondsOnFire(5);
            int lastHurtResistant = target.invulnerableTime;

            if (ignoreInvulnerability) target.invulnerableTime = 0;
            boolean damaged = target.hurt(this.damageSources().explosion(this, shooter), (float) bullet.modifyDamage(damage, this, target, shooter, Minecraft.getInstance().level));

            if (damaged && target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity) target;

                if (knockbackStrength > 0) {
                    double actualKnockback = knockbackStrength;

                    Vec3 vec = getDeltaMovement().multiply(1, 0, 1).normalize().scale(actualKnockback);
                    if (vec.lengthSqr() > 0) livingTarget.push(vec.x, 0.1, vec.z);
                }

                if (shooter instanceof LivingEntity) doEnchantDamageEffects((LivingEntity) shooter, target);

                bullet.onLivingEntityHit(this, livingTarget, shooter, Minecraft.getInstance().level);
            } else if (!damaged && ignoreInvulnerability) target.invulnerableTime = lastHurtResistant;
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (!Minecraft.getInstance().level.isClientSide && (!noPhysics || result.getType() != HitResult.Type.BLOCK)) remove(RemovalReason.KILLED);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("tsf", ticksSinceFired);
        compound.putDouble("damage", damage);

        if (ignoreInvulnerability) compound.putBoolean("ignoreinv", ignoreInvulnerability);
        if (knockbackStrength != 0) compound.putDouble("knockback", knockbackStrength);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        ticksSinceFired = compound.getInt("tsf");
        damage = compound.getDouble("damage");
        //The docs say if it's not here it's going to be false0, so it should be good
        ignoreInvulnerability = compound.getBoolean("ignoreinv");
        knockbackStrength = compound.getDouble("knockback");

    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setIgnoreInvulnerability(boolean ignoreInvulnerability) {
        this.ignoreInvulnerability = ignoreInvulnerability;
    }

    @Override
    public void shootFromRotation(Entity entity, float xRot, float yRot, float p_37255_, float speed, float spread) {
        float f = -Mth.sin(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
        float f1 = -Mth.sin((xRot + p_37255_) * ((float) Math.PI / 180F));
        float f2 = Mth.cos(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
        shoot((double) f, (double) f1, (double) f2, speed, spread);
    }

    /**
     * Knockback on impact, 0.6 is equivalent to Punch I.
     */
    public void setKnockbackStrength(double knockbackStrength) {
        this.knockbackStrength = knockbackStrength;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected float getInertia() {
        return 1;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
