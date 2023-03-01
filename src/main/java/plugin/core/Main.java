package plugin.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import plugin.adapters.LocationAdapter;
import plugin.commands.*;
import plugin.entities.LocationBean;
import plugin.entities.TPBean;
import plugin.events.PlayerJoinQuit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    public final static String PATHHOMES = "homes.json";
    ConsoleCommandSender mycmd = Bukkit.getConsoleSender();
    public static Map<String, TPBean> mapTps = new HashMap<>();
    public static Map<String, Location> mapHomes = new HashMap<>();
    public static Map<String, BalanceBean> mapBalances = new HashMap<>();
    public static Server server;
    
    @Override
    public void onEnable() {
        server = getServer();
        loadHomes();
        setCommands();
        setEvents();
        mycmd.sendMessage("El plugin se ha iniciado");
    }

    @Override
    public void onDisable() {
        mycmd.sendMessage("El plugin se ha desactivado");
        saveHomes();
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
    }

    private void setEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinQuit(), this);
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
}
