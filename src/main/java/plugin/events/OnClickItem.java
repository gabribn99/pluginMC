package plugin.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import utils.Utils;

public class OnClickItem implements Listener {
    @EventHandler
    public void Entrada(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player atacante = (Player) event.getDamager();
            System.out.println(event.getEntity().toString());
            ItemStack itemInHand = ((Player) event.getDamager()).getInventory().getItemInMainHand();
            ItemMeta meta = itemInHand.getItemMeta();
            String itemName;
            if (meta != null && meta.hasDisplayName()) {
                itemName = meta.getDisplayName();
            } else {
                itemName = Utils.capitalizeFully(itemInHand.getType().getKey().getKey().toLowerCase().replace("_", " "));
            }
            atacante.sendMessage("Has golpeado a " + event.getEntity().getName() + " con " + itemName);

        }
    }
}
