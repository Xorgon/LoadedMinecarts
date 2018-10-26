package me.xorgon.loadedminecarts;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class LMListener implements Listener {

    LoadedMinecartsPlugin plugin = LoadedMinecartsPlugin.getInstance();

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        Chunk chunk = event.getChunk();
        boolean stayLoaded = false;
        for (Entity entity : chunk.getEntities()) {
            if (entity.getType() == EntityType.MINECART_CHEST) {
                if (((Vehicle) entity).getVelocity().lengthSquared() != 0) {
                    stayLoaded = true;
                }
            }
        }
        if (stayLoaded) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onMinecartMove(VehicleMoveEvent event) {
        if (!(event.getVehicle() instanceof StorageMinecart)) {
            return;
        }

        Location toLoc = event.getTo();
        World world = toLoc.getWorld();

        if (!world.getChunkAt(toLoc).isLoaded()) {
            world.getChunkAt(toLoc).load();
        }
    }

}
