package gabriel.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit implements Listener {

    @EventHandler
    public void Entrada(PlayerJoinEvent event) {

        event.setJoinMessage("El jugador de " + event.getPlayer().getName() + " se ha unido");
    }

    @EventHandler
    public void Salida(PlayerQuitEvent event) {
        event.setQuitMessage("El jugador " + event.getPlayer().getName() + " se ha ido");
    }
}
