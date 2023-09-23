package net.tonimatasdev.stuff.api;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    public static final Map<Player, Map<Location, Material>> generatorEditMap = new HashMap<>();
}
