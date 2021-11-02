package net.moonlar.warps;

import net.moonlar.warps.commands.WarpCommand;
import net.moonlar.warps.providers.EssentialsWarpProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public final class MoonlarWarps extends JavaPlugin {

  private final YamlConfiguration menuConfig = new YamlConfiguration();

  private Inventory warpsInventory;
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
    reload();
  }

  @Override
  public void onDisable() {
    warpProvider = null;
  }

  public void reload() {
    loadConfig(menuConfig, "menus.yml");

    ConfigurationSection warps = menuConfig.getConfigurationSection("Warps");

    if(warps == null) {
      throw new YAMLException("Missing Warps menu");
    }

    String title = translateColors(warps.getString("Title", "Warps"));
    int size = warps.getInt("Size", 27);

    warpsInventory = getServer().createInventory(null, size, title);

    ConfigurationSection icons = warps.getConfigurationSection("WarpIcons");

    if(icons == null) {
      throw new YAMLException("Missing warp icons");
    }

    for(String key : icons.getKeys(false)) {
      ConfigurationSection icon = icons.getConfigurationSection(key);
      ItemStack item = new ItemStack(Material.getMaterial(icon.getString("Material")));
      ItemMeta meta = item.getItemMeta();

      meta.setDisplayName(translateColors(icon.getString("Title")));
      meta.setLore(icon.getStringList("Lore").stream().map(this::translateColors).collect(Collectors.toList()));

      item.setItemMeta(meta);
      warpsInventory.setItem(icon.getInt("Slot"), item);
    }
  }

  private String translateColors(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  private void loadConfig(YamlConfiguration config, String path) {
    File file = new File(getDataFolder(), path);

    if(!file.exists()) {
      saveResource(path, false);
    }

    try {
      config.load(file);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  public YamlConfiguration getMenuConfig() {
    return menuConfig;
  }

  public Inventory getWarpsInventory() {
    return warpsInventory;
  }

  public WarpProvider getWarpProvider() {
    return warpProvider;
  }
}
