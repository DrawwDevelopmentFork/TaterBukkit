package io.github.fukkitmc.fukkit.extras;

/**
 * Extra for {@link net.minecraft.item.BucketItem}
 */
public interface BucketItemExtra {

    net.minecraft.item.ItemStack a(net.minecraft.item.ItemStack var0, net.minecraft.entity.player.PlayerEntity var1, net.minecraft.item.Item var2, org.bukkit.inventory.ItemStack var3);

    boolean a(net.minecraft.entity.player.PlayerEntity var0, net.minecraft.world.World var1, net.minecraft.util.math.BlockPos var2, net.minecraft.util.hit.BlockHitResult var3, net.minecraft.util.math.Direction var4, net.minecraft.util.math.BlockPos var5, net.minecraft.item.ItemStack var6);
}
