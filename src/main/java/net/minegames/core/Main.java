package net.minegames.core;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.event.player.*;

public class Main extends PluginBase implements Listener {

    String lobbyWorld;
    String bossBarText;
    boolean gm2onJoin;
    boolean lobbyProtection;
    boolean disableWeather;
    boolean enableCommands;
    boolean spawnLightnings;

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        this.lobbyWorld = this.getConfig().getString("LobbyWorld");
        this.bossBarText = this.getConfig().getString("BossBarText").replace("§", "\u00A7").replace("%n", "\n");
        this.gm2onJoin = this.getConfig().getBoolean("Gm2OnJoin");
        this.lobbyProtection = this.getConfig().getBoolean("LobbyProtection");
        this.disableWeather = this.getConfig().getBoolean("DisableWeather");
        this.enableCommands = this.getConfig().getBoolean("EnableCommands");
        this.spawnLightnings = this.getConfig().getBoolean("SpawnLightnings");
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getLevel().getName().equals(lobbyWorld) && lobbyProtection && !e.getPlayer().isCreative()) e.setCancelled(true);
    }
    
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getLevel().getName().equals(lobbyWorld) && lobbyProtection && !e.getPlayer().isCreative()) e.setCancelled(true);
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (gm2onJoin) p.setGamemode(2);
        if (bossBarText != null) p.createBossBar(bossBarText, 100);
    }
    
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (disableWeather) e.setCancelled(true);
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return true;
    }
}
