package plugin.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.core.Main;

public class CambioBloque implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		Player player=(Player) sender;
		Location location;
		Block block;
		String blockDataString;
		  if (args != null & args.length > 0) {
			  location = new Location(Main.server.getWorld(player.getLocation().getWorld().getName()), Integer.parseInt(args[0]), Integer.parseInt(args[1]),Integer.parseInt(args[2]));
			  block= location.getBlock();
			  blockDataString = block.getBlockData().getAsString();
				System.out.println(blockDataString);
			  
			  blockDataString="minecraft:"+ args[3];
		  }
		  else {
			  location = new Location(Main.server.getWorld(player.getLocation().getWorld().getName()), player.getLocation().getX()+5, player.getLocation().getY(), player.getLocation().getZ());
			  block= location.getBlock();
			  blockDataString = block.getBlockData().getAsString();
				System.out.println(blockDataString);
			  blockDataString="minecraft:diamond_block";
		  }
		
		BlockData blockData = Main.server.createBlockData(blockDataString);
		block.setBlockData(blockData);
		return false;
	}

}
