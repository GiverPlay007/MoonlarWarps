package net.moonlar.warps;

import net.moonlar.warps.commands.WarpCommand;
import net.moonlar.warps.providers.EssentialsWarpProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoonlarWarps extends JavaPlugin {

  private WarpProvider warpProvider;

  @Override
  public void onEnable() {
    try {
      warpProvider = new EssentialsWarpProvider();
    } catch (UnsupportedOperationException e) {
      e.printStackTrace();
      getServer().getPluginManager().disablePlugin(this);
      return;
    }

    getLogger().fine("Using Essentials as warp provider");

    getCommand("warp").setExecutor(new WarpCommand(this));
  }

  @Override
  public void onDisable() {
    warpProvider = null;
  }

  public WarpProvider getWarpProvider() {
    return warpProvider;
  }
}
