package com.github.argoninc.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationSpawn {
	public static Location StringtoLocation(String str) {
		String[] temp = str.split(",");
		return new Location(Bukkit.getWorld(temp[0]), Double.parseDouble(temp[1]), Double.parseDouble(temp[2]),
				Double.parseDouble(temp[3]));
	}

	public static String LocationtoString(Location l) {
		return l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ();
	}
}
