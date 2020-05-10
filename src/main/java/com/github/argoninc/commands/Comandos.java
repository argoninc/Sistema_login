package com.github.argoninc.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.argoninc.Listener.ListenerJoin;
import com.github.argoninc.main.Usuario;

public class Comandos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		String cmd = command.getName();
		
		//Verifica se � um player que est� diigtando
		if(sender instanceof Player == false) {
			return true;
		}
		
		Player player = (Player) sender;
		
		//S� deixar fazer o comando se n�o estiver logado
		if (cmd.equalsIgnoreCase("login")) {
			Usuario u = ListenerJoin.getUserFromUUID(player);
			
			player.teleport(u.getLocation());
			player.getInventory().setContents(u.getInventory());
			player.updateInventory();
			
			System.out.println("++++++++++++++++++++++"+ u.getInventory());
			
			ListenerJoin.deleteUser(player);
		} else if (cmd.equalsIgnoreCase("register")) {
			if (args.length == 2) {
				sender.sendMessage(ChatColor.RED + "Parab�ns");
				return true;
			} else if (args.length == 1) {
				sender.sendMessage(ChatColor.RED + "Parece que voc� n�o confirmou sua senha");

				return false;
			}else {
				sender.sendMessage(ChatColor.RED + "Digite sua senha");
			}
		}

		return true;
	}

}
