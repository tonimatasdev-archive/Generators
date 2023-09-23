package net.tonimatasdev.stuff.commands;

import net.tonimatasdev.stuff.api.PlayerManager;
import net.tonimatasdev.stuff.api.generator.Generator;
import net.tonimatasdev.stuff.api.generator.GeneratorType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args[0].equalsIgnoreCase("create")) {
                PlayerManager.generatorEditMap.put(player, new HashMap<>());
            }

            if (args[0].equalsIgnoreCase("save")) {
                Map<Location, Material> blockMaterialMap = PlayerManager.generatorEditMap.get(player);
                List<Location> locations = new ArrayList<>();

                for (Location location : blockMaterialMap.keySet()) {
                    locations.add(new Location(player.getWorld(), location.getX(), location.getY() + 1, location.getZ()));
                    player.getWorld().getBlockAt(location).setType(blockMaterialMap.get(location));
                }

                Generator generator = new Generator(player.getWorld(), player.getLocation(), 10, locations, GeneratorType.WHEAT);
                generator.start();
                Generator.generators.add(generator);

                PlayerManager.generatorEditMap.remove(player);
            }
        }
        return true;
    }
}
