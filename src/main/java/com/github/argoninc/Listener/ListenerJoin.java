package com.github.argoninc.Listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONObject;

import com.github.argoninc.main.LocationSpawn;
import com.github.argoninc.main.Principal;
import com.github.argoninc.main.Usuario;

public class ListenerJoin implements Listener {

	public static ArrayList<Usuario> users;

	@EventHandler

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		String localSpawn = null;
		
		if(!Principal.banco.has(uuid)) {
			JSONObject js = new JSONObject();
			
			js.put("pass", "");
			js.put("hash", "");
			
			Principal.banco.set(uuid, js);
			
			}

		if(isLogged(player)) {
			users.add(new Usuario(player, player.getLocation(), player.getInventory().getContents()));
			System.out.println(player.getName() + " adicionado com sucesso!");
		}
		
		// Colocar por config
		
		
		localSpawn = (String) Principal.config.get("xyz");
		
		player.teleport(LocationSpawn.StringtoLocation(localSpawn));
		player.getInventory().clear();

		player.sendMessage(ChatColor.AQUA + "Seja Bem-vindo ao servidor!");
		player.sendMessage(ChatColor.AQUA + "Realize o login com /login senha");
		player.sendMessage(ChatColor.AQUA + "Ou utilize /register senha confirmar-senha");

	}

	public static boolean isLogged(Player player) {

		for (Usuario u : users) {
			if (u.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				return false;
			}
		}
		return true;
	}

	public static void deleteUser(Player player) {
		Usuario temp = null;
		for (Usuario u : users) {
			if (u.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				temp =u;
			}
		}
		
		users.remove(temp);
	}
	
	public static Usuario getUserFromUUID(Player player) {
		for (Usuario u : users) {
			if (u.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				return u;
			}
		}
		return null;
	}
}
