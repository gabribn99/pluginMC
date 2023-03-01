package gabriel.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit implements Listener {

    @EventHandler
    public void Entrada(PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.GREEN + "El jugador " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RESET + ChatColor.GREEN + " se ha unido");
    }

    @EventHandler
    public void Salida(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "El jugador " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RESET + ChatColor.RED + " se ha ido");
    }
}
