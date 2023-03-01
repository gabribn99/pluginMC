package plugin.recipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MyRecipes {
    Plugin plugin;

    public MyRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    public ShapedRecipe getEmeraldSword() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Emerald Sword");
        item.setItemMeta(meta);
        item.addEnchantment(Enchantment.DAMAGE_ALL, 5);

        NamespacedKey key = new NamespacedKey(plugin, "emerald_sword");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" E ", " E ", " S ");
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getSaddle() {
        ItemStack item = new ItemStack(Material.SADDLE);
        NamespacedKey key = new NamespacedKey(plugin, "saddle");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("CCC", "L L");
        recipe.setIngredient('C', Material.LEATHER);
        recipe.setIngredient('L', Material.LEAD);
        return recipe;
    }

    public ShapedRecipe getKnockbackStick() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Knockback Stick");
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 400);

        NamespacedKey key = new NamespacedKey(plugin, "knockback_stick");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" B ","BSB", " B ");
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('B',Material.SLIME_BLOCK);
        return recipe;
    }

    public ShapedRecipe getStickOfDOOM() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Stick of Doom");
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin, "doom_stick");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" F ","ESN");
        recipe.setIngredient('F',Material.WITHER_ROSE);
        recipe.setIngredient('E',Material.ENDER_PEARL);
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('N',Material.NETHER_STAR);

        return recipe;
    }
}
