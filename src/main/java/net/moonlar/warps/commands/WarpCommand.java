package net.moonlar.warps.commands;

import net.moonlar.warps.MoonlarWarps;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WarpCommand implements CommandExecutor {

  private final MoonlarWarps plugin;

  public WarpCommand(MoonlarWarps plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Esse comando só deve ser executado por jogadores.");
      return true;
    }

    Player player = (Player) sender;

    if(args.length == 0) {
      player.openInventory(plugin.getWarpsInventory());
      return true;
    }

    Location loc = plugin.getWarpProvider().getWarp(args[0]);

    if(loc == null) {
      player.sendMessage(ChatColor.RED + "Essa warp não existe!");
      return true;
    }

    player.sendMessage(ChatColor.GREEN + "Teleportando...");
    player.teleport(loc, PlayerTeleportEvent.TeleportCause.COMMAND);
    return true;
  }
}
