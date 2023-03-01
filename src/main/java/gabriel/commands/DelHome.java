package gabriel.commands;

import gabriel.core.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                Main.mapHomes.remove(player.getName());
            } catch (Exception e) {
                player.sendMessage("No tienes un home asignado");
            }
        }
        return false;
    }
}
