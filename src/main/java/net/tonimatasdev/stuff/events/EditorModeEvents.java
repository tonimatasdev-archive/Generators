package net.tonimatasdev.stuff.events;

import net.tonimatasdev.stuff.api.PlayerManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EditorModeEvents implements Listener {
    @EventHandler
    private void onPlayerInteract(BlockBreakEvent event) {
        if (PlayerManager.generatorEditMap.get(event.getPlayer()) == null) return;

        Block block = event.getBlock();

        if (block.getType() == Material.GREEN_CONCRETE) {
            Material material = PlayerManager.generatorEditMap.get(event.getPlayer()).get(block.getLocation());

            if (material != null) block.setType(material);
        } else {
            PlayerManager.generatorEditMap.get(event.getPlayer()).put(block.getLocation(), block.getType());
            block.setType(Material.GREEN_CONCRETE);
        }

        event.setCancelled(true);
    }
}
