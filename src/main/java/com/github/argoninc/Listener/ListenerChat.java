package com.github.argoninc.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ListenerChat implements Listener {
	
	@EventHandler
	public void onChatSend(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		
		System.out.println(event.getMessage());
	}

}
