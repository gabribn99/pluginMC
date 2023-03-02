package plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import plugin.core.Main;

import java.util.UUID;

public class LockChest implements Listener {

    @EventHandler
    public void lockChest(SignChangeEvent event) {
        Player player = event.getPlayer();
        Block sign = event.getBlock();
        if (sign != null && sign.getState() instanceof Sign) {
            BlockData data = sign.getBlockData();
            if (data instanceof Directional) {
                Directional directional = (Directional) data;
                Block blockBehind = sign.getRelative(directional.getFacing().getOppositeFace());
                //Temporal
                player.sendMessage(blockBehind.getLocation().toString());

                if (blockBehind.getType() == Material.CHEST) {
                    Chest chest = (Chest) blockBehind.getState();
                    if (event.getLine(0).equals("Bloquear")) {
                        event.setLine(0, ChatColor.BLUE + "[" + player.getName() + "]");
                        event.setLine(1, ChatColor.RED + "Cofre Bloqueado");

                        UUID playerId = player.getUniqueId();
                        Location chestLocation = chest.getLocation();

                        Main.mapLockedChests.put(chestLocation, playerId);
                    }
                }
            }
        }
    }

    @EventHandler
    public void openChest(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock.getType() == Material.CHEST) {
            Chest chest = (Chest) clickedBlock.getState();
            UUID playerID = Main.mapLockedChests.get(chest.getLocation());
            if (playerID != null) {
                if(playerID == player.getUniqueId()){
                    player.openInventory(chest.getInventory());
                }else{
                    player.sendMessage("No estás autorizado a abrir este cofre");
                }
            }
        }
    }
}
