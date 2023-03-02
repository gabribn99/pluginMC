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
        FurnaceRecipe recipe = new FurnaceRecipe(key, item, Material.GLASS, 0, 2000);
        return recipe;
    }

    public List<FurnaceRecipe> getIngotForTools() {
        Map<ToolsBean, Integer> tools = buildToolsMap();

        List<FurnaceRecipe> furnaceRecipes = new ArrayList<>();

        tools.forEach((material, amount) -> {
            ItemStack item = new ItemStack(material.getComposition());
            NamespacedKey key = new NamespacedKey(plugin, material.getComposition().getKey().getKey());
            item.setAmount(amount);
            furnaceRecipes.add(new FurnaceRecipe(key, item, material.getItem(), 0, 200));
        });

        return furnaceRecipes;
    }

    private Map<ToolsBean, Integer> buildToolsMap() {
        Map<ToolsBean, Integer> tools = new HashMap<>();

        //IRON
        tools.put(new ToolsBean(Material.IRON_CHESTPLATE, Material.IRON_INGOT), 8);
        tools.put(new ToolsBean(Material.IRON_LEGGINGS, Material.IRON_INGOT), 7);
        tools.put(new ToolsBean(Material.IRON_HELMET, Material.IRON_INGOT), 5);
        tools.put(new ToolsBean(Material.IRON_BOOTS, Material.IRON_INGOT), 4);
        tools.put(new ToolsBean(Material.IRON_AXE, Material.IRON_INGOT), 3);
        tools.put(new ToolsBean(Material.IRON_PICKAXE, Material.IRON_INGOT), 3);
        tools.put(new ToolsBean(Material.IRON_HOE, Material.IRON_INGOT), 2);
        tools.put(new ToolsBean(Material.IRON_SWORD, Material.IRON_INGOT), 2);
        tools.put(new ToolsBean(Material.IRON_SHOVEL, Material.IRON_INGOT), 1);

        //GOLD
        tools.put(new ToolsBean(Material.GOLDEN_CHESTPLATE, Material.GOLD_INGOT), 8);
        tools.put(new ToolsBean(Material.GOLDEN_LEGGINGS, Material.GOLD_INGOT), 7);
        tools.put(new ToolsBean(Material.GOLDEN_HELMET, Material.GOLD_INGOT), 5);
        tools.put(new ToolsBean(Material.GOLDEN_BOOTS, Material.GOLD_INGOT), 4);
        tools.put(new ToolsBean(Material.GOLDEN_AXE, Material.GOLD_INGOT), 3);
        tools.put(new ToolsBean(Material.GOLDEN_PICKAXE, Material.GOLD_INGOT), 3);
        tools.put(new ToolsBean(Material.GOLDEN_HOE, Material.GOLD_INGOT), 2);
        tools.put(new ToolsBean(Material.GOLDEN_SWORD, Material.GOLD_INGOT), 2);
        tools.put(new ToolsBean(Material.GOLDEN_SHOVEL, Material.GOLD_INGOT), 1);

        return tools;
    }
}
