package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.passive.FoxEntity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;

public class CraftFox extends CraftAnimals implements Fox {

    public CraftFox(CraftServer server, FoxEntity entity) {
        super(server, entity);
    }

    @Override
    public FoxEntity getHandle() {
        return (FoxEntity) super.getHandle();
    }

    @Override
    public EntityType getType() {
        return EntityType.FOX;
    }

    @Override
    public String toString() {
        return "CraftFox";
    }

    @Override
    public Type getFoxType() {
        return Type.values()[getHandle().getFoxType().ordinal()];
    }

    @Override
    public void setFoxType(Type type) {
        Preconditions.checkArgument(type != null, "type");

        getHandle().setType(FoxEntity.Type.values()[type.ordinal()]);
    }

    @Override
    public boolean isCrouching() {
        return getHandle().isSitting();
    }

    @Override
    public void setCrouching(boolean crouching) {
        getHandle().setCrouching(crouching);
    }

    @Override
    public boolean isSitting() {
        return getHandle().isSitting();
    }

    @Override
    public void setSitting(boolean sitting) {
        getHandle().setSitting(sitting);
    }

    @Override
    public void setSleeping(boolean sleeping) {
        getHandle().setSleeping(sleeping);
    }

    @Override
    public AnimalTamer getFirstTrustedPlayer() {
        UUID uuid = getHandle().getDataTracker().get(FoxEntity.OWNER).orElse(null);
        if (uuid == null) {
            return null;
        }

        AnimalTamer player = getServer().getPlayer(uuid);
        if (player == null) {
            player = getServer().getOfflinePlayer(uuid);
        }

        return player;
    }

    @Override
    public void setFirstTrustedPlayer(AnimalTamer player) {
        if (player == null && getHandle().getDataTracker().get(FoxEntity.OTHER_TRUSTED).isPresent()) {
            throw new IllegalStateException("Must remove second trusted player first");
        }

        getHandle().getDataTracker().set(FoxEntity.OWNER, player == null ? Optional.empty() : Optional.of(player.getUniqueId()));
    }

    @Override
    public AnimalTamer getSecondTrustedPlayer() {
        UUID uuid = getHandle().getDataTracker().get(FoxEntity.OTHER_TRUSTED).orElse(null);
        if (uuid == null) {
            return null;
        }

        AnimalTamer player = getServer().getPlayer(uuid);
        if (player == null) {
            player = getServer().getOfflinePlayer(uuid);
        }

        return player;
    }

    @Override
    public void setSecondTrustedPlayer(AnimalTamer player) {
        if (player != null && !getHandle().getDataTracker().get(FoxEntity.OWNER).isPresent()) {
            throw new IllegalStateException("Must add first trusted player first");
        }

        getHandle().getDataTracker().set(FoxEntity.OTHER_TRUSTED, player == null ? Optional.empty() : Optional.of(player.getUniqueId()));
    }
}
