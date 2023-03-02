package plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.core.Main;
import plugin.entities.BalanceBean;

public class PlayerJoinQuit implements Listener {

    public static final double DEFAULT_AMOUNT = 10;

    @EventHandler
    public void Entrada(PlayerJoinEvent event) {

        BalanceBean wallet = Main.mapBalances.get(event.getPlayer().getName());
        if (wallet == null) {
            Main.mapBalances.put(event.getPlayer().getName(), new BalanceBean(event.getPlayer().getName(), DEFAULT_AMOUNT));
            event.getPlayer().sendMessage("Saldo actual: " + DEFAULT_AMOUNT + "₱");
        } else {
            event.getPlayer().sendMessage("Saldo actual: " + wallet.getAmount() + "₱");
        }
        event.setJoinMessage(ChatColor.GREEN + "El jugador " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RESET + ChatColor.GREEN + " se ha unido");
    }

    @EventHandler
    public void Salida(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "El jugador " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RESET + ChatColor.RED + " se ha ido");
    }
}
