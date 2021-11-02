package net.moonlar.warps.listeners;

import net.moonlar.warps.MoonlarWarps;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

  private final MoonlarWarps plugin;

  public MenuListener(MoonlarWarps plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if(event.getInventory() == plugin.getWarpsInventory()) {
      event.setCancelled(true);
    }
  }
}
