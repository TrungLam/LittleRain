package com.lamtrung.littlerain;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class LittleRain extends JavaPlugin implements Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		// TODO Auto-generated method stub
		return super.onCommand(sender, command, label, args);
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onRain(WeatherChangeEvent event) {
		
		if (event.toWeatherState() == true) {
			Random rand = new Random();
			int value = rand.nextInt(100) + 1;
			
			if (value < 60) {
				getServer().broadcastMessage(ChatColor.DARK_RED + "The gods are cruel, insufferable rain pours down upon you");
			}
			else {
				event.setCancelled(true);
				getServer().broadcastMessage(ChatColor.BLUE + "The gods are kind, another few days of clear skies");
			}
			
		}
	}
	
	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR) {
			if (event.getPlayer().getItemInHand().getType().equals(Material.GOLD_INGOT)) {
				if (event.getPlayer().getWorld().hasStorm()) {
					event.getPlayer().getWorld().setStorm(false);
					event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
					getServer().broadcastMessage( ChatColor.GOLD + event.getPlayer().getName() + " has harnessed the power of the gods and brought clear skies");
				}
				else {
					event.getPlayer().sendMessage(ChatColor.STRIKETHROUGH + "This is not the time for that, fool");
				}
			}
		}
	}
	
	

}
