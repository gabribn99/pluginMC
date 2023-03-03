package plugin.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import plugin.entities.ToolsBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmeltingRecipes {

    Plugin plugin;

    public SmeltingRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    public FurnaceRecipe getClay() {
        ItemStack item = new ItemStack(Material.CLAY);
        NamespacedKey key = new NamespacedKey(plugin, "clay");
        FurnaceRecipe recipe = new FurnaceRecipe(key, item, Material.GLASS, 0, 200);
        return recipe;
    }
}
