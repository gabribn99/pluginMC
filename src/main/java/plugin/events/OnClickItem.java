package plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnClickItem implements Listener {
	@EventHandler
    public void Entrada(EntityDamageByEntityEvent event) {
		 if(event.getDamager() instanceof Player) {
			Player atacante = (Player) event.getDamager();
			event.getCause().toString();
			atacante.sendMessage("Has golpeado");
			
		}
		}
}
