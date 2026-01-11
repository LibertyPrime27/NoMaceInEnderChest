package com.example.nomaceenderchest;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class NoMaceEnderChest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        Inventory top = view.getTopInventory();

        if (top == null || top.getType() != org.bukkit.event.inventory.InventoryType.ENDER_CHEST) {
            return;
        }

        ItemStack current = event.getCurrentItem();
        ItemStack cursor = event.getCursor();

        boolean isMace =
                (current != null && current.getType() == Material.MACE) ||
                (cursor != null && cursor.getType() == Material.MACE);

        if (!isMace) {
            return;
        }

        event.setCancelled(true);

        if (event.getWhoClicked() instanceof org.bukkit.entity.Player player) {
            player.sendMessage("You cannot store a mace in an ender chest.");
        }
    }
}