package com.jasonjat.episodeone.mixin;

import com.jasonjat.episodeone.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Inject(method="damage", at = @At(value = "TAIL"))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if (source.getAttacker() == null) return;
        Entity attacker = source.getAttacker();

        Iterable<ItemStack> armorItems = this.getArmorItems();

        boolean check = false;
        for (ItemStack armor : armorItems) {
            if (armor.getItem() instanceof ArmorItem armorItem) {
                check = armorItem.getMaterial() == ItemRegistry.COPPER_ARMOR_MATERIAL;
            } else {
                check = false;
                break;
            }
        }

        if (check) {
//            attacker.addVelocity(0, 3, 0);
            Objects.requireNonNull(Objects.requireNonNull(attacker.getServer()).getWorld(attacker.getWorld().getRegistryKey())).spawnParticles(ParticleTypes.FLAME, attacker.getX(), attacker.getY(), attacker.getZ(), 250, 1,1,1, 0.2);
        }
    }
}
