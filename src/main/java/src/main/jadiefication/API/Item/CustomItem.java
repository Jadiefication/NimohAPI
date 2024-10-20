package src.main.jadiefication.API.Item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import src.main.jadiefication.API.Listeners.CustomItemListener;

import java.util.List;
import java.util.function.Consumer;

public class CustomItem {

    public static ItemStack createItem(Material material, Component name, List<Component> lore, int CustomModelData) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = createItemMeta(item, name, lore, CustomModelData);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemMeta createItemMeta(ItemStack item, Component name, List<Component> lore, int CustomModelData) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        meta.setCustomModelData(CustomModelData);
        return meta;
    }

    public static ItemStack createAdvancedItem(Material material, Component name, List<Component> lore, int CustomModelData, Consumer<PlayerInteractEvent> itemFunction) {
        ItemStack item = createItem(material, name, lore, CustomModelData);
        CustomItemListener.registerCustomItemAction(CustomModelData, itemFunction);
        return item;
    }
}
