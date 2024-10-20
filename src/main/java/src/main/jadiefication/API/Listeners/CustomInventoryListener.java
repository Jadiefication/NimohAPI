package src.main.jadiefication.API.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CustomInventoryListener implements Listener {

    private Map<InventoryHolder, Map<Integer, Consumer<InventoryClickEvent>>> inventories;

    public CustomInventoryListener(Map<InventoryHolder, Map<Integer, Consumer<InventoryClickEvent>>> inventories) {
        this.inventories = inventories;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (inventories.containsKey(holder)) {
            event.setCancelled(true);
            int clickedSlot = event.getRawSlot();
            Map<Integer, Consumer<InventoryClickEvent>> slotActions = inventories.get(holder);
            if (slotActions.containsKey(clickedSlot)) {
                slotActions.get(clickedSlot).accept(event);
            }
        }
    }
}