package plugin.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.adapters.BalanceAdapter;
import plugin.adapters.LocationAdapter;
import plugin.commands.*;
import plugin.entities.BalanceBean;
import plugin.entities.LocationBean;
import plugin.entities.TPBean;
import plugin.events.*;
import plugin.recipes.CraftingRecipes;
import plugin.recipes.SmeltingRecipes;

import java.io.*;
import java.util.*;

public final class Main extends JavaPlugin {

    public final static String PATHHOMES = "homes.json";
    public final static String PATHBALANCES = "balances.json";
    ConsoleCommandSender mycmd = Bukkit.getConsoleSender();
    public static Map<String, TPBean> mapTps = new HashMap<>();
    public static Map<String, Location> mapHomes = new HashMap<>();
    public static Map<String, BalanceBean> mapBalances = new HashMap<>();
    public static Map<Location, UUID> mapLockedChests = new HashMap<>();
    public static Server server;

    @Override
    public void onEnable() {
        server = getServer();
        loadHomes();
        loadBalances();
        loadRecipes();
        setCommands();
        setEvents();
        mycmd.sendMessage("El plugin se ha iniciado");
    }

    @Override
    public void onDisable() {
        mycmd.sendMessage("El plugin se ha desactivado");
        saveHomes();
        saveBalances();
    }

    private void loadRecipes() {
        CraftingRecipes craftingRecipes = new CraftingRecipes(this);
        SmeltingRecipes smeltingRecipes = new SmeltingRecipes(this);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(craftingRecipes.getEmeraldSword());
        recipes.add(craftingRecipes.getSaddle());
        recipes.add(craftingRecipes.getKnockbackStick());
        recipes.add(craftingRecipes.getStickOfDOOM());
        recipes.add(smeltingRecipes.getClay());
        smeltingRecipes.getIngotForTools().forEach(recipe -> {
            recipes.add(recipe);
        });

        recipes.forEach(recipe -> Bukkit.addRecipe(recipe));
    }

    private void setCommands() {
        getCommand("heal").setExecutor(new Heal());
        getCommand("tpm").setExecutor(new TPM());
        getCommand("tpa").setExecutor(new TPA());
        getCommand("tpaccept").setExecutor(new TPAccept());
        getCommand("tpdeny").setExecutor(new TPDeny());
        getCommand("home").setExecutor(new Home());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("delhome").setExecutor(new DelHome());
        getCommand("balance").setExecutor(new Balance());
        getCommand("pay").setExecutor(new Pay());
        getCommand("cambiarbloque").setExecutor(new CambioBloque());
        getCommand("beacon").setExecutor(new Beacon());
    }

    private void setEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinQuit(), this);
        getServer().getPluginManager().registerEvents(new HitPlayer(), this);
        getServer().getPluginManager().registerEvents(new LockChest(), this);
    }

    private void saveHomes() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocationBean.class, new LocationAdapter());
        Gson gson = builder.create();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(PATHHOMES));
            mapHomes.forEach((playerName, location) -> {
                LocationBean locationBean = new LocationBean(location.getWorld().getName(), playerName, location.getX(), location.getY(), location.getZ());
                pw.println(gson.toJson(locationBean));
            });
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHomes() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocationBean.class, new LocationAdapter());
        Gson gson = builder.create();
        List<LocationBean> locationBeanList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATHHOMES));
            String jsonString;
            while ((jsonString = br.readLine()) != null) {
                locationBeanList.add(gson.fromJson(jsonString, LocationBean.class));
            }
            locationBeanList.forEach(locationBean -> {
                Location location = new Location(getServer().getWorld(locationBean.getWorldName()), locationBean.getX(), locationBean.getY(), locationBean.getZ());
                mapHomes.put(locationBean.getPlayerName(), location);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveBalances() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BalanceBean.class, new BalanceAdapter());
        Gson gson = builder.create();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(PATHBALANCES));
            mapBalances.forEach((playerName, balance) -> {
                BalanceBean balanceBean = new BalanceBean(balance.getPlayerName(), balance.getAmount());
                pw.println(gson.toJson(balanceBean));
            });
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBalances() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BalanceBean.class, new BalanceAdapter());
        Gson gson = builder.create();
        List<BalanceBean> balanceBeanList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATHBALANCES));
            String jsonString;
            while ((jsonString = br.readLine()) != null) {
                balanceBeanList.add(gson.fromJson(jsonString, BalanceBean.class));
            }
            balanceBeanList.forEach(balanceBean -> {
                mapBalances.put(balanceBean.getPlayerName(), balanceBean);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
