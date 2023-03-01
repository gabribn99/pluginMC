package gabriel.commands;

import gabriel.core.Main;
import gabriel.entities.TPBean;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPA implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player playerSender = (Player) sender;
        Player playerReceiver = (args != null) ? Bukkit.getPlayer(args[0]) : null;
        if (playerReceiver == null) {
            sender.sendMessage("El comando esta incompleto [Falta el destinatario]");
            return false;
        }
        if (sender instanceof Player) {
            playerSender.sendMessage("Petición de tp enviada");
            playerReceiver.sendMessage(playerSender.getName() + " quiere teletransportarse contigo.\n- \"tpaccept\" para aceptar\n- \"tpdeny\" para denegar");
            Main.mapTps.put(playerReceiver.getName(), new TPBean(playerSender, playerReceiver));
        }
        return false;
    }
}
