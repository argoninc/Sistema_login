package com.github.argoninc.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ListenerChat implements Listener {

	@EventHandler
	public void onChatSend(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String chat = event.getMessage();
		
		if(!isValidChatNotLogged(chat) && !ListenerJoin.isLogged(player)) {
			System.out.println("Não estaria logado para usar");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String chat = event.getMessage();
		
		if(!isValidChatNotLogged(chat) && !ListenerJoin.isLogged(player)) {
			System.out.println("Não estaria logado para usar");
			event.setCancelled(true);
		}
	} 

	public boolean isValidChatNotLogged(String message) {
		if (message.contains("login") || message.contains("register") || message.contains("logar")
				|| message.contains("registrar") || message.contains("registro")) {
			return true;
		}

		return false;
	}

}
