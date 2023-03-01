package plugin.events;

import org.apache.commons.text.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnClickItem implements Listener {
    @EventHandler
    public void Entrada(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player atacante = (Player) event.getDamager();
            System.out.println(event.getEntity().toString());
            String itemInHand = ((Player) event.getDamager()).getInventory().getItemInMainHand().getType().toString();
            atacante.sendMessage("Has golpeado a " + event.getEntity().getName() + " con " + WordUtils.capitalizeFully(itemInHand.replace("_", " ")));

        }
    }
}
