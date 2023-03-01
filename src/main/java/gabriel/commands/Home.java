package gabriel.commands;

import gabriel.core.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isFlying()) {
                Location location = Main.mapHomes.get(player.getName());
                if (location != null) {
                    player.teleport(location);
                    player.sendMessage("Volviendo a casa...");
                } else {
                    location = player.getBedSpawnLocation();
                    player.sendMessage("Volviendo a la cama...");
                    if (location != null) {
                        player.teleport(location);
                    } else player.sendMessage("No tienes cama ni home asignado");
                }
            } else {
                player.sendMessage("No te puedes teletransportar en el aire");
            }
        }
        return false;
    }
}
