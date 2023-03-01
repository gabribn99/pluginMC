package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player playerSender = (Player) sender;
            Player playerReceiver = playerSender;
            if (args != null & args.length > 0) {
                playerReceiver = Bukkit.getPlayer(args[0]);
            }
            playerReceiver.setHealth(20);
            playerReceiver.setFoodLevel(20);
            playerSender.sendMessage(ChatColor.GREEN + "El jugador " + playerReceiver.getName() + " se ha curado");
            playerReceiver.sendMessage(ChatColor.GREEN + "El jugador " + playerSender.getName() + " te ha curado");
        }
        return false;
    }
}