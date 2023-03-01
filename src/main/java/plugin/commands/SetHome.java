package plugin.commands;

import plugin.core.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.isFlying()) {
                Main.mapHomes.put(player.getName(),player.getLocation());
                player.sendMessage("Casa establecida");
            } else player.sendMessage("Necesitas estar en el suelo para establecer tu casa");
        }
        return false;
    }
}
