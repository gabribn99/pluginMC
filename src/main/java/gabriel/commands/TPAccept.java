package gabriel.commands;

import org.bukkit.ChatColor;
import gabriel.core.Main;
import gabriel.entities.TPBean;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAccept implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            TPBean tp = Main.mapTps.get(sender.getName());
            if (tp == null) {
                player.sendMessage(ChatColor.DARK_AQUA + "No tienes ninguna petición de tp pendiente.");
                return false;
            }
            player.sendMessage(ChatColor.GOLD + "Has aceptado la petición de tp, se te está teletransportando...");
            tp.getSender().sendMessage(ChatColor.GOLD + player.getName() + " ha aceptado tu solicitud de tp.");
            tp.getSender().teleport(player);
            Main.mapTps.remove(sender.getName());
        }
        return false;
    }
}