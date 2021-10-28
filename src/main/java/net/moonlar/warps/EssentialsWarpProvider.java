package net.moonlar.warps;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;
import com.earth2me.essentials.commands.WarpNotFoundException;
import net.ess3.api.InvalidWorldException;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;

public class EssentialsWarpProvider implements WarpProvider {

  private final Warps warps;

  public EssentialsWarpProvider() {
    Essentials essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

    if(essentials == null) {
      throw new UnsupportedOperationException("Essentials not installed!");
    }

    if(!essentials.isEnabled()) {
      throw new UnsupportedOperationException("Essentials not enabled!");
    }

    this.warps = essentials.getWarps();
  }

  @Override
  public Location getWarp(String warpName) {
    try {
      return warps.getWarp(warpName);
    } catch (WarpNotFoundException | InvalidWorldException e) {
      return null;
    }
  }

  @Override
  public boolean setWarp(String warpName, Location location) {
    try {
      warps.setWarp(warpName, location);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  @Override
  public boolean deleteWarp(String warpName) {
    try {
      warps.removeWarp(warpName);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  @Override
  public boolean warpExists(String warpName) {
    return warps.isWarp(warpName);
  }

  @Override
  public List<String> listWarps() {
    return (List<String>) warps.getList();
  }
}
