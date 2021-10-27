package net.moonlar.warps;

import com.earth2me.essentials.Warps;
import org.bukkit.Location;

import java.util.List;

public interface WarpProvider {

  Location getWarp(String warpName);

  void setWarp(String warpName, String location);

  void deleteWarp(String warpName);

  List<Location> listWarps();
}
