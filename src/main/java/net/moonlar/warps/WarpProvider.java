package net.moonlar.warps;

import org.bukkit.Location;

import java.util.List;

public interface WarpProvider {

  Location getWarp(String warpName);

  void setWarp(String warpName, Location location);

  void deleteWarp(String warpName);

  List<Location> listWarps();
}
