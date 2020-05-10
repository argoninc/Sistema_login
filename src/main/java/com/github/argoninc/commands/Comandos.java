package com.github.argoninc.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import com.github.argoninc.Listener.ListenerJoin;
import com.github.argoninc.criptografy.Cripto;
import com.github.argoninc.main.Principal;
import com.github.argoninc.main.Usuario;

public class Comandos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		String cmd = command.getName();

		// Verifica se é um player que está diigtando
		if (sender instanceof Player == false) {
			return true;
		}

		Player player = (Player) sender;

		// Só deixar fazer o comando se não estiver logado
		if (cmd.equalsIgnoreCase("login")) {
			if (ListenerJoin.isLogged(player)) {
				sender.sendMessage(ChatColor.RED + "Parece que você já está logado!");
				return true;
			}
			if (args.length == 1) {
				String uuid = player.getUniqueId().toString();
				String passTyped = args[0];
				Double hash = (Double) ((JSONObject) Principal.banco.get(uuid)).get("hash");
				String md5Esperado = (String) ((JSONObject) Principal.banco.get(uuid)).get("pass");
				String md5Calculado = Cripto.getPassCript(passTyped, hash);

				if (md5Calculado.equals(md5Esperado)) {
					liberarPlayer(player);
					sender.sendMessage(ChatColor.GREEN + "Logado com sucesso!");
				} else {
					sender.sendMessage(ChatColor.RED + "Senha inválida");
					sender.sendMessage(ChatColor.RED + "Use novamente /login senha");
				}

				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Parece que você digitou mais do que precisava =D");
				sender.sendMessage(ChatColor.RED + "Use /login senha");
				return true;
			}

		} else if (cmd.equalsIgnoreCase("register")) {
			if (ListenerJoin.isLogged(player)) {
				sender.sendMessage(ChatColor.RED + "Parece que você já está logado!");
				return true;
			}
			if (args.length == 2) {
				
				if (args[1].equals(args[0])) {

					String uuid = player.getUniqueId().toString();
					Double hash = null;

					if (!((JSONObject) Principal.banco.get(uuid)).get("pass").equals("")) {
						sender.sendMessage(ChatColor.RED + "Parece que você já é registrado");
						sender.sendMessage(ChatColor.RED + "Use /login senha");
						return true;
					}

					hash = Cripto.generateHash();

					JSONObject js = new JSONObject();

					js.put("pass", Cripto.getPassCript(args[0], hash));
					js.put("hash", hash);

					Principal.banco.set(uuid, js);

					liberarPlayer(player);

					sender.sendMessage(ChatColor.GREEN + "Registrado com sucesso!");

					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Parece que suas senhas não coincidem");
					return true;
				}

			} else if (args.length == 1) {
				sender.sendMessage(ChatColor.RED + "Parece que você não confirmou sua senha");

				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Digite sua senha");
				return true;
			}
		}

		return true;
	}

	public void liberarPlayer(Player player) {
		Usuario u = ListenerJoin.getUserFromUUID(player);

		player.teleport(u.getLocation());
		player.getInventory().setContents(u.getInventory());
		player.updateInventory();

		ListenerJoin.deleteUser(player);
	}

}
