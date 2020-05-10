package com.github.argoninc.main;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.argoninc.Listener.ListenerChat;
import com.github.argoninc.Listener.ListenerJoin;
import com.github.argoninc.commands.Comandos;


public class Principal extends JavaPlugin {
	@Override

	public void onEnable() {
		
		ListenerJoin.users = new ArrayList<Usuario>();
		
		//Registra listeners
		getServer().getPluginManager().registerEvents(new ListenerJoin(), this);
		getServer().getPluginManager().registerEvents(new ListenerChat(), this);
		
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
