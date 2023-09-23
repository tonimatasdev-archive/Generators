package net.tonimatasdev.stuff.api.generator;

import net.tonimatasdev.stuff.Stuff;
import net.tonimatasdev.stuff.api.Line;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record Generator(World world, Location location, long time, List<Location> locations, GeneratorType type) {
    public static final List<Generator> generators = new ArrayList<>();

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                generate();
            }
        }.runTaskTimer(Stuff.getInstance(), 0, time*20);
    }

    public void generate() {
        int random = new Random().nextInt(locations.size());

        Block block = world.getBlockAt(locations.get(random));
        Location blockLocation = new Location(world, block.getLocation().getX() + 0.5, block.getLocation().getY() + 0.5, block.getLocation().getZ() + 0.5);


        if (type == GeneratorType.WHEAT) {
            if (block.getType() != Material.WHEAT) {
                new Line(location, blockLocation).generateDustLine(Color.YELLOW);
                block.setType(Material.WHEAT);

                block.applyBoneMeal(BlockFace.UP);

                if (block.getBlockData() instanceof Ageable blockData) {
                    blockData.setAge(blockData.getMaximumAge());
                    block.setBlockData(blockData);
                }
            } else {
                generate();
            }
        }
    }
}
