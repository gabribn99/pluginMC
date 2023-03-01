package plugin.events;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import utils.Utils;

import java.util.Random;

public class OnClickItem implements Listener {
    @EventHandler
    public void Entrada(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            ItemStack itemInHand = ((Player) event.getDamager()).getInventory().getItemInMainHand();
            ItemMeta meta = itemInHand.getItemMeta();
            String itemName;
            if (meta != null && meta.hasDisplayName()) {
                itemName = meta.getDisplayName();
            } else {
                itemName = Utils.capitalizeFully(itemInHand.getType().getKey().getKey().toLowerCase().replace("_", " "));
            }
            effects(itemName, ((Player) event.getDamager()).getPlayer(), (Player) event.getEntity(), event.getFinalDamage());
            event.getDamager().sendMessage("Damage: " + event.getDamage() + "\nFinal Damage: " + event.getFinalDamage());
        }
    }

    public void effects(String itemName, Player damager, Player damaged, double damage) {
        if (itemName.equals(ChatColor.RED + "Stick of Doom")) {
            System.out.println("Prueba");
            World world = damaged.getLocation().getWorld();
            int dice = (int) ((Math.random() * 6) + 1);
            switch (dice) {
                case 1:
                    damager.teleport(new Location(world, 0, -150, 0));
                    damager.sendMessage(ChatColor.DARK_RED + "El destino ha decidido que castigarte por abusar del poder que se te ha concedido");
                    break;
                case 6:
                    damaged.teleport(new Location(world, 0, -150, 0));
                    damaged.sendMessage(ChatColor.GOLD + "Has sido condenado");
                    damager.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Sangre para el dios de la sangre y calaveras para el trono de las calaveras");
                    break;
                default:
                    damager.setHealth(damager.getHealth() + damage);
                    damager.sendMessage("Has sido recompensado por tu sacrificio");
            }
        }
    }
}
