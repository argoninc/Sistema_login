package com.github.argoninc.main;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.argoninc.Listener.ListenerChat;
import com.github.argoninc.Listener.ListenerDamage;
import com.github.argoninc.Listener.ListenerJoin;
import com.github.argoninc.commands.Comandos;
import com.github.rillis.dao.DB;


public class Principal extends JavaPlugin {
	
	public static DB banco = null;
	public static DB config = null;
	
	@Override
	public void onEnable() {
		
		ListenerJoin.users = new ArrayList<Usuario>();
		
		banco = new DB("argoninc/login.json");
		config= new DB("argoninc/defaultSpawn.cfg");
		
		if(!config.has("xyz")) {
			config.set("xyz", "world,-191,73,-92");
		}
		
		//Registra listeners
		getServer().getPluginManager().registerEvents(new ListenerJoin(), this);
		getServer().getPluginManager().registerEvents(new ListenerChat(), this);
		getServer().getPluginManager().registerEvents(new ListenerDamage(), this);
		
		//Registra comandos
		this.getCommand("login").setExecutor(new Comandos());
		this.getCommand("register").setExecutor(new Comandos());
			
		System.out.println("***************** ENABLE LOGIN ******************");
	}

	@Override
	public void onDisable() {
		System.out.println("***************** DISABLE LOGIN ******************");
	}

}
