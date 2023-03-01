package plugin.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.core.Main;

public class Beacon implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		Player player = (Player) sender;
		String WorldName = player.getLocation().getWorld().getName();
		Location location = new Location(Main.server.getWorld(WorldName),
				player.getLocation().getX() + 5, player.getLocation().getY(), player.getLocation().getZ());
		Location PlayerLocation= player.getLocation();
		
		Location bloqueACambiar;
		Block block;
		String blockDataString;
		blockDataString = "minecraft:diamond_block";
		BlockData blockData = Main.server.createBlockData(blockDataString);
		double sizeX=9;
		double sizeZ=9;
		if(PlayerLocation.getX() + 5<0) {
			sizeX= -9;
		}
		if(PlayerLocation.getZ()<0) {
			sizeZ= -9;
		}
		for (double y = PlayerLocation.getY(); y <PlayerLocation.getY()+4; y++) {
			System.out.println("Y entra.");
			for (double x = PlayerLocation.getX() - 5; x > PlayerLocation.getX()-5 +sizeX; x--) {
				System.out.println("X entra.");
				for (double z = PlayerLocation.getZ()+4; z > PlayerLocation.getZ()+4+sizeZ ; z--) {
					System.out.println("Z entra.");
				bloqueACambiar= new Location(Main.server.getWorld(WorldName),x, y, z);
				block=bloqueACambiar.getBlock();
				block.setBlockData(blockData);
				System.out.println("Cambiando bloque en x: " +x+",y: "+y+",z: "+z);
				}
			}
			PlayerLocation.setZ(PlayerLocation.getZ()-1);
			PlayerLocation.setX(PlayerLocation.getX()-1);
			sizeZ=sizeZ+2;
			sizeX=sizeX+2;
		}
		location = new Location(Main.server.getWorld(WorldName),
				player.getLocation().getX() + 9, player.getLocation().getY()+4, player.getLocation().getZ());
		block = location.getBlock();
		blockDataString = "minecraft:beacon";
		blockData = Main.server.createBlockData(blockDataString);
		block.setBlockData(blockData);
		
		
		
		return false;
	}

}
