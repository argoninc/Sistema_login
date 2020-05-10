package com.github.argoninc.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Usuario {
	private Player player;
	private Location location;
	private ItemStack[] inventory;

	public Usuario(Player player, Location location, ItemStack[] inventory) {
		super();
		this.player = player;
		this.location = location;
		this.inventory = inventory;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ItemStack[] getInventory() {
		return inventory;
	}

	public void setInventory(ItemStack[] inventory) {
		this.inventory = inventory;
	}
}
