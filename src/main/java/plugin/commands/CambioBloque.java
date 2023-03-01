package plugin.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import plugin.core.Main;

public class CambioBloque implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		Location location= new Location(Main.server.getWorld("world"), 0, 0, 0);
		Block block = location.getBlock();
		String blockData= block.getBlockData().getAsString();
		System.out.println(blockData);
		Main.server.createBlockData(blockData);
		return false;
	}

}
