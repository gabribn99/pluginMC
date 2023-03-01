package gabriel.events;

import gabriel.core.Main;
import gabriel.entities.BalanceBean;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit implements Listener {

    @EventHandler
    public void Entrada(PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.GREEN + "El jugador " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RESET + ChatColor.GREEN + " se ha unido");
        try {
            Thread.sleep(1000);
            double amount = 10; //Este dato deberá cargar de un json
            Main.mapBalances.put(event.getPlayer().getName(), new BalanceBean(event.getPlayer().getName(), amount));
            event.getPlayer().sendMessage(ChatColor.GOLD + "Tu saldo actual es: " + ChatColor.WHITE + amount + "€");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void Salida(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "El jugador " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RESET + ChatColor.RED + " se ha ido");
    }
}
