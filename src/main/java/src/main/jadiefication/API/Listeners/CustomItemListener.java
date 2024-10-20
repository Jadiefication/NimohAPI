package src.main.jadiefication.API.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CustomItemListener implements Listener {

    private static Map<Integer, Consumer<PlayerInteractEvent>> customItemActions = new HashMap<>();

    public static void registerCustomItemAction(int customModelData, Consumer<PlayerInteractEvent> action) {
        customItemActions.put(customModelData, action);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
            int customModelData = item.getItemMeta().getCustomModelData();
            Consumer<PlayerInteractEvent> action = customItemActions.get(customModelData);
            if (action != null) {
                action.accept(event);
            }
        }
    }
}
