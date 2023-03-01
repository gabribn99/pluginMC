package gabriel.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPM implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player playerSender = (Player) sender;
        Player playerReceiver = (args != null) ? Bukkit.getPlayer(args[0]) : null;
        if (playerReceiver == null) {
            sender.sendMessage(ChatColor.RED + "El comando esta incompleto [Falta el destinatario]");
            return false;
        }
        if (sender instanceof Player) {
            playerSender.teleport(playerReceiver);
            playerReceiver.sendMessage(ChatColor.GOLD + "¡Ha llegado " + playerSender.getName() + "!");
        }
        return false;
    }
}
