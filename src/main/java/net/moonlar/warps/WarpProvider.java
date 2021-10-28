package net.moonlar.warps;

import org.bukkit.Location;

import java.util.List;

public interface WarpProvider {

  Location getWarp(String warpName);

  boolean setWarp(String warpName, Location location);

  boolean deleteWarp(String warpName);

  boolean warpExists(String warpName);

  List<String> listWarps();
}
